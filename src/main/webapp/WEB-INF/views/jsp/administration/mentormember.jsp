<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<c:url var="addMentorMember" value="/web/taskforce/mentor/Member" />
 <script>
	 $(function () {
		$(".select2").select2();
	 });
 </script>  
          <div class="modal-dialog">
             <div class="modal-content" id="groupMemberModal">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" style="color:#008000">Add New Member</h4>
				</div>
				<div class="modal-body">
				  	<form:form id="member" commandName="member" action="${addMentorMember}" method="POST" class="pure-form pure-form-aligned" role="form">					    
					    <div class="col-sm-12" >
					    <input type="hidden" name="sid" value="${sid}" required="required" class="form-control"/>				 	
					      <form:input type="hidden" path="id" value="${id}" required="required" class="form-control"/>
					 	   <label class="control-label" style="color:#008000"for="value">Select Member:</label>
								<select name="students" multiple="multiple" class="form-control select2" data-placeholder="Select Members..." style="width: 100%">
					               <c:forEach var="Sitem" items="${studentlist}">
									  <%-- <option value="${Sitem.studentId}" ${Sitem.studentId == selectedDept ? 'selected="selected"' : ''}>${Sitem.comClientName.firstName} ${Sitem.comClientName.lastName} </option>
								   </c:forEach>  --%>
								   <c:set var = "dep" value = "${Sitem.depName}"/>
      							<c:set var = "depname" value = "${fn:substring(dep, 0, 4)}" />
								  <option  value="${Sitem.id}" ${Sitem.id == selectedDept ? 'selected="selected"' : ''}>${Sitem.firstName}  ${Sitem.middleName} ${Sitem.lastName}  (${Sitem.year} - ${depname})</option>							   
							   </c:forEach>   
				                </select>
				             <br><br><br><br><br><hr>
			             </div> 
			          <div class="form-group model-footer">
			          <br>
					  <div class="fileupload ">
					      <input type="submit" style="background-color:#d3efc6" value="Add Member" class="btn ">
				    	  </div> 
					</div>		
				</form:form>
			 </div>
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