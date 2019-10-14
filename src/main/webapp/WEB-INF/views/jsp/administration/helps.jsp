<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.IOException"%>;
<%@page import="java.io.*"%>
<c:url var="download" value="/web/taskforce/serives/grievance/download" />

<html>
<style>
.success {
	background-color: #ddffdd;
	border-left: 6px solid #4CAF50;
	width: 400px;
	margin-bottom: 15px;
	padding: 4px 12px;
	
}

div.ex3 {
	background-color: white;
	width:100%;
	height: 75%;
	overflow: auto;
  
}

</style>

<body class="hold-transition skin-blue fixed sidebar-mini">

	<div class="wrapper">
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->

			<!-- Main content -->
			<section class="content">
				<!-- /.col -->
				<div class="col-md-12">
					<div class="box box-default">
						<div class="box-body">
							<!-- enctype="multipart/form-data" -->
							<div class="container">

								<div class="row">
									<div class="col-sm-4">

										<div class="dropdown">
											<button class="btn btn-primary dropdown-toggle" type="button"
												data-toggle="dropdown">
												Admin <span class="caret"></span>
											</button>
											<ul class="dropdown-menu">
												<li><a href="#"  ></a></li>
												<li><a href="#" onclick="getUrl('1','1')">Profile</a></li>
												<li><a href="#" onclick="getUrl('1','2')">Notice</a></li>
												<li><a href="#" onclick="getUrl('1','3')">Grievance</a></li>
												<li><a href="#" onclick="getUrl('1','4')">Polls</a></li>
												<li><a href="#" onclick="getUrl('1','5')">Stafflist</a></li>
												<li><a href="#" onclick="getUrl('1','6')">Studentlist</a></li>
												<li><a href="#" onclick="getUrl('1','7')">Student verification</a></li>
												<li><a href="#" onclick="getUrl('1','8')">Upload Yearly File</a></li>
												<li><a href="#" onclick="getUrl('1','9')">Student Count</a></li>
												<li><a href="#" onclick="getUrl('1','10')">Calendar</a></li>
											</ul>
										</div>
									</div>
									<div class="col-sm-4">

										<div class="dropdown">
											<button class="btn btn-primary dropdown-toggle" type="button"
												data-toggle="dropdown">
												HOD <span class="caret"></span>
											</button>
											<ul class="dropdown-menu">
												<li><a href="#" onclick="getUrl('2','1')">Profile</a></li>
												<li><a href="#" onclick="getUrl('2','2')">Notice</a></li>
												<li><a href="#" onclick="getUrl('2','3')">Activities</a></li>
												<li><a href="#" onclick="getUrl('2','4')">Grievance</a></li>
												<li><a href="#" onclick="getUrl('2','5')">Attendance</a></li>
												<li><a href="#" onclick="getUrl('2','6')">Group</a></li>
												<li><a href="#" onclick="getUrl('2','7')">GFM</a></li>
												<li><a href="#" onclick="getUrl('2','8')">Feedback</a></li>
												<li><a href="#" onclick="getUrl('2','9')">Student Count</a></li>
												<li><a href="#" onclick="getUrl('2','10')">Calendar</a></li>
											</ul>
										</div>
									</div>
									<div class="col-sm-4">

										<div class="dropdown">
											<button class="btn btn-primary dropdown-toggle" type="button"
												data-toggle="dropdown">
												Teacher <span class="caret"></span>
											</button>
											<ul class="dropdown-menu">
												<li><a href="#" onclick="getUrl('3','1')">Profile</a></li>
												<li><a href="#" onclick="getUrl('3','2')">Notice</a></li>
												<li><a href="#" onclick="getUrl('3','3')">Attendance</a></li>
												<li><a href="#" onclick="getUrl('3','4')">Group</a></li>
												<li><a href="#" onclick="getUrl('3','5')">GFM Profile</a></li>
												<li><a href="#" onclick="getUrl('3','6')">Student Count</a></li>
												<li><a href="#" onclick="getUrl('3','7')">Calendar</a></li>
												
											</ul>
										</div>
									</div>
								</div>
								

							</div>
							<div class="ex3" style="margin-top: 10px;" id="urlListId">
									
								</div>
						</div>
						</div>
					</div>
			</section>
		</div>
	</div>
	<script type="text/javascript">
		
	</script>
	<!-- Global site tag (gtag.js) - Google Analytics -->
	<script async
		src="https://www.googletagmanager.com/gtag/js?id=UA-127607784-1"></script>
	<script>
	
	function getUrl(id,menuid) {
	     //alert(id);
	     
		//alert(menuid);
		var myvar="";
		  $.ajax({
		         type: "POST",
		         url: "/"+location.pathname.split("/")[1]+"/web/help/geturl",
		         data:'id='+ id +'&menuid='+menuid,
		         dataType: 'json',
		         success: function(data)
		         { 
		        	 if(data.length > 0){
		        		// alert(data);
		 		      var myvar="";
		 		            
		 		   		var count="1";
		 		      for ( var i = 0, len = data.length; i < len; i++) 
		 		            {
		 		                var helpBean = data[i];
					
		 		               myvar+= '<p style="font-size: 20px">' +count+ ' : ' +helpBean.title+'</p>'+
		 		               '										<img'+
		 		               '											src="'+helpBean.url+'"'+
		 		               '											class="img-rounded"  width="90%"'+
		 		               '											height="93%" style="border-style:solid; border-width: 3px; margin-left: 5%">';
								
		 		              count++;
		 		            } 
		 		            
		 		            $("#urlListId").html(myvar);
		            }
		         },
			     error:function(data){
			    	    alert("error");
			     }
		    });
	}
	</script>
</body>
</html>