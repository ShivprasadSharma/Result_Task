<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<style type="text/css">
p{
    text-indent: 90px;
    text-align: justify;
    letter-spacing: 3px;
}
table{
   margin:10px;
}
</style>
</head>
  <body bgcolor="#F4F6F7">
     <h2 align="center" style="color:#78909C">${aboutus.value}</h2>
         <c:if test="${not empty aboutus.url}" >
			 <p><img  src="${aboutus.url}" width="250px" height="250px"/></p>
		 </c:if>
	 <c:forEach var="dtl" items="${aboutus.comListDetails}">
          <table >
	         <tr>
	             <th><h3 align="left">${dtl.value}</h3><th>
	         </tr>
	         <tr>
	             <td ><p><i>${dtl.dtlDescription}</i></p></td>
	         </tr>
          </table> 
      </c:forEach>   
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