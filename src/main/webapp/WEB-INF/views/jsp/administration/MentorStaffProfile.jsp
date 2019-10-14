<%@page import="javax.naming.NoInitialContextException"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jspdf/1.2.61/jspdf.min.js"></script>


<style>

.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */.
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color:white; /* Fallback color */
	background-color:white; /* Black w/ opacity */
	
}

/* Modal Content */
.modal-content {
	background-color:white;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	margin-left: 25%;
	width: 60%;
	border-radius: 10px;
	border-bottom-color: #006400;
	border-bottom-width: 5px;
}


.closegraph1:hover, .closegraph1:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}
.well
{
background-color:white;
}

</style>
<body class="hold-transition skin-blue fixed sidebar-mini" >
<div class="wrapper">
<div class="content-wrapper">
  <c:set var="n" value="0"/>
    <!-- Main content -->
    <section class="content" >
         <div class="col-md-12" style="margin-top:21px">
		    <section class="content-header">
				<div class="col-md-12 col-sm-12 col-lg-12">
				</div>
			</section>
			<c:set var="list" value="${mentprof}"/>
			
			<!-- Info boxes -->
			    <div class="col-md-12 col-sm-12 " >
				    <div class="box">
				    
		 <div class="well">
      <div class="tab-content">
      <div class="tab-pane fade in active" id="tab1" style="font-size:16px;color: #5e605f ">		    
				 <table id="example1" class="table table-bordered table-striped" >
						   <thead>
				                <tr>
				                  <th style="width: 10px">#</th>
				                  <th style="width: 110px">Emp Id</th>
				                  <th>Name</th>
				                  <th style="width: 210px">Contact Number</th>
				                  <th>Email ID</th>
				                </tr>
				            </thead>
				            <tbody >
				             
				             <c:forEach var="x" items="${mentprof}" >		
				             	      <c:set var="n" value="${n + 1}"/>
					                    	
				                         <tr>
				                         <td>${n}</td>
				                         <td>${x.staff.employeeNo}</td>
				                          <td><b><a href="<c:url value="/web/taskforce/service/staffprofile/${x.staff.comClientName.id}"/>">${x.staff.comClientName.firstName}${x.staff.comClientName.lastName}</a></b></td>
				                         <td>${x.staff.comClientName.contactNos}</td>
				                         <td>${x.staff.comClientName.emailId}</td>
				                         </tr>
				                       
					                     
				                         </c:forEach>
				                         	
					                 </tbody>
			             </table>
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

</body>
