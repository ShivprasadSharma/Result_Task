<%@page import="javax.naming.NoInitialContextException"%>
<%@ page import="java.util.Date"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>


<!--

-->
.progress-bar {
  width: 0;
  animation: progress 1.5s ease-in-out forwards;
  
  .title {
    opacity: 0;
    animation: show 0.35s forwards ease-in-out 0.5s;
  }
} 

@keyframes progress {
  from {
    width: 0;n
  }
  to {
    width: 100%;
  }
} 
@keyframes show  {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
</style>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<c:url var="addPoll" value="/web/taskforce/serives/poll/add" />
<c:url var="updatePoll" value="/web/taskforce/serives/poll/update" />
<body class="hold-transition skin-blue fixed sidebar-mini">
	<div class="wrapper">
		<div class="content-wrapper">

			<!-- Main content -->
			<section class="content ">


				<div class="col-md-9 col-sm-9 main">


					<!-- Info boxes -->
					<div class="col-md-12 col-sm-16 "
						style="overflow: auto; height: 100%; text-align: left">
						<div style="margin-top: 2%; font-size: 25px;">
							<b>Create Poll</b>
						</div>
						<div class="container createpolls" id="datepicker"
							style="margin-top: 1%; width: 100%; background-color: white;">
							<form action="${addPoll}" method="post" id="addform"
								modelAttribute="poll">
							
							<div class="row" style="margin-top: 2%">
									
									<div class='col-sm-2'>
										<b style="font-size: 17px">Department </b>
									</div>
									<div class='col-sm-4'>
										<select style="height: 32px; width: 200px" name="depID">
											<option value="0">ALL</option>
											<c:forEach var="item" items="${departments}">

												<option value="${item.dep_id}">${item.dep_name}</option>
											</c:forEach>

										</select>
									</div>
									
									<div class='col-sm-1' style="margin-left: 2%">
										<b style="font-size: 17px">Year </b>
									</div>
									<div class='col-sm-3'>
										<select style="height: 32px; width: 200px" name="year">
											<option value="0">All</option>
											<option value="1">FE</option>
											<option value="2">SE</option>
											<option value="3">TE</option>
											<option value="4">BE</option>

										</select>
									</div>
								
								</div>

									
								

								<div class="row" style="margin-top: 2%">
									
									<div class='col-sm-2'>
										<b style="font-size: 17px">Question </b>
									</div>
									<div class='col-sm-4'><textarea rows="2" cols="50" name="question"
											placeholder="Write Question...."></textarea></div>
									

								</div>
								<div class="row" style="margin-top: 10px">
									
									
										<div class='col-sm-2'>
										<b style="font-size: 17px">To Date</b>
									</div>
								
									<div class='col-sm-4'>
										<div class="form-group">

											<div class='input-group date' id='datetimepicker1'>
												<input type='text' name="to_Date" class="form-control"
													required="required" id="datemain" placeholder="select date" />
												<span class="input-group-addon"> <span
													class="glyphicon glyphicon-calendar"></span>
												</span>
											</div>
										</div>
									</div>

								</div>
								<div class="row" style="margin-top: 2%">
									<div class='col-sm-1'></div>
									<div class='col-sm-2'>
										<b style="font-size: 17px">Option :</b>
									</div>


								</div>
								<div id="optionList" style="">
									<div class="row" style="margin-top: 1%">
										<div class='col-sm-2'></div>
										<div class='col-sm-4'>
											<input type="text" name="option"
												style="height: 35px; width: 300px;">
										</div>


									</div>
									<div class="row" style="margin-top: 1%">
										<div class='col-sm-2'></div>
										<div class='col-sm-4'>
											<input type="text" name="option"
												style="height: 35px; width: 300px;">
										</div>


									</div>

								</div>
								<div class="row" style="margin-top: 1%">
									<div class='col-sm-2'></div>
									<div class='col-sm-4'>
										<p>
											<u style="color: green; cursor: pointer;" onclick="addOption()">Add Answer</u>
										</p>
									</div>


								</div>
								<div class="row" style="margin-top: 0%">
									<div class='col-sm-8'></div>
									<div class='col-sm-2'>
										<input type="submit" value="Submit" class="btn btn-success" />
									</div>


								</div>
								<div class="row" style="margin-top: 2%">
									<div class='col-sm-1'></div>
									<div class='col-sm-2'></div>
									<div class='col-sm-2'></div>

								</div>

							</form>

						</div>
						<div style="margin-top: 2%; font-size: 25px;">
							<b>Current Polls</b>
						</div>
						<div class="box">
							<div class="box-body">
								<table id="example1" class="table table-bordered table-striped">
									<thead>
										<tr>
											<th style="width: 10px">#</th>
											<th>Poll Question<i style="margin-left: 50%">.</i></th>
											<th>Last Date</th>
											<th>view</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="poll" items="${currentPollList}">
											<tr>
												<td>${n + 1}</td>
												<td>${poll.question}</td>
												<td>${poll.to_Date}</td>
												<td><div onclick="graph('${poll.id}')"><u style="color: green;cursor: pointer;">View Graph</u></div></td>
												<td><a
													href="<c:url value='/web/taskforce/serives/poll/delete/${poll.id}'/>"
													onclick="return confirm('Are You Sure You Want To Delete This Poll ?')"><i
														class="fa fa-fw fa-trash text-danger" ></i></a> &nbsp; <i
													onclick="editPoll('${poll.id}','${poll.to_Date}')"><i
														class="fa fa-fw fa-pencil"></i></i> <input type="hidden"
													value="${poll.question}" id="Questiondata${poll.id}">
												</td>
											</tr>
											<c:set var='n' value="${n + 1 }" />
										</c:forEach>
									</tbody>
								</table>
							</div>


						</div>
					</div>
				</div>




				<!-- The Modal -->
				<div id="myModal" class="modal">

					<!-- Modal content -->
					<div class="modal-content">
						<span class="close">&times;</span> <b style="font-size: 40px">Edit Poll</b>
						<div class="container createpolls" id="datepicker"
							style="margin-top: 1%; width: 100%; background-color: white;">
							<form:form action="${updatePoll}" method="post" id="addform"
								modelAttribute="polldata">

								<input type="hidden" name="id" id="pollID">
								<div class="row" style="margin-top: 2%">
									<div class='col-sm-1'></div>
									<div class='col-sm-2'>
										<b style="font-size: 17px">Department </b>
									</div>
									<div class='col-sm-3'>
										<select style="height: 32px; width: 200px" name="depID">
											<option value="0">ALL</option>
											<c:forEach var="item" items="${departments}">

												<option value="${item.dep_id}">${item.dep_name}</option>
											</c:forEach>

										</select>
									</div>
									<div class='col-sm-1' style="margin-left: 2%">
										<b style="font-size: 17px">Year </b>
									</div>
									<div class='col-sm-3'>
										<select style="height: 32px; width: 200px" name="year">
											<option value="0">All</option>
											<option value="1">FE</option>
											<option value="2">SE</option>
											<option value="3">TE</option>
											<option value="4">BE</option>

										</select>
									</div>
								</div>

								<div class="row" style="margin-top: 2%">
									<div class='col-sm-1'></div>
									<div class='col-sm-2'>
										<b style="font-size: 17px">Question :</b>
									</div>
									<div class='col-sm-4'><textarea rows="2" cols="50" name="question" id="questionVal"
											placeholder="Write Question...."></textarea></div>
									
								</div>
								<div class="row" style="margin-top: 2%">
								<div class='col-sm-1'></div>
									<div class='col-sm-2'>
										<b style="font-size: 17px">To Date</b>
									</div>


									<div class='col-sm-4'>
										<div class="form-group">

											<div class='input-group date' id='datetimepicker2'>
												<input type='text' name="to_Date" id="editdate"
													class="form-control" required="required"
													placeholder="select date"/ > <span
													class="input-group-addon"> <span
													class="glyphicon glyphicon-calendar"></span>
												</span>
											</div>
										</div>
									</div>

								</div>

								<div class="row" style="margin-top: 2%">
									<div class='col-sm-8'></div>
									<div class='col-sm-2'>
										<input type="submit" value="Submit" class="btn btn-success" />
									</div>


								</div>


							</form:form>

						</div>
					</div>

				</div>
			
			<div id="mygraph" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <span class="closegraph">&times;</span>
    <h1>Poll Graph</h1>
  
<h3 id="graphquesion"></h3>
<div class="container createpolls" id="progressBar" style="margin-top: 1%; width: 100%; background-color: white;">
							
							
							
							
  </div>

</div>
</div>
			

				<!-- /.col -->
				<div class="col-md-3 col-sm-3 right" style="background-color: #fff;">
					<div style="text-align: left">
						<div class="" style="border-radius: 21px;">
							<div style="padding-top: 25px">
								<h4><b>Past Polls</b></h4>
								<hr>
								<%-- ${upcomingNotice} --%>
								<div style="height: 86%; overflow-x: hidden;">
									<c:forEach var="notice" items="${PastPollList}">


										<div class="box" style="background-color: #eff5ec">
											<div style="padding-left: 10px;">
												<div onclick="graph('${notice.id}')"><u style="color: green;cursor: pointer;">${n1 + 1}:Poll</u></div>
												<%-- <a
													href="<c:url value="/web/taskforce/service/notification/"/>"><font
													size="4px" style="color: #008000">${n1 + 1}:Poll</font></a> --%>
											</div>
											<c:set var="msg" value="${notice.question}" />
											<p style="padding-left: 10px; color: gray">${fn:substring(msg,0, 70)}
												<c:if test="${fn:length(msg) >= 70}"> ...</c:if>
											</p>

										</div>
										<c:set var='n1' value="${n1 + 1 }" />
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
			<!-- /.row -->
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
</body>
<style>
.right {
	float: left;
	width: 22%;
	height: 86.5%;
	padding: 15px;
	margin-top: 7px;
	text-align: center;
	overflow-x: hidden;
}

.main {
	float: left;
	width: 78%;
	height: 86.5%;
	padding: 15px;
	margin-top: 7px;
	text-align: center;
}

@media only screen and (max-width:620px) {
	.menu, .main, .right {
		width: 100%;
	}
}
</style>
<style>

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



<script type="text/javascript">
	$(function() {
		$('#datetimepicker1').datetimepicker();

		$('#datetimepicker2').datetimepicker();
	});
</script>

<script>
	$(function() {
		//$("#example1").DataTable();
		$('#example1').DataTable({
			scrollY : '20vh',
			scrollCollapse : true,
			paging : false
		});
	});
</script>
<script>
	// Get the modal
	var modal = document.getElementById('myModal');
	
	var graphid = document.getElementById('mygraph');

	// Get the button that opens the modal
	var btn = document.getElementById("myBtn");

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];
	var closegraph = document.getElementsByClassName("closegraph")[0];

	// When the user clicks the button, open the modal 

	function editPoll(id, date) {
		modal.style.display = "block";
		var data = $("#Questiondata" + id).val();

	

		$("#questionVal").val(data);
		$("#datemain").hide();
		$("#pollID").val(id);

		
	}

	

	
	function graph(pid) {
		
		var data = $("#Questiondata" + pid).val();
		  $.ajax({
		         type: "POST",
		         url: "/"+location.pathname.split("/")[1]+"/web/taskforce/serives/poll/graph",
		         data:'pollid='+ pid,
		         dataType: 'json',
		         success: function(data){ 
		        	var optionlist="";
		        	 
		        	for ( var i = 0, len = data.length; i < len; ++i) {
 		                var option = data[i];

 		               optionlist+= '<div class="row" style="margin-top: 2%">'+
 		               '									<div class=\'col-sm-1\'></div>'+
 		               '									<div class=\'col-sm-2\'>'+
 		               '										<b style="font-size: 17px">'+option.optionname+' :</b>'+
 		               '									</div>'+
 		               '									<div class=\'col-sm-7\'>'+
 		               '										<div class="progress">'+
 		               '    <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="max-width: '+option.percentage+'%">'+
 		               '    <span class="title">'+option.percentage+'%</span>'+
 		               '    </div>'+
 		               '    '+
 		               '  </div>		</div>'+
 		               '							</div>';
 		               	

		        	} 
		        	$("#progressBar").html(optionlist);
		      
		        	
		        			 	
		            
		         },
			     error:function(data){
			    	    alert("error");
			     }
		    });
		var data = $("#Questiondata" + pid).val();
		$("#graphquesion").text(data);
		graphid.style.display = "block";
		$("#datemain").hide();
	}
	//............................................................................................................
		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
			modal.style.display = "none";
			$("#datemain").show();
		}
		closegraph.onclick = function() {
			graphid.style.display = "none";
			$("#datemain").show();
		}
		
	
	

	// When the user clicks anywhere outside of the modal, close it	
	window.onclick = function(event) {
		
		if (event.target == modal) {
			modal.style.display = "none";
			$("#datemain").show();
		}
		if (event.target == graphid) {
			graphid.style.display = "none";
			$("#datemain").show();
		}
	}

	//Add option another
	function addOption(id, date) {

		var myvar = "<div class=\"row\" style=\"margin-top: 1%\">"
				+ "     <div class='col-sm-2'>"
				+ "     	"
				+ "        </div> "
				+ "     <div class='col-sm-4'>"
				+ "     	<input type=\"text\" name=\"option\" style=\"height: 35px;width: 300px;\">"
				+ "        </div> " + "          " + "  " + "    </div>";
		$("#optionList").append(myvar);

	}
</script>
