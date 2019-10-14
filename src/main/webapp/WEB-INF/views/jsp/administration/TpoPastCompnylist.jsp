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
<body class="hold-transition skin-blue fixed sidebar-mini" >
<div class="wrapper">
<div class="content-wrapper">
  <c:set var="n" value="0"/>
    <!-- Main content -->
    <section class="content " >
         <div class="col-md-12 ">
		    <section class="content-header">
				<div class="col-md-12 col-sm-12 col-lg-12">
				 <span class="pull-left" ><font size="5">Past Drive List </font></span>
				</div>
			</section >
			<c:set var="plist" value="${pastcompny}"/>
			
			<!-- Info boxes -->
			
			    <div class="col-md-12 col-sm-12 " >
				    <div class="box">
				    <div class="box-body" >
				    
					   <table id="example1" class="table table-bordered table-striped" >
						   <thead>
				                <tr>
				                  <th style="width: 10px">#</th>
				                  <th style="width: 20%">Compny Name :</th>
				               	  <th style="width: 25%">Job Title :</th>
				                  <th style="width: 15%"> Salary Offering</th>
				                  <th>Date Time:</th>
				                  <th>Venue</th>
				                  <th>Details</th>
				                </tr>
				            </thead>
				            <tbody >
				                <c:forEach var="x" items="${plist}" >
				                     <tr>
				                      <c:set var='n' value="${n+1}"/> 
				                       <td>${n}</td>
 				                       <td><a href="#"> ${x.companyName}</a></td>
 				                       <td>${x.jobtitle}</td>
 				                       <td>${x.salary}</td>
 				                        <td><fmt:formatDate pattern = "yyyy-MM-dd" 
                                               value = "${x.dateInfo}" /> ${x.time}</td>
 				                         <td>${x.venue}</td>
 				                       <td> 
 				                       <input type="button" class="btnn success" value="View" onclick="window.location.href='<c:url value="/web/taskforce/service/compny/${x.reInfoId}"/>' "/>
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
