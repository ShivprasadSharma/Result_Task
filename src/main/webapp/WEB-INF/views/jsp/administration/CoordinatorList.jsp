<%@page import="javax.naming.NoInitialContextException"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url var="addcoordinatorDetails" value="/web/taskforce/service/assigncoordinator/save" />
<script type="text/javascript" src="//cdn.rawgit.com/MrRio/jsPDF/master/dist/jspdf.min.js">
    </script>
    <script type="text/javascript" src="//cdn.rawgit.com/niklasvh/html2canvas/0.5.0-alpha2/dist/html2canvas.min.js">
    </script>
<style>


.btnn {
  border: 2px solid black;
  background-color: white;
  color: black;
  padding: 4px 120px;
  font-size: 15px;
  cursor: pointer;
  border-radius: 5px;
}

/* Green */
.success {
  border-color: #4CAF50;
  color: green;
}
input[type=submit], input[type=reset] ,input[type=button]{
	  background-color: #4CAF50;
	  color: white;
	  padding: 6px 15px;
	  border: none;
	  border-radius: 4px;
	  cursor: pointer;
	  width:110px;
	  font-size: 12px;
}

.success:hover {
  background-color: #4CAF50;
  color: white;
}
</style>
<body class="hold-transition skin-blue fixed sidebar-mini" >
<div class="wrapper">
<div class="content-wrapper">
  <c:set var="n" value="0"/>
    <!-- Main content -->
    <section class="content " >
         <div class="col-md-12 ">
		    <section class="content-header">
				<div class="col-md-12 col-sm-12 col-lg-12">
				 <div class="box-body">
	       <span class="pull-left" ><font size="5">Add Coordinator </font></span>
	      
	    </div>
				</div>
			</section >
			<c:set var="dlist" value="${departments}"/>
			
			<c:set var="list" value="${allstaff}"/>
			
			<c:set var="deptstaff" value="${stafflistbydept}"/>
			
			<!-- Info boxes -->
			
			    <div class="col-md-12 col-sm-12 " >
				    <div class="box">
				    <div class="box-body" >
				    
				 <form action="${addcoordinatorDetails}" method="post">
				  <c:choose>
				  	<c:when test="${sessionScope.user.comClientName.comUserDetails.userRole =='ROLE_TPO'}">
								    <div class="col-sm-12">
								     <div class="col-sm-6">
									      <label class="control-label" style="color:#008000" >Coordinator Type:</label>
										  <select  name="co_type" class="form-control  select2" >
												  <option value="4">TPO Coordinator</option>
										  </select> <br><br>
										  </div>
							
									      <div class="col-sm-6">
										    <label class="control-label" style="color:#008000" for="value" >Select Staff:</label>
								             <select class="form-control select2" name="client_id">
								                <c:forEach var="x" items="${list}" >
												 <option  value="${x.comClientName.id}">${x.comClientName.firstName}  &nbsp;${x.comClientName.middleName} &nbsp;${x.comClientName.lastName}<hr></option>								 
											    
											   </c:forEach> 
											  </select>  
										  </div>
									</div>	
						</c:when>
						 <c:otherwise>
						  <div class="col-sm-12">
								     <div class="col-sm-6">
									      <label class="control-label" style="color:#008000" >Coordinator Type:</label>
										  <select  name="co_type" class="form-control  select2" >
												  <option value="1">Academic Coordinator</option>
												   <option value="2">Project Coordinator</option>
												    <option value="3">GFM Coordinator</option>
												     <option value="4">Dept.TPO Coordinator</option>
												      <option value="5">Dept Examination</option>
												       <option value="6">Seminar Coordinator</option>
												        <option value="7">Admin Coordinator</option>
												         <option value="8">Triple-I(III) Coordinator</option>
										  </select> <br><br>
										  </div>
							
									      <div class="col-sm-6">
										    <label class="control-label" style="color:#008000" for="value" >Select Staff:</label>
								             <select class="form-control select2" name="client_id">
								                <c:forEach var="y" items="${deptstaff}" >
												 <option  class="form-control select2 studLIstID" value="${y.comClientName.id}">${y.comClientName.firstName}  &nbsp;${y.comClientName.middleName} &nbsp;${y.comClientName.lastName}<hr></option>								 
											   </c:forEach> 
											  </select>  
										  </div>
									</div>	
						 </c:otherwise>
						
					</c:choose>
				 <c:set var="list1" value="${stafflistbydept[1]}"/>
			     <input type="hidden" name="dept_id" value="${list1.department}"> 
					
					
					 <div class="col-sm-12">
					  <div class="col-sm-5"></div>
					   <div class="col-sm-2">
						&nbsp;&nbsp;<input type="submit" style="background-color:green" value="Submit" class="btn ">
						</div> 
						 <div class="col-sm-5"></div>
						 <br>
						 <br>
						 <br>
					</div>					
					</form>
					   <!-- <div class="row">
  			  	 <div class="col-sm-10"> </div>
  			   	<div class="col-sm-2"> <button id="cmd" class="buttn buttn1"><i class="fa fa-download"></i> PDF</button></div><br>
  			    </div> -->
					 <c:set var="colist" value="${coordinatorList}"/>
					 <div class="col-sm-12">
						  <div class="col-sm-12">
		       <table class="table table-bordered table-striped"  >
				 
				     <tr class="success">
				        <th style="width: 50px;">#</th>
				        <th>Coordinator Name</th>
				        <th>Department</th>
				        <th> Coordinator Type </th>
				         <th> Activity </th>
				     </tr>
				   <c:forEach var="c" items="${colist}" >
				    <tr>
				     <c:set var='n' value="${n+1}"/> 
				   		<td>${n}</td>
				   		<td>${c.comClientName.firstName}&nbsp; ${c.comClientName.lastName}</td>
				   		<td>
				   		 <c:forEach var="y" items="${dlist}" >
				                       		<c:if test="${c.dep_id == y.dep_id}">
							  			  	 ${y.dep_name}
							           		 </c:if>
				                       </c:forEach>
				   		</td>
				   		<c:if test="${c.co_type eq 1}">
				   		<td>Academic Coordinator</td>
				   		</c:if>
				   		<c:if test="${c.co_type eq 2}">
				   		<td>Project Coordinator</td>
				   		</c:if>
				   		<c:if test="${c.co_type eq 3}">
				   		<td>GFM Coordinator</td>
				   		</c:if>
				   		<c:if test="${c.co_type eq 4}">
				   		<td>TPO Coordinator</td>
				   		</c:if>
				   		<c:if test="${c.co_type eq 5}">
				   		<td>Dept Examination</td>
				   		</c:if>
				   		<c:if test="${c.co_type eq 6}">
				   		<td>Seminar Coordinator</td>
				   		</c:if>
				   		<c:if test="${c.co_type eq 7 }">
				   		<td>Admin Coordinator</td>
				   		</c:if>
				   		<c:if test="${c.co_type eq 8}">
				   		<td>Triple-I(III) Coordinatorr</td>
				   		</c:if>
				  		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="<c:url value="/web/taskforce/service/removecoordinator/${c.comClientName.id}/${c.co_id}"/>"><i style="color:red;" class="fa fa-trash"></i></a></td>
				   
			<%-- 	    <td>${sessionScope.user.comClientName.comUserDetails.userRole}</td>  --%>
				   </tr>
				   </c:forEach>
			    </table> 
					
					</div>
					</div> 
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
<script>
 $(function () {
   $(".select2").select2();
 });	 
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