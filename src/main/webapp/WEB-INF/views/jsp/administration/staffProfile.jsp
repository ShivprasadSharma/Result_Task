<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.zertones.model.sims.Staff"%>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style type="text/css">/* USER PROFILE PAGE */
 .card {
    margin-top: 5px;
    padding: 30px;
    background-color: rgba(214, 224, 226, 0.2);
    -webkit-border-top-left-radius:5px;
    -moz-border-top-left-radius:5px;
    border-top-left-radius:5px;
    -webkit-border-top-right-radius:5px;
    -moz-border-top-right-radius:5px;
    border-top-right-radius:5px;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}
.list-group
{
width:80%;
}

.card.hovercard {
    position: relative;
    padding-top: 0;
    overflow: hidden;
    text-align: center;
    background-color: #fff;
    background-color: rgba(255, 255, 255, 1);
}
.card.hovercard .card-background {
    height: 130px;
}
.card-background img {
    -webkit-filter: blur(25px);
    -moz-filter: blur(25px);
    -o-filter: blur(25px);
    -ms-filter: blur(25px);
    filter: blur(25px);
    margin-left: -100px;
    margin-top: -200px;
    min-width: 130%;
}
.card.hovercard .useravatar {
    position: absolute;
    top: 15px;
    left: 0;
    right: 0;
}
.card.hovercard .useravatar img {
    width: 100px;
    height: 100px;
    max-width: 100px;
    max-height: 100px;
    -webkit-border-radius: 50%;
    -moz-border-radius: 50%;
    border-radius: 50%;
    border: 5px solid rgba(255, 255, 255, 0.5);
}
.card.hovercard .card-info {
    position: absolute;
    bottom: 14px;
    left: 0;
    right: 0;
}
.card.hovercard .card-info .card-title {
    padding:0 5px;
    font-size: 20px;
    line-height: 1;
    color: #262626;
    background-color: rgba(255, 255, 255, 0.1);
    -webkit-border-radius: 4px;
    -moz-border-radius: 4px;
    border-radius: 4px;
}
.card.hovercard .card-info {
    overflow: hidden;
    font-size: 12px;
    line-height: 20px;
    color: #737373;
    text-overflow: ellipsis;
}
.card.hovercard .bottom {
    padding: 0 20px;
    margin-bottom: 17px;
}
.btn-pref .btn {
    -webkit-border-radius:0 !important;
    height:50px;
}

#addQuestion{
   
    display: block;
  margin-right: auto;
  margin-left: 10px;   
  width: 1050px;
  height: 600px;
}
/* .table-wrapper-scroll-y {
  display: block;
  max-height: 300px;
  overflow-y: auto;
  -ms-overflow-style: -ms-autohiding-scrollbar;
} */

div.ex1 {
    
    width: 900px;
    height: 230px;
    overflow: scroll;
}
.buttn {
    background-color: #428bca;
    border: none;
    color: white;
    padding: 4px 20px;
    border-radius: 1px;
    text-decoration: none;
    font-size: 16px;
    cursor: pointer;
   
}
.buttn1 {
    background-color: #f6faf7 ; 
    color: black; 
    border: 1px solid  #f6faf7 ;
}
.buttn1:hover {
    background-color: #428bca;
    color: white;
}

#myBtn
{

 background-color: #428bca;
    border: none;
    color: white;
    padding: 4px 20px;
    border-radius: 1px;
    text-decoration: none;
    font-size: 18px;
    cursor: pointer;
    margin-left: 300px;  
}

#myBtn1
{

 background-color: #428bca;
    border: none;
    color: white;
    padding: 4px 20px;
    border-radius: 1px;
    text-decoration: none;
    font-size: 15px;
    cursor: pointer;
    margin-left: 100px;  
}

body {font-family: Arial, Helvetica, sans-serif;}

/* The Modal (background) */
.myModal1 {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 80px; /* Location of the box */
  left: 60px;
  top: 40px;
  width: 60%; /* Full width */
  height:50%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
}

.myModal2 {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 80px; /* Location of the box */
  left: 80px;
  top: 40px;
  width: 100%; /* Full width */
  height:150%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
}


/* Modal Content */
.modal-content5 {
  margin: auto;
  padding: 120px;
  width: 70%;
  height:70%;
}


/* Modal Content */
.modal-content1 {
 
  background-color: #fefefe;
  margin: auto;
  padding: 60px;
  left: 60px;
  top: 20px;
   margin-top:120px;
  margin-left:300px;
  border: 2px solid green;
  width: 70%;
  height:70%;
}

/* The Close Button */
.close1 {
  color: #aaaaaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

/* The Modal (background) */
.myModal3 {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 80px; /* Location of the box */
  left: 60px;
  top: 40px;
  width: 100%; /* Full width */
  height:200%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
}

/* Modal Content */
.modal-content3 {
 
  background-color: #fefefe;
  margin: auto;
  padding: 60px;
  left: 60px;
  top: 20px;
  margin-top:120px;
  margin-left:250px;
  border: 2px solid green;
  width: 70%;
  height:50%;
}

/* Modal Content */
.modal-content {
 
  background-color: #fefefe;
  margin: auto;
  padding: 60px;
  left: 60px;
  top: 20px;
  border: 2px solid green;;
  width: 70%;
  height:95%;
}

/* The Close Button */
.close {
  color: #aaaaaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}


/* The Modal (background) */
.myModal4 {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 80px; /* Location of the box */
  left-margin:80px;
  left: 60px;
  top: 40px;
  width: 100%; /* Full width */
  height:200%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
}

/* Modal Content */
.modal-content4{
 
  background-color: #fefefe;
  margin: auto;
  padding: 60px;
  left: 60px;
  top: 20px;
  margin-top:120px;
  margin-left:250px;
  border:2px solid green;
  width: 70%;
  height:50%;
}



.dropbtn {
  background-color: #3498DB;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
  cursor: pointer;
}

.dropbtn:hover, .dropbtn:focus {
  background-color: #2980B9;
}

.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  overflow: auto;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown a:hover {background-color: #ddd;}

.show {display: block;}

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

/* 

.accordion-option .toggle-accordion:before {
  content: "Collapse All";
}

.accordion-option .toggle-accordion.active:before {
  content: "Collapse All";
} 

*/
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script> 
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<c:url var="saveNotice" value="/web/taskforce/service/notification/add" />

<c:url var="getNoticeSendBySubordinate" value="/web/taskforce/service/notice/sendbysubordinate" />
<c:url var="editprofile" value="/web/taskforce/service/staff/profile/${staffprofile.comClientName.id}" />
<c:set var="dept" value="${dep}"></c:set>

<body  class="hold-transition skin-blue fixed sidebar-mini" >
 <div class="wrapper" >
<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" >
    <section  class="content-header" >
       <div class="box-body" style="padding-top: 30px;">
              <span class="pull-left" ><font size="4">Staff Profile </font></span>
	   </div> 
   </section>
 <section style="overflow: auto">  
   <div class="col-lg-10 col-md-8 col-sm-6" id="addQuestion">
    <div class="card hovercard">
        <div class="card-background">
            <div class="row">
  			  	<div class="col-sm-4"> </div>
  			   	<%-- <div class="col-sm-2.5"> <button id="cmd" class=" pull-right buttn buttn1" onclick="window.location.href='${editprofil}'"><i class="fa fa-edit" ></i>  Genrate Resume </button></div>
  			     --%>
  			   <div class="col-sm-2.5"> 
  			    <div class="col-sm-2.5"> <button id="cmd" class=" pull-right buttn buttn1" onclick="window.location.href='${editprofile}'"><i class="fa fa-edit" ></i>  Edit Profile </button></div></div><br>
  			   </div>
        </div>
        <div class="useravatar">
        	<c:choose>
			   <c:when test="${staffprofile.getProfileImg() != null}">
					<img alt="" src="data:image/jpg;base64,${staffprofile.getByteArrayString()}">
			   </c:when>
			   <c:otherwise>
				    <img  src="<c:url value="/static/img/author.png"/>">
			   </c:otherwise>
			</c:choose> 
        </div>
        <div class="card-info"> <span class="card-title" style="color:#076C1E"><b>${staffprofile.comClientName.firstName} ${staffprofile.comClientName.lastName}</b>
         <%-- <c:forEach var="client" items="${staffprofile.comClientName}">
             <h4>Address  :${client.firstName}</h4>
        </c:forEach> --%>
        </span>

        </div>
    </div>
    <div class="btn-pref btn-group btn-group-justified btn-group-lg" role="group" aria-label="...">
        <div class="btn-group" role="group">
            <button type="button" id="stars" class="btn btn-primary" href="#tab1" data-toggle="tab"><span class="glyphicon glyphicon-user " style="font-size:24px;" aria-hidden="true"></span>
                <div class="hidden-xs">Personal Info</div>
            </button>
        </div>
        <div class="btn-group" role="group">
            <button type="button" id="favorites" class="btn btn-default" href="#tab2" data-toggle="tab"><span class="glyphicon glyphicon-education" style="font-size:24px;" aria-hidden="true"></span>
                <div class="hidden-xs">Professional</div>
            </button>
        </div>
        <div class="btn-group" role="group">
            <button type="button" id="following" class="btn btn-default" href="#tab3" data-toggle="tab"><span class="fa fa-users" style="font-size:24px;" aria-hidden="true"></span>
                <div class="hidden-xs">Mentees</div>
            </button>
        </div>
    </div>

     <div class="well">
      <div class="tab-content">
      <c:set var="add" value="${staffprofile.comClientName.comClientAddresses}"></c:set>
      <div class="tab-pane fade in active" id="tab1" style="font-size:16px;color: #5e605f ">
        	 <div class="row">
              <div class="col-sm-3"><b>Name :</b></div>
              <div class="col-sm-9"> <i> ${staffprofile.comClientName.firstName}   ${staffprofile.comClientName.middleName} ${staffprofile.comClientName.lastName} </i></div>
             </div>
             
              <div class="row">
           <div class="col-sm-3"><b> Email Id       :</b></div>
            <div class="col-sm-9"><i>${staffprofile.comClientName.emailId} </i></div>
           </div>
           
            <div class="row">
           	<div class="col-sm-3"><b> Contact No.  :</b> </div> 
            <div class="col-sm-9"><i>${staffprofile.comClientName.contactNos}</i></div>
           </div>
           
            <div class="row">
            <div class="col-sm-3"><b>Date of Birth :</b></div>
 			<div class="col-sm-9"><i><fmt:formatDate type="date" value="${staffprofile.comClientName.dateOfBirth}" /></i></div>
            </div>
            
             <div class="row">
            <c:forEach var="a" items="${add }">
              <div class="col-sm-3"><b>Address  :</b></div>
               <div class="col-sm-9"><i>${a.address1}</i></div>
             </c:forEach>
             </div>
              <div class="row">
            <c:forEach var="a" items="${add }">
              <div class="col-sm-3"><b></b></div>
               <div class="col-sm-9"><i>${a.city} ${a.state}</i></div>
             </c:forEach>
             </div>
              <div class="row">
            <c:forEach var="a" items="${add }">
              <div class="col-sm-3"><b></b></div>
               <div class="col-sm-9"><i>${a.postalCode} </i></div>
             </c:forEach>
             </div>
           
        </div>
        
          <div class="tab-pane fade in " id="tab2" >
       			<div class="container">
						   <div class="accordion-option">
						    	<label>Employee No. :</label><span> ${staffprofile.employeeNo}</span>
						    	<label>Designation. :</label><span> ${staffprofile.designation}</span>
						    	<label>Department . :</label><span> 
						    									<c:forEach var="a" items="${dept}">
													           		<c:if test="${staffprofile.department eq a.dep_id}">
													 			  		 <i>${a.dep_name}</i>
														       		</c:if>
														    	</c:forEach>
													 		</span>
						  </div> 
						  
						   <div class="row">
				        	<table class="table table-border">
				        		<tr>
				        		  <c:forEach var="year" items="${academicyr}">
				        		 	 <fmt:formatDate pattern="yyyy" var="parsedDate" value="${year.yearStartDate}" />
				        		 	 ${c.comClientName.id}
								    <td>
				        				<button class="btn btn-success"> <fmt:formatDate pattern="yyyy" value="${year.yearStartDate}" /> - <fmt:formatDate pattern="yy" value="${year.yearEndDate}" /></button>
				        				<span>
				        				<a href="<c:url value="/web/taskforce/service/staffprofile/info/${staffprofile.comClientName.id}/2/"/><c:out value="${parsedDate }"/>">even</a>/
				        				<a href="<c:url value="/web/taskforce/service/staffprofile/info/${staffprofile.comClientName.id}/1/"/><c:out value="${parsedDate }"/>">odd</a>
				        				</span>
				        		    </td>
				        		  </c:forEach>
				        		</tr>
				        	</table>
				        </div>
						  
						  <div class="clearfix"></div>
						  <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
						    <div class="panel panel-default">
						      <div class="panel-heading" role="tab" id="headingOne">
						        <h4 class="panel-title">
						        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
						        	Subject And Results
						        </a>
						      </h4>
						      </div>
						       <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
						        <div class="panel-body">
						        	 <table class="table table-bordered table-striped">
						              	<thead>
						              		<tr>
						              			<th>
						              				Sr.No.
						              			</th>
						              			<th>
						              				Subject Name:
						              			</th>
						              			<th>
						              				Semester:
						              			</th>
						              			<th>
						              				semester Year:
						              			</th>
						              			<th>
						              				Action
						              			</th>
						              		</tr>
						              	</thead>
						              	
						              	<tbody>
						              	   <tr id="subjectinfo"></tr>
 			   							    <c:forEach var="subject" items="${subjectlist}" varStatus="count">
 			   							   		<tr>
	 			   									<c:set var='n'  value="${n + 1 }" />
	 			   									<td>${n}</td>
	 			   									<td>${subject.academicSubject.subject_name}</td>
	 			   									<td>${subject.academicSubject.semister}</td>
	 			   									<td>${subject.academicSubject.sem_year}</td>
	 			   									<td><a onclick="getFeedback('${subject.academicSubject.semister}')">Feedback</a>/
	 			   										<a type="button" onclick="getresult('${subject.academicSubject.sem_year}','${subject.academicSubject.sub_id}')">Result</a>
	 			   										/<a type="button" onclick="getAttendance('${subject.academicSubject.sem_year}','${subject.academicSubject.sub_id}','${subject.academicSubject.subject_name}')">Attendance</a>
	 			   								    </td>
	 			   								</tr>
	 			   								
 			   								</c:forEach> 
 			   							</tbody>
						              </table>
						        </div>
						      </div>
						    </div>
						    <div class="panel panel-default">
						      <div class="panel-heading" role="tab" id="headingTwo">
						        <h4 class="panel-title">
						        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
						        	PortFolio
						        </a>
						      </h4>
						      </div>
						      <c:set var="dlist" value="${departments}"/>
						      <c:set var="colist" value="${eventIncharge}"/>
						      <div id="collapseTwo" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingTwo">
						        <div class="panel-body">
						         	<table class="table table-bordered table-striped"  >
						         		<thead>
						         		  	<tr>
						         				<th>#</th>
						         				<th>  Name</th>
										        
										    </tr>
						         		</thead>
						         		<tbody>
						         		
						         		 <c:if  test="${not empty colist}" >
						         			<tr>
						         				<c:set var='no'  value="${no + 1 }" />
	 			   							    <td>${no}</td>
						         				<td><b><a href="">Event Incharge</a></b></td>
									        </tr>
									      </c:if>
									      
									      <c:if  test="${not empty mentorMember}" >
						         			<tr>
						         				<c:set var='no'  value="${no + 1 }" />
	 			   							    <td>${no}</td>
						         				<td><b><a href="">GFM Incharge</a></b></td>
									        </tr>
									      </c:if>
									      <c:if  test="${not empty grevenceMember }" >
						         			<tr>
						         				<c:set var='no'  value="${no + 1 }" />
	 			   							    <td>${no}</td>
						         				<td><b><a href="">Grievance Committee Member</a></b></td>
									        </tr>
									      </c:if>
						         		</tbody>
						         	</table>
						        </div>
						      </div>
						    </div>
						    <!-- <div class="panel panel-default">
						      <div class="panel-heading" role="tab" id="headingThree">
						        <h4 class="panel-title">
						        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="true" aria-controls="collapseThree">
						        	GFM In-charge:
						        </a>
						      </h4>
						      </div>
						      <div id="collapseThree" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingThree">
						        <div class="panel-body">
						        </div>
						      </div>
						    </div> -->

							<!-- <div class="panel panel-default">
						      <div class="panel-heading" role="tab" id="headingFour">
						        <h4 class="panel-title">
						        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="true" aria-controls="headingFour">
						        	Event In-charge:
						        </a>
						      </h4>
						      </div>
						      <div id="collapseFour" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingFour">
						        <div class="panel-body">
						        </div>
						      </div>
						    </div> -->
						       
						    <div class="panel panel-default">
						      <div class="panel-heading" role="tab" id="headingFive">
						        <h4 class="panel-title">
						        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="true" aria-controls="headingFour">
						        	Other Info
						        </a>
						      </h4>
						      </div>
						      <div id="collapseFive" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingFive">
						        <div class="panel-body">
						          <div class="row">
						          	  <div class="col-sm-2"><b>Notices/Poll:</b></div>
						          	   <div class="col-sm-2"> <button type="button" class="button" onclick="getnotices()">Notice</button><b></b></div>
						          	   
						 			 	<div class="col-sm-2"> <button type="button" class="button" onclick="getpoll()">poll</button><b></b></div>
						 			 	<div class="col-sm-2"><b>Groups:</b></div>
						 				<div class="col-sm-2"> <button type="button" class="button" onclick="getgroups()">Group</button><b></b></div>         	    			 
						 			 
						            </div>
						        </div>
						      </div>
						    </div> 
						  </div>
						</div>
        			</div>
    		 
                 <!-- ........................    Result     ...............................-->
            
			 <div id="myModal5" class="modal">
			    <div class="modal-content5">
			       <span class="close closeResult">&times;</span>
			       <div id="chartContainer" style="height: 300px; width: 100%;"></div>
			    </div>
			</div>
                 <!-- ---------------------------------     /result end --------------------------------->
            <br/>
     
            <!-- <div class="row">
          	  <div class="col-sm-2"><b>Notices/Poll:</b></div>
          	   <div class="col-sm-2"> <button type="button" class="button" onclick="getnotices()">Notice</button><b></b></div>
          	   
 			 <div class="col-sm-2"> <button type="button" class="button" onclick="getpoll()">poll</button><b></b></div>
 			 <div class="col-sm-2"><b>Groups:</b></div>
 			<div class="col-sm-2"> <button type="button" class="button" onclick="getgroups()">Group</button><b></b></div>         	    			 
 			 
            </div>
             -->
            <!-- groups -->
             <div id="myModal4" class="modal">
            	<div class="modal-content4">
            		<span class=" close closeGroup">&times;</span>
            			<div class="row">
            				<div class="col-sm-3"><b>Sr.No:</b></div>
            				<div class="col-sm-3"><b>Group Name:</b></div>
 		   					<div class="col-sm-3"><b>Group Date:</b></div>
 			
            		    </div>
			            <div id="grop">
			            </div>
	            </div>
            </div>
                 <!-- groups end -->
                          
            <div id="myModal2" class="modal">
            	<div class="modal-content1">
            		<span class="closeNotice close">&times;</span>
            			<div class="row">
            				<div class="col-sm-2"><b>Sr.No:</b></div>            
 			 				<div class="col-sm-3"><b>Heading:</b></div>
 			  				<div class="col-sm-3"><b>Date:</b></div>
            			</div>
            			<div id="notices">
            		    </div>
            	</div>
            </div>
            
            <!-- poll -->
            <div id="myModal3" class="modal">
            	<div class="modal-content3">
            		<span class=" close closePoll">&times;</span>
            			<div class="row">
                 			<div class="col-sm-3"><b>Sr.No:</b></div>
 			 				<div class="col-sm-3"><b>question:</b></div>
 			  				<div class="col-sm-3"><b>Date:</b></div>
       					</div>
           				<div id="poll">
            
            			</div>
           		 </div>
            </div>
            <!-- Attendance -->
              <div id="myModal11" class="modal">
             	<div class="modal-content">
      			 	<span class="Attandance close" >&times;</span>
     					<div class="row">
  			  	 			<div class="col-sm-2">
  			   					<h4><b style="text-align:center;color:green">Subject Name:</b></h4>
  			     			</div>
  			      			<div class="col-sm-4">
  			   					<h4 id="subject11"><b></b></h4>
  			   				</div>
  			   				
  			      			<div class="col-sm-2">
  			   					<h4 id="semester1"><b></b></h4>
  			   		       </div>
  					   </div> 
  					<hr>
  			
		  			 <div class="row">
		  			  	 <div class="col-sm-1">
		  			  	 <h4><b style="text-align:center;color: green">Month</b></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-7">
		  			  	 <h4><b style="text-align:center;color:green"></b></h4>
		  			  	 </div>
		  			  	 
		  			  	 <!-- <div class="col-sm-2">
		  			  	 <h4><b style="text-align:center;color:green"></b></h4>
		  			  	 </div>
		  			  	  -->
		  			  	 <div class="col-sm-2">
		  			  	 <h4><b style="text-align:center;color:green">Present Student(%)</b></h4>
		  			  	 </div>
		  			</div>
		  			<div class="row">
		  			  	 <div class="col-sm-1">
		  			  	 <h4>1</h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-7">
		  			  	 <h4 id="question1"></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2">
		  			  	 <h4 id="avg1"></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2" id="firstMonth">
		  			  	 <h4></h4>
		  			  	 </div>
		  			  	 
		  			</div>
  			
		  			<div class="row">
		  			  	 <div class="col-sm-1">
		  			  	 <h4>2</h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-7">
		  			  	 <h4 id="question2"></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2">
		  			  	 <h4 id="avg2"></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2" id="secondMonth">
		  			  	 <h4></h4>
		  			  	 </div>
		  			</div>
  			       <div class="row">
		  			  	 <div class="col-sm-8">
		  			  	 <h4><h4 ><b style="text-align:center;color:green">Term Attendance</b></h4></h4>
		  			  	 </div>
		  				<div class="col-sm-2">
		  			  	 <h4><h4><b style="text-align:center;color:green" ></b></h4></h4>
		  			  	 </div> 
		  			  	  <div class="col-sm-2">
		  			  	 <h4><h4><b style="text-align:center;color:green" ></b></h4></h4>
		  			  	 </div>
		  				<div class="col-sm-2">
		  			  	 <h4><h4><b style="text-align:center;color:green" id="total"></b></h4></h4>
		  			  	 </div>
		  			</div>
  			
  					<!-- endssss -->
  				</div>
             </div>
            <!-- /Attendance -->
            <!-- poll end -->
            <div id="myModal1" class="modal">
             	<div class="modal-content">
      			 	<span class="closeFeedback close" >&times;</span>
     					<div class="row">
  			  	 			<div class="col-sm-2">
  			   					<h4><b style="text-align:center;color:green">Subject Name:</b></h4>
  			     			</div>
  			      			<div class="col-sm-4">
  			   					<h4 id="subject"><b></b></h4>
  			   				</div>
  			   				<div class="col-sm-2">
  			   					<h4><b style="text-align:center;color:green">Semester:</b></h4>
  			     			</div>
  			      			<div class="col-sm-2">
  			   					<h4 id="semester"><b></b></h4>
  			   		       </div>
  					   </div> 
  					<hr>
  			
		  			<div class="row">
		  			  	 <div class="col-sm-1">
		  			  	 <h4><b style="text-align:center;color: green">Sr.NO</b></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-7">
		  			  	 <h4><b style="text-align:center;color:green">Criteria</b></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2">
		  			  	 <h4><b style="text-align:center;color:green">vote</b></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2">
		  			  	 <h4><b style="text-align:center;color:green">Max</b></h4>
		  			  	 </div>
		  			</div>
		  			<div class="row">
		  			  	 <div class="col-sm-1">
		  			  	 <h4>1</h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-7">
		  			  	 <h4 id="question1"></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2">
		  			  	 <h4 id="avg1"></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2">
		  			  	 <h4>5.0</h4>
		  			  	 </div>
		  			  	 
		  			</div>
  			
		  			<div class="row">
		  			  	 <div class="col-sm-1">
		  			  	 <h4>2</h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-7">
		  			  	 <h4 id="question2"></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2">
		  			  	 <h4 id="avg2"></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2">
		  			  	 <h4>5.0</h4>
		  			  	 </div>
		  			</div>
  			
		  			<div class="row">
		  			  	 <div class="col-sm-1">
		  			  	 <h4>3</h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-7">
		  			  	 <h4 id="question3"></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2">
		  			  	 <h4 id="avg3"></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2">
		  			  	 <h4>5.0</h4>
		  			  	 </div>
		  			</div>
		  		<div class="row">
		  			  	 <div class="col-sm-1">
		  			  	 <h4>4</h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-7">
		  			  	 <h4 id="question4"></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2">
		  			  	 <h4 id="avg4"></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2">
		  			  	 <h4>5.0</h4>
		  			  	 </div>
		  			</div>
		  			
		  			<div class="row">
		  			  	 <div class="col-sm-1">
		  			  	 <h4>5</h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-7">
		  			  	 <h4 id="question5"></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2">
		  			  	 <h4 id="avg5"></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2">
		  			  	 <h4>5.0</h4>
		  			  	 </div>
		  			</div>
		  			
		  				
		  			<div class="row">
		  			  	 <div class="col-sm-1">
		  			  	 <h4 >6</h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-7">
		  			  	 <h4 id="question6"></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2">
		  			  	 <h4 id="avg6"></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2">
		  			  	 <h4>5.0</h4>
		  			  	 </div>
		  			</div>
		  				
		  			<div class="row">
		  			  	 <div class="col-sm-1">
		  			  	 <h4>7</h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-7">
		  			  	 <h4 id="question7"></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2">
		  			  	 <h4 id="avg7"></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2">
		  			  	 <h4>5.0</h4>
		  			  	 </div>
		  			</div>
		  				
		  			<div class="row">
		  			  	 <div class="col-sm-1">
		  			  	 <h4>8</h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-7">
		  			  	 <h4 id="question8"></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2">
		  			  	 <h4 id="avg8"></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2">
		  			  	 <h4>5.0</h4>
		  			  	 </div>
		  			</div>
		  				
		  			<div class="row">
		  			  	 <div class="col-sm-1">
		  			  	 <h4>9</h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-7">
		  			  	 <h4 id="question9"></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2">
		  			  	 <h4 id="avg9"></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2">
		  			  	 <h4>5.0</h4>
		  			  	 </div>
		  			</div>
		  				
		  			<div class="row">
		  			  	 <div class="col-sm-1">
		  			  	 <h4>10</h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-7">
		  			  	 <h4 id="question10"></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2">
		  			  	 <h4 id="avg10"></h4>
		  			  	 </div>
		  			  	 
		  			  	 <div class="col-sm-2">
		  			  	 <h4>5.0</h4>
		  			  	 </div>
		  			</div>
		  			
		  			
		  			<div class="row">
		  			  	 <div class="col-sm-8">
		  			  	 <h4><h4 ><b style="text-align:center;color:green">Total Score</b></h4></h4>
		  			  	 </div>
		  				<div class="col-sm-2">
		  			  	 <h4><h4><b style="text-align:center;color:green" id="total"></b></h4></h4>
		  			  	 </div>
		  			  	 
		  				<div class="col-sm-2">
		  			  	 <h4><h4><b style="text-align:center;color:green">5.0</b></h4></h4>
		  			  	 </div>
		  			</div>
  			
  					<!-- endssss -->
  				</div>
             </div>
        <div class="tab-pane fade in" id="tab3">
         <section class="content" style="min-height: 300px;" >
         <div class="col-md-12 ">
		    <section class="content-header">
				<div class="col-md-12 col-sm-12 col-lg-12">
				</div>
			</section >
			<c:set var="list" value=""/>
			<c:set var="list" value="${studlist}"/>
			<!-- Info boxes -->
			    <div class="col-md-12 col-sm-12 " >
				    <div class="box">
				    <div class="box-body" >
				    <div class="ex1">
					   <table id="example1" class="table table-bordered table-striped  " >
						   <thead>
				                <tr>
				                  <th style="width: 10px">#</th>
				                  <th>Name</th>
				                  <th>Contact No</th>
				                  <th>Email ID</th>
				                </tr>
				            </thead>
				            <tbody >
				           		<c:forEach var="x" items="${list}" >		
				             	    <c:choose>
					                  <c:when test="${!fn:contains(x, 'mentorStudent')}">
					                  <c:set var="n" value="${n + 1}" scope="page"/>
					                      <tr>
					                       <td>${n}</td>
					                       <td><a href="<c:url value="/web/taskforce/service/staffprofile/${x.comClientName.id}"/>">${x.comClientName.firstName} ${x.comClientName.lastName}</a></td>
					                       <td>${x.comClientName.contactNos}</td>
					                       <td>${x.comClientName.emailId}</td>
					                      </tr> 
					                   </c:when>
					                   <c:otherwise>	
					                    <c:forEach var="m" items="${x.mentorStudent}">		
					                    <c:set var="n" value="${n + 1}" scope="page"/>				               
					                      <tr>
					                       <td>${n}</td>
					                
 											<td><a href="<c:url value="/web/taskforce/service/result/menterstudprof/${m.student.comClientName.id}"/>">${m.student.comClientName.firstName} ${m.student.comClientName.lastName}</a></td>					                       
					                       <%-- <td><a href="#">${m.student.comClientName.firstName} ${m.student.comClientName.lastName}</a></td> --%>
					                       <td>${m.student.comClientName.contactNos}</td>
					                       <td>${m.student.comClientName.emailId}</td>
					                     </tr>
					                     </c:forEach>	
					                   </c:otherwise>
					               </c:choose> 				                  
				                </c:forEach> 
				             </tbody>
			             </table>
			             </div>
			            </div>
				    </div>
		        </div>
			</div>
	  </section>  
        </div>
      </div>
    </div>
  </div>
</section>
 
 
 
   </div>
</div>
   <%-- <script src="<c:url value="/static/js/canvasjs.min.js"/>"></script>  --%>
  <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
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
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-127607784-1');
</script>

<script>

function getAttendance(resultyr,subid,subName){
	var json=null;
	var staffid="${staffprofile.comClientName.id}";
	 $.ajax({
	         type: "GET",
	         url: "/"+location.pathname.split("/")[1]+"/web/taskforce/student/monthly/attendance/"+subid,
	         //data:'staffid='+ staffid +'&subid='+ subid +'&resultyr='+ resultyr,
	         dataType: 'json',
	         success: function(data)
	         { 	
	        	displayAttendanceChart(data,subName);
	         },error:function(data){
		    	alert("error");
			 }
	    });
    resultId.style.display = "block";		
}

var span11 = document.getElementsByClassName("Attandance")[0];
span11.onclick = function() {
	AttandanceId.style.display = "none";
}
var AttandanceId = document.getElementById('myModal11');

var modal11 = document.getElementById('myModal11');
function displayAttendanceChart(data,subName){
	var accounting = [];
	var ss =data.split(",");
    var PresentStudent=[];
    var AbsentStudent =[];
    var months =[];
    var monthname ="";
    var p =0;
    var a=0;
      for(var j=0;j<ss.length;j++){
      	    if((j%2) == 0 ){
      	    	  var monthName= ss[j].replace('[','');
      	    	  months.push(monthName.substring(0, 4));
      	    	  monthname= monthName.substring(0, 4);
      	    }else{
      	      	
      	    	PresentStudent.push(ss[j].replace(']',''));
      	    }
      }
      var myvar="";
 		
 	      $("#subject11").text(subName);
 	           
 		  $("#question1").text("Month");
 		  $("#avg1").text(PresentStudent[0]);
 		  
 		  $("#question2").text("Month");
 		  $("#avg2").text(PresentStudent[1]);
 		  
 		 $("#total").text((PresentStudent[0] + PresentStudent[1])/2);
 	
 		 modal11.style.display = "block";
   
}

function getresult(resultyr,subid) {
	var json=null;
	 var staffid="${staffprofile.comClientName.id}";
	 $.ajax({
	         type: "POST",
	         url: "/"+location.pathname.split("/")[1]+"/web/taskforce/service/resultbysubject",
	         data:'staffid='+ staffid +'&subid='+ subid +'&resultyr='+ resultyr,
	         dataType: 'json',
	         success: function(data)
	         { 	        	 
	            displayChart(data);
	         },error:function(data){
		    	alert("error");
			 }
	 });
	 resultId.style.display = "block";		  
}

function displayChart(json){
	var accounting = [];
	for(var i=0;i<json.length;i++){
		var x = json[i];
		accounting.push({ 
	        "y" : x.y,
	        "label"  : x.label  
	    }); 
	} 
	var chart = new CanvasJS.Chart("chartContainer", {
			animationEnabled: true,
			title: {
				text: "Result"
			},
			data: [{
				type: "pie",
				startAngle: 240,
				yValueFormatString: "##0.00\"%\"",
				indexLabel: "{label} {y}",
				dataPoints: accounting			
			}]
		});
	  chart.render(); 
}

//Get the modal
var modal = document.getElementById('myModal1');
//Get the <span> element that closes the modal
var span = document.getElementsByClassName("closeFeedback")[0];
function getFeedback(semId) {
	var staffid="${staffprofile.comClientName.id}";
	var myvar="";
	
	  $.ajax({
	         type: "POST",
	         url: "/"+location.pathname.split("/")[1]+"/web/taskforce/service/feedback",
	         data:'staffid='+ staffid +'&semId='+semId,
	         dataType: 'json',
	         
	         success: function(data)
	         { 
	        	 var myvar="";
	        	 
	        	 $("#total").text(data.total_avg_Rate);
	             
	             $("#subject").text(data.subname);
	             
	             $("#semester").text(data.semester);
        	 
             $("#question1").text(data.q1);
             $("#avg1").text(data.avg1);
             
             $("#question2").text(data.q2);
             $("#avg2").text(data.avg2);
             
             $("#question3").text(data.q3);
             $("#avg3").text(data.avg3);
             
             $("#question4").text(data.q4);
             $("#avg4").text(data.avg4);
             
             $("#question5").text(data.q5);
             $("#avg5").text(data.avg5);
             
             $("#question6").text(data.q6);
             $("#avg6").text(data.avg6);
             
             $("#question7").text(data.q7);
             $("#avg7").text(data.avg7);
             
             $("#question8").text(data.q8);
             $("#avg8").text(data.avg8);
             
             $("#question9").text(data.q9);
             $("#avg9").text(data.avg9);
             
             $("#question10").text(data.q10);
             $("#avg10").text(data.avg10);

         	modal.style.display = "block";

	         },
	         
	         
		     error:function(data){
		    	    alert("data not found");
		     }
	    });

	

}

//When the user clicks on <span> (x), close the modal
span.onclick = function() {
modal.style.display = "none";
}


/* end feedback......................................................... */
 
 
//Get the modal
var NoticeId = document.getElementById('myModal2');

//Get the <span> element that closes the modal
var span1 = document.getElementsByClassName("closeNotice")[0];

function getnotices() {

	var name="${staffprofile.comClientName.firstName}${staffprofile.comClientName.lastName}"
	 $.ajax({
	         type: "POST",
	         url: "/"+location.pathname.split("/")[1]+"/web/taskforce/service/notice/${staffprofile.staffId}",
	        
	         dataType: 'json',
	         
	         success: function(data)
	         { 
	        	 var myvar="";
	        	
	        	 if(data.length > 0){
	        		
	        		 for ( var i = 0, len = data.length; i < len;)  {
	        		
   		              var info = data[i];
   		           
   		      	 
   		      	 
		   		     var date = new Date(info.updatedDate),
			   	        mnth = ("0" + (date.getMonth()+1)).slice(-2),
			   	        day  = ("0" + date.getDate()).slice(-2);
			   	         [ date.getFullYear(), mnth, day ].join("-");

   		      
                   var sn=i+1;
   		           myvar+= '<div class="row">'+
   		           '            <div class="col-sm-2"><b>'+sn+'</b></div>'+
   		           '            <div class="col-sm-3"><b>'+info.notificatiosHeadline+'</b></div>'+
   		           ' 			 <div class="col-sm-3"><b>'+info.notificationDetails+'</b></div>'+
   		           ' 			  <div class="col-sm-3"><b>'+[ date.getFullYear(), mnth, day ].join("-")+'</b></div>'+
   		           '            </div>';
   		           	
   		      i++;
   		    		 } 
	   		         $("#notices").html(myvar);
	   				NoticeId.style.display = "block";

   		    		 
   		       } else{
	   		    	   
	   		    	   alert("Notice not found");	
	   		       }
	        	 
	         },
	         
		     error:function(data){
		    	 
		    	    alert("error");
		    	    
		     }
	    });

	

}


//When the user clicks on <span> (x), close the modal
span1.onclick = function() {
	NoticeId.style.display = "none";
}


/* .....................start Poll......................................
 */

//Get the modal
 var PollId = document.getElementById('myModal3');
 //Get the <span> element that closes the modal
 var span3 = document.getElementsByClassName("closePoll")[0];

 function getpoll() {
 

 		
 		  $.ajax({
 		         type: "POST",
 		         url: "/"+location.pathname.split("/")[1]+"/web/taskforce/service/poll/${staffprofile.comClientName.id}",
 		        
 		         dataType: 'json',
 		         
 		         success: function(data)
 		         { 
 		        	 
 		        	 var myvar1="";
 			        	
 			        	
 			        	 if(data.length > 0){
 			        		
 			        		 for ( var i = 0, len = data.length; i < len;)  {
 			        		
 		   		            var poll = data[i];
 		   		           var no=i+1;
 		                  var date = new Date(poll.to_Date),
 		                 mnth = ("0" + (date.getMonth()+1)).slice(-2),
 		                 day  = ("0" + date.getDate()).slice(-2);
 		             [ date.getFullYear(), mnth, day ].join("-");
 		                   
 		                   
 		   		           myvar1+= '<div class="row">'+
 		   		           '            <div class="col-sm-3"><b>'+no+'</b></div>'+
 		   		           ' 			 <div class="col-sm-3"><b>'+poll.question+'</b></div>'+
 		   		           ' 			  <div class="col-sm-3"><b>'+[ date.getFullYear(), mnth, day ].join("-")+'</b></div>'+
 		   		           '            </div>';
 		   		           	
 		   		      i++;
 		   		    		 } 
 			   		         $("#poll").html(myvar1);

 			   		     PollId.style.display = "block";
 
 		   		       } else{
 		   		    	   
 		   		    	   alert("poll not found");	
 		   		       }

 		        	 
 		         },
 		         
 			     error:function(data){
 			    	    alert("error");
 			     }
 		    });
 }


 //When the user clicks on <span> (x), close the modal
 span3.onclick = function() {
 	PollId.style.display = "none";
 }

 
 
//Get the modal
 var groupId = document.getElementById('myModal4');


 //Get the <span> element that closes the modal
 var span4 = document.getElementsByClassName("closeGroup")[0];

 function getgroups() {
 	
 	groupId.style.display = "block";
 		
 		

 		  $.ajax({
 		         type: "POST",
 		         url: "/"+location.pathname.split("/")[1]+"/web/taskforce/service/group/${staffprofile.comClientName.id}",
 		        
 		         dataType: 'json',
 		       
 		         success: function(data)
 		         { 
 		        	 
 		        	 
 		        	 var myvar3="";
 			        	
 			        	
 			        	 if(data.length > 0){
 			        		
 			        		 for ( var i = 0, len = data.length; i < len;)  {
 			        		
 		   		              var group1 = data[i];
 		   		              
 		                   var no3=i+1;
 		                  
 		                   
 			   		      	
 			   		     var date = new Date(group1.createdDate),
 			   	        mnth = ("0" + (date.getMonth()+1)).slice(-2),
 			   	        day  = ("0" + date.getDate()).slice(-2);
 			   	         [ date.getFullYear(), mnth, day ].join("-");

 		                   myvar3+= '<div class="row">'+
 		   		           '            <div class="col-sm-3"><b>'+no3+'</b></div>'+
 		   		           '            <div class="col-sm-3"><b>'+group1.groupName+'</b></div>'+
 		   		           '            <div class="col-sm-3"><b>'+[ date.getFullYear(), mnth, day ].join("-")+'</b></div>'+
 		   		           '            </div>';
 		   		         
 		   		           	
 		   		            i++;
 		   		            
 		   		    		 } 
 			   		         $("#grop").html(myvar3);

 		   		    		 
 		   		       } 
 			        	 else{
 	 		   		    	   
 	 		   		    	   alert("Group not found");	
 	 		   		       }

 		        	 
 		         },
 		         
         
 			     error:function(data){
 			    	    alert("error");
 			     }
 		    });


 		}


 //When the user clicks on <span> (x), close the modal
 span4.onclick = function() {
 	groupId.style.display = "none";
 }


 
 
//Get the modal
 var resultId = document.getElementById('myModal5');

 //Get the <span> element that closes the modal
 var span5 = document.getElementsByClassName("closeResult")[0];




 //When the user clicks on <span> (x), close the modal
 span5.onclick = function() {
 	resultId.style.display = "none";
 }


//When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
if (event.target == NoticeId) {
	NoticeId.style.display = "none";
}else if(event.target == modal){
	modal.style.display = "none";
}else if(event.target == PollId){
	PollId.style.display = "none";
}else if(event.target ==groupId ){
	groupId.style.display = "none";
}
else if(event.target ==resultId ){
	resultId.style.display = "none";
}

}
</script>

<!-- ------- -->

</body>
</html>