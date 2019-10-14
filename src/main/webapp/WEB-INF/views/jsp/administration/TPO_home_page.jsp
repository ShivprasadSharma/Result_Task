<%@page import="javax.naming.NoInitialContextException"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:url var="addrecruitmentInfo" value="/web/taskforce/service/add/recruitmentInfo" />




<style>


input[type=text], input[type=email], input[type=date], input[type=number],
	select, textarea {
	width: 100%;
	padding: 4px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 5px;
	resize: vertical;
	background-color: #f2f2f2;
}
/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 80px; /* Location of the box */
	left: 0;
	top: 0;
	width: 108%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
	font-size: 14px;
}
.modal1 {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 150px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
	font-size: 14px;
	margin-left: 5%;
}
/* Modal Content */
.modal-content {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	margin-left: 20%;
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
.yello_hr {
   height: 2px;
   color: #ffe63b;
   background-color: #ffe63b;	
  }	
  
  .btnn {
  border: 2px solid black;
  background-color: #d9f1cf;
  color: black;
  padding: 7px 20px;
  font-size: 15px;
  cursor: pointer;
  border-radius: 1px;
  box-shadow: 0.3em 0.3em 0.3em rgba(0, 0, 0, 0.2);
}

/* Green */
.success {
  border-color: #4CAF50;
  color: green;
}

.success:hover {
  background-color: #4CAF50;
  color: white;
  }
  
.dd{

  box-shadow: 1em 1em 1em rgba(0, 0, 0, 0.2);

}

.card-profile {
  height:150px;
  width:190px;
  background-color: #f6f6f6;
  border-radius: 0;
  border: 0;
  box-shadow: 1em 1em 2em rgba(0, 0, 0, 0.2);
}
.card-profile .card-img-top {
  border-radius: 0;
}
.card-profile .card-img-profile {
  height:100px;
  width:100px;
  margin-top:10px;
  border-radius: 30%;
  border: 2px solid green;
}
.card-profile .card-title {
 font-size: 13px;
}
.card-profile .card-title small {
  display: block;
  font-size: .4em;
 
}


  
</style>

<body class="hold-transition skin-blue fixed sidebar-mini" >
<div class="wrapper">
<div class="content-wrapper myStyle">
			
			<!-- Main content -->
			<section class="content">
				<div class="col-md-12 ">
					<section class="content-header">
						
					</section>
					<c:set var="dlist" value="${departments}" />
					<!-- Info boxes -->
					<c:set var="n" value="0" />
					<div class="col-md-12 col-sm-12 ">
						<c:set var="colist" value="${deptcoordinatorList}" />
						<p>
						<br>
							<font size="4px"><b>Department of TPO</b></font>
						</p>
						<div class="box">
							<div class="box-body" style="height:73%;width:100%;border:solid 2px white-space;overflow:scroll;overflow-x:hidden;overflow-y:scroll;" >
		
							<div class="row">
								<br>
								
									<div class="col-sm-3" > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 <a href="<c:url value="/web/taskforce/service/assigncoordinator/"/>${sessionScope.user.comClientName.id}" class="btnn success"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <b>Portfolio</b>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
									</div>
									<div class="col-sm-3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="<c:url value="/web/taskforce/service/tpo/getstudent"/>"class="btnn success"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Student Data &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></a>
									</div>
									<div class="col-sm-3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<a onclick="deleteSubject()" class="btnn success"><b>Add New Drive</b></a>
									</div>
									<div class="col-sm-3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="<c:url value="/web/taskforce/service/pastcompany"/>" class="btnn success"><b>Past Drive</b></a>
									</div>
								</div>
									<div class="row">
								<br>
									<div class="col-sm-3" > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 <a href="<c:url value="/web/taskforce/service/placed/studlist"/>" class="btnn success"><b>Placed Students</b></a>
									</div>
									<div class="col-sm-3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 <a href="<c:url value="/web/taskforce/service/offcampus/addstud"/>" class="btnn success"><b>Add Placed Students</b></a>
									</div>
									<div class="col-sm-3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 <a href="#" class="btnn success"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Reports&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></a>
									</div>
									<div class="col-sm-3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
									
								</div>
								<hr class="new5">
									

                                 <div class="row"> 
                                 <div class="col-sm-1"></div>
                                 <div class="col-sm-11 dd " style="background-color:#f6f6f6;margin-left: 15px;">
								<h4> <a id="displayText" href="javascript:toggle();"><i style="color: green;"  class="fa fa-plus-square" > <b>&nbsp; &nbsp;Upcoming Companies</b></i></a></h4>
								</div>
								 <div class="col-sm-0"></div></div>
	
								<c:set var="dlis" value="${cmpnylogo}" />
								<div class="row" id="toggleText" style="display: none">
								<hr>
								<c:forEach var="y" items="${dlis}" >
								
									<div class="col-sm-3">
									<c:set var="currency" value="USD" />
									<div class='card card-profile text-center'>
								        <div class='card-block'>
								           <img alt="loading" class='card-img-profile'  src="${y.logoUrl}"  onclick="window.location.href='<c:url value="/web/taskforce/service/compny/${y.reInfoId}"/>' "/>
								               <h4 class='card-title'>
								                  <b style="color: green; "onclick="classGroup('${y.reInfoId}')" >${y.companyName}</b>
          								          </h4>
								              <br>
								       </div>
								  </div>
									 </div>     				
								    </c:forEach>
								</div>
								<hr>
								   <div class="row"> 
								  
                                 <div class="col-sm-1"></div>
                                 <div class="col-sm-11 dd " style="background-color:#f6f6f6;margin-left: 15px;">
								<h4> <a id="displayText1" href="javascript:toggle1();"><i style="color: green;" class="fa fa-plus-square" > <b>&nbsp;&nbsp; Past Companies</b></i></a></h4>
								</div>
								 <div class="col-sm-1"></div></div>
								<c:set var="plist" value="${pastcompny}" />
								
								<div class="row" id="toggleText1" style="display: none">
								 <hr>
								<c:forEach var="x" items="${plist}" >
									<div class="col-sm-3">
									       <div class='card card-profile text-center'>
								        <div class='card-block'>
								           <img alt="loading" class='card-img-profile'  src="${x.logoUrl}"  onclick="window.location.href='<c:url value="/web/taskforce/service/compny/${x.reInfoId}"/>' "/>
								               <h4 class='card-title'>
								                  <b style="color: green; "onclick="classGroup('${x.reInfoId}')" >${x.companyName}</b>
          								          </h4>
								              <br>
								       </div>
								  </div><br>       
									    <%--     <img src="${x.logoUrl}" alt="loading" onclick="classGroup('${x.reInfoId}')" width="80px" height="80px;" style=" border-radius: 50%" >
                                              <br><b style="color: green;font-size: 15px; "onclick="classGroup('${x.reInfoId}')" >${x.companyName}</b>
                                         --%> </div>     				
								    </c:forEach>
								</div><hr>
							</div>
						</div>
					</div>
			<!-- ************************************ POPUP OF RECRUITIES DRIVE ********************************************  -->
					<div id="mygraph" class="modal">
						<!-- Modal content -->
						<div class="modal-content">
							<span class="closegraph">&times;</span>
							<h3 id="graphquesion"></h3>
								<div class="container createpolls" id="progressBar"style="margin-top: 1%; width: 100%; background-color: white;">

								<form action="${addrecruitmentInfo}" method="POST" modelAttribute="recruitment" enctype="multipart/form-data">

									<div class="row">
										<div class="col-sm-5"></div>
										<div class="col-sm-5">
											<b style="color: green;font-size: 20px; ">Recruitment Info </b>
										</div>
										<div class="col-sm-4"></div>
									</div>
									<div class="row">
										<div class="col-sm-2">
											<b>Add Compny Logo :</b>
										</div>
										<div class="col-sm-6">
											<input type="file" id="notifile1" name="logoimg"
												accept="application/pdf, image/*" value=" Upload Media"
												class="btn btn-default" onchange="check1()" />
										</div>
									</div>

									<div class="row">
										<div class="col-sm-2">
											<b>Name of Compny :</b>
										</div>
										<div class="col-sm-8">
											<input type="text" id="" name="companyName" required="required">
										</div>
									</div>
									<div class="row">
										<div class="col-sm-2">
											<b>Job Title :</b>
										</div>
										<div class="col-sm-8">
											<input type="text" id="" name="jobtitle" required="required">
										</div>
									</div>

									<div class="row">
										<div class="col-sm-2">
											<b>Job Discription :</b>
										</div>
										<div class="col-sm-10">
											<textarea rows="1" cols="100"
												placeholder="Description ...0 to 250 Characters" name="jobDescription"></textarea>
										</div>
									</div>

										<div class="row">
										<div class="col-sm-2">
											<b>Department :</b>
										</div>
										<div class="col-sm-3">
												<select   multiple="multiple" name="dept"
												class=" select2 " data-placeholder="Select Department..."
												style="width: 100%">
												 <c:forEach var="item" items="${dlist}">
								                      <option class="yearoption" value="${item.dep_name}">${item.dep_name}</option>
								               </c:forEach>
											</select>
										</div>

										<div class="col-sm-2">
											<b>Select Year :</b>
										</div>
										<div class="col-sm-3">
											<select name="year" required="required">
												<option value="4">Fourth Year</option>
												<option value="5">Pass Out Student</option>
											</select>
										</div>
									</div>


									<div class="row">
										<div class="col-sm-2">
											<b>Eligibility Criteria :</b>
										</div>
										<div class="col-sm-2">
											<select name="tenth" required="required">
											    <option value="">10th Criteria</option>
												<option value="0">No Criteria</option>
												<option value="50">50% & above</option>
												<option value="55">55% & above</option>
												<option value="60">60% & above</option>
											</select>
										</div>
										<div class="col-sm-2">
											<select name="twelveth" required="required">
											 <option value="">12th/Diploma Criteria</option>
												<option value="0">No Criteria</option>
												<option value="50">50% & above</option>
												<option value="55">55% & above</option>
												<option value="60">60% & above</option>
											</select>
										</div>
										<div class="col-sm-2">
											<select name="degree" required="required">
											 <option value="No Criteria">Degree Criteria</option>
												<option value="0">No Criteria</option>
												<option value="50">50% & above</option>
												<option value="55">55% & above</option>
												<option value="60">60% & above</option>
											</select>
										</div>
										<div class="col-sm-2">
											<select name="backlog" required="required">
											 <option value="No Criteria">Backlog Allow</option>
											 	<option value="11">No Criteria</option>
												<option value="0">No BAcklog</option>
												<option value="1">one Allow</option>
												<option value="2">Two Allow</option>
												<option value="3">Three Allow</option>
											</select>
										</div>

										
									</div>
									<div class="row">
									<div class="col-sm-2">
											<b>Type Of Industry :</b>
										</div>
										<div class="col-sm-3">
											<select  id="industry" multiple="multiple" name="typeindustry"
												class=" select2 " data-placeholder="Select Industry..."
												style="width: 100%">
												<option value="Manufacturing">IT Services</option>
												<option value="Infrastructure ">Manufacturing </option>
												<option value="Information Technology">IT Product</option>
												<option value="Robotics">Engineering</option>
												<option value="Advertising">Banking & Finance</option>							
												<option value="Service Industry "> KPO </option>
												<option value="Finance">Telecom</option>
												<option value="Farming">Logistics </option>
												<option value="Quality">Construction</option>
												<option value="Artificial Intelligence">Software Engineer</option>
												<option value="Artificial Intelligence">Core Companies</option>
												<option value="Artificial Intelligence">HR Positions</option>
												<option value="Artificial Intelligence">Traniee Engineer</option>
												<option value="Artificial Intelligence">Marketing</option>
												<option value="Other">Other</option>
											</select>
										</div>
									<div class="col-sm-2">
											<b> Salary Offering :</b>
										</div>
										<div class="col-sm-3">
											<input type="text" name="salary" required="required"
												placeholder="eg.2.4 to 2.8 PLA">
										</div>
									</div>
									<div class="row">
										<div class="col-sm-2">
											<b> Selection Process :</b>
										</div>
										<div class="col-sm-3">
											<textarea rows="1" cols="60" placeholder="Description... " name="slectionProcess" required="required"></textarea>
										</div>
										<div class="col-sm-2">
											<b> Select No of rounds:</b>
										</div>
										<div class="col-sm-4">
											<select  id="studentsId" multiple="multiple" name="SelectRound"
												class=" select2 " data-placeholder="Select rounds..."
												style="width: 100%">
												<option value="Aptitude Round">Aptitude Round</option>
												<option value="Group Discussion Round">Group Discussion Round</option>
												<option value="Technical Round1">Technical Round1</option>
												<option value="Technical Round2">Technical Round2</option>
												<option value="HR Round">HR Round</option>
											</select>
										</div>
									</div>

									<div class="row">
										<div class="col-sm-2">
											<b> Drive Date :</b>
										</div>
										<div class="col-sm-3">
											<div class="form-group">

											<div class='input-group date' id='datetimepicker2'>
												<input type='text' name="dateInfo" class="form-control"
													required="required" id="datemain" placeholder="select date" />
												<span class="input-group-addon"> <span
													class="glyphicon glyphicon-calendar"></span>
												</span>
											</div>
										</div>
										</div>
										<div class="col-sm-1">
											<b>Time :</b>
										</div>
										<div class="col-sm-2">
											<input type="text" name="time" placeholder="eg 10:30pm">
										</div>
										<div class="col-sm-1">
											<b>Venue :</b>
										</div>
										<div class="col-sm-2">
											<input type="text" name="venue">
										</div>
									</div>
									
									<div class="row">
										<div class="col-sm-2">
											<b>Last Date Of Applly :</b>
										</div>
										<div class="col-sm-3">
											<div class="form-group">

											<div class='input-group date' id='datetimepicker1'>
												<input type='text' name="applydate" class="form-control"
													required="required" id="datemain" placeholder="select date" />
												<span class="input-group-addon"> <span
													class="glyphicon glyphicon-calendar"></span>
												</span>
											</div>
										</div>
										</div>
										</div>
									<br /> <b>Company Representative Details :</b> <br />
									<div id="representativ">
										<div id="representativ">
											<div class="row">
												<div class="col-sm-2">
													<b>First Name :</b>

												</div>
												<div class="col-sm-3">
													<input type="text" name="list[0].fname">
												</div>
												<div class="col-sm-2">
													<b>Last Name :</b>

												</div>
												<div class="col-sm-3">
													<input type="text" name="list[0].lname">
												</div>

											</div>
											<div class="row">
												<div class="col-sm-2">
													<b>Mobile Number:</b>

												</div>
												<div class="col-sm-2">
													<input type="text" name="list[0].mobno">
												</div>
												<div class="col-sm-1">
													<b>Email :</b>

												</div>
												<div class="col-sm-2">
													<input type="email"  name="list[0].email">
												</div>
												<div class="col-sm-1">
													<b>Designation </b>
												</div>
												<div class="col-sm-2">
													<input type="text"  name="list[0].designation">
												</div>
												<div class="col-sm-2">
													<input type="button" value="+"  class="btnn success" onclick="add()" style="border-radius: 80px">
												</div>
											</div>
                                          <br>
										</div>
                               
									</div>
									<br>
									 <div class="row">
									 <div class="col-sm-5"></div>
										<input type="submit"  class="btnn success" value="submit.">
									</div>
								</form>


							</div>

						</div>
					</div>
					
					
	<!-- ************************************ POPUP OF RECRUITIES DRIVE ********************************************  -->
				
				<!-- ************************************ On click logo Popup*******************************************. -->
			 <div id="mygraph1" class="modal1" >
					  <!-- Modal content -->
				<div class="modal-content">
					<span class="closegraph1">&times;</span>
					    
					<div id="display"></div>
					   <table id="example" class="table table-bordered table-striped"  >
				   <thead >
				     <tr>
				        <th style="width: 50px;">SrNo.</th>
				        <th>Name</th>
				        <th>Email</th>
				        <th>Mob No:</th>
				        <th>designation</th>
				     </tr>
				   </thead>
				   <tbody id="recuiritor" >
				   </tbody> 
			    </table>
					 
					 <hr class="yello_hr">
					 <h3 id="graphquesion"></h3>
				    	<div class="container createpolls" id="progressBar" style="margin-top: 1%; width: 100%; background-color: white;">
					 </div>
					</div>
				</div>
				<!--********************************************************************************************  -->
				
				</div>
			</section>
			<!-- /Main Content -->
		</div>
	</div>
	<script>
		$(function() {
			//$("#example1").DataTable();
			$('#example1').DataTable({
				scrollY : '57vh',
				scrollCollapse : true,
				paging : false
			});
		});
	</script>
	<script type="text/javascript">
	$(function() {
		$('#datetimepicker1').datetimepicker();

	});
	
	$(function() {
		$('#datetimepicker2').datetimepicker();

	});
</script>
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

	<script>

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
	function classGroup(reInfoId) {
		//alert("hiiiiiiiiii"+reInfoId);
		
		    $.ajax({
		         type: "GET",
		         url: "/"+location.pathname.split("/")[1]+"/web/taskforce/service/compny/data/"+reInfoId,
		         dataType: 'json',
		          success: function(data){
				    	
		        	//  alert(data);
		        	  var date = data.dateInfo;
		        	  var dt = new Date(data.dateInfo);
		        	 
		        	  var myvar = '<div class="row">'+
		        	  '		<div class="col-sm-1"></div>'+
		        	  '		 <div class="col-sm-2">'+
		        	  '		 <img src="'+data.logoUrl+'" alt="loading"  width="100px" height="100px;" style=" border-radius: 50%" >'+
		        	  '		    </div>     				'+
		        	  '			<div class="col-sm-9"><br><p style="color: green;font-size: 20px; " > <b> '+ data.companyName+'</b></p>'+
		        	  '						Job Title : <b>'+ data.jobtitle+'</b>'+
		        	  '						<br>Job Discription : <b>'+ data.jobDescription+'</b>'+
		        	  '						<br>Selection Process : <b>'+ data.slectionProcess+'</b>'+
		        	  '						<br>Salary Offering : <b>'+ data.salary+'</b>'+
		        	  '						<br>Eligibility Criteria : <b> <u>10th and 12th/diploma </u>--> '+ data.tenth+', ' +data.twelveth+'</b>'+
		        	  '						<br><b> <u style="margin-left:110px"> Degree </u> --> '+ data.degree+', ' +data.backlog +' Backlog Allow</b>'+
		        	  '						<br><i class="fa fa-calendar"></i>: <b>'+ dt+'</b>'+
		        	  '						<br><i class="fa fa-clock-o"></i>: <b>'+ data.time+'</b>'+
		        	  '						<br><i class="fa fa-map-marker"></i>: <b>'+ data.venue+'</b>'+
		        	  '				</div>'+
		        	  '				</div>';
		        	  $('#display').html(myvar);
		        	  var list=data.list;
		        	  
		        	  for ( var i = 0, len = list.length; i < len; )  {
				        var inf =list[i];
				          var sn=i+1;
				           $('#recuiritor').append("<tr>");
				      	   $('#recuiritor').append("<td align='center'>"+ sn +"</td>");
				 		   $('#recuiritor').append("<td> <a href='#'>" + inf.fname+ " " + inf.lname + " </a></td>");
				 		   $('#recuiritor').append("<td>" + inf.email+ "</a></td>");
				 		   $('#recuiritor').append("<td> " + inf.mobno+ "  </a></td>");
				 		   $('#recuiritor').append("<td> " + inf.designation+ " </a></td>");
				 		   $('#recuiritor').append("</tr><br><br>");
				         i++ ;  	
		 	    	} 
				  },
			     error:function(data){
			    	    alert("error");
			     }
		    }); 
		
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
 
 
 
		$(function() {
			$(".select2").select2();
		});
		
		var idCount=1;
		function add() {

			var myvar = '<div id="representativ'+idCount+'">'+
			'											<div class="row">'+
			'												<div class="col-sm-2">'+
			'													<b>First Name :</b>'+
			''+
			'												</div>'+
			'												<div class="col-sm-3">'+
			'													<input type="text" name="list['+idCount+'].fname">'+
			'												</div>'+
			'												<div class="col-sm-2">'+
			'													<b>Last Name :</b>'+
			''+
			'												</div>'+
			'												<div class="col-sm-3">'+
			'													<input type="text" name="list['+idCount+'].lname">'+
			'												</div>'+
			''+
			'											</div>'+
			'											<div class="row">'+
			'												<div class="col-sm-2">'+
			'													<b>Mobile Number:</b>'+
			''+
			'												</div>'+
			'												<div class="col-sm-2">'+
			'													<input type="text" name="list['+idCount+'].mobmo">'+
			'												</div>'+
			'												<div class="col-sm-1">'+
			'													<b>Email :</b>'+
			''+
			'												</div>'+
			'												<div class="col-sm-2">'+
			'													<input type="email"  name="list['+idCount+'].email">'+
			'												</div>'+
			'												<div class="col-sm-1">'+
			'													<b>Designation </b>'+
			''+
			'												</div>'+
			'												<div class="col-sm-2">'+
			'													<input type="text"  name="list['+idCount+'].designation">'+
			'												</div>'+
			'												<div class="col-sm-2">'+
			'													<input type="button" value="-" class="btnn success" onclick="removediv('+idCount+')" style="border-radius: 80px">'+
			'												</div>'+
			'											</div>'+
			'<br>'+
			'										</div>';
				

				
			$("#representativ").append(myvar);
			idCount++;
		}
		
	function removediv(id) {
		
		$( "#representativ"+id ).empty();
	}
	
	
	
	function openForm() {
	  document.getElementById("myForm").style.display = "block";
	}

	function closeForm() {
	  document.getElementById("myForm").style.display = "none";
	}
	
	function toggle() {
		var ele = document.getElementById("toggleText");
		var text = document.getElementById("displayText");
		if(ele.style.display == "block") {
	    		ele.style.display = "none";
			text.innerHTML = "<i style='color: green;' class='fa fa-plus-square'><b>&nbsp; &nbsp; Upcoming Companies</b></i>";
	  	}
		else { 
			ele.style.display = "block";
			text.innerHTML = "<i style='color: green;' class='fa fa-minus-square'><b>&nbsp; &nbsp; Upcoming Companies</b></i>";
		}
	} 
	
	function toggle1() {
		var ele1 = document.getElementById("toggleText1");
		var text1 = document.getElementById("displayText1");
		if(ele1.style.display == "block") {
	    		ele1.style.display = "none";
			text1.innerHTML = "<i style='color: green;'  class='fa fa-plus-square'><b>&nbsp; &nbsp; Past Companies</b></i>";
	  	}
		else { 
			ele1.style.display = "block";
			text1.innerHTML = "<i style='color: green;'  class='fa fa-minus-square'><b> &nbsp; &nbsp;Past Companies</b></i>";
		}
	}
	</script>

</body>
