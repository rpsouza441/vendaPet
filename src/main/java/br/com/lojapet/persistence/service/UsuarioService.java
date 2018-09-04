//package br.com.lojapet.persistence.service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import br.com.lojapet.model.Usuario;
//import br.com.lojapet.persistence.repository.UsuarioRepository;
//
//
//@Service
//@Transactional
//public class UsuarioService {
//
//	@Autowired
//	private UsuarioRepository dao;
//
//	@Transactional
//	public void saveUsuario(Usuario usuario) {
//		try {
//			dao.save(usuario);
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public List<Usuario> getAllUsuarios() {
//		try {
//			return dao.findAll();
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public Usuario getUsuarioById(UUID id) {
//		try {
//
//			Optional<Usuario> usuarioOptional = dao.findById(id);
//			Usuario usuario;
//			if (usuarioOptional.isPresent()) {
//				 usuario = usuarioOptional.get();
//			} else {
//				usuario = new Usuario();
//			}
//			return usuario;
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Transactional
//	public void deleteUsuario(UUID id) {
//		try {
//			dao.deleteById(id);
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Transactional
//	public void updateUsuario(Usuario usuario) {
//		try {
//			dao.save(usuario);
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//		}
//	}
//
//}
