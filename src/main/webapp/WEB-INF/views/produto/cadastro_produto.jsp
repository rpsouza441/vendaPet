<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="customTags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message key="produto.cadastro.title" var="title" />
<customTags:page title="${title}"  cadastroProduto="active">

<jsp:attribute name="extraScripts">
<!-- Focus no input -->
<script src="/resources/extras/js/focusPrimeiroInput.js"></script>

 <!-- toggle -->
  <link href="/resources/plugins/bootstrap-toggle/bootstrap-toggle.min.css" rel="stylesheet">
  <script src="/resources/plugins/bootstrap-toggle/bootstrap-toggle.min.js"></script>


<script src="/resources/bower_components/maskmoney/jquery.maskMoney.js"></script>




 <!-- InputMask -->
  <script src="/resources/plugins/jquerymask/jquery.mask.js"></script>
 
   <script src="/resources/extras/js/cadprod.js"></script>
 

 
 
 
</jsp:attribute>


<jsp:body>
<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
           <fmt:message key="produto.cadastro.h3" />
      </h1>
      <ol class="breadcrumb">
        <li><a href="/"><i class="fa fa-dashboard"></i><fmt:message key="navegacao.home" /></a></li>
        <li class="active"><a href="#"><fmt:message key="navegacao.estoque" /></a></li>
        <li class="active"><a href="#"><fmt:message key="navegacao.cadastro" /></a></li>
        <li class="active"><a href="/produto/cadastro"><fmt:message key="navegacao.produto" /></a></li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <!-- Small boxes (Stat box) -->
      	
    <div class="row">
     
        <!-- right column -->
        <div class="col-md-12">
          <!-- Horizontal Form -->
          <div class="box box-primary">
            <div class="box-header with-border">
<!--               <h3 class="box-title"> -->
<%--               <fmt:message key="produto.cadastro.h3" /> --%>
<!-- 			  </h3> -->
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form:form action="${s:mvcUrl('PC#cadastroProduto').build() }" method="post"
					modelAttribute="produto" enctype="multipart/form-data" autocomplete="off">
            
	              <div class="box-body">
	                <div class="form-group col-lg-4 col-md-6 col-sm-6">
	                  <label ><fmt:message key="produto.cadastro.nome" /></label>
	                  <fmt:message key="produto.cadastro.nomePlaceHolder" var="nomePlaceholder"/>
	                  <form:input path="nome" cssClass="form-control"  placeholder="${nomePlaceholder}" />
	                <form:errors path="nome" class="text-danger" />
	                </div>
	                
	                 <div class="form-group col-lg-4 col-md-6 col-sm-6">
	                  <label ><fmt:message key="produto.cadastro.descricao" /></label>
	                  <fmt:message key="produto.cadastro.descricaoPlaceHolder" var="descricaoPlaceholder"/>
	                  <form:input path="descricao" cssClass="form-control"  placeholder="${descricaoPlaceholder}" />
	                <form:errors class="text-danger" path="descricao" />
	                </div>
	                <div class="form-group col-lg-4 col-md-12 col-sm-12 is-empty is-fileinput">
								<div class="form-group">
				                  <label for="exampleInputFile">
										<fmt:message key="produto.cadastro.foto" />
								  </label>
				                  <input type="file" id="exampleInputFile" name="endereco">
				                  <p class="help-block"><fmt:message key="produto.cadastro.textoAjuda" /></p>
				                </div>
					</div>	
	               
	               <div class="form-group col-lg-3 col-md-6 col-sm-6">
	                  <label ><fmt:message key="produto.cadastro.codBarras" /></label>
	                  <fmt:message key="produto.cadastro.codBarrasPlaceHolder" var="codBarrasPlaceholder"/>
	                  <form:input path="codBarras" cssClass="form-control"  placeholder="${codBarrasPlaceholder}" />
	                <form:errors class="text-danger" path="codBarras" />
	                </div>
	               

	              
	               <div class="form-group col-lg-4 col-md-6 col-sm-6">
	                  <label ><fmt:message key="produto.cadastro.fabricante" /></label>
	                  <fmt:message key="produto.cadastro.fabricantePlaceHolder" var="fabricantePlaceholder"/>
	                  <form:input path="fabricante" cssClass="form-control"  placeholder="${fabricantePlaceholder}" />
	                <form:errors class="text-danger" path="fabricante" />
	                </div>
	                
	                
	                <div class="form-group col-lg-3 col-md-6 col-sm-6">
		                <label><fmt:message key="produto.cadastro.grupo" /></label>
						<form:select  path="grupo.id"  class="form-control select2" >
						    <!-- <option selected="selected">Alabama</option> -->
						    <form:options items="${grupos}" itemLabel="nome"  itemValue="id" />
						</form:select>
	               </div>
	               
	               <div class="form-group col-lg-2 col-md-6 col-sm-6">
		                 <div id="console-event">   
			                  <label >
			                	 <fmt:message key="produto.cadastro.estaAtivo" />
			                 </label>
		                 </div>
	                  <label >
	                  
	                  
	                  <form:checkbox data-toggle="toggle" 
	                 	 	 data-on="Ativo" data-off="Desativado"
	                  		 data-onstyle="success" id="toggle-demo"
							 data-offstyle="danger" path="estaAtivo"  class="btn btn-default" />
	                  
	               	                     </label>
	               
	                  
	                <form:errors class="text-danger" path="estaAtivo" />
	                </div>
	                
	               
	                
	                <div class="form-group col-lg-3 col-md-6 col-sm-6">
	                  <label ><fmt:message key="produto.cadastro.minEstoque" /></label>
	                  <fmt:message key="produto.cadastro.minEstoquePlaceHolder" var="minEstoquePlaceholder"/>
	                  <form:input path="minEstoque" cssClass="form-control"  placeholder="${minEstoquePlaceholder}" />
	                <form:errors class="text-danger" path="minEstoque" />
	                </div>
	                
	                <div class="form-group col-lg-3 col-md-6 col-sm-6">
	                  <label ><fmt:message key="produto.cadastro.maxEstoque" /></label>
	                  <fmt:message key="produto.cadastro.maxEstoquePlaceHolder" var="maxEstoquePlaceholder"/>
	                  <form:input path="maxEstoque" cssClass="form-control"  placeholder="${maxEstoquePlaceholder}" />
	                <form:errors class="text-danger" path="maxEstoque" />
	                </div>
	                 
	                <div class="form-group col-lg-3 col-md-6 col-sm-6">
	                  <label ><fmt:message key="produto.cadastro.quantidade" /></label>
	                  <fmt:message key="produto.cadastro.quantidadePlaceHolder" var="quantidadePlaceHolder"/>
	                  <form:input path="quantidade" cssClass="form-control"  placeholder="${quantidadePlaceHolder}" />
	                <form:errors class="text-danger" path="quantidade" />
	                </div>
	                
	                <div class="form-group col-lg-3 col-md-6 col-sm-6">
	                  <label ><fmt:message key="produto.cadastro.unidade" /></label>
	                  <fmt:message key="produto.cadastro.unidadePlaceHolder" var="unidadePlaceholder"/>
	                  <form:input path="unidade" cssClass="form-control"  placeholder="${unidadePlaceholder}" />
	                <form:errors class="text-danger" path="unidade" />
	                </div>
	                
	                 <div class="form-group col-lg-4 col-md-4 col-sm-4">
	                  <label ><fmt:message key="produto.cadastro.valorCusto" /></label>
	                  <fmt:message key="produto.cadastro.valorCustoPlaceHolder" var="valorCustoPlaceholder"/>
	                  <form:input path="valorCusto" cssClass="form-control money item valorDeCompra"  placeholder="${valorCustoPlaceholder}" />
	                <form:errors class="text-danger" path="valorCusto" />
	                </div>
	                
	                 <div class="form-group col-lg-4 col-md-4 col-sm-4">
	                  <label ><fmt:message key="produto.cadastro.margemLucro" /></label>
	                  <fmt:message key="produto.cadastro.margemLucroPlaceHolder" var="margemLucroPlaceholder"/>
	                  <form:input path="margemLucro" cssClass="form-control  percent item margemDeLucro"  placeholder="${margemLucroPlaceholder}" />
	               <form:errors class="text-danger" path="margemLucro" />
	                </div>
	                
	                 <div class="form-group col-lg-4 col-md-4 col-sm-4">
	                  <label ><fmt:message key="produto.cadastro.valorVenda" /></label>
	                  <fmt:message key="produto.cadastro.valorVendaPlaceHolder" var="valorVendaPlaceholder"/>
	                  <form:input path="valorVenda" cssClass="form-control money item valorDeVenda"  placeholder="${valorVendaPlaceholder}" />
	                 <form:errors class="text-danger" path="valorVenda" />
	                </div>
	                
	               
	                
	                <div class="form-group col-lg-12 col-sm-12">
	                  <label ><fmt:message key="produto.cadastro.observacoes" /></label>
	                  <fmt:message key="produto.cadastro.observacoesPlaceHolder" var="observacoesPlaceholder"/>
	                  <form:textarea path="observacoes" cssClass="form-control" placeholder="${observacoesPlaceholder}" id="observacoes" rows="3" />
	                 <form:errors class="text-danger" path="observacoes" />
	                </div>
	                
				
							
				
	              
	              					
							
							               
	          </div>
	              
	              <!-- /.box-body -->
              <div class="box-footer ">
<!--                 <button type="submit" class="btn btn-default">Cancel</button> -->
                	<button type="submit" class="btn btn-info pull-right btn-lg btn-block" >
						<fmt:message key="produto.cadastro.salvar" />
					</button>
					<div class="clearfix"></div>
              </div>
     <input type="hidden" name="id" value="${produto.id}" />
              
              <!-- /.box-footer -->
            </form:form>
          </div>
          <!-- /.box -->
         
        </div>
        <!--/.col (right) -->
      </div>
      <!-- /.row -->
      

    </section>
    <!-- /.content -->
    
    
    </jsp:body>

</customTags:page>