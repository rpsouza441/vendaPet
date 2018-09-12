//package br.com.lojapet.persistence.repository;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import br.com.lojapet.model.DespesaAPagar;
//
//public interface DespesaAPagarRepository extends JpaRepository<DespesaAPagar, UUID> {
//
//	@Query("SELECT d FROM DespesaAPagar d where d.nome like %:keyword%")
//	List<DespesaAPagar> search(@Param("keyword") String keyword);
//	
//	public Optional<DespesaAPagar> findById(UUID id);
//
//}
