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
 
 
  <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/locale/pt-br.js"></script>
                 <link rel="stylesheet" href="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css" />
         
<script>
$(document).ready(function() {
	$('#input-search').autocomplete({
		source : '${pageContext.request.contextPath }/venda/search',
	      select: function( event, ui ) {
	    	  var keyword = ui.item.label;
	          console.log( "Selected: " + ui.item.value + " aka " + ui.item.id +" --- "+ keyword );
	          $.ajax({
	        	  type:'GET',
	        	  url:'${pageContext.request.contextPath }/venda/searchAjax?keyword=' + keyword,
	              success: function(result){
	            	  var clientes = JSON.parse(result)
	            	  console.log(clientes.id);
	            	  $("#idCliente").val(clientes.id);
	              }
	        	  
	          });
	      }
	});
});
</script>
         
         
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



  
  <link rel="stylesheet" href="/resources/bower_components/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css">
<script src="/resources/bower_components/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script src="/resources/bower_components/moment/src/moment.js"></script>
               
                                        
 </jsp:attribute>
<jsp:body>
<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
           <fmt:message key="carrinho.finalizando.h3" />
      </h1>
      <ol class="breadcrumb">
        <li><a href="/"><i class="fa fa-dashboard"></i><fmt:message key="navegacao.home" /></a></li>
        <li class="active"><a href="/venda"><fmt:message key="navegacao.venda" /></a></li>
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
                       <form:input type="datetime" path="dataEmissao" id="dataPicker" cssClass="form-control dateTimePicker"
                         placeholder="${dataEmissao} DD/MM/AAAA " />
                        <form:errors path="dataEmissao" class="text-danger" />
                     </div>
                     
                     
                     
	                <div class="form-group col-lg-12">
	                  <label ><fmt:message key="venda.cadastro.subtotal" /></label>
	                  <fmt:message key="venda.cadastro.subtotalPlaceHolder" var="subtotalPlaceHolder"/>
	                  <form:input  path="subtotal" cssClass="form-control money" readonly="true" placeholder="${subtotalPlaceHolder}" />
	                <form:errors path="subtotal" class="text-danger" />
	                </div>
	                <div class="form-group col-lg-12">
	                  <label ><fmt:message key="venda.cadastro.desconto" /></label>
	                  <fmt:message key="venda.cadastro.descontoPlaceHolder" var="descontoPlaceHolder"/>
	                  <form:input  path="desconto" cssClass="form-control money"  placeholder="${descontoPlaceHolder}" />
	                <form:errors path="desconto" class="text-danger" />
	                </div>
	                <div class="form-group col-lg-12">
	                  <label ><fmt:message key="venda.cadastro.total" /></label>
	                  <fmt:message key="venda.cadastro.totalPlaceHolder" var="totalPlaceHolder"/>
	                  <form:input  path="total" cssClass="form-control money"  placeholder="${totalPlaceHolder}" />
	                <form:errors path="total" class="text-danger" />
	                </div>
	               
	               
<!-- 	                <div class="form-group col-lg-12"> -->
<%-- 	                  <label ><fmt:message key="venda.cadastro.valorDesconto" /></label> --%>
<%-- 	                  <fmt:message key="venda.cadastro.valorDescontoPlaceHolder" var="valorDescontoPlaceHolder"/> --%>
<%-- 	                  <form:input type="number"  path="valorDesconto"  cssClass="form-control money" style="-webkit-outer-spin-button {  --%>
<%-- 							    -webkit-appearance: none; --%>
<%-- 							    -moz-appearance: none; --%>
<%-- 							    appearance: none; --%>
<%-- 							    margin: 0;  --%>
<%-- 							}"  placeholder="${valorDescontoPlaceHolder}" /> --%>
<%-- 	                <form:errors path="valorDesconto" class="text-danger" /> --%>
<!-- 	                </div> -->
	                
	                
<!-- 	                 <div class="form-group col-lg-12"> -->
<%-- 	                  <label ><fmt:message key="venda.cadastro.valorFinal" /></label> --%>
<%-- 	                  <fmt:message key="venda.cadastro.valorFinalPlaceHolder" var="valorFinalPlaceHolder"/> --%>
<%-- 	                  <form:input  path="valorFinal" cssClass="form-control money"  placeholder="${valorFinalPlaceHolder}" /> --%>
<%-- 	                <form:errors path="valorFinal" class="text-danger" /> --%>
<!-- 	                </div> -->
</div>
	                <!-- PARCELAS -->
	                <div class="box box-info">
	                       <div class="col-md-12">
	                
			            <div class="box-header with-border">
			            <h3 class="box-title"> 
			            	<fmt:message key="venda.fmt.parcela" />
			            	</h3>
					   </div>
			            </div>
			            <div class="row">
			              <div class="col-md-12">
			              
				            <div class=" form-group col-lg-4">
				               <label><fmt:message key="venda.label.cliente" /></label>
				               <fmt:message key="venda.search.placeHolder" var="placeHolder"/>
							   <fmt:message key="procurar.produto.mensagem.erro" var="erro" />
							   <form:input id="input-search" path="cliente.nomeCompleto" cssClass="form-control" 
							   placeholder="${placeHolder}" data-minlength="3" 
							  data-error="${erro }" />
							<form:errors path="cliente" class="text-danger" />
								
								
								
									
						<form:hidden id="idCliente" path="cliente.id" value="" />									
			               </div>
			               
				            <div class=" form-group col-lg-4">
		                         	      
								            <label><fmt:message key="venda.label.parcelas" /></label>
								            <form:input  path="parcelas" cssClass="form-control"  />
							</div>
							
				               <div class=" form-group col-lg-4">
				               <label></label>
								             <button class="btn btn-block " type="submit" name="gerarParcelas">
												    <i class="fa fa-gear"></i>
												    <fmt:message key="btn.gerar" />
									    	 </button> 
				              </div>
			              
						  </div>
						</div>
						
				<div class="row">
				  <div class="col-md-12">
						
	              <c:forEach items="${venda.contaAReceber }" var="conta" varStatus="cont">
		                <div class="div${cont.index} replace">
		                 <div class="form-group col-lg-4 ">
	                       <label><fmt:message key="venda.cadastro.dataVencimento" /></label>
	                       <form:input type="datetime" path="contaAReceber[${cont.index}].dataVencimento" 
	                       id="dataPicker" cssClass="form-control datePicker"
	                         placeholder="${conta.dataVencimento} DD/MM/AAAA " />
	                        <form:errors path="contaAReceber[${cont.index}].dataVencimento" class="text-danger" />
	                     </div>
	                
	                
		                <div class="form-group col-lg-4">
			                <label><fmt:message key="venda.fechar.formadepagamento" /></label>
							<form:select  path="contaAReceber[${cont.index}].formaDePagamento"  class="form-control select2" >
							     <c:forEach items="${formaDePagamento}" var="p">  
								    <fmt:message key="formaDePagamento.${p }"  var="forma"  />
							        <form:option value="${p}" label="${forma }"/>
						        </c:forEach>
							</form:select>
		               </div>
		               
		              <div class="form-group col-lg-4"> 
		                  <label ><fmt:message key="venda.cadastro.valorTotalParcela" /></label>
		                  <fmt:message key="venda.cadastro.totalPlaceHolder" var="totalPlaceHolder"/>
		                  <form:input path="contaAReceber[${cont.index}].total" cssClass="form-control money totalParcelado" readonly="true" placeholder="${totalPlaceHolder}" />
		                  <form:errors path="contaAReceber[${cont.index}].total" class="text-danger" />
	                </div> 
		               
		                </div>
	                
	              </c:forEach>
	          </div> 
	          </div>    
	               
	               
<!-- 		                 <div class="form-group col-lg-12"> -->
<!-- 		                 <div class="row"> -->
<!-- 		                 <div class="col-lg-8"> -->
<!-- 							<div class="col-lg-12"> -->
<%-- 			                  <label ><fmt:message key="venda.cadastro.valorParcelado" /></label> --%>
<%-- 			                  <fmt:message key="venda.cadastro.valorFinalPlaceHolder" var="valorFinalPlaceHolder"/> --%>
<%-- 			                  <form:input  path="parcela" cssClass="form-control number"  placeholder="${quantidadeParcelasPlaceHolder}" /> --%>
<!-- 							</div> -->
<!-- 							<div class="col-lg-12"> -->
							
<!-- 							<p class="text-muted well well-sm no-shadow" style="margin-top: 10px;"> -->
<%-- 							<fmt:message key="venda.cadastro.totalde" /><label id="valorParcelamento" style=" font-weight: normal "> --%>
					            
<!-- 							</label> -->
<!--           					</p> -->
<!-- 							</div> -->
<!-- 							</div> -->
<!-- 		                 </div> -->

<!-- 		                </div> -->
                     
						
	              </div>
	              <!-- /.box-body -->
              <div class="box-footer">
<!--                 <button type="submit" class="btn btn-default">Cancel</button> -->
                	<button type="submit" class="btn btn-primary pull-right btn-lg btn-block">
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