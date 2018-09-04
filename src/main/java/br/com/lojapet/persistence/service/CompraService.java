package br.com.lojapet.persistence.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lojapet.model.Compra;
import br.com.lojapet.model.Fornecedor;
import br.com.lojapet.model.Produto;
import br.com.lojapet.persistence.repository.CompraRepository;
import br.com.lojapet.persistence.repository.FornecedorRepository;
import br.com.lojapet.persistence.repository.ProdutoRepository;

@Service
@Transactional
public class CompraService {

	@Autowired
	private CompraRepository dao;

	@Autowired
	private FornecedorService fornecedorService;

	@Autowired
	private ProdutoService produtoService;

	@Transactional
	public void saveCompra(Compra compra) {
		try {
			dao.save(compra);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void saveCompra(List<Compra> compras) {
		for (Compra c : compras) {
			try {
				dao.save(c);
			} catch (DataAccessException e) {
				e.printStackTrace();
			}

		}

	}

	@Transactional
	public void saveCompra(Compra compra, UUID fornecedor, List<Produto> produtos) {
		Fornecedor fornecedorById = fornecedorService.getFornecedorById(fornecedor);
		List<Produto> saveProdutoWithReturn = produtoService.saveProdutoWithReturn(produtos, fornecedorById);

		compra.setProduto(saveProdutoWithReturn);

		try {
			dao.save(compra);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

	}

	public List<Compra> getAllCompras() {
		try {
			return dao.findAll();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Compra getCompraById(UUID id) {
		try {

			Optional<Compra> compraOptional = dao.findById(id);
			Compra compra;
			if (compraOptional.isPresent()) {
				compra = compraOptional.get();
			} else {
				compra = new Compra();
			}
			return compra;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public void deleteCompra(UUID id) {
		try {
			dao.deleteById(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void updateCompra(Compra compra) {
		try {
			dao.save(compra);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

}
