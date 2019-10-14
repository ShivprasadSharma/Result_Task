<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(document).ready(function () { $("#description").cleditor(); });
</script>
<c:set var="listValue" value="${infoList}"/>
<c:url var="saveMasterDtl" value="/web/taskforce/masterdtl/save" />

     <div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content" id="masterModal">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Add Master Details</h4>
				</div>
				<div class="modal-body">
					<form:form id="masterDetailForm" commandName="masterDetailForm" action="${saveMasterDtl}" method="post" class="pure-form pure-form-aligned" role="form">
						<div class="form-group">
							<label class="control-label col-sm-3" for="instituteInfotMaster.listId">Master:</label>
							<div class="col-sm-9">
								<c:choose>
								    <c:when test="${empty masterDetailForm.instituteInfotMaster.listId}">
								    	<form:select path="instituteInfotMaster.listId" items="${listService.listMap['infoList']}" />
								    </c:when>    
								    <c:otherwise>
								    	<form:input path="instituteInfotMaster.value" class="form-control" readonly="true" placeholder="Master Id" />								        
								    </c:otherwise>
								</c:choose>								
								<!-- form:hidden path="instituteInfotMaster.listId" /-->
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-sm-3" for="listDtlId">Master Id:</label>
							<div class="col-sm-9">
								<form:input path="listDtlId" class="form-control" readonly="true" placeholder="Master Id" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-sm-3" for="value">Value:</label>
							<div class="col-sm-9">
							<form:input path="value" class="form-control"  placeholder="Value" />
								
							</div>
							
						</div>
						<div class="form-group">
							<label class="control-label col-sm-3" for="description">Description:</label>
							<div class="col-sm-9">
								<form:textarea id="description" path="description" rows="10" cols="50" placeholder="Description"/>
							</div>
						</div>
						<div class="form-group">
							<div class="fileupload fileupload-new" data-provides="fileupload">
								<span class="btn btn-primary btn-file"><span
									class="fileupload-new">Change</span> 
									<input type="file" name="logo"/>
								</span>
							</div>

						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary">Submit</button>
							<button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
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