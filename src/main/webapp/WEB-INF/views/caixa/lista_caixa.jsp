<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="customTags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message key="caixa.lista.title" var="title" />
<customTags:page title="${title}" listaCaixa="active" >
<jsp:attribute name="extraScripts">
	<!-- DataTables -->
	<script src="/resources/bower_components/datatables.net/js/jquery.dataTables.min.js" type="text/javascript"></script>
	<script src="/resources/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js" type="text/javascript"></script>
	<script src="/resources/extras/js/configDateTable.js"></script>


	<script src="/resources/plugins/validate/validator.js"
			type="text/javascript"></script>

</jsp:attribute>

<jsp:body>
<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
           <fmt:message key="caixa.lista.h1" />
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
     
      <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              
              <div class="form-group col-lg-12">
              
              <blockquote class="pull-right">
                <p>
                <c:choose>
		             <c:when test="${ultimoCaixaFechado != null }">
							<fmt:formatDate type = "both" value = "${ultimoCaixaFechado.time}" />
		             </c:when>
		             <c:when test="${ultimoCaixaFechado  == null}" >
		            	 <fmt:message key="caixa.semCaixaFechado" />
		              </c:when>
	                   
	                   </c:choose>
                </p>
                <small><fmt:message key="caixa.ultimoFechamento" /></small>
              </blockquote>
	                  
	                  
	          </div>
	         <div class="col-xs-12">
			 			    <div class="row">
			 			       <div class="col-xs-4">
						 			<a  rel="tooltip" class="btn btn-primary btn-lg  btn-block "
										 id="edit_event"   
 								href="<s:url value='/caixa/entrada' />">
										 <i	class="fa  fa-arrow-down"></i> 
							    	<fmt:message key="caixa.entrada" />
									</a>
				    			</div>
			 			       <div class="col-xs-4">
						 			<a  rel="tooltip" class="btn btn-primary btn-lg btn-block"
										 id="edit_event"   
									href="<s:url value='/caixa/saida' />">
										 <i	class="fa  fa-arrow-up"></i> 
							    	<fmt:message key="caixa.saida" />
									</a>
				    			</div>
			 			       <div class="col-xs-4">
			 			       
			 			        <button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="modal" data-target="#modal-default">
					               	<i	class="fa  fa-remove"></i> 
					               	<fmt:message key="caixa.fechar" />
					              </button>
				    			</div>
										<div class="modal fade" id="modal-default">
										          <div class="modal-dialog">
										            <div class="modal-content">
										              <div class="modal-header">
										                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
										                  <span aria-hidden="true">&times;</span></button>
										                <h4 class="modal-title">
										                	<fmt:message key="caixa.fechamento" />
										                </h4>
										              </div>
										              <div class="modal-body">
										                <p>
										                	<fmt:message key="caixa.fechamento.mensagem" />
														</p>
										              </div>
										              <div class="modal-footer">
										                <button type="button" class="btn btn-default btn-lg" data-dismiss="modal">
										                	<fmt:message key="caixa.fechamento.nao" />
										                </button>
										                
										                <a  rel="tooltip" class="btn btn-primary btn-lg"
														href="<s:url value='/caixa/fecharCaixa' />">
												    	<fmt:message key="caixa.fechamento.sim" />

														</a>
 										              </div>
										            </div>
										            <!-- /.modal-content -->
										          </div>
										          <!-- /.modal-dialog -->
										        </div>
										        <!-- /.modal -->
						
						</div>
			 			
			 	</div>      
          	</div>
            
            
            <!-- /.box-header -->
            <div class="box-body">
             <div class="table-responsive">
              <table id="example2" class="table table-bordered table-hover">
                <thead>
	                <tr>
	                  <th><fmt:message key="caixa.vendedor" /></th>
	                  <th><fmt:message key="caixa.tipo" /></th>
	                  <th><fmt:message key="caixa.DataHora" /></th>
	                  <th><fmt:message key="caixa.forma" /></th>
	                  <th><fmt:message key="caixa.origem" /></th>
	                  <th><fmt:message key="caixa.obs" /></th>
	                  <th></th>
	                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listaMovimentacoes}" var="movimentacao">
                <tr>
                  <td>${movimentacao.user.nome }</td>
                  
                  <c:set var = "movimento" value="${movimentacao.tipoDeMovimentacao }"/>
	                   <c:choose>
		                   <c:when test="${movimento =='ENTRADA' }">
		                  		<td class="text-success"><b><fmt:message key="caixa.${movimentacao.tipoDeMovimentacao }" /> </b> </td>
		                   </c:when>
		                   <c:when test="${movimento =='SAIDA' }">
		                  		<td class="text-danger"><b><fmt:message key="caixa.${movimentacao.tipoDeMovimentacao }" /></b></td>
		                   </c:when>
	                   
	                   </c:choose>
                
                  
                  <td><fmt:formatDate type="date" value="${movimentacao.dataHoraMovimento.time }" pattern="dd/MM/YYYY hh:mm"/>   </td>
                  <td>${movimentacao.formaDePagamento }</td>
                  <td>${movimentacao.origemMovimento }</td>
                  <td>${movimentacao.observacao }</td>
                  <td>${movimentacao.valor }</td>
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