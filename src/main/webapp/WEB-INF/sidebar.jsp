<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>	
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@attribute name="home" required="false" %>
<%@attribute name="cadastroGrupo" required="false" %>
<%@attribute name="cadastroCliente" required="false" %>
<%@attribute name="cadastroFornecedor" required="false" %>
<%@attribute name="cadastroProduto" required="false" %>
<%@attribute name="cadastroUser" required="false" %>
<%@attribute name="cadastroDespesaAPagar" required="false" %>
<%@attribute name="cadastroCompra" required="false" %>
<%@attribute name="cadastroVenda" required="false" %>


<%@attribute name="atendimentoCarrinho" required="false" %>


<%@attribute name="listaGrupo" required="false" %>
<%@attribute name="listaCliente" required="false" %>
<%@attribute name="listaFornecedor" required="false" %>
<%@attribute name="listaProduto" required="false" %>
<%@attribute name="listaVenda" required="false" %>



<!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="/resources/dist/img/logo-white.png" class="img-circle" alt="User Image">
        </div>
      </div>
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header"> <fmt:message key="navegacao" /></li>


<!-- 		link home -->
		<li class="${home}">
          <a href="/">
            <i class="fa fa-fw fa-home"></i> <span><fmt:message key="navegacao.home" /></span>
          </a>
        </li>
        
        
<!--         arvore de atendimento -->
        <li class="treeview ${atendimentoCarrinho} ${atendimentoOrcamento} ">

          <a href="#">
            <i class="fa fa-fw fa-dollar"></i> <span>
            <fmt:message key="navegacao.atendimento" /></span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
          
          <li >
              <a href="#"> 
              	<h4><fmt:message key="navegacao.atendimento" /></h4>
              </a>
              <ul class="treeview-menu" style="display: block;">
                <li class="${atendimentoCarrinho}">
	          <a href="/carrinho">
	            <i class="fa fa-fw fa-shopping-cart"></i> <span>
	            <fmt:message key="navegacao.venda" /></span>
	          </a>
	        </li>
	        <li class="${atendimentoOrcamento}">
	          <a href="/produto/orcamento">
	            <i class="fa fa-fw fa-th-list"></i> <span><fmt:message key="navegacao.orcamento" /></span>
	          </a>
	        </li>
                
                
              </ul>
            </li>
            
            
            
          <li >
              <a href="#"> 
              <h4>
              	<fmt:message key="navegacao.cadastro" />
              </h4>
              </a>
              <ul class="treeview-menu" style="display: block;">
                <li class="${cadastroCliente}">
			          <a href="/cliente/cadastro">
			            <i class="fa fa-fw fa-user"></i> <span>
			            <fmt:message key="navegacao.cliente" /></span>
			          </a>
	           </li>
                
                
              </ul>
            </li>
            
            
            
           
          </ul>
        </li>
        
        <!-- Arvore de estoque -->
        <li class="treeview ${cadastroGrupo } ${cadastroCliente} 
        ${cadastroFornecedor} ${cadastroProduto} ${cadastroUser} ${cadastroDespesaAPagar}  
        ${cadastroCompra} ${cadastroVenda}">
          <a href="#">
            <i class="fa fa-fw fa-cubes"></i> <span><fmt:message key="navegacao.estoque" /></span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
          	
          	
          	<li >
              <a href="#"> 
              <h4>
              	<fmt:message key="navegacao.controle" />
              </h4>
              </a>
              <ul class="treeview-menu" style="display: block;">
                <li>
	                <a href="#">
		                <i class="fa fa-fw fa-search"></i>
		                <fmt:message key="navegacao.consulta" />
	                </a>
                </li>
                
                <li>
	                <a href="#">
		                <i class="fa fa-fw fa-arrow-down"></i>
		                <fmt:message key="navegacao.entradaNF" />
	                </a>
                </li>
                
                <li>
                	<a href="#">
		                <i class="fa fa-fw fa-check"></i>
		                <fmt:message key="navegacao.auditoria" />
	                </a>
                </li>
                
                
              </ul>
            </li>
            
            
            
            
          	<li >
              <a href="#"> 
              	 <h4>
              	 	<fmt:message key="navegacao.cadastro" />
              	 </h4>
              </a>
              <ul class="treeview-menu" style="display: block;">
               <li class="${cadastroFornecedor}">
			          <a href="/fornecedor/cadastro">
			            <i class="fa fa-fw fa-user-circle"></i> <span>
			            <fmt:message key="navegacao.fornecedor" /></span>
			          </a>
	          </li>
	          
	        <li class="${cadastroProduto}">
	          <a href="/produto/cadastro">
	            <i class="fa fa-fw fa-cube"></i> <span>
	            <fmt:message key="navegacao.produto" /></span>
	          </a>
	        </li>
	        
	        <li class="${cadastroGrupo}">
	          <a href="/grupo/cadastro">
	            <i class="fa fa-fw fa-qrcode"></i> <span>
	            <fmt:message key="navegacao.grupo" /></span>
	          </a>
	        </li>
                
                
              </ul>
            </li>
          </ul>
        </li>
        
        
        
        
        <!-- arvore financeiro  -->
        <li class="treeview ${atendimentoCarrinho} ${atendimentoOrcamento} ">

          <a href="#">
            <i class="fa fa-fw fa-dollar"></i> <span>
            <fmt:message key="navegacao.financeiro" /></span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
          <li class="${atendimentoOrcamento}">
	          <a href="/produto/orcamento">
	            <i class="fa fa-fw fa-inbox"></i> <span>
	            <fmt:message key="navegacao.gaveta" /></span>
	          </a>
	        </li>
          
          <li >
              <a href="#"> 
              	<h4><fmt:message key="navegacao.controle" /></h4>
              </a>
              <ul class="treeview-menu" style="display: block;">
                <li class="${atendimentoCarrinho}">
	          <a href="/carrinho">
	            <i class="fa fa-fw fa-sign-in"></i> <span>
	            <fmt:message key="navegacao.contaAReceber" /></span>
	          </a>
	        </li>
	        <li class="${atendimentoOrcamento}">
	          <a href="/produto/orcamento">
	            <i class="fa fa-fw fa-sign-out"></i> <span>
	            <fmt:message key="navegacao.contaAPagar" /></span>
	          </a>
	        </li>
	        <li class="${atendimentoOrcamento}">
	          <a href="/produto/orcamento">
	            <i class="fa fa-fw fa-bar-chart"></i> <span>
	            <fmt:message key="navegacao.fluxoCaixa" /></span>
	          </a>
	        </li>
                
                
              </ul>
            </li>
            
           
          </ul>
        </li>
        
        
        
        
        <!-- arvore Relatorio  -->
         <li class="treeview ">
          <a href="#">
            <i class="fa fa-fw fa-bar-chart-o"></i> <span>
            <fmt:message key="navegacao.relatorio" /></span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
           <li class="${cadastroFornecedor}">
			   <a href="/fornecedor/cadastro">
			        <i class="fa fa-fw fa-inbox"></i> <span>
			        <fmt:message key="navegacao.relatorio.gaveta" /></span>
			   </a>
	          </li>
	          
	          
           <li class="${cadastroFornecedor}">
			   <a href="/fornecedor/cadastro">
			        <i class="fa fa-fw fa-bar-chart"></i> <span>
			        <fmt:message key="navegacao.relatorio.fluxo" /></span>
			   </a>
	        </li>
	          
	          
           <li class="${cadastroFornecedor}">
			   <a href="/fornecedor/cadastro">
			        <i class="fa fa-fw fa-sign-in"></i> <span>
			        <fmt:message key="navegacao.relatorio.contaReceber" /></span>
			   </a>
	       </li>
	          
	          
           <li class="${cadastroFornecedor}">
			   <a href="/fornecedor/cadastro">
			        <i class="fa fa-fw fa-sign-in"></i> <span>
			        <fmt:message key="navegacao.relatorio.contaRecebidas" /></span>
			   </a>
	       </li>
	          
	          
           <li class="${cadastroFornecedor}">
			   <a href="/fornecedor/cadastro">
			        <i class="fa fa-fw fa-sign-out"></i> <span>
			        <fmt:message key="navegacao.relatorio.contaPagar" /></span>
			   </a>
	       </li>
	          
	          
	          
           <li class="${cadastroFornecedor}">
			   <a href="/fornecedor/cadastro">
			        <i class="fa fa-fw fa-sign-out"></i> <span>
			        <fmt:message key="navegacao.relatorio.contaQuitadas" /></span>
			   </a>
	       </li>
	          
	          
	          
	          
          </ul>
        </li>
        
        
            
           
        
        
        
        
        
        
        
<!--         arvore de cadastro -->
        <li class="treeview ${cadastroGrupo } ${cadastroCliente} 
        ${cadastroFornecedor} ${cadastroProduto} ${cadastroUser} ${cadastroDespesaAPagar}  
        ${cadastroCompra} ${cadastroVenda}">
          <a href="#">
            <i class="fa fa-plus-square"></i> <span><fmt:message key="navegacao.cadastro" /></span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
	        <li class="${cadastroCompra}">
	          <a href="/compra/cadastro">
	            <i class="fa   fa-circle-o"></i> <span><fmt:message key="navegacao.compra" /></span>
	          </a>
	        </li>
           <li class="${cadastroCliente}">
	          <a href="/cliente/cadastro">
	            <i class="fa   fa-circle-o"></i> <span><fmt:message key="navegacao.cliente" /></span>
	          </a>
	        </li>
           <li class="${cadastroDespesaAPagar}">
	          <a href="/despesa/cadastro">
	            <i class="fa   fa-circle-o"></i> <span><fmt:message key="navegacao.despesa" /></span>
	          </a>
	        </li>
	        <li class="${cadastroFornecedor}">
	          <a href="/fornecedor/cadastro">
	            <i class="fa   fa-circle-o"></i> <span><fmt:message key="navegacao.fornecedor" /></span>
	          </a>
	        </li>
	         <li class="${cadastroGrupo}">
	          <a href="/grupo/cadastro">
	            <i class="fa   fa-circle-o"></i> <span><fmt:message key="navegacao.grupo" /></span>
	          </a>
	        </li>
	        <li class="${cadastroProduto}">
	          <a href="/produto/cadastro">
	            <i class="fa   fa-circle-o"></i> <span><fmt:message key="navegacao.produto" /></span>
	          </a>
	        </li>
	        <li class="${cadastroUser}">
	          <a href="/usuario/cadastro">
	            <i class="fa   fa-circle-o"></i> <span><fmt:message key="navegacao.user" /></span>
	          </a>
	        </li>
          </ul>
        </li>
        
        
<!--         arvore listas -->
         <li class="treeview ${listaGrupo }  ${listaCliente} 
         ${listaFornecedor}  ${listaProduto}">
          <a href="#">
            <i class="fa  fa-table"></i> <span><fmt:message key="navegacao.lista" /></span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
	        <li class="${listaCliente}">
	          <a href="/cliente">
	            <i class="fa   fa-circle-o"></i> <span><fmt:message key="navegacao.cliente" /></span>
	          </a>
	        </li>
	         <li class="${listaFornecedor}">
	          <a href="/fornecedor">
	            <i class="fa   fa-circle-o"></i> <span><fmt:message key="navegacao.fornecedor" /></span>
	          </a>
	        </li>
	        <li class="${listaGrupo}">
	          <a href="/grupo">
	            <i class="fa   fa-circle-o"></i> <span><fmt:message key="navegacao.grupo" /></span>
	          </a>
	        </li>
	        
	        <li class="${listaProduto}">
	          <a href="/produto">
	            <i class="fa   fa-circle-o"></i> <span><fmt:message key="navegacao.produto" /></span>
	          </a>
	        </li>
          </ul>
        </li>
        
        
        
<!--         arvore relatorio -->
 
        
       
      
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>