package br.com.softblue.bluefood.application.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softblue.bluefood.domain.pedido.Pedido;
import br.com.softblue.bluefood.domain.pedido.PedidoRepository;
import br.com.softblue.bluefood.domain.pedido.RelatorioItemFaturamento;
import br.com.softblue.bluefood.domain.pedido.RelatorioItemFilter;
import br.com.softblue.bluefood.domain.pedido.RelatorioPedidoFilter;

@Service
public class RelatorioService {

	private static final int DATA_INICIAL = 0;
	private static final int DATA_FINAL = 1;

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<Pedido> listPedidos(Integer restauranteId, RelatorioPedidoFilter filter) {
		Integer pedidoId = filter.getPedidoId();
		
		if (pedidoId != null) {
			Pedido pedido = pedidoRepository.findByIdAndRestauranteId(pedidoId, restauranteId);
			return List.of(pedido);
		}
		
		LocalDateTime[] dateRange = rangeDateTimeBuilder(filter.getDataInicial(), filter.getDataFinal());
		if (dateRange == null) {
			return List.of();
		}
		
		return pedidoRepository.findByDateInterval(restauranteId, dateRange[DATA_INICIAL], dateRange[DATA_FINAL]);
	}
	
	public List<RelatorioItemFaturamento> calcularFaturamentoItens(Integer restauranteId, RelatorioItemFilter filter) {
		List<Object[]> itensObj;
		
		Integer itemId = filter.getItemId();
		
		LocalDateTime[] dateRange = rangeDateTimeBuilder(filter.getDataInicial(), filter.getDataFinal());
		if (dateRange == null) {
			return List.of();
		}
		
		if (itemId > 0) {
			itensObj = pedidoRepository.findItensForFaturamento(restauranteId, itemId, dateRange[DATA_INICIAL], dateRange[DATA_FINAL]);
		} else {
			itensObj = pedidoRepository.findItensForFaturamento(restauranteId, dateRange[DATA_INICIAL], dateRange[DATA_FINAL]);
		}
		
		List<RelatorioItemFaturamento> itens = new ArrayList<>();
		for (Object[] item : itensObj) {
			String nome = (String) item[0];
			Long quantidade = (Long) item[1];
			BigDecimal valor = (BigDecimal) item[2];
			itens.add(new RelatorioItemFaturamento(nome, quantidade, valor));
		}
		
		return itens;
	}
	
	private LocalDateTime[] rangeDateTimeBuilder(LocalDate dataInicio, LocalDate dataFim) {
		if (dataInicio == null) {
			return null;
		}
		
		if (dataFim == null) {
			dataFim = LocalDate.now();
		}
		
		return new LocalDateTime[] { dataInicio.atStartOfDay(), dataFim.atTime(23, 59, 59) };
	}
}
