package br.com.softblue.bluefood.domain.pagamento;

public enum StatusPagamento {
	
	Autorizado("Autorizado"), 
	NaoAutorizado("Não Autorizado pela instituição financeira"), 
	CartaoInvalido("Cartão Inválido ou bloqueado");
	
	String descricao;
	
	StatusPagamento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
