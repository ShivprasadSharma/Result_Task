<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="<c:url value="/static/css/bootstrap.min.css"/>" type='text/css' />
<link rel="stylesheet" href="<c:url value="/static/css/styles.css"/>" type='text/css' />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jspdf/1.2.61/jspdf.min.js"></script>

<c:url var="addGPoll" value="/web/taskforce/serives/grouppoll/add"/>
<c:url var="updategPoll" value="/web/taskforce/serives/grouppoll/update" />
<head>
<style>
input[type=text] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    box-sizing: border-box;
}
.comment{
background-color:#008000; color:white; margin-top: 12px;
}
.ScrollStyle
{
    max-height: 590px;
    overflow-y: scroll;
}

.modal1 {
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
.modal1-content {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	margin-left: 23%;
	width: 54%;
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

.closegraph1:hover, .closegraph:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}
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
.progress-bar {
  width: 0;
  animation: progress 2.0s ease-in-out forwards   ;
  background-color: #80d2a3;
  border-radius: 4px;
  
  
  
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
</head>
 <body class="hold-transition skin-blue fixed sidebar-mini" >
     <div style="height:89%; margin-top:73px" >
	         <div class="left">
                <section class="section ScrollStyle" >
                  <div class="box-body">
                   <c:forEach var="gp" items="${groupInfo}">
                     <c:forEach var="m" items="${gp.groupMembers}">
                      <c:if test="${gp.groupIncharge_2 eq m.id}">	
						<button type="button" onclick="addMembers(${gp.groupId})" class="btn btn-block btn-success ">Add Member</button>
                      </c:if>
                      </c:forEach> 
                    </c:forEach>  
                    <form action=""></form>
				       <c:forEach var="group" items="${groupInfo}">				       
				         <c:forEach var="m" items="${group.groupMembers}">
				            <div class="section">
					          <c:choose>
								<c:when test="${m.imgUrl ne null }">
								    <img src="<c:url value="${m.imgUrl}"/>" style="border-radius: 50px;height:40px;width:40px" alt="Member Image">
						        </c:when>
						        <c:otherwise>
						         <img src="<c:url value="/static/img/author.png"/>" style="border-radius: 50px;height:40px;width:40px" alt="Member Image">
						       </c:otherwise>
						       </c:choose>
						             &nbsp;
					            <span class="hidden-xs" style="color:green">${m.firstName} &nbsp; ${m.lastName}</span>	  
							 </div> <br>
						  </c:forEach>   
						</c:forEach>  
				    </div>
                </section>
            </div>
            
            <div class="contents" >
			           <section  class="content-header"   style="overflow:scroll; height:100%; text-align: left">
			            <c:forEach var="group" items="${groupInfo}">
		                      <div class="box-body" >
		                       <div class="col-md-6 col-sm-6">
			                         <sec:authorize access="hasAnyRole('ROLE_HOD','ROLE_PRINCIPAL')">
			                           <span><a href="<c:url value='/web/taskforce/group' />">Group</a> <i class=" fa fa-arrow-right "> </i> <font size="3">${group.groupName}</font></span>
			                         </sec:authorize>
			                         <sec:authorize access="hasAnyRole('ROLE_TEACHER','ROLE_LECTURER')"> 
			                         <span><a href="<c:url value='/web/taskforce/group' />">Group</a> <i class=" fa fa-arrow-right " > </i> <font size="3">${group.groupName}</font></span>
			                         </sec:authorize>
							    </div>${msg}
							    
							    <div class="col-md-6 " align="right">
							    <c:forEach var="group" items="${groupInfo}">				       
				          			<c:forEach var="m" items="${group.groupMembers}">     
								         <c:if test="${group.groupIncharge_2 eq m.id}">	
								           <div class="btn-group">
								           <i style="font-size: 20px;" class="fa fa-gear" data-toggle="dropdown"></i>
								              <ul class="dropdown-menu">
								                  <li><a onclick="editGroup(${group.groupId})"><i class="fa fa-pencil"></i> Edit</a></li>
								                  <li><a href='<c:url value="/web/taskforce/group/delete/${group.groupId}" />
								                  ' onclick="return confirm('Are you sure you want to delete this item?')"><i class="fa fa-trash"></i> Delete Group </a></li>
								                  <c:if test="${empty group.gfm_id}"> 
								                  <li><a onclick="removeMember(${group.groupId})"><i class="fa fa-remove"></i>Remove Member</a></li>
								                  </c:if>
								              </ul>
								            </div>
								          </c:if>
								      
								    </c:forEach>  
								    &nbsp; 
						            						        
						           
							    <button class="btn btn" style="background-color:#008000; color:white;" onclick="deleteSubject()" > Create Poll </button>
						        <button class="btn btn" style="background-color:#008000; color:white;" onclick="addGroupNotice(${group.groupId})" >New Post </button>
						</c:forEach>
					           </div>
						   </div>
						</c:forEach> 
						
						 <div class="btn-pref btn-group btn-group-justified btn-group-lg" role="group" aria-label="...">
        <div class="btn-group" role="group">
            <button type="button" id="stars" class="btn btn-success" href="#tab1" data-toggle="tab"><span class="fa fa-clipboard" style="font-size:10px;" aria-hidden="true"></span>
                <div class="hidden-xs" style="font-size:13px;"> <b>Current Group Notices</b></div>
            </button>
        </div>
        <div class="btn-group" role="group">
            <button type="button" id="favorites" class="btn btn-default" href="#tab2" data-toggle="tab"><span class="fa fa-bar-chart" style="font-size:10px;" aria-hidden="true"></span>
                <div class="hidden-xs" style="font-size:13px;"> <b>Current Group Polls</b></div>
            </button>
        </div>
    </div>
						
						 <div class="well">
      <div class="tab-content" >
      <div class="tab-pane fade in active" id="tab1" style="font-size:16px;color: #5e605f ;" >
						
						<table  id="example" class="table table-bordered table-striped ">
						<tr  style="overflow:scroll; height:53%; text-align: left">
						<td >
					     <c:forEach var="notice" items="${notices }">	
					       <input type="hidden" value="${notice.notificationId}" id="noticeId">	
						   <div class="box ">
					           <div class="box-body">
					                 <div class="col-md-12 col-sm-12">
						              <c:forEach var="noticeImage" items="${notice.notificationFilesDTO}">
						                <div class="col-md-3 col-sm-3" align="center">
						                   		 <c:set var="fileExtension" value="${noticeImage.getDocument1Type()}"/>
											     <c:choose>
												     <c:when test="${fileExtension eq 'application/msword'}">
												        <img src="<c:url value="/static/img/doc.jpg"/>" width="100px"">
												     </c:when>
												     <c:when test="${fileExtension eq 'application/vnd.openxmlformats-officedocument.spreadsheetxml.sheet'}" >
												        <img src="<c:url value="/static/img/Excel-Logo.png"/>" width="100px" >
												     </c:when>
												      <c:when test="${fileExtension eq 'application/zip'}" >
												         <img src="<c:url value="/static/img/Excel-Logo.png"/>" width="100px" >
												     </c:when>
												      <c:when test="${fileExtension eq 'application/pdf'}" >
												         <img src="<c:url value="/static/img/pdf_logo.png"/>" width="100px" >
												     </c:when>
												     <c:when test="${fileExtension eq 'application/vnd.ms-powerpoint'}" >
												        <img src="<c:url value="/static/img/ppt_logo.jpg"/>" width="100px">
												     </c:when>
												     <c:when test="${not empty noticeImage.getString1()}" >
												        <img  src="${noticeImage.getString1()}" width="100px"/>
												     </c:when>
												     <c:otherwise>
												        <img src="<c:url value="/static/img/ImgNA.jpg"/>" width="100px"">
												     </c:otherwise>
											     </c:choose>
									        </div>
										</c:forEach>
							           <div class="col-md-9 col-sm-9">
							              <font size="3" color="green"><b>${notice.notificatiosHeadline}</b></font>
							              <c:set var="msg" value="${notice.notificationDetails}"/>
							              <p><font size="3">${fn:substring(msg, 0, 11)}</font></p>
							           </div>
							        </div>
						            <div style="padding-left:35px;padding-top:100px ">
						            <p align="right">Posted By:<b>${notice.postNotice}</b></p>
						               <font size="2">Likes: <b id="likeCount${notice.notificationId}">${notice.like_count}</b></font>&nbsp;&nbsp;
						                <font size="2" >Comments : <b id="commentCount${notice.notificationId}"><c:choose><c:when test="${empty notice.comment_count}" >0</c:when><c:otherwise>${notice.comment_count}</c:otherwise></c:choose>  							 
						            </div>   	
						            <div class="col-md-12"><hr/>
						               <button type="submit" class="btn" value="${notice.like_Status}" id="likebtn${notice.notificationId}" style="background-color:white;border-style: solid;border-color: #e6e6e6;<c:choose><c:when test="${notice.like_Status == 'true'}" >color:blue;</c:when><c:otherwise></c:otherwise></c:choose>" onclick="likeFunction(${notice.notificationId},${sessionScope.user.comClientName.id})"><li class="fa fa-heart-o" style="font-size:15px"></li><b>&nbsp;Like</b></button>&nbsp;&nbsp;
							           <button type="submit" class="btn" style="background-color:white;    border-style: solid;border-color: #e6e6e6;" onclick="commentfunction(${notice.notificationId},${sessionScope.user.comClientName.id})" value="true" id="commentbtn${notice.notificationId}"><i class="fa fa-commenting-o" style="font-size:15px"></i> &nbsp;<b>Comment</b></button>
						           </div>
						            <div class="col-md-12" style="background-color: white-space; display: none;" id="commentbox${notice.notificationId}"><hr/>
							          <div id="commentlist${notice.notificationId}">
							           </div>
							             <div class="row">
  											<div class="col-sm-8"> <input id="commenttext${notice.notificationId}" type="text" placeholder="add comment.." autocomplete="off" /></div>
  											<div class="col-sm-4 "> <button class="btn btn comment" style="" onclick="commentInsert(${notice.notificationId},${sessionScope.user.comClientName.id},${notice.comment_count})" >Comment </button></div>
										</div>
							             
						            </div>
						            
<%-- 						            <h6>${currentGPollList}</h6>
 --%>								</div>
						      </div>
					       </c:forEach>
					       </td>
					    <tr>
				         </table>
				         </div>
				         <div class="tab-pane fade in " id="tab2" >
						       			
												   <div class="accordion-option">
									
						<div class="box">
							<div class="box-body">
								<table id="example1" class="table table-bordered table-striped" style="font-size: 14px;">
									<thead>
										<tr>
											<th style="width: 10px">#</th>
											<th style="width: 350px"> Group Poll Question</th>
											<th>Last Date</th>
											<th>view</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="poll" items="${currentGPollList}">
											<tr>
											
												<td>${n + 1}</td>
												<td>${poll.question}</td>
												<td><fmt:formatDate value="${poll.to_Date}" pattern="dd-MMM-yyyy"/></td>
												<td style="font-size: 20px;"><div onclick="graph('${poll.id}')"><u style="color: green; pointer;"> <i  class="fa fa-bar-chart"></i></u></div></td>
												<td style="font-size: 15px;"><a
													href="<c:url value='/web/taskforce/serives/grouppoll/delete/${poll.id}/${poll.g_id }'/>"
													onclick="return confirm('Are You Sure You Want To Delete This Poll ?')"><i
														class="fa fa-fw fa-trash text-danger" ></i></a>  <i
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
				         </div>
				         </div>
				 </section> 
            </div>
		    <div class="right">
			    <div class="content-header">	    
				   <span><p align="center" style="font-size: 16px;"><b>Total Number of Members</b></p>
				      <c:forEach var="group" items="${groupInfo}">
				        <c:set var="s" value="${group.groupMembers}"></c:set>
	    						<c:forEach var="t" items="${s}">
	    						    <c:set var="count" value="${count + 1}" scope="page"/>
	    						</c:forEach>
	   					<p align="center" style="font-size: 16px;"> <b>${count}</b></p> 
				    </c:forEach>
				    </span> 
				    <hr>
				    <h4><p align="center">Past Poll List</p></h4>
				    
				    <c:forEach var="notice" items="${PastGPollList}">


										<div class="box" style="background-color: #d3efc6;border-bottom: 3px solid green;" >
											<div style="padding-left: 5px;">
												<div onclick="graph('${notice.id}')"> <u style="color: green;cursor: pointer;">${n1 + 1} :Poll </u></div>
												<%-- <a
													href="<c:url value="/web/taskforce/service/notification/"/>"><font
													size="4px" style="color: #008000">${n1 + 1}:Poll</font></a> --%>
											</div>
											<c:set var="msg" value="${notice.question}" />
											<p style="padding-left: 5px; color: gray">${fn:substring(msg,0, 80)}
												<c:if test="${fn:length(msg) >= 80}"> ...</c:if>
											</p>
										</div>
										<c:set var='n1' value="${n1 + 1 }" />
									</c:forEach>
				    
 				</div>
             </div>
             
             
             <!-- ..............................................Group Pop Up Start............................................................ -->
        <div id="mygraph" class="modal1" >
					  <!-- Modal content -->
				<div class="modal1-content">
					<span class="closegraph">&times;</span>
					    <h4>Create Group Poll</h4>
					  <hr class="yello_hr">
					 	<form action="${addGPoll}" method="post" id="addform" modelAttribute="gpoll">
  				<input type="hidden"  name="clientId" value="${sessionScope.user.comClientName.id}"> 
  				<c:forEach var="gp" items="${groupInfo}">
  				<input type="hidden"  name="groupid" value="${gp.groupId}"> 
  				<input type="hidden"  name="g_id" value="${gp.groupId}"> 
				</c:forEach>
								<div class="row" style="margin-top: 2%">
									
									<div class='col-sm-2'>
										<b style="font-size: 14px">Question </b>
									</div>
									<div class='col-sm-4'><textarea rows="2" cols="50" name="question"
											placeholder="Write Question...."></textarea></div>
									

								</div>
								<div class="row" style="margin-top: 10px">
									
									
										<div class='col-sm-2'>
										<b style="font-size: 14px">To Date</b>
									</div>
								
									<div class='col-sm-4'>
										<div class="form-group">

											<div class='input-group date' id='datetimepicker1'>
												<input type='text' name="to_Date" class="form-control"
													required="required" id="datemain" placeholder="select date"/>
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
										<b style="font-size: 14px">Option :</b>
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
					</div>
				</div>     
        
        
  <!-- ........................................................Group Poll Popup End.................................................. -->
             
             <!-- ,.........................................The Modal........................................... -->
				<div id="myModal" class="modal1">

					<!-- Modal content -->
					<div class="modal1-content">
						<span class="close">&times;</span> <b style="font-size: 20px;color: green" >Edit Poll</b>
						<div class="container createpolls" id="datepicker"
							style="margin-top: 1%; width: 100%; background-color: white;">
							<form:form action="${updategPoll}" method="post" id="addform"
								modelAttribute="polldata">
							<input type="hidden"  name="clientId" value="${sessionScope.user.comClientName.id}"> 
					<c:forEach var="gp" items="${groupInfo}">
  				<input type="hidden"  name="groupid" value="${gp.groupId}"> 
  				<input type="hidden"  name="g_id" value="${gp.groupId}"> 
				</c:forEach>								<input type="hidden" name="id" id="pollID">
								

								<div class="row" style="margin-top: 2%">
									<div class='col-sm-1'></div>
									<div class='col-sm-2'>
										<b style="font-size: 15px">Question :</b>
									</div>
									<div class='col-sm-4'><textarea rows="2" cols="50" name="question" id="questionVal"
											placeholder="Write Question...."></textarea></div>
									
								</div>
								<div class="row" style="margin-top: 2%">
								<div class='col-sm-1'></div>
									<div class='col-sm-2'>
										<b style="font-size: 15px">To Date</b>
									</div>


									<div class='col-sm-4'>
										<div class="form-group">

											<div class='input-group date' id='datetimepicker2'>
												<input type='text' name="to_Date" id="editdate"
													class="form-control" required="required"
													placeholder="select date" > <span
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
				<!-- ............................................END................... -->
             
             
             <!--.................... poll graph ........................ -->
             
             
             		<div id="mygraph1" class="modal1">
				  <!-- Modal content -->
				  <div class="modal1-content">
				    <span class="closegraph1">&times;</span>
				    <h3> <b style="font-size: 20px;color: green">Poll Graph</b></h3>
				  
				<h4 > <span  id="graphquesion"> </span></h4>
				<br>
				<div class="container createpolls" id="progressBar" style="margin-top: 1%; width: 100%; background-color: white;">
				  </div>
				</div>
				</div>
				             <!--....................poll graph END,,,,,,,,,,,,,,,,,,,,,,  -->
    
         
     <div class="modal " id="groupMemberModal" role="dialog"></div>
     <div class="modal " id="editGroupModel" role="dialog"></div>
     <div class="modal " id="removeMemberModel" role="dialog"></div>
     
     
     
     
<script type="text/javascript">
$(function() {
	$('#datetimepicker1').datetimepicker();
	$('#datetimepicker2').datetimepicker();
})
</script>
     
     
<script>
    


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

function editPoll(id, date) {
	modal1.style.display = "block";
	var data = $("#Questiondata" + id).val();


	$("#questionVal").val(data);
	$("#datemain").hide();
	$("#pollID").val(id);

	
}
function graph(pid) {
	
	 var data = $("#Questiondata" + pid).val();
	  $.ajax({
	         type: "POST",
	         url: "/"+location.pathname.split("/")[1]+"/web/taskforce/serives/grouppoll/graph",
	         data:'pollid='+ pid,
	         dataType: 'json',
	         success: function(data){ 
	        	var optionlist="";
	        	 
	        	for ( var i = 0, len = data.length; i < len; ++i) {
		                var option = data[i];

		               optionlist+= '<div class="row" style="margin-top: 2%">'+
		               '									<div class=\'col-sm-1\'></div>'+
		               '									<div class=\'col-sm-2\'>'+
		               '										<b style="font-size: 13px">'+option.optionname+' :</b>'+
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
	
 	graphid1.style.display = "block";
	$("#datemain").hide(); 
}


//............................................................................................................
	// When the user clicks on <span> (x), close the modal
	closegraph.onclick = function() {
		graphid.style.display = "none";
       
	}
	span.onclick = function() {
		modal1.style.display = "none";
		$("#datemain").show();
	}
	closegraph1.onclick = function() {
		graphid1.style.display = "none";
		$("#datemain").show();
	}
	
	
// When the user clicks anywhere outside of the modal, close it	
window.onclick = function(event) 
{
	if (event.target == graphid) {
		graphid.style.display = "none";
		
	}
	if (event.target == modal1) {
		modal1.style.display = "none";
		$("#datemain").show();
	}
	if (event.target == graphid1) {
		graphid1.style.display = "none";
		$("#datemain").show();
	}
	
}



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
<script type="text/javascript">
  $(document).ready(function() {
	  $(".btn-pref .btn").click(function () {
	      $(".btn-pref .btn").removeClass("btn-success").addClass("btn-default");
	      // $(".tab").addClass("active"); // instead of this do the below 
	      $(this).removeClass("btn-default").addClass("btn-success");   
	  });
  });
  </script>

 </body>
