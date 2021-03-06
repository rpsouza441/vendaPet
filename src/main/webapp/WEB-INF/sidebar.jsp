<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>	
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@attribute name="home" required="false" %>
<%@attribute name="cadastroGrupo" required="false" %>
<%@attribute name="cadastroCliente" required="false" %>
<%@attribute name="cadastroFornecedor" required="false" %>
<%@attribute name="cadastroProduto" required="false" %>
<%@attribute name="cadastroUser" required="false" %>
<%@attribute name="cadastroDespesa" required="false" %>
<%@attribute name="cadastroCompra" required="false" %>
<%@attribute name="cadastroVenda" required="false" %>
<%@attribute name="cadastroContaReceber" required="false" %>

<%@attribute name="financeiroContaPaga" required="false" %>
<%@attribute name="financeiroContaRecebida" required="false" %>
<%@attribute name="fluxoCaixa" required="false" %>

<%@attribute name="atendimentoCarrinho" required="false" %>

<%@attribute name="relatorioCaixa" required="false" %>
<%@attribute name="relatorioFluxo" required="false" %>
<%@attribute name="relatorioReceber" required="false" %>
<%@attribute name="relatorioRecebidas" required="false" %>
<%@attribute name="relatorioPagar" required="false" %>
<%@attribute name="relatorioPagas" required="false" %>

<%@attribute name="listaGrupo" required="false" %>
<%@attribute name="listaProduto" required="false" %>
<%@attribute name="listaVenda" required="false" %>
<%@attribute name="listaCaixa" required="false" %>
<%@attribute name="listaCompra" required="false" %>
<%@attribute name="listaUser" required="false" %>



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
        <li class="treeview ${atendimentoCarrinho} ${cadastroCliente}">

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
	          <a href="/venda">
	            <i class="fa fa-fw fa-shopping-cart"></i> <span>
	            <fmt:message key="navegacao.venda" /></span>
	          </a>
	        </li>
<%-- 	        <li class="${atendimentoOrcamento}"> --%>
<!-- 	          <a href="/produto/orcamento"> -->
<%-- 	            <i class="fa fa-fw fa-th-list"></i> <span><fmt:message key="navegacao.orcamento" /></span> --%>
<!-- 	          </a> -->
<!-- 	        </li> -->
                
                
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
			          <a href="/cliente">
			            <i class="fa fa-fw fa-user"></i> <span>
			            <fmt:message key="navegacao.cliente" /></span>
			          </a>
	           </li>
                
                
              </ul>
            </li>
            
            
            
           
          </ul>
        </li>
        
        <!-- Arvore de estoque -->
        <li class="treeview ${cadastroGrupo } 
        ${listaProduto}
        ${cadastroCompra}
        ${cadastroFornecedor}
        ${cadastroProduto }
        ${cadastroGrupo }
        ${listaCompra}
        ">
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
                <li class="${listaProduto}">
	                <a href="/produto">
		                <i class="fa fa-fw fa-search"></i>
		                <fmt:message key="navegacao.consulta" />
	                </a>
                </li>
                
                <li class="${cadastroCompra}">
	                <a href="/compra/entradaNF">
		                <i class="fa fa-fw fa-arrow-down"></i>
		                <fmt:message key="navegacao.entradaNF" />
	                </a>
                </li>
                
<!--                 <li> -->
<!--                 	<a href="#"> -->
<!-- 		                <i class="fa fa-fw fa-check"></i> -->
<%-- 		                <fmt:message key="navegacao.auditoria" /> --%>
<!-- 	                </a> -->
<!--                 </li> -->
                
                
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
			          <a href="/fornecedor">
			            <i class="fa fa-fw fa-user-circle"></i> <span>
			            <fmt:message key="navegacao.fornecedor" /></span>
			          </a>
	          </li>
               <li class=" ${listaCompra}">
			          <a href="/compra">
			            <i class="fa fa-fw fa-shopping-bag"></i> <span>
			            <fmt:message key="navegacao.compras" /></span>
			          </a>
	          </li>
	          
	        <li class="${cadastroProduto}">
	          <a href="/produto/cadastro">
	            <i class="fa fa-fw fa-cube"></i> <span>
	            <fmt:message key="navegacao.produto" /></span>
	          </a>
	        </li>
	        
	        <li class="${cadastroGrupo}">
	          <a href="/grupo">
	            <i class="fa fa-fw fa-qrcode"></i> <span>
	            <fmt:message key="navegacao.grupo" /></span>
	          </a>
	        </li>
                
                
              </ul>
            </li>
          </ul>
        </li>
        
        
        
        
        <!-- arvore financeiro  -->
        <li class="treeview 
        ${listaCaixa }
        ${cadastroDespesa}
        ${cadastroContaReceber}
        ${financeiroContaPaga }
        ${financeiroContaRecebida }
        ${fluxoCaixa }
        ">

          <a href="#">
            <i class="fa fa-fw  fa-line-chart"></i> <span>
            <fmt:message key="navegacao.financeiro" /></span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
          <li class="${listaCaixa}">
	          <a href="/caixa">
	            <i class="fa fa-fw fa-inbox"></i> <span>
	            <fmt:message key="navegacao.gaveta" /></span>
	          </a>
	        </li>
          
          <li >
              <a href="#"> 
              	<h4><fmt:message key="navegacao.controle" /></h4>
              </a>
              <ul class="treeview-menu" style="display: block;">
                <li class="${financeiroContaRecebida}">
	          <a href="/conta/receber">
	            <i class="fa fa-fw fa-sign-in"></i> <span>
	            <fmt:message key="navegacao.contaAReceber" /></span>
	          </a>
	        </li>
	        <li class="${financeiroContaPaga}">
	          <a href="/conta/pagar">
	            <i class="fa fa-fw fa-sign-out"></i> <span>
	            <fmt:message key="navegacao.contaAPagar" /></span>
	          </a>
	        </li>
	        <li class="${fluxoCaixa}">
	          <a href="/sistema/fluxoCaixa">
	            <i class="fa fa-fw fa-bar-chart"></i> <span>
	            <fmt:message key="navegacao.fluxoCaixa" /></span>
	          </a>
	        </li>
                
                
              </ul>
            </li>
            
           
          </ul>
        </li>
        
        
        
        
        <!-- arvore Relatorio  -->
         <li class="treeview 
         ${relatorioCaixa }
         ${relatorioFluxo }
         ${relatorioRecebidas }
         ${relatorioReceber }
         ${relatorioPagar }
         ${relatorioPagas }
         ">
          <a href="#">
            <i class="fa fa-fw fa-bar-chart-o"></i> <span>
            <fmt:message key="navegacao.relatorio" /></span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
           <li class="${relatorioCaixa}">
			   <a href="/relatorio/caixa">
			        <i class="fa fa-fw fa-inbox"></i> <span>
			        <fmt:message key="navegacao.relatorio.gaveta" /></span>
			   </a>
	          </li>
	          
           <li class="${relatorioFluxo}">
			   <a href="/relatorio/fluxoCaixa">
			        <i class="fa fa-fw fa-bar-chart"></i> <span>
			        <fmt:message key="navegacao.relatorio.fluxo" /></span>
			   </a>
	        </li>
	          
           <li class="${relatorioReceber}">
			   <a href="/relatorio/contasAReceber">
			        <i class="fa fa-fw fa-sign-in"></i> <span>
			        <fmt:message key="navegacao.relatorio.contaReceber" /></span>
			   </a>
	       </li>
	          
           <li class="${relatorioRecebidas}">
			   <a href="/relatorio/contasRecebidas">
			        <i class="fa fa-fw fa-sign-in"></i> <span>
			        <fmt:message key="navegacao.relatorio.contaRecebidas" /></span>
			   </a>
	       </li>
	          
           <li class="${relatorioPagar}">
			   <a href="/relatorio/contasAPagar">
			        <i class="fa fa-fw fa-sign-out"></i> <span>
			        <fmt:message key="navegacao.relatorio.contaPagar" /></span>
			   </a>
	       </li>
	          
           <li class="${relatorioPagas}">
			   <a href="/relatorio/contasPagas">
			        <i class="fa fa-fw fa-sign-out"></i> <span>
			        <fmt:message key="navegacao.relatorio.contaQuitadas" /></span>
			   </a>
	       </li>
	          
          </ul>
        </li>
        
        
          
  <li class="${listaUser}"><a href="/usuario"><i class="fa fa-fw fa-user "></i> <span><fmt:message key="navegacao.user" /></span></a></li>
        
  <li><a href="/logout"><i class=" fa fa-fw fa-power-off "></i> <span><fmt:message key="navegacao.logout" /></span></a></li>
        
 
        
       
      
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>