package br.com.lojapet.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.lojapet.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {

	Optional<User> findByUsername(String username);

	@Query("SELECT u FROM User u where u.nome like %:keyword%")
	public List<User> search(@Param("keyword") String keyword);

	boolean existsByUsername(String username);
	
}
