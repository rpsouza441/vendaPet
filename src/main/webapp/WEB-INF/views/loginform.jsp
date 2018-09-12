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


<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 2 | Log in</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="/resources/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="/resources/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="/resources/bower_components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="/resources/dist/css/AdminLTE.min.css">
 
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  
  

  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
   <b><fmt:message key="navegacao.nomeDoProgramaBOLD" /></b><fmt:message key="navegacao.nomeDoPrograma" />
      
  </div>
  <!-- /.login-logo -->
  
  <div class="login-box-body">
    <p class="login-box-msg"><fmt:message key="login.login" /></p>
   <c:set var="root" value="${pageContext.request.contextPath}"/>
 
     <form:form servletRelativeAction="/login" autocomplete="off">
      
      <div class="form-group has-feedback">
      <fmt:message key="login.email" var="emailPlaceHolder" />
      <input type="text" name='username' class="form-control .focus" placeholder="${emailPlaceHolder }"/>
      <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
      
      <div class="form-group has-feedback">
        <fmt:message key="login.senha" var="senhaPlaceHolder" />
        <input type="password"  name='password' class="form-control" placeholder="${senhaPlaceHolder }">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      
      <div class="row">
        <div class="col-xs-8">
        </div>
        <!-- /.col -->
        <div class="col-xs-4">
          <button type="submit"
            class="btn btn-primary btn-block btn-flat">
           			        <fmt:message key="login.logar" />

           </button>
        </div>
        <!-- /.col -->
      </div>
     </form:form>

<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
   <div class="box-body">

	<div class="callout callout-danger">
		<h4><fmt:message key="login.erro" /></h4>
		<p><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
		<c:remove var = "SPRING_SECURITY_LAST_EXCEPTION" scope = "session" /></p>
	</div>
 </div>
</c:if>
              
    <!-- /.social-auth-links -->

<!--     <a href="#">I forgot my password</a><br> -->
<!--     <a href="register.html" class="text-center">Register a new membership</a> -->

  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery 3 -->
<script src="/resources/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Focus no input -->
<script src="/resources/extras/js/focusPrimeiroInput.js"></script>

</body>
</html>
