<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="customTags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message key="caixa.saida.title" var="title" />
<customTags:page title="${title}"  listaCaixa="active">
 <jsp:attribute name="extraScripts">
 
 
  <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/locale/pt-br.js"></script>
                 <link rel="stylesheet" href="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css" />
         
         
         
  <!-- toggle -->
  <link href="/resources/plugins/bootstrap-toggle/bootstrap-toggle.min.css" rel="stylesheet">
  <script src="/resources/plugins/bootstrap-toggle/bootstrap-toggle.min.js"></script>
	
<!--  TouchSPin -->
 <script src="/resources/plugins/touchspin/jquery.bootstrap-touchspin.js"></script>
 <link rel="stylesheet" href="/resources/plugins/touchspin/jquery.bootstrap-touchspin.css">

<!-- js de ajuda na pagina -->
<script src="/resources/extras/js/caixaEntradaSaida.js"></script>

 <!-- InputMask -->
  <script src="/resources/bower_components/maskmoney/jquery.maskMoney.js"></script>
 
 
  <!-- Date Picker -->
  <link rel="stylesheet" href="/resources/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
  <!-- Daterange picker -->
  <link rel="stylesheet" href="/resources/bower_components/bootstrap-daterangepicker/daterangepicker.css">
   			<!-- 		datepicker -->
<script src="/resources/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
  <script src="/resources/bower_components/bootstrap-datepicker/js/locales/bootstrap-datepicker.pt-BR.js"></script>



  
  <link rel="stylesheet" href="/resources/bower_components/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css">
<script src="/resources/bower_components/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script src="/resources/bower_components/moment/src/moment.js"></script>
               
                                        
 </jsp:attribute>
<jsp:body>
<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
           <fmt:message key="caixa.saida.h3" />
      </h1>
      <ol class="breadcrumb">
        <li><a href="/"><i class="fa fa-dashboard"></i><fmt:message key="navegacao.home" /></a></li>
        <li class="active"><a href="#"><fmt:message key="navegacao.financeiro" /></a></li>
        <li class="active"><a href="/caixa"><fmt:message key="navegacao.caixa" /></a></li>
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
<%--               <fmt:message key="caixa.cadastro.h3" /> --%>
<!-- 			  </h3> -->
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form:form action="${s:mvcUrl('CC#gravarSaida').build() }" method="post"
					modelAttribute="movimentoDeCaixa"  autocomplete="off">
            
	              <div class="box-body" >
	              
	              
<!-- 	                <div class="form-group col-lg-4 col-md-6 col-sm-12"> -->
	               	                
	                <div class="form-group col-lg-12">
                       <label><fmt:message key="caixa.movimento.dataEmissao" /></label>
                       <fmt:message key="caixa.movimento.dataHoraMovimentoPlaceHolder" var="dataHoraMovimentoPlaceHolder"/>
                       <form:input type="datetime" path="dataHoraMovimento" id="dataPicker" cssClass="form-control dataPicker"
                         placeholder="${data} ${dataHoraMovimentoPlaceHolder } " />
                        <form:errors path="dataHoraMovimento" class="text-danger" />
                     </div>
                     
                     <div class="form-group col-lg-12">
		                <label><fmt:message key="caixa.movimento.formadepagamento" /></label>
						<form:select  path="formaDePagamento"  class="form-control select2" >
						     <c:forEach items="${formaDePagamento}" var="p">  
					        		<option value="${p}">
					        			<fmt:message key="formaDePagamento.${p }"  />
					        		</option>  
					        </c:forEach>
						</form:select>
	               </div>
                     
	                <div class="form-group col-lg-12">
	                  <label ><fmt:message key="caixa.movimento.valor" /></label>
	                  <fmt:message key="caixa.movimento.valorSaidaPlaceHolder" var="valorPlaceHolder"/>
	                  <form:input  path="valor" cssClass="form-control money" placeholder="${valorPlaceHolder}" />
	                <form:errors path="valor" class="text-danger" />
	                </div>
	                
	               
						
	              </div>
	              <!-- /.box-body -->
              <div class="box-footer">
<!--                 <button type="submit" class="btn btn-default">Cancel</button> -->
                	<button type="submit" class="btn btn-info pull-right btn-lg btn-block">
						<fmt:message key="caixa.button.saida" />
					</button>
					<div class="clearfix"></div>
              </div>
               <input type="hidden" name="id" value="${movimentoDeCaixa.id}" />
              
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