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

<fmt:message key="conta.editar.pagar.title" var="title" />
<customTags:page title="${title}" financeiroContaPaga="active">
	<jsp:attribute name="extraScripts">
<!-- Focus no input -->
<script src="/resources/extras/js/focusPrimeiroInput.js"></script>	
									
<script src="/resources/bower_components/maskmoney/jquery.maskMoney.js"></script>
			
			<script>
				function moedaParaNumero(valor) {
					return isNaN(valor) == false ? parseFloat(valor)
							: parseFloat(valor.replace("R$", "").replace(".",
									"").replace(",", "."));
				};
				function numeroParaMoeda(n, c, d, t) {
							c = isNaN(c = Math.abs(c)) ? 2 : c,
							d = d == undefined ? "," : d,
							t = t == undefined ? "." : t,
							s = n < 0 ? "-" : "",
							i = parseInt(n = Math.abs(+n || 0).toFixed(c)) + "",
							j = (j = i.length) > 3 ? j % 3 : 0;
					return s
							+ (j ? i.substr(0, j) + t : "")
							+ i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t)
							+ (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
				};

				$(document).on(
						"change",
						"#pago",
						function() {

							var pago = moedaParaNumero($('#pago').val());
							var total = moedaParaNumero($('#total').val());

							if (pago > 0 && total > 0) {
								var resultado = (total - pago);
								$('#aPagar').val(
										numeroParaMoeda(resultado.toFixed(2)));
							}

						});
				$(document).on("change", "#aPagar", function() {

					var aPagar = moedaParaNumero($('#aPagar').val());
					var total = moedaParaNumero($('#total').val());

					if (total > 0 && aPagar > 0) {
						var resultado = (total - aPagar);
						$('#pago').val(numeroParaMoeda(resultado.toFixed(2)));
					}

				});

				$(function() {
					$('.money').maskMoney({
						prefix : 'R$ ',
						allowNegative : false,
						thousands : '.',
						decimal : ',',
						affixesStay : false
					});
				});
			</script>
</jsp:attribute>
	<jsp:body>
<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
           <fmt:message key="conta.editar.pagar.h3" />
      </h1>
      <ol class="breadcrumb">
        <li><a href="/"><i class="fa fa-dashboard"></i>
					<fmt:message key="navegacao.home" /></a></li>
        <li class="active"><a href="#"><fmt:message
							key="navegacao.financeiro" /></a></li>
        <li class="active"><a href="#"><fmt:message
							key="navegacao.controle" /></a></li>
        <li class="active"><a href="/conta/paga"><fmt:message
							key="navegacao.contaAPagar" /></a></li>
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
<%--               <fmt:message key="conta.cadastro.h3" /> --%>
<!-- 			  </h3> -->
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form:form
							action="${s:mvcUrl('PPC#gravarContaAPagar').build() }"
							method="post" modelAttribute="pagamentoEditForm"
							autocomplete="off">
            
	              <div class="box-body">
	                
	                <div class="form-group col-lg-12">
                         <label><fmt:message
											key="conta.edit.dataVencimento" /></label>
                         <form:input type="text" path="dataVencimento"
										cssClass="form-control dataPicker" id="date" name="date"
										placeholder="${dataVencimento} DD/MM/AAAA" />
                         <form:errors class="text-danger"
										path="dataVencimento" />
                         
                   </div>
                   
                    <div class="form-group col-lg-12">
	                  <label><fmt:message key="conta.edit.total" /></label>
	                  <fmt:message key="conta.edit.totalPlaceHolder"
										var="totalPlaceHolder" />
	                  <form:input path="total"
										cssClass="form-control money item valorDeCompra"
										placeholder="${totalPlaceHolder}" />
	                <form:errors class="text-danger" path="total" />
	                </div>
	           
	                <div class="form-group col-lg-12">
	                  <label><fmt:message key="conta.edit.pago" /></label>
	                  <fmt:message key="conta.edit.pagoPlaceHolder"
										var="pagoPlaceHolder" />
	                  <form:input path="pago" readonly="true"
										cssClass="form-control money item valorDeCompra"
										placeholder="${pagoPlaceHolder}" />
	                <form:errors class="text-danger" path="pago" />
	                </div>
	           
	                <div class="form-group col-lg-12">
	                  <label><fmt:message key="conta.edit.aPagar" /></label>
	                  <fmt:message key="conta.edit.aPagarPlaceHolder"
										var="aPagarPlaceHolder" />
	                  <form:input path="aPagar" readonly="true"
										cssClass="form-control money item valorDeCompra"
										placeholder="${aPagarPlaceHolder}" />
	                <form:errors class="text-danger" path="aPagar" />
	                </div>
	                
                    <div class="form-group col-lg-12">
	                  <label><fmt:message
											key="conta.edit.observacao" /></label>
	                  <fmt:message key="conta.edit.observacaoPlaceHolder"
										var="observacaoPlaceHolder" />
	                  <form:textarea path="observacao"
										cssClass="form-control" placeholder="${observacaoPlaceHolder}" />
	                <p class="text-danger"> <form:errors
											path="observacao" />
									</p>
	                </div>
	                  
    			<div class="box-body">
              <dl>
              </dl>
            </div>
           
           
           
	        <div class="box-header">
              <h3 class="box-title">
											<fmt:message key="conta.pagamento.h3" />
										</h3>
            </div>
	       <div class="table-responsive">
              <table id="example2"
											class="table table-bordered table-hover">
								
                <thead>
	                <tr>
	                  <th width="20%"><fmt:message
															key="conta.pagamento.efetuado" /></th>
	                  <th width="15%"><fmt:message
															key="conta.pagamento.data" /></th>
	                  <th width="15%"><fmt:message
															key="conta.pagamento.forma" /></th>
<!-- 	                    -->
	                   
	                </tr>
                </thead>
                <tbody>
                <c:forEach
													items="${pagamentoEditForm.listaPagamentosEfetuados}"
													var="pgtEfetuado">
                
                <tr>
                <td>${pgtEfetuado.pago }  </td>
                <td><fmt:formatDate type="date" dateStyle="short"
																timeStyle="short"
																value="${pgtEfetuado.dataPagamento.time }" />
                
														<td>${pgtEfetuado.formaDePagamento }  </td>
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
              <div class="box-footer">
              <div class="form-group col-lg-3">
                	<button type="submit"
										class="btn btn-primary pull-right btn-lg btn-block">
						<fmt:message key="cadastro.salvar" />
					</button>
					<div class="clearfix"></div>
			  </div>
              <div class="form-group col-lg-3">
            
                	<button type="submit" name="cancelar"
                	
					 <c:choose>
    						<c:when test="${( empty pagamentoEditForm.listaPagamentosEfetuados) && (pagamentoEditForm.estaQuitado == 'NAOQUITADO' || pagamentoEditForm.estaQuitado != 'CANCELADO' )}">
                	  
										class="btn btn-primary pull-right btn-lg btn-block "
                	 </c:when>    
    					<c:otherwise>
                	  					class="btn btn-primary pull-right btn-lg btn-block disabled"
						  </c:otherwise>
					</c:choose>
										
										>
						<fmt:message key="cadastro.cancelar" />
					</button>
					<div class="clearfix"></div>
			  </div>
              <div class="form-group col-lg-3">
                	<button type="submit" name="baixa"
                	  <c:choose>
    						<c:when test="${pagamentoEditForm.estaQuitado == 'QUITADO'}">
                	  										class="btn btn-primary pull-right btn-lg btn-block disabled"
                	  
                	 </c:when>    
    					<c:otherwise>
										class="btn btn-primary pull-right btn-lg btn-block "
						  </c:otherwise>
					</c:choose>
										>
						<fmt:message key="cadastro.baixa" />
					</button>
					<div class="clearfix"></div>
			  </div>
              <div class="form-group col-lg-3">
                	<button type="submit" name="estornar"
                	
					<c:choose>
    						<c:when test="${not empty pagamentoEditForm.listaPagamentosEfetuados}">
                	  
										class="btn btn-primary pull-right btn-lg btn-block "
                	 </c:when>    
    					<c:otherwise>
                	  										class="btn btn-primary pull-right btn-lg btn-block disabled"
						  </c:otherwise>
					</c:choose>
										
										
										>
						<fmt:message key="cadastro.estornar" />
					</button>
					<div class="clearfix"></div>
			  </div>
              </div>
               <input type="hidden" name="id"
								value="${pagamentoEditForm.id}" />
              
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