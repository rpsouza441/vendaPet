<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="customTags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message key="despesa.cadastro.title" var="title" />
<customTags:page title="${title}"  cadastroDespesa="active">
	<jsp:attribute name="extraScripts">
<!-- Focus no input -->
<script src="/resources/extras/js/focusPrimeiroInput.js"></script>		
										
<!--  TouchSPin -->
 <script src="/resources/plugins/touchspin/jquery.bootstrap-touchspin.js"></script>
 <link rel="stylesheet" href="/resources/plugins/touchspin/jquery.bootstrap-touchspin.css">
 
  <script>
            $("input[name='quantidadeParcelas']").TouchSpin({
                initval: 1,
                min: 1,
                max: 12,
                
            });
  </script>
 

 
 <!-- InputMask -->
  <script src="/resources/bower_components/maskmoney/jquery.maskMoney.js"></script>
 <script>
 
 $(function() {
	    $('.money').maskMoney({prefix:'R$ ', allowNegative: false, thousands:'.', decimal:',', affixesStay: false});
	  })
 </script>
 
 
                                         <script>
                                        
                                            $(document)
                                            
                                                .ready(
                                                    function() {
                                                        var date_input = $('.dataPicker'); //our date input has the name "dataPicker"
                                                        var container = $('.bootstrap-iso form').length > 0 ? $(
                                                                '.bootstrap-iso form').parent() :
                                                            "body";
                                                        var options = {
                                                            format: 'dd/mm/yyyy',
                                                            language: "pt-BR",
                                                            container: container,
                                                            todayHighlight: true,
                                                            autoclose: true,
                                                            orientation: 'bottom',
                                                            
                                                        };
                                                        date_input.datepicker(options);
                                                    })

                                        </script>
 
 
 
 
  <!-- Date Picker -->
  <link rel="stylesheet" href="/resources/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
  <!-- Daterange picker -->
  <link rel="stylesheet" href="/resources/bower_components/bootstrap-daterangepicker/daterangepicker.css">
  
<script src="/resources/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
  <script src="/resources/bower_components/bootstrap-datepicker/js/locales/bootstrap-datepicker.pt-BR.js"></script>
 			<!-- 		datepicker -->
 </jsp:attribute>
<jsp:body>
<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
           <fmt:message key="despesa.cadastro.h3" />
      </h1>
      <ol class="breadcrumb">
        <li><a href="/"><i class="fa fa-dashboard"></i><fmt:message key="navegacao.home" /></a></li>
        <li class="active"><a href="#"><fmt:message key="navegacao.financeiro" /></a></li>
        <li class="active"><a href="#"><fmt:message key="navegacao.controle" /></a></li>
        <li class="active"><a href="/despesa/cadastro"><fmt:message key="navegacao.despesa" /></a></li>
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
<%--               <fmt:message key="despesa.cadastro.h3" /> --%>
<!-- 			  </h3> -->
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form:form action="${s:mvcUrl('DAPC#gravarEditarDespesa').build() }" method="post"
					modelAttribute="despesaAPagar" autocomplete="off">
            
	              <div class="box-body">
	              
	              
<!-- 	                <div class="form-group col-lg-4 col-md-6 col-sm-12"> -->
	                <div class="form-group col-lg-12">
	                  <label ><fmt:message key="despesa.cadastro.nome" /></label>
	                  <fmt:message key="despesa.cadastro.nomePlaceHolder" var="nomePlaceHolder"/>
	                  <form:input path="nome" cssClass="form-control"  placeholder="${nomePlaceHolder}" />
	                <form:errors path="nome" class="text-danger" />
	                </div>
	                
	                
	                <div class="form-group col-lg-12">
                       <label><fmt:message key="despesa.cadastro.dataVencimento" /></label>
                       <form:input type="text" path="dataVencimento" cssClass="form-control dataPicker"  placeholder="${data} DD/MM/AAAA" />
                        <form:errors path="dataVencimento" class="text-danger" />
                     </div>
                     
	                <div class="form-group col-lg-12">
	                  <label ><fmt:message key="despesa.cadastro.valor" /></label>
	                  <fmt:message key="despesa.cadastro.valorPlaceHolder" var="valorPlaceHolder"/>
	                  <form:input id="currency"  path="valor" cssClass="form-control money"  placeholder="${valorPlaceHolder}" />
	                <form:errors path="valor" class="text-danger" />
	                </div>
                     
					
						
	              </div>
	              <!-- /.box-body -->
              <div class="box-footer">
<!--                 <button type="submit" class="btn btn-default">Cancel</button> -->
                	<button type="submit" class="btn btn-info pull-right btn-lg btn-block">
						<fmt:message key="cadastro.salvar" />
					</button>
					<div class="clearfix"></div>
              </div>
               <input type="hidden" name="id" value="${despesaAPagar.id}" />
              
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