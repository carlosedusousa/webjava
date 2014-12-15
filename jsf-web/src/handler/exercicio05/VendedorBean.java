package handler.exercicio05;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import util.JPA;
import model.exercicio05.Vendedor;

@ManagedBean(name = "vendedorBean")
@SessionScoped
public class VendedorBean implements Serializable {

	private static final long serialVersionUID = 5583687646678546663L;
	private Vendedor vendedor;
	private List<Vendedor> vendedores;

	public VendedorBean() {
		vendedor = new Vendedor();
		vendedores = new ArrayList<>();
	}

	public String add() {
		vendedores.add(vendedor);
		EntityManager em = JPA.getEM();
		em.getTransaction().begin();
		em.persist(vendedor);
		em.getTransaction().commit();
		setVendedor(new Vendedor());
		return "/trabalhos/exercicio05/betainc/vendedor/listar";
	}

	// Limpa um registro.
	public String remove() {
		vendedores.remove(vendedor);
		EntityManager em = JPA.getEM();
		em.getTransaction().begin();
		em.remove(vendedor);
		em.getTransaction().commit();
		setVendedor(new Vendedor());
		return "/trabalhos/exercicio05/betainc/vendedor/listar";
	}

	// Limpa tela de cadastro.
	public String clearCadastro() {
		vendedor = new Vendedor();
		return "/trabalhos/exercicio05/betainc/vendedor/listar";
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public List<Vendedor> getVendedores() {
		EntityManager em = JPA.getEM();
		TypedQuery<Vendedor> query = em.createQuery("SELECT v from Vendedor v",
				Vendedor.class);
		return query.getResultList();
	}

	public void setVendedores(List<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}
}
