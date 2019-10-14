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

<html>
<style>
.loader-block {
  position: relative;
  margin:50px;
  width: 120px;
  height: 120px;
}
.loader {
   width: 100%;
   height: 100%;
   border-radius: 50%;
   border-top: 8px solid #008000;
   border-bottom: 8px solid #008000;
   border-right:8px solid #008000;
   border-left:8px solid white;   
   animation: spin 4s linear infinite;   
   box-sizing: border-box;
     	background: #d3efc7;
   
}

.stop{
  position: absolute;
  display: inline-block;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-size:25px;
  font-style:
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
</style>

<body class="hold-transition skin-blue fixed sidebar-mini" onload="startTime()">

	<div class="wrapper">
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
		<section class="content-header">
				<div class="box-body">
					<span><font size="6"> Student Daily Punch</font></span>
				</div>
			</section>
			<!-- Main content -->
			<section class="content">
				<!-- /.col -->
				<div class="col-md-12">
					<div class="box box-default">
						<div class="box-body">
							<!-- enctype="multipart/form-data" -->


							<div class="container">
								<div class="row">
									<div class="col-sm-3"></div>
									<div class="col-sm-4" align="center">

									<input type="hidden" id="Cid" value="${attendancePunch.studId}">
											<c:if test="${attendancePunch.checkIn eq true && attendancePunch.checkOut eq true}">
														
														<h3  align="center" id="">Punching is done for the today.</h3>
														</c:if>
									<h3  align="center" id="labPunch"></h3>
									
										<div class="loader-block">
  							<div class="loader" ></div>
  							        <c:set var="attendancePunch" value="${attendancePunch}" />
  							
  							 <c:choose>
						                          <c:when test="${attendancePunch.checkIn eq false && attendancePunch.checkOut eq false}">
		                                               <div class="stop" id="checkIn" onclick="punchfun('1')"style=" color:green">Punch<p style="margin-left:3px; color:green"> <b>In</b></p></div>
		                                               <div id="checkout" class="stop" onclick="punchfun('2')" style="display: none; color:red">Punch<p style="margin-left: 2px;color:red"><b>Out</b></p></div>
		                                               <div class="stop" id="donePunch"  style="display: none; color:green ">Done </div> 
		                                               
		                                              </c:when>
						                       		<c:when test="${attendancePunch.checkIn eq true && attendancePunch.checkOut eq false}">
		                                        <div class="stop" id="checkIn" onclick="punchfun('1')" style="display: none;color:green">Punch <p style="margin-left:3px;color:green"><b> In</b></p></div>
		                                               <div id="checkout" class="stop" onclick="punchfun('2')"style=" color:red" >Punch <br><p style="margin-left: 2px;color:red"><b>Out</b></p></div>
		                                               <div class="stop" id="donePunch" style="display: none ;color:green">Done </div> 
		                                                      </c:when>
						                       		<c:when test="${attendancePunch.checkIn eq true && attendancePunch.checkOut eq true}">
		                                              <div class="stop" id="checkIn" onclick="punchfun('1')" style="display: none;color:green">Punch<p style="margin-left: 3px;color:green"> <b>In</b></p></div>
		                                               <div id="checkout" class="stop" onclick="punchfun('2')" style="display: none;color:red">Punch <p style="margin-left:2px; color:red"><b>Out</b></p></div>
		                                               <div class="stop" id="donePunch" style=" color:green" >Done </div> 
		                                               </c:when>
						                       	
				                       </c:choose>															
  										<h1 id="time" style=" color:#708090"> </h1>
  									
									</div>

									</div>

									<div class="col-sm-5"></div>
								</div>

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
	
	function punchfun(punchId) {
		 $.ajax({
			    type: "POST",
			    url: "/"+location.pathname.split("/")[1]+"/web/punch/add",
			    data:'punchId='+ punchId+ '&studId='+$("#Cid").val() ,
			    dataType: 'json',
			    success: function(data){ 
			    	if(punchId==1)
		    		{
		    			if(data.checkIn==true && data.checkOut==false)
		    			{
		    				alert("Punch-IN successfully Done");
		    				$("#checkIn").hide();
		    				$("#checkout").show();
		    			}
		    		}else {
		    			if(data.checkIn==true && data.checkOut==true)
		    			{
		    				alert("Punch-OUT successfully Done");

			    			$("#checkout").hide();
			    			$("#donePunch").show();
			    			$("#labPunch").text("Punching is done for the today.");

		    			}
		    		}
			    	
			     },
				 error:function(data){
				    alert("student data error");
				}
			  });
	}
	</script>
	<script>
function startTime() {
  var today = new Date();
  var h = today.getHours();
  var m = today.getMinutes();
  var s = today.getSeconds();
  m = checkTime(m);
  s = checkTime(s);
  document.getElementById('time').innerHTML =
  h + ":" + m + ":" + s;
  var t = setTimeout(startTime, 500);
}
function checkTime(i) {
  if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
  return i;
}
</script>
</body>
</html>