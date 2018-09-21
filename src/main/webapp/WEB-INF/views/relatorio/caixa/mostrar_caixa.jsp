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

<fmt:message key="relatorio.caixa.title" var="title" />
<customTags:page title="${title}" relatorioCaixa="active">
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
           <fmt:message key="relatorio.caixa.h1" />
      </h1>
       <ol class="breadcrumb">
        <li><a href="/"><i class="fa fa-dashboard"></i>
					<fmt:message key="navegacao.home" /></a></li>
        <li class="active"><a href="#"><fmt:message
							key="navegacao.relatorio" /></a></li>
        <li class="active"><a href="/relatorio/caixa"><fmt:message
							key="navegacao.relatorio.gaveta" /></a></li>
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
          <div class="row">
          <div class="col-xs-12">
          <div class="box-body">
           <div class="visible-print">
				<h1>
					           <fmt:message key="relatorio.caixa.h1" />
					
				</h1>
			</div>
            <div class="box-body table-responsive no-padding">
                
                <table class="table borderless table-hover table-striped">
                <thead>
	                <tr>
	                  <th width="20%"><fmt:message key="relatorio.caixa.responsavel" /></th>
	                  <th width="15%"><fmt:message key="relatorio.caixa.aberto" /></th>
	                  <th width="15%"><fmt:message key="relatorio.caixa.fechado" /></th>
	                  <th width="10%"><fmt:message key="relatorio.caixa.total" /></th>
<!-- 	                    -->
	                   
	                </tr>
                </thead>
                    <tbody>
                <tr>
                <td>${caixa.user.nome }  </td>
                  <td><fmt:formatDate type = "date"    dateStyle = "short" timeStyle = "short" value = "${caixa.abertoDataHora.time }" />
                <td><fmt:formatDate type = "date"  dateStyle = "short" timeStyle = "short" value = "${caixa.fechadoDataHora.time }" />
                <td>${caixa.totalCaixa }  </td>
                </tr>
                
                </tbody>
                  <tfoot>
                <tr>
                </tr>
                </tfoot>
              </table>
              
              
              </div>
              
             
            </div>
          
          </div>
          </div>
          <div class="row">
          <div class="col-xs-12">
          
          <div class="box-body"> 
             <div class="table-responsive no-padding">
              <table id="example2" class="table borderless table-hover table-striped">
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
                <c:forEach items="${caixa.listaMovimentacoes}" var="movimentacao">
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
          </div>
          
            
            
              
            </div>
            
            
            
            
            
            
            <!-- /.box-body -->
          </div>
          
          <!-- /.box -->

        </div>
        <!-- /.col -->
      <!-- /.row -->
      

    </section>
    <!-- /.content -->
    </jsp:body>


</customTags:page>