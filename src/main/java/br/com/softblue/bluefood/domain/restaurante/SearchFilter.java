package br.com.softblue.bluefood.domain.restaurante;

import br.com.softblue.bluefood.util.StringUtils;
import lombok.Data;

@Data
public class SearchFilter {

	public enum SearchType {
		Texto, Categoria
	}

	public enum SortOrder {
		Taxa, Tempo
	}

	public enum Command {
		EntregaGratis, MaiorTaxa, MenorTaxa, MaiorTempo, MenorTempo
	}

	private String texto;
	private SearchType searchType;
	private Integer categoriaId;
	private SortOrder sortOrder = SortOrder.Taxa;
	private boolean ascendent;
	private boolean entregaGratis;

	public void processFilter(String cmdString) {		
		if (!StringUtils.isEmpty(cmdString)) {
			Command cmd = Command.valueOf(cmdString);
			
			if (cmd == Command.EntregaGratis) {
				entregaGratis = !entregaGratis;
			} else if (cmd == Command.MaiorTaxa) {
				sortOrder = SortOrder.Taxa;
				ascendent = false;
			} else if (cmd == Command.MenorTaxa) {
				sortOrder = SortOrder.Taxa;
				ascendent = true;
			}else if (cmd == Command.MaiorTempo) {
				sortOrder = SortOrder.Tempo;
				ascendent = false;
			} else if (cmd == Command.MenorTempo) {
				sortOrder = SortOrder.Tempo;
				ascendent = true;
			}

		}

		if (searchType == SearchType.Texto) {
			categoriaId = null;
		} else if (searchType == SearchType.Categoria) {
			texto = null;
		}
	}
}
