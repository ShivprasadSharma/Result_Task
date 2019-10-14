<%@page import="javax.naming.NoInitialContextException"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:url var="AssignMember"
	value="/web/taskforce/serives/grievance/assign/toMember" />
<c:url var="Replay" value="/web/taskforce/serives/grievance/replay" />
<c:url var="download" value="/web/taskforce/serives/grievance/download" />
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
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
	font-size: 38px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}

.view {
	border-radius: 7px;
	background-color: #4CAF50; /* Green */
	border: none;
	color: white;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 17px;
	margin: 1px 1px;
	cursor: pointer;
}

.details {
	font-size: 25px;
}

.insideMainDiv {
	margin-top: 3%;
	border-radius: 15px;
	border: 1.3px solid #73AD21;
}

.leftmargin {
	margin-left: 2%;
}

.dot {
	height: 25px;
	width: 25px;
	border-radius: 50%;
	display: inline-block;
	border: 2px;
	margin-left: 26%;
}
.line{
margin-top: 10px;
margin-bottom: 10px;
}
</style>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper ">
		<div class="content-wrapper myStyle">
			<c:set var="n" value="0" />
			<!-- Main content -->
			<section class="content ">
				<div class="col-md-12 ">
					<section class="content-header">
						<div class="col-md-12 col-sm-12 col-lg-12"></div>
					</section>
					
					
					<c:set var="list" value="${grievanceList}" />
					<!-- Info boxes -->
					<div class="col-md-12 col-sm-12 ">
						<div class="box">
							<div class="box-body">
								<table id="example1" class="table table-bordered table-striped">
									<thead>
										<tr>
											<th style="width: 10px">Sr.No</th>
											<th>Type</>
											<th>Subject</>
											<th>Date</th>
											<th>View</th>
											<th>Status</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="x" items="${list}">
											<tr>
												<td>${n + 1}</td>
												<td>${x.type}</td>
												<td>${x.title}</td>

												<td>${x.grievance_Date}</td>
												<td><input type="button" class="view" value="View"
													onclick='openpopup("${x.id}","${x.assignToMembers}","${x.grievance_Status}","${x.process_Status}","${x.replay_Date}","${x.reOpen}","${x.oneTime_reOpen}")'>

													<input type="hidden" id="descriptionID${x.id}"
													value="${x.description}">
													
													<input type="hidden" id="ReopenReplayID${x.id}"
													value="${x.reOpen_replay} ">
													<input type="hidden" id="replayID${x.id}"
													value="${x.replay}">
													</td>
												<td>
												<c:choose>
				                <c:when test="${x.reOpen==true && x.oneTime_reOpen==true}">
								      <b style="color: red">Reopened</b>
			                    </c:when>
			                     <c:when test="${x.reOpen==false}">
								      <b >Disposed</b>
			                    </c:when>
			                    <c:when test="${x.grievance_Status==false && x.reOpen==true}">
								       <b style="color: blue">Replayed</b>
			                    </c:when>
								<c:otherwise>
								  	<b style="color: green">Pending</b>
			                    </c:otherwise>			                    
				            </c:choose> 
												
												
												
												
												</td>
											</tr>
											<c:set var='n' value="${n + 1 }" />
										</c:forEach>
									</tbody>
								</table>




								<!-- view popup module -->
								<!-- The Modal -->
								<div id="myModal" class="modal">

									<!-- Modal content -->
									<div class="modal-content">
										<span class="close">&times;</span>

										<div class="box-body">
											<div class="details">Grievance Details</div>
											<div class="insideMainDiv">
												<br />
												<div>
													<b style="color: #008000; " class="leftmargin">Description:</b>
													<div id="description" style="height: auto" class="leftmargin"></div>
												</div>
												<hr class="line"/>
												<!-- enctype="multipart/form-data" -->
												<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
													<div id="addForm">
														<form action="${AssignMember}" method="post" id="addform">

															<c:set var="stafflist" value="${stafflist}" />
															<label class="control-label leftmargin"
																style="color: #008000">Grievance Assign to
																Members:</label>
															<div style="width: 50%" class="leftmargin">
																<select class="js-example-basic-multiple"
																	id="selectMember" name="userId[]" multiple="multiple"
																	data-placeholder="Select Members..."
																	style="width: 100%" onchange="checkval()">
																	<c:forEach var="Sitem" items="${stafflist}">
																		<option value="${Sitem.comClientName.id}">${Sitem.comClientName.firstName}
																			${Sitem.comClientName.lastName}</option>
																	</c:forEach>
																</select> <input type="submit" id="submitbtn" value="Add Members"
																	class="btn btn-success" style="margin-top: 2%" disabled />
															</div>

															<input type="hidden" name="grievanceId" value=""
																id="GrievanceId">
														</form>
													</div>
												</sec:authorize>
												<div id="assignmembersdiv">
													<b style="color: #008000;" class="leftmargin">Assigned
														Member:</b>
													<div id="membersList" class="leftmargin"></div>
												</div>
												<hr class="line" />
												<div id="assignmembersdiv">
													<table style="width: 100%" class="leftmargin">
														<tr>
															<th style="color: #008000;">Grievance Status :</th>
															<th style="color: #008000;" id="replayheading">Replay
																Date:</th>

														</tr>
														<tr>
															<td><div id="griStatus" class="leftmargin"></div></td>
															<td id="replaydatasds"><div id="replayDatediv"
																	class="leftmargin"></div></td>

														</tr>

													</table>
												</div>
												<hr class="line" />
												<div id="assignmembersdiv">
													<b style="color: #008000;" class="leftmargin">Replay :</b>
													<div id="ReplayTextData" class="leftmargin"></div>
													<div id="ReplayTextArea" class="leftmargin">
														<form action="${Replay}" method="post" id="addform">
															<input type="hidden" name="grievanceId" value=""
																id="GrievanceIdReplay">
																	<input type="hidden" name="checkReopen" value="0">
															<textarea rows="2" cols="50" name="Replay"></textarea>
															<input type="submit" id="submitbtn" value="Replay"
																class="btn btn-success" />

														</form>
													</div>



												</div>
												
												<div id="reOpenDiv" ><hr class="line" />
													<b style="color: #008000;" class="leftmargin">ReOpen Replay :</b>
													<div id="Reopen_ReplayData" class="leftmargin"></div>
													 <div id="Reopen_ReplayText" class="leftmargin">
														<form action="${Replay}" method="post" id="addform">
															<input type="hidden" name="grievanceId" value=""
																id="REgrievanceId">
																<input type="hidden" name="checkReopen" value="1">
															<textarea rows="2" cols="50" name="Replay"></textarea>
															<input type="submit" id="submitbtn" value="Replay"
																class="btn btn-success" />

														</form>
													</div> 



												</div>


											</div>
											<div></div>
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
	<script type="text/javascript">
            $(function () {
              
                $('#datetimepicker2').datetimepicker();
                
            });
        </script>
	  <script type="text/javascript">
            $(function () {
                $('#datetimepicker1').datetimepicker();
              
                
            });
        </script>

	<script>
		// Get the modal
		var modal = document.getElementById('myModal');

		// Get the button that opens the modal
		var btn = document.getElementById("myBtn");

		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];

		// When the user clicks the button, open the modal 
		function openpopup(griId, status, griStatus, processId, replayDate,reopenSts,onetimeSts) {
			
			$("#datepicker").hide();

			var desc = $("#descriptionID" + griId).val();
			var replay = $("#replayID" + griId).val();
			
			
			var clientId = "${sessionScope.user.comClientName.id}";
			var statusofAssign = false;
			//Grievance Assign to Members Check 
			if (status == "true") {
				
				$.ajax({
					type : "POST",
					url : "/" + location.pathname.split("/")[1]
							+ "/web/taskforce/serives/grievance/get/Member",
					data : 'grievanceID=' + griId,
					dataType : 'json',
					success : function(data) {
						if (data.length > 0) {
							var datalist = "";

							for (var i = 0, len = data.length; i < len; ++i) {
								var user = data[i];
								
								datalist += "<div  class=\"leftmargin\">"
										+ user.firstName + " " + user.lastName
										+ "</div>";

								//using this id check replay box show or not
								if (clientId == user.id) {
									
									statusofAssign = true;

								}
							}
							
							if (griStatus == "true") {
								
								if (statusofAssign == true) {
									setTimeout(function () {
										if (statusofAssign == true) {
										
											$("#Reopen_ReplayText").show();
										x
										} else {
										
											$("#Reopen_ReplayText").hide();
										}
								    }, 1000);
									$("#ReplayTextArea").show();
								} else {
									
									$("#ReplayTextArea").hide();
								}
							}
							$("#membersList").html(datalist);
							$("#addForm").hide();
							$("#assignmembersdiv").show();

						}
					},
					error : function(data) {
						alert("error");
					}
				});
				
			} else {
				$("#addForm").show();
				$("#assignmembersdiv").hide();

			}
			//repaly box hide or show
			if (statusofAssign == true) {
				
				$("#ReplayTextArea").show();
			} else {
				
				$("#ReplayTextArea").hide();
			}
			//use for grievance status process
			if (griStatus == "true") {
				var myvar = "";
				if (processId == "1") {
					myvar = "<span class=\"dot\" style=\" background-color: green;\"></span>  &nbsp&nbsp Below 24 Hours";

				} else if (processId == "2") {
					myvar = "<span class=\"dot\" style=\" background-color: yellow;\"></span>  &nbsp&nbsp Above 24 Hours";

				} else if (processId == "3") {
					myvar = "<span class=\"dot\" style=\" background-color: red;\"></span>  &nbsp&nbsp Above 72 Hours";

				}
				$("#griStatus").html(myvar);
				$("#replaydata").hide();
				$("#replayheading").hide();
				$("#ReplayTextData").hide();

			} else {
				
				if(reopenSts=="true")
				{
					if(onetimeSts=="true"){
						var myvar1 = "<span class=\"dot\" style=\" background-color: red;\"></span> &nbsp&nbsp  Grievance Reopen";
					}else
						{
						var myvar1 = "<span class=\"dot\" style=\" background-color: black;\"></span> &nbsp&nbsp  Process Completed";
						}
					
				}else
					{
					var myvar1 = "<span class=\"dot\" style=\" background-color: black;\"></span> &nbsp&nbsp  Process Completed";
					}
				
				$("#griStatus").html(myvar1);
				$("#replaydata").show();
				$("#replayheading").show();
				$("#replayDatediv").text(replayDate);
				$("#ReplayTextArea").hide();
				$("#ReplayTextData").show();
				$("#ReplayTextData").text(replay);
			}
			
			$("#description").text(desc);

			//used to hide assign to member 
			try {
				document.getElementById('GrievanceIdReplay').value = griId;
				document.getElementById('GrievanceId').value = griId;
			} catch (err) {

				$("#assignmembersdiv").show();
				$("#membersList").html("Members not Assign to Grievance. ");
				
			}
			
			//reopen grievance
			if(reopenSts=="true")
				{
				
				
				if(onetimeSts=="true")
					{
					
					setTimeout(function () {
						if (statusofAssign == true) {
							
							
							$("#Reopen_ReplayText").show();
							document.getElementById('REgrievanceId').value = griId;
						} else {
							
							$("#Reopen_ReplayText").hide();
							$("#Reopen_ReplayData").text("");
						}
				    }, 100);
					
					}else{
						$("#reOpenDiv").hide();
						
					}
				
				}else
					{
					$("#reOpenDiv").show();
					
					if(onetimeSts=="true")
					{
						$("#Reopen_ReplayData").show();
						$("#Reopen_ReplayText").hide();
						var REreplay = $("#ReopenReplayID" + griId).val();
						$("#Reopen_ReplayData").text(REreplay);
					}else
						$("#reOpenDiv").hide();
					
					}
			
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
				$("#datepicker").show();
			}
		}

		function checkval() {

			var value = $("#selectMember").val();

			if (value == null) {
				$('#submitbtn').attr('disabled', true);
			} else {

				$('#submitbtn').attr('disabled', false);
			}

		}
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('.js-example-basic-multiple').select2();
		});
	</script>
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
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-127607784-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-127607784-1');
</script>
</body>
