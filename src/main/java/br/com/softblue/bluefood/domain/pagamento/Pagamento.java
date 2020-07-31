package br.com.softblue.bluefood.domain.pagamento;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.softblue.bluefood.domain.pedido.Pedido;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pagamento")
public class Pagamento implements Serializable {

	@Id
	private Integer id;

	@NotNull
	private LocalDateTime data;

	@NotNull
	@OneToOne
	@MapsId
	private Pedido pedido;

	@NotNull
	@Size(min = 4, max = 4)
	@Column(name = "num_cartao_final")
	private String numCartaoFinal;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private BandeiraCartao bandeiraCartao;

	public void definirNumeroEBandeira(String numCartao) {
		numCartaoFinal = numCartao.substring(12);
		bandeiraCartao = getBandeira(numCartao);
	}

	private BandeiraCartao getBandeira(String numCartao) {
		if (numCartao.startsWith("0000")) {
			return BandeiraCartao.AMEX;
		} else if (numCartao.startsWith("1111")) {
			return BandeiraCartao.ELO;
		} else if (numCartao.startsWith("2222")) {
			return BandeiraCartao.MASTER;
		} else {
			return BandeiraCartao.VISA;
		}
	}
}
