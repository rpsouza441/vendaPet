package br.com.lojapet.persistence.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import br.com.lojapet.model.Fornecedor;
import br.com.lojapet.model.Produto;
import br.com.lojapet.persistence.repository.ProdutoRepository;

@Service
@Transactional
public class ProdutoService {

	@Autowired
	private ProdutoRepository dao;

	@Transactional
	public void saveProduto(Produto produto) {
		try {
			dao.save(produto);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	public void removeQuantidade(Map<UUID, Integer> uuidEQuantidade) {
		try {
			uuidEQuantidade.forEach((k, v) -> removeQuantidade(k, v));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void removeQuantidade(UUID id, Integer quantidade) {
		try {
			Produto produtoById = getProdutoById(id);
			produtoById.removeQuantidade(quantidade);
			dao.save(produtoById);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public Produto saveProdutoWithReturn(Produto produto) {
		try {
			return dao.save(produto);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<Produto> saveProdutoWithReturn(List<Produto> produtos, Fornecedor fornecedor) {
		List<Produto> produtoRetorno = new ArrayList<>();
		List<Fornecedor> fornecedores = new ArrayList<>();
		fornecedores.add(fornecedor);
		for (Produto p : produtos) {
			try {
				p.setFornecedores(fornecedores);
				Produto produto = dao.save(p);
				produtoRetorno.add(produto);
			} catch (DataAccessException e) {
				e.printStackTrace();
				return null;
			}
		}

		return produtoRetorno;

	}

	public List<Produto> getAllProdutos() {
		try {
			return dao.findAll();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Produto> search(String keyword) {
		return dao.search(keyword);
	}
	public List<Produto> searchSemRestrincao(String keyword) {
		return dao.search(keyword);
	}

	public Produto getProdutoById(UUID id) {
		try {

			Optional<Produto> produtoOptional = dao.findById(id);
			Produto produto;
			if (produtoOptional.isPresent()) {
				produto = produtoOptional.get();
			} else {
				produto = new Produto();
			}
			return produto;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public void deleteProduto(UUID id) {
		try {
			dao.deleteById(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void updateProduto(Produto produto) {
		try {
			dao.save(produto);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

}
