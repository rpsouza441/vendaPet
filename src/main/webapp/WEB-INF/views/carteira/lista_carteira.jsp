<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="customTags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message key="carteira.lista.title" var="title" />
<customTags:page title="${title}" listaCarteira="active" >
<jsp:attribute name="extraScripts">
	<!-- DataTables -->
	<script src="/resources/bower_components/datatables.net/js/jquery.dataTables.min.js" type="text/javascript"></script>
	<script src="/resources/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js" type="text/javascript"></script>

<script>
  $(function () {
    $('#example2').DataTable({
      'paging'      : true,
      'lengthChange': false,
      'searching'   : false,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : false
    })
  })
</script>

</jsp:attribute>

<jsp:body>
<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
           <fmt:message key="carteira.lista.h3" />
      </h1>
       <ol class="breadcrumb">
        <li><a href="/"><i class="fa fa-dashboard"></i><fmt:message key="navegacao.home" /></a></li>
        <li class="active"><a href="#"><fmt:message key="navegacao.lista" /></a></li>
        <li class="active"><a href="/carteira"><fmt:message key="navegacao.carteira" /></a></li>
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
              <table id="example2" class="table table-bordered table-hover">
                <thead>
	                <tr>
	                  <th><fmt:message key="carteira.lista.nome" /></th>
                  <th><fmt:message key="carteira.lista.saldo" /></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${carteiras}" var="carteira">
                <tr>
                  <td>${carteira.nome }</td>
                  <td>${carteira.saldo }</td>
                </tr>
                </c:forEach>
                
                </tbody>
                <tfoot>
                <tr>
                  <th><fmt:message key="carteira.lista.nome" /></th>
                  <th><fmt:message key="carteira.lista.saldo" /></th>
                </tr>
                </tfoot>
              </table>
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