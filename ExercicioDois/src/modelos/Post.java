package modelos;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "titulo", nullable = false, unique = true)
	private String titulo;
	private String subtitulo;
	@Column(nullable = false)
	private String texto;
	private String tags;
	@Column(nullable = false)
	private String autor;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Categoria> categorias;

	public Post() {
		setCategorias(new ArrayList<Categoria>());
	}

	public Post(String titulo, String texto, String autor) {
		setTitulo(titulo);
		setTexto(texto);
		setAutor(autor);
		setCategorias(new ArrayList<Categoria>());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public void addCategoria(Categoria categoria) {
		this.categorias.add(categoria);
	}

	public void removeCategoria(Categoria categoria) {
		this.categorias.remove(categoria);
	}
}
