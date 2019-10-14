<%@page import="javax.naming.NoInitialContextException"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>

input[type=text], input[type=email], input[type=date], input[type=number],
	select, textarea {
	width: 100%;
	padding: 4px;
	border: 1px solid #ccc;
	border-radius: 2px;
	box-sizing: border-box;
	margin-top: 5px;
	resize: vertical;
	background-color: #f2f2f2;
}

.new5 {
   height: 2px;
   color: yellow;
   background-color: yellow;	
  }	
  
  .btnn {
  border: 2px solid black;
  background-color: #d9f1cf;
  color: black;
  padding: 7px 20px;
  font-size: 15px;
  cursor: pointer;
  border-radius: 1px;
  box-shadow: 0.3em 0.3em 0.3em rgba(0, 0, 0, 0.2);
}

/* Green */
.success {
  border-color: #4CAF50;
  color: black;
}

.success:hover {
 border-color: black;
  background-color: #4CAF50;
  color: white;
  }
  
.dd{

  box-shadow: 1em 1em 1em rgba(0, 0, 0, 0.2);

}


  
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
			<c:set var="dlist" value="${departments}"/>
			<c:set var="list" value="${stafflist}"/>
			<!-- Info boxes -->
			    <div class="col-md-12 col-sm-12 " >
			   
				    <div class="box">
				    <div class="box-body" >
				    
				    	 <div class="btn-pref btn-group btn-group-justified btn-group-lg" role="group" aria-label="...">
							       
							       <c:set var="li" value="${stafflist[0]}"/>
							             <c:set var="ss" value="${stafflist[1]}"/>							         
							         <div class="btn-group" role="group">
							             <a href="<c:url value="/web/taskforce/stafflist/1"/>">
							             <c:choose>
							            
									   		 <c:when test="${ss.comClientName.comUserDetails.userRole == 'ROLE_HOD'}">
							             		<button type="button" class="btn btn success" style="border-radius: 0px;background-color: #4CAF50;color:white;"><span class="fa fa-users" style="font-size:15px;" ></span>
							            			<div  style="font-size:14px;">
							             		   Head Of Department </div>
							            		</button>
							            </c:when>
							           		 <c:otherwise>
										             <button type="button" class="btn btn success" style="border-radius: 0px;"><span class="fa fa-users" style="font-size:15px;" ></span>
										            <div  style="font-size:14px;">
										                Head Of Department </div>
										            </button>
							            	</c:otherwise>
							            </c:choose>
							            </a>
							        </div>
							         <div class="btn-group" role="group">
							             <a href="<c:url value="/web/taskforce/stafflist/2"/>">
							               <c:choose>
									   		 <c:when test="${li.comClientName.comUserDetails.userRole == 'ROLE_TEACHER'}">
							             		<button type="button" class="btn btn success" style="border-radius: 0px;background-color: #4CAF50;color:white;"><span class="fa fa-users" style="font-size:15px;" ></span>
							            			<div  style="font-size:14px;">
							             		   Teaching Staff </div>
							            		</button>
							            </c:when>
							           		 <c:otherwise>
										             <button type="button" class="btn btn success" style="border-radius: 0px;"><span class="fa fa-users" style="font-size:15px;" ></span>
										            <div  style="font-size:14px;">
										                Teaching Staff </div>
										            </button>
							            	</c:otherwise>
							            </c:choose>
							             </a>
							        </div>
							         <div class="btn-group" role="group">
							             <a href="<c:url value="/web/taskforce/stafflist/4"/>">
							              <c:choose>
									   		 <c:when test="${li.comClientName.comUserDetails.userRole == 'ROLE_ADMIN'}">
									   		 <button type="button" class="btn btn success" style="border-radius: 0px;background-color: #4CAF50;color:white;"><span class="glyphicon glyphicon-user" style="font-size:15px;" ></span>
							            <div  style="font-size:14px;">
							               
							               Administration </div>
							            </button>
							            </c:when>
							            <c:otherwise>
							             <button type="button" class="btn btn success" style="border-radius: 0px;"><span class="glyphicon glyphicon-user" style="font-size:15px;" ></span>
							            <div  style="font-size:14px;">
							               
							               Administration </div>
							            </button>
							            </c:otherwise>
							            </c:choose>
							            </a>
							        </div>
							        <div class="btn-group" role="group">
							             <a href="<c:url value="/web/taskforce/stafflist/6"/>">
							              <c:choose>
									   		 <c:when test="${li.comClientName.comUserDetails.userRole == 'ROLE_IT'}">
									   		 <button type="button" class="btn btn success" style="border-radius: 0px;background-color: #4CAF50;color:white;"><span class="glyphicon glyphicon-user" style="font-size:15px;" ></span>
							            <div  style="font-size:14px;">
							               
							                 IT Support </div>
							            </button>
							            </c:when>
							            <c:otherwise>
							             <button type="button" class="btn btn success" style="border-radius: 0px;"><span class="glyphicon glyphicon-user" style="font-size:15px;" ></span>
							            <div  style="font-size:14px;">
							               
							                 IT Support  </div>
							            </button>
							            </c:otherwise>
							            </c:choose>
							            </a>
							        </div>
							        
							        <div class="btn-group" role="group">
							             <a href="<c:url value="/web/taskforce/service/Admin_details"/>"><button type="button" class="btn btn success"style="border-radius: 0px;" ><span class="fa fa-user-plus" style="font-size:15px;" ></span>
							            <div  style="font-size:14px;">
							               
							                Add New User</div>
							            </button></a>
							        </div>
							        
							        <div class="btn-group" role="group">
							             <a href="<c:url value="/web/taskforce/services/add"/>"><button type="button" class="btn btn success"style="border-radius: 0px;" ><span class="fa fa-file-excel-o" style="font-size:15px;" ></span>
							            <div  style="font-size:14px;">
							               
							                Import Users Excel</div>
							            </button></a>
							        </div>
							    </div>
							    <hr class="new5">
					   <table id="example1" class="table table-bordered table-striped" >
							   <thead>
					                <tr>
					                  <th style="width: 10px">#</th>
					                  <th>Name</th>
					                  <th style="width: 210px">Department</th>
					                  <th>Email ID</th>
					                  <th>Role</th>
					                  <th>Delete Staff</th>
					                </tr>
					            </thead>
				            <tbody >
				                <c:forEach var="x" items="${list}" >
				                     <tr>
				                       <td>${n + 1}</td>
				                       <td><b><a href="<c:url value="/web/taskforce/service/staffprofile/${x.comClientName.id}"/>">${x.comClientName.firstName} ${x.comClientName.lastName}</a></b></td>
				                       
				                      <%--  <td>${x.comClientName.firstName} ${x.comClientName.lastName}</td> --%>
				                       <td>
				                       <c:forEach var="y" items="${dlist}" >
				                       		<c:if test="${x.department == y.dep_id}">
							  			  	 ${y.dep_name}
							           		 </c:if>
				                       </c:forEach>
				                       </td>
				                       <td>${x.comClientName.emailId}</td>
				                       <td>${x.comClientName.comUserDetails.userRole}</td>
				                       <td>
				                              &nbsp;&nbsp;&nbsp;&nbsp;
				                               <a href="<c:url value='/web/taskforce/serives/staff/delete/${x.comClientName.id}/${x.department}'/>"
													onclick="return confirm('Are You Sure You Want To Delete This Staff ?')"> <button class="button button4 delete" style="background-color: red;color:black ">Delete</button></a>            
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
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-127607784-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-127607784-1');
</script>
</body>
