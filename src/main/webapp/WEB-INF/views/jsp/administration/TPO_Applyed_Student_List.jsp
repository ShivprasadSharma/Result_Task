<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<c:url var="addAttendance" value="/web/taskforce/student/insert/attendance" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
<c:url var="addslectedstud" value="/web/taskforce/service/rounds/addstud" />
</head>
<style>
 .yello_hr {
   height: 2px;
   color: #ffe63b;
   background-color: #ffe63b;	
  }	
  body{
   background: #7d7e7d;
  }
  .container{
    padding-top:10px;
    color:#fff;
   }	
</style>
<body class="hold-transition skin-blue fixed sidebar-mini">
 <div class="wrapper" >
  <div class="content-wrapper">
	  <section class="content">  
	    <div class="box-body" style="margin-top: 2%">
	       <span class="pull-left" ><font size="5">Add Selected Students For Next Raund</font></span>
	    </div>
	    <div class="box" style="margin-left:10px ; margin-right:10px">
	     <form action="${addslectedstud}" method="POST" modelAttribute="recruitment" enctype="multipart/form-data">
	     <input type="hidden" name="roundId" value="${roundID}">
	     <input type="hidden" name="reInfoId" value="${reInfoId}">
	        <div class="box-body">
	        <c:set var="n" value="0"/>
	        <c:set var="list" value="${studlist}" />
	  
	       <table id="example" class="table table-bordered table-striped" style="font-size: small;">
				   <thead >
				     <tr class="table-success">
				        <th style="width: 10%;">Sr No.</th>
				        <th style="width: 50%;">Name of Student</th>
				        <th><button class="btn btn-success" type="button" id='checkAll'>Check All</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-success" type="button" id='checkAll1'>Uncheck All</button></th>
				     </tr>
				     </thead>
				     <tbody >
					     <c:forEach var="x" items="${list}" >
						     <tr>
							     <td>${n + 1}</td>
							          <td> ${x.comClientName.firstName}&nbsp; ${x.comClientName.middleName}&nbsp; ${x.comClientName.lastName}</td>
			
							     <c:choose>
  <c:when test="${roundID==0 && x.finalfilter==true}">
   												 <td ><input type='checkbox'  name='studId'  value='${x.comClientName.id}' onclick='deselect()' class='check' checked> 
							     </td>
  </c:when>
  <c:when test="${roundID==1 && x.round1==true}">
								 <td ><input type='checkbox'  name='studId'  value='${x.comClientName.id}' onclick='deselect()' class='check' checked> 
							     </td>
  </c:when>
   <c:when test="${roundID==2 && x.round2==true}">
								 <td ><input type='checkbox'  name='studId'  value='${x.comClientName.id}' onclick='deselect()' class='check' checked> 
							     </td>
  </c:when>
  <c:when test="${roundID==3 && x.round3==true}">
								 <td ><input type='checkbox'  name='studId'  value='${x.comClientName.id}' onclick='deselect()' class='check' checked> 
							     </td>
  </c:when>
  <c:when test="${roundID==4 && x.round4==true}">
								 <td ><input type='checkbox'  name='studId'  value='${x.comClientName.id}' onclick='deselect()' class='check' checked> 
							     </td>
  </c:when>
  <c:when test="${roundID==5 && x.round5==true}">
								 <td ><input type='checkbox'  name='studId'  value='${x.comClientName.id}' onclick='deselect()' class='check' checked> 
							     </td>
  </c:when>
  <c:when test="${roundID==6 && x.round6==true}">
								 <td ><input type='checkbox'  name='studId'  value='${x.comClientName.id}' onclick='deselect()' class='check' checked> 
							     </td>
  </c:when>
  <c:otherwise>
								 <td ><input type='checkbox'  name='studId'  value='${x.comClientName.id}' onclick='deselect()' class='check'> 
							     </td>
  </c:otherwise>
</c:choose>
							     
							    
						     </tr>
					     <c:set var='n' value="${n + 1}"/>
					     </c:forEach>
				  
				  
				   </tbody> 
			    </table><br>
			    
			      <div align="center" ><input type="submit" class="btn btn-success" value="Selected For Next" ></div>
		      </div>
		      </form>
		  </div>
	   </section> 
     </div>
  </div>
 

<script type="text/javascript">

$('#checkAll').click(function() {
    $('input[name=studId]').prop('checked', true);
});

$('#checkAll1').click(function() {
    $('input[name=studId]').prop('checked', false);
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
 </body>
</html>