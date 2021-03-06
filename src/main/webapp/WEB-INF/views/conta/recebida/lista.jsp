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

<fmt:message key="conta.paga.title" var="title" />
<customTags:page title="${title}" financeiroContaRecebida="active">
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
           <fmt:message key="conta.paga.h1" />
      </h1>
       <ol class="breadcrumb">
        <li><a href="/"><i class="fa fa-dashboard"></i>
					<fmt:message key="navegacao.home" /></a></li>
        <li class="active"><a href="#"><fmt:message
							key="navegacao.financeiro" /></a></li>
        <li class="active"><a href="#"><fmt:message
							key="navegacao.cadastro" /></a></li>
        <li class="active"><a href="/conta/recebida"><fmt:message
							key="navegacao.contaAPagar" /></a></li>
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
								action="${s:mvcUrl('RPC#listaRecebidaComSearch').build() }"
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
				                <fmt:message key="procurar.conta.mensagem.naoEcontrado" />
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
             <div class="table-responsive">
              <table id="example2"
									class="table table-bordered table-hover">
                <thead>
	                <tr>
	                  <th width="15%"><fmt:message key="conta.cliente" /></th>
	                  <th width="20%"><fmt:message key="conta.obs" /></th>
	                  <th width="13%"><fmt:message key="conta.dataEmissao" /></th>
	                  <th width="13%"><fmt:message key="conta.dataVencimento" /></th>
	                  <th width="8%"><fmt:message key="conta.total" /></th>
	                  <th width="8%"><fmt:message key="conta.pago" /></th>
	                  <th width="8%"><fmt:message key="conta.apagar" /></th>
	                  <th width="10%"><fmt:message key="conta.status" /></th>
	                   <th width="5%"></th>
<!-- 	                    -->
	                   
	                </tr>
                </thead>
                <tbody>
                <c:forEach items="${contas}" var="conta">
                <tr>
                <td>${conta.contaRecebida.cliente.nomeCompleto }  </td>
                <td>${conta.observacao }  </td>
                <td><fmt:formatDate type = "date"  dateStyle = "short" timeStyle = "short" value = "${conta.dataEmissao.time }" />
                  <td><fmt:formatDate type = "date"    dateStyle = "short" timeStyle = "short" value = "${conta.dataVencimento.time }" />
                <td>${conta.total }  </td>
                <td>${conta.pago }  </td>
                <td>${conta.APagar }  </td>
                <td><fmt:message key="status.${conta.estaQuitado }" />  </td>
                 <td>
                  <a rel="tooltip" class="btn btn-success" id="edit_event"
													href="<s:url value='/conta/editarContaRecebida/${conta.id}' />">
							 <i class="fa   fa-eye"></i> 
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