package modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
// Nomeando a tablela no banco.
@Table(name = "representantes")
public class Representante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Integer id;

	// Nome obrigatorio do representante.
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	// A JPA obriga ter um construtor vazio.
	public Representante() {
	}

	// construtor com nome.
	public Representante(String nome) {
		setNome(nome);
	}

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
}
