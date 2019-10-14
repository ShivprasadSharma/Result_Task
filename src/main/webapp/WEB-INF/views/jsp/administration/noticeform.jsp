<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- <c:url var="download" value="/web/taskforce/service/notification/download" />
 --%>
<body class="hold-transition fixed skin-blue sidebar-mini" >

<div class="wrapper myStyle">
<div class="content-wrapper">
   
<!-- Main content -->
    <section class="content " >
	   <div class="col-md-9 col-sm-9">
    <section class="content-header " style="margin-top:10px">
    <h5>
<%--         <a href="<c:url value="/web/taskforce/service/notification/list" />">Notice Board</a> > ${noticeForm.notificatiosHeadline}
 --%>     </h5>
     </section >
       <div class="box box-default " style="border-radius:21px; " >
		 <div class="box-body"  >
			    <div class="clo-md-12 col-sm-12">
				    <div class="col-md-9 col-sm-9"><h4 style="color:#008000; padding-left:20px ">${noticeForm.notificatiosHeadline} </h4></div>
		             <c:choose>
			              <c:when test="${noticeForm.postNotice ne null}" >
							Post By: ${noticeForm.postNotice}
						  </c:when>
		              </c:choose>		              
		            </div><br>
	                <div class="form-group pul-left" style="padding-left:20px">
	                  <input type="hidden" id="noticeId" value="${noticeForm.notificationId }">
	                  <c:forEach var="noticeImage" items="${noticeForm.notificationFilesDTO}">
	                  <%-- <input type="hidden" value="${noticeImage.getByteArrayString()}" id="docfile">
						<input type="hidden" id="context" value="${noticeImage.getDocument1Type()}" > --%>
						 <div class=" ">
						  <div class="" >
							<div class="" >
							     <c:set var="fileExtension" value="${noticeImage.getDocument1Type()}"/>
							     <c:choose >
								     <c:when test="${fileExtension eq 'application/msword'}">
								         <img onclick="downloadfile(${noticeForm.notificationId})" src="<c:url value="/static/img/doc.jpg"/>" width="200px" height="200px">
								     </c:when>
								     <c:when test="${fileExtension eq 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'}" >
								         <img onclick="downloadfile(${noticeForm.notificationId})" src="<c:url value="/static/img/Excel-Logo.png"/>" width="200px" height="200px">
								     </c:when>
								      <c:when test="${fileExtension eq 'application/zip'}" >
								         <img onclick="downloadfile(${noticeForm.notificationId})" src="<c:url value="/static/img/Excel-Logo.png"/>" width="200px" height="200px">
								     </c:when>
								     <c:when test="${fileExtension eq 'application/pdf'}" >
								         <img onclick="downloadfile(${noticeForm.notificationId})" src="<c:url value="/static/img/pdf_logo.png"/>" width="200px" height="200px">
								     </c:when>
								     <c:when test="${fileExtension eq 'application/vnd.ms-powerpoint'}" >
								         <img onclick="downloadfile(${noticeForm.notificationId})" src="<c:url value="/static/img/ppt_logo.jpg"/>" width="200px" height="200px">
								     </c:when>
								      <c:when test="${not empty noticeImage.getString1()}" >
								         <img onclick="downloadfile(${noticeForm.notificationId})" src="${noticeImage.getString1()}" width="200px" height="200px"/>
								     </c:when>
								     <c:otherwise>
								        <img src="<c:url value="/static/img/nofileIcon.png"/>" width="80px" height="80px">
								     </c:otherwise>
							     </c:choose>
							 </div>
					       </div>
					     </div>
	                  </c:forEach>
	                </div>
	               
	               <h4 style="color:gray; padding-left:20px ">${noticeForm.notificationDetails}</h4> 
	              <div class="col-md-12">
					<hr/>
				  <font size="3"  >
					  <li class="fa fa-heart-o" style="font-size:20px"></li> &nbsp; &nbsp;: &nbsp;&nbsp;<b id="likeCount${noticeForm.notificationId}">${noticeForm.like_count}</b>
				  </font>&nbsp;&nbsp;
					<button type="submit" class="btn" value="${noticeForm.like_Status}" id="likebtn${noticeForm.notificationId}" 
					<c:choose><c:when test="${noticeForm.like_Status == 'true'}" ></c:when>
					<c:otherwise>
					</c:otherwise>
					</c:choose>" onclick="likeFunction(${noticeForm.notificationId},${sessionScope.user.comClientName.id})">
						<li class="fa fa-heart-o" style="font-size:15px"></li>
						<b>&nbsp;Like</b>
					</button>
				  </div>	
              </div>
	      </div>
	  </div>
     <!-- /.col -->
      <div class="col-md-3 col-sm-3 myStyle1" style="height:88%">
             <div >
				  <div style="border-radius:21px; margin-top:20px">
					   <!-- /.box-header -->
					   <div class="box-body" align="center">
					        <h4>Total No. of </h4><h4>Student's</h4><h4 style="color:008000">${noticeForm.totalStudent}</h4> <hr>
					        <c:choose>
						        <c:when test="${noticeForm.department eq 0 }">
						         <h4>Departments </h4><h4>Invites</h4><h4 style="color:008000">All</h4> <hr>
						        </c:when>
						        <c:when test="${noticeForm.groups ne 0 && noticeForm.groups ne null}">
						         <h4>Departments </h4><h4>Invites</h4><h4 style="color:008000">Group</h4> <hr>
						        </c:when>
						        <c:otherwise>
						           <h4>Departments </h4><h4>Invites</h4><h4 style="color:008000">1</h4> <hr>
						        </c:otherwise>
					        </c:choose>
					        <h4>Out of </h4><h4> Message Send To</h4><h4 style="color:008000">${noticeForm.totalSendNotices}</h4> <hr>
					    </div>
					</div>
			  </div>			
	    </div>
   </section>
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
<script >
$(document).ready(function () { 
		
		$("#notificatiosDetails").cleditor(); 	
	});
   
	function downloadfile(noticeId)
	{
		
		 //window.location = "<c:url value='/web/taskforce/service/notification/download/'/>" + noticeId;
	}
	$(function () {
        $('#datetimepicker6').datetimepicker();
        $('#datetimepicker7').datetimepicker({
            useCurrent: false //Important! See issue #1075
        });
        $("#datetimepicker6").on("dp.change", function (e) {
            $('#datetimepicker7').data("DateTimePicker").minDate(e.date);
        });
        $("#datetimepicker7").on("dp.change", function (e) {
            $('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
        });
    });
	
	/* function downloadfile(){
		var url = document.getElementById("docfile").value;
		var context = document.getElementById("context").value;
		var link = document.createElement('a');
		    link.download = "Document."+context.split("/")[1];
		    link.href = 'data:application/octet-stream;base64,' + url;
		    link.click();
	} */
	function view(){
		var url = document.getElementById("docfile").value;
		var context = document.getElementById("context").value;
	    //window.open("data:"+context+";base64," + url,"_self","width=400,height=400");
	    window.open("data:"+context+";base64," + url, "_self");
	}
</script>
