
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Page Not Found !</title>
</head>
	<body style="background-color:#d3efc6">
	   <div class="error-content" style="padding-top: 200px">
	      <h3 align="center">
	          <c:if test="${not empty msg}">
	               ${msg}
	          </c:if>
	      </h3>
	      <h3 align="center"><i class="fa fa-warning text-yellow"></i><font style="color:#FFFF00" size="15"> 404 </font> Oops! Page not found.</h3>
		  <p align="center">
	            We could not find the page you were looking for.
	      </p>
	           <h4 align="center"><a href="<c:url value="/web/taskforce/service/notification/list" />"> <font style="color:green" size="4"> Go to home </font></a></h4>
	   </div>
	</body>
