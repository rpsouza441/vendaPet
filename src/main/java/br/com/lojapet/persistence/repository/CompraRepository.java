package br.com.lojapet.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lojapet.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, UUID> {

}
