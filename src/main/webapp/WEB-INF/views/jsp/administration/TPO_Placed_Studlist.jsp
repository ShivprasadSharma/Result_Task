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
.container {
  max-width: 960px;
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
			<c:set var="list" value="${placestudlist}"/>
			<c:set var="list1" value="${studinternlist}"/> 
			 <c:set var="list4" value="${futureinterestlist}"/> 
			  <c:set var="list2" value="${offcampusplace}"/>  
			<!-- <div class="row">
			 <div class="col-sm-10"> </div>
  			   	<div class="col-sm-2"> <button id="cmd" class="btn btn-success"><i class="fa fa-download"></i> PDF download</button></div><br>
  			  </div>  -->
			<!-- Info boxes -->
			    <div class="col-md-12 col-sm-12 " >
				    <div class="box">
				    
				    <div class="box-body" >
				    			
				    <div class="btn-pref btn-group btn-group-justified btn-group-lg" role="group" aria-label="...">
        <div class="btn-group" role="group">
            <button type="button" id="stars" class="btn btn-primary" href="#tab1" data-toggle="tab"><span class="glyphicon glyphicon-education" style="font-size:15px;" aria-hidden="true"></span>
                <div class="hidden-xs" style="font-size:14px;">On-Campus Placed Students</div>
            </button>
        </div>
        <div class="btn-group" role="group">
            <button type="button" id="favorites" class="btn btn-default" href="#tab2" data-toggle="tab"><span class="glyphicon glyphicon-education" style="font-size:15px;" aria-hidden="true"></span>
                <div class="hidden-xs" style="font-size:14px;">Off-Campus Placed Students</div>
            </button>
        </div>
        <div class="btn-group" role="group">
            <button type="button" id="intern" class="btn btn-default" href="#tab3" data-toggle="tab"><span class="glyphicon glyphicon-education" style="font-size:15px;" aria-hidden="true"></span>
                <div class="hidden-xs" style="font-size:14px;">Internship</div>
            </button>
        </div>
        <div class="btn-group" role="group">
            <button type="button" id="higherstudy" class="btn btn-default" href="#tab4" data-toggle="tab"><span class="glyphicon glyphicon-education" style="font-size:15px;" aria-hidden="true"></span>
                <div class="hidden-xs" style="font-size:14px;">Future Interest</div>
            </button>
        </div>
        </div>
    </div>
     <div class="well">
      <div class="tab-content">
      <div class="tab-pane fade in active" id="tab1" style="font-size:16px;color: #5e605f ">
        	   <table id="example" class="table table-bordered table-striped" >
						   <thead>
				                <tr class="success">
				                  <th style="width: 10px">#</th>
				                  <th style="width: 270px">Name of Student</th>
								  <th>Job Title</th>
				                  <th>Annual Package</th>
				                  <th>Company Name</th>
				                  
				                </tr>
				            </thead>
				            <tbody >
				            <c:set var="n" value="1"/>
				            <c:forEach var="x" items="${list}" >
				             <tr>
								<td style=min-width:50px>${n}</td>
								<td style=min-width:50px>${x.firstName} ${x.middleName} ${x.lastName}</td>
								<td style=min-width:50px>${x.jobtitle}</td>
								<td style=min-width:50px>${x.salary}</td>						
								<td style=min-width:50px>${x.companyName}</td>
					      </tr>
					      <c:set var='n' value="${n + 1}"/>
					</c:forEach>
					
				             </tbody>
			             </table>
        </div>
						          <div class="tab-pane fade in " id="tab2" >
						       			<div class="container">
												   <div class="accordion-option">
									 <table id="example" class="table table-bordered table-striped" >
						   <thead>
				                <tr class="success">
				                  <th style="width: 10px">#</th>
				                  <th style="width: 270px">Name of Student</th>
								  <th>Company Name</th>
				                  <th>Placed Date</th>
				                  <th>Annual Package</th>
				                </tr>
				            </thead>
				            <tbody >
				           <c:set var="n" value="1"/>
				            <c:forEach var="y" items="${list2}" >
				             <tr>
								<td style=min-width:50px>${n}</td>
								<td style=min-width:50px>${y.studname}</td>
								<td style=min-width:50px>${y.cmpnyname}</td>
								<td style=min-width:50px>${y.placdate}</td>						
								<td style=min-width:50px>${y.salary}</td>
					      </tr>
					      <c:set var='n' value="${n + 1}"/>
					</c:forEach>
				             </tbody>
			             </table>
									   </div>
								   </div>
								</div>
        <div class="tab-pane fade" id="tab3" > 
						       			<div class="container">
												   <div class="accordion-option">
									 <table id="example" class="table table-bordered table-striped" >
						   <thead>
				                <tr class="success">
				                  <th style="width: 10px">#</th>
				                  <th style="width: 270px">Name of Student</th>
								  <th>Company Name</th>
								  <th>Class Name</th>
				                  <th>Start Date</th>
				                  <th>End Date</th>
				                </tr>
				            </thead>
					<tbody >
				            <c:set var="n" value="1"/>
				            <c:forEach var="y" items="${list1}" >
				             <tr>
								<td style=min-width:50px>${n}</td>
								<td style=min-width:50px>${y.firstName} ${y.middleName} ${y.lastName}</td>
					            <td style=min-width:50px>${y.organizer}</td>
								<td style=min-width:50px>${y.className}</td>
								<td style=min-width:50px>${y.dateOfEvent}</td>						
								<td style=min-width:50px>${y.toDate}</td>
					      </tr>
					      <c:set var='n' value="${n + 1}"/>
					</c:forEach>
					
				             </tbody>
			             </table>
									   </div>
								   </div>
								</div>
									         <div class="tab-pane fade in " id="tab4" >
						       			<div class="container">
												   <div class="accordion-option">
									 <table id="example" class="table table-bordered table-striped" >
						   <thead>
				                <tr class="success">
				                  <th style="width: 10px">#</th>
				                   <th>Name Of Student</th>
				                    <th style="width: 100px">Department</th>
				                     <th>Class</th>
				                  <th style="width: 100px">Placement</th>
								  <th style="width: 100px">Higher Study</th>
				                  <th>Business</th>
				                 </tr>
				            </thead>
				            <tbody >
				            <c:set var="n" value="1"/>
				            <c:forEach var="z" items="${list4}" >
				             <tr>
								<td style=min-width:50px><b>${n}</b></td>
								<td style=min-width:50px><b>${z.comClientName.firstName} ${z.comClientName.middleName} ${z.comClientName.lastName}</b></td>
					           	<td style=min-width:50px><b>${z.dept}</b></td>
								<td style=min-width:50px><b>${z.className}</b></td>
					  			<td style=min-width:50px>  
					               <c:if test="${z.placement eq true}">
					      		   <b>Yes</b>
					      	   </c:if>
					      	    <c:if test="${z.placement eq false}">
					      		   <b>No</b>
					      	   </c:if>
					      	   </td>
					      	  
								<td style=min-width:50px>
								<c:if test="${z.higherstudy eq true}">
					      		   <b>Yes</b>
					      	   </c:if>
					      	    <c:if test="${z.higherstudy eq false}">
					      		   <b>No</b>
					      	   </c:if>
					      	   </td>
								
								<td style=min-width:50px>
								<c:if test="${z.bussiness eq true}">
					      		   <b>Yes</b>
					      	   </c:if>
					      	    <c:if test="${z.bussiness eq false}">
					      		   <b>No</b>
					      	   </c:if></td>						
					      </tr>
					      <c:set var='n' value="${n + 1}"/>
				            
					</c:forEach>
				             </tbody>
			             </table>
									   </div>
								   </div>
								</div>
								
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
<script type="text/javascript">
  $(document).ready(function() {
	  $(".btn-pref .btn").click(function () {
	      $(".btn-pref .btn").removeClass("btn-primary").addClass("btn-default");
	      // $(".tab").addClass("active"); // instead of this do the below 
	      $(this).removeClass("btn-default").addClass("btn-primary");   
	  });
	  });
  </script>
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-127607784-1"></script>
</body>

