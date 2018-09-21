package br.com.lojapet.persistence.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lojapet.model.Fornecedor;
import br.com.lojapet.persistence.repository.FornecedorRepository;

@Service
@Transactional
public class FornecedorService {

	@Autowired
	private FornecedorRepository dao;

	@Transactional
	public void saveFornecedor(Fornecedor fornecedor) {
		try {
			dao.save(fornecedor);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public Fornecedor saveFornecedorWithReturn(Fornecedor fornecedor) {

		try {
			return dao.save(fornecedor);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Fornecedor> getAllFornecedors() {
		try {
			return dao.findAll();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Fornecedor getFornecedorByNome(String nome) {
		try {

			Optional<Fornecedor> fornecedorOptional = dao.findByNome(nome);
			Fornecedor fornecedor;
			if (fornecedorOptional.isPresent()) {
				fornecedor = fornecedorOptional.get();
			} else {
				fornecedor = new Fornecedor();
			}
			return fornecedor;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public Fornecedor getFornecedorById(UUID id) {
		try {

			Optional<Fornecedor> fornecedorOptional = dao.findById(id);
			Fornecedor fornecedor;
			if (fornecedorOptional.isPresent()) {
				fornecedor = fornecedorOptional.get();
			} else {
				fornecedor = new Fornecedor();
			}
			return fornecedor;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public void deleteFornecedor(UUID id) {
		try {
			dao.deleteById(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void updateFornecedor(Fornecedor fornecedor) {
		try {
			dao.save(fornecedor);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	public List<Fornecedor> search(String q) {
		return dao.search(q);

	}

	public boolean fornecedorExisteCom(String nome) {
		if (nome != null && nome != "") {
			return dao.existsByNome(nome);
		}
		return false;
	}

	public boolean existeComNome(String nome) {
		if (nome != null && nome != "") {
			return dao.existsByNome(nome);
		}
		return false;
	}
	

}
