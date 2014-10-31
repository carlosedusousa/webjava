package modelos;

import java.util.List;

public class Venda {

	private Representante nome;
	private List<Produto> produtos;
	private Double totalVenda;
	private Double valorTotalVenda;
	private Double comissaoRepresentante;

	public Representante getNome() {
		return nome;
	}

	public void setNome(Representante nome) {
		this.nome = nome;
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

	public Double getValorTotalVenda() {
		return valorTotalVenda;
	}

	public void setValorTotalVenda(Double valorTotalVenda) {
		this.valorTotalVenda = valorTotalVenda;
	}

	public Double getComissaoRepresentante() {
		return comissaoRepresentante;
	}

	public void setComissaoRepresentante(Double comissaoRepresentante) {
		this.comissaoRepresentante = comissaoRepresentante;
	}

}
