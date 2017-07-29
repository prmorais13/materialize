package br.com.paulo.financeiro.modelo;

public enum Situacao {

	COMPENSADO("Compensado"), 
	CANCELADO("Cancelado"), 
	PAGAMENTO_NAO_REALIZADO("Pagamento não realizado");

	private String descricao;

	private Situacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
