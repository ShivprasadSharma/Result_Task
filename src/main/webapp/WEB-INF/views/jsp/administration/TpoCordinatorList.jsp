<%@page import="javax.naming.NoInitialContextException"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
			<!-- Info boxes -->
			    <div class="col-md-12 col-sm-12 " >
			    <c:set var="colist" value="${deptcoordinatorList}"/>
			  	 <p><font size="4px"><b>Departmental TPO List</b></font></p>
				    <div class="box">
				    <div class="box-body" >
					    <table class="table table-bordered table-striped"  >
					     <tr class="success">
					        <th style="width: 50px;">#</th>
					        <th> Coordinator Name</th>
					        <th> Department </th>
					        <th> Email </th>
					        <th> Activity </th>
					     </tr>
					      <c:forEach var="c" items="${colist}" >
					       <tr>
					          <c:set var='n' value="${n+1}"/> 
					   		<td>${n}</td>
					           <td><b><a href="<c:url value="/web/taskforce/service/staffprofile/${c.comClientName.id}"/>">${c.comClientName.firstName}&nbsp; ${c.comClientName.lastName}</a></b></td>
					             <td>
					         	 <c:forEach var="y" items="${dlist}" >
					                <c:if test="${c.dep_id == y.dep_id}">
								  		${y.dep_name}
								      </c:if>
					                 </c:forEach>
					               </td>
					      	  <td> ${c.comClientName.emailId}</td>
					        <td> <button class="btn btn-success"> log</button> </td>
					     </tr>
					    </c:forEach>
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
