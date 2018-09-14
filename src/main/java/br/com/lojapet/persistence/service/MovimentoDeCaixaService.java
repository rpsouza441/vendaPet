package br.com.lojapet.persistence.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lojapet.model.MovimentoDeCaixa;
import br.com.lojapet.model.Produto;
import br.com.lojapet.persistence.repository.MovimentoDeCaixaRepository;


@Service
@Transactional
public class MovimentoDeCaixaService {

	@Autowired
	private MovimentoDeCaixaRepository dao;

	@Transactional
	public void saveMovimentoDeCaixa(MovimentoDeCaixa movimentoDeCaixa) {
		try {
			dao.save(movimentoDeCaixa);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	@Transactional
	public MovimentoDeCaixa saveMovimentoDeCaixaWithReturn(MovimentoDeCaixa movimentoDeCaixa) {
		try {
			return dao.save(movimentoDeCaixa);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	public List<MovimentoDeCaixa> saveMovimentoDeCaixaWithReturn(List<MovimentoDeCaixa> movimentoEntrada) {

		List<MovimentoDeCaixa> listaMovimentoPersistido = new ArrayList<>();
		for (MovimentoDeCaixa m : movimentoEntrada) {
			try {
				listaMovimentoPersistido.add(dao.save(m));
			} catch (DataAccessException e) {
				e.printStackTrace();
				return null;
			}
		}
		return listaMovimentoPersistido;
	}

	public List<MovimentoDeCaixa> getAllMovimentoDeCaixas() {
		try {
			return dao.findAll();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public MovimentoDeCaixa getMovimentoDeCaixaById(UUID id) {
		try {

			Optional<MovimentoDeCaixa> movimentoDeCaixaOptional = dao.findById(id);
			MovimentoDeCaixa movimentoDeCaixa;
			if (movimentoDeCaixaOptional.isPresent()) {
				 movimentoDeCaixa = movimentoDeCaixaOptional.get();
			} else {
				movimentoDeCaixa = new MovimentoDeCaixa();
			}
			return movimentoDeCaixa;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public void deleteMovimentoDeCaixa(UUID id) {
		try {
			dao.deleteById(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void updateMovimentoDeCaixa(MovimentoDeCaixa movimentoDeCaixa) {
		try {
			dao.save(movimentoDeCaixa);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	


}
