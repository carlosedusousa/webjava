package handler.exercicio05;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.exercicio05.Cliente;
import util.JPA;

@ManagedBean(name = "clienteBean")
//@SessionScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 8766169177480928357L;
	private Cliente cliente;
	private List<Cliente> clientes;

	public ClienteBean() {
		cliente = new Cliente();
		clientes = new ArrayList<>();
	}

	public String add() {
		clientes.add(cliente);
		EntityManager em = JPA.getEM();
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
		setCliente(new Cliente());
		return "/trabalhos/exercicio05/betainc/cliente/listar";
	}

	// Limpa um registro.
	public String remove() {
		clientes.remove(cliente);
		EntityManager em = JPA.getEM();
		em.getTransaction().begin();
		em.remove(cliente);
		em.getTransaction().commit();
		setCliente(new Cliente());
		return "/trabalhos/exercicio05/betainc/cliente/listar";
	}

	// Limpa tela de cadastro.
	public String clearCadastro() {
		cliente = new Cliente();
		return "/trabalhos/exercicio05/betainc/cliente/listar";
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		EntityManager em = JPA.getEM();
		TypedQuery<Cliente> query = em.createQuery("SELECT c from Cliente c",
				Cliente.class);
		return query.getResultList();
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
}
