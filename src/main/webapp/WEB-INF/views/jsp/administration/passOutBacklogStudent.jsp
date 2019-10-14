<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<c:url var="addAttendance" value="/web/taskforce/student/insert/attendance" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.js"></script>
  
</head>
<style>

/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 15%;
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
<style>

.success {
    background-color: #ddffdd;
    border-left: 6px solid #4CAF50;
    width: 400px;
    margin-bottom: 15px;
    padding: 4px 12px;
}
.loader {
  border: 16px solid #f3f3f3;
  border-radius: 50%;
  border-top: 16px solid #3498db;
  width: 120px;
  height: 120px;
margin-left:45%;
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
   
  form, table,thead,tbody,tfoot,tr,th,td,p { font-family:"Calibri"; font-size:small }
		a.comment-indicator:hover + comment { background:#ffd; position:absolute; display:block; border:1px solid black; padding:0.5em;  }
		a.comment-indicator { background:red; display:inline-block; border:1px solid black; width:0.5em; height:0.5em;  }
		comment { display:none;  }
		tr:nth-child(even){background-color: #f2f2f2}
		tr:hover {background-color:#f5f5f5;}
		
		input[type=text], input[type=email], input[type=date], input[type=number],
	select, textarea {
	width: 100%;
	padding: 4px;
	border: 1px solid #ccc;
	border-radius: px;
	box-sizing: border-box;
	margin-top: 5px;
	resize: vertical;
	background-color: #f2f2f2;
}

.new5 {
   height: 2px;
   color: yellow;
   background-color: #ffe63b;	
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
   <c:url var="GetStudentlist" value="/web/taskforce/service/tpo/studentlist/download" />
<body class="hold-transition skin-blue fixed sidebar-mini">
 <div class="wrapper" >
  <div class="content-wrapper">
	  <section class="content">  
	    <div class="box-body" style="margin-top: 2%">
	       <span class="pull-left" ><font size="5">Student List</font></span>
	     
	    </div>
	    <div class="box" style="margin-left:10px ; margin-right:10px">
	        <div class="box-body">
	           <div class="btn-pref btn-group btn-group-justified btn-group-lg" role="group" aria-label="...">
							       <c:forEach var="item" items="${sessionScope.departments}">
							       <c:if test="${item.dep_id ne 0}">
							       <c:set var="li" value="${studentList[1]}"/>
							       <c:choose>
									    <c:when test="${item.dep_id eq li.branch}">
									       
									        <div class="btn-group" role="group">
									            <a href="<c:url value="/web/taskforce/cims/student/list/${item.dep_id}"/>"  onclick="getStudentList(${item.dep_id})" ><button type="button" class="btn btn success" style="border-radius: 0px;background-color: #4CAF50;color:white;"><span class="glyphicon glyphicon-education" style="font-size:15px;" ></span>
									            <div  style="font-size:14px;">
									               ${item.dep_name}</div>
									            </button></a>
									        </div>
									   </c:when> 
							        
							        <c:otherwise>
										        <div class="btn-group" role="group">
									            <a href="<c:url value="/web/taskforce/cims/student/list/${item.dep_id}"/>"  onclick="getStudentList(${item.dep_id})" ><button type="button" class="btn btn success" style="border-radius: 0px;"><span class="glyphicon glyphicon-education" style="font-size:15px;" ></span>
									            <div  style="font-size:14px;">
									               ${item.dep_name}</div>
									            </button></a>
									        </div>
									</c:otherwise>
										</c:choose>
							            </c:if>
							         </c:forEach>
							      
							        <div class="btn-group" role="group">
							             <a href="<c:url value="/web/taskforce/service/studentdetails"/>"><button type="button" class="btn btn success"style="border-radius: 0px;background-color: #4CAF50;color:white;" ><span class="fa fa-user-plus" style="font-size:15px;" ></span>
							            <div  style="font-size:14px;">
							               
							                Add New User</div>
							            </button></a>
							        </div>
							        
							      <div class="btn-group" role="group">
									            <a href="<c:url value="/web/taskforce/service/backlog/passout"/>" ><button type="button" class="btn btn success" style="border-radius: 0px;"><span class="glyphicon glyphicon-education" style="font-size:15px;" ></span>
									            <div  style="font-size:14px;">
									               Update Pass/Yd Stud </div>
									            </button></a>
									        </div>
							    </div>
							    <hr class="new5">
	        
	        
	        
	        
	        	        <form action="${GetStudentlist}" method="POST">
	        
	             <div class="row" id="allInfo">
	                <div class="col-md-4">
		                <div class="form-group" >
		                  <label>Select passout & Year Down</label>
		                	 		 <select name="year" class="form-control" required="required" id="YearID">
								        <option class="yearoption" value="">select </option>
								        <option class="yearoption" value="5"> Year Down</option>
								        <option class="yearoption" value="6">Pass Out</option>
			                  		</select>
								</div>
		             	</div>
		             <div class="col-sm-4">
		             <div class="form-group" >
		              <label>Department</label>
											<select  name="dept" 
												class=" form-control " id="deptm" data-placeholder="Select Department..."
												style="width: 100%">
												 <c:forEach var="item" items="${sessionScope.departments}">
								                      <option class="yearoption" value="${item.dep_id}">${item.dep_name}</option>
								               </c:forEach>
											</select>
										</div>
										</div>
						<div class="col-md-4">
		                <div class="form-group" >
		                  <label>Academic Year</label>
		                			 		              <input name="acYear"  id="ACyear" class="date-own form-control" style="width: 300px;" type="text">

									</div>
		            			 </div>
		        		 </div>
		   
		              <div class="row" id="allInfo">
	               			 <div class="col-md-4">
		              			  <div class="form-group" >
		             				 <div class="col-sm-2">
<!--                       					       <br><input type="submit" value="Downlad"   class="btn" style=" background-color:#008000; color:#fff ;font-weight: bold;margin-left: 20px;margin-top: 10px;height: 40px;"></span>
 -->                   					 </div>
		            			 </div>
		            		 </div>
		            		  <div class="col-md-4">
		              			  <div class="form-group" >
		             				 <div class="col-sm-2">
                      					       <br><input type="button" value="Display Student"  onclick="getStudent()" class="btn" style=" background-color:#008000; color:#fff ;font-weight: bold;margin-left: 20px;margin-top: 10px;height: 40px;"></span>
                   					 </div>
		            			 </div>
		            		 </div>
		             </div>
		             
		             </form>
		      
		      
		   
		      </div>
		      <div class="success" id="notification" style="display: none">
  <p><h1>Students Are Not Present.......</h1></p>
</div>  
		      
		  </div>
				
				  <div class="box" style="margin-left:10px ; margin-right:10px;display: none"    id="loderDivId">
	        <div class="box-body">
	        
	        
		     
								<div class="loader" id="LoderID"></div>
															<h1 style="margin-left: 40%">Loading Students</h1>
								
		   
		      </div>
		      
		      
		  </div>
				
				
				<div id="studentListDIV">
				
				
				</div>
		
				
<div id="mygraph" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <span class="closegraph">&times;</span>
    <h1>Update Student</h1>
  
<h3 id="graphquesion"></h3>
<div class="container createpolls" id="progressBar" style="margin-top: 1%; width: 100%; background-color: white;">
						
						<input type="hidden" id="clientID" name="clientId">
						<input type="hidden" id="depID" name="depId">
						
	 <div class="row">
  		       <div class="col-sm-6">
		         <label style="color: black;">Select Year :</label> <br>
		            <select  name="year" id="yearID"  class="form-control "  style="width: 60%" required="required">
		            				<option value="">select Year</option>
				       		            <option value="1">FE</option>
				       		            <option value="2">SE</option>
				       		            <option value="3">TE</option>
				       		            <option value="4">BE</option>
				       		            <option value="5">Year down</option>
				 				 	    <option value="6">pass out</option>
				       		            
				       
					</select>
		       </div>
		         <div class="col-sm-6">
		         <label style="color: black;">Select PassOut Year :</label> 
		              <input name="acYear" id="acYear" class="date-own form-control" style="width: 300px;" type="text">
		       </div>
		      
		 </div>
		
		 <div class="row" >
		     <div align="center" style="padding-top: 20px">
		    	      <input type="button" onclick="updateStudent()" value="update" class="btn btn-danger" />
		     </div>
		  </div>
							
							
  </div>

</div>
</div>
			</section> 
     </div>
  </div>
 <script type="text/javascript">
 
		//get department 
		//this function call after page loading...
		var Departmets;

		$(window).on(
				'load',
				function() {
					// code here
					$.ajax({
						type : "GET",
						url : "/" + location.pathname.split("/")[1]
								+ "/web/department",
						dataType : 'json',
						success : function(data) {
							if (data.length > 0) {
								Departmets = data;

							}
						},
						error : function(data) {
							alert("student data error");
						}
					});

				});
/* ........................get Student list ................
 */		
 function getStudent() {
			
			$("#studentListDIV").empty();//delete old all old student list

			var deptID = $('#deptm').val();
			var YearID = $('#YearID').val();
			var ACyear = $('#ACyear').val();
			
			if ((ACyear=="") || (YearID=="") || (deptID=="")) {
				
				alert("first select option");
				return;
			}

			
			
			
			$("#loderDivId").show();//display loader

			$.ajax({
						type : "POST",
						url : "/" + location.pathname.split("/")[1]
								+ "/web/taskforce/service/passout/yd/student/list",
								  	data:'deptID='+ deptID + '&YearID='+ YearID+ '&ACyear='+ACyear,
								    dataType: 'json',
						success : function(data) {

							if (data.length > 0) {
								var rows = "";
								var srno = 1;
								for (var i = 0, len = data.length; i < len; ++i) {
									var info = data[i];
											
									//calculate Year
									var year = "";
									if (info.year_id == 1) {
										year = "FE";
									} else if (info.year_id == 2) {
										year = "SE";

									} else if (info.year_id == 3) {
										year = "TE";

									} else if (info.year_id == 4) {
										year = "BE";
									}else if (info.year_id == 5) {
										year = "Year Down";
									}else if (info.year_id == 6) {
										year = "PassOut "+ACyear;
									}
									var deptName = getDepartment(info.depId);//find the department Name using id
									
									rows += '<tr>' + '										<td><v>'
											+ srno
											+ '</td>'
											+ '										<td><b><a'
											+ '												href="<c:url value="/web/taskforce/service/result/menterstudprof/'+info.id+'"/>">'
											+ info.firstName + ' '
											+ info.middleName + ' '
											+ info.lastName + '</a></b></td>'
											+ '										<td>' + year + '</td>'
											+ '										<td>' + deptName
											+ '</td>' + '										<td><b>'
											+ info.emailId + '</b></td>'
											+ '										<td><b>'
											+ info.contactNos + '</b></td>'
											+ '										<td>'
											+ '<button class="button button4 delete" style="" onclick="update(\''+info.id+'\')">update</button></td>'
											+ '									</tr>';
									srno++;

								}
								var myvar = '<div class="col-md-12 col-sm-12 ">'
										+ '					<div class="box"   >'
										+ '						<div class="box-body"  >'
										+ '							'
										+ '							<table id="example1" class="table table-bordered table-striped" >'
										+ '								<thead>'
										+ '									<tr class="success">'
										+ '										<th style="width: 10px">#</th>'
										+ '										<th style="width: 270px">Name of Student</th>'
										+ '										<th>Year</th>'
										+ '										<th>Department</th>'
										+ '										<th>Email</th>'
										+ '										<th>Contact No</th>'
										+ '										<th>Update</th>'

										+ '									</tr>'
										+ '								</thead>'
										+ '								<tbody id="listOfRows">'
										+ rows
										+ '					</tbody>'
										+ '							</table>'
										+ ''
										+ '						</div>'
										+ '					</div>'
										+ '				</div>';

								$("#studentListDIV").html(myvar);

							} else {
								
								$("#loderDivId").hide();
								
								//display Notifiction...
								$("#notification").show();
								showNotification();
								setTimeout(hideNotification, 3000);
							}

							//dislay data table...
							$('#example1').DataTable({
								scrollY : '57vh',
								scrollCollapse : true,
								paging : true
							});

							$("#loderDivId").hide();

						},

						error : function(data) {
							alert("error");
						}
					});
		}
		
function updateStudent() {
	
	
	var ACyear=$("#acYear").val(); 
	var YearID=$("#yearID").val();
	var clientID=$("#clientID").val();
	
	
	if((ACyear=="")||(YearID==""))
		{
			alert("first select option");
			return;
		}
$.ajax({
		type : "POST",
		url : "/" + location.pathname.split("/")[1]
				+ "/web/taskforce/update/passout/to/redular",
				  	data:'clientID='+ clientID + '&YearID='+ YearID+ '&ACyear='+ACyear,
				    dataType: 'json',
		success : function(data) {
			$("#mygraph").hide();	
			getStudent();
		},

		error : function(data) {
			alert("error");
		}
	});
	
}

//find depatment name using Id
		function getDepartment(id) {
			for (var i = 0, len = Departmets.length; i < len; ++i) {
				var dept = Departmets[i];
				if (id == dept.dep_id) {
					return dept.dep_name;
				}
			} // The function returns the product of p1 and p2
		}

		$(function() {
			$(".select2").select2();
		});

		
		// notification code ........
		function showNotification() {
			$(".success").fadeIn().css({
				right : 0,
				position : "absolute"
			}).animate({
				left : 0
			}, 1800, function() {
				// $('#selector').delay(5000).fadeOut('slow');
			});
		}

		function hideNotification() {
			$(".success").fadeOut("slow");
		}
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
      $('.date-own').datepicker({
         minViewMode: 2,
         format: 'yyyy'
       });
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


function update(id) {
	
	graphid.style.display = "block";
	$("#clientID").val(id);
	//$("#depID").val(depId);

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
</html>