<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="customTags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message key="user.cadastro.title" var="title" />
<customTags:page title="${title}"  cadastroUser="active">

	<jsp:attribute name="extraScripts">
<!-- Focus no input -->
<script src="/resources/extras/js/focusPrimeiroInput.js"></script>
										
</jsp:attribute>

     <jsp:body>
         <!-- Content Header (Page header) -->
         <section class="content-header">
             <h1>
                 <fmt:message key="user.cadastro.h3" />
             </h1>
             <ol class="breadcrumb">
                 <li><a href="/"><i class="fa fa-dashboard"></i><fmt:message key="navegacao.home" /></a></li>
                 <li class="active">
                     <a href="#">
                         <fmt:message key="navegacao.cadastro" />
                     </a>
                 </li>
                 <li class="active">
                     <a href="/user/cadastro">
                         <fmt:message key="navegacao.user" />
                     </a>
                 </li>
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
	                  <%--               <fmt:message key="grupo.cadastro.h3" /> --%>
	                      <!-- 			  </h3> -->
	              </div>
	              <!-- /.box-header -->
	              <!-- form start -->
	              <form:form action="${s:mvcUrl('UC#gravarUser').build() }" method="post" 
	              	modelAttribute="user" autocomplete="off"> 
	
	                  <div class="box-body">
	                   
                      <div class="form-group col-lg-12 ">
                          <label><fmt:message key="user.cadastro.nome" /></label>
                          <fmt:message key="user.cadastro.nomePlaceHolder" var="nomePlaceHolder" />
                          <form:input path="nome" cssClass="form-control" placeholder="${nomePlaceHolder}" />
                              <form:errors class="text-danger" path="nome" />
                      </div>
                      <div class="form-group col-lg-12">
                          <label><fmt:message key="user.cadastro.username" /></label>
                          <fmt:message key="user.cadastro.usernamePlaceHolder" var="usernamePlaceHolder" />
                          <form:input path="username" cssClass="form-control" placeholder="${usernamePlaceHolder}" />
                              <form:errors class="text-danger" path="username" />
                      </div>
	                      
	                      <div class="form-group col-lg-12">
	                          <label><fmt:message key="user.cadastro.password" /></label>
	                          <fmt:message key="user.cadastro.passwordPlaceHolder" var="passwordPlaceHolder" />
	                          <form:password path="password" cssClass="form-control" placeholder="${passwordPlaceHolder}" />
	                          <form:errors class="text-danger" path="password" />
	                       </div>
                                  <div class="form-group col-lg-12">
								<label><fmt:message key="user.cadastro.authorities" /></label>
								<form:select  multiple="true" path="authorities"  class="form-control select2" >
								<form:options items="${authorities}" itemLabel="authority"  itemValue="id" />
								</form:select>
                              <form:errors class="text-danger" path="authorities" />
                         
                 			</div>
	                  <!-- /.box-body -->
	                  <div class="box-footer">
	                      <!--                 <button type="submit" class="btn btn-default">Cancel</button> -->
	                      <button type="submit" class="btn btn-primary pull-right btn-lg btn-block">
							<fmt:message key="cadastro.salvar" />
							</button>
	                                                <div class="clearfix"></div>
	                                            </div>
	                                       <input type="hidden" name="id" value="${user.id}" />
	                  </div>
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
