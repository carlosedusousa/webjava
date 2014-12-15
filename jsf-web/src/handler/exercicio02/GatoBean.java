package handler.exercicio02;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import util.JPA;
import model.exercicio02.Gato;

@ManagedBean(name = "catBean")
//@SessionScoped
public class GatoBean implements Serializable {

	private static final long serialVersionUID = 2181068188667135726L;
	private Gato cat;
	private List<Gato> cats;

	public GatoBean() {
		cat = new Gato();
		cats = new ArrayList<>();
	}

	public String add() {
		cats.add(cat);
		EntityManager em = JPA.getEM();
		em.getTransaction().begin();
		em.persist(cat);
		em.getTransaction().commit();
		setCat(new Gato());
		return "/trabalhos/exercicio02/gato/listar";
	}

	// Limpa um registro.
	public String remove() {
		cats.remove(cat);
		EntityManager em = JPA.getEM();
		em.getTransaction().begin();
		em.remove(cat);
		em.getTransaction().commit();
		setCat(new Gato());
		return "/trabalhos/exercicio02/gato/listar";
	}

	// Limpa tela de cadastro.
	public String clearCadastro() {
		cat = new Gato();
		return "/trabalhos/exercicio02/gato/listar";
	}

	// Limpa todos os registros
	public String clearTudo() {
		cats.clear();
		cat = new Gato();
		return "/trabalhos/exercicio02/gato/listar";
	}

	public Gato getCat() {
		return cat;
	}

	public void setCat(Gato cat) {
		this.cat = cat;
	}

	public List<Gato> getCats() {
		EntityManager em = JPA.getEM();
		TypedQuery<Gato> query = em.createQuery("SELECT c from Gato c",
				Gato.class);
		return query.getResultList();
	}

	public void setCats(List<Gato> cats) {
		this.cats = cats;
	}

}
