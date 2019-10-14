<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<c:url var="addparentlogin" value="/web/taskforce/serives/staff/SaveParentProfile" />
<link rel="stylesheet" href="/code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">

<style>
 .yello_hr {
   height: 2px;
   color: #ffe63b;
   background-color: #ffe63b;	
  }	
  body{
   background: #7d7e7d;
  }
  .container{
    padding-top:10px;
    color:#fff;
   }	
   input[type=text],input[type=email], input[type=date],input[type=number],select, textarea {
      width: 100%;
      padding: 4px;
      border: 1px solid #ccc;
      border-radius: 5px;
      box-sizing: border-box;
      margin-top: 10px;
      margin-bottom: 14px;
      resize: vertical;
      background-color: #f2f2f2;
}
.catagory{

    font-size:13px;

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
tr:nth-child(even){background-color: #f2f2f2}
</style>
<body class="hold-transition skin-blue fixed sidebar-mini">
 <div class="wrapper" >
  <div class="content-wrapper">
	  <section class="content">  
	    <div class="box-body">
	       <span class="pull-left" ><font size="5">Add Parent Login Details</font></span>
	    </div>
	    <div class="box" style="margin-left:10px ; margin-right:10px">
	        <div class="box-body">
	        <c:set var="parent" value="${parentprofile}"></c:set>
	        
	          <form action="${addparentlogin}" method="post" id="FeeDetails">
	            
	            <input type="hidden" name="client_id" value="${stud.comClientName.id}">
  				<input type="hidden" value="${stud.studentId}"  name="studentId" > 
  				<input type="hidden" value="${stud.comClientName.dateOfBirth}"  name="dateOfBirth"> 
  				<input type="hidden"  name="firstName" value="${student.firstName}"> 
		       	<input type="hidden"  name="middleName" value="${student.middleName}"> 
		       	<input type="hidden"  name="lastName" value="${student.lastName}" > 
		        <input type="hidden"  name="motherName"value="${student.motherName} "> 
  				<input type="hidden" name="contactNos" value="${student.contactNos}" >
  				<input type="hidden" name="contactNo2" value="${student.contactNo2}" >
  				<input type="hidden" name="emailId" value="${student.emailId}">
  				<c:forEach var="a" items="${student.comClientAddresses}">
  				<input type="hidden"  name="address1" value="${a.address1}" >
  				<input type="hidden" id="s1" name="postalCode" value="${a.postalCode}">
  				<input type="hidden" id="city" name="city" value="${a.city}"> 
  				<input type="hidden" id="s" name="state" value="${a.state}"> 
  				<input type="hidden" id="ci" name="country" value="${a.country}">  
  				</c:forEach>
  				<input type="hidden"  name="branch" value="${stud.branch}"> 
  				<input type="hidden"  name="year" value="${stud.year}">
  				<input type="hidden"  name="rollNo" value="${stud.rollNo}">
  				<input type="hidden"  name="universityEnrollNo" value="${stud.universityEnrollNo}">
  				<input type="hidden"  name="batch" value="${stud.batch}">
  				<input type="hidden"  name="standard" value="${stud.standard}">
  					<div class="row">
  					<div class="col-sm-2"></div>
   					<div class="col-sm-10"><span><b style="color:red">* </b>Make Sure Your Username Is Your Contact Number ,So Enter Correct Contact Number</span></div>
  					</div>
  					<br>
  					
  					
  					<div class="row">
  					<div class="col-sm-2"></div>
  					 <div class="col-sm-4"><b>Enter Username As Contact No : </b></div>
  					 <div class="col-sm-4"><b>Enter Emaild : </b></div>
  					  <div class="col-sm-2"></div>
  					</div>
  					 <div class="row"> 
		    			
		    			
		    			   <c:choose>
		    			  <c:when test="${empty parentprofile }">
		    			  <div class="col-sm-2"></div>
		    			  <div class="col-sm-4"><input type="text" name="parentContactNo1"  placeholder="parent contact no.." required > </div>
			         	 <div class="col-sm-4"><input type="text" name="parentEmail"  placeholder="parent email.." > </div>
						<div class="col-sm-2"></div>
						</c:when> 
			         		     
		    		     		 <c:otherwise>
		    		     		  <c:forEach var="p" items="${parent}">
		    		     		  <div class="col-sm-2"></div>
							     <div class="col-sm-4"><input type="text" name="parentContactNo1" value="${p.contact_no1}" placeholder="parent contact no.." required > </div>
			         		      <div class="col-sm-4"><input type="text" name="parentEmail" value="${p.email}" placeholder="parent email.." > </div>
			         		   <div class="col-sm-2"></div>
			         		    </c:forEach> 
							    </c:otherwise>
							     </c:choose> 
					         </div>
  					
		       			<hr>
		       		<div class="row">
  						    <div class="col-sm-5"></div>
    						
    						<div class="col-sm-4">
    						<input type="submit" onclick="return Validate()" value="Submit">&nbsp;&nbsp;&nbsp;
    					    <input type="reset" name="resetBtn" value="Clear" class="" ></div>
  						</div>
		       </form>
		      </div>
		  </div>
	   </section> 
     </div>
  </div>
 


<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-127607784-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-127607784-1');
</script>
 </body>
</html>

