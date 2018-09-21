package br.com.lojapet.persistence.repository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.lojapet.model.Caixa;
import br.com.lojapet.model.Pagamento;

public interface CaixaRepository extends JpaRepository<Caixa, UUID>{

	Optional<Caixa> findByIsAbertoTrue();

	@Query("SELECT c FROM Caixa c WHERE c.isAberto = true")
	public Caixa caixaAbertoParaPagina();
	
	public Caixa findTopByOrderByFechadoDataHoraDesc();
	
	public Caixa findFirstByOrderByFechadoDataHoraDesc();

	
	@Query("SELECT c FROM Caixa c WHERE c.fechadoDataHora BETWEEN :start AND :end")
	List<Caixa> findCaixasPorDataFechamentoBetween(@Param("start") Calendar startWith,
			@Param("end") Calendar endWith);
}
