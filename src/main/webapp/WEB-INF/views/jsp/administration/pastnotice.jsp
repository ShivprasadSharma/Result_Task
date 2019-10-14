<%@page import="javax.naming.NoInitialContextException"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url var="getBlankNotice" value="/web/taskforce/service/notifications" />
<c:url var="getNoticeDtl" value="/web/taskforce/service/notification/"/>
<c:url var="getNoticeByDep" value="/web/taskforce/service/notification/past/" />
<c:url var="getAllNotices" value="/web/taskforce/service/notification/list" />
<script>
$(document).ready( function(){
	   $("img").click(function() {
		    var noticeId = document.getElementById("noticeId").value;
		    window.location = "<c:url value='/web/taskforce/service/notification/download/'/>" + noticeId;
	   });
	});
function sort(){
	var select = document.getElementById("selectdept");
 	var typ = document.getElementById("selectType");
 	 if(select.value == 0 && typ == 0){
 		window.location = '${getAllNotices}';
 	}else{
 		window.location = '${getNoticeByDep}'+select.value+'/'+typ.value;
 	} 
}

function goToNoticeDetails(notificationId)
{
	$.get('${getBlankNotice}'+notificationId, function(result)
	{
		 $("#noticeFormModal").html(result);
    });
    $("#noticeFormModal").modal('show');
}

</script>
<body class="hold-transition skin-blue fixed sidebar-mini" >
<div class="wrapper ">
<div class="content-wrapper">
<!-- Main content -->
    <section class="content" >
          <c:set var="list" value="${notificationList}"/>	
          <c:set var="s" value="1"/>
          <c:set var="a" value="1"/>
          <c:forEach var="qd" items="${list}">
           <c:set var="s" value="${s * qd.department}" />
          <c:set var="a" value="${a * qd.notificationType }"/>
          </c:forEach>
          <div class="col-md-12 ">
		    <section class="content-header">
				<div class="col-md-12 col-sm-12 col-lg-12">
					<div  style="padding-top: 21px;">  
						<div class=" col-md-3 col-sm-3 col-lg-3"><font size="3px"><b>Past Notices</b></font></div>
						    <div class="styled-select select col-md-3 col-sm-4 col-lg-3"> 
							<c:set var="grades" value="1,2,3,4,5,6,7,8,9"  scope="application" />
							<c:set var="opton" value="Civil,Electrical,Mechanical,Mechanical,Chemical,Computer,Automobile,Produnction,Information Technology,Electronics and Telecommunication"  scope="application" />
							  <c:forEach var="ss" items="${list}">
					          <c:set var="d" value="${ss.department}"/>
					          <c:set var="t" value="${ss.notificationType }"/>
					          </c:forEach>  
					           <c:set var="deptlist" value="${departments}"/>	
					       <span>   
					       <c:if test="${s == 0 }" >
							    <select onchange="sort()" id="selectdept" class="form-control">
								    <c:forEach var="item" items="${deptlist}">
								        <option value="${item.dep_id}">${item.dep_name}</option>
								    </c:forEach> 
								</select>
							</c:if>
							<c:if test="${s > 0 }">
							   <select onchange="sort()" id="selectdept" class="form-control">
								    <c:forEach var="item" items="${deptlist}">
								        <option value="${item.dep_id}" ${(d == item.dep_id) ? 'selected':'' }>${item.dep_name}</option>
								    </c:forEach> 
								</select>
							</c:if>
							</span>
							</div>
							<div class="styled-select select col-md-3 col-sm-4 col-lg-3"> 
							<c:set var="grades" value="1,2,3,4,5,6,7,8,9"  scope="application" />
							<c:set var="opton" value="Civil,Electrical,Mechanical,Mechanical,Chemical,Computer,Automobile,Produnction,Information Technology,Electronics and Telecommunication"  scope="application" />
							  <c:forEach var="ss" items="${list}">
					          <c:set var="d" value="${ss.department}"/>
					          </c:forEach>  
					       <span>  
					       <c:if test="${a == 0 }" >
							 <select id="selectType" onchange="sort()">
								    <option value="0">Genral Notice</option>
									<option value="1" >Academics</option>
						      	    <option value="2" >Awards & Achivement</option>
									<option value="3" >Circulars</option>
									<option value="4">Event</option>
									
							    </select>
							</c:if>
							<c:if test="${a > 0 }" >
							   <select id="selectType" title="Grade Obtained" onchange="sort()">
								    <option value="0" ${(t == 0) ? 'selected':'' }>Notice Type</option>
									<option value="1" ${(t == 1) ? 'selected':'' }>Academics</option>
						      	    <option value="2" ${(t == 2) ? 'selected':'' }>Awards & Achivement</option>
									<option value="3" ${(t == 3) ? 'selected':'' }>Circulars</option>
									<option value="4" ${(t == 4) ? 'selected':'' }>Event</option>
									    
								</select>
							</c:if>
							</span>
							</div>
						</div>
				</div>
			</section >
			
     			  <!-- Info boxes -->
			     <div class="col-md-12" style="height:76%; overflow-x:hidden " >
			     <jsp:useBean id="now" class="java.util.Date"/>
			     <br>
			     <c:forEach var="notice" items="${list}">
			      <c:if test="${(notice.notificationToDate lt now )  &&  notice.notificatiosHeadline != Null}">
	                   <div class="box box-default" style="border-radius:21px" >
	                           <div class="box-body" >
					                  <ul>
						                 <div class="floatleft"><li style="color:green;" class="li">${notice.notificatiosHeadline}</li></div>
						                  <div class="floatright">
						                     <li style="color:black" class="li">Deparment: 
							                   <c:forEach var="item" items="${deptlist}">
									             <c:if test="${notice.department == item.dep_id}">${item.dep_name}</c:if>
									          </c:forEach> 
									         </li>
									      </div>
									  </ul>
						            </div>
						           <div class="col-md-12 col-sm-12 " style="padding-left:30px">
									 <c:forEach var="noticeImage" items="${notice.notificationFilesDTO}">
									    <div class="col-md-2 col-sm-2">
										  <c:set var="fileExtension" value="${noticeImage.getDocument1Type()}"/>
										     <c:choose>
											     <c:when test="${fileExtension eq 'application/msword'}">
											          <img src="<c:url value="/static/img/doc.jpg"/>" width="80px" height="80px">
											     </c:when>
											     <c:when test="${fileExtension eq 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'}" >
											           <img src="<c:url value="/static/img/Excel-Logo.png"/>" width="80px" height="80px">
											     </c:when>
											      <c:when test="${fileExtension eq 'application/zip'}" >
											           <img src="<c:url value="/static/img/Excel-Logo.png"/>" width="80px" height="80px">
											     </c:when>
											     <c:when test="${fileExtension eq 'application/pdf'}" >
											           <img src="<c:url value="/static/img/pdf_logo.png"/>" width="80px" height="80px">
											     </c:when>
											     <c:when test="${fileExtension eq 'application/vnd.ms-powerpoint'}" >
											           <img src="<c:url value="/static/img/ppt_logo.jpg"/>" width="80px" height="80px">
											     </c:when>
											      <c:when test="${not empty noticeImage.getString1()}" >
											            <img  src="${noticeImage.getString1()}" width="80px" height="80px"/>
											     </c:when> 
											     <c:otherwise>
											            <img src="<c:url value="/static/img/nofileIcon.png"/>" width="80px" height="80px">
											     </c:otherwise>
										     </c:choose>
									     </div>
									</c:forEach>
									 <div class="col-md-10 col-sm-9">
						                 <c:set var="msg" value="${notice.notificationDetails}"/>
											<p style="padding-left:10px ; color:gray">
												${fn:substring(msg,0, 400)}
												<c:if test="${fn:length(msg) >= 400}"> ...</c:if>
											</p>
						                </div>
						              <div >
						              	<a href="<c:url value="/web/taskforce/service/notification/${notice.notificationId}" />" ><font style="padding-left:15px" size="1px">View Details</font></a>
						              </div>
						              </div><br><br><br>
						              <div class="box-body" >
						                <ul>
						                   <div  class="floatleft">
						                     <c:if test="${not empty notice.notificatioStatus}">
						                         <li style="color:green" class="li">
						                              Student Notified: ${notice.notificatioStatus}
						                          </li>
						                     </c:if>
						                   </div>
					                       <div class="floatright" >
						                       <li style="color:black" class="li">
							                         Date: <fmt:formatDate pattern="dd MMM" value="${notice.notificationFromDate}" />
						                             To <fmt:formatDate pattern="dd MMM yyyy" value="${notice.notificationToDate}" />
 						                          </li>
						                    </div>
									        <br>
									    </ul> 
									    <ul>
									        <div  class="floatleft">
										        <c:if test="${notice.venue ne 'none'}" >
										            <li style="color:green" class="li">
										                    Venue: ${notice.venue}
										             </li>
									             </c:if>
									        </div>
						                    <div class="floatright">
						                         <li style="color:black" class="li">
						                                 Time: <fmt:formatDate type="time" timeStyle="short" value="${notice.notificationFromDate}" /> to 
																  <fmt:formatDate type="time" timeStyle="short" value="${notice.notificationToDate}" />
						                          </li>
						                     </div>
									  </ul>	
									  <div class="col-md-12">
									   	   <hr/>
									   	   <font size="3" style="color:green" >
								               Likes: <b id="likeCount${notice.notificationId}">${notice.like_count}</b>
								            </font>&nbsp;&nbsp;
									           <button type="submit" class="btn" value="${notice.like_Status}" id="likebtn${notice.notificationId}" style="background-color:white;border-style: solid;border-color: #e6e6e6;<c:choose><c:when test="${notice.like_Status == 'true'}" >color:blue;</c:when><c:otherwise></c:otherwise></c:choose>" onclick="likeFunction(${notice.notificationId},${sessionScope.user.comClientName.id})">
									        <li class="fa fa-heart-o" style="font-size:20px"></li>
									        <b>&nbsp;Like</b></button>
									   </div>		  
					            </div>
					        </div>
						  </c:if>
						  </c:forEach> 
						  <%-- <c:forEach var="notice" items="${list}">
						   <c:if test="${notice.notificationId ne null}">
					       <input type="hidden" value="${notice.notificationId}" id="noticeId">	
						   <div class="box " >
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
							            <div style="text-align:left">
						              		<a href="${notice.notificationId}" ><font style="padding-left:15px" size="1px">View Details</font></a>
						              </div>
							        </div>
							        <div  class="floatleft">
						                     <c:if test="${not empty notice.notificatioStatus}">
						                         <li style="color:green" class="li">
						                              Student Notified: ${notice.notificatioStatus}
						                          </li>
						                     </c:if>
						                   </div>
					                       <div class="floatright" >
						                       <li style="color:black" class="li">
						                             Date: <fmt:formatDate pattern="dd MMM" value="${notice.notificationFromDate}" />
						                             <c:if test="${notice.notificationToDate ne null}">To</c:if>
						                              <fmt:formatDate pattern="dd MMM yyyy" value="${notice.notificationToDate}" />
 						                          </li>
						                    </div>
							        <div class="col-md-12">
							            <div class="floatleft">
									        <c:choose>
										        <c:when test="${notice.venue ne 'none' && notice.venue ne null}" >
										            <li style="color:green" class="li">
										                    Venue: ${notice.venue}
										             </li>
									             </c:when>
									             <c:otherwise  >
										            <li style="color:green" class="li">
										                    Post By: ${notice.postNotice}
										             </li>
									             </c:otherwise>
									         </c:choose>
									      </div>
						                    <div class="floatright">
						                         <li style="color:black" class="li">
						                         Time: <fmt:formatDate type="time" timeStyle="short" value="${notice.notificationFromDate}" />
						                               <c:if test="${notice.notificationToDate ne null}">To</c:if>  
													  <fmt:formatDate type="time" timeStyle="short" value="${notice.notificationToDate}" />
						                          </li>
						                     </div>
							        </div>
						            <div style="padding-left:20px;padding-top:100px ">
						               <font size="3" style="color:green" >
						               		Likes: <b id="likeCount${notice.notificationId}">${notice.like_count}</b>
						               </font>&nbsp;&nbsp;
						            </div>   	
						            <div class="col-md-12"><hr/>
						               <button type="submit" class="btn" value="${notice.like_Status}" id="likebtn${notice.notificationId}" style="background-color:white;border-style: solid;border-color: #e6e6e6;<c:choose><c:when test="${notice.like_Status == 'true'}" >color:blue;</c:when><c:otherwise></c:otherwise></c:choose>" onclick="likeFunction(${notice.notificationId},${sessionScope.user.comClientName.id})"><li class="fa fa-thumbs-o-up" style="font-size:20px"></li><b>&nbsp;Like</b></button>&nbsp;&nbsp;
							       </div>						            						            
								</div>
						      </div>
						      </c:if>	
					       </c:forEach> --%>
					</div>
			</div>

	 <div id="userDetails" class="tab-pane fade" ></div>
     <div id="listDetails" class="tab-pane fade"></div>
    </section>
      <!-- /.row -->
   <div id="noticeDetails" class="tab-pane fade"></div> 
</div>
</div>
<div class="modal fade" id="noticeFormModal" role="dialog">
		<!-- %@include file="masterform.jsp"%-->
	</div>
	<div class="modal fade" id="noticeFormModalLoading" role="dialog" >
		<div class="modal-dialog">
			<div class="modal-content" id="noticeModalLoading">
				<div class="modal-body">
					<img height="50" width="50" class="img-responsive"
						src='<c:url value="/static/dist/img/loading.gif"/>' />
				</div>
			</div>
			
		</div>
		<!-- %@include file="masterform.jsp"%-->
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
	.floatleft {
	    float:left;
	}
	.floatright {
	    float:right;
	}
	.li {
	    display: inline;
	    padding: 0 0.3em; 
		line-height:25px;
	}
	.styled-select {
   background: url(http://i62.tinypic.com/15xvbd5.png) no-repeat 111% 0;
   height: 29px;
   overflow: hidden;
   width: 240px;
}

.styled-select select {
   background: transparent;
   border: none;
   font-size: 14px;
   height: 29px;
   padding: 5px; /* If you add too much padding here, the options won't show in IE */
   width: 200px;
}

.styled-select.slate {
   background: url(http://i62.tinypic.com/2e3ybe1.jpg) no-repeat right center;
   height: 34px;
   width: 240px;
}

.styled-select.slate select {
   border: 1px solid #ccc;
   font-size: 16px;
   height: 34px;
   width: 10px;
}
	li.special_text {
	    font-size: 200%;
	    font-weight: bold;
	    font-family: "Arial";
	}
	
	li.special_text a {
	    text-decoration: none;
		color:green;
	}

</style>