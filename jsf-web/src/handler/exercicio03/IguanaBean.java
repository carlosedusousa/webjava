package handler.exercicio03;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import util.JPA;
import model.exercicio03.Iguana;

@ManagedBean(name = "iguanaBean")
//@SessionScoped
public class IguanaBean implements Serializable {

	private static final long serialVersionUID = -6295051795918663530L;
	private Iguana iguana;
	private List<Iguana> iguanas;

	public IguanaBean() {
		setIguana(new Iguana());
		setIguanas(new ArrayList<>());
	}

	public String add() {
		iguanas.add(iguana);
		EntityManager em = JPA.getEM();
		em.getTransaction().begin();
		em.persist(iguana);
		em.getTransaction().commit();
		setIguana(new Iguana());
		return "/trabalhos/exercicio03/iguana/listar";
	}

	// Limpa um registro.
	public String remove() {
		iguanas.remove(iguana);
		EntityManager em = JPA.getEM();
		em.getTransaction().begin();
		em.remove(iguana);
		em.getTransaction().commit();
		setIguana(new Iguana());
		return "/trabalhos/exercicio03/iguana/listar";
	}

	// Limpa tela de cadastro.
	public String clearCadastro() {
		iguana = new Iguana();
		return "/trabalhos/exercicio03/iguana/listar";
	}

	public Iguana getIguana() {
		return iguana;
	}

	public void setIguana(Iguana iguana) {
		this.iguana = iguana;
	}

	public List<Iguana> getIguanas() {
		EntityManager em = JPA.getEM();
		TypedQuery<Iguana> query = em.createQuery("SELECT i from Iguana i",
				Iguana.class);
		return query.getResultList();
	}

	public void setIguanas(List<Iguana> iguanas) {
		this.iguanas = iguanas;
	}
}
