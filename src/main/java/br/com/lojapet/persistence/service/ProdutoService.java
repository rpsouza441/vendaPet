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

	public void novaCompra(List<Produto> list) {
		for (Produto p : list) {
			Produto produtoById = getProdutoById(p.getId());
			System.out.println("quantidade antes de ser alterada" + produtoById.getQuantidade());
			produtoById.adicionaQuantidadeAlteravalor(p.getQuantidade(), p.getValorCusto());
			System.out.println("quantidade a adicionar" + p.getQuantidade());

			System.out.println("quantidade alteradada" + produtoById.getQuantidade());

			updateProduto(produtoById);
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

	@Transactional
	public List<Produto> saveProdutoWithReturn(List<Produto> produtos) {
		List<Produto> produtoRetorno = new ArrayList<>();
		for (Produto p : produtos) {
			try {
				produtoRetorno.add(dao.save(p));
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
		return dao.searchSemRestrincao(keyword);
	}

	public Produto getProdutoByNome(String nome) {
		try {

			Optional<Produto> produtoOptional = dao.findByNome(nome);
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
	public Produto updateProduto(Produto produto) {
		try {
			return dao.save(produto);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean produtoExisteCom(String nome) {
		if (nome != null && nome != "") {
			return dao.existsByNome(nome);
		}
		return false;
	}

	public boolean existeComNome(String nome) {
		if (nome != null && nome != "") {
			return dao.existsByNome(nome);
		}
		return false;
	}
}
