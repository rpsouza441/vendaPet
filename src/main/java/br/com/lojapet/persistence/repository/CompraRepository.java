package br.com.lojapet.persistence.repository;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.lojapet.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, UUID> {

	@Query("select c from Compra c where c.dataEmissao >= :comecando and c.dataEmissao <= :terminando")
	public List<Compra> findByStartWithCalendarAndEndWithCalendar(
//			@DateTimeFormat(pattern = "yyyy-MM-dd") 
			@Param("comecando") Calendar comecando,
//			@DateTimeFormat(pattern = "yyyy-MM-dd") 
			@Param("terminando") Calendar terminando);
	
	public List<Compra> findByDataEmissaoBetweenOrderByDataEmissaoAsc( Calendar comecando, Calendar terminando);
	
	public List<Compra> findByDataEmissaoBetween( Calendar comecando, Calendar terminando);


}
