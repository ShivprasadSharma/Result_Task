<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="<c:url value="/static/css/bootstrap.min.css"/>" type='text/css' />
<link rel="stylesheet" href="<c:url value="/static/css/styles.css"/>" type='text/css' />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<c:url var="addGroupNotice" value="/web/taskforce/notice/blankMentorNotice/" />
<c:url var="getGroupMember" value="/web/taskforce/mentor/students/" />
<c:url var="removeMember" value="/web/taskforce/service/mentor/d/student/remove/" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style>
.ScrollStyle
{
    max-height: 590px;
    overflow-y: scroll;
}

</style>

       <body class="hold-transition skin-blue fixed sidebar-mini" >
           <div style="height:89%; margin-top:73px" >
			     <c:set var="loginuser">
			          <sec:authentication property="principal.username"/>
			     </c:set>
					 <div class="left" style="background-color: white">
		                <section class="section ScrollStyle"" >
		                  <div class="box-body">		                 
		                  <c:forEach var="m" items="${mentor}">
		                    <button type="button" onclick="addMembers(${m.id},${id} )" class="btn btn-block btn-success ">Add Member</button>
			                 <form action=""></form>
			                    <c:forEach var="ms" items="${m.mentorStudent }" >
							       <div class="section">
									  <img src="<c:url value="/static/img/author.png"/>" style="border-radius: 50px;height:40px;width:40px" alt="Member Image">
								      &nbsp;
							          <span class="hidden-xs" style="color:green">${ms.student.rollNo}:&nbsp;${ms.student.comClientName.firstName} &nbsp; ${ms.student.comClientName.lastName}</span>	  
								  </div><br>
								  </c:forEach>
							   </c:forEach> 
							</div>
		                </section>
		            </div>
			       <div class="contents" style="background-color:#d3efc6">
			         <section  class="content-header" >
				       <div class="box-body" >
				         <c:forEach var="m" items="${mentor}">
		                    <div class="col-md-9 col-sm-6">
			                    <span><a href="<c:url value='/web/taskforce/service/mentor/grplist/' />${m.staff.staffId}">GFM GROUP</a> >> <font size="4"></font></span>
			                </div>
			         		 <div class="col-md-3 " align="right">
								   <button class="btn btn" style="background-color:#008000; color:white;" onclick="addGroupNotice(${m.id})" >New Post </button>
								        &nbsp; 
							         <div class="btn-group">
							            <i class="fa fa-gear" data-toggle="dropdown"></i>
							              <ul class="dropdown-menu">
							                  <li><a onclick="removeMember(${m.id})"><i class="fa fa-remove"></i>Remove Member</a></li>
							              </ul>
							           </div>
							        </div>							    
							</c:forEach>					          
						  </div>
						 <div style="height:490px;overflow-x:hidden ">						
					       <c:forEach var="notice" items="${notices }">	
					       <input type="hidden" value="${notice.notificationId}" id="noticeId">	
						     <div class="box " >
					           <div class="box-body">					          
					                 <div class="col-md-12 col-sm-12">
						              <c:forEach var="noticeImage" items="${notice.notificationFilesDTO}">
						                <div class="col-md-3 col-sm-3" align="center">
						                   		 <c:set var="fileExtension" value="${noticeImage.getDocument1Type()}"/>
											     <%-- <c:choose>
												     <c:when test="${not empty noticeImage.getString1()}" >
												        <img  src="${noticeImage.getString1()}" width="100px"/>
												     </c:when>
												     <c:otherwise>
												        <img src="<c:url value="/static/img/doc.jpg"/>" width="100px"">
												     </c:otherwise>
											     </c:choose> --%>
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
												        <img src="<c:url value="/static/img/doc.jpg"/>" width="100px"">
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
						               <font size="2">Likes: <b id="likeCount${notice.notificationId}">${notice.like_count}</b></font>&nbsp;&nbsp;
						                <font size="2" >Comments : <b id="commentCount${notice.notificationId}"><c:choose><c:when test="${empty notice.comment_count}" >0</c:when><c:otherwise>${notice.comment_count}</c:otherwise></c:choose>  							 
						            </div>   	
						            <div class="col-md-12"><hr/>
						               <button type="submit" class="btn" value="${notice.like_Status}" id="likebtn${notice.notificationId}" style="background-color:white;border-style: solid;border-color: #e6e6e6;<c:choose><c:when test="${notice.like_Status == 'true'}" >color:blue;</c:when><c:otherwise></c:otherwise></c:choose>" onclick="likeFunction(${notice.notificationId},${sessionScope.user.comClientName.id})"><li class="fa fa-heart-o" style="font-size:15px"></li><b>&nbsp;Like</b></button>&nbsp;&nbsp;
							           <button type="submit" class="btn" style="background-color:white;    border-style: solid;border-color: #e6e6e6;" onclick="commentfunction(${notice.notificationId},${sessionScope.user.comClientName.id})" value="true" id="commentbtn${notice.notificationId}"><i class="fa fa-commenting-o" style="font-size:15px"></i> &nbsp;<b>Comment</b></button>
						           </div>
						            <div class="col-md-12" style="background-color: white-space:;display: none;" id="commentbox${notice.notificationId}"><hr/>
							          <div id="commentlist${notice.notificationId}">
							           </div>
							             <div class="row">
  											<div class="col-sm-8"> <input id="commenttext${notice.notificationId}" type="text" placeholder="add comment.." autocomplete="off" /></div>
  											<div class="col-sm-4 "> <button class="btn btn comment" style="" onclick="commentInsert(${notice.notificationId},${sessionScope.user.comClientName.id},${notice.comment_count})" >Comment </button></div>
										</div>						             
						            </div>
								</div>
						      </div>
					       </c:forEach>
				       </div>
				 </section> 
            </div>
		    <div class="right" style="background-color:white">
			    <div class="content-header">	    
				   <h4><p align="center">Total Number of Members</p>				  
				      <c:forEach var="group" items="${mentor}">				       
				        <c:set var="s" value="${group.mentorStudent}"></c:set>
	    						<c:forEach var="t" items="${s}">	    						
	    						    <c:set var="count" value="${count + 1}" scope="page"/>
	    						</c:forEach>
	   					<p align="center">${count}</p> 
				    </c:forEach>
				    </h4> 
				    <hr>
				    <h4><p align="center">Recent Activity</p></h4>
 				</div>
             </div>
        </div>
     <div class="modal " id="noticeFormModal" role="dialog"></div>
     <div class="modal " id="groupMemberModal" role="dialog"></div>
     <div class="modal " id="editGroupModel" role="dialog"></div>
     <div class="modal " id="removeMemberModel" role="dialog"></div>
 <script>
    function addGroupNotice(d)
	{
	    $.get('${addGroupNotice}'+  d, function(result)
		{
			$("#noticeFormModal").html(result);
	    });
	    $("#noticeFormModal").modal('show');
	}

	function addMembers(d,m)
	{
		 $.get('${getGroupMember}'+d+"/"+m, function(result)
		{
			$("#groupMemberModal").html(result);
	    });
	    $("#groupMemberModal").modal('show'); 
	}
	function removeMember(d)
	{
		$.get('${removeMember}'+d, function(result)
		{
			$("#removeMemberModel").html(result);
	    });
	    $("#removeMemberModel").modal('show');
	}
	/* $(document).ready( function(){
		   $("img").click(function() {
			    var noticeId = document.getElementById("noticeId").value;
			    window.location = "<c:url value='/web/taskforce/service/notification/download/'/>" + noticeId;
		   	});
		}); */
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

