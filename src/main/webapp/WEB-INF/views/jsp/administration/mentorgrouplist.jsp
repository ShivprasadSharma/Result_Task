<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="javax.naming.NoInitialContextException"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body class="hold-transition skin-blue fixed sidebar-mini" >
<div class="wrapper ">
  <div class="content-wrapper">
    <!-- Main content -->
    <section class="content" >
		   <div class="col-md-9 col-sm-9">
		       <section class="content-header">
				      <div class="col-md-12 col-sm-12 col-lg-12">
					      <div class=" col-md-9 col-sm-9 col-lg-9" style="padding-top: 7px;">  
 							<font size="3px"><b>GFM Group</b></font>
 						  </div>
					 </div>
               </section >
              <br><br>
              <section>  
              <c:set var="grouplist" value="${grouplist}"></c:set>
                 <div style="height:470px;overflow-x:hidden ">
              		<c:forEach var="Sitem" items="${grouplist}">
              		   <div class="box">
              		   
	              		 <c:set var="count" value="0"></c:set>
                             <div class="box-body" >             
                             
                             <%-- <h6>${grouplist}</h6> --%>
                                      
	   							   <span><h5 style="color: green;"> <b >
						  			  	   <c:set var='n' value="${n+1}"/>
						  			  	       ${n} GFM GROUP<b class="pull-left:30px ">${Sitem.mgroupname}</b> 
						  			  	       
						  			  	   <b class="pull-right "> Head of Mentee : ${Sitem.staff.comClientName.firstName}${Sitem.staff.comClientName.lastName} 
							   			 </b></b></h5>
							   				
							   				   <a class="pull-right" style="color: red; width: 30px;margin-top: 10px;margin-left: 10px"
													href="<c:url value='/web/taskforce/serives/mentor/group/delete/${Sitem.id}/${Sitem.staff.staffId}'/>"
													onclick="return confirm('Are You Sure You Want To Delete This Group ?')"><i
														class="fa fa-fw fa-trash"></i></a>
							   	       <button onclick="details(${Sitem.id})" class="pull-right btn" style=" background-color: #d3efc6 ">Details</button>
							   				
	    								
	    						   </span>
 	    								<h5>View Members :
 	    								<a href="<c:url value="/web/taskforce/service/mentor/student/"/>${Sitem.id}" >
               click here</a>
 	    								
 	    								
 	    								</h5> 
  					      </div> 
	  				     </div>
	  				 </c:forEach>
  				   </div>  
  				</section>	
              </div>
		  <div class="col-md-3 col-sm-3 myStyle1" style="height:85%;">
	         <div class="" >
			    <div class=""  style="border-radius:21px;">
				<!-- /.box-header -->
					<div style="padding-top:25px">
						<h4>Recent Activity</h4><hr>
	 				</div>
			    </div>
			</div>	
		</div>
	</section>
  </div>
 </div>
 
<script>

  function details(groupId){
	  
	  window.location = "<c:url value='/web/taskforce/service/mentor/d/'/>" + groupId;
/* 	  <c:url value='/web/taskforce/service/mentor/d/'/>${sessionScope.user.staffId}
 */  }
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
