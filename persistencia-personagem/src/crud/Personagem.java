package crud;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Personagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false, unique = true)
	private String nome;
	@Column(nullable = false)
	private String tipo;
	private Float forca;
	private Float destreza;
	private Float constituicao;
	private Float inteligencia;
	private Float sabedoria;
	private Float carisma;
	@Column(nullable = false)
	private Integer vida;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Float getForca() {
		return forca;
	}

	public void setForca(Float forca) {
		this.forca = forca;
	}

	public Float getDestreza() {
		return destreza;
	}

	public void setDestreza(Float destreza) {
		this.destreza = destreza;
	}

	public Float getConstituicao() {
		return constituicao;
	}

	public void setConstituicao(Float constituicao) {
		this.constituicao = constituicao;
	}

	public Float getInteligencia() {
		return inteligencia;
	}

	public void setInteligencia(Float inteligencia) {
		this.inteligencia = inteligencia;
	}

	public Float getSabedoria() {
		return sabedoria;
	}

	public void setSabedoria(Float sabedoria) {
		this.sabedoria = sabedoria;
	}

	public Float getCarisma() {
		return carisma;
	}

	public void setCarisma(Float carisma) {
		this.carisma = carisma;
	}

	public Integer getVida() {
		return vida;
	}

	public void setVida(Integer vida) {
		this.vida = vida;
	}

}
