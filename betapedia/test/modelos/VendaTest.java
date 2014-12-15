package modelos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import org.hamcrest.Matcher;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class VendaTest {

	private static EntityManager em;

	// Definindo a entidade de persistencia da betapedia-test.
	@BeforeClass
	public static void startEntityManager() throws Exception {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("betapedia-test");
		em = emf.createEntityManager();
	}

	// Testar insercao no banco do objeto sem nome do representante que fez a
	// venda.
	// O roolback desfaz a falha de insercao pois não ha nome a ser inserido.
	@Test(expected = RollbackException.class)
	public void nomeRepresentanteObrigatorio() {
		Representante r = new Representante();
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();

	}

	@Test
	public void adicionarProdutos() {

		Representante representante = new Representante("Giraia");

		Produto produtoUm = new Produto("Farinha de Mandioca DeLa",
				"Farinha tipo 2", 10.00);
		Produto produtoDois = new Produto("Coca-Cola", "lata 290", 10.00);
		Produto produtoTres = new Produto("Massa para pastel", "pre cozida",
				10.00);

		Venda venda = new Venda();
		venda.setRepresentante(representante);
		venda.addProdutos(produtoUm);
		venda.addProdutos(produtoDois);
		venda.addProdutos(produtoTres);

		em.getTransaction().begin();
		em.persist(representante);
		em.persist(produtoUm);
		em.persist(produtoDois);
		em.persist(produtoTres);
		em.persist(venda);
		em.getTransaction().commit();

		TypedQuery<Venda> query = em.createQuery(
				"select v from Venda v where v.id = :id", Venda.class);
		query.setParameter("id", venda.getId());
		Venda sQuery = query.getSingleResult();

		assertThat(venda.getProdutos(), Is.is(sQuery.getProdutos()));

		// comparar total da venda com um valor X.
		// Executa a soma dos valores dos produtos.
		venda.calculaTotalVenda();

		// Compara se o total da venda e a comissao sao o esperado.
		Assert.assertEquals(venda.getTotalVenda(), new Double(30.00));
		Assert.assertEquals(venda.getComissaoRepresentante(), new Double(3.00));
		
	}

	private void assertThat(List<Produto> produtos,
			Matcher<List<Produto>> matcher) {
	}
}
