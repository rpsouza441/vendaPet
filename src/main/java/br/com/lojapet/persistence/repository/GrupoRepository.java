package br.com.lojapet.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.lojapet.model.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, UUID> {

	@Query("SELECT g FROM Grupo g where g.nome like %:keyword%")
	public List<Grupo> search(@Param("keyword") String keyword);

}
