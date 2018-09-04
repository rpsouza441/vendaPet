package br.com.lojapet.persistence.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lojapet.model.Cliente;
import br.com.lojapet.model.Produto;
import br.com.lojapet.persistence.repository.ClienteRepository;


@Service
@Transactional
public class ClienteService {

	@Autowired
	private ClienteRepository dao;

	@Transactional
	public void saveCliente(Cliente cliente) {
		try {
			cliente.setDataCadastro(Calendar.getInstance());
			dao.save(cliente);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	public List<Cliente> search(String keyword) {
		return dao.search(keyword);
	}

	public List<Cliente> getAllClientes() {
		try {
			return dao.findAll();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Cliente getClienteById(UUID id) {
		try {

			Optional<Cliente> clienteOptional = dao.findById(id);
			Cliente cliente;
			if (clienteOptional.isPresent()) {
				 cliente = clienteOptional.get();
			} else {
				cliente = new Cliente();
			}
			return cliente;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public void deleteCliente(UUID id) {
		try {
			dao.deleteById(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void updateCliente(Cliente cliente) {
		try {
			dao.save(cliente);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

}
