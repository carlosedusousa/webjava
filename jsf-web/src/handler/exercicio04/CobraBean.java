package handler.exercicio04;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import util.JPA;
import model.exercicio04.Cobra;

@ManagedBean(name="cobraBean")
//@SessionScoped
public class CobraBean implements Serializable{

	private static final long serialVersionUID = -1860953225066831231L;
	private Cobra cobra;
	private List<Cobra> cobras;

	public CobraBean() {
		setCobra(new Cobra());
		setCobras(new ArrayList<>());
	}

	public String add() {
		cobras.add(cobra);
		EntityManager em = JPA.getEM();
		em.getTransaction().begin();
		em.persist(cobra);
		em.getTransaction().commit();
		setCobra(new Cobra());
		return "/trabalhos/exercicio04/cobra/listar";
	}

	// Limpa um registro.
	public String remove() {
		cobras.remove(cobra);
		EntityManager em = JPA.getEM();
		em.getTransaction().begin();
		em.remove(cobra);
		em.getTransaction().commit();
		setCobra(new Cobra());
		return "/trabalhos/exercicio04/cobra/listar";
	}

	// Limpa tela de cadastro.
	public String clearCadastro() {
		cobra = new Cobra();
		return "/trabalhos/exercicio04/cobra/listar";
	}

	public Cobra getCobra() {
		return cobra;
	}

	public void setCobra(Cobra cobra) {
		this.cobra = cobra;
	}

	public List<Cobra> getCobras() {
		EntityManager em = JPA.getEM();
		TypedQuery<Cobra> query = em.createQuery("SELECT c from Cobra c",
				Cobra.class);
		return query.getResultList();
	}

	public void setCobras(List<Cobra> cobras) {
		this.cobras = cobras;
	}

}
