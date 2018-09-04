package br.com.lojapet.persistence.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lojapet.model.Carteira;
import br.com.lojapet.persistence.repository.CarteiraRepository;


@Service
@Transactional
public class CarteiraService {

	@Autowired
	private CarteiraRepository dao;

	@Transactional
	public void saveCarteira(Carteira carteira) {
		try {
			dao.save(carteira);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	public List<Carteira> getAllCarteiras() {
		try {
			return dao.findAll();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Carteira getCarteiraById(UUID id) {
		try {

			Optional<Carteira> carteiraOptional = dao.findById(id);
			Carteira carteira;
			if (carteiraOptional.isPresent()) {
				 carteira = carteiraOptional.get();
			} else {
				carteira = new Carteira();
			}
			return carteira;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public void deleteCarteira(UUID id) {
		try {
			dao.deleteById(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void updateCarteira(Carteira carteira) {
		try {
			dao.save(carteira);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

}
