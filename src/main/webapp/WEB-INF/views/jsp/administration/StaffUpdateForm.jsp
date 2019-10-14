<%@page import="javax.naming.NoInitialContextException"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<c:url var="updateStaffProfile" value="/web/taskforce/serives/staff/updateProfile"/>
<style>

body {font-family: Arial, Helvetica, sans-serif;
   background-color: #d3efc6;
}
input[type=text],input[type=email], input[type=date],input[type=number],select, textarea ,input[type=file]{
    width: 100%;
    padding: 5px;
    border: 1px solid #ccc;
    border-radius: 4px;
    border-bottom-color:#4CAF50;
    box-sizing: border-box;
    margin-top: 5px;
    margin-bottom: 14px;
    resize: vertical;
    background-color: #f2f2f2;
     
}
.Submitbtn{
    background-color: #FFF;
    color: white;
    margin-left: 70%;
    margin-top: 30px;

}
input[type=submit], input[type=reset] {
	    background-color: #4CAF50;
	    color: white;
	    padding: 8px 15px;
	    border: none;
	    border-radius: 4px;
	    cursor: pointer;
	    width:110px;
	     font-size: 12px;
}
.row {
    font-size: 12px;
	padding: 0px 25px;
   
   
}
.left {
	  position: absolute;
	  top: 0;
	  left:20px;
	  box-sizing: border-box;
	  padding: 40px;
	  width: 500px;
	  height: 400px;
}
.head {
	 color:#4CAF50;
	}
	.yello_hr {
   height: 3px;
   color:   #4CAF50;
   background-color:   #4CAF50;	
  }	
</style>



<body class="hold-transition skin-blue fixed sidebar-mini" >
<div class="wrapper ">
 <div class="content-wrapper myStyle" >
  <c:set var="n" value="0"/>
    <!-- Main content -->
    
     <form:form action="${updateStaffProfile}" method="post" modelAttribute="stsff">
      
    <section class="content" >
         <div class="col-md-12" style="margin-top:21px">
		    <section class="content-header">
				<div class="col-md-12 col-sm-12 col-lg-12">
			<span><font size="5">Update Staff Profile</font></span>
				</div>
			</section >
		
				      <c:set var="staff" value="${staffprofile}"></c:set>       
			<!-- Info boxes -->
			    <div class="col-md-12 col-sm-12 " >
				    <div class="box">
				    <div class="box-body" >
					<div class="head">
  				<h4><b>Personal.</b></h4>
  			      </div>
  			      <input type="hidden" name="staff_id" value="${staff.comClientName.id}">
  			           <hr class="yello_hr"/>
  		
  			 		 	  <div class="row">
		         			 <div class="col-sm-4"> <b>First Name :</b> </div> 
		         			 <div class="col-sm-4"><b>Middle Name :</b> </div>
		         			 <div class="col-sm-4"><b>Last Name : </b> </div>
		    			 </div>
		    			 <div class="row">
		       				 <div class="col-sm-4"><input type="text" id="name" name="firstName" value="${staff.comClientName.firstName}" required > </div>
		       				 <div class="col-sm-4"><input type="text" id="name" name="middleName"   value="${staff.comClientName.middleName}"     required placeholder="Enter Middle Name.."> </div>
		       				 <div class="col-sm-4"><input type="text" id="name" name="lastName" value="${staff.comClientName.lastName}" required placeholder="Enter last Name.."> </div>
		       			 </div>
		       			  <div class="row">
		         			 <div class="col-sm-4"> <b>Department :</b> </div> 
		         			 <div class="col-sm-4"><b>Designation :</b> </div>
		         			 <div class="col-sm-4"><b>Employee No./ID :</b> </div>
		    			 </div>
		    			 <div class="row">
							<div class="col-sm-4">
								<select  name="branch" >
							 	  <c:forEach var="item" items="${dep}">
								      <c:if test="${item.dep_id eq staff.department}">
								        <option class="yearoption" value="${item.dep_id}">${item.dep_name}</option>
								      </c:if>
								  </c:forEach>
							  	  <c:forEach var="item" items="${dep}">
                                      <c:if test="${item.dep_id ne 0  &&  item.dep_id ne stud.branch}">
				                       <option class="yearoption" value="${item.dep_id}">${item.dep_name}</option>
			                          </c:if>
			                      </c:forEach>
								</select>
		     			 	 </div>
							 <div class="col-sm-4">
		       				 <select  name="designation">
					      		 
					      		   <option value="${staff.designation}">${staff.designation}</option>
					      		
								  <option value="Professor">Professor</option>
								  <option value="HOD">HOD</option>
								  <option value="Principal">Principal</option>
								  <option value="Administrator">Administrator</option>
								</select>
		       				 </div>
		       				 
		       				 <div class="col-sm-4"><input type="text" id="name" name="employeeNo" value="${staff.employeeNo}" required placeholder="Enter Emp Id.."> </div>
		       			 </div>
		   				 <div class="row">
		         			 <div class="col-sm-4"><b>Contact No :</b> </div>
		         			  <div class="col-sm-4"><b>Email :</b> </div> 
		         			 <div class="col-sm-4"><b>Date Of Birth :</b> </div>
		         			 </div>
		         			
		    			 <div class="row">
		    				 <div class="col-sm-4"><input type="text" name="contactNos" value="${staff.comClientName.contactNos}"  required placeholder="Enter Contact No..">  </div>
		         			<div class="col-sm-4"><input type="email" name="emailId" value="${staff.comClientName.emailId}" required placeholder="Enter Valid Email Id.."> </div>
		         			<div class="col-sm-4">
		         			<input type="date"  name="dateOfBirth" value="00/00/00"  id="datepicker" >
		         			</div>
		         				
  			 		 	  <div class="row">
		         			 <div class="col-sm-8"> <b>Address :</b> </div> 
		         			 <div class="col-sm-4"><b>city :</b> </div>
		    			 </div>
		    			 <div class="row">
		    			 <c:forEach var="a" items="${staff.comClientName.comClientAddresses}">
		       				 <div class="col-sm-8"><input type="text" id="name" name="address1" value="${a.address1}" required placeholder="Enter Address.."> </div>
		       				 <div class="col-sm-4"><input type="text" id="name" name="city"   value="${a.city}"     required placeholder="Enter city.."> </div>
		       			 </c:forEach>
		       			 </div>
		        		
					 <div class="row">
		         			 <div class="col-sm-4"> <b>State :</b> </div> 
		         			 <div class="col-sm-4"><b>Country :</b> </div>
		         			 <div class="col-sm-4"><b>PostalCode /Pincode : </b> </div>
		    			 </div>
		    			 <div class="row">
		    			  <c:forEach var="b" items="${staff.comClientName.comClientAddresses}">
		       				 <div class="col-sm-4"><input type="text" id="name" name="state" value="${b.state}" required placeholder="Enter State..">  </div>
		       				 <div class="col-sm-4"><input type="text" id="name" name="country"   value="${b.country}"     required placeholder="Enter country.."> </div>
		       				 <div class="col-sm-4"><input type="text" id="name" name="postalCode" value="${b.postalCode}" required placeholder="Enter Postalcode.."> </div>
		       			</c:forEach>
		       			 </div>
		       			
					 
					 	<div class="Submitbtn">
		     					<input type="submit" onclick="return Validate()" value="Submit" class="" >
		      					<input type="reset" name="resetBtn" value="Clear" class="" > 
		 				</div>
					 </div>
				    </div>
		        </div>
			</div>
			</div>
	  </section>  
	<!-- /Main Content -->
	</form:form>
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
  
  $( function() {
	    $( "#datepicker" ).datepicker();
	  } );


  
  
  
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












































  			<%-- <div class="head">
  				<h4><b>Personal.</b></h4>
  			</div>
  			<hr>
  		
  			 		 	  <div class="row">
		         			 <div class="col-sm-3"> <b>First Name:</b> </div> 
		         			 <div class="col-sm-3"><b>Middle Name:</b> </div>
		         			 <div class="col-sm-3"><b>Last Name: </b> </div>
		         			 <div class="col-sm-3"><b>Mothers Name : </b></div>
		    			 </div>
		    			 <div class="row">
		       				 <div class="col-sm-3"><input type="text" id="name" name="firstName" value="${student.firstName}" required> </div>
		       				 <div class="col-sm-3"><input type="text" id="name" name="middleName"   value="${student.middleName}"     required> </div>
		       				 <div class="col-sm-3"><input type="text" id="name" name="lastName" value="${student.lastName}" required> </div>
		       				 <div class="col-sm-3"><input type="text" id="name" name="motherName"value="${student.motherName} "> </div>
		       			 </div>
		   				 <div class="row">
		         			 <div class="col-sm-3"><b>Contact No:</b> </div>
		         			 <div class="col-sm-3"><b>Contact No 2:</b> </div>
		         			 <div class="col-sm-3"><b>Date Of Birth:</b> </div>
		         			 <div class="col-sm-3"><b>Email:</b> </div> 
		         			
		         			 </div>
		         			
		    			 <div class="row">
		    				 <div class="col-sm-3"><input type="text" name="contactNos" value="${student.contactNos}"  required>  </div>
		    				 <div class="col-sm-3"><input type="text" name="contactNo2" value="${student.contactNo2}" >  </div>
		         			<div class="col-sm-3">
		         			<input type="date"  name="dateOfBirth" value="00/00/00"  id="datepicker" >
		         			</div>
		         			<div class="col-sm-3"><input type="email" name="emailId" value="${student.emailId}" required > </div>
		         			
		         			<!--  <div class="col-sm-3"><input type="date" class="form-control" placeholder="YYYY-MM-DD" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}  [0-9]{2}:[0-9]{2}:[0-9]{2}" name="dateOfBirth" id="myDate" required > </div> 
						 -->	
		        		
		  --%>
		    			