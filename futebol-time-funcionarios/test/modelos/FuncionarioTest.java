package modelos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import org.junit.BeforeClass;
import org.junit.Test;

import futebol.Funcionario;

public class FuncionarioTest {

	private static EntityManager em;

	@BeforeClass
	public static void startEntityManager() throws Exception {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("futebol");
		em = emf.createEntityManager();
	}

	// Testar insercao no banco sem nome. roolback desfaz a falha de insercao.
	@Test(expected = RollbackException.class)
	public void nomeObrigatorio() {
		Funcionario c = new Funcionario();
		c.setSalario(100.00);
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();

	}

	// Testar insercao no banco sem salario. roolback desfaz a falha de
	// insercao.
	@Test(expected = RollbackException.class)
	public void salarioObrigatorio() {
		Funcionario c = new Funcionario();
		c.setNome("carlos");
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();

	}

	// Testar insercao no banco nome e salario com sucesso.
	@Test
	public void inserirSucesso() {
		Funcionario c = new Funcionario();
		c.setNome("Pedro");
		c.setSalario(100.00);
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();

	}

}
