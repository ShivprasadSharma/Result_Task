<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="removeMember" value="/web/taskforce/group/removeGroupMember" />
    <div class="modal-dialog">
       <div class="modal-content" id="removeMemberModel">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" style="color:#008000">Remove Group Member</h4>
				</div>
				<div class="modal-body">
					<form:form id="groupMember" modelAttribute="groupMember" commandName="groupMember" action="${removeMember}" method="post" class="pure-form pure-form-aligned" role="form">
					  <div class="col-sm-12" >
					  <c:forEach var="group" items="${members}">
					 	   <form:hidden path="groupId" value="${group.groupId}" required="required" class="form-control"/>
						 	<label class="control-label" style="color:#008000"for="value">Select Member:</label>
							<select name="member" multiple="multiple" class="form-control select2" data-placeholder="Select Members..." style="width: 100%">
					            <c:forEach var="Sitem" items="${group.groupMembers}">
								   <option value="${Sitem.membserId}" ${Sitem.membserId == selectedDept ? 'selected="selected"' : ''}>${Sitem.firstName} ${Sitem.lastName}</option>
							    </c:forEach> 
				             </select>  <br><br><br><br><br><hr>
				       </c:forEach>
			          </div>
			          <div class="form-group model-footer">
			          <br>
						  <div class="fileupload ">
							 <input type="submit" style="background-color:#d3efc6" value="Remove Member" class="btn ">
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
