<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="com.zertones.model.sims.Staff"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
.qq{
     background-color: #F4F6F6;
     padding: 7px;
     border: 2px solid #2faf2f;
     border-radius: 4px;
     box-sizing: border-box;
     margin-bottom: 16px;
    
}
input[type=time], input[type=date]{
background-color: #F4F6F6;
    padding: 7px;
    border: 2px solid #2faf2f;
    border-radius: 4px;
    box-sizing: border-box;
    margin-top: 6px;
    margin-bottom: 16px;
    resize: vertical;
    height:37px;
    width:150px;



}
input[type=submit], input[type=reset] {
    background-color: #4CAF50;
    color: white;
    padding: 12px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    margin-left:46px;
    margin-top:30px;
    width:110px;
}
input[type=submit]:hover {
    	background-color: #45a049;
}
.buttn {
    background-color: #4CAF50;
    border: none;
    color: white;
    padding: 4px 20px;
    border-radius: 1px;
    text-decoration: none;
    font-size: 16px;
    cursor: pointer;
}
.buttn1 {
    background-color: white; 
    color: black; 
    border: 2px solid #4CAF50;
}
.buttn1:hover {
    background-color: #4CAF50;
    color: white;
}
.container11 {
		  max-width: 97%;
		  padding: 1em 3em 2em 3em;
		  margin: 0em auto;
		  background-color:  #f1f3f2 ;
		  border-radius: 4.2px;
		  box-shadow: 0px 5px 15px -2px rgba(0, 0, 0, 0.2);
		  margin-left:20px;
		  margin-top:8px;
		  height:64em;
		  font-size:13px;
}
.container1 {
	  max-width: 35em;
	  padding: 1em 1em 2em 3em;
	  margin: 0em auto;
	  background-color: #fff;
	  border-radius: 4.2px;
	  box-shadow: 0px 3px 10px -2px rgba(0, 0, 0, 0.2);
	  margin-left:39em;
	  margin-top:-59.2em;
	  height:65em;
	}
ixmg {
	  border-radius: 50%;
	}
	

.name {
      text-align: left;
	  font-size: 22px;
	  margin-top: 20px;
	  color:;
}
.mail{
     text-align: left;
	  font-size: 13px;
	  margin-top: 10px;
	  color:#1b75bb;
padding: 10px 30px;

}
	th {
	  text-align: left;
	}

td, th {
	padding: 20px 45px;
}
.head
{
	  width:100%;
	  height: 60px;
	  border-radius: 5px;
	  padding: 2px 12px;
	  text-align: center;
	  background-color: #4CAF50;
	  color: white;
	  margin-top:10px;
}
/*.....................................................................................  */
.image-container
	{
		  margin-top:10px;
	    border-radius: 5px;
	
	padding:10px 10px 10px 10px;
	box-shadow:1px 2px 10px -2px hsla(120,100%,25%);;
}
.txtblur {
    font-weight: 900;
    color:   #80958d  ;
}
#myImg {
    border-radius: 2px;
    cursor: pointer;
    transition: 0.3s;
}
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 12%;
    top: 12%;
    width: auto; /* Full width */
    height: auto; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.5); /* Black w/ opacity */
}
#myImg:hover {opacity: 0.7;}

/* The Modal (background) */


/* Modal Content (image) */

/* Caption of Modal Image */
#caption {
    margin: auto;
    display: block;
    width: 80%;
    max-width: 700px;
    text-align: center;
    color: #ccc;
    padding: 10px 0;
    height: 150px;
}

/* Add Animation */
.modal-content, #caption {    
    -webkit-animation-name: zoom;
    -webkit-animation-duration: 0.6s;
    animation-name: zoom;
    animation-duration: 0.6s;
}

@-webkit-keyframes zoom {
    from {-webkit-transform:scale(0)} 
    to {-webkit-transform:scale(1)}
}

.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}
.modal-content {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	margin-left: 35%;
	width: 40%;
}

/* The Close Button */
.close {
	color: #aaaaaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}
.closegraph {
	color: #aaaaaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.closegraph:hover, .closegraph:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}

</style>

<style type="text/css">

.container {
  max-width: 960px;
}

.panel-default>.panel-heading {
  color: #333;
  background-color: #fff;
  border-color: #e4e5e7;
  padding: 0;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

.panel-default>.panel-heading a {
  display: block;
  padding: 10px 15px;
}

.panel-default>.panel-heading a:after {
  content: "";
  position: relative;
  top: 1px;
  display: inline-block;
  font-family: 'Glyphicons Halflings';
  font-style: normal;
  font-weight: 400;
  line-height: 1;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  float: right;
  transition: transform .25s linear;
  -webkit-transition: -webkit-transform .25s linear;
}

.panel-default>.panel-heading a[aria-expanded="true"] {
  background-color: #eee;
}

.panel-default>.panel-heading a[aria-expanded="true"]:after {
  content: "\2212";
  -webkit-transform: rotate(180deg);
  transform: rotate(180deg);
}

.panel-default>.panel-heading a[aria-expanded="false"]:after {
  content: "\002b";
  -webkit-transform: rotate(90deg);
  transform: rotate(90deg);
}

.accordion-option {
  width: 100%;
  float: left;
  clear: both;
  margin: 15px 0;
}

.accordion-option .title {
  font-size: 20px;
  font-weight: bold;
  float: left;
  padding: 0;
  margin: 0;
}

.accordion-option .toggle-accordion {
  float: right;
  font-size: 16px;
  color: #6a6c6f;
}

</style>
<!-- Style for tabs -->
<style>

/* Style the tab */
.tab {
  overflow: hidden;
  border: 1px solid #ccc;
  background-color: #f1f1f1;
}

/* Style the buttons inside the tab */
.tab button {
  background-color: white;
  float: left;
  border: none;
  outline: none;
  cursor: pointer;
  padding: 14px 16px;
  transition: 0.3s;
  font-size: 17px;
  color:green;
}

/* Change background color of buttons on hover */
.tab button:hover {
  background-color: #ddd;
   color:green;
}

/* Create an active/current tablink class */
.tab button.active {
  background-color:  #178708;
  color:white;
}

/* Style the tab content */
.tabcontent {
  display: none;
  padding: 6px 12px;
  border: none;
  border-top: none;
}
</style>
<!-- /Style for tabs -->
  <c:url var="editPlacementDtl" value="/web/taskforce/service/student/placementPage/${studprofile.comClientName.id}" />

   <c:url var="editprofile" value="/web/taskforce/service/student/profile/${studprofile.comClientName.id}" />
   
    <c:url var="editparent" value="/web/taskforce/service/student/parentprofile/${studprofile.comClientName.id}" />

   <c:url var="AddFeeDetails" value="/web/taskforce/service/student/FeeDetails/${studprofile.comClientName.id}" />

     <c:set var="parent" value="${parentprofile}"></c:set>

       <c:set var="feeDetail" value="${feeDetails}"></c:set>


</head>
<body class="hold-transition skin-blue fixed sidebar-mini" >
                 <div class="wrapper ">
                     <div class="content-wrapper myStyle">
                        <section class="content-header" >
  							<section style="padding-top: 15px">
  								<div class="row ">
  								<br>
									<div class="col-sm-2.5"> <button id="cmd" class=" pull-right buttn buttn1" style="margin-right: 30px;" onclick="window.location.href='${AddFeeDetails}'"><i class="fa fa-edit" ></i>  Fee Status </button>
									<div class="col-sm-2.5"> <button id="cmd" class=" pull-right buttn buttn1" onclick="window.location.href='${editparent}'"><i class="fa fa-edit" ></i> Parent Login </button>
									<div class="col-sm-2.5"> <button id="cmd" class=" pull-right buttn buttn1" onclick="window.location.href='${editprofile}'"><i class="fa fa-edit" ></i>  Edit Profile </button></div>
									<div class="col-sm-2.5"> <button id="cmd" class=" pull-right buttn buttn1" onclick="window.location.href='${editPlacementDtl}'"><i class="fa fa-edit" ></i> Edit Placement </button></div></div>
      							 </div>
  								 <div class="col-md-12 col-sm-16 " style="text-align: left"  >
  					
<div class="tab">
 <div class="col-md-3"> <button class="tablinks"  onclick="openCity(event, 'asia')" id="defaultOpen">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Profile&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button></div>
 <div class="col-md-3"> <button class="tablinks"  onclick="openCity(event, 'London')" >Academic</button></div>
 <div class="col-md-3"> <button class="tablinks"  onclick="openCity(event, 'Paris')">Placement</b></button></div>
 <div class="col-md-3"> <button class="tablinks" onclick="openCity(event, 'Tokyo')">Extra curricular Activities</button></div>
</div>

<div style="overflow:scroll; height:73%; text-align: left">

<div id="asia" class="tabcontent" >
<div class="box box-default " style="border-radius:7px; "  >
	                       				<div class="box-body" >
	                       				
	                       					 <!-- /row start -->
	                       					<div class="row  "  style="height:45%">
												<div class="col-sm-2">
												  <div class="image-container">
											         <c:choose>
														<c:when test="${studprofile.comClientName.imgUrl  != null}">
															<img src="${studprofile.comClientName.imgUrl}" class="user-image" alt="User Image" width="100%" height="30%">
														</c:when>
													    <c:otherwise>
															<img id="myImg" alt="Student" src="<c:url value="/static/img/author.png"/>" width="100%" height="30%"  />
													    </c:otherwise>
													 </c:choose> 
												</div>
											  </div>
											  <div class="col-sm-4">
	                       				        <span class="name"><i class="fa fa-user-o" style="color:green"></i><b> &nbsp;${studprofile.comClientName.firstName} ${studprofile.comClientName.middleName} ${studprofile.comClientName.lastName} </b></span><br>
			                       				   <div class="mail">
													   <div>
														   <label><i class="txtblur">Student Contact Details :<br></i><i class="fa fa-envelope-o" style="color:green"></i>&nbsp; <a href="https://mail.google.com/mail/?view=cm&fs=1&tf=1&to=${studprofile.comClientName.emailId}" target="_blank">${studprofile.comClientName.emailId}</a></label>
											 		       <label><i class="fa fa-phone" style="color:green"></i> &nbsp; +91-${studprofile.comClientName.contactNos} /  <br><i class="fa fa-phone" style="color:green"></i>  &nbsp; +91-${studprofile.comClientName.contactNo2}  </label>  
													   </div>
											       </div>
											   </div>
	                       				      <div class="col-md-6">
	                       				          <div class="row">
													  
												      <div class="col-sm-3">
														  <i class="txtblur">Address:</i>
													  </div>
													  <div class="col-sm-9">
														<c:set var="add" value="${studprofile.comClientName.comClientAddresses}"></c:set>
															<c:forEach var="a" items="${add }">
																<b>${a.address1} , ${a.city} ,${a.state} ,${a.country} ,${a.postalCode} .</b>
															</c:forEach>
													 </div>
													 <div class="col-sm-3">
													  	 <i class="txtblur">Date ofBirth:</i>
													  </div>
													  <div class="col-sm-9">
															<b> <fmt:formatDate type="date" value="${studprofile.comClientName.dateOfBirth}" /> </b>
													  </div>
													  
													  <div class="col-sm-3">
															<i class="txtblur"> Gender:</i>
													  </div>
													  <div class="col-sm-9">
															<c:if test="${studprofile.comClientName.gender == 1}">
																<b>Male.</b>
															</c:if>
														 	<c:if test="${studprofile.comClientName.gender == 2}">
																<b>Female.</b>
															</c:if>
													  </div>
													  
													  <div class="col-sm-3">
														  <i class="txtblur"> Aadhar No:</i>
													  </div>
													  <div class="col-sm-9">
															<b>--</b>
													   </div>
													
														<div class="col-sm-3">
															<i class="txtblur"> Handicaped:</i>
														</div>
														<div class="col-sm-9">
																<c:if test="${studprofile.comClientName.isHandicaped == 0}">
															 	 	<b>No.</b>
																</c:if>
																<c:if test="${studprofile.comClientName.isHandicaped == 1}">
																	<b>Yes.</b>
																</c:if>
														</div>
														<div class="col-sm-3">
															<i class="txtblur"> Blood Group:</i>
											    	    </div>
													    <div class="col-sm-9">
													         <b>--</b>
													    </div>
													    
												  </div>
											   </div>
										    </div>
										    <div class="row">
										    <div class="col-sm-2"></div>
										    <div class="col-sm-3">
										    <h4 style="color:green">Parent Details.</h4>
										    </div>
										    </div>
										     <div class="row">
										    <div class="col-sm-2"></div>
										    	<div class="col-sm-2">
														<i class="txtblur">Father Name : </i>
													  </div>
													  <div class="col-sm-7">
															<label>${studprofile.comClientName. middleName} ${studprofile.comClientName. lastName}</label>
													  </div></div>
													   <div class="row">
										    <div class="col-sm-2"></div>
										    	<div class="col-sm-2">
														<i class="txtblur">Mother Name : </i>
													  </div>
													  <div class="col-sm-7">
															<label>${studprofile.comClientName. motherName} ${studprofile.comClientName. lastName}</label>
													  </div></div>
										    <c:forEach items="${parent}" var="p">
										    <div class="row">
										    <div class="col-sm-2"></div>
										    <div class="col-sm-2">
										    <i class="txtblur">Parent Contact No :</i>
										    </div>
										    <div class="col-sm-3">
										         <label><i class="fa fa-envelope-o" style="color:green"></i>&nbsp; <a href="https://mail.google.com/mail/?view=cm&fs=1&tf=1&to=${p.email}" target="_blank">${p.email}</a></label>
										    </div>
										     </div>
										     
										     <div class="row">
										    <div class="col-sm-2"></div>
										    <div class="col-sm-2">
										    <i class="txtblur">Parent Email ID :</i>
										    </div>
										    <div class="col-sm-3">
										        <label style="color:#3c8dbc" ><i class="fa fa-phone" style="color:green"></i> &nbsp; +91-${p.contact_no1}</label>
										    </div>
										     </div>
										   </c:forEach> 
										   
										    </div>
										     
										    <!-- /row end -->
										 </div>
	                       			  </div>


										<div id="London" class="tabcontent" >
  
                                                  <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" >
										   		 	  <div class="panel panel-default">
														<div class="panel-heading" role="tab" id="headingOne">
															 <h4 class="panel-title">
																<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
																 Acadmic.
															    </a>
														     </h4>
														</div>
														
														<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
															<div class="row">
																<div class="col-sm-6">
																	<h5 style="text-align:left ;color:green"><b> Acadmic Info.</b></h5>
																 </div>
																<div class="col-sm-6">
																	<h5 style="text-align:left ;color:green"><b> Fee Status.</b></h5>
																</div>
															</div>
															  <div class="row">

																		 	    <div class="col-sm-1"></div>
																		
																		 	 	<div class="col-sm-2">
																		
																		 	 	<i class="txtblur"> Branch :</i>
																		
																		 	    </div>
																		
																		 	 	<div class="col-sm-3">
																		
																		 	 	<c:set var="dept" value="${departments}"></c:set>
																		
																		           	<c:forEach var="a" items="${dept}">
																		
																		           	  <c:if test="${studprofile.branch ==a.dep_id }">
																		
																		           	  <b>${a.dep_name}</b>
																		
																		           	  </c:if>
																		
																		           	</c:forEach>
																		
																		 	    </div>
																		
																		        <div class="col-sm-1"></div>
																		
																		        <div class="col-sm-2">
																		
																		            <i class="txtblur"> Total Fee :</i>
																		
																		 	    </div>
																		
																		 	    <div class="col-sm-3">
																		
																		<b>${feeDetail.total_fee } rs</b>
																		
																		 	    </div>
																		
																		       
																		
																		  </div>
																		
																		  <div class="row" >
																		
																		 	    <div class="col-sm-1"></div>
																		
																		 	 	<div class="col-sm-2">
																		
																		<i class="txtblur">Roll Number :</i>
																		
																		 	    </div>
																		
																		 	 	<div class="col-sm-3">
																		
																		<b>${studprofile.rollNo}</b>
																		
																		 	    </div>
																		
																		        <div class="col-sm-1"></div>
																		
																		        <div class="col-sm-2">
																		
																		            <i class="txtblur"> Category :</i>
																		
																		 	    </div>
																		
																		 	    <div class="col-sm-3">
																		
																		<b>${feeDetail.category }</b>
																		
																		 	    </div>
																		
																		  </div>
																		
																		  <div class="row">
																		
																		 	    <div class="col-sm-1"></div>
																		
																		 	 	<div class="col-sm-2">
																		
																		 	 	<i class="txtblur"> Standerd / Division :</i>
																		
																		 	    </div>
																		
																		 	 	<div class="col-sm-3">
																		
																		<c:if test="${studprofile.standard == 1}">
																		
																		 	 	<b>A .</b>
																		
																		           	</c:if>
																		
																		           	<c:if test="${studprofile.standard == 2}">
																		
																		 	 	<b>B .</b>
																		
																		           	</c:if>
																		
																		           	<c:if test="${studprofile.standard == 3}">
																		
																		 	 	<b>c .</b>
																		
																		           	</c:if>
																		
																		           	<c:if test="${studprofile.standard == 4}">
																		
																		 	 	<b>D .</b>
																		
																		           	</c:if>
																		
																		           	<c:if test="${studprofile.standard == 5}">
																		
																		 	 	<b>E .</b>
																		
																		           	</c:if>
																		
																		           	<c:if test="${studprofile.standard == 6}">
																		
																		 	 	<b>F .</b>
																		
																		           	</c:if>
																		
																		 	    </div>
																		
																		        <div class="col-sm-1"></div>
																		
																		        <div class="col-sm-2">
																		
																		            <i class="txtblur">Outstanding :</i>
																		
																		 	    </div>
																		
																		 	    <div class="col-sm-3">
																		
																		<b>${feeDetail.outstanding } rs</b>
																		
																		 	    </div>
																		
																		  </div>
																		
																		  <div class="row">
																		
																		 	    <div class="col-sm-1"></div>
																		
																		 	 	<div class="col-sm-2">
																		
																		    <i class="txtblur"> University PRN NO :</i>
																		
																		 	    </div>
																		
																		 	 	<div class="col-sm-3">
																		
																		<b>${studprofile.universityEnrollNo}.</b>
																		
																		 	    </div>
																		
																		        <div class="col-sm-1"></div>
																		
																		        <div class="col-sm-2">
																		
																		            <i class="txtblur"> Paid :</i>
																		
																		 	    </div>
																		
																		 	    <div class="col-sm-3">
																		
																		<b>${feeDetail.paid } rs</b>
																		
																		 	    </div>
																		
																		       
																		
																		  </div>
																		
																		  <div class="row">
																		
																		 	    <div class="col-sm-1"></div>
																		
																		 	 	<div class="col-sm-2">
																		
																		    <i class="txtblur"> Year :</i>
																		
																		 	    </div>
																		
																		 	 	<div class="col-sm-3">
																		
																		<c:if test="${studprofile.year == 1}">
																		
																		 	 	<b>First Year .</b>
																		
																		           	</c:if>
																		
																		           	<c:if test="${studprofile.year == 2}">
																		
																		 	 	<b>Second Year .</b>
																		
																		           	</c:if>
																		
																		           	<c:if test="${studprofile.year == 3}">
																		
																		 	 	<b>Third Year .</b>
																		
																		           	</c:if>
																		
																		           	<c:if test="${studprofile.year == 4}">
																		
																		 	 	<b>Fourth Year .</b>
																		
																		           	</c:if>
																		
																		 	    </div>
																		
																		        <div class="col-sm-1"></div>
																		
																		        <div class="col-sm-2">
																		
																		            <i class="txtblur"> Remaining  :</i>
																		
																		 	    </div>
																		
																		 	    <div class="col-sm-3">
																		
																		<b>${feeDetail.remaining } rs</b>
																		
																		 	    </div>
																		
																		  </div>
																		
																		  <div class="row">
																		
																		 	    <div class="col-sm-1"></div>
																		
																		 	 	<div class="col-sm-2">
																		
																		    <i class="txtblur"> Registration NO :</i>
																		
																		 	    </div>
																		
																		 	 	<div class="col-sm-3">
																		
																		<b>${studprofile.registrationNo} </b>
																		
																		 	    </div>
																		
																		    </div>

															
														</div>
													</div>
												</div>
												
												<div class="panel panel-default">
													<div class="panel-heading" role="tab" id="headingTwo">
														<h4 class="panel-title">
															<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
																Attendance
															</a>
														</h4>
													</div>
													
													<div id="collapseTwo" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingTwo">
														<div class="panel-body">
														     <table class="table table-bordered table-striped">

															<thead>
															
															<tr>
															
															<th>Subject Name</th>
															
															<th>1st Month</th>
															
															<th>2nd month</th>
															
															<th>Term End</th>
															
															</tr>
															
															</thead>
															     
															   
														       <tbody>
															      <c:forEach items="${currentAttendance}" var="currentAttendance">
															      		<c:set var="total" value="0"></c:set>
															      		<c:set var='n' value="0"/>
															          <tr>
																		<td>${currentAttendance.subName}</td>
															               <c:forEach var="attendances" items="${currentAttendance.list}">
															                  <c:if test="${n lt 2}">
															                    <c:set var='n' value="${n+1}"/> 
															                    <td>${attendances.attendance}</td>
															                    <c:set var="total" value="${total + attendances.attendance }"></c:set>
															                  </c:if>
															               </c:forEach>
															               
																		<td>${total/n}</td>
																	  </tr>
															      </c:forEach>
															    </tbody>
															
															</table>
														</div>
													</div>
												</div>												
												
	<div class="panel panel-default">

      <div class="panel-heading" role="tab" id="headingSix">

        <h4 class="panel-title">

        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSix" aria-expanded="true" aria-controls="headingFour">

        GFM Information 

        </a>

      </h4>

      </div>

     

      <div id="collapseSix" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingSix">

        <div class="panel-body">

          <table class="table table-bordered table-striped">

          <thead>

          <tr>

          <th>Sr No</th>

          <th>Year</th>

          <th>Semester</th>

          <th>Academic Year</th>

          <th>Name Of the GFM</th>

          </tr>

          </thead>

          <tbody>

           	<c:if test="${not empty GfmInfo}">

           	  <c:forEach var="items" items="${GfmInfo}">

           	  <tr>

          <td>1</td>

          <td>FE</td>

          <td>${items.sem}</td>

          <td>${items.yr}</td>

          <td>${items.firstName} ${items.middleName} ${items.lastName} </td>

          </tr>

           	  </c:forEach>

           	</c:if>

          </tbody>

          </table>

           

        </div>

      </div>

    </div>
    
    											
												
</div>

<div id="Paris" class="tabcontent">
  <div class="panel panel-default">


      <div class="panel-heading" role="tab" id="headingFive">


        <h4 class="panel-title">


        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="true" aria-controls="headingFour">


        Result


        </a>


      </h4>


      </div>


      <div id="collapseFive" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingFive">


        <div class="panel-body">


          <div class="row " style="background-color:#fff;">

     	<div class="col-sm-12">

      <table id="example1" class="table table-bordered table-striped" >

  <thead>

                <tr style="text-align:left ;color:green">

                  <th>Education</th>

                  <th>Semester</th>

                  <th>%</th>

                  <th>Result</th>

                  <th>Is Backlog</th>

                </tr>

            </thead>

            <tbody >

              <c:forEach var="eduDtl" items="${educationDtl}">

                <tr>

              <td>${eduDtl.standard}</td>

              <c:if test="${not empty eduDtl.semester}">

         	<td>${eduDtl.semester.sem_name}</td>

          </c:if>

          <c:if test="${empty eduDtl.semester}">

         	<td>NA</td>

          </c:if>

              <td>${eduDtl.persentage}</td>

              <td>${eduDtl.resultModel.result_type}</td>

              <c:if test="${eduDtl.isBacklog}">

              <td>Yes / ${eduDtl.noOfBacklog}</td>

              </c:if>

              <c:if test="${not eduDtl.isBacklog}">

              <td></td>

              </c:if>

            </tr> 

              </c:forEach>

            </tbody>

        </table>    

  </div>

    </div>

  </div>

        </div>

      </div>
      
   <div class="panel panel-default">

      <div class="panel-heading" role="tab" id="heading8">

        <h4 class="panel-title">

        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse8" aria-expanded="true" aria-controls="headingFour">

        Apprentice/Internship in company   

        </a>

      </h4>

      </div>

      <div id="collapse8" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading8">

        <div class="panel-body">

        <table class="table table-bordered table-striped">

          <thead>

          <tr>

          <th>Semester</th>

          <th>Name of organization</th>

          <th>From</th>

          <th>To</th>

          <th>TPO</th>

          </tr>

          </thead>

          <tbody>
         

		         <c:if test="${not empty inranship}">
		
		            <c:forEach var="inranship" items="${inranship}">
		
		       	      <tr>
						 <td>${inranship.className}</td>
			
			             <td>${inranship.organizer}</td>
			
			             <td>${inranship.dateOfEvent}</td>
			
			             <td>${inranship.toDate}</td>
			             
			             <td>${inranship.coordinateName}</td>
			
			          </tr>
		           </c:forEach>
		
		        </c:if>

          </tbody>

            </table>

        </div>

      </div>

    </div>   
     <div class="panel panel-default">

      <div class="panel-heading" role="tab" id="heading7">

        <h4 class="panel-title">

        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse7" aria-expanded="true" aria-controls="headingFour">

          Participation in Value Added Programs(VAP)

        </a>

      </h4>

      </div>

      <div id="collapse7" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading7">

        <div class="panel-body">

        <table class="table table-bordered table-striped">

          <thead>

          <tr>

            <th>Class & Sem</th>

            <th>Name of Course Participated</th>

            <th>Duration</th>

            <th>Remark</th>

          </tr>

          </thead>

          <tbody>

          <c:if test="${not empty vpa}">

            <c:forEach var="vpa" items="${vpa}">

       	      <tr>
				 <td>${vpa.className}</td>
	
	             <td>${vpa.eventname}</td>
	
	             <td>${vpa.duration}</td>
	
	             <td>${vpa.remark}</td>
	
	          </tr>
           </c:forEach>

        </c:if>

           	</tbody>

          </table>

        </div>

      </div>

    </div>  
    
     <div class="panel panel-default">

      <div class="panel-heading" role="tab" id="heading7">

        <h4 class="panel-title">

        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapsea7" aria-expanded="true" aria-controls="headingFour">

          Placement Status

        </a>

      </h4>

      </div>

      <div id="collapsea7" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading7">

        <div class="panel-body">

        <table class="table table-bordered table-striped" style="font-size: 13px;">
     <c:set var="placmentstatus" value="${placmentstatus}"></c:set>


          <tr>
            <th  style="width: 10px">#</th>
            <th>Company Name</th>
            <th>Job Title</th>                         
            <th>Package</th>
            <th>Date Time</th>
            <th>Status</th>
             <th>view</th>
         </tr>
				         <c:forEach var="x" items="${placmentstatus}">
				          <tr>
				          <td>${n + 1}</td>
				          <td>${x.recruitmentInfo.companyName}</td>
				          <td>${x.recruitmentInfo.jobtitle }</td>
				          <td>${x.recruitmentInfo.salary }</td>
				          <td><fmt:formatDate pattern = "yyyy-MM-dd" 
                                               value = "${x.recruitmentInfo.dateInfo}" /> ${x.recruitmentInfo.time}
				         </td>
				         
				         <td>
							 <c:choose>
							  						<c:when test="${x.offeraccepted == true}">	
							  						<b>Select And Offer Accepted</b>				
                                                    </c:when>
                                                    <c:when test="${x.selectstud == true}">	
                                                    <b>Select but Offer not Accepted</b>                                       
				         							</c:when>
				         							<c:when test="${x.round1 == false}">
				         							<b>First Round can't clear</b> 
				         							</c:when>
				         							<c:when test="${(x.round1 == true )&&(x.round2 == false)}">
				         							<b>Second Round can't clear</b> 
				         							</c:when>
				         							<c:when test="${(x.round1 == true) && (x.round2 == true) && (x.round3 == false) }">
				         							<b>Third Round can't clear</b> 
				         							</c:when>
				         							<c:when test="${(x.round1 == true) && (x.round2 == true) &&(x.round3 == true) && (x.round4 == false) }">
				         							<b>Fourth Round can't clear</b> 
				         							</c:when>
				         							<c:when test="${(x.round1 == true) && (x.round2 == true) &&(x.round3 == true) &&(x.round4 == true) && (x.round5 == false) }">
				         							<b>Last Round can't clear</b> 
				         							</c:when>
				         
				         </c:choose>
				         
				         </td>
				         <td><b style="color: green; "onclick="classGroup('${x.recruitmentInfo.reInfoId}')" >View Round List</b></td>
				          </tr>
				          <c:set var='n' value="${n + 1 }"/>
				</c:forEach>
      
          </table>

        </div>

      </div>

    </div>  
      
      
      
      
</div>

<div id="Tokyo" class="tabcontent">
 <div class="panel panel-default">

      <div class="panel-heading" role="tab" id="heading9">

        <h4 class="panel-title">

        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse9" aria-expanded="true" aria-controls="headingFour">

        Feedback about student by GFM after each semester

        </a>

      </h4>

      </div>

      <div id="collapse9" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading9">

        <div class="panel-body">

        <div class="row qq" style="background-color:#fff;	box-shadow: 0px 3px 10px -2px rgba(0, 0, 0, 0.2);">

      <div class="col-sm-12">

 	   

      <div class="row">

 	 	<div class="col-sm-6">

        <h5 style="text-align:left ;color:green"><b>FE Sem-I</b></h5>

 	    </div>

        <div class="col-sm-6">

        <h5 style="text-align:left ;color:green;float: right "><b>Academic Year</b></h5>

 	    </div>

  </div>

  <div class="row">

       

  </div>

  <div class="row" >

       

  </div>

  <div class="row">

       

  </div>

  <div class="row">

       

  </div>

  <div class="row">

       

  </div>

  <div class="row">

 	 	<div class="col-sm-6">

        <h5 style="text-align:left ;color:green"><b>Name & Sign Of GFM</b></h5>

 	    </div>

        <div class="col-sm-6">

        <h5 style="text-align:left ;color:green"><b></b></h5>

 	    </div>

  </div>

 	      </div>

</div>

 

<div class="row qq" style="background-color:#fff;	box-shadow: 0px 3px 10px -2px rgba(0, 0, 0, 0.2);">

      <div class="col-sm-12">

 	   

      <div class="row">

 	 	<div class="col-sm-6">

        <h5 style="text-align:left ;color:green"><b>FE Sem-II</b></h5>

 	    </div>

        <div class="col-sm-6" >

        <h5 style="text-align:left ;color:green; float: right"><b>Academic Year</b></h5>

 	    </div>

  </div>

  <div class="row">

       

  </div>

  <div class="row" >

       

  </div>

  <div class="row">

       

  </div>

  <div class="row">

       

  </div>

  <div class="row">

       

  </div>

  <div class="row">

 	 	<div class="col-sm-6">

        <h5 style="text-align:left ;color:green"><b>Name & Sign Of GFM</b></h5>

 	    </div>

        <div class="col-sm-6">

        <h5 style="text-align:left ;color:green"><b></b></h5>

 	    </div>

  </div>

 	      </div>

</div>

 

      </div>

    </div>

  </div>

    
    
    <div class="panel panel-default">


      <div class="panel-heading" role="tab" id="heading10">


        <h4 class="panel-title">


        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse10" aria-expanded="true" aria-controls="heading10">


        Contribution in Organizing/Participation in Departmental Programs


        </a>


      </h4>


      </div>


      <div id="collapse10" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading10">


        <div class="panel-body">

            <div class="row " style="background-color:#fff;">

 	<div class="col-sm-12">

      <table id="example1" class="table table-bordered table-striped" >

    <thead>

		  <tr style="text-align:left ;color:green">
		
		      <th>Name of Program</th>
		
		      <th>Date of program</th>
		
		      <th>Post held/Prize won</th>
		
		      <th>Name and Sign of Prog. coordinator</th>
		
		    </tr>
		
		</thead>
		
		<tbody >
		
		        <c:forEach var="departmentalEvent" items="${departmetalProgram}">
		
		  <tr>
		
		      <td>${departmentalEvent.eventname}</td>
		
		      <td>${departmentalEvent.dateOfEvent}</td>
		
		      <td>${departmentalEvent.prizewon}</td>
		
		      <td>${departmentalEvent.coordinateName}</td>
		
		  </tr>
		
		  </c:forEach> 
		
		</tbody>
		
		</table>
		
		</div>

      </div>

  </div>

        </div>

      </div>

     
     
     
      <div class="panel panel-default">


      <div class="panel-heading" role="tab" id="heading11">


        <h4 class="panel-title">


        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse11" aria-expanded="true" aria-controls="heading11">


        Participation in Extra Curricular Activities 


        </a>


      </h4>


      </div>


      <div id="collapse11" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading11">


        <div class="panel-body">

            <div class="row " style="background-color:#fff;">

 	<div class="col-sm-12">

        <table id="example1" class="table table-bordered table-striped" >

    <thead>

        <tr style="text-align:left ;color:green">

            <th>Class</th>

            <th>Date and Name of Event</th>

            <th>Level of Event</th>

            <th>Name of Organizer and Address</th>

            <th>Prize Won</th>

        </tr>

    </thead>

    <tbody >
          <c:forEach var="extraActivity" items="${extraActivities}">
		    <tr>
				<td>${extraActivity.className }</td>
		
		        <td>${extraActivity.dateOfEvent } ${extraActivity.eventname}</td>
		
		        <td>${extraActivity.levelOfEvent }</td>
		
		        <td>${extraActivity.organizer } <br/>${extraActivity.venue}</td>
		
		        <td>${extraActivity.prizewon }</td>
		
		      </tr>
		   </c:forEach>   
      </tbody>
    </table>    
  </div>
</div>
</div>
</div>
</div>

     
     
      <div class="panel panel-default">


      <div class="panel-heading" role="tab" id="heading12">


        <h4 class="panel-title">


        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse12" aria-expanded="true" aria-controls="heading12">


        Participation in Sports Activities 


        </a>


      </h4>


      </div>


      <div id="collapse12" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading12">


        <div class="panel-body">


          <div class="row " style="background-color:#fff;">

     	<div class="col-sm-12">

      <table id="example1" class="table table-bordered table-striped" >

  <thead>

                <tr style="text-align:left ;color:green">

                 

                  <th>Date and Name of Event</th>

                  <th>Level of Event</th>

                  <th>Details of Organizer and Address</th>

                  <th>Prize Won</th>

                </tr>

            </thead>

            <tbody>

              <c:forEach var="sportActivity" items="${sportActivity}">

                <tr>

                  <td>${sportActivity.dateOfEvent}  ${sportActivity.eventname}</td>

                  <td>${sportActivity.levelOfEvent}</td>

                  <td>${sportActivity.organizer} <br> ${sportActivity.venue}</td>

                  <td>${sportActivity.prizewon}</td>

                </tr>

              </c:forEach>

            </tbody>

        </table>    

  </div>

    </div>

  </div>

        </div>

      </div> 
      
      
       <div class="panel panel-default">


      <div class="panel-heading" role="tab" id="heading13">


        <h4 class="panel-title">


        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse13" aria-expanded="true" aria-controls="heading13">


          Project Details


        </a>


      </h4>


      </div>


      <div id="collapse13" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading13">


        <div class="panel-body">


          <div class="row " style="background-color:#fff;">

     	<div class="col-sm-12">

      <table id="example1" class="table table-bordered table-striped" >

  <thead>

                <tr style="text-align:left ;color:green">

                  <th>Title of Project (Year)</th>

                  <th>Sponsorship if any</th>

                  <th>Name of Guide</th>

                  <th>Remarks from guide</th>

                </tr>

            </thead>

            <tbody >

              <c:forEach var="projects" items="${projects}">

                <tr>

                  <td>${projects.dateOfEvent}  ${projects.eventname}</td>

                  <td>${projects.organizer}</td>

                  <td>${projects.coordinateName}</td>

                  <td>${projects.remark}</td>

                </tr>

              </c:forEach>

        </tbody>

        </table>    

  </div>

    </div>

  </div>

        </div>

      </div>

      <div class="panel panel-default">


      <div class="panel-heading" role="tab" id="heading14">


        <h4 class="panel-title">


        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse14" aria-expanded="true" aria-controls="heading14">


          Participation in Paper Presentation/Technical Events Activities


        </a>


      </h4>


      </div>


      <div id="collapse14" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading14">

   

        <div class="panel-body">


          <div class="row " style="background-color:#fff;">

     	<div class="col-sm-12">

      <table id="example1" class="table table-bordered table-striped" >

  <thead>

                <tr style="text-align:left ;color:green">

                  <th>Date</th>

                  <th>Details of Venue </th>

                  <th>Title of Paper Presented</th>

                </tr>

            </thead>

            <tbody >

              <c:forEach var="paperPresentaion" items="${paperPresentaion}">

                <tr>

                  <td>${paperPresentaion.dateOfEvent}</td>

                  <td>${paperPresentaion.organizer} <br> ${paperPresentaion.venue}</td>

                  <td>${paperPresentaion.eventname}</td>

                </tr>

              </c:forEach>

            </tbody>

        </table>    

  </div>

    </div>

  </div>

        </div>

      </div>

      <div class="panel panel-default">


      <div class="panel-heading" role="tab" id="heading15">


        <h4 class="panel-title">


        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse15" aria-expanded="true" aria-controls="heading15">


          Participation in Seminars Activities


        </a>


      </h4>


      </div>


      <div id="collapse15" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading15">


        <div class="panel-body">

			<div class="row " style="background-color:#fff;">
			
			  <div class="col-sm-12">
			
			      <table id="example1" class="table table-bordered table-striped" >
			
			<thead>
			
			    <tr style="text-align:left ;color:green">
			
			        <th>Title </th>
			
			        <th>Date</th>
			
			        <th>Place</th>
			
			    </tr>
			
			</thead>
			
			<tbody >
			
			  <c:forEach var="semenars" items="${semenars}">
			
			    <tr>
			
			      <td>${semenars.eventname}</td>
			
			      <td>${semenars.dateOfEvent} </td>
			
			      <td>${semenars.organizer} <br> ${semenars.venue}</td>
			
			    </tr>
		
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
<!-- /Tabs -->	       	                       			  
          		
  							</section>
				  		</section>
				  		
				  		</div>
				  		</div>
				  		<div id="mygraph" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <span class="closegraph">&times;</span>
     <form action="${sendoffer}" method="POST" modelAttribute="recruitment" enctype="multipart/form-data">
       
	     <input type="hidden" name="reInfoId" value="${list.reInfoId}">
  <table id="example" class="table table-bordered table-striped" style="font-size:15px;">
				   <thead >
				     <tr class="table-success">
				       
				        <th style="width: 50%;">Name of Round</th>
				     </tr>
				     </thead>
				     <tbody> 
				     <tr>
				     <td id="studListRow"></td>
                        </tr>  
				</tbody>
			</table>
			<br>
			</form>
		</div>
	</div>
				  		
				     </div>
				  </div>
  <!--..............Image View Code..............  -->
  			
  		    <div id="myModal" class="modal">
               <span class="close">&times;</span>
               <img class="modal-content" id="img01">
               <div id="caption"></div>
            </div>	 

      <script type="text/javascript">

		//Get the modal
		var modal = document.getElementById('myModal');

		//Get the image and insert it inside the modal - use its "alt" text as a caption
		var img = document.getElementById('myImg');
		var modalImg = document.getElementById("img01");
		var captionText = document.getElementById("caption");
		img.onclick = function(){
		 modal.style.display = "block";
		 modalImg.src = this.src;
		 captionText.innerHTML = this.alt;
		}
		
		//Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];
		
		//When the user clicks on <span> (x), close the modal
		span.onclick = function() { 
		 modal.style.display = "none";
		}


	
	function setstaffId(value) {
		 var res = value.substr(0, value.indexOf("."));
		$("#custId").val(res);
		var res1 = value.substr(value.indexOf(".")+1,value.length);
	}
</script>
<script type="text/javascript">
    window.onload = function () { 
	
    	var id="${depId}";
    	if(id!="")
    	{
    		$("#Department").val("${depId}");
        	$("#acYear").val("${semId}");
        	$("#divID").val("${devisionID}");
    	}
    
    }
    function checksub(val) {
    	
    	 if(val=="")
    		{
    		alert("Please Select Department and Semister");
    		}
		
	}
    function batch(val) {
    	if(val=="practical")
    		$('#batchId').show();
    	else{
    		$('#batchId').hide();
    		$("#batchId").val("0");
    	}
    		
	}
 	
 </script>
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-127607784-1"></script>
<script>
function openCity(evt, cityName) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(cityName).style.display = "block";
  evt.currentTarget.className += " active";
}
//Get the element with id="defaultOpen" and click on it
document.getElementById("defaultOpen").click();
</script>
 
 <script type="text/javascript">
//Get the modal
	var modal = document.getElementById('myModal');
	
	var graphid = document.getElementById('mygraph');

	// Get the button that opens the modal
	var btn = document.getElementById("myBtn");

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];
	var closegraph = document.getElementsByClassName("closegraph")[0];

	// When the user clicks the button, open the modal 


	function classGroup(reInfoId) {
		alert("hiiiiiiiiii"+reInfoId);
		
		    $.ajax({
		         type: "GET",
		         url: "/"+location.pathname.split("/")[1]+"/web/taskforce/service/rounds/list/"+reInfoId,
		         dataType: 'json',
		          success: function(data){
				    	
		        	 alert(data);
		        	 if(data.length > 0){
			    		 var srno=1;

			    		 var myvar ="";
			    		 for ( var i = 0, len = data.length; i < len; ++i)  {
			          var info = data[i];
			          
			          myvar+= info+'<br>';
			          srno++;
			    		 }
			    		 $("#studListRow").html(myvar);
			    		
			       	 }
		        	 
		        	 
		        	 
		        	 
				  },
			     error:function(data){
			    	    alert("error");
			     }
		    }); 
		
		graphid.style.display = "block";
		
	}
	//............................................................................................................
		// When the user clicks on <span> (x), close the modal
		
		closegraph.onclick = function() {
			graphid.style.display = "none";
			
		}

	// When the user clicks anywhere outside of the modal, close it	
	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
			
		}
		if (event.target == graphid) {
			graphid.style.display = "none";
			
		}
	}
</script>
 
 

 
 
 
 
 
 
</body>
