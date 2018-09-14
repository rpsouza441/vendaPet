package br.com.lojapet.persistence.repository;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.lojapet.model.Pagamento;
import br.com.lojapet.model.StatusConta;

public interface PagamentoRepository extends JpaRepository<Pagamento, UUID> {

	// Optional<Pagamento> findByNome(String lastName);
	//
	// @Query("SELECT p FROM Produto p where p.nome like %:keyword% and p.quantidade
	// > 0")
	// public List<Produto> search(@Param("keyword") String keyword);
	//
	// @Query("SELECT p FROM Produto p where p.nome like %:keyword%")
	// public List<Produto> searchSemRestrincao(@Param("keyword") String keyword);
	//
	// List<Pagamento> findFirst10ProdutoByNomeContainingIgnoreCase(String nome);
	//
	//

	List<Pagamento> findByAndContaRecebidaIsNotNull();

	@Query("SELECT p FROM Pagamento p WHERE p.contaRecebida is not null")
	List<Pagamento> findAllContasRecebidas();

	@Query("SELECT p FROM Pagamento p WHERE p.contaPaga is not null")
	List<Pagamento> findAllContasPagas();

	@Query("SELECT p FROM Pagamento p WHERE p.contaPaga is not null AND p.dataVencimento BETWEEN :start AND :end")
	List<Pagamento> findContasPagasDataVencimentoBetween(@Param("start") Calendar startWith,
			@Param("end") Calendar endWith);

	@Query("SELECT p FROM Pagamento p WHERE p.contaPaga is not null AND p.estaQuitado = :status AND p.dataVencimento BETWEEN :start AND :end")
	List<Pagamento> findContasPagasDataVencimentoBetweenAndStatus(@Param("start") Calendar startWith,
			@Param("end") Calendar endWith, @Param("status") StatusConta status);

	@Query("SELECT p FROM Pagamento p WHERE p.contaRecebida is not null AND p.dataVencimento BETWEEN :start AND :end")
	List<Pagamento> findContasRecebidasDataVencimentoBetween(@Param("start") Calendar startWith,
			@Param("end") Calendar endWith);

	@Query("SELECT p FROM Pagamento p WHERE p.contaRecebida is not null AND p.estaQuitado = :status AND p.dataVencimento BETWEEN :start AND :end")
	List<Pagamento> findContasRecebidasDataVencimentoBetweenAndStatus(@Param("start") Calendar startWith,
			@Param("end") Calendar endWith, @Param("status") StatusConta status);
	// boolean existsByNome(String nome);

}
