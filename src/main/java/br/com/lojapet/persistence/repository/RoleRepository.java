package br.com.lojapet.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lojapet.model.Role;

public interface RoleRepository extends JpaRepository<Role, UUID> {

	Optional<Role> findByAuthority(String authority);

}
