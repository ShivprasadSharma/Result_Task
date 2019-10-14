<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="attendance" value="/web/taskforce/student/attendance/daily/report/download" />


<html>
<style>
.success {
    background-color: #ddffdd;
    border-left: 6px solid #4CAF50;
    width: 400px;
    margin-bottom: 15px;
    padding: 4px 12px;
}


</style>

<body class="hold-transition skin-blue fixed sidebar-mini" >

<div class="wrapper">
 <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
        <section  class="content-header">
       <div class="box-body" style="margin-top: 2%"><span><font size="5">Daily Attendance and Cumulative Attendance Report Download</font></span></div>
	</section>
   <!-- Main content -->
    <section class="content">
      <!-- /.col -->
        <div class="col-md-12" >
          <div class="box box-default">          
            <div class="box-body">
            <!-- enctype="multipart/form-data" -->
            			<div class="container" >
					<form action="${attendance}" method="post" id="comAttendance">
					
					<div class="row">
					 <div class='col-sm-6 pt-1x' style="text-align: right;">
					
					<input type="radio" name="Reportid" style="width: 20px;height: 20px"  value="1" checked="checked" onclick="radioButton(this.value)"> 
					<label style="font-size: 20px">Daily Attendance</label> 
					 
					 </div>
					 <div  class='col-sm-6'>
					<input type="radio" name="Reportid" style="width: 20px;height: 20px" onclick="radioButton(this.value)" value="2">
					  <label style="font-size: 20px">Cumulative Attendance </label> 
					 </div>
					</div>
					
					
					<div class="row">
					 <div class='col-sm-6 pt-1x' style="text-align: right;">
					
					<input type="radio" name="theId" style="width: 20px;height: 20px" onclick="TheORpractical(this.value)" value="1" checked="checked" /"> 
					<label style="font-size: 20px">Theory</label> 
					 
					 </div>
					 <div  class='col-sm-6'>
					<input type="radio" name="theId" style="width: 20px;height: 20px" onclick="TheORpractical(this.value)" value="2">
					  <label style="font-size: 20px">Practical</label> 
					 </div>
					</div>
    <div class="row" style="margin-top: 30px;">
        <div class='col-sm-1'>
    <b>Department</b> 
        </div> 
      
        <div class='col-sm-4'>
       <select id="DeptId"  name="Dep_Id" class="form-control" required="required">
        <c:forEach var="department" items="${department}">
							  	<option class="yearoption" value="${department.dep_id}" >${department.dep_name} </option>
						</c:forEach> 
    </select> 
        </div>
        <div class='col-sm-1'>
    <b>Year</b> 
        </div> 
      
        <div class='col-sm-4'>
       <select id="YearId"  name="year" class="form-control" onclick="semister(this.value)" required="required">
       <option value="">Select Year</option>
       <option value="1">FE</option>
         <option value="2">SE</option>
           <option value="3">TE</option>
             <option value="4">BE</option>
    </select> 
        </div>
      
        
         </div>
    <br>
    <div class="row">
    
        
            <div class='col-sm-1'>
    <b>Semester</b> 
        </div> 
      
        <div class='col-sm-4'>
     <select  name="sem" class="form-control" id="semId" required="required" >
		            <option value="">Select Semester</option>        
 
		                </select>
        </div>
    
    
           <div class='col-sm-1'>
    <b>Division</b> 
        </div> 
      
        <div class='col-sm-4'>
     <select  name="division" class="form-control" id="diviID" onclick="getBatch(this.value)">
		                </select>
        </div>
        
         </div>
    
          <br>
    <div class="row">
     
    
     <div class='col-sm-1'>
    <b id="fromDate">Date</b> 
        </div> 
        <div class='col-sm-4'>
      
            <div class="form-group">
                <div class='input-group date' id='datetimepicker1'>
                    <input type='text' name="class_date" class="form-control" required="required"/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </div>
        </div> 
      <div class='col-sm-1' style="display: none;" id="todateText">
    <b>To Date</b> 
        </div> 
        <div class='col-sm-4' style="display: none;" id="todateId">
      
            <div class="form-group">
                <div class='input-group date' id='datetimepicker2'>
                    <input type='text' name="todate" class="form-control" />
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </div>
        </div> 
      
      </div>
    
   
    
    	 <div class="success" id="notification" style="display: none">
  <p><strong>Response !</strong>Attendance is not present ...</p>
</div>  
    <div class="row">
      <div class='col-sm-1' >
    <b  id="batchText" style="display: none;">Batch</b> 
        </div> 
        <div class='col-sm-4'  >
      
               <select  name="batch" class="form-control"  id="BatchList" style="display: none;">          
    			
		                </select>
        </div> 
        <div class='col-sm-2'>
        </div>
           <div class='col-sm-3'><br/>
            	<input type="submit"  value="Download Excel File" class="btn btn-success" />
        </div>
       
    </div>
    
     
         
    </form>
    
</div>
	
              </div>
           </div>
         </div>
        </section>
       </div>
     </div> 
     <script type="text/javascript">
            $(function () {
              
                $('#datetimepicker1').datetimepicker();
                $('#datetimepicker2').datetimepicker();
                
            });
            
            var sts="${status}";
            if(sts=="false")
            {
            	
            	$("#notification").show();
            	 showNotification();
                 setTimeout(hideNotification, 5000);
           		     
            }
            
 
            function showNotification() {
                $(".success")
                  .fadeIn()
                  .css({ right: 0, position: "absolute" })
                  .animate({ left: 0 }, 1800, function() {
                    // $('#selector').delay(5000).fadeOut('slow');
                  });
              }

              function hideNotification() {
                $(".success").fadeOut("slow");
              } 
            
              function semister(id) {
            
              	var sem="";
              	if(id==1)
              	{
              		sem+= '<option value="">Select Semester</option>';
              		sem+= '<option value="1">I</option>';
              		sem+= '<option value="2">II</option>';
              		
              	}else if(id==2)
              	{
              		sem+= '<option value="">Select Semester</option>';
              		sem+= '<option value="3">III</option>';
              		sem+= '<option value="4">IV</option>>';
              	}else if(id==3)
              	{
              		sem+= '<option value="">Select Semester</option>';
              		sem+= '<option value="5">V</option>';
              		sem+= '<option value="6">VI</option>';
              	}
              	else if(id==4)
              	{
              		sem+= '<option value="">Select Semester</option>';
              		sem+= '<option value="7">VII</option>';
              		sem+= '<option value="8">VIII</option>';
              	}else{
              		sem+= '<option value="">Select Semester</option>';
              	}
              	
  				$("#semId").html(sem);
  				 var deptVal=$("#DeptId").val();
  				var diviList="";
  				if(id!=null){
  					 $.ajax({
					    type: "POST",
					    url: "/"+location.pathname.split("/")[1]+"/web/taskforce/service/report/division",
					    data:'deptId='+ deptVal + '&yearId='+id ,
					    dataType: 'json',
					    success: function(data){ 
					    	 if(data.length > 0){
					    		diviList+="<option value=\"\"> Select Division </option>";
					    		 for ( var i = 0, len = data.length; i < len; ++i)  {
					          var info = data[i];
					          diviList+="<option value=\"" + info + "\">" + info + "</option>"
					 		} 
					    		 $("#diviID").html(diviList);
					       } else{
					    		diviList+="<option value=\"\"> Select Division </option>";
					    		 $("#diviID").html(diviList);

					       }
					     },
						 error:function(data){
						    alert("student data error");
						}
					  }); 

					
  				}
  	
              }
            
              function radioButton(id) {
			
				if(id=="2")
				{
					$('#todateText').show();
					$('#todateId').show();
					$("#fromDate").text("From Date");
				}else{
					$('#todateText').hide();
					$('#todateId').hide();
					$("#fromDate").text("Date");
				}
			}
              
              function TheORpractical(id) {
				
            	  if(id=="2")
  				{
  					$('#batchText').show();
  					$('#BatchList').show();
  				}else{
  					$('#batchText').hide();
  					$('#BatchList').hide();
  				}
            	  $("#YearId").val($("#target option:first").val());
            	  
					
            		var sem= '<option value="">Select Semester</option>';
            		$("#semId").html(sem);
            		
            		var diviList="<option value=\"\"> Select Division </option>";
            		$("#diviID").html(diviList);
            		
            		var batch="<option value=\"\"> Select Batch </option>";
            		$("#BatchList").html(batch);

			}
             
              function getBatch(id) {
            	  var redioVal=$('input[name=theId]:checked').val();
            	 	if(id!="" && redioVal==2)
            	 	{
            	 	
            	 		var yearVal=$("#DeptId").val();
                	 	var deptVal=$("#YearId").val();
          				var diviList="";

                	 	 $.ajax({
     					    type: "POST",
     					    url: "/"+location.pathname.split("/")[1]+"/web/taskforce/service/attendance/report/batchlist",
     					    data:'deptId='+ deptVal + '&yearId='+yearVal+ '&divId='+id ,
     					    dataType: 'json',
     					    success: function(data){ 
     					    	alert(data);
     					    	 if(data.length > 0){
     					    		diviList+="<option value=\"\"> Select Batch </option>";
     					    		 for ( var i = 0, len = data.length; i < len; ++i)  {
     					          var info = data[i];
     					          diviList+="<option value=\"" + info + "\">" + info + "</option>"
     					 		} 
     					    		 $("#BatchList").html(diviList);
     					       } 
     					     },
     						 error:function(data){
     						    alert("student data error");
     						}
     					  }); 
                	 

            	 	}
			}
              
          
        </script>
	  <!-- Global site tag (gtag.js) - Google Analytics -->
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-127607784-1');
</script>
   </body>
 </html>