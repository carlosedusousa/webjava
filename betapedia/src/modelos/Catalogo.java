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

//Cada Catálogo possui um nome único e sua lista de Produtos.

@Entity
@Table(name = "catalogos")
public class Catalogo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@OneToMany
	private List<Produto> produtos;

	public Catalogo() {
		this.produtos = new ArrayList<Produto>();
	}

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

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public void addProduto(Produto p) {
		this.produtos.add(p);
	}

	public void removeProduto(Produto p) {
		this.produtos.remove(p);
	}

}
