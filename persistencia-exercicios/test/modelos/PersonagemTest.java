package modelos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import org.junit.BeforeClass;
import org.junit.Test;

import crud.Personagem;

public class PersonagemTest {
	// O EntityManager é reponsável por alterar os estados
	private static EntityManager em;

	@BeforeClass
	public static void startEntityManager() throws Exception {
		// classe EntityManagerFactory, responsável por carregar as
		// configurações da unidade de persistência para dentro do
		// EntityManager
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("persistencia-exercicios");
		em = emf.createEntityManager();
	}

	@Test(expected = RollbackException.class)
	public void nomeObrigatorio() {
		Personagem personagem = new Personagem();
		personagem.setVida(10);
		personagem.setTipo("heroi");
		em.getTransaction().begin();
		em.persist(personagem);
		em.getTransaction().commit();
	}

	@Test(expected = RollbackException.class)
	public void tipoObrigatorio() {
		Personagem personagem = new Personagem();
		personagem.setVida(10);
		personagem.setNome("Nome");
		em.getTransaction().begin();
		em.persist(personagem);
		em.getTransaction().commit();
	}

	@Test(expected = RollbackException.class)
	public void pontosDeVidaObrigatorio() {
		Personagem personagem = new Personagem();
		personagem.setTipo("heroi");
		personagem.setNome("Nome");
		em.getTransaction().begin();
		em.persist(personagem);
		em.getTransaction().commit();
	}

	@Test(expected = RollbackException.class)
	public void nomeUnico() {
		Personagem personagem = new Personagem();
		personagem.setVida(10);
		personagem.setTipo("heroi");
		personagem.setNome("Nome");
		Personagem personagemDois = new Personagem();
		personagemDois.setVida(10);
		personagemDois.setTipo("heroi");
		personagemDois.setNome("Nome");
		em.getTransaction().begin();
		em.persist(personagem);
		em.persist(personagemDois);
		em.getTransaction().commit();
	}

	@Test
	public void atualizacaoTest() {
		Personagem personagem = new Personagem();
		personagem.setVida(10);
		personagem.setTipo("heroi");
		personagem.setNome("Nome");
		em.getTransaction().begin();
		em.persist(personagem);
		em.getTransaction().commit();
		Integer pId = personagem.getId();
		personagem.setNome("Nome Divertido");
		em.getTransaction().begin();
		em.persist(personagem);
		em.getTransaction().commit();
		Personagem pAtualizado = em.find(Personagem.class, pId);
		assertEquals("Nome Divertido", pAtualizado.getNome());
	}

	@Test
	public void remocaoTest() {
		// Adicionando um novo funcionário no banco de dados para realizar o
		// teste
		Personagem personagem = new Personagem();
		personagem.setVida(10);
		personagem.setTipo("heroi");
		personagem.setNome("Nome remocao");
		em.getTransaction().begin();
		em.persist(personagem);
		em.getTransaction().commit();
		Integer pId = personagem.getId();
		Personagem p = em.find(Personagem.class, pId);
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
		Personagem pRemovido = em.find(Personagem.class, pId);
		assertNull(pRemovido);
	}
}