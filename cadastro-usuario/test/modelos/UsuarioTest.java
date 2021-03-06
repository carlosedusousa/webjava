package modelos;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import estudos.Usuario;

public class UsuarioTest {
	
	// O EntityManager eh reponsavel por alterar os estados
	private static EntityManager em;  

	@BeforeClass
	public static void startEntityManager() throws Exception {
		// classe EntityManagerFactory, responsavel por carregar as configuracoes da unidade
		// de persistencia para dentro do EntityManager
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("estudos");
		em = emf.createEntityManager();
		
		// Criacao de um objeto como se faz normalmente em Java
		Usuario usuario = new Usuario();
		usuario.setNome("Carlos Eduardo");
		usuario.setEmail("carlos@email,com");
		
		// Inicio dos trabalhos da JPA 
		em.getTransaction().begin();
		
		// Persiste, o objeto funcinario
		em.persist(usuario);
		
		// Commit de todas as operacoes efetuadas do begin() ate este ponto 
		em.getTransaction().commit();
	}
	
	@Test
	public void FindByIdTest() {
		Usuario u = em.find(Usuario.class, 1);
		assertNome(u);
	}

	@Test
	public void FindByNomeTest() {
		TypedQuery<Usuario> query = 
				em.createQuery("select u from Usuario u where u.nome = :nome", 
						Usuario.class);
		query.setParameter("nome", "Carlos Eduardo");
		Usuario u = query.getSingleResult();
		Assert.assertEquals("Carlos Eduardo", u);
	}
	
	@Test
	public void FindByNascimentoTest() {
		TypedQuery<Usuario> query = 
				em.createQuery("select u from Usuario u where u.nascimento = :data",
						Usuario.class);
		query.setParameter("data", LocalDate.of(1981, 5, 20));
		Usuario u = (Usuario) query.getSingleResult();
		assertNome(u);
	}
	
	private void assertNome(Usuario u) {
		Assert.assertEquals("Carlos Eduardo", u.getNome());

	}
}
