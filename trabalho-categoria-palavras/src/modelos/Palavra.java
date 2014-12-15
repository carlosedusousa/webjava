package modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "palavras")
public class Palavra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "palavra")
	private String palavra;

	public Palavra() {
	}

	public Palavra(String texto) {
		setPalavra(texto);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String p) {
		this.palavra = p;
	}

}
