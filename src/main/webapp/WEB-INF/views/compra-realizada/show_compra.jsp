<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="customTags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message key="compra.lista.title" var="title" />
<customTags:page title="${title}" listaCompra="active" >
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
           <fmt:message key="compra.lista.h1" />
      </h1>
       <ol class="breadcrumb">
        <li><a href="/"><i class="fa fa-dashboard"></i><fmt:message key="navegacao.home" /></a></li>
        <li class="active"><a href="#"><fmt:message key="navegacao.estoque" /></a></li>
        <li class="active"><a href="#"><fmt:message key="navegacao.cadastro" /></a></li>
        <li class="active"><a href="/compra"><fmt:message key="navegacao.compra" /></a></li>
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

           <div class="row">
           
             <div class="col-lg-12">
              <div class="form-group col-lg-4">
				 <label ><fmt:message key="compra.cadastro.nomeFornecedor" /></label>
				<form:input path="compra.fornecedor.nome" cssClass="form-control"  readOnly="readOnly" />
			</div>
             
             
              <div class="form-group col-lg-4" >
				 <label ><fmt:message key="compra.cadastro.dataEmissao" /></label>
				<form:input type="text" path="compra.dataEmissao" cssClass="form-control" readOnly="readOnly" id="date" name="date"  />
			</div>
				
              <div class="form-group col-lg-4">
				 <label ><fmt:message key="compra.cadastro.valorTotal" /></label>
				<form:input path="compra.total" cssClass="form-control"  readOnly="readOnly" />
			</div>
             
             
             </div>
           </div>
           
           
           
             <div class="table-responsive">
              <table id="example2" class="table table-bordered table-hover">
                <thead>
	                <tr>
	                  <th><fmt:message key="produto.lista.nome" /></th>
	                  <th><fmt:message key="produto.lista.quantidade" /></th>
	                  <th><fmt:message key="produto.lista.venda" /></th>
	                </tr>
                </thead>
                <tbody>
                <c:forEach items="${compra.listaProduto}" var="p">
                <tr>
                  <td>${p.nome }</td>
                  <td>${p.quantidade }</td>
                  <td>${p.valorVenda }</td>
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