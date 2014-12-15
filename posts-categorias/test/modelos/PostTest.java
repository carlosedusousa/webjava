package modelos;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import org.junit.BeforeClass;
import org.junit.Test;

public class PostTest {
	// O EntityManager é reponsável por alterar os estados
	private static EntityManager em;

	@BeforeClass
	public static void startEntityManager() throws Exception {
		// classe EntityManagerFactory, responsável por carregar as
		// configurações da unidade
		// de persistência para dentro do EntityManager
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("ExercicioDois");
		em = emf.createEntityManager();
	}

	@Test(expected = RollbackException.class)
	public void remocaoNaoSeguraCategoria() {
		Categoria c = new Categoria("lalala");
		Post p = new Post("omgosh", "omgosh", "omgosh");
		p.addCategoria(c);
		em.getTransaction().begin();
		em.persist(c);
		em.persist(p);
		em.getTransaction().commit();
		em.getTransaction().begin();
		em.remove(c);
		em.getTransaction().commit();
	}

	@Test
	public void remocaoSeguraCategoria() {
		Categoria c = new Categoria("lalala");
		Post p = new Post("omgoshhh", "omgosh", "omgosh");
		p.addCategoria(c);
		em.getTransaction().begin();
		em.persist(c);
		em.persist(p);
		em.getTransaction().commit();
		p.removeCategoria(c);
		em.getTransaction().begin();
		em.persist(p);
		em.remove(c);
		em.getTransaction().commit();
		TypedQuery<Post> query = em.createQuery(
				"select p from Post p where p.titulo = :titulo", Post.class);
		query.setParameter("titulo", "omgoshhh");
		Post pQuery = query.getSingleResult();
		assertThat(p.getCategorias(), is(pQuery.getCategorias()));
	}

	@Test
	public void FindByNomeTest() {
		Post post = new Post();
		post.setTitulo("mimimimimimi");
		post.setTexto("mimimimimimi");
		post.setSubtitulo("mimimimimimi");
		post.setAutor("mimimimimimi");
		post.setTags("mimimi, mimimi");
		em.getTransaction().begin();
		em.persist(post);
		em.getTransaction().commit();
		TypedQuery<Post> query = em.createQuery(
				"select p from Post p where p.titulo = :titulo", Post.class);
		query.setParameter("titulo", "mimimimimimi");
		Post p = query.getSingleResult();
		assertEquals("mimimimimimi", p.getTitulo());
	}

	@Test(expected = RollbackException.class)
	public void tituloObrigatorio() {
		Post post = new Post();
		post.setTexto("mimimi");
		post.setSubtitulo("mimimi");
		post.setAutor("mimimi");
		post.setTags("mimimi, mimimi");
		em.getTransaction().begin();
		em.persist(post);
		em.getTransaction().commit();
	}

	@Test(expected = RollbackException.class)
	public void tituloUnico() {
		Post post1 = new Post();
		post1.setTitulo("mimimilalala");
		post1.setTexto("mimimilalala");
		post1.setSubtitulo("mimimilalala");
		post1.setAutor("mimimilalala");
		post1.setTags("mimimi, mimimi");
		Post post2 = new Post();
		post2.setTitulo("mimimilalala");
		post2.setTexto("mimimilalala");
		post2.setSubtitulo("mimimilalala");
		post2.setAutor("mimimilalala");
		post2.setTags("mimimi, mimimi");
		em.getTransaction().begin();
		em.persist(post1);
		em.persist(post2);
		em.getTransaction().commit();
	}

	@Test(expected = RollbackException.class)
	public void textoObrigatorio() {
		Post post = new Post();
		post.setTitulo("mimimi");
		post.setSubtitulo("mimimi");
		post.setAutor("mimimi");
		post.setTags("mimimi, mimimi");
		em.getTransaction().begin();
		em.persist(post);
		em.getTransaction().commit();
	}

	@Test(expected = RollbackException.class)
	public void autorObrigatorio() {
		Post post = new Post();
		post.setTexto("mimimi");
		post.setSubtitulo("mimimi");
		post.setTitulo("mimimi");
		post.setTags("mimimi, mimimi");
		em.getTransaction().begin();
		em.persist(post);
		em.getTransaction().commit();
	}
}