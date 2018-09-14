package br.com.lojapet.persistence.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lojapet.model.Caixa;
import br.com.lojapet.model.MovimentoDeCaixa;
import br.com.lojapet.persistence.repository.CaixaRepository;

@Service
@Transactional
public class CaixaService {

	@Autowired
	private CaixaRepository dao;

	@Transactional
	public void saveCaixa(Caixa caixa) {
		try {
			dao.save(caixa);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public Caixa saveCaixaWithReturn(Caixa caixa) {
		try {
			return dao.save(caixa);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Caixa> getAllCaixas() {
		try {
			return dao.findAll();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Caixa getCaixaById(UUID id) {
		try {

			Optional<Caixa> caixaOptional = dao.findById(id);
			Caixa caixa;
			if (caixaOptional.isPresent()) {
				caixa = caixaOptional.get();
			} else {
				caixa = new Caixa();
			}
			return caixa;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Caixa getCaixaAberto() {
		try {

			Optional<Caixa> caixaOptional = dao.findByIsAbertoTrue();
			Caixa caixa;
			if (caixaOptional.isPresent()) {
				caixa = caixaOptional.get();
			} else {
				Caixa caixaTemp = Caixa.builder().abertoDataHora(Calendar.getInstance())
						.listaMovimentacoes(new ArrayList<MovimentoDeCaixa>()).isAberto(true).build();

				caixa = saveCaixaWithReturn(caixaTemp);
			}
			return caixa;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public void deleteCaixa(UUID id) {
		try {
			dao.deleteById(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void updateCaixa(Caixa caixa) {
		try {
			dao.save(caixa);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	public Caixa buscaCaixaParaPagina() {
		return dao.caixaAbertoParaPagina();
	}

	public Calendar getDateTimeUltimoCaixaFechado() {
		Calendar fechadoDataHora = null;

		try {
			fechadoDataHora = dao.findFirstByOrderByFechadoDataHoraDesc().getFechadoDataHora();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return fechadoDataHora;

	}

}
