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
		
		td, th {
		    border: 1px solid #dddddd;
		    text-align: left;
		    padding: 8px;
		}
		
		h2{
			text-align: center;
		}
	</style>
</head>
<body>
	<c:set var="courselist" value="${courses}"/>
		<table > 
		    <tr>
		    	     <th colspan="7">Under Graduate FE / SE / TE / BE / M.E. Students Details Academic Year 2018 - 19</th>   	
		    </tr>
		    <tr>
		    	     <th rowspan="2">Sr No</th>
		    	     <th rowspan="2">Name Of Course</th>
		    	     <th colspan="5">Sanctioned Intake </th>  	
		    </tr>
		    <tr>  	
		    		<th >1st yr</th>
		    		<th >2nd yr</th>
		    		<th >3rd yr</th>
		    		<th >4th Yr</th>
		    		<th >Total</th>
		    </tr>
		    
		    <c:forEach var="courses" items="${courselist}">
			   <c:set var="nos" value="${nos + 1}"></c:set>
			    <c:if test="${not fn:startsWith(courses.courseName,'ME') }">
			        <tr>
			    			<td >${nos}</td>
			    			<td >${courses.courseName}</td>
			    			<td >${courses.FE}</td>
			    			<td >${courses.SE}</td>
			    			<td >${courses.TE}</td>
			    			<td >${courses.BE}</td>
			    			<td >${courses.FE + courses.SE + courses.TE + courses.BE}</td>
			    		</tr>
			     </c:if>
			</c:forEach>
		    <tr>
		    		<th colspan="5">Post Graduate Students Details Academic Year 2018 - 19</th>
		    </tr>
		    <tr>
			   <th rowspan="2" >Sr No</th>
			   <th rowspan="2" >Name Of Course</th>
			   <th colspan="7">Sanctioned Intake </th>
			 </tr>
			 <tr>
			   	<td colspan="2">1st</td>
			    	<td colspan="2">2nd</td>
			    	<td colspan="2">Total</td>
			  </tr>
			  <c:forEach var="courses" items="${courselist}">
			    <c:set var="no" value="${no + 1}"></c:set>
			     <c:if test="${fn:startsWith(courses.courseName,'ME') }"> 	 
			    	    <tr>
				    		<td rowspan="2" >${no}</td>
				    		<td rowspan="2" >${courses.courseName}</td>    		
				    </tr>
				     <tr>
				   	 	<td colspan="2">${courses.FE}</td>
				    		<td colspan="2">${courses.SE}</td>
				    		<td colspan="2">${courses.FE + courses.SE}</td>
				    	 </tr>
				 </c:if>
			  </c:forEach>
		</table>
	</body>
</html>

