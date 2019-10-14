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
  padding: 3px 25px;
  font-size: 12px;
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


.btnn1 {
  border: 2px solid black;
  background-color: white;
  color: black;
  padding: 3px 25px;
  font-size: 12px;
  cursor: pointer;
  border-radius: 5px;
}

/* Green */
.success1 {
  border-color: #d14b4b;
  color: red;
}

.success1:hover {
  background-color: #d14b4b;
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
			<c:set var="list" value="${studlist}"/>
			<c:set var="list1" value="${dept}"/>
			<!-- Info boxes -->
			<h4>Student Verification</h4>
			
			    <div class="col-md-12 col-sm-12 " >
				    <div class="box">
				    <div class="box-body" >
				    <table id="example1" class="table table-bordered table-striped" >
				    
					   <thead>
				                <tr>
				                  <th style="width: 10px">#</th>
				                  <th style="width: 27%">Name</th>
				                  <th>Branch</th>
				                  <th>Year</th>
				                  <th>Active/Deactive</th>
				                </tr>
				            </thead>
					  <tbody >
				                <c:forEach var="x" items="${list}" >
				                     <tr>
				                      <c:set var='n' value="${n+1}"/> 
				                       <td>${n}</td>
 				                       <td><b>${x.comClientName.firstName} &nbsp;&nbsp;${x.comClientName.middleName} &nbsp;&nbsp;${x.comClientName.lastName}</b></td>				                      <td>
				                       		 <c:forEach var="y" items="${list1}" >
				                         		<c:if test="${x.branch == y.dep_id}">
	  			  	                                <span>${y.dep_name}</span>
	           		                       		</c:if>
				                    		 </c:forEach>
				                       </td>
				                       <td>
				                       		<c:if test="${x.year == 1}">
	  			  	                                 <span>FE</span>
	           		                       	</c:if>
				                            <c:if test="${x.year == 2}">
	  			  	                                 <span>SE</span>
	           		                       	</c:if>
	           		                       	<c:if test="${x.year == 3}">
	  			  	                                 <span>TE</span>
	           		                       	</c:if>
	           		                       	<c:if test="${x.year == 4}">
	  			  	                                 <span>BE</span>
	           		                       	</c:if>
				                       </td>
				                        <td> 
 				                       <input type="button" class="btnn success" value="Accept" onclick="window.location.href='<c:url value="/web/taskforce/activation/active_status/${x.comClientName.id}"/>' "/>&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;
 				                       <input type="button" class="btnn1 success1" value="Decline" onclick="window.location.href='<c:url value="/web/taskforce/activation/deactive_status/${x.comClientName.id}"/>' "/>
 				                       
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

