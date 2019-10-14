<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url var="updateGroup" value="/web/taskforce/group/update" />
   <div class="modal-dialog">
       <div class="modal-content" id="editGroupModel">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" style="color:#008000">Update Group</h4>
				</div>
				<div class="modal-body">
					<form:form id="groupForm" modelAttribute="groupForm" commandName="groupForm" action="${updateGroup}" method="POST" class="pure-form pure-form-aligned" role="form">
					  <c:forEach var="groupinfo" items="${groupinfo}">
					   <div class="col-sm-12" >
					     <div class="col-sm-6">
					         <form:hidden path="groupId" value="${groupinfo.groupId}"/>
							 <label class="control-label" style="color:#008000"for="value">Group Name:</label>
							 <form:input path="groupName" required="required" value="${groupinfo.groupName }"  class="form-control" placeholder="Group Name" /><br>
						   </div>
					    </div>
					    <form:hidden path="department" value="${groupinfo.department }"/>
					  <%--   <form:hidden path="createdBy" value="${groupinfo.createdBy }"/>
					   --%>  <br>
					   <c:if test="${not empty groupinfo.groupIncharge_1}">
						<div class="col-sm-12">
						    <div class="col-sm-6">
							  <c:set var="staffLst" value="${stafflist}"/>								  
				                <label class="control-label" style="color:#008000" for="value" >Select Incharge / Member:</label>
								 <form:select path="groupIncharge_1" class="form-control select2" data-placeholder="Select Incharge..." style="width: 100%">					                 
					                  <c:forEach var="Sitem" items="${staffLst}">	
					                   	<c:if test="${ngroupinfo.groupIncharge_1 ne Sitem.id}">				                
									        <option value="${Sitem.id}" ${Sitem.id == groupinfo.groupIncharge_1 ? 'selected="selected"' : ''}>  ${groupinfo.groupIncharge_1} ${Sitem.firstName} ${Sitem.lastName}</option>
									    </c:if>
									  </c:forEach> 
				                </form:select>
						       <br>
							</div><br><br><br><br>
						</div>
						</c:if>
					</c:forEach>
					<div class="form-group">
						<div class="fileupload ">
							<input type="submit" style="background-color:#d3efc6" value="Update" class="btn ">
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
