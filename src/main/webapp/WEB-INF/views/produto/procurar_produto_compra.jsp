<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="customTags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message key="venda.lista.title" var="title" />
<customTags:page title="${title}" listaCompra="active" >
<jsp:attribute name="extraScripts">
	<!-- DataTables -->
	<script src="/resources/bower_components/datatables.net/js/jquery.dataTables.min.js" type="text/javascript"></script>
	<script src="/resources/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js" type="text/javascript"></script>
	<script src="/resources/extras/js/configDateTable.js"></script>

	
	<script src="/resources/plugins/validate/validator.js" type="text/javascript"></script>

</jsp:attribute>

<jsp:body>
<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
           <fmt:message key="produto.procurar.h1" />
      </h1>
       <ol class="breadcrumb">
        <li><a href="/"><i class="fa fa-dashboard"></i>
					<fmt:message key="navegacao.home" /></a></li>
        <li class="active"><a href="#"><fmt:message
							key="navegacao.estoque" /></a></li>
        <li class="active"><a href="#"><fmt:message
							key="navegacao.cadastro" /></a></li>
        <li class="active"><a href="/compra"><fmt:message
							key="navegacao.compra" /></a></li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <!-- Small boxes (Stat box) -->
      	
    <div class="row">
     
      <div class="col-xs-12">
      
          <div class="box box-primary">
           <div class="box-header with-border">
    <h3 class="box-title">


			</h3>            </div>
            <div class="box-header">
              <h3 class="box-title"></h3>
            </div>
            
            <!-- /.box-header -->
            <div class="box-body">
            
             <div class="table-responsive">
             
    <form:form action="${s:mvcUrl('CCC#procurarProduto').build() }" id="form_search"  method="post"
					modelAttribute="search" autocomplete="off"
					data-toggle="validator">
				    <div class="col-xs-6">
						<div class="form-group">
						      <div class="input-group ">
							        <input type="text" name="search" id="search" class="form-control" 
							        placeholder="Digite o nome do Produto" id="search"
							        data-minlength="3"
							        data-error="
							        <fmt:message key="procurar.produto.mensagem.erro" />
							        "
							        
							        required/>
									        <div class="input-group-btn">
									          <button class="btn btn-primary" type="submit">
									            <span class="glyphicon glyphicon-search"></span>
									          </button>
									        </div>
								</div>
								
						        <div class="help-block with-errors"></div>
					      </div>
					   <c:if test="${not empty error}">	
					      
					     <div class="alert alert-warning alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <h5><i class="icon fa fa-ban"></i> <fmt:message key="procurar.produto.mensagem.naoEcontrado" /></h5>
              </div>
				            </c:if>
				      
				    </div>
				  
    			<div class="box-body">
              <dl>
              </dl>
            </div>
           
           
            <c:if test="${empty produtos}">	
            <div class="box-body">
              <strong><i class="fa fa-search margin-r-5"></i> Pesquise o produto a ser vendido</strong>

              <p >
              </p>

              <hr>

            </div>
            </c:if>
           </form:form>
             <form:form action="${s:mvcUrl('CC#adicionarProdutoNoCarrinho').build() }" method="post"
					modelAttribute="search" autocomplete="off">  
    <c:if test="${not empty produtos}">	
    
    	
              <table id="example2" class="table table-responsive">
                <thead>
	                <tr >
	                  <th><fmt:message key="venda.lista.nome" /></th>
	                  <th style="width: 150px"><fmt:message key="venda.lista.valorVenda" /></th>
	                  <th ><fmt:message key="venda.lista.quantidade" /></th>
	                  <th style="width: 100px"></th>
	                   
	                </tr>
                </thead>
                <tbody>
                <c:forEach items="${produtos}" var="p">
                <tr>
                  <td width="60%">
                  
                  <h4 class="nomargin"> ${p.nome}</h4>
				<p class="hidden-xs">${p.descricao }</p>
                  
                   </td>
                  <td width="20%">${p.valorVenda }  </td>
                  <td width="15%">${p.quantidade }  </td>
                  <td class="td-actions text-middle" width="5%">
						<a  rel="tooltip" class="btn btn-success" id="edit_event"   href="<s:url value='/compra/adicionaNoCarrinho/${p.id}' />">
							 <i	class="fa   fa-cart-plus"></i> 
						</a>
					</td>
                </tr>
                </c:forEach>
                
                </tbody>
                <tfoot>
                <tr>
                 
                  
                </tr>
                </tfoot>
              </table>
              </c:if>
     </form:form>          
              
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