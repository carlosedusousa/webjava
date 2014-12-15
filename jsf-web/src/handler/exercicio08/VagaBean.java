package handler.exercicio08;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import util.JPA;
import model.exercicio08.Vaga;

@ManagedBean(name = "vagaBean")
//@SessionScoped
public class VagaBean {

	private Vaga vaga;
	private List<Vaga> vagas;

	public VagaBean() {
		vaga = new Vaga();
		vagas = new ArrayList<>();
	}

	public String add() {
		vagas.add(vaga);
		EntityManager em = JPA.getEM();
		em.getTransaction().begin();
		em.persist(vaga);
		em.getTransaction().commit();
		setVaga(new Vaga());
		return "/trabalhos/exercicio08/geracao/vaga/listar";
	}

	// Limpa um registro.
	public String remove() {
		vagas.remove(vaga);
		EntityManager em = JPA.getEM();
		em.getTransaction().begin();
		em.remove(vaga);
		em.getTransaction().commit();
		setVaga(new Vaga());
		return "/trabalhos/exercicio08/geracao/vaga/listar";
	}

	// Limpa tela de cadastro.
	public String clearCadastro() {
		vaga = new Vaga();
		return "/trabalhos/exercicio08/geracao/vaga/registrar";
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	public List<Vaga> getVagas() {
		EntityManager em = JPA.getEM();
		TypedQuery<Vaga> query = em.createQuery("SELECT v from Vaga v",
				Vaga.class);
		return query.getResultList();
	}

	public void setVagas(List<Vaga> vagas) {
		this.vagas = vagas;
	}

}
