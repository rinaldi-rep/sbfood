package br.com.softblue.bluefood.domain.pedido;

public enum Status {
	Producao(1, "Em Produ��o", false), 
	Entrega(2, "Saiu para Entrega", false), 
	Concluido(3, "Conclu�do", true);

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

	public static Status fromOrdem(int ordem) {
		for (Status status : Status.values()) {
			if (status.getOrdem() == ordem) {
				return status;
			}
		}
		return null;
	}
}
