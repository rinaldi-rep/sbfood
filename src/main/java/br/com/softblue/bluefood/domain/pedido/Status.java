package br.com.softblue.bluefood.domain.pedido;

public enum Status {
	Producao(1, "Em Produção", false), 
	Entrega(2, "Saiu para Entrega", false), 
	Concluido(1, "Concluído", true);

	private Status(int ordem, String descricao, boolean ultimo) {
		this.ordem = ordem;
		this.descricao = descricao;
		this.ultimo = ultimo;
	}
		
	int ordem;
	String descricao;
	boolean ultimo;
	
	public String getDescricao() {
		return descricao;
	}
	
	public int getOrdem() {
		return ordem;
	}
	
	public boolean isUltimo() {
		return ultimo;
	}
}
