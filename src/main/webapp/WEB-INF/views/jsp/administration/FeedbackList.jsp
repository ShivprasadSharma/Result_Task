<%@page import="javax.naming.NoInitialContextException"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>


.btnn {
  border: 2px solid black;
  background-color: white;
  color: black;
  padding: 5px 25px;
  font-size: 15px;
  cursor: pointer;
  border-radius: 5px;
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
			<c:set var="list" value="${feedbacklist}"/>
			<!-- Info boxes -->
			
			    <div class="col-md-12 col-sm-12 " >
				    <div class="box">
				    <div class="box-body" >
					   <table id="example1" class="table table-bordered table-striped" >
						   <thead>
				                <tr>
				                  <th style="width: 10px">#</th>
				                  <th style="width: 30%">Staff Name</th>
				                  <th>Subject Name</th>
				                  <th>View Feedback</th>
				                </tr>
				            </thead>
				            <tbody >
				                <c:forEach var="x" items="${list}" >
				                     <tr>
				                      <c:set var='n' value="${n+1}"/> 
				                       <td>${n}</td>
 				                       <td><a href="#">${x.comClientName.firstName} ${x.comClientName.lastName}</a></td>
 				                       <td>${x.subject_name}</td>
 				                       <td> 
 				                       <input type="button" class="btnn success" value="View" onclick="window.location.href='<c:url value="/web/taskforce/feedback/feedback/${x.comClientName.id}/${x.semister}"/>' "/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 				                       <input type="button" class="btnn success" value="Graph" onclick="window.location.href='<c:url value="/web/taskforce/feedback/feedbackchart/${x.comClientName.id}/${x.semister}"/>' "/>
 				                       </td>
				                     </tr>
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
