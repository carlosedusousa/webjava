package modelos;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import org.junit.BeforeClass;
import org.junit.Test;

import futebol.Funcionario;
import futebol.Time;

public class TimeTest {
	// O EntityManager é reponsável por alterar os estados
	private static EntityManager em;

	@BeforeClass
	public static void startEntityManager() throws Exception {
		// classe EntityManagerFactory, responsável por carregar as
		// configurações da unidade
		// de persistência para dentro do EntityManager
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("futebol");
		em = emf.createEntityManager();
	}

	@Test(expected = RollbackException.class)
	public void nomeObrigatorio() {
		Time t = new Time();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
	}

	@Test
	public void adicaoTecnico() {
		Funcionario tecnico = new Funcionario();
		tecnico.setNome("Tele Santana");
		tecnico.setSalario(300000.00);
		Time sp = new Time();
		sp.setNome("São Paulo");
		sp.setTecnico(tecnico);
		em.getTransaction().begin();
		em.persist(tecnico);
		em.persist(sp);
		em.getTransaction().commit();
		TypedQuery<Time> query = em.createQuery(
				"select t from Time t where t.nome = :nome", Time.class);
		query.setParameter("nome", "São Paulo");
		Time t = query.getSingleResult();
		assertThat(tecnico, is(t.getTecnico()));
	}

	@Test(expected = RollbackException.class)
	public void remocaoTecnicoNaoSegura() {
		Funcionario tecnico = new Funcionario();
		tecnico.setNome("Mimimi");
		tecnico.setSalario(300000.00);
		Time sp = new Time();
		sp.setNome("Mimimilalala");
		sp.setTecnico(tecnico);
		em.getTransaction().begin();
		em.persist(tecnico);
		em.persist(sp);
		em.getTransaction().commit();
		em.getTransaction().begin();
		em.remove(tecnico);
		em.getTransaction().commit();
	}

	@Test
	public void adicaoJogadores() {
		Funcionario j1 = new Funcionario();
		j1.setNome("Pele");
		j1.setSalario(300000.00);
		Funcionario j2 = new Funcionario();
		j2.setNome("Garrinha");
		j2.setSalario(300000.00);
		Time sp = new Time();
		sp.setNome("Brasil");
		sp.addJogador(j1);
		sp.addJogador(j2);
		em.getTransaction().begin();
		em.persist(j1);
		em.persist(j2);
		em.persist(sp);
		em.getTransaction().commit();
		TypedQuery<Time> query = em.createQuery(
				"select t from Time t where t.nome = :nome", Time.class);
		query.setParameter("nome", "Brasil");
		Time t = query.getSingleResult();
		assertThat(sp, is(t));
	}

	@Test(expected = RollbackException.class)
	public void remocaoJogadorNaoSegura() {
		Funcionario j1 = new Funcionario();
		j1.setNome("dj2u3dfdaf");
		j1.setSalario(300000.00);
		Funcionario j2 = new Funcionario();
		j2.setNome("09ropajfklajsf");
		j2.setSalario(300000.00);
		Time sp = new Time();
		sp.setNome("Argentina");
		sp.addJogador(j1);
		sp.addJogador(j2);
		em.getTransaction().begin();
		em.persist(j1);
		em.persist(j2);
		em.persist(sp);
		em.getTransaction().commit();
		em.getTransaction().begin();
		em.remove(j1);
		em.getTransaction().commit();
	}
}