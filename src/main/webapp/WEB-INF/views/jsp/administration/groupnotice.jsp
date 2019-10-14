<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="saveGroupNotice" value="/web/taskforce/group/notice/save" />
    <div class="modal-dialog">
       <div class="modal-content" id="noticeFormModal">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" style="color:#008000">Add Group Notice</h4>
				</div>
				<div class="modal-body">
					<form:form id="groupNotice" modelAttribute="groupNotice" enctype="multipart/form-data" commandName="groupNotice" action="${saveGroupNotice}" method="post" class="pure-form pure-form-aligned" role="form">
					  <div class="col-sm-12" >
					 	  <div class="col-sm-6">
					 	    <form:input type="hidden" path="groups" value="${groupId}" required="required" class="form-control"/>
					 	    <form:input type="hidden" path="department" value=" " required="required" class="form-control"/>
						    <label class="control-label" style="color:#008000"for="value">Notice Headline:</label>
							 <form:input path="notificatiosHeadline" required="required" class="form-control" placeholder="Notice Headline..." /><br>
						   </div>
					  </div>
					<br>
					<div class="col-sm-11">
					  <div class="col-sm-6">
					    <label class="control-label" style="color:#008000"for="id">Notice Description:</label>
					    <form:input path="notificationDetails" required="required" maxlength="250" class="form-control" placeholder="Notice Description...1 to 250 Characters" /><br>
					  </div>
					  <div class="col-sm-6">
						 <label class="control-label" style="color:#008000" for="value" >Select Image:</label>
						 <input type="file" id="notifile1" name="notificationFile" accept="image/*,application/msword, application/vnd.ms-excel, application/vnd.ms-powerpoint,text/plain, application/pdf"  value="Upload Media" class="btn btn-default"  onchange="check1()" />
						  <p style="color:red" id="demo"></p> 
		               </div>
					</div>
					<div class="form-group">
						<div class="fileupload ">
							<input type="submit"  id="submitbtn" style="background-color:#d3efc6" value="Send Notice" class="btn " onmouseover="check()">
						</div> 
					</div>
				</form:form>
			</div>
		 </div>
   </div>
   <script>
	 $(function () {
		$(".select2").select2();
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
