<%@attribute name="title" required="true"%>
<%@attribute name="extraScripts" fragment="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>${title } - Assistente de vendas Pet</title>
 <%@include file="/WEB-INF/header.jsp"%>
 <%@include file="/WEB-INF/sidebar.jsp"%>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <jsp:doBody />
    
    
  </div>
  <!-- /.content-wrapper -->
  	<%@include file="/WEB-INF/footer.jsp"%>
</body>
</html>
 <jsp:invoke fragment="extraScripts"></jsp:invoke>

