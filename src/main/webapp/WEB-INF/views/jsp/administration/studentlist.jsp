<%@page import="javax.naming.NoInitialContextException"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>

<c:url var="updateStudent" value="/web/taskforce/service/student/year" />

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.js"></script>

   <!--  <script type="text/javascript" src="https://code.jquery.com/jquery-3.0.0.min.js"></script> -->
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jspdf/1.2.61/jspdf.min.js"></script>
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

.modal1 {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top:0px; /* Location of the box */
	left: 0;
	top: 12%;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal1-content {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	margin-left: 23%;
	width: 70%;
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
<style>
<!--
.delete{border: none;
  color: white;
  
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 12px;
  margin: 1px 1px;
  cursor: pointer;
  border-radius: 3px;background-color: #BD1010;
  }
-->


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
<body class="hold-transition skin-blue sidebar-mini" >
<div class="wrapper ">
 <div class="content-wrapper myStyle"  >
  <c:set var="n" value="0"/>
    <!-- Main content -->
    <section class="content " >
         <div class="col-md-12 ">
		    <section class="content-header">
				<div class="col-md-12 col-sm-12 col-lg-12">
				</div>
			</section >
			<c:set var="list" value="${studentList}"/>
		
			<div class="row">
			 <div class="col-sm-8"> </div>
  			   	<div class="col-sm-4" align="right"> <button id="cmd" class="btn btn-success"><i class="fa fa-download"> &nbsp;</i> PDF download</button>
  			   <button onclick="classGroup()" class="btn btn-success"><i class="fa fa-search-plus"> &nbsp;</i> Add Filter</button></div><br>
  			   	 
  			  </div> 
			<!-- Info boxes -->
			    <div class="col-md-12 col-sm-12 " >
				    <div class="box">
				    <div class="box-body" >
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
							             <a href="<c:url value="/web/taskforce/service/studentdetails"/>"><button type="button" class="btn btn success"style="border-radius:0px;" ><span style="font-size:15px;" ></span>
							            <div  style="font-size:14px;">
							               
							                Add New User</div>
							            </button></a>
							        </div>
							        
							        
							        
							      <div class="btn-group" role="group">
									            <a href="<c:url value="/web/taskforce/service/backlog/passout"/>" ><button type="button" class="btn btn success" style="border-radius: 0px;"><span  style="font-size:15px;" ></span>
									            <div  style="font-size:14px;">
									               Update Year </div>
									            </button></a>
									        </div>
							    </div>
							    <hr class="new5">
							    
			  <table id="example1" class="table table-bordered table-striped" >
						   <thead>
				                <tr class="success">
				                  <th style="width: 10px">#</th>
				                  <th style="width: 270px">Name of Student</th>
				                  <th>Year</th>
				                  <th>Class</th>
				                  <th>Roll No</th>
				                  <th>Delete</th>
				                  <th>Update</th>
				                </tr>
				            </thead>
				            <tbody id="" >
				              <c:forEach var="info" items="${list}" >
				                     <tr>
				                       <td>${n + 1}</td>
				                       <td><b><a href="<c:url value="/web/taskforce/service/result/menterstudprof/${info.comClientName.id}"/>">${info.comClientName.firstName} ${info.comClientName.lastName}</a></b></td>
				                       <td>
				                       <c:choose>
	                                               <c:when test="${info.year eq 4}">
	                                                  BE
	                                              	</c:when>
	                                              <c:when test="${info.year eq 2}">
												     SE
												    </c:when>
												  <c:when test="${info.year eq 3}">
												      TE
												  </c:when>
												  <c:when test="${info.year eq 1}">
												     FE
												  </c:when>
											</c:choose>
										</td>
				                       <td>
				                       <c:choose>
						                            <c:when test="${info.standard eq 1}">
		                                                A
		                                            </c:when>
						                       		<c:when test="${info.standard eq 2}">
		                                               B
		                                            </c:when>
						                       		<c:when test="${info.standard eq 3}">
		                                               C
		                                            </c:when>
						                       		<c:when test="${info.standard eq 4}">
		                                              D
		                                            </c:when>
						                       		<c:when test="${info.standard eq 5}">
		                                              E
		                                            </c:when>
						                       		<c:when test="${info.standard eq 6}">
		                                               F
		                                            </c:when>
				                       </c:choose>
				                       </td>
				                       <td>${info.rollNo}</td>
				                        <td>
				                              &nbsp;&nbsp;&nbsp;&nbsp;
				                               <a href="<c:url value='/web/taskforce/serives/student/delete/${info.comClientName.id}/${info.branch}'/>"
													onclick="return confirm('Are You Sure You Want To Delete This Student ?')"> <button class="button button4 delete" style="; ">Delete</button></a>            
				                       </td>
				                       <td>
												<a> <button class="button button4 delete" style="" onclick="update('${info.comClientName.id}','${info.branch}')">update</button></a>    
				                       </td>
				                     </tr>
				                  <c:set var='n' value="${n + 1}"/>
				                </c:forEach>
				             </tbody>
				              
			     </table>
			             
			         
			
			            </div>
				    </div>
		       </div>
			</div>
			
			<!--..........................................start.......................  -->
				<div id="mygraph" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <span class="closegraph">&times;</span>
    <h1>Update Student</h1>
  
<div class="container createpolls" id="progressBar" style="margin-top: 1%; width: 100%; background-color: white;">
						
						
						<form action="${updateStudent}" method="post">
						<input type="hidden" id="clientID" name="clientId">
						<input type="hidden" id="depID" name="depId">
						
	 <div class="row">
  		       <div class="col-sm-6">
		         <label>Select Year :</label> 
		            <select  name="year" id="clientIDdelete" onchange="deletefun(this.value)" class="form-control select2" data-placeholder="Select Teacher..." style="width: 60%" required="required">
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
		         <label>Select PassOut Year :</label> 
		              <input name="acYear" class="date-own form-control" style="width: 300px;" type="text">
		       </div>
		      
		 </div>
		
		 <div class="row" >
		     <div align="center" style="padding-top: 20px">
		    	      <input type="submit" onclick="return Validate()" value="update" class="btn btn-danger" />
		     </div>
		  </div>
		  </form>		 						
							
							
  </div>

</div>
</div>
<!--..........................................End.......................  -->
<!--..........................................start.......................  -->
<div id="mygraph1" class="modal1" >
					  <!-- Modal content -->
				<div class="modal1-content">
					<span class="closegraph1">&times;</span>
					    <h5><b>Add Filter</b></h5>
				    	<div class="container createpolls"  style="margin-top: 1%; width: 100%; background-color: white;">
								
                  <form action="${updateStudent}" method="post">
                  
                  
					

						<div class="row">
  		       <div class="col-sm-4">
  		       
  		       
  		       
  		          <label>Department</label>
											<select  id="deptid">
												 <c:forEach var="item" items="${departments }">
								                      <option class="yearoption" value="${item.dep_id}">${item.dep_name}</option>
								               </c:forEach>
											</select>
											</div>
				 <div class="col-sm-4">
		         <label>Select Year :</label> 
		            <select  name="year" id="YearID"  class="form-control select2" data-placeholder="Select Teacher..." style="width: 60%" required="required">
		            				<option value="">select Year</option>
				       		            <option value="1">FE</option>
				       		            <option value="2">SE</option>
				       		            <option value="3">TE</option>
				       		            <option value="4">BE</option>
				       		            
				       
					</select>
		       </div>
		         <div class="col-sm-4">
		         <label>Select Division :</label> 
		         <select  name="year" id="Div" class="form-control select2" data-placeholder="Select Teacher..." style="width: 60%" required="required">
		            				<option value="">select Year</option>
				       		            <option value="1">A</option>
				       		            <option value="2">B</option>
				       		            <option value="3">C</option>
				       		            <option value="4">D</option>
				       		            <option value="4">E</option>
				       		            <option value="4">F</option>
					</select>
		       </div>
		        <table id="example1" class="table table-bordered table-striped" >
						   <thead style="font-size: 15px";>
				                <tr class="success">
				                 <th>Roll No</th>
				                  <th style="width: 270px">Name of Student</th>
				                  <th>PRN No</th>
				                   <th>Practical Batch</th>
				                  <th>Email</th>
				                  <th>ContactNO</th>
				                </tr>
				            </thead>
				            <tbody style="font-size: 13px"; id="students"  >
				              
				              
				             </tbody>
			     </table>
		      
		      
		 </div>
		 <div class="row" >
		     <div align="center" style="padding-top: 20px">
                    <br><input type="button" value="Display Student"  onclick="getStudent()" class="btn" style=" background-color:#008000; color:#fff ;font-weight: bold;margin-left: 20px;margin-top: 10px;height: 40px;"></span>
		     </div>
		  </div>		
						</form>			 						
					  </div>
					</div>
				</div>

<!--..........................................End.......................  -->




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
        paging:         false
    } );
  });
  
  </script>
  <script type="text/javascript">
  $('#cmd').click(function () {
	  var name_val = 'a';
	  var empid_val = 'b';
	  var age_val = 'c';
	  var today = new Date();
	  var dd = today.getDate();
	  var mm = today.getMonth() + 1; //January is 0!
	  var yyyy = today.getFullYear();

	  if (dd < 10) {
	    dd = '0' + dd;
	  }

	  if (mm < 10) {
	    mm = '0' + mm;
	  }

	  today = dd + '/' +mm + '/' + yyyy;
	  
	  var pdf = new jsPDF();
		pdf.setPage(3);
		pdf.setFontSize(15);
		pdf.setTextColor(0, 100, 0);
		pdf.setFontType('bold')
		pdf.text(50, 10, '${sessionScope.collegeInfo.name}');
		pdf.setFontSize(11);
		pdf.text(87, 18, 'Student List'); 
		pdf.setFontSize(8);
		pdf.text(11,22,'Department :');
		<c:forEach var="info" items="${list}"   begin="0" end="0" >
		   <c:forEach var="y" items="${departments}" >
     	    	<c:if test="${info.branch == y.dep_id}">
     	    	var dept='${y.dep_name}';
		  			pdf.text(30, 22,''+" ${y.dep_name}");
     		     </c:if>
           </c:forEach>
		</c:forEach> 
		//pdf.text(30,22,'Computer');
		pdf.text(172,22,'Date :');
		pdf.text(183,22,today);
		pdf.setLineWidth(0.5);
		pdf.line(10, 25, 10, 31);
		pdf.line(200, 25, 200, 31);
		pdf.line(18, 25, 18, 31);
		pdf.line(69, 25, 69, 31);
		pdf.line(116, 25, 116, 31);
		pdf.line(133, 25, 133, 31);
		pdf.line(144, 25, 144, 31);
		pdf.line(159, 25, 159, 31);
		pdf.line(180, 25, 180, 31);
		pdf.setLineWidth(0.5);
		pdf.line(10, 25, 200, 25);
		pdf.text(13, 29, '#');
		pdf.text(22, 29, 'Name');
		pdf.text(81,29,'EmailID');
		pdf.text(117,29,'ContactNO');
		pdf.text(135,29,'Year');
		pdf.text(146,29,'Division');
		pdf.text(166,29,'DOB');
		pdf.text(184,29,'PRN NO.');
		pdf.setLineWidth(0.5);
		pdf.line(10, 31, 200, 31);
		pdf.setFontSize(6.5);
		 var b=35;
		 var n=1;
		 pdf.setTextColor(0, 0, 0);
		<c:forEach var="info" items="${list}">
		pdf.setFontType('normal')
		pdf.text(13,b,''+ n);
		pdf.text(20, b,''+"${info.comClientName.firstName} ${info.comClientName.middleName}  ${info.comClientName.lastName}");
		pdf.text(70, b,''+"${info.comClientName.emailId}");
		pdf.text(118, b,''+"${info.comClientName.contactNos}");
		 <c:choose>
         <c:when test="${info.year eq 4}">
         pdf.text(137, b,'BE');
        	</c:when>
        <c:when test="${info.year eq 2}">
        pdf.text(137, b,'SE');
		    </c:when>
		  <c:when test="${info.year eq 3}">
		  pdf.text(137, b,'TE');
		  </c:when>
		  <c:when test="${info.year eq 1}">
		  pdf.text(137, b,'FE');
		  </c:when>
		</c:choose>
		<c:choose>
        <c:when test="${info.standard eq 1}">
        	 pdf.text(150, b,'A');
            </c:when>
     		<c:when test="${info.standard eq 2}">
     		 pdf.text(150, b,'B');
            </c:when>
     		<c:when test="${info.standard eq 3}">
     		 pdf.text(150, b,'C');
            </c:when>
     		<c:when test="${info.standard eq 4}">
     		 pdf.text(150, b,'D');
            </c:when>
     		<c:when test="${info.standard eq 5}">
     		 pdf.text(150, b,'E');
            </c:when>
     		<c:when test="${info.standard eq 6}">
     		 pdf.text(150, b,'F');
            </c:when>
		</c:choose>
		var date="${info.comClientName.dateOfBirth}";
		var subdate=date.substring(0,date.indexOf(" "));
		pdf.text(164, b, subdate);
		pdf.text(184, b,''+"${info.universityEnrollNo}");
		var d=b-4;
		var c=b+2;
		pdf.setLineWidth(0.1);
		pdf.line(10, c, 200, c);
		pdf.line(18, d, 18, c);
		pdf.line(69, d, 69, c);
		pdf.line(116, d, 116, c);
		pdf.line(133, d, 133, c);
		pdf.line(144, d,144, c);
		pdf.line(159, d,159, c);
		pdf.line(180, d,180, c);
		//pdf.setLineWidth(0.5);
		pdf.line(10, d, 10, c);
		pdf.line(200, d, 200, c);
		n=n+1;
		b=b+6;
		if (b >= 285) {
			pdf.addPage(210,297);
			b=10;
			pdf.line(10, 6,200, 6);
		}
		</c:forEach>
	  pdf.save(dept+'StudentList.pdf');
	  });	

  
</script>
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-125893256-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-125893256-1');
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


function update(id,depId) {
	graphid.style.display = "block";
	$("#clientID").val(id);
	$("#depID").val(depId);

}

function classGroup() {
	
	graphid1.style.display = "block";
	
	
}
//............................................................................................................
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
<script>
function getStudent() {
			
			var foo = $('#deptid').val();
			var yr=$("#YearID").val()
			var div=$("#Div").val()
		
			

			// type cast string to int
			
			
			$.ajax({
						type : "POST",
						url : "/" + location.pathname.split("/")[1]
								+ "/web/taskforce/service/student/getfilterlist",
						data : {
							dept : foo,
							year : yr,
							div : div
						},
						
						
						dataType : 'json',
						success : function(data) {
							
							 if(data.length > 0){
					    		 var srno=1;
					    		 for ( var i = 0, len = data.length; i < len; ++i)  {
					          var info = data[i];
					          
					          var year = "";
								if (info.year == 1) {
									year = "FE";
								} else if (info.year == 2) {
									year = "SE";

								} else if (info.year == 3) {
									year = "TE";

								} else if (info.year == 4) {
									year = "BE";
								}
					           $('#students').append("<tr>");
					           $('#students').append("<td align='center'>" +info.rollNo+ "</td>");
					 		   $('#students').append("<td>" + info.comClientName.firstName +" "+ info.comClientName.middleName + " " + info.comClientName.lastName+ "</td>");
					      	   $('#students').append("<td align='center'>" +info.universityEnrollNo+ "</td>");
					      	   $('#students').append("<td align='center'>" +info.batch+ "</td>");
					 		   $('#students').append("<td>" +info.comClientName.emailId +"</td>");
					 		   $('#students').append("<td>" +info.comClientName.contactNos +"</td>");
					 		   $('#students').append("</tr><br><br>");
					 		   
					 		   srno++;
					 		} 
					       }
							
						},

						error : function(data) {
							alert("error");
						}
					});
		}
</script>
</body>
