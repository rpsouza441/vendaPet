<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="customTags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message key="carrinho.finalizar.title" var="title" />
<customTags:page title="${title}"  >
 <jsp:attribute name="extraScripts">
 
  <!-- toggle -->
  <link href="/resources/plugins/bootstrap-toggle/bootstrap-toggle.min.css" rel="stylesheet">
  <script src="/resources/plugins/bootstrap-toggle/bootstrap-toggle.min.js"></script>
	
<!--  TouchSPin -->
 <script src="/resources/plugins/touchspin/jquery.bootstrap-touchspin.js"></script>
 <link rel="stylesheet" href="/resources/plugins/touchspin/jquery.bootstrap-touchspin.css">

	<!-- js de ajuda na pagina -->
	<script src="/resources/extras/js/finalizarVenda.js"></script>

 <!-- InputMask -->
  <script src="/resources/bower_components/maskmoney/jquery.maskMoney.js"></script>
 
 
  <!-- Date Picker -->
  <link rel="stylesheet" href="/resources/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
  <!-- Daterange picker -->
  <link rel="stylesheet" href="/resources/bower_components/bootstrap-daterangepicker/daterangepicker.css">
   			<!-- 		datepicker -->
<script src="/resources/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
  <script src="/resources/bower_components/bootstrap-datepicker/js/locales/bootstrap-datepicker.pt-BR.js"></script>
                                        
 </jsp:attribute>
<jsp:body>
<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
           <fmt:message key="carrinho.finalizando.h3" />
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
     
        <!-- right column -->
        <div class="col-md-8">
          <!-- Horizontal Form -->
          <div class="box box-primary">
            <div class="box-header with-border">
<!--               <h3 class="box-title"> -->
<%--               <fmt:message key="venda.cadastro.h3" /> --%>
<!-- 			  </h3> -->
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form:form action="${s:mvcUrl('CC#finalizarVenda').build() }" method="post"
					modelAttribute="venda"  autocomplete="off">
            
	              <div class="box-body" >
	              
	              
<!-- 	                <div class="form-group col-lg-4 col-md-6 col-sm-12"> -->
	               	                
	                <div class="form-group col-lg-12">
                       <label><fmt:message key="venda.cadastro.dataEmissao" /></label>
                       <form:input type="text" path="dataEmissao" cssClass="form-control dataPicker"  placeholder="${data} DD/MM/AAAA" />
                        <form:errors path="dataEmissao" class="text-danger" />
                     </div>
                     
	                <div class="form-group col-lg-12">
	                  <label ><fmt:message key="venda.cadastro.valorVenda" /></label>
	                  <fmt:message key="venda.cadastro.valorVendaPlaceHolder" var="valorVendaPlaceHolder"/>
	                  <form:input  path="valorVenda" cssClass="form-control money" readonly="true" placeholder="${valorVendaPlaceHolder}" />
	                <form:errors path="valorVenda" class="text-danger" />
	                </div>
	               
	               
	                <div class="form-group col-lg-12">
	                  <label ><fmt:message key="venda.cadastro.valorDesconto" /></label>
	                  <fmt:message key="venda.cadastro.valorDescontoPlaceHolder" var="valorDescontoPlaceHolder"/>
	                  <form:input type="number"  path="valorDesconto"  cssClass="form-control money" style="-webkit-outer-spin-button { 
							    -webkit-appearance: none;
							    -moz-appearance: none;
							    appearance: none;
							    margin: 0; 
							}"  placeholder="${valorDescontoPlaceHolder}" />
	                <form:errors path="valorDesconto" class="text-danger" />
	                </div>
	                
	                
	                 <div class="form-group col-lg-12">
	                  <label ><fmt:message key="venda.cadastro.valorFinal" /></label>
	                  <fmt:message key="venda.cadastro.valorFinalPlaceHolder" var="valorFinalPlaceHolder"/>
	                  <form:input  path="valorFinal" cssClass="form-control money"  placeholder="${valorFinalPlaceHolder}" />
	                <form:errors path="valorFinal" class="text-danger" />
	                </div>
	               
<!-- 	                 <div class="form-group col-lg-12"> -->
<%-- 		                <label><fmt:message key="venda.cadastro.carteira" /></label> --%>
<%-- 						<form:select  path="carteira.id"  class="form-control select2" > --%>
<!-- 						    <option selected="selected">Alabama</option> -->
<%-- 						    <form:options items="${carteiras}" itemLabel="nome"  itemValue="id" /> --%>
<%-- 						</form:select> --%>
<%-- 						<form:errors path="carteira" class="text-danger" /> --%>
						
<!-- 	               </div> -->
	               
<!-- 	                 <div class="form-group col-lg-6"> -->
<!-- 		                 <div id="console-event">    -->
<!-- 			                  <label > -->
<%-- 			                	 <fmt:message key="produto.cadastro.estaAtivo" /> --%>
<!-- 			                 </label> -->
<!-- 		                 </div> -->
<!-- 	                  <label > -->
	                  
	                  
<%-- 	                  <form:checkbox data-toggle="toggle"  --%>
<%-- 	                 	 	 data-on="Ativo" data-off="Desativado" --%>
<%-- 	                  		 data-onstyle="success" id="toggle-parcelamento" --%>
<%-- 							 data-offstyle="danger" path="estaPago"   --%>
<%-- 							 class="btn btn-default" /> --%>
	                  
<!-- 	               	                     </label> -->
	               
	                  
<%-- 	                <form:errors class="text-danger" path="estaPago" /> --%>
<!-- 	                </div> -->
	               
	               
	               
	               
	               
	               
		                 <div class="form-group col-lg-12">
		                 <div class="row">
		                 <div class="col-lg-8">
							<div class="col-lg-12">
			                  <label ><fmt:message key="venda.cadastro.valorParcelado" /></label>
			                  <fmt:message key="venda.cadastro.valorFinalPlaceHolder" var="valorFinalPlaceHolder"/>
			                  <form:input  path="parcela" cssClass="form-control number"  placeholder="${quantidadeParcelasPlaceHolder}" />
							</div>
							<div class="col-lg-12">
							
							<p class="text-muted well well-sm no-shadow" style="margin-top: 10px;">
							<fmt:message key="venda.cadastro.totalde" /><label id="valorParcelamento" style=" font-weight: normal ">
					            
							</label>
          					</p>
							</div>
							</div>
		                 </div>

		                </div>
                     
						
	              </div>
	              <!-- /.box-body -->
              <div class="box-footer">
<!--                 <button type="submit" class="btn btn-default">Cancel</button> -->
                	<button type="submit" class="btn btn-info pull-right btn-lg btn-block">
						<fmt:message key="carrinho.fechar" />
					</button>
					<div class="clearfix"></div>
              </div>
               <input type="hidden" name="id" value="${venda.id}" />
              
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