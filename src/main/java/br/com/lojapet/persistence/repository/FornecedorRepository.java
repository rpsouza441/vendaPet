package br.com.lojapet.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.lojapet.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, UUID> {

	@Query("SELECT f FROM Fornecedor f where f.nome like %:keyword%")
	public List<Fornecedor> search(@Param("keyword") String keyword);

	public boolean existsByNome(String nome);

	public Optional<Fornecedor> findByNome(String nome);

}
