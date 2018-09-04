package br.com.lojapet.persistence.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lojapet.model.Grupo;
import br.com.lojapet.persistence.repository.GrupoRepository;


@Service
@Transactional
public class GrupoService {

	@Autowired
	private GrupoRepository dao;

	@Transactional
	public void saveGrupo(Grupo grupo) {
		try {
			dao.save(grupo);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	public List<Grupo> getAllGrupos() {
		try {
			return dao.findAll();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Grupo getGrupoById(UUID id) {
		try {

			Optional<Grupo> grupoOptional = dao.findById(id);
			Grupo grupo;
			if (grupoOptional.isPresent()) {
				 grupo = grupoOptional.get();
			} else {
				grupo = new Grupo();
			}
			return grupo;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public void deleteGrupo(UUID id) {
		try {
			dao.deleteById(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void updateGrupo(Grupo grupo) {
		try {
			dao.save(grupo);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	public List<Grupo> search(String q) {
		return dao.search(q);
	}

}
