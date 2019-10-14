<%@page import="javax.naming.NoInitialContextException"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>


   <!--  <script type="text/javascript" src="https://code.jquery.com/jquery-3.0.0.min.js"></script> -->
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jspdf/1.2.61/jspdf.min.js"></script>
<style>
<!--
.delete{border: none;
  color: white;
  
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 12px;
  margin: 1px 1px;
  cursor: pointer;
  border-radius: 3px;background-color: #BD1010;
  }
-->
</style>
<body class="hold-transition skin-blue sidebar-mini" >
<div class="wrapper ">
 <div class="content-wrapper myStyle" >
  <c:set var="n" value="0"/>
    <!-- Main content -->
    <section class="content " >
         <div class="col-md-12 ">
		    <section class="content-header">
				<div class="col-md-12 col-sm-12 col-lg-12">
				</div>
			</section >
			<c:set var="list" value="${StudentList}"/>
						<c:set var="dlist" value="${departments}"/>
						<!-- <div class="row">
			
			<!-- Info boxes -->
			    <div class="col-md-12 col-sm-12 " >
				    <div class="box">
				    <div class="box-body" >
				    <table id="example1" class="table table-bordered table-striped" >
						   <thead>
				                <tr class="success">
				                  <th style="width: 10px">#</th>
				                  <th style="width: 270px">Name of Student</th>
				                  <th>Year</th>
				                  <th>Department</th>
				                  <th>Email</th>
				                  <th>Contact No</th>
				                </tr>
				            </thead>
				            <tbody >
				                <c:forEach var="info" items="${list}" >
				                     <tr>
				                       <td>${n + 1}</td>
				                       <td><b><a href="<c:url value="/web/taskforce/service/result/menterstudprof/${info.id}"/>">${info.firstName} ${info.middleName} ${info.lastName}</a></b></td>
				                       <td>
				                       <c:choose>
	                                               <c:when test="${info.year_id eq 4}">
	                                                  BE
	                                              	</c:when>
	                                              <c:when test="${info.year_id eq 2}">
												     SE
												    </c:when>
												  <c:when test="${info.year_id eq 3}">
												      TE
												  </c:when>
												  <c:when test="${info.year_id eq 1}">
												     FE
												  </c:when>
											</c:choose>
										</td>
				                       <td>
				                     	  
				                     	  
				                     	   <c:forEach var="y" items="${dlist}" >
				                       		<c:if test="${info.depId == y.dep_id}">
							  			  	 ${y.dep_name}
							           		 </c:if>
				                       </c:forEach>
				                       </td>
				                       <td>
				                     	  <b>${info.emailId}</b>
				                       </td>
				                		 <td>
				                     	  <b>${info.contactNos}</b>
				                       </td>
				                     </tr>
				                  <c:set var='n' value="${n + 1 }"/>
				                </c:forEach>
				                
				             </tbody>
			             </table>
			           
			            </div>
				    </div>
		       </div>
			</div>
			
	  </section>
	    
	<!-- /Main Content -->
  </div>
</div>
<script>
  $(function () {
    //$("#example1").DataTable();
    $('#example1').DataTable( {
        scrollY:        '57vh',
        scrollCollapse: true,
        paging:         false
    } );
  });
  
  </script>
 
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-125893256-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-125893256-1');
</script>
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-127607784-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-127607784-1');
</script>
</body>
