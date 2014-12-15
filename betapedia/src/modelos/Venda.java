package modelos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
// Nomeando a tablela no banco.
@Table(name = "vendas")
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "vendas_representantes")
	private Representante representante;

	// Vedas possui a lista de produtos vendidos
	@OneToMany
	private List<Produto> produtos;

//	@Column(name = "totalVenda", nullable = false)
	private Double totalVenda;

	private Double comissaoRepresentante;

	public Venda() {
		setProdutos(new ArrayList<Produto>());
	}

	public Venda(Representante representante) {
		setRepresentante(representante);
		setProdutos(new ArrayList<Produto>());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Representante getRepresentante() {
		return representante;
	}

	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Double getTotalVenda() {
		return totalVenda;
	}

	public void setTotalVenda(Double totalVenda) {
		this.totalVenda = totalVenda;
	}

	// Métodos de calculo de total de vendas e comissao.

	public Double getComissaoRepresentante() {
		return comissaoRepresentante;
	}

	// Calcula comissao do representante em 10 porcento.
	public void setComissaoRepresentante(Double comissaoRepresentante) {
		this.comissaoRepresentante = comissaoRepresentante * 0.1;
	}

	public void addProdutos(Produto p) {
		this.produtos.add(p);
	}

	public void removeProdutos(Produto p) {
		this.produtos.remove(p);
	}

	// Método de calculo de total de vendas e comissao.

	public void calculaTotalVenda() {

		// Definicao de variavel contadora do total da venda.
		Double total = 0.0;

		// Para cada produto da lista o valor é contabilizado.
		for (int i = 0; i < getProdutos().size(); i++) {
			total += getProdutos().get(i).getValor();
		}

		// Valor é setado na variavel totalVenda da classe.
		setTotalVenda(total);
		// Valor da comissao é setada na variavel comissaoRepresentante da
		// classe.
		setComissaoRepresentante(total);

	}

}
//rudolf-hoffmann@hotmail.com