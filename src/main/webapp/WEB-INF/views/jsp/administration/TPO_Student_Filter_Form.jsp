<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<c:url var="addAttendance" value="/web/taskforce/student/insert/attendance" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
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
	        	        <form action="${GetStudentlist}" method="POST">
	        
	             <div class="row" id="allInfo">
	                <div class="col-md-4">
		                <div class="form-group" >
		                  <label>Year</label>
		                	 		 <select name="year" class="form-control" required="required" id="YearID">
								        <option class="yearoption" value="3">Third Year</option>
								        <option class="yearoption" value="4">Fourth Year</option>
								        <option class="yearoption" value="5">Pass Out Student</option>
			                  		</select>
								</div>
		             	</div>
		             <div class="col-sm-4">
		             <div class="form-group" >
		              <label>Department</label>
											<select   multiple="multiple" name="dept" 
												class=" select2 " id="deptm" data-placeholder="Select Department..."
												style="width: 100%">
												 <c:forEach var="item" items="${departments }">
								                      <option class="yearoption" value="${item.dep_id}">${item.dep_name}</option>
								               </c:forEach>
											</select>
										</div>
										</div>
						<div class="col-md-4">
		                <div class="form-group" >
		                  <label>10th Criteria</label>
		                			  <select name="tencriteria" class="form-control" id="tencriteriaID" required="required">
								         <option class="yearoption" value="0">No Criteria</option>
								        <option class="yearoption" value="60">60% and Above</option>
								        <option class="yearoption" value="65">65% and Above</option>
								        <option class="yearoption" value="70">70% and Above</option>
								        <option class="yearoption" value="75">75% and Above</option>
		              				    </select>
									</div>
		            			 </div>
		        		 </div>
		          <div class="row" id="allInfo">
	                 <div class="col-md-4">
		                <div class="form-group" >
		                  <label> 12th/Deploma Criteria</label>
		                       <select  name="deplocriteria" class="form-control" id="deplocriteriaId" required="required">
								        <option class="yearoption" value="0">No Criteria</option>
								        <option class="yearoption" value="60">60% and Above</option>
								        <option class="yearoption" value="65">65% and Above</option>
								        <option class="yearoption" value="70">70% and Above</option>
								        <option class="yearoption" value="75">75% and Above</option>
		                       </select>
						 </div>
		              </div>
		              <div class="col-md-4">
		                 <div class="form-group" >
		                   <label> Degree Criteria</label>
		                         <select name="degreecriteria" class="form-control" id="degreecriteriaID" required="required">
								        <option class="yearoption" value="0">No Criteria</option>
								        <option class="yearoption" value="55">55% and Above</option>
								        <option class="yearoption" value="57">57% and Above</option>
								      	<option class="yearoption" value="60">60% and Above</option>
								  </select>
						   </div>
		                </div>
		             	 <div class="col-md-4">
		                     <div class="form-group" >
		                   <label> Backlog Criteria</label>
		                         <select name="backlog" class="form-control" id="backlogID" required="required">
								        <option class="yearoption" value="11">No Criteria</option>
								        <option class="yearoption" value="0">No Backlog</option>
								         <option class="yearoption" value="1">One Backlog</option>
								        <option class="yearoption" value="2">Two Backlog</option>
								       	<option class="yearoption" value="3">Three Backlog</option>
								  </select>
						   </div>
		            </div>
		             </div>
		              <div class="row" id="allInfo">
	               			 <div class="col-md-4">
		              			  <div class="form-group" >
		             				 <div class="col-sm-2">
                      					       <br><input type="submit" value="Downlad"   class="btn" style=" background-color:#008000; color:#fff ;font-weight: bold;margin-left: 20px;margin-top: 10px;height: 40px;"></span>
                   					 </div>
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
 */		function getStudent() {
			
			$("#studentListDIV").empty();//delete old all old student list

			var foo = $('#deptm').val();
			if (foo == null) {
				alert("select Department...");
			}

			// type cast string to int
			var result = foo.map(function(x) {
				return parseInt(x, 10);
			});
			
			
			$("#loderDivId").show();//display loader

			$.ajax({
						type : "POST",
						url : "/" + location.pathname.split("/")[1]
								+ "/web/taskforce/service/tpo/getstudentlist",
						data : {
							dept : result,
							year : $("#YearID").val(),
							tencriteria : $("#tencriteriaID").val(),
							deplocriteria : $("#deplocriteriaId").val(),
							degreecriteria : $("#degreecriteriaID").val(),
							backlog : $("#backlogID").val()
						},
						dataType : 'json',
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
		
 /* ........................Download Student Student list ................
  */		function downloadStudent() {
 				var foo = $('#deptm').val();
 			if (foo == null) {
 				alert("select Department...");
 			}

 			// type cast string to int
 			var result = foo.map(function(x) {
 				return parseInt(x, 10);
 			});
 			
 			$.ajax({
 						type : "POST",
 						url : "/" + location.pathname.split("/")[1]
 								+ "/web/taskforce/service/tpo/studentlist/download",
 						data : {
 							dept : result,
 							year : $("#YearID").val(),
 							tencriteria : $("#tencriteriaID").val(),
 							deplocriteria : $("#deplocriteriaId").val(),
 							degreecriteria : $("#degreecriteriaID").val(),
 							backlog : $("#backlogID").val()
 						},
 						dataType : 'json',
 						success : function(data) {

 							
						alert(data);
 							


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
 </body>
</html>