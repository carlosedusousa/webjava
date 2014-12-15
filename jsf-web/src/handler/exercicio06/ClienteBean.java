package handler.exercicio06;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.exercicio06.ClienteMonster;
import util.JPA;

@ManagedBean(name = "monsterBean")
@ViewScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 4256228367286684495L;
	private ClienteMonster cliente;
	private List<ClienteMonster> clientes;

	public ClienteBean() {
		cliente = new ClienteMonster();
		clientes = new ArrayList<>();
	}

	public String add() {
		clientes.add(cliente);
		EntityManager em = JPA.getEM();
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
		setCliente(new ClienteMonster());
		return "/trabalhos/exercicio06/monsterinc/cliente/listar";
	}

	// Limpa um registro.
	public String remove() {
		clientes.remove(cliente);
		EntityManager em = JPA.getEM();
		em.getTransaction().begin();
		em.remove(cliente);
		em.getTransaction().commit();
		setCliente(new ClienteMonster());
		return "/trabalhos/exercicio06/monsterinc/cliente/listar";
	}

	// Limpa tela de cadastro.
	public String clearCadastro() {
		cliente = new ClienteMonster();
		return "/trabalhos/exercicio06/monsterinc/cliente/registrar";
	}

	public ClienteMonster getCliente() {
		return cliente;
	}

	public void setCliente(ClienteMonster cliente) {
		this.cliente = cliente;
	}

	public List<ClienteMonster> getClientes() {
		EntityManager em = JPA.getEM();
		TypedQuery<ClienteMonster> query = em.createQuery(
				"SELECT c from ClienteMonster c", ClienteMonster.class);
		return query.getResultList();
	}

	public void setClientes(List<ClienteMonster> clientes) {
		this.clientes = clientes;
	}
	
	public String list() {
		return "/trabalhos/exercicio06/monsterinc/cliente/listar";
	}
}
