<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>	
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@attribute name="home" required="false" %>
<%@attribute name="cadastroGrupo" required="false" %>
<%-- <%@attribute name="cadastroCarteira" required="false" %> --%>
<%@attribute name="cadastroCliente" required="false" %>
<%@attribute name="cadastroFornecedor" required="false" %>
<%@attribute name="cadastroProduto" required="false" %>
<%@attribute name="cadastroUser" required="false" %>
<%@attribute name="cadastroDespesaAPagar" required="false" %>
<%@attribute name="cadastroCompra" required="false" %>
<%@attribute name="cadastroVenda" required="false" %>


<%@attribute name="atendimentoProcurarProduto" required="false" %>
<%@attribute name="atendimentoCarrinho" required="false" %>


<%@attribute name="listaGrupo" required="false" %>
<%-- <%@attribute name="listaCarteira" required="false" %> --%>
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
<!--       search form -->
<!--       <form action="#" method="get" class="sidebar-form"> -->
<!--         <div class="input-group"> -->
<!--           <input type="text" name="q" class="form-control" placeholder="Search..."> -->
<!--           <span class="input-group-btn"> -->
<!--                 <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i> -->
<!--                 </button> -->
<!--               </span> -->
<!--         </div> -->
<!--       </form> -->
<!--       /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header"> <fmt:message key="navegacao" /></li>


<!-- 		link home -->
		<li class="${home}">
          <a href="/">
            <i class="fa  fa-home"></i> <span><fmt:message key="navegacao.home" /></span>
          </a>
        </li>
        
        
<!--         arvore de atendimento -->
        <li class="treeview ${atendimentoCarrinho} ${atendimentoOrcamento} ${atendimentoProcurarProduto}">
          <a href="#">
            <i class="fa fa-dollar"></i> <span><fmt:message key="navegacao.atendimento" /></span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
           <li class="${atendimentoCarrinho}">
	          <a href="/carrinho">
	            <i class="fa   fa-circle-o"></i> <span><fmt:message key="navegacao.venda" /></span>
	          </a>
	        </li>
	        <li class="${atendimentoProcurarProduto}">
	          <a href="/produto/procurarProduto">
	            <i class="fa   fa-circle-o"></i> <span><fmt:message key="navegacao.procurarProduto" /></span>
	          </a>
	        </li>
<%-- 	        <li class="${atendimentoOrcamento}"> --%>
<!-- 	          <a href="/carteira/cadastro"> -->
<%-- 	            <i class="fa   fa-circle-o"></i> <span><fmt:message key="navegacao.orcamento" /></span> --%>
<!-- 	          </a> -->
<!-- 	        </li> -->
          </ul>
        </li>
        
        
<!--         arvore de cadastro -->
        <li class="treeview ${cadastroGrupo } ${cadastroCarteira} ${cadastroCliente} 
        ${cadastroFornecedor} ${cadastroProduto} ${cadastroUser} ${cadastroDespesaAPagar}  
        ${cadastroCompra} ${cadastroVenda}">
          <a href="#">
            <i class="fa fa-plus-square"></i> <span><fmt:message key="navegacao.cadastro" /></span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="${cadastroCarteira}">
	          <a href="/carteira/cadastro">
	            <i class="fa   fa-circle-o"></i> <span><fmt:message key="navegacao.carteira" /></span>
	          </a>
	        </li>
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
         <li class="treeview ${listaGrupo } ${listaCarteira} ${listaCliente} 
         ${listaFornecedor}  ${listaProduto}">
          <a href="#">
            <i class="fa  fa-table"></i> <span><fmt:message key="navegacao.lista" /></span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
          <li class="${listaCarteira}">
	          <a href="/carteira">
	            <i class="fa   fa-circle-o"></i> <span><fmt:message key="navegacao.carteira" /></span>
	          </a>
	        </li>
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
         <li class="treeview ">
          <a href="#">
            <i class="fa  ion-clipboard"></i> <span><fmt:message key="navegacao.relatorio" /></span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
           <li class="">
	          <a href="#">
	            <i class="fa   fa-circle-o"></i> <span><fmt:message key="relatorio.vendas" /></span>
	          </a>
	        </li>
          </ul>
        </li>
        
       
      
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>