package br.com.lojapet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.lojapet.model.Cliente;
import br.com.lojapet.model.Fornecedor;
import br.com.lojapet.model.Grupo;
import br.com.lojapet.model.Produto;
import br.com.lojapet.model.Role;
import br.com.lojapet.model.User;
import br.com.lojapet.persistence.service.ClienteService;
import br.com.lojapet.persistence.service.FornecedorService;
import br.com.lojapet.persistence.service.GrupoService;
import br.com.lojapet.persistence.service.ProdutoService;
import br.com.lojapet.persistence.service.RoleService;
import br.com.lojapet.persistence.service.UserService;

@Transactional
@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
public class PopuladorDeBanco implements CommandLineRunner{


	
	@Autowired private GrupoService grupoService;
	@Autowired private FornecedorService fornecedorService;
	@Autowired private RoleService roleService;
	@Autowired private UserService userService;
	@Autowired private ClienteService clienteService;
	@Autowired private ProdutoService produtoService;

	
	
	
	public PopuladorDeBanco( GrupoService grupoService,
			FornecedorService fornecedorService,  RoleService roleService,
			UserService userService) {
		this.grupoService = grupoService;
		this.fornecedorService = fornecedorService;
		this.roleService = roleService;
		this.userService = userService;
	}


	@Override
	public void run(String... args) throws Exception {
		for (Grupo g : populaGrupo()) {
			this.grupoService.saveGrupo(g);
		}
		
		
		for (Fornecedor f : populaFornecedor()) {
			this.fornecedorService.saveFornecedor(f);
		}
		
		
		for (Role r : populaRole()) {
			this.roleService.saveRole(r);
		}
		for (Produto p : populaProduto()) {
			this.produtoService.saveProduto(p);
			;
		}
		
		for (User u : populaUser()) {
			List<Role> role = new ArrayList<>();
			role = Arrays.asList(this.roleService.getRoleByAuthority
								(u.getAuthorities().get(0).getAuthority()));
//			u.getAuthorities().get(0).setUser(u);
			u.setAuthorities(role);
			this.userService.saveUser(u);
		}
		
		
		for(Cliente c : populaCliente()) {
			this.clienteService.saveCliente(c);
		}
		
	}
	





//	private List<Role> roleAdminParaUsuarioAdmin() {
//		Role role = roleRepository.findOne("ROLE_ADMIN");
//		List<Role> roles2 = new ArrayList<>();
//		roles2.add(role);
//		return roles2;
//	}
//
//	private List<Role> populaRoles() {
//		List<Role> roles = new ArrayList<Role>();
//
//		roles.add(new Role("ROLE_ADMIN"));
//		roles.add(new Role("ROLE_USER"));
//		return roles;
//	}
//
//	private void populaAdmin(Usuario usuario) {
//		usuario.setDataNascimento(Calendar.getInstance());
//		usuario.setEmail("admin@admin.com");
//		usuario.setFirstName("Admin");
//		usuario.setLastName("");
//		usuario.setSenha("admin");
//	}
	
	
	
	private List<Grupo> populaGrupo(){
		List<Grupo> grupos = new ArrayList<>();
		grupos = Arrays.asList(Grupo.builder().nome("Banho").build(), 
								Grupo.builder().nome("Jardim").build(),
								Grupo.builder().nome("Petiscos").build(),
								Grupo.builder().nome("Atacado").build(),
								Grupo.builder().nome("Varejo").build());
		return grupos;
	}
	
	private List<Produto> populaProduto(){
		List<Produto> produtos = new ArrayList<>();
		produtos = Arrays.asList(Produto.builder().nome("Sache de petisto")
												.estaAtivo(true).quantidade(10)
												.valorVenda(new BigDecimal(7))
												.build(),
								Produto.builder().nome("Remedio Anti pulgas")
												.estaAtivo(true)
												.quantidade(30)
												.valorVenda(new BigDecimal(25))
												.build()
								);
		return produtos;
	}
	
	
	private List<Fornecedor> populaFornecedor(){
		List<Fornecedor> fornecedor = new ArrayList<>();
		fornecedor = Arrays.asList(Fornecedor.builder().nome("Fornecedor Atacado").build(), 
								Fornecedor.builder().nome("Fornecedor Petisco").build(),
								Fornecedor.builder().nome("Fornecedor Remedios").build());
		return fornecedor;
	}
	
	
	private List<Role> populaRole(){
		List<Role> role = new ArrayList<>();
		role = Arrays.asList(Role.builder().authority("ADMIN").build(),
							Role.builder().authority("VENDOR").build(),
							Role.builder().authority("USER").build());
		return role;
	}
	
	private List<Cliente> populaCliente() {
		List<Cliente> cliente = new ArrayList<>();
		cliente = Arrays.asList(Cliente.builder().nomeCompleto("Cliente Básico").clienteSemNome(true).build());
		
		return cliente;
	}
	
	/*To-Do 
	 * cadastras passwords codificados */
	private List<User> populaUser(){
		List<User> cliente = new ArrayList<>();
		cliente = Arrays.asList(User.builder().nome("Administrador").username("admin").password(new BCryptPasswordEncoder().encode("admin")).authorities(Arrays.asList(Role.builder().authority("ADMIN").build())).build(), 
				User.builder().nome("Usuário").username("user").password(new BCryptPasswordEncoder().encode("user")).authorities(Arrays.asList(Role.builder().authority("USER").build())).build(),
				User.builder().nome("Usuário2").username("user2").password(new BCryptPasswordEncoder().encode("user2")).authorities(Arrays.asList(Role.builder().authority("USER").build())).build());
		return cliente;
	}

	

}
