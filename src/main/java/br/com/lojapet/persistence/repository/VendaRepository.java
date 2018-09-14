package br.com.lojapet.persistence.repository;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.lojapet.model.Pagamento;
import br.com.lojapet.model.Venda;

public interface VendaRepository  extends JpaRepository<Venda, UUID> {
	
	
	
//	@Query("SELECT SUM(v.total) FROM venda v WHERE as 'Total' AND p.dataVencimento BETWEEN :start AND :end")
//	public List<Venda> somatorioTotalVendaPorMes(@Param("start")Calendar startWith, @Param("end")Calendar endWith);
	
	public List<Venda> findByDataEmissaoBetween(Calendar comecando, Calendar terminando);
	public List<Venda> findByDataEmissaoBetweenOrderByDataEmissaoAsc(Calendar comecando, Calendar terminando);



}
