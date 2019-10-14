<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="removeMember" value="/web/taskforce/service/mentor/d/student/remove" />
    <div class="modal-dialog">
       <div class="modal-content" id="removeMemberModel">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" style="color:#008000">Remove Student</h4>
				</div>
				<div class="modal-body">
				<form:form id="mentor" modelAttribute="mentor" commandName="mentor" action="${removeMember}" method="post" class="pure-form pure-form-aligned" role="form">
					  <div class="col-sm-12" >
					     <label class="control-label" style="color:#008000"for="value">Select Member:</label>
							<select name="member" multiple="multiple" class="form-control select2" data-placeholder="Select Student..." style="width: 100%">
					             <c:forEach var="Sitem" items="${members}">
								   <option value="${Sitem.mid}" ${Sitem.student.studentId == selectedDept ? 'selected="selected"' : ''}>${Sitem.student.comClientName.firstName}  ${Sitem.student.comClientName.lastName}</option>
							    </c:forEach>  
				             </select>  <br><br><br><br><br><hr>
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