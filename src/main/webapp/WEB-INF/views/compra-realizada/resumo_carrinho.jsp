<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="customTags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message key="compra.title" var="title" />
<customTags:page title="${title}" listaCompra="active" >
<jsp:attribute name="extraScripts">
  
  <!-- datetimepicker -->
    <link rel="stylesheet" href="/resources/bower_components/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css">
<script src="/resources/bower_components/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script src="/resources/bower_components/moment/src/moment.js"></script>
 <script>

 </script>
.ready(
		function() {
			
			   $('.dataPicker').datetimepicker({locale: 'pt-br'});
		});
</jsp:attribute>

<jsp:body>
<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
           <fmt:message key="compra.listaProduto.h1" />
      </h1>
       <ol class="breadcrumb">
        <li><a href="/"><i class="fa fa-dashboard"></i><fmt:message key="navegacao.home" /></a></li>
        <li class="active"><a href=""><fmt:message key="navegacao.estoque" /></a></li>
        <li class="active"><a href=""><fmt:message key="navegacao.cadastro" /></a></li>
        <li class="active"><a href="/compra"><fmt:message key="navegacao.compra" /></a></li>
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
             
             
             
             
 <c:if test="${carrinhoCompra.total >0}">	
		<form:form action="${s:mvcUrl('CCC#finalizar').build()}" method="post"
		modelAttribute="itensCarrinho" >

<div class="col-xs-12">
 <div class="row">
		<table class="table table-condensed">
			<thead >
				<tr class="bg-warning">
					<th width="40%"><fmt:message key="carrinho.td.item" /></th>
					<th width="5%"></th>
				</tr>
			</thead>
			<c:forEach items="${carrinhoCompra.itens }" var="item" varStatus="cont">
				<tr>
					<td >${item.produto.nome}</td>
					<td >
							<a  rel="tooltip" class="btn btn-danger"
							 id="edit_event"   href="<s:url value='/compra/removerDoCarrinho/${item.produto.id}' />">
							 <i	class="fa  fa-trash-o"></i> 
						</a>
					</td>
				</tr>
				
			</c:forEach>
			

			
			
		</table>
</div>
</div>
		<div class="col-xs-3">
		</div>
		<div class="col-xs-6">
	
				   <button class="btn btn-block btn-primary btn-lg pull-left" type="submit">
				    <i class="glyphicon glyphicon-ok"></i>
				    	<fmt:message key="compras.button.adicionarACompra" />
				    	 </button> 			
	  </div>
	  <div class="col-xs-3">
</div>
		
	</form:form>
		</c:if>
		
              <c:if test="${carrinhoCompra.total < 1}">	
			 	<div class="alert bg-gray text-center col-xs-4" >
				    	<fmt:message key="carrinho.alert.vazio " />
				</div>
			 </c:if>
</div>
             <div class="col-xs-12">
			 			
			 			<a  rel="tooltip" class="btn btn-default btn-lg pull-left"
							 id="edit_event"   href="<s:url value='/compra/procurarProduto' />">
							 <i	class="fa fa-angle-left"></i> 
				    	<fmt:message key="carrinho.adicionarProduto" />

						</a>
						<a  rel="tooltip" class="btn btn-default btn-lg pull-right "
							 id="edit_event"   href="<s:url value='/compra/limparCarrinho/' />">
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