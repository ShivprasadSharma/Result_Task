<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<script type="text/javascript">
$(document).ready( function(){
   $("#myElem").show().delay(5000).queue(function(n) {
	  $(this).hide(); 
	});
});
</script>
<head>
<link rel="shortcut icon" href="https://res.cloudinary.com/dcptr5ivh/image/upload/v1555936240/zertones/favicon.ico" />

<style>
#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
.error {
    width: 260px;
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
    width: 260px;
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}
</style>
</head>
<body background="https://res.cloudinary.com/dcptr5ivh/image/upload/v1567082987/ctluump8etwqmzoymcyp.png">
  
   <div class="container">
	
	<div class="login-box animated fadeInUp">	
       <form id="formForgotPassword"  action="<c:url value='/identify/vesvl=req' />" method='POST'>
            <div class="box-header"><br>
			    <img src="<c:url value='/static/img/lock.png"'/> width="90px" height="90px">
			</div>
			<table align="center">
		       <h3 style="color:green;">Forgotten Password ?</h3>
		        <c:if test="${not empty error}">
				    <div align="Center"><div id="myElem" class="error" >${error}</div></div>
				</c:if>
				<c:if test="${not empty msg}">
					 <div align="Center"><div id="myElem" class="msg" >${msg}</div></div>
			    </c:if>
			<tr>
			  
			   <td><input type="email" name="inputEmail"   required="required" placeholder="Email Address..." required="required" /></td>
			</tr>
			<tr>
			   <td colspan='6' align="center"><button type="submit" >Reset my Password</button></td>
			</tr>
		  </table><br>
		   <a href="<c:url value='/login' />"><font style="font-style: italic ; color:green">Login</font></a>
		</form>
		
      </div>
   </div>
</body>
</html>