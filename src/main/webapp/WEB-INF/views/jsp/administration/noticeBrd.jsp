<%@ page import="javax.naming.NoInitialContextException"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url var="getBlankNotice" value="/web/taskforce/service/notification" />
<%-- <c:url var="getNoticeDtl" value="/web/taskforce/service/notification/"/> --%>
<c:url var="getNoticeByDep" value="/web/taskforce/service/notification/" />
<c:url var="getAllNotices" value="/web/taskforce/service/notification/list" />
<body class="hold-transition skin-blue fixed sidebar-mini" >
 <div class="wrapper">
  <div class="content-wrapper">
   <!-- Main content -->
    <section class="content " >
       <c:set var="list" value="${notificationList}"/>	
       <c:set var="s" value="1"/>
       <c:set var="a" value="1"/>
       <c:forEach var="qd" items="${list}">
          <c:set var="s" value="${s * qd.department}" />
          <c:set var="a" value="${a * qd.notificationType }"/>
       </c:forEach>
        
          <div class="col-md-9 col-sm-9 main" >
		    <section>
			  <div >		  
				<%-- <div class=" col-md-9 col-sm-9" style="padding-top: 17px;">  
						<div class=" col-md-3 col-sm-3 "><font size="3px"><b>Notice Board</b></font></div>
							<div class="styled-select select col-md-5 col-sm-4"> 
							  <c:forEach var="ss" items="${list}">
					          <c:set var="d" value="${ss.department}"/>
					          <c:set var="t" value="${ss.notificationType }"/>
					          </c:forEach>  
					           <c:set var="deptlist" value="${departments}"/>	
					       <span>   
					    	    <c:if test="${s == 0 }" >
							   <select onchange="sort()" id="selectdept" class="form-control">
							   	<option value="0">All</option>
								    <c:forEach var="item" items="${deptlist}">
								        <option value="${item.dep_id}">${item.dep_name}</option>
								    </c:forEach> 
								</select>
							</c:if>
							<c:if test="${s > 0 }">
							   <select onchange="sort()" id="selectdept" class="form-control">
							   	 <option value="0">All</option>
							        <c:forEach var="item" items="${deptlist}">
								        <option value="${item.dep_id}" ${(d == item.dep_id) ? 'selected':'' }>${item.dep_name}</option>
								    </c:forEach> 
								</select>
							</c:if>
							</span>
							</div>
							<div class="styled-select select col-md-4 col-sm-8"> 
							<c:set var="grades" value="1,2,3,4,5,6,7,8,9"  scope="application" />
							<c:set var="opton" value="Civil,Electrical,Mechanical,Mechanical,Chemical,Computer,Automobile,Produnction,Information Technology,Electronics and Telecommunication"  scope="application" />
							  <c:forEach var="ss" items="${list}">
					          <c:set var="d" value="${ss.department}"/>
					          </c:forEach>  
					       <span>  
					       <c:if test="${a == 0 }" >
							 <select id="selectType" onchange="sort()">
								    <option value="0">ALL</option>
									<option value="1" >Department</option>
						      	    <option value="2" >Awards & Achievement</option>
									<option value="3" >Circulars</option>
									<option value="4">Event</option>
								</select>
							</c:if>
							<c:if test="${a > 0 }" >
							   <select id="selectType" title="Grade Obtained" onchange="sort()">
								    <option value="0" ${(t == 0) ? 'selected':'' }>ALL</option>
									<option value="1" ${(t == 1) ? 'selected':'' }>Department</option>
						      	    <option value="2" ${(t == 2) ? 'selected':'' }>Awards & Achievement</option>
									<option value="3" ${(t == 3) ? 'selected':'' }>Circulars</option>
									<option value="4" ${(t == 4) ? 'selected':'' }>Event</option>
								</select>
							</c:if>
							</span>
						</div>
					</div>
				<sec:authorize access="hasAnyRole('ROLE_TEACHER','ROLE_HOD','ROLE_LECTURER','ROLE_ADMIN','ROLE_TPO')">
					<div class="col-md-3 col-sm-3" style="padding-bottom: 10px;padding-top: 13px;">
					<a href="<c:url value="/web/taskforce/service/notification" />"><input type="submit"  value="Pin New Notice" class="btn" style="background-color:#008000;color:#fff;font-weight: bold;"></a>
			      </div>
				</sec:authorize>
				</div>
			 --%>	
             </section  >
            
            <!-- Info boxes -->
             <div class="col-md-12 col-sm-16 " style="overflow: auto; height:89%; text-align: left" >
			     <c:forEach var="notice" items="${list}">
			       <c:if test="${notice.notificatiosHeadline != null}">
	                  <div class="box box-default " style="border-radius:7px; "  >
	                       <div class="box-body" >
	                       <input type="hidden" id="noticeId" value="${notice.notificationId }">
	                                <ul>
						               <li style="color:green" class="li">${notice.notificatiosHeadline} </li>
						                <div class="floatright">
						                  <li style="color:black" class="li">Department:
						                      <c:if test="${notice.groups ne null }"> 
						                        <c:forEach var="group" items="${group}">
									                  <c:if test="${notice.groups == group.groupId}">${group.groupName}</c:if>
									                </c:forEach>
									            </c:if>
									            <c:if test="${notice.department ne null }">
											        <c:forEach var="item" items="${deptlist}">
									                  <c:if test="${notice.department == item.dep_id}">${item.dep_name}</c:if>
									                </c:forEach> 
									             </c:if>
									          </li>
								          </div>
								       </ul>
								       <hr>
						            </div>
						            
						           <div class="col-md-12  col-sm-12" style="padding-left:0px">
									 <c:forEach var="noticeImage" items="${notice.notificationFilesDTO}">
										<div class="col-md-2 col-sm-2">
										   <c:set var="fileExtension" value="${noticeImage.getDocument1Type()}"/>
										     <c:choose>
											     <c:when test="${fileExtension eq 'application/msword'}">
											          <img src="<c:url value="/static/img/doc.jpg"/>" width="70px" height="70px">
											     </c:when>
											     <c:when test="${fileExtension eq 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'}" >
											           <img src="<c:url value="/static/img/Excel-Logo.png"/>" width="70px" height="70px">
											     </c:when>
											      <c:when test="${fileExtension eq 'application/zip'}" >
											           <img src="<c:url value="/static/img/Excel-Logo.png"/>" width="70px" height="70px">
								     		     </c:when>
								     		     <c:when test="${fileExtension eq 'application/pdf'}" >
											           <img src="<c:url value="/static/img/pdf_logo.png"/>" width="70px" height="70px">
								     		     </c:when>
											     <c:when test="${fileExtension eq 'application/vnd.ms-powerpoint'}" >
											           <img src="<c:url value="/static/img/ppt_logo.jpg"/>" width="70px" height="70px">
											     </c:when>
											     <c:when test="${not empty noticeImage.getString1() }" >
											            <img  src="${noticeImage.getString1()}" width="70px" height="70px"/>
											     </c:when>
											       <c:otherwise>
											            <img src="<c:url value="/static/img/nofileIcon.png"/>" width="70px" height="70px">
											     </c:otherwise>
										     </c:choose>
										  </div> 
								       </c:forEach>
								       <div class="col-md-10 col-sm-10 " style="text-align:left">
						                 <c:set var="msg" value="${notice.notificationDetails}"/>
											<p style="padding-left:0px ; color:gray">
												${fn:substring(msg,0, 50)}
												${fn:substring(msg,51, 100)}
												${fn:substring(msg,101, 300)}
												<c:if test="${fn:length(msg) >= 100}"> ...</c:if>
											</p>
						                </div>
						              <div style="text-align:left">
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
						                             <c:if test="${notice.notificationToDate ne null}">To</c:if>
						                              <fmt:formatDate pattern="dd MMM yyyy" value="${notice.notificationToDate}" />
 						                          </li>
						                    </div>
									      <br>
									   </ul> 
									   
									 <ul>
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
						 
						 <%--  <c:forEach var="notice" items="${list}">
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
	<!-- /.col -->
      <div class="col-md-3 col-sm-3 right" style="background-color:#fff;">
             <div style="text-align:left">
				  <div class=""  style="border-radius:21px;">
					  <div >
							<h4>Upcoming......</h4> <hr>
							<%-- ${upcomingNotice} --%>
								<div style="height:86%;overflow-x: hidden;">
								    <c:forEach var="notice" items="${upcomingNotice}">
								     <c:set var="noticeHeadline" value="${notice.notificatiosHeadline}" />
									  <c:if test="${not empty noticeHeadline}">
									    	  <div class="box" style="background-color: #eff5ec">
								            <div style="padding-left:10px;">
								              <a href="<c:url value="/web/taskforce/service/notification/${notice.notificationId}"/>"><font size="4px" style="color:#008000">${notice.notificatiosHeadline}</font></a></div>
										       <c:set var="msg" value="${notice.notificationDetails}"/>
												 <p style="padding-left:10px ; color:gray">${fn:substring(msg,0, 70)}
												    <c:if test="${fn:length(msg) >= 70}"> ...</c:if>
												  </p>
								               <div style="padding-left:10px"> Date: <fmt:formatDate pattern="dd MMM" value="${notice.notificationFromDate}" />
						                          To <fmt:formatDate pattern="dd MMM yyyy" value="${notice.notificationToDate}" /></div>
								               <div style="padding-left:10px"> Time: <fmt:formatDate type="time" timeStyle="short" value="${notice.notificationFromDate}" /> 
								                	 To<fmt:formatDate type="time" timeStyle="short" value="${notice.notificationToDate}" />
								               </div>
								          </div> 
									  </c:if>								      
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
  float:left;
  width:22%;
  height:86.5%;
  padding:15px;
  margin-top:0px;
  text-align:center;
  overflow-x:hidden ;
}
.main {
  float:left;
  width:78%;
  height:86.5%;
  padding:15px;
  margin-top:0px;
  text-align:center;
}
@media only screen and (max-width:620px) {
  .menu, .main, .right {
    width:100%;
  }
</style>
<script>
/* $(document).ready( function(){
   $("img").click(function() {
	    var noticeId = document.getElementById("noticeId").value;
	    window.location = "<c:url value='/web/taskforce/service/notification/download/'/>" + noticeId;
   });
}); */
function sort(){
	var select = document.getElementById("selectdept");
 	var typ = document.getElementById("selectType");
 	 if(select.value == 0 && typ.value == 0){
 		window.location = '${getAllNotices}';
 		//window.location = '${getNoticeByDep}'+select.value+'/'+typ.value;
 	}else
 	{
 		window.location = '${getNoticeByDep}'+select.value+'/'+typ.value;
 	} 
}
function addnotice()
{
	$.get('${getBlankNotice}', function(result)
	{
		 $("#noticeFormModal").html(result);
    });

	$("#noticeFormModal").modal('show');
}

function goToNoticeDetails(notificationId)
{
	$.get('${getBlankNotice}'+notificationId, function(result)
	{
		 $("#noticeFormModal").html(result);
    });
    $("#noticeFormModal").modal('show');
}
/* function likeFunction(noficationId,client_Id) {
	var likeStatus=$("#likebtn"+noficationId).val();
	if(likeStatus == "false"){
		likeStatus = true;
	}else if(likeStatus == "true"){
		likeStatus= false;
	}
	$.ajax({
         type: "POST",
         url: "/"+location.pathname.split("/")[1]+"/web/sims/like/incriment",
        data:'noticeId='+ noficationId +'&likeStatus='+ likeStatus+'&client_Id='+client_Id,
         dataType: 'json',
         success: function(data){
        		if(data)
        			{
        				var count=$("#likeCount"+noficationId).text();
        				var a=parseInt(count);
        				var commentCount=++a;
        				$("#likeCount"+noficationId).html(commentCount);
        				$("#likebtn"+noficationId).css({"color":"blue"});
        				$("#likebtn"+noficationId).val(true);
        			}else
        			{
        				var count=$("#likeCount"+noficationId).text();
        				var a=parseInt(count);
        				var commentCount=--a;
        				$("#likeCount"+noficationId).html(commentCount);
        				$("#likebtn"+noficationId).css({"color":"black"});
        				$("#likebtn"+noficationId).val(false);
        			}
         },
	     error:function(data){
	    	    alert("Opps..!");
	     }
    }); 	 
} */
</script>

