package handler.exercicio08;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import util.JPA;
import model.exercicio08.Empresa;

@ManagedBean(name = "empresaBean")
// @SessionScoped
public class EmpresaBean implements Serializable {

	private static final long serialVersionUID = 4596097440803509278L;
	private Empresa empresa;
	private List<Empresa> empresas;

	public EmpresaBean() {
		empresa = new Empresa();
		empresas = new ArrayList<>();
	}

	public String add() {
		empresas.add(empresa);
		EntityManager em = JPA.getEM();
		em.getTransaction().begin();
		em.persist(empresa);
		em.getTransaction().commit();
		setEmpresa(new Empresa());
		return "/trabalhos/exercicio08/geracao/empresa/listar";
	}

	// Limpa um registro.
	public String remove() {
		empresas.remove(empresa);
		EntityManager em = JPA.getEM();
		em.getTransaction().begin();
		em.remove(empresa);
		em.getTransaction().commit();
		setEmpresa(new Empresa());
		return "/trabalhos/exercicio08/geracao/empresa/listar";
	}

	// Limpa tela de cadastro.
	public String clearCadastro() {
		empresa = new Empresa();
		return "/trabalhos/exercicio08/geracao/empresa/registrar";
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Empresa> getEmpresas() {
		EntityManager em = JPA.getEM();
		TypedQuery<Empresa> query = em.createQuery("SELECT e from Empresa e",
				Empresa.class);
		return query.getResultList();
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
}
