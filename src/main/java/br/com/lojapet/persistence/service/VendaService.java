package br.com.lojapet.persistence.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lojapet.model.Venda;
import br.com.lojapet.persistence.repository.VendaRepository;


@Service
@Transactional
public class VendaService {

	@Autowired
	private VendaRepository dao;

	@Transactional
	public void saveVenda(Venda venda) {
		try {
			dao.save(venda);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public Venda saveVendaWithReturn(Venda venda) {
		try {
			return dao.save(venda);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Venda> getAllVendas() {
		try {
			return dao.findAll();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Venda getVendaById(UUID id) {
		try {

			Optional<Venda> vendaOptional = dao.findById(id);
			Venda venda;
			if (vendaOptional.isPresent()) {
				 venda = vendaOptional.get();
			} else {
				venda = new Venda();
			}
			return venda;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public void deleteVenda(UUID id) {
		try {
			dao.deleteById(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void updateVenda(Venda venda) {
		try {
			dao.save(venda);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

}
