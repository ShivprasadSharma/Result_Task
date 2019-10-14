<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.zertones.model.ComClientName"%>
<%@page import="com.zertones.model.sims.Staff"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.io.*"%>
<c:url var="institutereport" value="/taskforce/service/result/superadmin/report" />

<c:url var="
" value="/web/institute/add" />

<style>
.bt {
	font-size: 10px;
	background-color: #d3efc6;
}
/* The Modal (background) */
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
	font-size: 14px;
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

.rw {
	pading: 10px
}

.ex1 {
	background-color: #fff;
	width: 100%;
	height: 20%;
	overflow: scroll;
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

.square_btn {
	display: inline-block;
	padding: 0.2em 0.5em;
	text-decoration: none;
	background: #3e9cc1; /*Button Color*/
	color: blue; /*Same as background*/
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.29);
	border-bottom: solid 3px blue;
	border-radius: 2px;
	font-weight: bold;
	text-shadow: -1px -1px rgba(255, 255, 255, 0.44), 1px 1px
		rgba(0, 0, 0, 0.38);
}

.square_btn:active { /*on Click*/
	-ms-transform: translateY(4px);
	-webkit-transform: translateY(4px);
	transform: translateY(4px);
	box-shadow: 0px 0px 1px rgba(0, 0, 0, 0.2);
	border-bottom: none;
}

/* Safari */
@
-webkit-keyframes spin { 0% {
	-webkit-transform: rotate(0deg);
}

100%
{
-webkit-transform
:
 
rotate
(360deg);
 
}
}
@
keyframes spin { 0% {
	transform: rotate(0deg);
}
100%
{
transform
:
 
rotate
(360deg);
 
}
}
</style>
<script>
 $(function () {
   $(".select2").select2();
 });	 
</script>

<body class="hold-transition skin-blue fixed sidebar-mini">
	<div class="wrapper">
		<div class="content-wrapper">
			<section class="content">
				<div class="box-body" style="margin-top: 20px">
					<span class="pull-left"><font size="5"><b>All Institute PDF Info:</b></font></span>
				</div>
				
										
				<div class="col-md-12 col-sm-12 ">
					<div class="box">
						<div class="box-body">
							<div class="modal-body" >
								<c:set var="instilist" value="${institutelist}"></c:set>
								<table id="example1" class="table table-bordered table-striped">
									<thead>
										<tr class="success">
											<th style="width: 50px;">Sr No.</th>
											<th>Institude Code</th>
											<th style="width: 270px;">Name Of Institude</th>	
										    <th>Exam Year</th>	
										    <th>Academic year</th>									
											<th>Department</th>										
											<th>Semester</th>
											<th>Subject Report</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="il" items="${resultlist}">
				                       <c:set var="n" value="${n + 1}" />
										
											<tr>				
											  	<td>${n}</td>
											  	<td>${il.instituteId}</td>
											  	<td>${il.collegename}</td>
												<td>${il.year}</td>
												<td>${il.academiyr}</td>
												
												<c:forEach var="dept" items="${departmets}">
																<c:if test="${dept.dep_id==il.branch}">
																	<td>${dept.dep_name}</td>
																</c:if>
															</c:forEach>
												
												
															<c:choose>
															<c:when test="${((il.semister)=='1')&&((il.academiyr)=='F.E.')}">
                                                                 <c:set var="semester" value="1"/>
                                                                 <td>${semester}</td>
																</c:when>
																
																<c:when test="${((il.semister)=='2')&&((il.academiyr)=='F.E.')}">
                                                                 <c:set var="semester" value="2"/>
                                                                <td>${semester}</td>
																</c:when>
																
																<c:when test="${((il.semister)=='1')&&((il.academiyr)=='S.E.')}">
                                                                 <c:set var="semester" value="3"/>
                                                                 <td>${semester}</td>
																</c:when>
																
																<c:when test="${((il.semister)=='2')&&((il.academiyr)=='S.E.')}">
                                                                 <c:set var="semester" value="4"/>
                                                                 <td>${semester}</td>
																</c:when>
															
																<c:when test="${((il.semister)=='1')&&((il.academiyr)=='T.E.')}">
                                                                 <c:set var="semester" value="5"/>
                                                                 <td>${semester}</td>
																</c:when>
																
																<c:when test="${((il.semister)=='2')&&((il.academiyr)=='T.E.')}">
                                                                 <c:set var="semester" value="6"/>
                                                                 <td>${semester}</td>
																</c:when>
																
																<c:when test="${((il.semister)=='1')&&((il.academiyr)=='B.E.')}">
                                                                 <c:set var="semester" value="7"/>
                                                                 <td>${semester}</td>
																</c:when>
																
																<c:when test="${((il.semister)=='2')&&((il.academiyr)=='B.E.')}">
                                                                 <c:set var="semester" value="8"/>
                                                                <td>${semester}</td>
																</c:when>
														
															</c:choose>

																<td>
						<form action="${institutereport}" method="post">																
	                       <input type="hidden" name="yearId" value="${il.year}">				          
                          <input type="hidden" name="branchId" value="${il.branch}">
                          <input type="hidden" name="classId" value="${semester}"> 
                           <input type="hidden" name="instituteid" value="${il.instituteId}">
			<input type="submit"  value="Download Excel File" class="btn btn-success" />
					
																</form>									
															</td>
												
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>
	<!-- Global site tag (gtag.js) - Google Analytics -->
	<script async
		src="https://www.googletagmanager.com/gtag/js?id=UA-127607784-1"></script>
	<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());
  gtag('config', 'UA-127607784-1');
</script>
	<script>
 window.dataLayer = window.dataLayer || [];
 function gtag(){dataLayer.push(arguments);}
 gtag('js', new Date());

 gtag('config', 'UA-127607784-1');

	// Get the modal
	
	var updateModel = document.getElementById('updateModel');
	
	var graphid = document.getElementById('mygraph');
	
	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];
	var closegraph = document.getElementsByClassName("closegraph")[0];
	
	var closegraphUpdate = document.getElementsByClassName("closegraphUpdate")[0];


	// When the user clicks the button, open the modal 
	function deleteSubject() {
		//alert("hiiiiiiiiii");
		graphid.style.display = "block";
	}
	
	
	
	//edit institute info...
	function editinfo(id,instCode,context,shortcontect,address,name,logourl,bgurl,contactno,email) {
		
 		$("#insIDD").val(id);

 		$("#instcode").val(instCode);
 		$("#instcode").attr('readonly', true);
 		
 		$("#context").val(context);
 		$("#short").val(shortcontect);
 		$("#InstName").val(name);
 		$("#address").val(address);
 		$("#logoUrl").val(logourl);
 		$("#bgImgUrl").val(bgurl);
 		$("#emailId").val(email);
 		$("#contactNo").val(contactno);

 		var datalist="";
	    $.ajax({
	         type: "POST",
	         url: "/"+location.pathname.split("/")[1]+"/web/menulist/collegeMenu",
	         data:'id='+ instCode ,
	         dataType: 'json',
	          success: function(data){
		    	 if(data.length > 0){
			    		 for ( var i = 0, len = data.length; i < len; ++i)  {
			    			 var inst = data[i];
				    		 document.getElementById("MenuID"+inst.id).checked = true;
			    			
			 		}
			    		 
				 		  
			       } 
			     },
	         
		     error:function(data){
		    	    alert("error");
		     }
	    });
 		
 		updateModel.style.display = "block";
	}
	//........................................................................................
		// When the user clicks on <span> (x), close the modal
		
		closegraph.onclick = function() {
			graphid.style.display = "none";
 		}
		
		closegraphUpdate.onclick = function() {
			updateModel.style.display = "none";
 		}
		
	// When the user clicks anywhere outside of the modal, close it	
	window.onclick = function(event) {
		if (event.target == graphid) {
			graphid.style.display = "none";
		}else if(event.target == updateModel){
			updateModel.style.display = "none";
		}
	}
	
	
	 
	  
		  
	

 </script>
</body>



