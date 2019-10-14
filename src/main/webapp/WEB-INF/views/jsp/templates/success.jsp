<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.IOException"%>;
<%@page import="java.io.*"%>
<c:url var="saveuserdtl" value='/taskforce/service/staff/register/save' />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Login Page</title>
<head>
<style>
.success {
  background-color:white;
 height: 200px;
  width: 40%;
  border: 3px solid black;
  padding: 50px;
  margin-top: 100px;
  margin-bottom: 100px;
  margin-right: 150px;
  margin-left: 340px;
}
.t1
{
color:green;
 background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 8px 10px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  margin: 4px 2px;
  cursor: pointer;
  
}


</style>
</head>
<body onload='document.loginForm.username.focus();'  background="https://res.cloudinary.com/dcptr5ivh/image/upload/v1567082987/ctluump8etwqmzoymcyp.png">
  <div class="container">
		<div class="success">	  	 
    <h3 style="color:green; font-style: italic">Congratulations !!!!!!!!!!!!!!!!!</h3>
    <h4 style="color:green; font-style: italic">Your Successfully Created Account</h4>
    <h5>Your Username and Password Send Your Emailid</h5>
     <a href="<c:url value='/login'/>"><font style="font-style: italic ; color:green"><button class="t1">Login</button></font></a>
    
      </div>
     </div>  


</body>
</html>