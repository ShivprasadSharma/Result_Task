<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%><html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<head>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 96%;
    border: 1px solid #dddddd;
    margin: 15px;
    
}
tr:nth-child(even) {background: #EBF5FB}
tr:nth-child(odd) {background: #B2BABB}
td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}
</style>
</head>
<body>
<c:set var="placementinfo" value="${placestudentlist}"/>
 <table> 
  <h3 style="text-align: center" ><font color="#5F6A6A" >Placement</font> </h3>
    <tr>
    	     <th>Year</th>
    	     <th>Student Name</th>
    	     <th>Name of the employer</th>  
    	     <th>Package received</th>
    	     <th>Program graduated from</th>  	
    </tr>
    <c:forEach var="placestudent" items="${placementinfo }">
    <tr>  	
    		<td >${placestudent.yr }</td>
    		<td >${placestudent.noOfStudentPlace}</td>
    		<td >${placestudent.employeer.employerName }</td>
    		<td >${placestudent.pkg}</td>
    		<td >${placestudent.branch.dep_name}</td>
    </tr>       
    </c:forEach>
  </table>
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

