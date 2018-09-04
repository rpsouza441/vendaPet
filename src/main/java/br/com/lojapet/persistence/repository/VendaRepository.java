package br.com.lojapet.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lojapet.model.Venda;

public interface VendaRepository  extends JpaRepository<Venda, UUID> {
	
	

}
