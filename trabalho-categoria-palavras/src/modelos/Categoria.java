package modelos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "categoria")
	private String categoria;
	@OneToMany
	@JoinTable(name = "categorias_palavras")
	private List<Palavra> palavras;

	public Categoria() {
		setPalavras(new ArrayList<Palavra>());
	}

	public Categoria(String categoria) {
		setCategoria(categoria);
		setPalavras(new ArrayList<Palavra>());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<Palavra> getPalavras() {
		return palavras;
	}

	public void setPalavras(ArrayList<Palavra> lista) {
		this.palavras = lista;
	}

	public void addPalavra(Palavra palavra) {
		getPalavras().add(palavra);
	}

	public void removePalavra(Palavra palavra) {
		getPalavras().remove(palavra);
	}

}
