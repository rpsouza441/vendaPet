package br.com.lojapet.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.lojapet.model.Produto;

public interface ProdutoRepository  extends JpaRepository<Produto, UUID> {
	
	List<Produto> findByNome(String lastName);
	
	
	
	
	@Query("SELECT p FROM Produto p where p.nome like %:keyword% and p.quantidade > 0")
	public List<Produto> search(@Param("keyword") String keyword);
	
	@Query("SELECT p FROM Produto p where p.nome like %:keyword%")
	public List<Produto> searchSemRestrincao(@Param("keyword") String keyword);

	List<Produto> findFirst10ProdutoByNomeContainingIgnoreCase(String nome);

	
		
}
