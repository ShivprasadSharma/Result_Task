<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.IOException"%>;
<%@page import="java.io.*"%>
<c:url var="download" value="/web/taskforce/serives/grievance/download" />
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>jQuery UI Datepicker - Default functionality</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
  $( function() {
    $( "#datepicker1222" ).datepicker({ dateFormat: 'yy-mm-dd' });
  } );
  
  $( function() {
	  
	    $( "#datepickerDEp" ).datepicker({ dateFormat: 'yy-mm-dd' });
	  } );
  $( function() {
	    $( "#datepickerYER" ).datepicker({ dateFormat: 'yy-mm-dd' });
	  } );
  </script>
<style type="text/css">
.circle {
	width: 130px;
	height: 130px;
	border-radius: 50%;
	font-size: 50px;
	color: green;
	line-height: 130px;
	text-align: center;
	background: #d3efc7;
	border-style: solid;
	border-color: green;
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

.closegraph1 {
	color: #aaaaaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.closegraph1:hover, .closegraph1:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}
</style>
</head>

<html>

<body class="hold-transition skin-blue fixed sidebar-mini">
	<div class="wrapper">
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="box-body">
					<span><font size="5"> Student Count</font></span>
				</div>
			</section>
			<!-- Main content -->
			<section class="content">
				<!-- /.col -->
				<div class="col-md-12">
					<div class="box box-default">
						<div class="box-body">
							<!-- enctype="multipart/form-data" -->
							<div class="container" id="datepicker" style="margin-top: 1%;">
								<form action="${download}" method="post" id="addform">
									<div class="row" style="margin-top: 5px;">
										<div class='col-sm-1'>
											<b>Department</b>
										</div>

										<div class='col-sm-4'>
											<select id="DEpID" name="dept" class="form-control"
												required="required">
												<c:forEach var="department" items="${department}">
													<c:if test="${department.dep_id ne 0}">
														<option class="yearoption" value="${department.dep_id}">${department.dep_name}
														</option>
													</c:if>
												</c:forEach>
											</select>
										</div>
										<div class='col-sm-1'>
											<b>Year</b>
										</div>

										<div class='col-sm-4'>
											<select id="Year" name="year" class="form-control"
												onclick="semister(this.value)" required="required">
												<option value="1">FE</option>
												<option value="2">SE</option>
												<option value="3">TE</option>
												<option value="4">BE</option>
											</select>
										</div>


									</div>
									<br>
									<div class="row">
										<div class='col-sm-1'>
											<b>Division</b>
										</div>

										<div class='col-sm-4'>
											<select id="Division" name="div" class="form-control"
												onchange="getsubject(this.value)">

												<option value="1">A</option>
												<option value="2">B</option>
												<option value="3">C</option>
												<option value="4">D</option>
											</select>
										</div>


										<div class='col-sm-1'>
											<b>Date</b>
										</div>

										<div class='col-sm-4'>
											<input type="text" id="datepicker1222" autocomplete="off"
												class="form-control" placeholder="Select Date"
												required="required">
										</div>

									</div>


									<div class="row">

										<div class='col-sm-4'></div>



										<div class='col-sm-3'>
											<input type="button" value="Submit" onclick="loadstud()"
												class="btn"
												style="background-color: #008000; color: #fff; font-weight: bold; text-align: center; 10 px; height: 40px; margin-top: 10px"></span>

										</div>
									</div>
<br>
									<div class="row">

										<div class='col-sm-4'>
											<div style="float: right;">
												<label style="font-size: 20px">Present Student </label>
												<div class="circle">
													<b id="present">0</b>
												</div>
											</div>

										</div>
										<div class='col-sm-3'>
											<label style="font-size: 20px">Total Student</label>
											<div class="circle">
												<b id="total">0</b>
											</div>


										</div>

										<div class='col-sm-5'>
											<br>
											<br>
											<button onclick="departmental()" class="btn btn-info">Department
											</button>
											<button onclick="yearly()" class="btn btn-info">year
												wise</button>
										</div>


									</div>


								</form>

							</div>

						</div>
					</div>
				</div>
				<!-- model for Displya Departmental Attendance -->
				<div id="mygraph" class="modal">
					<!-- Modal content -->
					<div class="modal-content">
						<span class="closegraph">&times;</span>
						<h1>Check Department Attendance :</h1>
						<hr>
						<h3 id="graphquesion"></h3>
						<div class="container createpolls" id="progressBar"
							style="margin-top: 1%; width: 100%; background-color: white;">

							<div class="row">
								<div class="col-sm-12">
									<label>Department :</label> <select id="DEpIDCount" name="dept"
										class="form-control" required="required">
										<c:forEach var="department" items="${department}">
											<c:if test="${department.dep_id ne 0}">
												<option class="yearoption" value="${department.dep_id}">${department.dep_name}
												</option>
											</c:if>
										</c:forEach>
									</select>
								</div>
							</div>
							<br>
							<div class="row">

								<div class="col-sm-12">
									<label>Date:</label> <input type="text" name="class_date"
										id="datepickerDEp" autocomplete="off" class="form-control"
										placeholder="Select Date" onclick="datefun()">

								</div>
							</div>
							<div class="row">
								<div align="center" style="padding-top: 20px">
									<input type="button" onclick="DepCount()" value="Submit" class="btn btn-success" />
								</div>
							</div>
							<br>
							<div class="row">
								<div class='col-sm-6'>
									<div style="float: right;">
										<label style="font-size: 20px">Present Student</label>
										<div class="circle">
											<b id="presentDEP">0</b>
										</div>
									</div>

								</div>
								<div class='col-sm-6'>
									<label style="font-size: 20px">Total Student</label>
									<div class="circle">
										<b id="totalDEP">0</b>
									</div>


								</div>


							</div>


						</div>
					</div>
				</div>


				<!-- model for Displya yearly Attendance -->
				<div id="mygraph1" class="modal">
					<!-- Modal content -->
					<div class="modal-content">
						<span class="closegraph1">&times;</span>
						<h1>Check yearwise Attendance :</h1>
						<hr>
						<h3 id="graphquesion"></h3>
						<div class="container createpolls" id="progressBar"
							style="margin-top: 1%; width: 100%; background-color: white;">


							<div class="row">
								<div class="col-sm-12">
									<label>Select Year :</label> <select id="YearCount" name="year"
										class="form-control" onclick="semister(this.value)"
										required="required">
										<option value="1">FE</option>
										<option value="2">SE</option>
										<option value="3">TE</option>
										<option value="4">BE</option>
									</select>
								</div>


							</div>
							<br>
							<div class="row">

								<div class="col-sm-12">
									<label>Date :</label> <input type="text" name="class_date"
										id="datepickerYER" autocomplete="off" class="form-control"
										placeholder="Select Date" onclick="datefun()">
								</div>

							</div>

							<div class="row">
								<div align="center" style="padding-top: 20px">
									<input type="button" onclick="YearCount()" value="Submit" class="btn btn-success" />
								</div>
							</div>
							<br>
							<div class="row">
								<div class='col-sm-6'>
									<div style="float: right;">
										<label style="font-size: 20px">Present Student</label>
										<div class="circle">
											<b id="presentYEAR">0</b>
										</div>
									</div>

								</div>
								<div class='col-sm-6'>
									<label style="font-size: 20px">Total Student</label>
									<div class="circle">
										<b id="totalYEAR">0</b>
									</div>


								</div>


							</div>



						</div>

					</div>
				</div>
			</section>
		</div>
	</div>

	<script type="text/javascript">

	  $("#DEpID").change(function(){ 
		  $("#present").text("0");
	    	$("#total").text("0");

	    }); 
	  $("#Division").change(function(){ 
		  $("#present").text("0");
	    	$("#total").text("0");

	    }); 
	  $("#Year").change(function(){ 
		  $("#present").text("0");
	    	$("#total").text("0");;

	    }); 
	  
	  function datefun() {
		  $("#present").text("0");
	    	$("#total").text("0");
	}
	  
	  $("#DEpIDCount").change(function(){ 
		  $("#presentDEP").text("0");
	    	$("#totalDEP").text("0");

	    });
	 
	  
function loadstud() {
	
	if($("#DEpID").val()==""){
		alert(" First select Department");
	}else if ($("#Year").val()=="") {
		alert("First select Year");
	}else if ($("#Division").val()=="") {
		alert("First Select Division");
	}else if ($("#datepicker1222").val()=="") {
		alert("select Date");
	}else{

		var punch  = new Object();
		punch.year = $("#Year").val();
		punch.div = $("#Division").val();
		punch.dept = $("#DEpID").val();
		punch.tdate = $("#datepicker1222").val();
		
		 $.ajax({
			    type: "POST",
			    url: "/"+location.pathname.split("/")[1]+"/web/taskforce/punch/student/count/1",
			    dataType: 'json',
			    headers: { 
			        'Accept': 'application/json',
			        'Content-Type': 'application/json' 
			    },
			    data : JSON.stringify(punch),
			    success: function(data){
			    	$("#present").text(data.year);
			    	$("#total").text(data.dept);
			     },
				 error:function(data){
				    alert("student data error");
				}
			  });
		
	}
}	

function DepCount() {
	
	if($("#DEpIDCount").val()==""){
		alert(" First select Department");
	}else if ($("#datepickerDEp").val()=="") {
		alert("select Date");
	}else{

		var punch1  = new Object();
		punch1.dept = $("#DEpIDCount").val();
		punch1.tdate = $("#datepickerDEp").val();

		 $.ajax({
			    type: "POST",
			    url: "/"+location.pathname.split("/")[1]+"/web/taskforce/punch/student/count/2",
			    dataType: 'json',
			    headers: { 
			        'Accept': 'application/json',
			        'Content-Type': 'application/json' 
			    },
			    data : JSON.stringify(punch1),
			    success: function(data){
			    	$("#presentDEP").text(data.year);
			    	$("#totalDEP").text(data.dept);
			     },
				 error:function(data){
				    alert("student data error");
				}
			  });
		
	}
}
function YearCount() {
	
	if($("#YearCount").val()==""){
		alert(" First select Department");
	}else if ($("#datepickerYER").val()=="") {
		alert("select Date");
	}else{

		var punch2  = new Object();
		punch2.year = $("#YearCount").val();
		punch2.tdate = $("#datepickerYER").val();

		 $.ajax({
			    type: "POST",
			    url: "/"+location.pathname.split("/")[1]+"/web/taskforce/punch/student/count/3",
			    dataType: 'json',
			    headers: { 
			        'Accept': 'application/json',
			        'Content-Type': 'application/json' 
			    },
			    data : JSON.stringify(punch2),
			    success: function(data){
			    	$("#presentYEAR").text(data.year);
			    	$("#totalYEAR").text(data.dept);
			     },
				 error:function(data){
				    alert("student data error");
				}
			  });
		
	}
}	


var graphid = document.getElementById('mygraph');
var graphid1 = document.getElementById('mygraph1');
// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];
var closegraph = document.getElementsByClassName("closegraph")[0];
var closegraph1 = document.getElementsByClassName("closegraph1")[0];
// When the user clicks the button, open the modal 
function departmental() {
	//alert("hiiiiiiiiii");
	 $( "#datepicker1222" ).hide();
	graphid.style.display = "block";
	
}
function yearly() {
	//alert("hiiiiiiiiii");
	 $( "#datepicker1222" ).hide();
	graphid1.style.display = "block";
}

//............................................................................................................
	// When the user clicks on <span> (x), close the modal
	closegraph.onclick = function() {
		 $( "#datepicker1222" ).show();
		graphid.style.display = "none";	
	}
	closegraph1.onclick = function() {
		 $( "#datepicker1222" ).show();
		graphid1.style.display = "none";	
	}

// When the user clicks anywhere outside of the modal, close it	
window.onclick = function(event) {
	if (event.target == graphid) {
		 $( "#datepicker1222" ).show();
		 graphid.style.display = "none";
		
	}
	if (event.target == graphid1) {
		 $( "#datepicker1222" ).show();
		graphid1.style.display = "none";
		
	}
}


</script>
</body>
</html>