package modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
// Nomeando a tablela no banco.
@Table(name = "produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Integer id;

	// Nome obrigatorio do produto.
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "valor")
	private Double valor;

	// A JPA obriga ter um construtor vazio.
	public Produto() {
	}

	public Produto(String nome) {
		setNome(nome);
	}

	// Construtor da classe Produto.
	public Produto(String nome, String descricao, Double valor) {
		setNome(nome);
		setDescricao(descricao);
		setValor(valor);
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
