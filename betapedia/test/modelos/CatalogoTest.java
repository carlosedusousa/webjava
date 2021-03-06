package modelos;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import org.junit.BeforeClass;
import org.junit.Test;

public class CatalogoTest {

	private static EntityManager em;

	@BeforeClass
	public static void startEntityManager() throws Exception {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("betapedia-test");
		em = emf.createEntityManager();
	}

	@Test(expected = RollbackException.class)
	public void nomeCatalogoObrigatorio() {
		Catalogo c = new Catalogo();
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
	}

	// Teste para verificar se o nome é único na base.
	@Test(expected = RollbackException.class)
	public void nomeCatalogoUnico() {
		Catalogo catalogoUm = new Catalogo("BlackFriday");
		Catalogo catalogoDois = new Catalogo("BlackFriday");
		em.getTransaction().begin();
		em.persist(catalogoUm);
		em.persist(catalogoDois);
		em.getTransaction().commit();
	}

	// Teste para insercao do nome e consulta da insercao com sucesso.
	@Test
	public void inserirCatalogo() {
		Catalogo c = new Catalogo("Quarta Barata");
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
	}

	// teste de remocao de catalogo.
	@Test
	public void removerCatalogo() {
		Catalogo catalogo = new Catalogo("Do Dia");
		em.getTransaction().begin();
		em.persist(catalogo);
		em.getTransaction().commit();
		Integer pId = catalogo.getId();
		Catalogo p = em.find(Catalogo.class, pId);
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
		Catalogo rRemovido = em.find(Catalogo.class, pId);
		assertNull(rRemovido);
	}

	// Testar adicao de varios produtos ao catalogo.
	@Test
	public void adicionarProdutos() {
		Produto p1 = new Produto("Arroz A", "Branquinho", 9.99);
		Produto p2 = new Produto("Arroz B", "Integral", 5.99);
		Produto p3 = new Produto("Arroz C", "Paboirizado", 8.99);

		Catalogo BlackFriday = new Catalogo();
		BlackFriday.setNome("Super BlackFriday");
		BlackFriday.addProduto(p1);
		BlackFriday.addProduto(p2);
		BlackFriday.addProduto(p3);
		em.getTransaction().begin();
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		em.persist(BlackFriday);
		em.getTransaction().commit();
		TypedQuery<Catalogo> query = em
				.createQuery("select t from Catalogo t where t.nome = :nome",
						Catalogo.class);
		query.setParameter("nome", "Super BlackFriday");
		Catalogo t = query.getSingleResult();
		assertThat(BlackFriday, is(t));
	}

	@Test(expected = RollbackException.class)
	public void remocaoProdutoNaoSegura() {
		Produto p1 = new Produto("Arroz A", "Branquinho", 9.99);
		Produto p2 = new Produto("Arroz B", "Integral", 5.99);
		Produto p3 = new Produto("Arroz C", "Paboirizado", 8.99);

		Catalogo BlackFriday = new Catalogo();
		BlackFriday.setNome("Super BlackFriday");
		BlackFriday.addProduto(p1);
		BlackFriday.addProduto(p2);
		BlackFriday.addProduto(p3);
		em.getTransaction().begin();
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		em.persist(BlackFriday);
		em.getTransaction().commit();
		em.getTransaction().begin();
		em.remove(p1);
		em.getTransaction().commit();
	}

}
