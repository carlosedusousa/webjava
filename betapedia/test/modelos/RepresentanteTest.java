package modelos;

import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import org.junit.BeforeClass;
import org.junit.Test;

// Classe de teste da classe Representante.
public class RepresentanteTest {

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
	public void nomeRepresentanteObrigatorio() {
		Representante r = new Representante();
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();

	}

	// Teste para verificar se o nome é único na base.
	@Test(expected = RollbackException.class)
	public void nomeRepresentanteUnico() {
		Representante representanteUm = new Representante();
		representanteUm.setNome("Carlos Eduardo");
		Representante representanteDois = new Representante();
		representanteDois.setNome("Carlos Eduardo");
		em.getTransaction().begin();
		em.persist(representanteUm);
		em.persist(representanteDois);
		em.getTransaction().commit();
	}

	// Teste para insercao do nome e consulta da insercao com sucesso.
	@Test
	public void inserirRepresentante() {
		Representante r = new Representante("Carlos Eduardo Sousa");
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
	}

	// teste de remocao de representante.
	@Test
	public void removerRepresentante() {
		Representante representante = new Representante();
		representante.setNome("Pedro Joao");
		em.getTransaction().begin();
		em.persist(representante);
		em.getTransaction().commit();
		Integer pId = representante.getId();
		Representante r = em.find(Representante.class, pId);
		em.getTransaction().begin();
		em.remove(r);
		em.getTransaction().commit();
		Representante rRemovido = em.find(Representante.class, pId);
		assertNull(rRemovido);
	}

}
