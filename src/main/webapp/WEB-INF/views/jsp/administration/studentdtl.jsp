<%@page import="javax.naming.NoInitialContextException"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
   <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
    background-color: #d3efc6;
}
input[type=text],input[type=email], input[type=date],input[type=number],select, textarea {
    width: 140%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    margin-top: 6px;
    margin-bottom: 16px;
    resize: vertical;
    background-color: #f2f2f2;
    margin-left: -18px;
    text-align:left;    
 }
.submit
{
    matgin-top:25%;
    margin-left:40%;
}
input[type="button"]
{
    background-color: #4CAF50;
    width: 50%;
    padding: 5px;
    border: 2px solid #red;
    border-radius: 4px;
    box-sizing: border-box;
    resize: vertical;
    color: #fff;
    margin-left:23%;
    
   }
input[type=submit], input[type=reset] {
    background-color: #4CAF50;
    color: white;
    padding: 12px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    margin-left:20px;
    margin-top:30px;
    width:110px;
}
input[type=submit]:hover {
    background-color: #45a049;
}
.container {
	max-width: 97%;
	padding: 0em 3em 2em 3em;
	margin: 0em auto;
	background-color: #fff;
	border-radius: 4.2px;
	box-shadow: 0px 5px 15px -2px rgba(0, 0, 0, 0.2);
	margin-left:20px;
	margin-top:8px;
}

ixmg {
	border-radius: 50%;
	}
	th {
	text-align: left;
		font-size:13px;
	
	}
td, th {
	padding: 5px 69px;
		font-size:13px;
}
.head
{
	width: 98%;
	height: 40px;
	border-radius: 5px;
	box-shadow: 0px 6px 10px -2px rgba(0, 0, 0, 0.6);
	padding: 2px 12px;
	text-align: center;
	background-color: #4CAF50;
	color: white;
	margin-top:10px;
}
.new5 {
   height: 2px;
   color: yellow;
   background-color: yellow;	
  }	
  
  
  .btnn {
  border: 2px solid black;
  background-color: #d9f1cf;
  color: black;
  padding: 7px 20px;
  font-size: 15px;
  cursor: pointer;
  border-radius: 0px;
  box-shadow: 0.3em 0.3em 0.3em rgba(0, 0, 0, 0.2);
}

/* Green */
.success {
  border-color: #4CAF50;
  color: green;
}

.success:hover {
border-color: black;
  background-color: #4CAF50;
  color: white;
  }
  
.dd{

  box-shadow: 1em 1em 1em rgba(0, 0, 0, 0.2);

}
</style>
</head>
<body class="hold-transition skin-blue sidebar-mini" >
<div class="wrapper ">
 <div class="content-wrapper myStyle" >
 
 <c:url var="saveForm" value="/web/taskforce/service/result/studentupdate" />
   <form:form action="${saveForm}" method="post" modelAttribute="studentform">
    <!-- Main content -->
   		 <section class="content " >
   			<div class="container">
   			<br>
							           <div class="btn-pref btn-group btn-group-justified btn-group-lg" role="group" aria-label="...">
							       <c:forEach var="item" items="${sessionScope.departments}">
							       <c:if test="${item.dep_id ne 0}">
							       <c:set var="li" value="${studentList[1]}"/>
							       <c:choose>
									    <c:when test="${item.dep_id eq li.branch}">
									       
									        <div class="btn-group" role="group">
									            <a href="<c:url value="/web/taskforce/cims/student/list/${item.dep_id}"/>"  onclick="getStudentList(${item.dep_id})" ><button type="button" class="btn btn success" style="border-radius: 0px;background-color: #4CAF50;color:white;"><span  style="font-size:15px;" ></span>
									            <div  style="font-size:14px;">
									               ${item.dep_name}</div>
									            </button></a>
									        </div>
									   </c:when> 
							        
							        <c:otherwise>
										        <div class="btn-group" role="group">
									            <a href="<c:url value="/web/taskforce/cims/student/list/${item.dep_id}"/>"  onclick="getStudentList(${item.dep_id})" ><button type="button" class="btn btn success" style="border-radius: 0px;"><span  style="font-size:15px;" ></span>
									            <div  style="font-size:14px;">
									               ${item.dep_name}</div>
									            </button></a>
									        </div>
									</c:otherwise>
										</c:choose>
							            </c:if>
							         </c:forEach>
							      
							        <div class="btn-group" role="group">
							             <a href="<c:url value="/web/taskforce/service/studentdetails"/>"><button type="button" class="btn btn success"style="border-radius: 0px;background-color: #4CAF50;color:white;" ><span  style="font-size:15px;" ></span>
							            <div  style="font-size:14px;">
							               
							                Add New User</div>
							            </button></a>
							        </div>
							        
							      
							        
							        
							      <div class="btn-group" role="group">
									            <a href="<c:url value="/web/taskforce/service/backlog/passout"/>" ><button type="button" class="btn btn success" style="border-radius: 0px;"><span  style="font-size:15px;" ></span>
									            <div  style="font-size:14px;">
									               Update Year</div>
									            </button></a>
									        </div>
									        
									        
									        
							    </div>
							    
							    
							    <hr class="new5">
			
  			<div class="">
  			<c:set var="list" value="${dept}"/>
  				<h4><b>Add User.</b></h4>
  			</div>
  			<hr>
	  			<table>
		     			<tr>
		         			<td><b>First Name :</td> 
		         			<td><b>Middle Name :</td>
		         			<td><b>Last Name : </td>
		    			</tr>
		    			<tr>
		       				<td><input type="text" id="name" name="firstName" placeholder="Enter First name.."required></td>
		       				<td><input type="text" id="name" name="middleName" placeholder="Enter middle name.."required></td>
		       			    <td><input type="text" id="name" name="lastName" placeholder="Enter last name.."required></td>
		   				<tr>
		         			<td><b>Email id :</td> 
		         			<td><b>Contact No :</td>
		         			<td><b>Designation :</td>
		    			</tr>
		    			<tr>
		   					<td><input type="email" name="emailId" placeholder="Enter Email-id.." required ></td>
		    				<td><input type="text" name="contactNos" placeholder="Enter Contact no.."  required> </td>
		    				<td>
		    				   <select  name="designation">
                            	 	 <option value="ROLE_STUDENT">Student</option>
                             			
                             		 
                               </select>
		    				</td>
		    			</tr>
		    			
		    			<tr>
		    			<td> <b>Department</b></td> 
		    			<td><b>Standard:</td>
		         	    <td><b>Year :</td>
		    			
		    			</tr>
		    			<tr>
		    			<td>
		    				<select  name="department" required>
                            	 <c:forEach var="item" items="${dept}">
						             <c:if test="${item.dep_id ne 0}">
					          	 	<option value="${item.dep_id}">${item.dep_name}</option> 
									 </c:if>
								  </c:forEach> 
                               </select>
		    				</td>
		    		
		    			
		    			<td>
		            <select  name="standard" id="standard" onchange="deletefun(this.value)" required >
		            				<option value="">select standard</option>
				       		            <option value="1">A</option>
				       		            <option value="2">B</option>
				       		            <option value="3">C</option>
				       		            <option value="4">D</option>
				       		            <option value="5">E</option>
				       		            <option value="6">F</option>
				       		            <option value="7">G</option>
				       		            <option value="8">H</option>
				       		            
				      
					</select>
					</td>
					<td>
		            <select  name="year" id="year" onchange="deletefun(this.value)" required>
		            				<option value="">select Year</option>
				       		            <option value="1">FE</option>
				       		            <option value="2">SE</option>
				       		            <option value="3">TE</option>
				       		            <option value="4">BE</option>
				       		          
				       		            
				       
					</select>
		    			</td>
		    			
		    			</tr>
		    			
		    			
		    			<tr>
		    		    <td><b>Gendar:</td>
		    			<td> <b>DOB</b></td> 
		    			
		    		<td> <b>RollNO</b></td> 
		    			
		    			</tr>
		    			
		    			<tr>
		    			<td>
		<div required> 
		<row> 			
  <input type="radio" name="gender" value="1"checked>Male
  <input type="radio" name="gender" value="2"> Female</div></row></td>
   
  <td><input type="date" name="dateofbirth" placeholder="Select dateofbirth....."></td>
  	<td><input type="text" id="rollNo" name="rollNo" placeholder="Enter Roll Number....."required></td>
		       			
		    			
		    			<td>
		    			
		    			</td>
		    			</tr>
		    			
		    			
		    		</table>
		    			<div class="submit">
		    		  <span>
		    		     <input type="submit" onclick="returSaveate()" value="Save">
		    		 </span>
  		      </div><br>
  		</div>
  		</div>
		</section>  
	</form:form>  
</div>       
 <!-- /Main Content -->

<script>
function myFunction() {
    var x = document.getElementById("myDate").value;
    document.getElementById("demo").innerHTML = x;
}
$('#date').datepicker({ dateFormat: 'yyyy-mm-dd' }).val();

$(document).ready(function(){
	var messg="${msg}";
	
	if(messg!="")
	alert("${msg}");
	
	});
	

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
