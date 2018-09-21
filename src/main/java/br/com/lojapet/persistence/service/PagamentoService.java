package br.com.lojapet.persistence.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lojapet.model.Pagamento;
import br.com.lojapet.model.PagamentoEditForm;
import br.com.lojapet.model.StatusConta;
import br.com.lojapet.persistence.repository.PagamentoRepository;

@Service
@Transactional
public class PagamentoService {

	@Autowired
	private PagamentoRepository dao;

	@Transactional
	public void savePagamento(Pagamento produto) {
		try {
			dao.save(produto);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public Pagamento savePagamentoWithReturn(Pagamento produto) {
		try {
			return dao.save(produto);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Transactional
	public List<Pagamento> savePagamentoWithReturn(List<Pagamento> produtos) {
		List<Pagamento> produtoRetorno = new ArrayList<>();
		for (Pagamento p : produtos) {
			try {
				produtoRetorno.add(dao.save(p));
			} catch (DataAccessException e) {
				e.printStackTrace();
				return null;
			}
		}

		return produtoRetorno;

	}

	public List<Pagamento> getAllPagamentos() {
		try {
			return dao.findAll();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	// public List<Pagamento> procurarContasAReceber(String keyword) {
	// return dao.search(keyword);
	// }
	// public List<Pagamento> procurarContasAPagar(String keyword) {
	// return dao.search(keyword);
	// }

	public Pagamento getPagamentoById(UUID id) {
		try {

			Optional<Pagamento> produtoOptional = dao.findById(id);
			Pagamento produto;
			if (produtoOptional.isPresent()) {
				produto = produtoOptional.get();
			} else {
				produto = new Pagamento();
			}
			return produto;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public void deletePagamento(UUID id) {
		try {
			dao.deleteById(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	public void updateCom(PagamentoEditForm pagamentoEditForm) {
		Pagamento pagamento = dao.getOne(pagamentoEditForm.getId());

		pagamento.updateCom(pagamentoEditForm);
		updatePagamento(pagamento);

	}


	public void cancelaPagamento(UUID id) {
		Pagamento pagamento = dao.getOne(id);
		pagamento.cancelar();
		updatePagamento(pagamento);

	}
	
	public void estornar(UUID id) {
		Pagamento pagamento = dao.getOne(id);
		pagamento.estorno();
		updatePagamento(pagamento);


	}

	@Transactional
	public Pagamento updatePagamento(Pagamento pagamento) {
		try {
			return dao.save(pagamento);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Pagamento> getAllContasRecebidas() {
		return dao.findAllContasRecebidas();
	}

	public List<Pagamento> getAllContasPagas() {
		return dao.findAllContasPagas();
	}

	public List<Pagamento> findContasPagasPorDataVencimentoBetween(Calendar startWith, Calendar endWith) {
		return dao.findContasPagasDataVencimentoBetween(startWith, endWith);
	}

	public List<Pagamento> findContasPagasPorDataVencimentoPagasBetween(Calendar startWith, Calendar endWith) {
		return dao.findContasPagasDataVencimentoBetweenAndStatus(startWith, endWith, StatusConta.QUITADO);
	}

	public List<Pagamento> findContasPagasPorDataVencimentoNaoPagasBetween(Calendar startWith, Calendar endWith) {
		return dao.findContasPagasDataVencimentoBetweenAndStatus(startWith, endWith, StatusConta.NAOQUITADO);
	}

	public List<Pagamento> findContasPagasPorDataVencimentoCanceladasBetween(Calendar startWith, Calendar endWith) {
		return dao.findContasPagasDataVencimentoBetweenAndStatus(startWith, endWith, StatusConta.CANCELADO);
	}

	public List<Pagamento> findContasRecebidasPorDataVencimentoBetween(Calendar startWith, Calendar endWith) {
		return dao.findContasRecebidasDataVencimentoBetween(startWith, endWith);

	}

	public List<Pagamento> findContasRecebidasPorDataVencimentoPagasBetween(Calendar startWith, Calendar endWith) {
		return dao.findContasRecebidasDataVencimentoBetweenAndStatus(startWith, endWith, StatusConta.QUITADO);

	}

	public List<Pagamento> findContasRecebidasPorDataVencimentoNaoPagasBetween(Calendar startWith, Calendar endWith) {
		return dao.findContasRecebidasDataVencimentoBetweenAndStatus(startWith, endWith, StatusConta.NAOQUITADO);

	}

	public List<Pagamento> findContasRecebidasPorDataVencimentoCanceladasBetween(Calendar startWith, Calendar endWith) {
		return dao.findContasRecebidasDataVencimentoBetweenAndStatus(startWith, endWith, StatusConta.CANCELADO);

	}



	// public boolean produtoExisteCom(String nome) {
	// if (nome != null && nome != "") {
	// return dao.existsByNome(nome);
	// }
	// return false;
	// }

}
