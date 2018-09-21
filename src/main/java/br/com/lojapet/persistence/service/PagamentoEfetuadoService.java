package br.com.lojapet.persistence.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lojapet.model.PagamentoEfetuado;
import br.com.lojapet.persistence.repository.PagamentoEfetuadoRepository;

@Service
@Transactional
public class PagamentoEfetuadoService {

	@Autowired
	private PagamentoEfetuadoRepository dao;

	@Transactional
	public void savePagamentoEfetuado(PagamentoEfetuado produto) {
		try {
			dao.save(produto);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public PagamentoEfetuado savePagamentoEfetuadoWithReturn(PagamentoEfetuado produto) {
		try {
			return dao.save(produto);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Transactional
	public List<PagamentoEfetuado> savePagamentoEfetuadoWithReturn(List<PagamentoEfetuado> produtos) {
		List<PagamentoEfetuado> produtoRetorno = new ArrayList<>();
		for (PagamentoEfetuado p : produtos) {
			try {
				produtoRetorno.add(dao.save(p));
			} catch (DataAccessException e) {
				e.printStackTrace();
				return null;
			}
		}

		return produtoRetorno;

	}

	public List<PagamentoEfetuado> getAllPagamentoEfetuados() {
		try {
			return dao.findAll();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public PagamentoEfetuado getPagamentoEfetuadoById(UUID id) {
		try {

			Optional<PagamentoEfetuado> produtoOptional = dao.findById(id);
			PagamentoEfetuado produto;
			if (produtoOptional.isPresent()) {
				produto = produtoOptional.get();
			} else {
				produto = new PagamentoEfetuado();
			}
			return produto;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public void deletePagamentoEfetuado(UUID id) {
		try {
			dao.deleteById(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public PagamentoEfetuado updatePagamentoEfetuado(PagamentoEfetuado pagamento) {
		try {
			return dao.save(pagamento);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void deletePagamentoEfetuado(List<PagamentoEfetuado> listaPagamentosEfetuados) {
		for (PagamentoEfetuado pagamentoEfetuado : listaPagamentosEfetuados) {
			deletePagamentoEfetuado(pagamentoEfetuado.getId());
		}
	}

}
