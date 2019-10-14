<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="<c:url value="/static/css/myFunctions.css"/> ">

<c:url var="getBalnkUser" value="/web/taskforce/user/blank" />
<c:url var="getUserDtls" value="/web/taskforce/user" />
<c:url var="getUser" value="/web/taskforce/user/" />
<c:url var="delUser" value="/web/taskforce/deluser/" />
 
 <body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
       User Details
      </h1>
      <ol class="breadcrumb">
        <li><input type="submit" value="Create New User" onclick="addUser()"class="btn" style="background-color:#ffffff"></li>
      </ol>
    </section>
  <!-- Main content -->
   <section class="content" style="padding-top:0px">
        <div class="box box-default" >
        	<!-- /.box-header -->
        	<div class="box-body" >
          <div class="tab-content">
		<div id="home" class="tab-pane fade in active">
		 
			<table
				class="table table-striped table-bordered table-hover table-condensed">
				<thead>
					<tr>
						<th>Sr #</th>
						<th>Name</th>
						<th>User Name</th>
						<th>Email</th>
						<th>Department</th>
						<th>Role</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${userList}">
						<tr>
							<td><a href="#" onClick="goToMasterDetails(${user.id})">${user.id}</a></td>
							<td>${user.firstName} </td>
							<td>${user.comUserDetails.userName}</td>
							<td>${user.emailId}</td>
							<td>${user.comUserDetails.dep}</td>
							<td>${user.comUserDetails.userRole}</td>
							<td><nobr>
									<button id="new-responsive-btn"
										onclick="editUser(${user.id});"
										class="btn btn-primary new-responsive-btn">
										<i class="fa fa-pencil"></i> Edit
									</button>

									<button id="new-responsive-btn1"
										onclick="deleteUser(${user.id});"
										class="btn btn-warning new-responsive-btn">Delete</button>
								</nobr></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		
		</div>
         	</div>
        </div>
        </div>
      </section>
     <!--  <div id="userDetails" class="tab-pane fade">
			
		</div> -->
	  </div>
    <!-- /main content -->
	  </div>

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
	<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-127607784-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-127607784-1');
</script>
</body>

</html>