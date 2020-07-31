package br.com.softblue.bluefood.domain.pedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	// Duas op��es de uma mesma funconalidade --------------------------
	//public List<Pedido> findByCliente_IdOrderByDataDesc(Integer clienteId);
	
	@Query("SELECT p FROM Pedido p WHERE p.cliente.id = ?1 ORDER BY p.data DESC")
	public List<Pedido> listPedidosByCliente(Integer clienteId);
	// -----------------------------------------------------------------
}
