package br.com.lojapet.persistence.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lojapet.model.DespesaAPagar;
import br.com.lojapet.persistence.repository.DespesaAPagarRepository;


@Service
@Transactional
public class DespesaService {

	@Autowired
	private DespesaAPagarRepository dao;

	@Transactional
	public void saveDespesa(DespesaAPagar despesa) {
		try {
			dao.save(despesa);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void saveDespesa(List<DespesaAPagar> despesas) {
		for (DespesaAPagar d : despesas) {
			try {
				dao.save(d);
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}

	public List<DespesaAPagar> getAllDespesas() {
		try {
			return dao.findAll();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public DespesaAPagar getDespesaById(UUID id) {
		try {

			Optional<DespesaAPagar> despesaOptional = dao.findById(id);
			DespesaAPagar despesa;
			if (despesaOptional.isPresent()) {
				 despesa = despesaOptional.get();
			} else {
				despesa = new DespesaAPagar();
			}
			return despesa;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public void deleteDespesa(UUID id) {
		try {
			dao.deleteById(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void updateDespesa(DespesaAPagar despesa) {
		try {
			dao.save(despesa);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

}
