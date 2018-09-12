<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="customTags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<fmt:message key="produto.lista.title" var="title" />
<customTags:page title="${title}" listaProduto="active">
	<jsp:attribute name="extraScripts">
	<!-- DataTables -->
	<script
			src="/resources/bower_components/datatables.net/js/jquery.dataTables.min.js"
			type="text/javascript"></script>
	<script
			src="/resources/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"
			type="text/javascript"></script>

	<script src="/resources/extras/js/configDateTable.js"></script>

	<script src="/resources/plugins/validate/validator.js"
			type="text/javascript"></script>


</jsp:attribute>

	<jsp:body>
<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
           <fmt:message key="produto.lista.h1" />
      </h1>
       <ol class="breadcrumb">
        <li><a href="/"><i class="fa fa-dashboard"></i>
					<fmt:message key="navegacao.home" /></a></li>
        <li class="active"><a href="#"><fmt:message
							key="navegacao.estoque" /></a></li>
        <li class="active"><a href="#"><fmt:message
							key="navegacao.controle" /></a></li>
        <li class="active"><a href="/produto"><fmt:message
							key="navegacao.produto" /></a></li>
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
            <form:form
								action="${s:mvcUrl('PC#listaComSearch').build() }"
								id="form_search" method="post" modelAttribute="search"
								autocomplete="off" data-toggle="validator">
								
					<div class="col-xs-6">
						<div class="form-group">
						
						<a rel="tooltip" class="btn btn-success btn-block" id="edit_event"
											href="<s:url value='/produto/cadastro' />">
							 <span class="glyphicon glyphicon-plus pull-left"></span> 
							   <fmt:message key="produto.adicionar" />
						</a>
						
						
					      </div>
				      
				    </div>
				    <div class="col-xs-6">
						<div class="form-group">
						      <div class="input-group ">
							        <input type="text" name="search" id="search"
												class="form-control" placeholder="Digite o nome do Produto"
												id="search" data-minlength="3"
												data-error="
							        <fmt:message key="procurar.mensagem.erro" />
							        " />
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
                <button type="button" class="close" data-dismiss="alert"
												aria-hidden="true">&times;</button>
                <h5>
												<i class="icon fa fa-ban"></i> <fmt:message
													key="procurar.produto.mensagem.naoEcontrado" />
											</h5>
              </div>
				            </c:if>
				      
				    </div>
				  
    			<div class="box-body">
              <dl>
              </dl>
            </div>
           
           
           </form:form>
             <div class="table-responsive">
              <table id="example2"
									class="table table-bordered table-hover">
                <thead>
	                <tr>
	                  <th><fmt:message key="produto.lista.foto" /></th>
	                  <th><fmt:message key="produto.lista.nome" /></th>
	                  <th><fmt:message key="produto.lista.codBarra" /></th>
	                  <th><fmt:message key="produto.lista.fabricante" /></th>
	                  <th><fmt:message key="produto.lista.custo" /></th>
	                  <th><fmt:message key="produto.lista.lucro" /></th>
	                  <th><fmt:message key="produto.lista.venda" /></th>
	                  <th><fmt:message key="produto.lista.observacoes" /></th>
	                   <th><fmt:message key="lista.editarExcluir" /></th>
<!-- 	                    -->
	                   
	                </tr>
                </thead>
                <tbody>
                <c:forEach items="${produtos}" var="produto">
                <tr>
                  <td>
 				<img class="img-thumbnail" 
 				src="<c:url value='/arquivos/${produto.foto}'/>"
				alt="${produto.nome}" style="width: 75%" class="img-rounded img-responsive"/>                 

                  </td>
                  <td>${produto.nome } ${produto.descricao } </td>
                  <td>${produto.codBarras }  </td>
                  <td>${produto.fabricante }  </td>
                  <td>${produto.valorCusto }  </td>
                  <td>${produto.margemLucro }  </td>
                  <td>${produto.valorVenda } </td>
                  <td>${produto.observacoes } </td>                  
                  <td width="15%" class="td-actions text-middle">
						<a rel="tooltip" class="btn btn-success" id="edit_event"
													href="<s:url value='/produto/editarProduto/${produto.id}' />">
							 <i class="fa  fa-pencil"></i> 
						</a>
						<a rel="tooltip" class="btn btn-danger" id="edit_event"
													href="<s:url value='/produto/excluirProduto/${produto.id}' />">
							 <i class="fa  fa-trash-o"></i> 
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