package model.exercicio04;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cobra implements Serializable{

	private static final long serialVersionUID = -3128516620377181937L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@Temporal(TemporalType.DATE)
	private Date captura;
	private Float tamanho;
	private Float peso;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getCaptura() {
		return captura;
	}

	public void setCaptura(Date captura) {
		this.captura = captura;
	}

	public Float getTamanho() {
		return tamanho;
	}

	public void setTamanho(Float tamanho) {
		this.tamanho = tamanho;
	}

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}
}
