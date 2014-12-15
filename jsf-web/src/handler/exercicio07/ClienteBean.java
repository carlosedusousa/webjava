package handler.exercicio07;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.exercicio07.ClienteMonsterInc;
import util.JPA;

@ManagedBean(name = "monsterBean7")
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = -7674178316142868154L;
	private ClienteMonsterInc cliente;
	private List<ClienteMonsterInc> clientes;

	public ClienteBean() {
		cliente = new ClienteMonsterInc();
		clientes = new ArrayList<>();
	}

	public String add() {
		clientes.add(cliente);
		EntityManager em = JPA.getEM();
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
		setCliente(new ClienteMonsterInc());
		return "/trabalhos/exercicio07/monsterinc/cliente/listar";
	}

	// Limpa um registro.
	public String remove() {
		clientes.remove(cliente);
		EntityManager em = JPA.getEM();
		em.getTransaction().begin();
		em.remove(cliente);
		em.getTransaction().commit();
		setCliente(new ClienteMonsterInc());
		return "/trabalhos/exercicio07/monsterinc/cliente/listar";
	}

	// Limpa tela de cadastro.
	public String clearCadastro() {
		cliente = new ClienteMonsterInc();
		return "/trabalhos/exercicio07/monsterinc/cliente/registrar";
	}

	public ClienteMonsterInc getCliente() {
		return cliente;
	}

	public void setCliente(ClienteMonsterInc cliente) {
		this.cliente = cliente;
	}

	public List<ClienteMonsterInc> getClientes() {
		EntityManager em = JPA.getEM();
		TypedQuery<ClienteMonsterInc> query = em.createQuery(
				"SELECT c from ClienteMonsterInc c", ClienteMonsterInc.class);
		return query.getResultList();
	}

	public void setClientes(List<ClienteMonsterInc> clientes) {
		this.clientes = clientes;
	}
}
