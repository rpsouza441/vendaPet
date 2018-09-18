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

<fmt:message key="compra.lista.title" var="title" />
<customTags:page title="${title}" listaCompra="active">
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
           <fmt:message key="compra.lista.h1" />
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
          <div class="box">
            <div class="box-header">
              <h3 class="box-title"></h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
            <form:form
								action="${s:mvcUrl('CRC#listaComSearch').build() }"
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
														
						        <div class="help-block with-errors"></div>
					   <c:if test="${not empty error}">	
					      
					     <div class="alert alert-warning alert-dismissible">
                <button type="button" class="close" data-dismiss="alert"
												aria-hidden="true">&times;</button>
                <h5><i class="icon fa fa-ban"></i> <fmt:message key="procurar.compra.mensagem.naoEcontrado" />
				</h5>
              </div>
				            </c:if>
				      
				    </div>
				    <div class="col-xs-3 ">
				    </div>
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
	                  <th ><fmt:message key="compra.lista.fornecedor" /></th>
	                  <th ><fmt:message key="compra.lista.datEmissao" /></th>
	                   <th ></th>
	                   <th></th>
<!-- 	                    -->
	                   
	                </tr>
                </thead>
                <tbody>
                <c:forEach items="${compras}" var="compra">
                <tr>
                  <td>${compra.fornecedor.nome }  </td>
                  <td><fmt:formatDate type = "date" 
         dateStyle = "short" timeStyle = "short" value = "${compra.dataEmissao.time }" />
                 <td width="5%" class="td-actions text-middle">
                  <a  rel="tooltip" class="btn btn-success" id="edit_event"
													href="<s:url value='/compra/visualizarCompra/${compra.id}' />">
							 <i class="fa   fa-eye"></i> 
						</a>  
				 </td>
				  <td width="5%" class="td-actions text-middle">
						<a rel="tooltip" class="btn btn-danger" id="edit_event"
													href="<s:url value='/compra/excluirCompra/${compra.id}' />">
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
              
               
				    <div class="col-xs-4 pull-right">
				    
						<div class="form-group ">
						
						
						<a rel="tooltip" class="btn btn-success btn-block" id="edit_event"
											href="<s:url value='/compra/lista' />">
							 <span class="glyphicon glyphicon-plus pull-left"></span> 
							   <fmt:message key="compra.adicionar" />
						</a>
						
						
					      </div>
				      
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