<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url var="getBalnkUser" value="/web/taskforce/user/blank" />
<!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar" >
    <!-- sidebar: style can be found in sidebar.less #f9f9f9 -->
    
    <section class="sidebar">
    <!-- Sidebar user panel -->
      <div class="user-panel">
      <!-- /.search form -->
       <ul class="sidebar-menu">
       
	    <c:forEach var="chatList1" items="${chatList}">
	    ${chatList1.comClientName.id}
	       <li class="treeview">
	          <a href='#chat_${chatList1.comClientName.id}' onclick="getMsges(${chatList1.studentId},${chatList1.comClientName.comUserDetails.id}) ">
	            <i class="fa">
	               <c:choose>
		                <c:when test="${chatList1.profileImage != null}">
						       <img src="data:image/jpg;base64,${chatList1.getByteArrayString()}"  style="width:40px; height:40px"  class="img-circle">
		                </c:when>
						<c:otherwise>
						   <img src="<c:url value="/static/img/author.png"/>" style="width:40px; height:40px" class="img-circle">
		               </c:otherwise>
	               </c:choose>
	            </i>
	              <span style="color:#000000;margin-left: 20px;"><font size='2'>${chatList1.comClientName.firstName}  ${chatList1.comClientName.lastName}</font></span>
	             <span class="pull-right-container"></span>
	          </a>
	        </li>
	        
	      </c:forEach> 
      </ul>
      </div>
    </section>
    <!-- /.sidebar -->
  </aside>
  
  <div class="modal fade" id="userFormModal" role="dialog">
		<!-- %@include file="masterform.jsp"%-->
	</div>
	<div class="modal fade" id="userFormModalLoading" role="dialog" >
		<div class="modal-dialog">
			<div class="modal-content" id="userModalLoading">
				<div class="modal-body">
					<img height="50" width="50" class="img-responsive"
						src='<c:url value="/static/dist/img/loading.gif"/>' />
				</div>
			</div>
			
		</div>
		<!-- %@include file="masterform.jsp"%-->
	</div>
<script>
function addUser()
{
	$.get('${getBalnkUser}', function(result)
	{
		$("#userFormModal").html(result);
    });

	$("#userFormModal").modal('show');
}

</script>