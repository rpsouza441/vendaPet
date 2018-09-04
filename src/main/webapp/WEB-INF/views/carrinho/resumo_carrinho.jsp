<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="customTags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message key="carrinho.lista.title" var="title" />
<customTags:page title="${title}" atendimentoCarrinho="active" >
<jsp:attribute name="extraScripts">
<!--  TouchSPin -->
 <script src="/resources/plugins/touchspin/jquery.bootstrap-touchspin.js"></script>
 <link rel="stylesheet" href="/resources/plugins/touchspin/jquery.bootstrap-touchspin.css">
 <script>
            $("input[name='quantidade']").TouchSpin({
                min: 1,
                
            });
            
            $("input[name='quantidade']").on('keyup keypress blur change', function(e) {
            	    
            	    var value = $(this).val();
            	    var max_chars = parseInt($(this).attr('max'));
            	    var min_chars = parseInt($(this).attr('min'));
console.log(max_chars);
            	    if ((value !== '') && (value.indexOf('.') === -1)) {
            	        
            	        $(this).val(Math.max(Math.min(value, max_chars), min_chars));
            	    }
            });
  </script>

</jsp:attribute>

<jsp:body>
<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
           <fmt:message key="venda.lista.h1" />
      </h1>
       <ol class="breadcrumb">
        <li><a href="/"><i class="fa fa-dashboard"></i><fmt:message key="navegacao.home" /></a></li>
        <li class="active"><a href="/carrinho"><fmt:message key="navegacao.venda" /></a></li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <!-- Small boxes (Stat box) -->
      	
    <div class="row">
     
      <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title"></h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
             <div class="table-responsive">
             
             
             
             
 <c:if test="${carrinho.total >0}">	
		<form:form action="${s:mvcUrl('CC#finalizar').build()}" method="post"
		modelAttribute="itensCarrinho" >
<div class="col-xs-12">
 
		<table class="table table-condensed">
			<thead >
				<tr class="bg-warning">
					<th width="40%"><fmt:message key="carrinho.td.item" /></th>
					<th width="15%"><fmt:message key="carrinho.td.preco" /></th>
					<th width="10%"><fmt:message key="carrinho.td.emEstoque" /></th>
					<th width="20%"><fmt:message key="carrinho.td.quantidade" /></th>
					<th width="15%"><fmt:message key="carrinho.td.total" /></th>
					<th width="5%"></th>
				</tr>
			</thead>
			<c:forEach items="${carrinho.itens }" var="item" varStatus="cont">
				<tr>
					<td >${item.produto.nome}</td>
					<td >${item.produto.valorVenda}</td>
					<td >${item.produto.quantidade}</td>
					<td >
					   <input  min="0"
						 id="quantidade" name="quantidade" cancelable="true" max="${item.produto.quantidade }"
						value="${carrinho.getQuantidade(item) }" />
					</td>
					
						
					<td >${carrinho.getTotal(item)}</td>
					<td >
							<a  rel="tooltip" class="btn btn-danger"
							 id="edit_event"   href="<s:url value='/carrinho/removerDoCarrinho/${item.produto.id}' />">
							 <i	class="fa  fa-trash-o"></i> 
						</a>
					</td>
				</tr>
				
			</c:forEach>
			
			
			
			<tfoot>
				<tr >
					<td colspan="3">
					</td>
						
						<td colspan="2">
				   <button class="btn btn-app " type="submit" name="atualizar">
				    <i class="fa fa-refresh"></i>
				    	<fmt:message key="carrinho.td.atualizar" />
				    	 </button> 
						</td>

				</tr>
			</tfoot>
		</table>
		</div>
		<div class="col-xs-6">
		</div>
		<div class="col-xs-6">
		 <h2 id="cart-title"><fmt:message key="navegacao.orcamento" /></h2>
		<table class="table  table-hover">
			<tr>
			<th width="30%"><fmt:message key="carrinho.td.total" /></th> 
			<th width="70%">${carrinho.total }</th>
			</tr>
			<tfoot>
				<tr >
					<td colspan="1">
					
					</td>
						
						<td colspan="1">
						
				  <button class="btn btn-primary" type="submit" name="orcamento">
				    <i class="fa  fa-print"></i>
				    	<fmt:message key="carrinho.orcamento" />
				    	 </button>  

				</tr>
				<tr >
					<td colspan="1">
					</td>
						
						<td colspan="1">
						
				   <button class="btn btn-primary btn-lg pull-left" type="submit">
				    <i class="glyphicon glyphicon-ok"></i>
				    	<fmt:message key="carrinho.adicionarProduto" />
				    	 </button> 
				    	 
				    	 

				</tr>
			</tfoot>
			</table>
			</div>
		
						</form:form>
		</c:if>
		</div>
		
              <c:if test="${carrinho.total < 1}">	
			 	<div class="alert bg-gray text-center col-xs-4" >
				    	<fmt:message key="carrinho.alert.vazio " />
				</div>
			 </c:if>
             <div class="col-xs-12">
			 			
			 			<a  rel="tooltip" class="btn btn-default btn-lg pull-left"
							 id="edit_event"   href="<s:url value='produto/procurarProduto' />">
							 <i	class="fa fa-angle-left"></i> 
				    	<fmt:message key="carrinho.adicionarProduto" />

						</a>
						<a  rel="tooltip" class="btn btn-default btn-lg pull-right "
							 id="edit_event"   href="<s:url value='/carrinho/limparCarrinho/' />">
						<fmt:message key="carrinho.zerar" />
							 <i	class="fa  fa-trash-o"></i> 
						</a>
			 			
			 	</div>
             
             
              </div>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->

        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
      

    </section>
    <!-- /.content -->
    </jsp:body>


</customTags:page>