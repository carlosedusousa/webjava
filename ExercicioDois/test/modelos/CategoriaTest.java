package modelos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import org.junit.BeforeClass;
import org.junit.Test;

public class CategoriaTest {
	
	// O EntityManager eh reponsavel por alterar os estados
	private static EntityManager em;  

	@BeforeClass
	public static void startEntityManager() throws Exception {
		// classe EntityManagerFactory, reponsavel por carregar as configura��es da unidade
		// de persist�ncia para dentro do EntityManager
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("ExercicioDois");
		em = emf.createEntityManager();
	}
	
	@Test(expected = RollbackException.class)
	public void nomeObrigatorio(){
		Categoria c = new Categoria();
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		
	}

}
