<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<c:url var="addAttendance" value="/web/taskforce/student/insert/attendance" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
	       <span class="pull-left" ><font size="5">Take Attendance</font></span>
	    </div>
	    <div class="box" style="margin-left:10px ; margin-right:10px">
	        <div class="box-body">
	           <form action="${addAttendance}" method="post" name="comAttendance" >
	             <div class="row" id="allInfo">
	              <div class="col-md-3">
		                <div class="form-group" >
		                  <label>Date</label>
		                	<input type="text" name="class_date" id="datepicker" autocomplete="off" class="form-control" required="required"></div>
		             </div>
		              <div class="col-md-3">
		                <div class="form-group" >
		                  <label>Subject Name</label>
		                  <select id="SubjectId"  name="subjectId" class="form-control" required="required">
		                    <option class="yearoption" value="" >Select Subject .. </option>
		                  <c:forEach var="subjectlist" items="${subjectlist}">
							  	<option class="yearoption" value="${subjectlist.academicSubject.sub_id}" >${subjectlist.academicSubject.subject_name} </option>
						</c:forEach> 
		                  </select>
						</div>
		             </div>
		             <div class="col-md-2">
		                <div class="form-group" >
		                  <label>Division</label>
		                  <select id="division"  name="division" class="form-control" required="required">
		                   
		                  </select>
						</div>
		             </div>
		               
		         </div>
		         
		          <div class="row" id="allInfo">
	              <div class="col-md-1">
		                <div class="form-group" >
		                <label>Theory  </label> <br> 
					&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="attendanceid" id="theoryID" style="width: 20px;height: 20px;margin-left: 2px" onclick="radioButton(this.value)" value="1" required="required">
		             </div>
		             </div>
		               <div class="col-md-2">
		                <div class="form-group" >
		                  <label>Practical </label><br>
					&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="attendanceid" id="practicalId" style="width: 20px;height: 20px;margin-left: 2px" onclick="radioButton(this.value)" value="2" required="required">
		             </div>
		             </div>
		             
		              <div class="col-md-2" style="display: none" id="batchdiv">
		                <div class="form-group" >
		                  <label>Practical Batch</label>
		                  <select id="batchList"  name="batch" class="form-control" >
		                   
		                  </select>
						</div>
		             </div>
		             
		             <div class="col-md-4">
		                <div class="form-group" >
		                  <label>Periods Time</label>
		                  <select id="startLecture" name="lectureTheoryTime.the_no" class="form-control" required="required">
				    	
		                  </select>
						</div> 
						
						
		             </div>
		             
		             <div class="col-sm-2">
                             <input type="button" value="Load" onclick="loadstud()" class="btn" style=" background-color:#008000; color:#fff ;font-weight: bold;margin-left: 20px;margin-top: 10px;height: 40px;"></span>
                   		 </div>
                     
		         </div>
		         <hr class="yello_hr"/>
		       <table id="example" class="table table-bordered table-striped"  >
				   <thead >
				     <tr>
				        <th style="width: 50px;">Roll No.</th>
				        <th>Name of Student</th>
				       
				        <th>Present &nbsp; <input type="checkbox" id="checkAll"></th>
				       
				     </tr>
				   </thead>
				   <tbody id="students" >
				   </tbody> 
			    </table>
			     <div align="center" ><input type="submit" class="btn btn-success" value="submit" ></div>
		       </form>
		      </div>
		  </div>
	   </section> 
     </div>
  </div>
 

<script type="text/javascript">

$( function() {
    $( "#datepicker" ).datepicker({  maxDate: 0,minDate: -3, });
  } );
  
  $(function () {
	var i = 0;
	//$('input[type="checkbox"]').prop('checked',true).change();
    $("#checkAll").click(function () {
        $(".check").prop('checked', $(this).prop('checked'));
     });  
    var dep =null;
    var div =null;
    var divArray = [];
    var data1 = null;
   
   $("#SubjectId").change(function(){
	   $('#students').empty();
	   $('#lecture').prop('selectedIndex',0);
	   var diviList="";
	   $.ajax({
		    type: "POST",
		    url: "/"+location.pathname.split("/")[1]+"/web/taskforce/service/division",
		    data:'subId='+ $("#SubjectId").val() + '&clientID='+ ${sessionScope.user.comClientName.id} ,
		    dataType: 'json',
		    success: function(data){ 
		    	 if(data.length > 0){
		    		 for ( var i = 0, len = data.length; i < len; ++i)  {
		          var info = data[i];
		          diviList+="<option value=\"" + info + "\">" + info + "</option>"
		 		} 
		    		 $("#division").html(diviList);
		       } 
		     },
			 error:function(data){
			    alert("student data error");
			}
		  });
	   $('#batchList').empty();
	   $("#batchdiv").hide();
       $("#theoryID").prop("checked", false);
       $("#practicalId").prop("checked", false);

   }); 
   $("#division").change(function(){
	   $('#students').empty();
	   $('#lecture').prop('selectedIndex',0);
   }); 
   
   
 $("#batchList").change(function(){
	 
	 
	   $('#students').empty();


    }); 
   });
  
  
  function radioButton(val) {
	var	subval=$("#SubjectId").val();
	if(val==2){
		
		if(subval=="")
		{
			alert("first select Subject");
	        $("#practicalId").prop("checked", false);
		}else{
			 var diviList="";
			   $.ajax({
				    type: "POST",
				    url: "/"+location.pathname.split("/")[1]+"/web/taskforce/service/attendance/batchlist",
				    data:'subId='+ $("#SubjectId").val() + '&division='+$("#division").val() ,
				    dataType: 'json',
				    success: function(data){ 
				    	 if(data.length > 0){
				    		 for ( var i = 0, len = data.length; i < len; ++i)  {
				          var info = data[i];
				          diviList+="<option value=\"" + info + "\">" + info + "</option>"
				 		} 
				    		 $("#batchList").html(diviList);
				    		 
				 			$("#batchdiv").show();
							 $('#students').empty();
								$('#startLecture').prop('selectedIndex',0);

				       } else{
				    	   
				    	   alert("No Batch Found Please Add Batch");
					        $("#practicalId").prop("checked", false);
							 $('#students').empty();

				       }
				     },
					 error:function(data){
					    alert("student data error");
					}
				  });
			   $("#startLecture").empty();
			   var periodsList="";
			   $.ajax({
				    type: "POST",
				    url: "/"+location.pathname.split("/")[1]+"/web/taskforce/service/attendance/practical/Periods/time",
				    dataType: 'json',
				    success: function(data){ 
				    	 if(data.length > 0){
				    		 periodsList+="<option value=\"\">select Period Time</option>";
				    		 for ( var i = 0, len = data.length; i < len; ++i)  {
				          var info = data[i];
							          periodsList+="<option value=\"" + info.pre_no + "\">" +info.start_time +" - "+ info.end_time + "</option>";
				    		 } 
				    		
						$("#startLecture").html(periodsList);

				       } 
				     },
					 error:function(data){
					    alert("student data error");
					}
				  });
			   
			  
		}
		
		
		
	}else{
		$("#batchdiv").hide();
		 $('#batchList').empty();
		 $('#students').empty();
//		$('#startLecture').prop('selectedIndex',0);
		if(subval=="")
		{
			alert("first select Subject");
	        $("#theoryID").prop("checked", false);
		}
		   var periodsList="";
		   $("#startLecture").empty();
		   $.ajax({
			    type: "POST",
			    url: "/"+location.pathname.split("/")[1]+"/web/taskforce/service/attendance/theroy/Periods/time",
			    dataType: 'json',
			    success: function(data){ 
			    	 if(data.length > 0){
			    		 periodsList+="<option value=\"\">select Period Time</option>";
			    		 for ( var i = 0, len = data.length; i < len; ++i)  {
			          var info = data[i];
						          periodsList+="<option value=\"" + info.the_no + "\">" +info.start_time +" - "+ info.end_time + "</option>";
			    		 } 
			    		
					$("#startLecture").html(periodsList);

			       } 
			     },
				 error:function(data){
				    alert("student data error");
				}
			  });
	}
	
	
}
  
  
  function loadstud() {
	  if($("#datepicker").val()=="")
	  {
			 alert("first select Date");
	  }else if ($("#SubjectId").val()=="") {
		  alert("select Subject");
	}else if ($('input[name=attendanceid]:checked').val()==null) {
		  alert("select Theory or Practical Batch");
	}
	  else if ($("#startLecture").val()=="") {
		  alert("select Periods Time");
	}else {
		var che=$('input[name=attendanceid]:checked').val();
		if(che==null)
		{
			alert("select Theory or Practical Batch");		
		}else{
		
			 var dateval=$("#datepicker").val();
			 if(dateval=="")
				 {
				 alert("first select Date");
				 $('#students').empty();
				 }else{
						$('#students').empty();
						  $.ajax({
						    type: "POST",
						    url: "/"+location.pathname.split("/")[1]+"/web/taskforce/student/set/attendance/getstudentlist",
						    data:'SubjectId='+ $("#SubjectId").val() + '&division='+ $("#division").val()+ '&attendanceid='+che + '&batchID='+ $("#batchList").val(),
						    dataType: 'json',
						    success: function(data){
						    	
						    	 if(data.length > 0){
						    		 var srno=1;
						    		 for ( var i = 0, len = data.length; i < len; ++i)  {
						          var info = data[i];
						           $('#students').append("<tr>");
						      	   $('#students').append("<td align='center'>"+ info.rollNo +"</td>");
						 		   $('#students').append("<td>" + info.firstName +" "+ info.middleName + " " + info.lastName+ "</td>");
						 		   $('#students').append("<td> <input type='checkbox' value='"+info.id+"'  name='studId' onclick='deselect()' class='check'> </td>");
						 		 
						 		   $('#students').append("</tr><br><br>");
						 		   
						 		   srno++;
						 		} 
						       } 
						     },
							 error:function(data){
							    alert("student data error");
							}
						  }); 
					 
				 }
				
		}
	}
	  
}
  
  
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