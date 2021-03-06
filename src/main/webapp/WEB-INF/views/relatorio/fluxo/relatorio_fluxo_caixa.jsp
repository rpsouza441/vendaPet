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

<fmt:message key="fluxoCaixa.title" var="title" />
<customTags:page title="${title}" relatorioFluxo="active">
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
			
			<!-- Date Picker -->
  <link rel="stylesheet" href="/resources/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
  <!-- Daterange picker -->
  <link rel="stylesheet" href="/resources/bower_components/bootstrap-daterangepicker/daterangepicker.css">
  
<script src="/resources/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
  <script src="/resources/bower_components/bootstrap-datepicker/js/locales/bootstrap-datepicker.pt-BR.js"></script>
 				<script src="/resources/extras/js/datapicker.js"></script>
 			


</jsp:attribute>

	<jsp:body>
<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
           <fmt:message key="fluxoCaixa.h1" />
      </h1>
       <ol class="breadcrumb">
        <li><a href="/"><i class="fa fa-dashboard"></i>
					<fmt:message key="navegacao.home" /></a></li>
        <li class="active"><a href="#"><fmt:message
							key="navegacao.relatorio" /></a></li>
        <li class="active"><a href="/compra"><fmt:message
							key="navegacao.fluxoCaixa" /></a></li>
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
            <div class=" hidden-print">
            <form:form
								action="${s:mvcUrl('RFCC#relatoriofluxoCaixaComSearch').build() }"
								id="form_search" method="post" modelAttribute="startWith"
								autocomplete="off" data-toggle="validator">
								
					
				    <div class="col-xs-12 ">
				    <div class="col-xs-3 ">
				    </div>
				    <div class="col-xs-6 ">
				      
							        
						 <div class="form-group col-lg-6 ">
						<label> <fmt:message key="compra.lista.dataDe" /></label>    
						
						           <input type="text" value="" id="startWith" name="startWith"
						            class="form-control dataPicker"  placeholder=" DD/MM/AAAA" />	
						 </div> 
						 <div class="form-group col-lg-6  ">
						 							    <label><fmt:message key="compra.lista.dataAte" /></label>    
						 
						        <input type="text" value="" id="endWith" name="endWith" 
						        class="form-control dataPicker" placeholder="DD/MM/AAAA" />
						  </div>
						 <div class="form-group col-lg-3  ">
							
						</div>
						 <div class="form-group col-lg-6  ">
							<div class="checkbox">
		                      <label>
		                        <input name="simples" type="checkbox"> <fmt:message key="fluxo.lista.simples" />
		                      </label>
                            </div>
						</div>
						 <div class="form-group col-lg-3  ">
							
						</div>
						  
						   <div class="form-group col-lg-12  ">
						  
						
								 <button class="btn btn-primary btn-block" type="submit">
									<span class="glyphicon glyphicon-search"></span>
									 </button>
							 </div>
														
						
				      
				    </div>
				    <div class="col-xs-3 ">
				    </div>
				    </div>
				    <div class="col-xs-12 ">
				    	<div class="col-xs-3 "> </div>
					    <div class="col-xs-6 ">
					        <div class="help-block with-errors"></div>
						   <c:if test="${not empty error}">	
						      
						     <div class="alert alert-warning alert-dismissible">
	                		<button type="button" class="close" data-dismiss="alert"
													aria-hidden="true">&times;</button>
				                <h5><i class="icon fa fa-ban"></i> 
				                <fmt:message key="procurar.fluxo.mensagem.naoEcontrado" />
								</h5>
	                       </div>
					       </c:if>
															
							
					      
					    </div>
				    	<div class="col-xs-3 "> </div>
				    </div>
				   
				  
    			<div class="box-body">
              <dl>
              </dl>
            </div>
           
           
           </form:form>
           </div>
             <div class="table-responsive no-padding">
             <p>
             <fmt:message key="fluxo.periodo" /> 
             <fmt:formatDate type = "date"  dateStyle = "short" timeStyle = "short" value = "${inicio.time }" />
             <fmt:message key="fluxo.ate" /> 
          
             <fmt:formatDate type = "date"  dateStyle = "short" timeStyle = "short" value = "${fim.time }" />
             </p>
             <h1>
					           <fmt:message key="fluxo.caixa.saida.h1" />
					
				</h1>
              <table 	class="table borderless table-hover table-striped">
                <c:if test="${showTableHead ==true }">
                <thead>
	                <tr>
	                  <th width="15%"><fmt:message key="fluxo.dataEmissao" /></th>
	                  <th width="20%"><fmt:message key="fluxo.responsavel" /></th>
	                  <th width="20%"><fmt:message key="fluxo.obs" /></th>
	                  <th width="10%"><fmt:message key="fluxo.subtotal" /></th>
<!-- 	                    -->
	                   
	                </tr>
                </thead>
                </c:if>
                <tbody>
                <c:forEach items="${compras}" var="compra">
                <tr>
                <td><fmt:formatDate type = "date"  dateStyle = "short" timeStyle = "short" value = "${compra.dataEmissao.time }" />
                <td>${compra.user.nome }  </td>
                <td>${compra.observacao }  </td>
                <td>${compra.total }  </td>
                </tr>
                </c:forEach>
                
                </tbody>
                <tfoot>
                <tr>
                <td> </td>
                <td> </td>
                <td><fmt:message key="fluxo.total" /></td>
                <td>${totalCompra }</td>
                </tr>
                </tfoot>
              </table>
              
               
              </div>
              
              
              
             <div class="table-responsive no-padding">
             <h1>
					           <fmt:message key="fluxo.caixa.entrada.h1" />
					
				</h1>
              <table class="table borderless table-hover table-striped">
                <c:if test="${showTableHead ==true }">
                <thead>
	                <tr>
	                  <th width="15%"><fmt:message key="fluxo.dataEmissao" /></th>
	                  <th width="20%"><fmt:message key="fluxo.cliente" /></th>
	                  <th width="20%"><fmt:message key="fluxo.obs" /></th>
	                  <th width="10%"><fmt:message key="fluxo.subtotal" /></th>
<!-- 	                    -->
	                   
	                </tr>
                </thead>
                </c:if>
                <tbody>
                <c:forEach items="${vendas}" var="venda">
                <tr>
                <td><fmt:formatDate type = "date"  dateStyle = "short" timeStyle = "short" value = "${venda.dataEmissao.time }" />
                <td>${venda.cliente.nomeCompleto }  </td>
                <td>${venda.observacao }  </td>
                <td>${venda.total }  </td>
                </tr>
                </c:forEach>
                
                
                </tbody>
                <tfoot>
                <tr>
                <td> </td>
                <td> </td>
                <td><fmt:message key="fluxo.total" /></td>
                <td>${totalVenda }</td>
                </tr>
                </tfoot>
              </table>
              
              <div class="box-footer hidden-print">
<!--                 <button type="submit" class="btn btn-default">Cancel</button> -->
                	    <a  rel="tooltip" class="btn btn-primary pull-right "
							 id="edit_event"   onclick="window.print()">
						<fmt:message key="carrinho.imprimir" />
							 <i	class="fa  fa-print"></i> 
						</a>
					<div class="clearfix"></div>
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