<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="com.zertones.model.sims.Staff"%>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<c:url var="sendoffer" value="/web/taskforce/service/rounds/sendoffer" />
<style type="text/css">
/* USER PROFILE PAGE */
 


div.ex1 {
    
    width: 760px;
    height: 230px;
    overflow: scroll;
}
.buttn {
    background-color: #428bca;
    border: none;
    color: white;
    padding: 4px 20px;
    border-radius: 1px;
    text-decoration: none;
    font-size: 16px;
    cursor: pointer;
   
}
.buttn1 {
    background-color: #f6faf7 ; 
    color: black; 
    border: 1px solid  #f6faf7 ;
}
.buttn1:hover {
    background-color: #428bca;
    color: white;
}
.btnn {
  border: 2px solid black;
  background-color: white;
  color: black;
  padding: 3px 15px;
  font-size: 12px;
  cursor: pointer;
  border-radius: 5px;
}

/* Green */
.success {
  border-color: #4CAF50;
  color: green;
}

.success:hover {
  background-color: #4CAF50;
  color: white;}

		/* table,thead,tbody,tfoot,tr,th,td,p { font-family:"Calibri"; font-size:small }
		a.comment-indicator:hover + comment { background:#ffd; position:absolute; display:block; border:1px solid black; padding:0.5em;  }
		a.comment-indicator { background:red; display:inline-block; border:1px solid black; width:0.5em; height:0.5em;  }
		comment { display:none;  }
		tr:nth-child(even){background-color: #f2f2f2}
		tr:hover {background-color:#f5f5f5;} */
		
		/* Modal Content */
		
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

<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<c:url var="saveNotice" value="/web/taskforce/service/notification/add" />

<c:url var="getNoticeSendBySubordinate" value="/web/taskforce/service/notice/sendbysubordinate" />
<body class="hold-transition skin-blue fixed sidebar-mini" >
<div class="wrapper">
<div class="content-wrapper">
  
     <section  class="content-header">
       <div class="box-body">
              <span class="pull-left" ><font size="5">Company Profile </font></span>
	   </div>
	 
   </section>
   <section>
   <div class="col-md-12 ">
		   
			<c:set var="list" value="${compny}"/>
			<c:set var="c" value="${count}"/>
			  <c:set var="n" value="1"/>
			<div class="row">
			 <div class="col-sm-10"> </div>
<!--   			   	<div class="col-sm-2"> <button id="cmd" class="btn btn-success"><i class="fa fa-download"></i> PDF download</button></div><br>
 -->  			  </div> 
			<!-- Info boxes -->
			    <div class="col-md-12 col-sm-12 " >
				    <div class="box">
				    <div class="box-body"style="height:80%;width:100%;border:solid 2px orange;overflow:scroll;overflow-x:hidden;overflow-y:scroll;" >
				    <div class="row">
							<div class="col-sm-1"></div>
						     <div class="col-sm-2">
								 <img src="${list.logoUrl}" alt="loading"  width="100px" height="100px;" style=" border-radius: 50%" >
		                     
		                      </div>     				
							<div class="col-sm-9"><br><p style="color: green;font-size: 20px; " > <b> ${list.companyName}</b></p>
							Job Title: <b>${list.jobtitle }</b><br>
							Job Discription :<b>${list.jobDescription }</b>
							</div>
					</div>
					<hr>
					
						 <div class="row">
							<div class="col-sm-1">&nbsp; &nbsp;<i class="fa fa-calendar"></i> </div>
						     <div class="col-sm-2 "><fmt:formatDate pattern = "yyyy-MM-dd"  value = "${list.dateInfo}" /></div>     				
							<div class="col-sm-2">Selection Process :</div>
							<div class="col-sm-6">${list.slectionProcess }</div>
						</div>
					
					 
					  <div class="row">
							<div class="col-sm-1"> &nbsp; &nbsp;<i class=" fa fa-clock-o"> </i></div>
						     <div class="col-sm-2"> ${list.time} </div>   
						     <div class="col-sm-2"> Salary Offering : </div>
							<div class="col-sm-6">${list.salary }
							</div>
						</div>
					
					
					<div class="row">
							<div class="col-sm-1">&nbsp; &nbsp;<i class="fa fa-map-marker"></i></div>
						     <div class="col-sm-2">${list.venue} </div>     				
							<div class="col-sm-2">Eligibility Criteria :</div>
							<div class="col-sm-6"><u>10th and 12th/diploma </u> >> 
							
											<c:choose>
	                                               <c:when test="${list.tenth == 0}">
	                                                  No Criteria
	                                              	</c:when>
	                                              	<c:when test="${list.tenth == 50}">
	                                                  50% And Above
	                                              	</c:when>
	                                              	<c:when test="${list.tenth == 55}">
	                                                  55% And Above
	                                              	</c:when>
	                                              	<c:when test="${list.tenth == 60}">
	                                                  56% And Above
	                                              	</c:when>
	                                              	</c:choose>
													,
											<c:choose>
							 						 <c:when test="${list.twelveth == 0}">
	                                                  No Criteria
	                                              	</c:when>
	                                              	<c:when test="${list.twelveth == 50}">
	                                                  50% And Above
	                                              	</c:when>
	                                              	<c:when test="${list.twelveth == 55}">
	                                                  55% And Above
	                                              	</c:when>
	                                              	<c:when test="${list.twelveth == 60}">
	                                                  56% And Above
	                                              	</c:when>
	                                              	</c:choose>
							 
												 <br>
									  <u>Degree </u> >>
										  <c:choose>
							  						<c:when test="${list.degree == 0}">
	                                                  No Criteria
	                                              	</c:when>
	                                              	<c:when test="${list.degree == 50}">
	                                                  50% And Above
	                                              	</c:when>
	                                              	<c:when test="${list.degree == 55}">
	                                                  55% And Above
	                                              	</c:when>
	                                              	<c:when test="${list.degree == 60}">
	                                                  56% And Above
	                                              	</c:when>
	                                              	</c:choose>
							   					 &
							    		 <c:choose>
							  						<c:when test="${list.backlog == 11}">
	                                                  No Backlog Criteria 
	                                              	</c:when>
	                                              	<c:when test="${list.backlog == 0}">
	                                                  No Backlog Allow
	                                              	</c:when>
	                                              	<c:when test="${list.backlog == 1}">
	                                                  One Backlog Allow
	                                              	</c:when>
	                                              	<c:when test="${list.backlog == 2}">
	                                                  Two Backlog Allow
	                                              	</c:when>
	                                              	<c:when test="${list.backlog == 3}">
	                                                  Three Backlog Allow
	                                              	</c:when>
	                                              	</c:choose>
	                                     </div>
					</div>
					
					<div class="row">
							<div class="col-sm-1"></i></div>
						     <div class="col-sm-2"></div>     				
							<div class="col-sm-2">Select No of rounds :</div>
							<div class="col-sm-7">
							<c:forEach var="info" items="${list.rounds}" >
							${info.roundName} ,
							</c:forEach>etc.
							</div>
					</div>
					
					<div class="row">
							<div class="col-sm-1"></i></div>
						     <div class="col-sm-2"></div>     				
							<div class="col-sm-2">Type Of Industry :</div>
							<div class="col-sm-7">
							<c:forEach var="info" items="${list.industrytype}" >
							${info.industryname} ,
							</c:forEach>etc.
							</div>
					</div>
					
						<div class="row">
							<div class="col-sm-1"></i></div>
						     <div class="col-sm-2"></div>     				
							<div class="col-sm-7">Drive for ${list.year}th Year <c:forEach var="info" items="${list.deptlist}" >
							${info.deptname} ,
							</c:forEach> etc students.</div>
							<div class="col-sm-2">
							<%-- <c:forEach var="info" items="${list.industrytype}" >
							${info.industryname} ,
							</c:forEach>etc --%>
							</div>
					</div>
					<br>
					<div class="row">
							<div class="col-sm-1"></i></div>
							<div class="col-sm-3"><b>Total Applied Student :</b></div>
							<div class="col-sm-2">
							Count :<b> ${c.totalapply}</b></div>
							<div class="col-sm-3"> <button class="btn"><a href="<c:url value="/web/taskforce/service/appyled/studlist/"/>${list.reInfoId}/0"> <b>View list</b> </a></button> </div>
					</div>
					<div class="row">
							<div class="col-sm-1"></i></div>
							<div class="col-sm-3"><b>Final Filtered Studet :</b></div>
							<div class="col-sm-2">
							Count :<b> ${c.ffilter }</b></div>
							<div class="col-sm-3"> <button class="btn"><a href="<c:url value="/web/taskforce/service/appyled/studlist/"/>${list.reInfoId}/1">  <b>View list</b> </a> </button></div>
					</div>
						<div class="row">
					<div class="col-sm-1"></div>
					<div class="col-sm-10">
						 <table id="example1" class="table table-bordered table-striped" >
						   <thead>
				                <tr>
				                  <th style="width: 30%">Round No</th>
				                  <th> Student Count:</th>
				                  <th> Student List</th>
				                </tr>
				            </thead>
				             <tbody >
				             <c:forEach var="info" items="${list.rounds}" >
							 	 <tr>
				                     <td><b>${n}. ${info.roundName}</b></td>
 				                      <td>
 				                       <c:choose>
	                                               <c:when test="${n == 1}">
	                                                  ${c.round1}
	                                              	</c:when>
	                                              <c:when test="${n == 2}">
												      ${c.round2}
												    </c:when>
												  <c:when test="${n ==3}">
												       ${c.round3}
												  </c:when>
												  <c:when test="${n == 4}">
												      ${c.round4}
												  </c:when>
												    <c:when test="${n == 5}">
												      ${c.round5}
												  </c:when>
											</c:choose> 				                      
 				                      </td>
 				                     	 <td> <button class=" btn"><a href="<c:url value="/web/taskforce/service/appyled/studlist/"/>${list.reInfoId}/${n+1}"> <b>view</b></a></button></td>
 				                     </tr>
 				                     <c:set var='n' value="${n + 1}"/>
 				             </c:forEach>
 				             
				             </tbody>
					 </table>
					 </div>
					 </div>
					 <div class="row">
							<div class="col-sm-1"></i></div>
							<div class="col-sm-3"><b>Selected Studet :</b></div>
							<div class="col-sm-3">
							Count :<b><c:choose>
							 						<c:when test="${n == 2}">
	                                                  ${c.round2}
	                                              	</c:when>
							              			<c:when test="${n == 3}">
	                                                  ${c.round3}
	                                              	</c:when>
							                     	<c:when test="${n == 4}">
	                                                  ${c.round4}
	                                              	</c:when>
	                                              	<c:when test="${n == 5}">
	                                                  ${c.round5}
	                                              	</c:when>
												    <c:when test="${n == 6}">
												      ${c.round6}
												  </c:when>
											</c:choose> 		</b></div>
							<div class="col-sm-3"> 
							          <button class="btn"   onclick="deleteSubject()"><b>Send Offer</b>  </button>
							</div>
					</div>
					<div class="row">
							<div class="col-sm-1"></i></div>
							<div class="col-sm-3"><b>Accept Offer :</b></div>
							<div class="col-sm-3">
							Count :<b> 0</b></div>
							<div class="col-sm-3"> <button class="btn"><a href="<c:url value="/web/taskforce/service/appyled/studlist"/>"> <b>View list</b> </a> </button></div>
					</div>
					<br>
					
					<div class="row">
					<div class="col-sm-1"></div>
					<div class="col-sm-10"> <b>Company Representative Details :</b></div>
					</div>
					<div class="row">
					<div class="col-sm-1"></div>
					<div class="col-sm-10">
						 <table id="example1" class="table table-bordered table-striped" >
						   <thead >
				                <tr>
				                  <th style="width: 30%">Name</th>
				                  <th style="width: 20%"> Email:</th>
				                  <th>Mob No:</th>
				                  <th>Designation</th>
				                </tr>
				            </thead>
				             <tbody >
				                <c:forEach var="x" items="${list.list}" >
				                     <tr>
 				                       <td><a href="#"> ${x.fname} ${x.lname}</a></td>
 				                       <td>${x.email}</td>
 				                       <td>${x.mobno}</td>
 				                       <td>${x.designation}</td>
				                     </tr>
				                </c:forEach>
				             </tbody>
					 </table>
					 </div>
					 </div>
					 <div class="row">
					<div class="col-sm-1"></div>
					<div class="col-sm-10"> <b style="color: green">Employer Exit Survey (Feedback)</b></div>
					</div><br>
					 <div class="row">
							<div class="col-sm-2">  </div>
						     <div class="col-sm-4"><b>1] Aptitude  Skill Of Students ? </b></div>     				
							<div class="col-sm-2">-</div>
							<div class="col-sm-2">3/5</div>
						</div>
					 
					  <div class="row">
							<div class="col-sm-2">  </div>
						     <div class="col-sm-4"><b>2] Technical Skill Of Students? </b></div>     				
							<div class="col-sm-2">-</div>
							<div class="col-sm-2">5/5</div>
						</div>
						 <div class="row">
							<div class="col-sm-2">  </div>
						     <div class="col-sm-4"><b>3] Communication Skill of Students ? </b></div>     				
							<div class="col-sm-2">-</div>
							<div class="col-sm-2">4/5</div>
						</div>
						 <div class="row">
							<div class="col-sm-2">  </div>
						     <div class="col-sm-4"><b>4] Any other Comments ? </b></div>     				
							<div class="col-sm-2">-</div>
							<div class="col-sm-2">N/A</div>
						</div>
					 <div class="row">
					 <div class="col-sm-10"></div>
					 <div class="col-sm-1"><input type="button" class="btn" value="Cancel" onclick="window.location.href='<c:url value="/web/taskforce/service/tpohome"/>' "/>
					 </div>
					 </div>
					 
					 
					 
		
  		 </div>
  	 </div></div>
   </div>
     
      
      
    </section>
         <div id="mygraph" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <span class="closegraph">&times;</span>
    <h3>Send Offer to student</h3>
     <form action="${sendoffer}" method="POST" modelAttribute="recruitment" enctype="multipart/form-data">
       
	     <input type="hidden" name="reInfoId" value="${list.reInfoId}">
  <table id="example" class="table table-bordered table-striped" style="font-size: small;">
				   <thead >
				     <tr class="table-success">
				        <th style="width: 10%;">Sr No.</th>
				        <th style="width: 50%;">Name of Student</th>
				        <th><button class="btn btn-success" type="button" id='checkAll'>Check All</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-success" type="button" id='checkAll1'>Uncheck All</button></th>
				     </tr>
				     </thead>
				     <tbody id="studListRow">
					       
				</tbody>
			</table>
			<br>
			   <div align="center" ><input type="submit" class="btn btn-success" value="Send Offer" ></div>
			</form>
		</div>
	</div>
    </div>
  </div>
  <script type="text/javascript">
  $(document).ready(function() {
	  $(".btn-pref .btn").click(function () {
	      $(".btn-pref .btn").removeClass("btn-primary").addClass("btn-default");
	      // $(".tab").addClass("active"); // instead of this do the below 
	      $(this).removeClass("btn-default").addClass("btn-primary");   
	  });
	  });
  </script>
  <!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-127607784-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-127607784-1');
  
  
 
//Get the modal
	var modal = document.getElementById('myModal');
	
	var graphid = document.getElementById('mygraph');

	// Get the button that opens the modal
	var btn = document.getElementById("myBtn");

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];
	var closegraph = document.getElementsByClassName("closegraph")[0];

	// When the user clicks the button, open the modal 

	
	function deleteSubject() {
		//alert("hiiiiiiiiii");
		
		
		var reInfoId="${list.reInfoId}";
		var round="${n+1}"
	    $.ajax({
	         type: "POST",
	         url: "/"+location.pathname.split("/")[1]+"/web/taskforce/service/tpo/selectstud",
	         data:'reInfoId='+ reInfoId +'&round='+ round,
	         dataType: 'json',
	         success: function(data){
	        	alert(data);
	       	 if(data.length > 0){
	    		 var srno=1;

	    		 var myvar ="";
	    		 for ( var i = 0, len = data.length; i < len; ++i)  {
	          var info = data[i];
	          myvar+= '<tr>'+
	        	'						     <td>'+srno+'</td>'+
	        	'						     <td>'+info.comClientName.firstName+' '+info.comClientName.middleName+' '+info.comClientName.lastName+'</td>'+
	        	'						     <td><input type=\'checkbox\'  name=\'studId\'  value=\''+info.comClientName.id+'\' onclick=\'deselect()\' class=\'check\' checked></td>'+
	        	'							</tr>';
	        		
	          srno++;
	    		 }
	    		 $("#studListRow").html(myvar);
	       	 }

			     },
	         
		     error:function(data){
		    	    alert("error");
		     }
	    });
	    
		graphid.style.display = "block";
		
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
<script type="text/javascript">

$('#checkAll').click(function() {
    $('input[name=studId]').prop('checked', true);
});

$('#checkAll1').click(function() {
    $('input[name=studId]').prop('checked', false);
});  
</script>
</body>
</html>
