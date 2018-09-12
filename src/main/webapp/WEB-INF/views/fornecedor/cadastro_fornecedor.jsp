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

<fmt:message key="fornecedor.cadastro.title" var="title" />
<customTags:page title="${title}" cadastroFornecedor="active">
	<jsp:attribute name="extraScripts">
<!-- Focus no input -->
<script src="/resources/extras/js/focusPrimeiroInput.js"></script>

										
										
</jsp:attribute>
	<jsp:body>
<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
           <fmt:message key="fornecedor.cadastro.h3" />
      </h1>
      <ol class="breadcrumb">
        <li><a href="/"><i class="fa fa-dashboard"></i>
					<fmt:message key="navegacao.home" /></a></li>
        <li class="active"><a href="#"><fmt:message
							key="navegacao.estoque" /></a></li>
        <li class="active"><a href="#"><fmt:message
							key="navegacao.cadastro" /></a></li>
        <li class="active"><a href="/fornecedor/cadastro"><fmt:message
							key="navegacao.fornecedor" /></a></li>
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
<%--               <fmt:message key="fornecedor.cadastro.h3" /> --%>
<!-- 			  </h3> -->
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form:form
							action="${s:mvcUrl('FC#gravarFornecedor').build() }"
							method="post" modelAttribute="fornecedor" autocomplete="off">
            
	              <div class="box-body">
	                <div class="form-group col-lg-12">
	                  <label><fmt:message
											key="fornecedor.cadastro.nome" /></label>
	                  <fmt:message
										key="fornecedor.cadastro.nomePlaceHolder" var="placeholder" />
	                  <form:input path="nome" cssClass="form-control"
										placeholder="${placeholder}" />
	                <p class="text-danger"> <form:errors path="nome" />
									</p>
	                </div>

						
	              </div>
	              <!-- /.box-body -->
              <div class="box-footer">
<!--                 <button type="submit" class="btn btn-default">Cancel</button> -->
                	<button type="submit"
									class="btn btn-info pull-right btn-lg btn-block">
						<fmt:message key="cadastro.salvar" />
					</button>
					<div class="clearfix"></div>
              </div>
               <input type="hidden" name="id" value="${fornecedor.id}" />
              
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