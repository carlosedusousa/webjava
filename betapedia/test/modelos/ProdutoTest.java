package modelos;

import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import org.junit.BeforeClass;
import org.junit.Test;

public class ProdutoTest {

	private static EntityManager em;

	// Definindo a entidade de persistencia da betapedia-test.
	@BeforeClass
	public static void startEntityManager() throws Exception {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("betapedia-test");
		em = emf.createEntityManager();
	}

	// Testar insercao no banco do objeto sem nome.
	// O roolback desfaz a falha de insercao pois não ha nome a ser inserido.
	@Test(expected = RollbackException.class)
	public void nomeProdutoObrigatorio() {
		Produto p = new Produto();
		p.setValor(8.99);
		p.setDescricao("Produto sem nome");
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();

	}

	// Teste para verificar se o nome é único na base.
	@Test(expected = RollbackException.class)
	public void nomeProdutoUnico() {
		Produto produtoUm = new Produto("Farinha de Mandioca DeLa",
				"Farinha tipo 2", 11.99);
		Produto produtoDois = new Produto("Farinha de Mandioca DeLa",
				"Farinha tipo 2", 11.99);
		em.getTransaction().begin();
		em.persist(produtoUm);
		em.persist(produtoDois);
		em.getTransaction().commit();
	}

	// Teste para insercao do nome e consulta da insercao com sucesso.
	@Test
	public void inserirProduto() {
		Produto produto = new Produto("Nescal", "Lata 500g", 6.99);
		em.getTransaction().begin();
		em.persist(produto);
		em.getTransaction().commit();
	}

	// teste de remocao de produto.
	@Test
	public void removerProduto() {
		Produto produto = new Produto("Farinha de Mandioca DeLa",
				"Farinha tipo 2", 11.99);
		em.getTransaction().begin();
		em.persist(produto);
		em.getTransaction().commit();
		Integer pId = produto.getId();
		Produto p = em.find(Produto.class, pId);
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
		Produto rRemovido = em.find(Produto.class, pId);
		assertNull(rRemovido);
	}
}
