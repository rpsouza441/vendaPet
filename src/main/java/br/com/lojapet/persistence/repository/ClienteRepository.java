package br.com.lojapet.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.lojapet.model.Cliente;

public interface ClienteRepository  extends JpaRepository<Cliente, UUID> {
	
	@Query("SELECT c FROM Cliente c where c.nomeCompleto like %:keyword%")
	public List<Cliente> search(@Param("keyword") String keyword);

	public Cliente findByNomeCompleto(String clienteNome);
	
	
	@Query("SELECT nomeCompleto FROM Cliente where nomeCompleto like %:keyword%")
	public List<String> autocomplete(@Param("keyword") String keyword);

	public boolean existsByNomeCompleto(String nome);
	

}
