<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="https://res.cloudinary.com/dcptr5ivh/image/upload/v1555936240/zertones/favicon.ico" />

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>

<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id"
     content="684282577285-tjiqn6ore4710t26h9hdc422uar8pae1.apps.googleusercontent.com">

<title>Login Page</title>
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
</head>

<body onload='document.loginForm.username.focus();'  background="https://res.cloudinary.com/dcptr5ivh/image/upload/v1567082987/ctluump8etwqmzoymcyp.png">
   <div class="container">
		<div class="login-box animated fadeInUp">	
       <form name='loginForm'  action="<c:url value='/j_spring_security_check' />" method='POST'>
            <div class="box-header"><br>
				<img src="https://res.cloudinary.com/dcptr5ivh/image/upload/v1566815485/mml05qw0hllgjjz8v5x5.png" width="90px" height="80px">
			</div>
			<c:if test="${not empty error}">
			    <div class="error">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>
		    <table align="center">
		       <h4 style="color:green; font-family: Verdana">Sign In to your account</h4>
			<tr >
				<td><input name="username"   required="required" placeholder="User Name / Email Address"/></td>
			</tr>
			<tr>
				<td><input name="password" type="password" required="required" placeholder="Password" /></td>
			</tr>
			<tr >
				<td colspan='6' align="center"><button type="submit" name="submit">Sign In</button></td>
			</tr>
		 </table>
		  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
          </form>
          <br>
          <a href="<c:url value='/register'/>"><font style="font-style: italic ; color:green">New User</font></a>  <br/>        
			<a href="<c:url value='/identify' />"><font style="font-style: italic ; color:green">Forgot Password</font></a>
	  </div>
     </div>  
   <!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-127607784-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());
  gtag('config', 'UA-127607784-1');
</script>
       
   </body>
</html>