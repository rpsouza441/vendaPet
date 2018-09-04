//package br.com.lojapet.persistence.repository;
//
//import java.util.List;
//import java.util.UUID;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import br.com.lojapet.model.Usuario;
//
//
//public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
//
//	Usuario findByEmail(String email);
//
//	List<Usuario> findByFirstNameStartingWithOrderByFirstName(String nome);
//
//	List<Usuario> findByFirstNameContainingOrLastNameContaining(String search,String search2);
//	
//}
//