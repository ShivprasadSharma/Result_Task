<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*"%>
<c:url var="save" value="/web/taskforce/service/result/save" />
<c:url var="resultReport" value="/web/taskforce/service/result/report" />
<c:url var="studentreport" value="/web/taskforce/service/result/report/student" />

<style>

.test
{

 border: 1.5px solid black;
  padding: 10px 10px 10px 80px;
 width:32%;

}
.success {
	background-color: #ddffdd;
	border-left: 6px solid #4CAF50;
	width: 400px;
	margin-bottom: 15px;
	padding: 4px 12px;
}

input[type=submit] {
	background-color: green;
	color: white;
}

.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */ .
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: scroll; /* Enable scroll if needed */
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

div.ex1 {
	width: 100%;
	height: 70%;
	overflow: scroll;
}

div.skillsdiv {
	width: 100%;
	height: 70%;
	overflow: scroll;
}

.label {
	color: green;
}
.well
{
overflow-x: auto; 
}


/* The Modal (background) */
.modalviewskill {
	border: 2px solid #888;
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 230px; /* Location of the box */ .
	left: 0;
	top: 0;
	width: 80%; /* Full width */
	height: 120%; /* Full height */
	overflow: scroll; /* Enable scroll if needed */
}

.buttonviewskill {
	background-color: white;
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
.modal-contentviewskill {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 2px solid green;
	margin-left: 35%;
	width: 40%;
	border-radius: 10px;
	overflow: scroll;
	border-bottom-color: green;
	border-bottom-width: 3px;
}

success {
	background-color: #ddffdd;
	border-left: 6px solid #4CAF50;
	width: 400px;
	margin-bottom: 15px;
	padding: 4px 12px;
}
</style>
<c:url var="addparentcallrecord"
	value="/web/taskforce/service/student/parentcallrecord" />
<body class="hold-transition skin-blue fixed sidebar-mini">
	<div class="wrapper">
		<div class="content-wrapper">
			<c:set var="n" value="0" />
			<!-- Main content -->
			<section class="content">
				<div class="col-md-12" style="margin-top: 21px">
					<section class="content-header">
						<div class="col-md-12 col-sm-12 col-lg-12"></div>
					</section>
					<c:set var="list" value="${mentor}" />

					<!-- Info boxes -->
					<div class="col-md-12 col-sm-12 ">
						<div class="box">
							<div class="box-body">

								<div class="box box-default">
									<div class="box-body">
										<span><font size="5" color="green"><b>Select
													File :</b></font></span>
										<div align="center">
											<form action = "${save}" method="POST"  enctype="multipart/form-data">
	            		    	<div class="form-group" >
			          		<h4 style="margin-top:13px">Select file to upload</h4>
	 		          		<input class="test" type="file" name="ResultPDFfile" accept="vnd.ms-excel/*" value=" Upload Media" class="btn" required="required"/> 
	 		          		<input type="hidden" name="empid" value="${sessionScope.user.employeeNo}">
			        		</div>
			    			<div>
					  		<input type="submit" value="Upload" class="btn btn-success" />
	 			    		</div>
	 			    </form>
										</div>
									</div>
								</div>


								<div class="well">
									<div class="t1">
										<div class="tab-pane fade in active" id="tab1"
											style="font-size: 16px; color: #5e605f">
											<table id="example1"
												class="table table-bordered table-striped">
												<thead>
													<tr>
														<th>Sr No.</th>
														<th>Exam Year</th>
														<th>Year</th>
														<th>Branch</th>
														<th>Semister</th>
														<th>Subject Report</th>
														<th>Student Report</th>
													</tr>
												</thead>
												<tbody>


													<c:forEach var="resultist" items="${resultlist}">
														<c:set var="n" value="${n + 1}" />

														<tr>
															<td>${n}</td>
															<td>${resultist.year}</td>
															<td>${resultist.academiyr}</td>
															
															<c:forEach var="dept" items="${departmets}">
																<c:if test="${(dept.dep_id)==(resultist.branch)}">
																	<td>${dept.dep_name}</td>
																</c:if>
															</c:forEach>
															
															
															<c:choose>
															<c:when test="${((resultist.semister)=='1')&&((resultist.academiyr)=='F.E.')}">
                                                                 <c:set var="semester" value="1"/>
                                                                 <td>${semester}</td>
																</c:when>
																
																<c:when test="${((resultist.semister)=='2')&&((resultist.academiyr)=='F.E.')}">
                                                                 <c:set var="semester" value="2"/>
                                                                <td>${semester}</td>
																</c:when>
																
																<c:when test="${((resultist.semister)=='1')&&((resultist.academiyr)=='S.E.')}">
                                                                 <c:set var="semester" value="3"/>
                                                                 <td>${semester}</td>
																</c:when>
																
																<c:when test="${((resultist.semister)=='2')&&((resultist.academiyr)=='S.E.')}">
                                                                 <c:set var="semester" value="4"/>
                                                                 <td>${semester}</td>
																</c:when>
															
																<c:when test="${((resultist.semister)=='1')&&((resultist.academiyr)=='T.E.')}">
                                                                 <c:set var="semester" value="5"/>
                                                                 <td>${semester}</td>
																</c:when>
																
																<c:when test="${((resultist.semister)=='2')&&((resultist.academiyr)=='T.E.')}">
                                                                 <c:set var="semester" value="6"/>
                                                                 <td>${semester}</td>
																</c:when>
																
																<c:when test="${((resultist.semister)=='1')&&((resultist.academiyr)=='B.E.')}">
                                                                 <c:set var="semester" value="7"/>
                                                                 <td>${semester}</td>
																</c:when>
																
																<c:when test="${((resultist.semister)=='2')&&((resultist.academiyr)=='B.E.')}">
                                                                 <c:set var="semester" value="8"/>
                                                                <td>${semester}</td>
																</c:when>
														
															</c:choose>

															<td>
						<form action="${resultReport}" method="post">																
				          <input type="hidden" name="yearId" value="${resultist.year}">
                          <input type="hidden" name="branchId" value="${resultist.branch}">
                          <input type="hidden" name="classId" value="${semester}"> 
			<input type="submit"  value="Download Excel File" class="btn btn-success" />
					
																</form>									
															</td>


															<td>
						<form action="${studentreport}" method="post">																
				          <input type="hidden" name="yearId" value="${resultist.year}">
                          <input type="hidden" name="branchId" value="${resultist.branch}">
                          <input type="hidden" name="classId" value="${semester}"> 
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
						</div>
					</div>

				</div>
			</section>
			<!-- /Main Content -->

		</div>
	</div>
	<!-- Global site tag (gtag.js) - Google Analytics -->
	<script async
		src="https://www.googletagmanager.com/gtag/js?id=UA-127607784-1"></script>
	<script>
		window.dataLayer = window.dataLayer || [];
		function gtag() {
			dataLayer.push(arguments);
		}
		gtag('js', new Date());
		gtag('config', 'UA-127607784-1');
	</script>



</body>
