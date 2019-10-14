<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.zertones.model.ComClientName"%>
<%@page import="com.zertones.model.sims.Staff"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<style>
.bt
{
   font-size:10px;
   background-color:#d3efc6;
   
}
/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */.
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
	
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
.yello_hr {
   height: 2px;
   color: #ffe63b;
   background-color: #ffe63b;	
  }	

</style>
<style>
.loader {
  border: 16px solid #f3f3f3;
  border-radius: 50%;
  border-top: 16px solid #3498db;
  width: 60px;
  height: 60px;
  -webkit-animation: spin 2s linear infinite; /* Safari */
  animation: spin 2s linear infinite;
}

/* Safari */
@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
</style>
<script>
 $(function () {
   $(".select2").select2();
 });	 
</script>   
   <c:url var="createGroup" value="/web/taskforce/group/create" />
   <c:url var="subjectName" value="/web/taskforce/subjectgroup/create" />
    <c:url var="ClassNamegroup" value="/web/taskforce/classgroup/create" />
      <body class="hold-transition skin-blue fixed sidebar-mini">
 <div class="wrapper" >
  <div class="content-wrapper">
	  <section class="content">  
	    <div class="box-body" style="margin-top: 20px">
	       <span class="pull-left" ><font size="5">Add New Group </font></span>
	    </div>
	     <div class="col-md-12 col-sm-12 " >
	     <div class="box">
		<div class="box-body" >
				<div class="modal-body" >
					<form:form id="groupForm" modelAttribute="groupForm" commandName="groupForm" action="${createGroup}" method="post" class="pure-form pure-form-aligned" role="form">
						 <div class="content-header">		
						 <label class="control-label" style="color:#008000; margin-left:15px;"for="value">Select Group Of:</label><br>
					    <label style="padding-left: 30px;padding-right: 10px"> Student </label><input type="radio" onclick="getInfo()" id="student" name="groupFor" value="11"required="required" >						
						<label style="padding-left: 30px;padding-right: 10px"> Staff </label><input type="radio" onclick="getInfo()" id="staff" name="groupFor" value="22" required="required"></h4>
					<label  id="subgroup" style="padding-left: 30px;padding-right: 10px; margin-left:30%;"> <button  onclick="deleteSubject()" class="btn btn-info"><b>Create Subject Group</b> </button></label>
					<label  id="subgroup1" style="padding-left: 0px;padding-right: 10px; margin-left:0%;"> <button  onclick="classGroup()" class="btn btn-info"><b>Create Class  Group</b> </button></label>
					
					</div>	<br>
					  <div class="col-sm-12">
					      <div class="col-sm-6">
						 	<label class="control-label" style="color:#008000" for="value" >Group Type:</label>
							 <form:select path="groupType" onchange="getInfo()" id="groupType" class="form-control select2" data-placeholder="Select Group Type..." style="width: 100%">
				                  <form:option value="">  </form:option>
				                  <form:option value="1"> College </form:option>
<%-- 				                  <form:option value="2"> Subject </form:option>
 --%>				                  <form:option value="3"> Social </form:option>
				                  <form:option value="4"> Department </form:option>				                  
			                 </form:select>
			              </div>
					 	  <div class="col-sm-6">
					 	     <form:hidden path="department" value="${sessionScope.user.department}"/>
							 <label   class="control-label" style="color:#008000"for="value">Group Name:</label>
							 <form:input  path="groupName" required="required" class="form-control" placeholder="Group Name" /><br>
						   </div>						   
					  </div>
					<br>
					<c:set var="staffLst" value="${stafflist}"/>	
					 <c:set var="studentLst" value="${studentlist}"/>
					 
					 
						<div class="col-sm-12">
					      <div class="col-sm-6" id="incharge">
					      <label class="control-label" style="color:#008000"for="id">Add Student In Group:
					      </label>
					     <div id="loderId" style="display: none" ><div class="loader" ></div><label>Loding Student ...</label></div>
							 

					         <select name="member" id="studentsId" multiple="multiple" class="form-control select2 studLIstID"  data-placeholder="Select Members..." style="width: 100%" >
				               <%-- <c:forEach var="Sitem" items="${studentlist}">
								<c:set var = "dep" value = "${Sitem.depName}"/>
      							<c:set var = "depname" value = "${fn:substring(dep, 0, 4)}" />
								  <option  value="${Sitem.id}" ${Sitem.id == selectedDept ? 'selected="selected"' : ''}>${Sitem.firstName} ${Sitem.middleName} ${Sitem.lastName}  (${Sitem.year} - ${depname})</option>							   
							   </c:forEach>  --%>
							  </select>	
							  
							  
			              </div>
						  <div class="col-sm-6" id="incharge1">						  
						    <label class="control-label" style="color:#008000" for="value" >Select Incharge:</label>
							 <form:select id="staffId" path="groupIncharge_1" class="form-control select2" style="width: 100%">
				                  <option value="">Select Incharge</option>
				                  <%-- <c:forEach var="Sitem" items="${staffLst}">
								        <option value="${Sitem.id}" ${Sitem.id == selectedDept ? 'selected="selected"' : ''}>${Sitem.firstName}  ${Sitem.lastName}</option>								   
								  </c:forEach> --%> 
			                </form:select>
			                <form:hidden  path="groupIncharge_2" value="${sessionScope.user.comClientName.id}"></form:hidden>
					       <br>
						</div>
						
						</div>
						
						<div class="col-sm-12">
						
						<br>
						  <div class="col-sm-6" id="incharge2">
					      <label class="control-label" style="color:#008000"for="id">Add Staff In Group:</label>
					        <select name="member" id="staffId1" multiple="multiple" class="form-control select2"  data-placeholder="Select Members..." style="width: 100%">
				               <%-- <c:forEach var="Sitem" items="${studentlist}">
								<c:set var = "dep" value = "${Sitem.depName}"/>
      							<c:set var = "depname" value = "${fn:substring(dep, 0, 4)}" />
								  <option  value="${Sitem.id}" ${Sitem.id == selectedDept ? 'selected="selected"' : ''}>${Sitem.firstName} ${Sitem.middleName} ${Sitem.lastName}  (${Sitem.year} - ${depname})</option>							   
							   </c:forEach>  --%>
							  </select>	
			              </div>
			               <div class="col-sm-6"></div>
			          <br><br><br><br>
					</div>
				     <div class="form-group">
						<div class="fileupload ">
							<input type="submit" style="background-color:#d3efc6" value="Create New Group" class="btn ">
						</div> 
					</div>
				</form:form>
				
	<!-- .........................................Subject Group Popup............................................ -->
			 <div id="mygraph" class="modal" >
					  <!-- Modal content -->
				<div class="modal-content">
					<span class="closegraph">&times;</span>
					    <h3>Select Assign Subject</h3>
					  <hr class="yello_hr">
					 <h3 id="graphquesion"></h3>
				    	<div class="container createpolls" id="progressBar" style="margin-top: 1%; width: 100%; background-color: white;">
											
							<form action="${subjectName}" method="post">
							 <div class="row">
					  		      <div class="col-sm-8">
					  		      <input type="hidden"  name="clientId" value="${sessionScope.user.comClientName.id}">
							         <label>Select Subject :</label> 
							         <br>
							          <c:if test="${empty subjectlist}">
									       <div><h5><b><font color=red>*</font> Sorry.. Not Assign Any Subject For You </b><br></h5></div>
							           </c:if>
							            <select name="subjectId" class="form-control select2" data-placeholder="Select sub..." style="width: 60%"  required="required">
							           
							            <c:forEach var="sublist" items="${subjectlist}">
											<option value="${sublist.academicSubject.sub_id}">${sublist.academicSubject.subject_name}</option>								   
										</c:forEach>
									   </select>
							       </div>
							       
							       <div class="col-sm-4">
					  		     
							         <label>Select Division :</label> 
							         <br>
							            <select name="div" class="form-control select2" data-placeholder="Select sub..." style="width: 60%"  required="required">
							          	  <option value="1">A</option>
										  <option value="2">B</option>
										  <option value="3">C</option>
										  <option value="4">D</option>
										  <option value="5">E</option>
										  <option value="6">F</option>
									   </select>
							       </div>
							    </div>
							 	<div class="row" >
							     <div align="center" style="padding-top: 20px">
							    	    <input type="submit" onclick="return Validate()" value="Create" class="btn btn-success" />
							     </div>
							  </div>
						 </form>		 						
					  </div>
					</div>
				</div>
				
				
	<!-- ......................................... Classvise Group Popup......................................... -->
			 <div id="mygraph1" class="modal" >
					  <!-- Modal content -->
				<div class="modal-content">
					<span class="closegraph1">&times;</span>
					    <h3> Class Group</h3>
					  <hr class="yello_hr">
					 <h3 id="graphquesion"></h3>
				    	<div class="container createpolls" id="progressBar" style="margin-top: 1%; width: 100%; background-color: white;">
											
							<form action="${ClassNamegroup}" method="post">
							 <div class="row">
					  		      <div class="col-sm-6">
<%-- 					  		      <input type="hidden"  name="clientId" value="${sessionScope.user.comClientName.id}">
 --%>							         <label>Select Class :</label> 
							         <br>
							         
							            <select name="yearId" class="form-control select2" data-placeholder="Select sub..." style="width: 60%"  required="required">
							          	  <option value="1">FE</option>
										  <option value="2">SE</option>
										  <option value="3">TE</option>
										  <option value="4">BE</option>
									   </select>
							       </div>
							        <div class="col-sm-6">=
					  		     
							         <label>Select Division :</label> 
							         <br>
							          
							            <select name="div" class="form-control select2" data-placeholder="Select sub..." style="width: 60%"  required="required">
							          	  <option value="1">A</option>
										  <option value="2">B</option>
										  <option value="3">C</option>
										  <option value="4">D</option>
										  <option value="5">E</option>
										  <option value="6">F</option>
									   </select>
							       </div>
							    </div>
							    <div class="row">
					  		      <div class="col-sm-6"><br>
					  		       <label>Select ClassTeacher :</label> 
							         <br>
							         
 						            <select name="staffId" class="form-control select2" data-placeholder="Select sub..." style="width: 60%"  required="required">
					  		       <c:set var="StaffList" value="${DeptStfflist}"/>
					  		       <c:forEach var="saflst" items="${StaffList}">
											<option value="${saflst.id}">${saflst.firstName} &nbsp;${saflst.lastName}</option>								   
										</c:forEach>
					  		       </select>
					  		      </div>
					  		      </div>
							 	<div class="row" >
							     <div align="center" style="padding-top: 20px">
							    	    <input type="submit" onclick="return Validate()" value="Create" class="btn btn-success" />
							     </div>
							  </div>
						 </form>		 						
					  </div>
					</div>
				</div>
				
			</div>
			</div>
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
<script>
 function getInfo() {
	  
	  var stffId="${sessionScope.user.comClientName.id}";
	
	  var forValue = 1;
	  if (document.getElementById('staff').checked) {
		 	forValue=$("#staff").val();
		 	$('#incharge').hide();
		 	$('#incharge1').hide();
		 	$('#incharge2').show();
		 	$('#subgroup').hide();
		 	$('#subgroup1').hide();
		}else{
			forValue=$("#student").val();
			$('#incharge').show();
			$('#incharge1').show();
			$('#incharge2').hide();
			$('#subgroup').hide();
			$('#subgroup1').hide();
		}
	var groupType=$("#groupType").val(); 
	//alert("groupfor :"+ forValue + " grouptype:"+groupType);
	//test(forValue ,groupType,stffId);
  }  
  
 $("#groupType").change(function(){
	  	var x= document.getElementById("groupType").value;
	    var y="${sessionScope.user.department}"; 
	    var user="${sessionScope.user.comClientName.id}";
	// alert(x);
	// alert(y);
	  if (document.getElementById('student').checked) {
		  
		  $('.studLIstID').hide();
		  $('#loderId').show();
			
			
	  }
	 var datalist="";
	    $.ajax({
	         type: "POST",
	         url: "/"+location.pathname.split("/")[1]+"/web/taskforce/user/blank/stud",
	         data:'gt='+ x +'&dept='+ y,
	         dataType: 'json',
	         success: function(data){
	        	
			    	 if(data.length > 0){
			    		 var srno=1;
			    		 for ( var i = 0, len = data.length; i < len; ++i)  {
			          var info = data[i];
			          
			          datalist+="<option value='"+info.id+"'>"+ info.firstName +"&nbsp;&nbsp;"+ info.middleName +"&nbsp;&nbsp;"+ info.lastName +"&nbsp;&nbsp;" +srno+"</option>" ;
			 		   srno++;
			 		}
			    		 $('#loderId').hide();
				 			$('.studLIstID').show();
			    		 $('#studentsId').html(datalist);
			       } 
			     },
	         
		     error:function(data){
		    	    alert("error");
		     }
	    });
	     
	  var datalist1="";
	    $.ajax({
	         type: "POST",
	         url: "/"+location.pathname.split("/")[1]+"/web/taskforce/user/blank/staff",
	         data:'gt='+ x +'&dept='+ y,
	         dataType: 'json',
	          success: function(data){
			    	
			    	 if(data.length > 0){
			    		 var srno=1;
			    		 for ( var i = 0, len = data.length; i < len; ++i)  {
			         	 var info = data[i];
			          //alert(info.firstName);
			            	 if(info.id != user)
			        	 	  {
			         			 datalist1+="<option value='"+info.id+"'>"+  info.firstName +"&nbsp;&nbsp;"+ info.middleName +"&nbsp;&nbsp;"+ info.lastName +"</option>" ;
			        		 }
			        	   srno++;
			 			}
			     $('#staffId').html(datalist1);
			     $('#staffId1').html(datalist1);
				 		  
			       } 
			     },
	         
		     error:function(data){
		    	    alert("error");
		     }
	    }); 
 });  
 
 
 window.dataLayer = window.dataLayer || [];
 function gtag(){dataLayer.push(arguments);}
 gtag('js', new Date());

 gtag('config', 'UA-127607784-1');

	// Get the modal
	var modal = document.getElementById('myModal');
	var modal1 = document.getElementById('myModal');
	var graphid = document.getElementById('mygraph');
	var graphid1 = document.getElementById('mygraph1');
	// Get the button that opens the modal
	var btn = document.getElementById("myBtn");

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];
	var closegraph = document.getElementsByClassName("closegraph")[0];
	var closegraph1 = document.getElementsByClassName("closegraph1")[0];

	// When the user clicks the button, open the modal 

	
	function deleteSubject() {
		//alert("hiiiiiiiiii");
		graphid.style.display = "block";
		
	}
	function classGroup() {
		//alert("hiiiiiiiiii");
		graphid1.style.display = "block";
		
	}
	//............................................................................................................
		// When the user clicks on <span> (x), close the modal
		
		closegraph.onclick = function() {
			graphid.style.display = "none";
			
		}
		
		closegraph1.onclick = function() {
			graphid1.style.display = "none";
			
		}

	// When the user clicks anywhere outside of the modal, close it	
	window.onclick = function(event) {
		if (event.target == graphid) {
			graphid.style.display = "none";
			
		}
		if (event.target == graphid1) {
			graphid1.style.display = "none";
			
		}
	}



 </script>


