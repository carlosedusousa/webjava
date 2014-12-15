package handler.exercicio01;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.exercicio01.Cachorro;
import util.JPA;

@ManagedBean(name = "dogBean")
// @SessionScoped
public class CachorroBean implements Serializable {

	private static final long serialVersionUID = 8972629795528195022L;
	private Cachorro dog;
	private List<Cachorro> dogs;

	public CachorroBean() {
		dog = new Cachorro();
		dogs = new ArrayList<>();
	}

	public String add() {
		dogs.add(dog);
		EntityManager em = JPA.getEM();
		em.getTransaction().begin();
		em.persist(dog);
		em.getTransaction().commit();
		setDog(new Cachorro());
		return "/trabalhos/exercicio01/cachorro/listar";
	}

	// Limpa um registro.
	public String remove() {
		dogs.remove(dog);
		EntityManager em = JPA.getEM();
		em.getTransaction().begin();
		em.remove(dog);
		em.getTransaction().commit();
		setDog(new Cachorro());
		return "/trabalhos/exercicio01/cachorro/listar";
	}

	// Limpa tela de cadastro.
	public String clearCadastro() {
		dog = new Cachorro();
		return "/trabalhos/exercicio01/cachorro/registrar";
	}

	public Cachorro getDog() {
		return dog;
	}

	public void setDog(Cachorro dog) {
		this.dog = dog;
	}

	public List<Cachorro> getDogs() {
		EntityManager em = JPA.getEM();
		TypedQuery<Cachorro> query = em.createQuery("SELECT c from Cachorro c",
				Cachorro.class);
		return query.getResultList();
	}

	public void setDogs(List<Cachorro> dogs) {
		this.dogs = dogs;
	}

}
