package modelos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
// Nomeando a tablela no banco.
@Table(name = "catalogos")
public class Catalogo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Integer id;

	// Nome obrigatorio do catalogo 
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	// O catalogo possui uma lista de produtos.
	@OneToMany
	private List<Produto> produtos;

	// Construtor de catalogo sem nome.
	// A JPA obriga ter um construtor vazio.
	public Catalogo() {
		this.produtos = new ArrayList<Produto>();
	}

	// Construtor de catalogo com um nome.
	public Catalogo(String nome) {
		setNome(nome);
		setProdutos(new ArrayList<Produto>());
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

	// retorna uma lista de produtos
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	// adiciona produto ao catalogo
	public void addProduto(Produto p) {
		this.produtos.add(p);
	}

	// remove o produto do catalogo
	public void removeProduto(Produto p) {
		this.produtos.remove(p);
	}
}
