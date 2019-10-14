<%@page import="javax.naming.NoInitialContextException"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jspdf/1.2.61/jspdf.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jspdf/1.2.61/jspdf.min.js"></script>
<c:url var="gfmremarkstudent" value="/web/taskforce/service/mentor/gfmremark" />
<c:url var="studentremarksform" value="/web/taskforce/service/studentremark/remarks" />

<style>

input[type=submit] {
    background-color: green;
   color:white;
   
  }

.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */.
	left: 0;
	top: 0;
	width:100%; /* Full width */
	height: 100%; /* Full height */
	overflow:scroll; /* Enable scroll if needed */
}

/* Modal Content */
.modal-content {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	margin-left: 25%;
	width: 60%;
	border-radius: 10px;
	overflow: scroll;
	border-bottom-color: #006400;
	border-bottom-width: 5px;
}

/* The Close Button */
.close {
	color: #aaaaaa;
	float: right;
	font-size: 40px;
	font-weight: bold;
}

.closegraph3 {
	color: #aaaaaa;
	float: right;
	font-size: 40px;
	font-weight: bold;
	cursor: pointer;
}

.closegraph6 {
	color: #aaaaaa;
	float: right;
	font-size: 30px;
	font-weight: bold;
	cursor: pointer;
}
.closegraph5 {
	color: #aaaaaa;
	float: right;
	font-size: 30px;
	font-weight: bold;
	cursor: pointer;
}
.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}
.closegraph {
	color: #aaaaaa;
	float: right;
	font-size: 40px;
	font-weight: bold;
}

.closegraph:hover, .closegraph:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}
.closegraph1 {
	color: #aaaaaa;
	float: right;
	font-size: 40px;
	font-weight: bold;
}

.closegraph1:hover, .closegraph1:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}

closegraph3:hover, .closegraph3:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}


div.ex1 {
  width: 100%;
  height: 70%;
  overflow: scroll;
}
div.skillsdiv{
  width: 100%;
  height: 70%;
  overflow: scroll;
}

.label
{
color:green;
}

.closegraph4 {
	color: #aaaaaa;
	float: right;
	font-size: 40px;
	font-weight: bold;
	cursor: pointer;
}

.delete {
    border: none;
    color: white;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    margin: 1px 1px;
    cursor: pointer;
    border-radius: 3px;
    background-color: #BD1010;
}

input[type="checkbox"]:checked {
  box-shadow: 0 0 0 2px red;  
}
input:checked + p {
color:red;
}



/* The Modal (background) */
.modalviewskill{
 border: 2px solid #888;
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 230px; /* Location of the box */.
	left: 0;
	top: 0;
	width:80%; /* Full width */
	height: 120%; /* Full height */
	overflow:scroll; /* Enable scroll if needed */ 
}
.buttonviewskill {
  background-color:white;
  border: none;
  color: white;
  padding: 5px 5px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}
/* Modal Content */
.modal-contentviewskill{
 background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 2px solid green;
	margin-left: 35%;
	width: 40%;
	border-radius: 10px;
	overflow: scroll;
	border-bottom-color:green;
	border-bottom-width: 3px;}

/* The Close Button */
.closeviewskill{
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

</style>
<c:url var="addparentcallrecord" value="/web/taskforce/service/student/parentcallrecord" />
<body class="hold-transition skin-blue fixed sidebar-mini" >
<div class="wrapper">
<div class="content-wrapper">
  <c:set var="n" value="0"/>
    <!-- Main content -->
    <section class="content" >
         <div class="col-md-12" style="margin-top:21px">
		    <section class="content-header">
				<div class="col-md-12 col-sm-12 col-lg-12">
				</div>
			</section >
			<c:set var="list" value="${mentor}"/>
			
			<!-- Info boxes -->
			    <div class="col-md-12 col-sm-12 " >
				    <div class="box">
				    <div class="box-body" >
				    
				 <div class="btn-pref btn-group btn-group-justified btn-group-lg" role="group" aria-label="...">
        <div class="btn-group" role="group">
            <button type="button" id="stars" class="btn btn-success" href="#tab1" data-toggle="tab"><span class="fa fa-users" style="font-size:15px;" aria-hidden="true"></span>
                <div class="hidden-xs" style="font-size:14px;">GFM Member</div>
            </button>
        </div>
        <div class="btn-group" role="group">
            <button type="button" id="favorites" class="btn btn-default" href="#tab2" data-toggle="tab"><span class="glyphicon glyphicon-check" style="font-size:15px;" aria-hidden="true"></span>
                <div class="hidden-xs" style="font-size:14px;">Attendance</div>
            </button>
        </div>
        <div class="btn-group" role="group">
            <button type="button" id="favorites1" class="btn btn-default" href="#tab3" data-toggle="tab"><span class="glyphicon glyphicon-record" style="font-size:15px;" aria-hidden="true"></span>
                <div class="hidden-xs" style="font-size:14px;">Parent Call Record</div>
            </button>
        </div>
        <div class="btn-group" role="group">
            <button type="button" id="favorites2" class="btn btn-default" href="#tab4" data-toggle="tab"><span class="fa fa-inr" style="font-size:15px;" aria-hidden="true"></span>
                <div class="hidden-xs" style="font-size:14px;">Fee Details</div>
            </button>
        </div>
        
        <div class="btn-group" role="group">
            <button type="button" id="favorites3" class="btn btn-default" href="#tab5" data-toggle="tab"><span class="glyphicon glyphicon-registration-mark" style="font-size:15px;" aria-hidden="true"></span>
                <div class="hidden-xs" style="font-size:14px;">Remarks</div>
            </button>
        </div>
        
    </div>
				    
		 <div class="well">
      <div class="tab-content">
      <div class="tab-pane fade in active" id="tab1" style="font-size:16px;color: #5e605f ">		    
				 <table id="example1" class="table table-bordered table-striped" >
						   <thead>
				                <tr>
				                  <th style="width: 10px">#</th>
				                  <th style="width: 110px">Roll No /Emp Id</th>
				                  <th>Name</th>
				                  <th style="width: 210px">Contact Number</th>
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
					                       <td>${x.employeeNo}</td>
					                       <td><b><a href="<c:url value="/web/taskforce/service/staffprofile/${x.comClientName.id}"/>">${x.comClientName.firstName} ${x.comClientName.lastName}</a></b></td>
					                       <td>${x.comClientName.contactNos}</td>
					                       <td>${x.comClientName.emailId}</td>
					                      </tr> 
					                   </c:when>
					                   <c:otherwise>	
					                    <c:forEach var="m" items="${x.mentorStudent}">		
					                    <c:set var="n" value="${n + 1}" scope="page"/>				               
					                      <tr>
					                       <td>${n}</td>
					                       <td>${m.student.rollNo}</td>
 											<td><b><a href="<c:url value="/web/taskforce/service/result/menterstudprof/${m.student.comClientName.id}"/>">${m.student.comClientName.firstName} ${m.student.comClientName.lastName}</a></b></td>					                       
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
			             
			             
			              <div class="tab-pane fade in " id="tab2" >
								            <table id="example1" class="table table-bordered table-striped" >
											   <thead>
									                <tr align="center">
									                  <th style="width: 50px">#</th>
									                  <th > Name</th>
									                  <th >Action</th>
									                </tr>
									            </thead>
									            <tbody >
									            <c:set var="n" value="0" scope="page"/>
									             <c:forEach var="x" items="${list}" >		
									                    <c:choose>
										                  <c:when test="${!fn:contains(x, 'mentorStudent')}">
										                  <c:set var="n" value="${n + 1}" scope="page"/>
										                      <tr>
										                       <td>${n}</td>
										                       <td><b><a href="<c:url value="/web/taskforce/service/staffprofile/${x.comClientName.id}"/>">${x.comClientName.firstName} ${x.comClientName.lastName}</a></b></td>
										                	<td>N/A</td>
										                      </tr> 
										                   </c:when>
										                   <c:otherwise>	
										                    <c:forEach var="m" items="${x.mentorStudent}">		
										                    <c:set var="n" value="${n + 1}" scope="page"/>				               
										                      <tr>
										                       <td>${n}</td>
					 											<td><b><a href="<c:url value="/web/taskforce/service/result/menterstudprof/${m.student.comClientName.id}"/>">${m.student.comClientName.firstName }  ${m.student.comClientName.middleName }  ${m.student.comClientName.lastName }</a></b></td>					                       
										                       <%-- <td><a href="#">${m.student.comClientName.firstName} ${m.student.comClientName.lastName}</a></td> --%>
										                     <td> <button type="button"  onclick="deleteSubject('${m.student.comClientName.id}')" class="btn btn-block btn-success ">View Attendance</button></td>
										                     </tr>
										                     </c:forEach>	
										                   </c:otherwise>
										               </c:choose> 				                  
									                </c:forEach>  				                
									             </tbody>
								             </table>
			             </div>
			             
			              <div class="tab-pane fade in " id="tab3" >
			             <c:forEach var="x" items="${mentor}" >	
			              <c:set var="y" value="${x.id}"/>
			              
			           </c:forEach>
			           <c:url var="addparentcallrecord" value="/web/taskforce/service/student/parentcallrecord/${y} " />
			              <c:set var="parent" value="${parentdtl}"/>
                         <table id="example1" class="table table-bordered table-striped table-hover table-sm" >
			            			   <thead style="color: green" >
										                <tr  align="center">
										                  <th style="width: 50px">#</th>
										                  <th >Name</th>
										                  <th> Parent Name</th>
										                  <th >Contact No</th>
										                   <th >&nbsp;&nbsp; Add &nbsp;&nbsp;</th>
										                   <th>History</th>
										                </tr>
										            </thead>
										            
										            <tbody >
										            <c:set var="n" value="0" scope="page"/>
											                    <c:forEach var="m" items="${parent}">											                    		
											                    <c:set var="n" value="${n + 1}" scope="page"/>				               
											                      <tr>
											                       <td>${n}</td>
						 											<td><b><a href="<c:url value="/web/taskforce/service/result/menterstudprof/${m.comClientName.id}"/>">${m.comClientName.firstName }     ${m.comClientName.lastName }</a></b></td>					                       
											                     	<td>${m.comClientName.middleName } ${m.comClientName.lastName } </td>
											                     	<td>${m.contact_no1 } </td>
											                       	<td style="font-size: 20px;"><button type="button" onclick="classGroup('${m.stud_id}')" class="btn btn-block btn-success "><i class="fa fa-plus-circle"></i> </button></td>
											                       	
											                        <td style="font-size: 20px;"><button type="button" onclick="parenthistrory('${m.stud_id}')"  class="btn btn-block btn-success "><i class="fa fa-history"></i> </button></td>
											                     </tr>
											                     </c:forEach>	
											               				                
										             </tbody>
									             </table>			             
			             
			            
			             </div>
			             
			              <div class="tab-pane fade in " id="tab4" >
			             <table id="example1" class="table table-bordered table-striped table-hover table-sm" >
			             <c:set var="list1" value="${fees}"/>
			             <c:forEach var="x" items="${mentor}" >	
			              <c:set var="y" value="${x.id}"/>
			           </c:forEach>
												   <thead style="color: green" >
										                <tr  align="center">
										                  <th style="width: 50px">#</th>
										                  <th >Name</th>
										                  <th> Category</th>
										                  <th colspan="4">&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Installment</th>
										                  <th >Total Fee</th>
										                   <th >Paid Fee</th>
										                   <th >Remaining Fee</th>
										                   <th>Edit Fee Details</th>
										                </tr>
										            </thead>
										            
										            <tbody >
										            
										             <tr style="color: green" align="center">
										                 
										                  <th colspan="3"></th>
										                
										                  <th>1 <sup>st</sup></th>
										                  <th >2 <sup>nd</sup></th>
										                   <th >3 <sup>rd</sup></th>
										                   <th >4 <sup>th</sup></th>
										                   <th colspan="4"></th>
										                </tr>
										            <c:set var="n" value="0" scope="page"/>
										           
											                    <c:forEach var="m" items="${list1}">
											                <c:url var="AddFeeDetails" value="/web/taskforce/service/student/feedtl/${m.student.comClientName.id}/${y} " />
											                    		
											                    <c:set var="n" value="${n + 1}" scope="page"/>				               
											                      <tr>
											                       <td>${n}</td>
						 											<td><b><a href="<c:url value="/web/taskforce/service/result/menterstudprof/${m.student.comClientName.id}"/>">${m.student.comClientName.firstName }     ${m.student.comClientName.lastName }</a></b></td>					                       
											                       <%-- <td><a href="#">${m.student.comClientName.firstName} ${m.student.comClientName.lastName}</a></td> --%>
											                     <td>${m.category } </td>
											                     
											                      <td>${m.installment1 } </td>
											                       <td>${m.installment2 } </td>
											                        <td>${m.installment3 } </td>
											                         <td>${m.installment4 } </td>
											                     
											                     <td>${m.total_fee } </td>
											                     <td> ${m.paid }</td>
											                     <td>${m.remaining } </td>
											                     <td> <button id="cmd" class="btn  btn-success" style="margin-right: 30px; font-size: 11px;" onclick="window.location.href='${AddFeeDetails}'"><i class="fa fa-edit" ></i> Add/Update </button>
											                     </td>
											                     </tr>
											                     </c:forEach>	
											               				                
										             </tbody>
									             </table>
			             </div>
			           
			           	 <div class="tab-pane fade in " id="tab5" >
			             <h4>ADD/REMARKS</h4>
			             
			             
		 <div class="well">
      <div class="tab-content">
      <div class="tab-pane fade in active" id="tab1" style="font-size:16px;color: #5e605f ">		    
				 <table id="example1" class="table table-bordered table-striped" >
						   <thead>
				                <tr align="center">
				                  <th style="width: 10px">Sr.No.</th>
				                  <th style="width: 210px">Name</th>	
				                   <th>Add Remarks</th>
				                  <th>Remarks</th>		                  
				                  <th style="width: 150px">Add Skills</th>
				                 
				                  <th>Skills and Endorsements</th>
				                </tr>
				            </thead>
				            
				            <tbody >
				              <c:set var="n" value="0" scope="page"/>
				             <c:forEach var="x" items="${list}" >		
				             	         
				                   	    <c:forEach var="m" items="${x.mentorStudent}">		
					                    <c:set var="n" value="${n + 1}" scope="page"/>				               
					                      <tr>
					                       <td>${n}</td>					                      
 											<td><b><a href="<c:url value="/web/taskforce/service/result/menterstudprof/${m.student.comClientName.id}"/>">${m.student.comClientName.firstName} ${m.student.comClientName.lastName}</a></b></td>					                       
				 	  			
				 <td><button class="btn btn-success" style="margin-right: 20px;  background-color: red;
				  font-size: 11px;border: none;" onclick="addremarks('${m.student.comClientName.id}','${m.mentor.id}')"><i class="fa fa-check-circle" >&nbspRemarks</i></button></td>
				 <div id="remark" class="">
				 <div id="mygraph5" class="modal" >
					  <!-- Modal content -->
				<div class="modal-content">
					<span class="closegraph5">&times;</span>					
					    <h3 style="margin-left:30px;">Remarks</h3>										    
				    	<div class="container createpolls" id="progressBar" style="margin-top: 1%; width: 100%; background-color: white;">
														 
<form action="${studentremarksform}" method="post" modelAttribute="studentremark">
<input type="hidden" name="studentid" value="${m.student.comClientName.id}">
<input type="hidden" name="mid" value="${m.mentor.id}">
   <div class="col-sm-6"> 	
    <h4 style="color:green">Select Remarks</h4>				
   <input type="checkbox" name="remarkname" value="false" id="checkbox1"><i>&nbsp&nbspAttendance Shortage</i><br>
   <input type="checkbox" name="remarkname" value="false" id="checkbox2"><i>&nbsp&nbspDefaulter</i><br>
   <input type="checkbox" name="remarkname" value="false" id="checkbox3"><i>&nbsp&nbspNot Submitted Assignments</i><br>
   <input type="checkbox" name="remarkname" value="false" id="checkbox4"><i>&nbsp&nbspFees Pending</i><br>
   <input type="checkbox" name="remarkname" value="false" id="checkbox5"><i>&nbsp&nbspDocuments Not Submitted</i><br>
   <input type="checkbox" name="remarkname" value="false" id="checkbox6"><i>&nbsp&nbspNot Submitted Offer Letter</i><br>
   <input type="submit" value="Add" id="addremkbutton"><br/>
   </div>
</form>
		 
				  </div>
					</div>					
				</div>
				</div>				
			 <td><button class="btn btn delete" style="margin-right: 20px;  background-color: red;
				  font-size: 11px;border: none;" onclick="ViewRemarks('${m.student.comClientName.id}','${m.mentor.id}')"><i class="fa fa-check-circle" >View Remarks</i></button></td>				  
				  <td id="${m.student.comClientName.id}"><button id="cmd" class="btn  btn-success" style="margin-right: 20px; font-size: 11px;" onclick="myFunction('${m.student.comClientName.id}','${m.mentor.id}')"><i class="fa fa-plus-circle" >Add Skill</i></button></td>	
					                  
					                   <td>					               
					                <button id="cmd" class="btn  btn-success" style="margin-right: 20px; font-size: 11px;" onclick="ViewSkills('${m.student.comClientName.id}','${m.mentor.id}')"><i class="fa fa-plus-circle" >View Skills</i></button>
					                   </td>					                   
					                    <div id="mygraph3" class="modal" >
					  <!-- Modal content -->
				<div class="modal-content" >
					<span class="closegraph3">&times;</span>
				<div class="container createpolls" id="progressBar" style="margin-top: 1%; width: 100%; background-color: white;">					
					    <h3 style="color:green">Add Skills</h3>
					    					 		 						 
<div id="myDIV" class="ex1">
<form action="${gfmremarkstudent}" method="post" modelAttribute="gfmremark">
<input type="hidden" name="studentid" value="${m.student.comClientName.id}">
<input type="hidden" name="mid" value="${m.mentor.id}">
<input type="hidden" name="techFields" id="techFields">

 <div class="row">
  <div class="col-sm-6" id="techId"> 					
  <label>Technical Skill and Tools & Technologies</label><br>
	 <input type="checkbox" class="Technical Skill and Tools & Technologies" name="skillsname1" value="Microsoft Office" id="MenuIDMicrosoft Office">Microsoft Office<br>
	 <input type="checkbox" class="Technical Skill and Tools & Technologies" name="skillsname1" value="Microsoft Excel" id="MenuIDMicrosoft Excel">Microsoft Excel<br>
	 <input type="checkbox" class="Technical Skill and Tools & Technologies" name="skillsname1" value="Matlab" id="MenuIDMatlab">Matlab<br>
	 <input type="checkbox" class="Technical Skill and Tools & Technologies" name="skillsname1" value="PowerPoint" id="MenuIDPowerPoint">PowerPoint<br>
	 <input type="checkbox" class="Technical Skill and Tools & Technologies" name="skillsname1" value="Microsoft Word" id="MenuIDMicrosoft Word">Microsoft Word<br>
	 <input type="checkbox" class="Technical Skill and Tools & Technologies" name="skillsname1" value="Solid Works" id="MenuIDSolid Works">Solid Works<br>
	 <input type="checkbox" class="Technical Skill and Tools & Technologies" name="skillsname1" value="CATAI" id="MenuIDCATAI">CATAI<br>
	 <input type="checkbox" class="Technical Skill and Tools & Technologies" name="skillsname1" value="Microsoft Power Point" id="MenuIDMicrosoft Power Point">Microsoft Power Point<br>      
	 <input type="checkbox" class="Technical Skill and Tools & Technologies" name="skillsname1" value="Software Suite Skills" id="MenuIDSoftware Suite Skills">Software Suite Skills<br>
	 <input type="checkbox" class="Technical Skill and Tools & Technologies" name="skillsname1" value="Information Security" id="MenuIDInformation Security">Information Security<br>
	 <input type="checkbox" class="Technical Skill and Tools & Technologies" name="skillsname1" value="Analysis of Big Data and Business Intelligence" id="MenuIDAnalysis of Big Data and Business Intelligence">Analysis of Big Data and Business Intelligence<br>
	 <input type="checkbox" class="Technical Skill and Tools & Technologies" name="skillsname1" value="Online Safety and Security" id="MenuIDOnline Safety and Security">Online Safety and Security<br> 
	 <input type="checkbox" class="Technical Skill and Tools & Technologies" name="skillsname1" value="Project Management" id="MenuIDProject Management">Project Management<br>
	 <input type="checkbox" class="Technical Skill and Tools & Technologies" name="skillsname1" value="Writer" id="MenuIDWriter">Writer<br>    
   </div>   
   
 <div class="col-sm-6" id="intSkills">
  
  <label>Interpersonal Skills</label><br>
  <input type="checkbox" class = "Interpersonal Skills" name="skillsname2" value="Good Listener" id="MenuIDGood Listener">Good Listener<br>
  <input type="checkbox" class = "Interpersonal Skills" name="skillsname2" value="Self Confidence" id="MenuIDSelf Confidence">Self Confidence<br>
  <input type="checkbox" class = "Interpersonal Skills" name="skillsname2" value="Work Ethic" id="MenuIDWork Ethic">Work Ethic<br>
  <input type="checkbox" class = "Interpersonal Skills" name="skillsname2" value="Relationship Management" id="MenuIDRelationship Management">Relationship Management<br>
  <input type="checkbox" class = "Interpersonal Skills" name="skillsname2" value="TeamWork" id="MenuIDTeamWork">TeamWork<br>
  <input type="checkbox" class = "Interpersonal Skills" name="skillsname2" value="Body Language" id="MenuIDBody Language">Body Language<br>
  <input type="checkbox" class = "Interpersonal Skills" name="skillsname2" value="Collaboration" id="MenuIDCollaboration">Collaboration<br>
  <input type="checkbox" class = "Interpersonal Skills" name="skillsname2" value="Life Coaching" id="MenuIDLife Coaching">Life Coaching<br>  
  <input type="checkbox" class = "Interpersonal Skills" name="skillsname2" value="Positive Attitude" id="MenuIDPositive Attitude">Positive Attitude<br> 
  <input type="checkbox" class = "Interpersonal Skills" name="skillsname2" value="Workplace Etiquette" id="MenuIDWorkplace Etiquette">Workplace Etiquette<br>
  </div>
  </div>
  
  
  <div class="row">
  <div class="col-sm-6" id ="comSkill"> 
    <label>Communication Skills</label><br>
	  <input type="checkbox" class="Communication Skills" name="skillsname3" value="Emotional Intelligence" id="MenuIDEmotional Intelligence">Emotional Intelligence<br>
	  <input type="checkbox" class="Communication Skills" name="skillsname3" value="Cohesion and Clarity" id="MenuIDCohesion and Clarity">Cohesion and Clarity<br> 
	  <input type="checkbox" class="Communication Skills" name="skillsname3" value="Friendliness" id="MenuIDFriendliness">Friendliness<br>  
	  <input type="checkbox" class="Communication Skills" name="skillsname3" value="Confidence" id="MenuIDConfidence">Confidence<br>
	  <input type="checkbox" class="Communication Skills" name="skillsname3" value="Open Mindedness" id="MenuIDOpen Mindedness">Open Mindedness<br>
  </div>
  
  <div class="col-sm-6" id ="softSkill">
  <label>Soft Skills</label><br>
		 <input type="checkbox" class = "Soft Skills" name="skillsname4" value="Self Motivation" id="MenuIDSelf Motivation">Self Motivation<br>
		 <input type="checkbox" class = "Soft Skills" name="skillsname4" value="Leadership" id="MenuIDLeadership">Leadership<br>  
		 <input type="checkbox" class = "Soft Skills" name="skillsname4" value="Responsibility" id="MenuIDResponsibility">Responsibility<br>
		 <input type="checkbox" class = "Soft Skills" name="skillsname4" value="Good Sense Of Humor" id="MenuIDGood Sense Of Humor">Good Sense Of Humor<br>
		 <input type="checkbox" class = "Soft Skills" name="skillsname4" value="Hard Worker" id="MenuIDHard Worker">Hard Worker<br>
		 <input type="checkbox" class = "Soft Skills" name="skillsname4" value="Teamwork" id="MenuIDTeamwork">Teamwork<br>
		 <input type="checkbox" class = "Soft Skills" name="skillsname4" value="Problem Solving" id="MenuIDProblem Solving">Problem Solving<br>
		 <input type="checkbox" class = "Soft Skills" name="skillsname4" value="Decisiveness" id="MenuIDDecisiveness">Decisiveness<br>
		 <input type="checkbox" class = "Soft Skills" name="skillsname4" value="Ability to Work Under Pressure and Time Management" id="Ability to Work Under Pressure and Time Management">Ability to Work Under Pressure and Time Management<br>
		 <input type="checkbox" class = "Soft Skills" name="skillsname4" value="Flexibility" id="MenuIDFlexibility">Flexibility<br>
		</div>
  	 </div>
  	
  <div class="row">
  	 <div class="col-sm-6" id ="indKno"> 
      <label>Industry Knowledge</label><br>
		  <input type="checkbox" class="Industry Knowledge" name="skillsname5" value="Research" id="MenuIDResearch">Research<br>
		  <input type="checkbox" class="Industry Knowledge" name="skillsname5" value="Engineering" id="MenuIDEngineering">Engineering<br> 
		  <input type="checkbox" class="Industry Knowledge" name="skillsname5" value="Manufacturing" id="MenuIDManufacturing">Manufacturing<br>  
		  <input type="checkbox" class="Industry Knowledge" name="skillsname5" value="Data Analysis" id="MenuIDData Analysis">Data Analysis<br>  
		  <input type="checkbox" class="Industry Knowledge" name="skillsname5" value="Project Planning" id="MenuIDProject Planning">Project Planning<br>  
    </div>
  	 <div class="col-sm-6" id ="lang">
  	 <label>Languages</label><br>
	    <input type="checkbox" class = "Languages" name="skillsname6" value="English" id="MenuIDEnglish">English<br>
	    <input type="checkbox" class = "Languages" name="skillsname6" value="Hindi" id="MenuIDHindi">Hindi<br>
	    <input type="checkbox" class = "Languages" name="skillsname6" value="Marathi" id="MenuIDMarathi">Marathi<br>
	  </div>
  	 </div>
  	
  	 	 
  <input type="submit" value="Add" id = "addButton">
  
</form>
</div>



</div>					 
					</div>
				</div>
	
					                    
					                     </tr>
					                     
					
		
					                     </c:forEach>						                 			                  
				                </c:forEach>  				                
				             </tbody>
			             </table>
			             </div>
			        
			             </div>
			             
			             </div></div>
			            </div>
				    </div>
		        </div>
			</div>
			
			<!-- .........................................Subject Group Popup............................................ -->
			 <div id="mygraph" class="modal" >
					  <!-- Modal content -->
				<div class="modal-content">
					<span class="closegraph">&times;</span>
					    <h3>Attendance</h3>
					  <hr class="yello_hr">
					 <h3 id="graphquesion"></h3>
				    	<div class="container createpolls" id="progressBar" style="margin-top: 1%; width: 100%; background-color: white;">							
							   <table id="example" class="table table-bordered table-striped"  >
				               <thead>
				                       <tr>
				                        <th style="width: 50px;">sr no</th>
				                             <th> Subject Name</th>
				                             <th> Attendance</th>
				       
				     </tr>
				   </thead>
				   <tbody id="recuiritor" >
				   </tbody> 
			    </table>
							
					  </div>
					</div>
				</div>
				
				<!-- .......................................END....................................... -->
	<!-- ......................................... Classvise Group Popup......................................... -->
			
			<div id="mygraph1" class="modal" >
					  <!-- Modal content -->
				<div class="modal-content">
					<span class="closegraph1">&times;</span>
					    <h3>Call Record Of Parent</h3>
					  <hr class="yello_hr">
				    	<div class="container createpolls" id="progressBar" style="margin-top: 1%; width: 100%; background-color: white;">
							<h3>Comming Soon..</h3>
				<%--  <form action="${addparentcallrecord}" method="post">
			   
				<input type="hidden" id="studid" name="studid">
				 
						<div class="row catagory">
  						   <div class="col-sm-2"></div>
    						<div class="col-sm-4"><b> Date: </b></div>
    						<div class="col-sm-4"><b> Remark/Reason : </b></div>
    						
  						</div>
  						
  						<div class="row">
  						<div class="col-sm-2"></div>

									<div class='col-sm-4'>
										<div class="form-group">

											<div class='input-group date' id='datetimepicker1'>
												<input type='text' name="date" class="form-control"
													required="required" id="datemain" placeholder="select date"/>
												<span class="input-group-addon"> <span
													class="glyphicon glyphicon-calendar"></span>
												</span>
											</div>
										</div>
									</div>			    			 <div class="col-sm-4"><input type="text"  name="remark" value="" required placeholder=""> </div>	
			    			 
	   						
		       			</div>
		       				<hr>
		       		<div class="row">
  						    <div class="col-sm-5"></div>
    						
    						<div class="col-sm-4">
    						<input type="submit" onclick="return Validate()" value="Submit">&nbsp;&nbsp;&nbsp;
  						</div>
					  </div>
					   </form> --%>
					</div>
				</div>
				</div>
			<!-- .................................................END................................................. -->
			
			
				<!-- ......................................... Classvise Group Popup......................................... -->
			
			<div id="mygraph2" class="modal" >
					  <!-- Modal content -->
				<div class="modal-content">
					<span class="closegraph2">&times;</span>
					    <h3>History</h3>
					  <hr class="yello_hr">
				    	<div class="container createpolls" id="progressBar" style="margin-top: 1%; width: 100%; background-color: white;">
							 <h3>Comming Soon..</h3>
					  </div>
					</div>
				</div>
				
				
				<!-- .........................................  Student skills Popup......................................... -->
				
			<div id="mygraph4" class="modal" >
					  <!-- Modal content -->
				<div class="modal-content">
					<span class="closegraph4">&times;</span>
					    <h3>Student Skills</h3>
					  <hr class="yello_hr">
				    	<div class="container createpolls" id="progressBar" style="margin-top: 1%; width: 100%; background-color: white;">
							 
          <div id="skilltypes" class="skillsdiv">
         
            		    		 	      					         		
				</div>
				
							 
					  </div>
					</div>
				</div>
		<!-- .........................................  studentend........................................ -->
				<div id="myModalviewskill" class="modalviewskill">

  <!-- Modal content -->
  <div class="modal-contentviewskill">
    <span class="closeviewskill">&times;</span>
    <p>coming soon</p>
  </div>

</div>
		<!-- .........................................  Student Remarks Popup......................................... -->
			
			<div id="mygraph6" class="modal" >
					  <!-- Modal content -->
				<div class="modal-content">
					<span class="closegraph6">&times;</span>
					    <h3>Student Remarks</h3>
					  <hr class="yello_hr">
				    	<div class="container createpolls" id="progressBar" style="margin-top: 1%; width: 100%; background-color: white;">							 
                   
                   <div id="remarklist">
               
                   </div>
                   
                   
					  </div>
					</div>
				</div>
		<!-- .........................................  Student skills end......................................... -->
				
	<!-- .................................................END................................................. -->
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
        paging:false
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

<script type="text/javascript">

  $(document).ready(function() {
	  $(".btn-pref .btn").click(function () {
	      $(".btn-pref .btn").removeClass("btn-success").addClass("btn-default");
	      // $(".tab").addClass("active"); // instead of this do the below 
	      $(this).removeClass("btn-default").addClass("btn-success");   
	   });
	  
	  $("#checkbox1").on('change', function() {
	    if ($(this).is(':checked')) {
	      $(this).attr('value', 'true');
	    } else {
	      $(this).attr('value', 'false');
	    }
	    
	  });
	  
	  
	  $("#addButton").click(function(){
		 
		  var skillsname1 = [];
		  $('#techId input:checked').each(function() {
			  skillsname1.push($(this).attr('class')+"-"+$(this).attr('value'));
		  });
		  
		  $('#intSkills input:checked').each(function() {
			  skillsname1.push($(this).attr('class')+"-"+$(this).attr('value'));
		  });
		  
		  $('#comSkill input:checked').each(function() {
			  skillsname1.push($(this).attr('class')+"-"+$(this).attr('value'));
		  });
		  
		  $('#softSkill input:checked').each(function() {
			  skillsname1.push($(this).attr('class')+"-"+$(this).attr('value'));
		  });
		  
		  $('#indKno input:checked').each(function() {
			  skillsname1.push($(this).attr('class')+"-"+$(this).attr('value'));
		  });
		  
		  $('#lang input:checked').each(function() {
			  skillsname1.push($(this).attr('class')+"-"+$(this).attr('value'));
		  });
		  $("#techFields").val(skillsname1);
		  
	    });
	  });
  </script>
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-127607784-1"></script>

<script type="text/javascript">


	// Get the modal
	var modal = document.getElementById('myModal');
	var modal1 = document.getElementById('myModal');
	var graphid = document.getElementById('mygraph');
	var graphid1 = document.getElementById('mygraph1');
	var graphid2 = document.getElementById('mygraph2');
	var graphid3 = document.getElementById('mygraph3');
	var graphid4 = document.getElementById('mygraph4');
	var graphid5 = document.getElementById('mygraph5');
	var graphid6 = document.getElementById('mygraph6');
	// Get the button that opens the modal
	var btn = document.getElementById("myBtn");
	
	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];
	var closegraph = document.getElementsByClassName("closegraph")[0];
	var closegraph1 = document.getElementsByClassName("closegraph1")[0];
	var closegraph2 = document.getElementsByClassName("closegraph2")[0];
	var closegraph3 = document.getElementsByClassName("closegraph3")[0];
	var closegraph4 = document.getElementsByClassName("closegraph4")[0];
	var closegraph5 = document.getElementsByClassName("closegraph5")[0];
	var closegraph6 = document.getElementsByClassName("closegraph6")[0];
	// When the user clicks the button, open the modal 

	
	function deleteSubject(clientid) {
		 $.ajax({
		         type: "GET",
		         url: "/"+location.pathname.split("/")[1]+"/web/taskforce/service/student/Attendance/"+clientid,
		         dataType: 'json',
		          success: function(data){
		       	  
		        		  var list =data;
		        	 
		        	 for ( var i = 0, len = list.length; i < len; )  {
				        var inf =list[i];
				       // alert(inf);
				          var sn=i+1;
				           $('#recuiritor').append("<tr>");
				      	   $('#recuiritor').append("<td align='center'>"+ sn +"</td>");
				 		   $('#recuiritor').append("<td>" + inf.subName+ " </td>");
				 		  
				 		   var inf1 = inf.list;
				 		
				 		  for ( var j = 0, len = inf1.length; j < inf1.list; )
				 			  {
				 			     $('#recuiritor').append("<td>" +inf1[j].attendance +"</td>");
				 			  j++;
				 			  }
				 		   $('#recuiritor').append("</tr><br><br>");
				         i++ ;  	
		 	    	}  
				  },
			     error:function(data){
			     }
		    }); 
		graphid.style.display = "block";
		
	}
	function classGroup(id) {
		$("#studid").val(id);
		graphid1.style.display = "block";
		
	}
	
	function parenthistrory(id) {
		graphid2.style.display = "block";
		
	}
	
	
	function myFunction(id,mid) {
			
		 $.ajax({
	         type: "GET",
	         url: "/"+location.pathname.split("/")[1]+"/web/taskforce/service/mentorstudent/remarklist",
	         data:'id='+ id +'&mid='+mid,	         
	          success: function(data){
	        	  var myvar="";	
	       	if(data.length > 0){
	       	var remark=data;
	       	var count="1";
	    		 for ( var i = 0, len = remark.length; i < len; ++i)  {	    			 

	    			 var readk =remark[i];
	    				 	     document.getElementById("MenuID"+readk.skilltype).checked = true; 
	    				 	     myvar+= readk.skillstypeid1+'&nbsp';	    				 	  
	    				 	  count++;	    				 	
	 		}
	    		  $("#skill").html(myvar); 
	       	}  
	        
			  },
		     error:function(data){
		     }
	    }); 
		 graphid3.style.display = "block";
		}
	
	
	
	
	function ViewSkills(studid,mentid) {
		
		 $.ajax({
	         type: "GET",
	         url: "/"+location.pathname.split("/")[1]+"/web/taskforce/service/studentskill/skillist",
	         data:'studid='+ studid +'&mentid='+mentid,	         
	          success: function(data)
	          {
	        	  var myvar=" ";
	        	  	        	    			    		
	        	  	      $.each(data, function(key, value ) {
	   		        			myvar+= '<div >'+
	   		        			'	   		        			         <h4 style="font-size:18px;color:green">'+key+'</h4>'+
	   		        			'	   		        			      <button class="buttonviewskill" id="myBtn"><i class="fa fa-plus-circle" style="font-size:20px;color:red"></i></button>'+
	   		        			'	   		        			            <span style="color:#000000">'+value+'</span>      		    		 	         					         		'+
	   		        			'	   		        							</div>';	   		        				   		        				
	    		        		});
	   		        		 $("#skilltypes").html(myvar);
	    		
			  },
		     error:function(data){
		     }
	    }); 		
		graphid4.style.display = "block";
		
	}
		
	function addremarks(stuid,mid) {
		
		$.ajax({
	         type: "GET",
	         url: "/"+location.pathname.split("/")[1]+"/web/taskforce/service/student/studcheckrmark",
	         data:'stuid='+ stuid +'&mid='+mid,	         
	          success: function(data)
	          {
	        	  
	         
		       	var studremklist=data;		    		       	
		    		 for ( var i = 0, len = studremklist.length; i < len; ++i) 
		    		 {	    			 
		    			var readk =studremklist[i];
		    			
		    		document.getElementById("studremkid"+readk).checked = true;  		    		
		 		    }		    		  		    			           	           
			  },
			    error:function(data){
			    	   // alert("error");
			     }
		    }); 		
			graphid5.style.display = "block";			
	}
	
	
	function ViewRemarks(stuid,mid) {
		
		 $.ajax({
	         type: "GET",
	         url: "/"+location.pathname.split("/")[1]+"/web/taskforce/service/student/studremarkview",
	         data:'stuid='+ stuid +'&mid='+mid,	         
	          success: function(data)
	          {	      
	        	  var remark=" ";
	           alert(data);
	           var rmklist=data;
	           
	    		 for ( var i = 0, len = rmklist.length; i < len; ++i) 
	    		 {	    			 
	    			var studremk =rmklist[i];
	    			alert(studremk);
	    			remark+='<i class="fa fa-check-square-o" style="font-size:18px;color:red"></i>'+studremk+'<br/>';	    			
	    					    		 
	    		 }
	    		 $("#remarklist").html(remark);
	           
	          },
			    error:function(data){
			    	   // alert("error");
			     }
		    }); 		
		 
			graphid6.style.display = "block";			
	}
	
	//............................................................................................................
		// When the user clicks on <span> (x), close the modal
		
		closegraph.onclick = function() {
			graphid.style.display = "none";
		}
		
		closegraph1.onclick = function() {
			graphid1.style.display = "none";
			
		}
		
		closegraph2.onclick = function() {
			graphid2.style.display = "none";
			
		}
		closegraph3.onclick = function() {
			graphid3.style.display = "none";
			
		}
		
		closegraph4.onclick = function() {
			graphid4.style.display = "none";
			
		}
		
		closegraph5.onclick = function() {
			graphid5.style.display = "none";
			
		}
		
		closegraph6.onclick = function() {
			graphid6.style.display = "none";
			
		}

	// When the user clicks anywhere outside of the modal, close it	
	window.onclick = function(event) {
		if (event.target == graphid) {
			graphid.style.display = "none";
			
		}
		if (event.target == graphid1) {
			graphid1.style.display = "none";
			
		}
		
		if (event.target == graphid2) {
			graphid2.style.display = "none";
			
		}

		if (event.target == graphid3) {
			graphid3.style.display = "none";
			
		}
		if (event.target == graphid4) {
			graphid4.style.display = "none";
			
		}
		if (event.target == graphid5) {
			graphid5.style.display = "none";
			
		}
		if (event.target == graphid6) {
			graphid6.style.display = "none";
			
		}
	}

 </script>
 
 <script type="text/javascript">
 
 
 
 </script>
<script type="text/javascript">
$(function() {
	$('#datetimepicker1').datetimepicker();
})
</script>

<script>
// Get the modal
var modal = document.getElementById("myModalviewskill");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("closeviewskill")[0];

// When the user clicks the button, open the modal 
btn.onclick = function() {
  modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}
</script>
  <script>
 
        
      </script>
</body>
