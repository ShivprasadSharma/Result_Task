<%@page import="javax.naming.NoInitialContextException"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
<c:url var="addoffcampusInfo" value="/web/taskforce/serives/offcampusplace/stud" />


   <!--  <script type="text/javascript" src="https://code.jquery.com/jquery-3.0.0.min.js"></script> -->
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jspdf/1.2.61/jspdf.min.js"></script>
<style>
input[type=text], input[type=email], input[type=date], input[type=number],
	select, textarea {
	width: 100%;
	padding: 4px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 5px;
	resize: vertical;
	background-color: #f2f2f2;
}
.btnn {
  border: 2px solid black;
  background-color: white;
  color: black;
  padding: 8px 25px;
  font-size: 15px;
  cursor: pointer;
  border-radius: 1px;
}

/* Green */
.success {
  border-color: #4CAF50;
  color: green;
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
				<br>
				</div>
			</section >
			
			<!-- Info boxes -->
			    <div class="col-md-12 col-sm-12 " >
				    <div class="box">
				    <div class="box-body" >
	                      
	                      <form action="${addoffcampusInfo}" method="POST" modelAttribute="offcampusplace" enctype="multipart/form-data">

									<div class="row">
										<div class="col-sm-4"></div>
										<div class="col-sm-5">
											<b style="color: green;font-size: 20px; ">Add Off Campus Placed Student </b>
										</div>
										<div class="col-sm-4"></div>
									</div><br>
								 <div class="row">
								 <div class="col-sm-1"></div>
										<div class="col-sm-2">
											<b>Select Student :</b>
										</div>
										<div class="col-sm-6">
										<select    name="studname"
												class=" select2 " data-placeholder="Select Student .."
												style="width: 100%">
										 <c:forEach var="item" items="${studlist}">
								                      <option class="yearoption" value="${item.firstName} ${item.middleName} ${item.lastName}">${item.firstName} ${item.middleName} ${item.lastName}</option>
								               </c:forEach>
								               </select>
												
										</div>
										</div><br>

									<div class="row">
									<div class="col-sm-1"></div>
										<div class="col-sm-2">
											<b>Name of Compny :</b>
										</div>
										<div class="col-sm-6">
											<input type="text" id="" name="cmpnyname" required="required" placeholder="Enter Name of Company..">
										</div>
									</div>	<br>	
									
									<div class="row">
									<div class="col-sm-1"></div>
										<div class="col-sm-2">
											<b>Placed Date :</b>
										</div>
										<div class="col-sm-3">
											<div class="form-group">

											<div class='input-group date' id='datetimepicker1'>
												<input type='text' name="placdate" class="form-control"
													required="required" id="datemain" placeholder="select date" />
												<span class="input-group-addon"> <span
													class="glyphicon glyphicon-calendar"></span>
												</span>
											</div>
										</div>
										</div>
										</div>    
								    	<div class="row">
								    	<div class="col-sm-1"></div>
								   					 <div class="col-sm-2">
															<b> Salary Offering :</b>
														</div>
														<div class="col-sm-4">
															<input type="text" name="salary" required="required"
																placeholder="eg.3.6 PLA"> 
														</div>
													</div>
								   <br><br>
									 <div class="row">
									 <div class="col-sm-5"></div>
										<input type="submit"  class="btnn success" value="submit.">
									</div>
				    </form>
				    
			           
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
  
  
  $(function() {
		$(".select2").select2();
	});
  
  $(function() {
		$('#datetimepicker1').datetimepicker();

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

