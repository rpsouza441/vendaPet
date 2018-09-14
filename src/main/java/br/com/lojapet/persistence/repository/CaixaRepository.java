package br.com.lojapet.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.lojapet.model.Caixa;

public interface CaixaRepository extends JpaRepository<Caixa, UUID>{

	Optional<Caixa> findByIsAbertoTrue();

	@Query("SELECT c FROM Caixa c WHERE c.isAberto = true")
	public Caixa caixaAbertoParaPagina();
	
	public Caixa findTopByOrderByFechadoDataHoraDesc();
	
	public Caixa findFirstByOrderByFechadoDataHoraDesc();

}
