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
  padding: 50px 100px;
  font-size: 22px;
  cursor: pointer;
  margin-left:50px;
  margin-top:20px;
   border-radius: 10px;
  
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
				</div>
			</section >
		
			<h3>Select Year </h3>
			   <div class="col-md-12 col-sm-6 " >
				    <div class="box">

				    <h5 style="margin-left:50px"><b> <font color=red>*</font> Note : Here You Can Select Year for Feedback... </b><br></h5>	    			   
				    <div class="row">
				       <div class="col-md-1">
				      </div>
  			  	       <div class="col-md-5">
  			  	 	        <button class="btnn success" onclick="window.location.href='<c:url value="/web/taskforce/feedback/feedbacklist/${sessionScope.user.staffId}/1"/>' "><b>First Year Feedback</b> </button>
  			  	       </div>
				   	 	<div class="col-md-5">
							 <button class="btnn success" onclick="window.location.href='<c:url value="/web/taskforce/feedback/feedbacklist/${sessionScope.user.staffId}/2"/>' "><b>Second Year Feedback</b></button><br>
  			  		 </div> 
				    </div>
				    
				       <div class="row">
				       	 <div class="col-md-1">
				    	</div>
  			  		 <div class="col-md-5">
  			  	 			<button class="btnn success" onclick="window.location.href='<c:url value="/web/taskforce/feedback/feedbacklist/${sessionScope.user.staffId}/3"/>' "><b>Third Year Feedback</b></button>
  			  	 	</div>
				    <div class="col-md-5">
							 <button class="btnn success" onclick="window.location.href='<c:url value="/web/taskforce/feedback/feedbacklist/${sessionScope.user.staffId}/4"/>' "><b>Forth Year Feedback </b></button><hr>
  			  		 </div> 
				    </div>
					        
<%-- 
				    <h5 style="margin-left:50px"><b> <font color=red>*</font> Note : Here You Can Select Year for Feedback... </b><br></h5>	    
				   
				    <div class="row">
				       <div class="col-sm-1">
				      </div>
  			  	       <div class="col-sm-5">
  			  	 	        <button class="btnn success" onclick="window.location.href='<c:url value="/web/taskforce/feedback/feedbacklist/${sessionScope.user.staffId}/1"/>' "><b>First Year Feedback</b> </button>
  			  	       </div>
				   	 	<div class="col-sm-5">
							 <button class="btnn success" onclick="window.location.href='<c:url value="/web/taskforce/feedback/feedbacklist/${sessionScope.user.staffId}/2"/>' "><b>Second Year Feedback</b></button><br>
  			  		 </div> 
				    </div>
				    
				       <div class="row">
				       	 <div class="col-sm-1">
				    	</div>
  			  		 <div class="col-sm-5">
  			  	 			<button class="btnn success" onclick="window.location.href='<c:url value="/web/taskforce/feedback/feedbacklist/${sessionScope.user.staffId}/3"/>' "><b>Third Year Feedback</b></button>
  			  	 	</div>
				    <div class="col-sm-5">
							 <button class="btnn success" onclick="window.location.href='<c:url value="/web/taskforce/feedback/feedbacklist/${sessionScope.user.staffId}/4"/>' "><b>Forth Year Feedback </b></button><hr>
  			  		 </div> 
				    </div> --%>
			
				    
			 <%-- <h5 style="margin-left:50px"><b> <font color=red>*</font> Note : Here You Can Select Year for Feedback... </b><br></h5>	    
			 <button class="btnn success" onclick="window.location.href='<c:url value="/web/taskforce/feedback/feedbacklist/${sessionScope.user.staffId}/1"/>' "><b>First Year Feedback</b> </button>
			 <button class="btnn success" onclick="window.location.href='<c:url value="/web/taskforce/feedback/feedbacklist/${sessionScope.user.staffId}/2"/>' "><b>Second Year Feedback</b></button><br>
			 <button class="btnn success" onclick="window.location.href='<c:url value="/web/taskforce/feedback/feedbacklist/${sessionScope.user.staffId}/3"/>' "><b>Third YearFeedback</b></button>
			 <button class="btnn success" onclick="window.location.href='<c:url value="/web/taskforce/feedback/feedbacklist/${sessionScope.user.staffId}/4"/>' "><b>Forth Year Feedback </b></button><hr>
				 --%>	</div>
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