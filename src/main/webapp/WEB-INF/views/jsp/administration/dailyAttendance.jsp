<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="attendance" value="/web/taskforce/student/attendance/daily/data" />


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
        <section  class="content-header" style="margin-top: 2%">
       <div class="box-body"><span><font size="5">Daily Attendance</font></span></div>
	</section>
   <!-- Main content -->
    <section class="content">
      <!-- /.col -->
        <div class="col-md-12" >
          <div class="box box-default">          
            <div class="box-body">
            <!-- enctype="multipart/form-data" -->
            			<div class="container" id="datepicker" style="margin-top: 1%;">
					<form action="${attendance}" method="post" id="comAttendance">
					
					<div class="row ">
					 <div class='col-sm-6 pt-1x' style="text-align: right;">
					
					<input type="radio" name="Reportid" style="width: 20px;height: 20px"  value="1" checked="checked" onclick="radioButton(this.value)"> 
					<label style="font-size: 20px">Theory</label> 
					 
					 </div>
					 <div  class='col-sm-6'>
					<input type="radio" name="Reportid" style="width: 20px;height: 20px" onclick="radioButton(this.value)" value="2">
					  <label style="font-size: 20px">Practical</label> 
					 </div>
					</div>
    <div class="row mt-3" style="margin-top: 2%;">
    
     <div class='col-sm-1'>
    <b>Date</b> 
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
        <div class='col-sm-1'>
    <b>Subject</b> 
        </div> 
      
        <div class='col-sm-4'>
       <select id="SubjectId"  name="subjectId" class="form-control" required="required">
       <option value="">select  subject</option>
        <c:forEach var="subjectlist" items="${subjectlist}">
							  	<option class="yearoption" value="${subjectlist.academicSubject.sub_id}" >${subjectlist.academicSubject.subject_name} </option>
						</c:forEach> 
    </select> 
        </div>
        
        
         </div>
    
   
          <br>
    <div class="row">
     
      
        <div class='col-sm-1'>
    <b>Division</b> 
        </div> 
      
        <div class='col-sm-4'>
     <select id="division" name="division" class="form-control" required="required" >
		          <option value="">select  division</option>
    
		                </select>
        </div>
        
         <div class='col-sm-1' id="batchStr" hidden >
    <b>Batch</b> 
        </div> 
         <div class='col-sm-4' id="batchList" hidden>
     <select id="batchID" name="batch" class="form-control" >
		          
    
		                </select>
        </div>
    </div>
    	
    <div class="row">
     <div class='col-sm-3'>
        </div> 
     <div class='col-sm-3'>
        </div> 
        <div class='col-sm-2'>
        </div>
           <div class='col-sm-3'><br/> <input type="submit" value="Submit"
												class="btn btn-success"  />
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
                
            });
            
            
            
            function semister(id) {
            	
           
            	var sem="";
            	if(id==1)
            	{
            		sem+= '<option value="1">I</option>';
            		sem+= '<option value="2">II</option>';
            		
            	}
            	if(id==2)
            	{
            		sem+= '<option value="3">III</option>';
            		sem+= '<option value="4">IV</option>>';
            	}if(id==3)
            	{
            		sem+= '<option value="5">V</option>';
            		sem+= '<option value="6">VI</option>';
            	}
            	if(id==4)
            	{
            		sem+= '<option value="7" >VII</option>';
            		sem+= '<option value="8">VIII</option>';
            	}
            	
				$("#semId").html(sem);
            	
			}
            
            $("#SubjectId").change(function(){
         	   $('#division').empty();
         	  
         	   var subid=$("#SubjectId").val();
         	   var diviList="";
         	   if(subid!="")
         		{
         		  $.ajax({
           		    type: "POST",
           		    url: "/"+location.pathname.split("/")[1]+"/web/taskforce/service/division",
           		    data:'subId='+ subid + '&clientID='+ ${sessionScope.user.comClientName.id},
           		    dataType: 'json',
           		    success: function(data){ 
           		    	 if(data.length > 0){
           		    		 diviList+="<option value=\"\" >Select Division</option>"
           		    		 for ( var i = 0, len = data.length; i < len; ++i)  {
           		          var info = data[i];
           		          diviList+="<option value=\"" + info + "\" >" + info + "</option>"
           		 		} 
           		    		 $("#division").html(diviList);
           		       } 
           		     },
           			 error:function(data){
           			    alert("student data error");
           			}
           		  });
         	   }
         	  
         	   
            });
            
            $("#division").change(function(){
          	   $('#batchID').empty();
          	  
          	  
          	   var diviList="";
          	   if($('input[name=Reportid]:checked').val()==2 && $("#division").val()!=""){
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
 				    		 $("#batchID").html(diviList);
 				    		 
 				 		
 				       } else{
 				    	   
 				    	   alert("No Batch Found Please Add Batch");
 					       
 				       }
 				     },
 					 error:function(data){
 					    alert("student data error");
 					}
 				  });
           	  
          	   }
          	 
          	   
             });
            
            /* function radioButton(val) {
				if(val==1)
				{
					$("#batchStr").hide();
					$("#batchList").hide();	
					 $("#batchID").empty();
				}else{
					$("#batchStr").show();
					$("#batchList").show();
					
				}
				
			} */
            
            $('input[type=radio][name=Reportid]').change(function() {
                if (this.value == 1) {
                	$("#batchStr").hide();
					$("#batchList").hide();	
					 $("#batchID").empty();
					 $("#division").empty();

					  $("#SubjectId").prop('selectedIndex',0);

                }
                else if (this.value == 2) {
                	$("#batchStr").show();
                	$("#division").empty();
					$("#batchList").show();
					$("#SubjectId").prop('selectedIndex',0);

                }
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