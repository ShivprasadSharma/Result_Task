
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<c:url var="AddBatch" value="/web/taskforce/student/batch/add" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">

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
   input[type=text],input[type=email], input[type=date],input[type=number],select, textarea {
      width: 100%;
      padding: 5px;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
      margin-top: 5px;
      margin-bottom: 14px;
      resize: vertical;
      background-color: #f2f2f2;
}
input[type=submit], input[type=reset] {
	  background-color: #4CAF50;
	  color: white;
	  padding: 8px 15px;
	  border: none;
	  border-radius: 4px;
	  cursor: pointer;
	  width:110px;
	  font-size: 12px;
}
tr:nth-child(even){background-color: #f2f2f2}
</style>
<body class="hold-transition skin-blue fixed sidebar-mini">
 <div class="wrapper" >
  <div class="content-wrapper">
	  <section class="content">  
	    <div class="box-body">
	       <span class="pull-left" ><font size="5">Add Practical Batch</font></span>
	    </div>
	    <div class="box" style="margin-left:10px ; margin-right:10px">
	        <div class="box-body">
	          <form action="${AddBatch}" method="post" modelAttribute="batchDetails">
	            
  					<hr>
  						<div class="row">
  						    <div class="col-sm-1"></div>
    						
    						<div class="col-sm-5"><b>*Department :</b></div>
    						<div class="col-sm-5"><b>*Class :</b></div>
  						</div>
  						
   						<div class="row">
	   						 <div class="col-sm-1"></div>
           					<div class="col-sm-5">
		       					<select name="depaId"  id="dep_id" required="required">
               							<option value="">select Department</option>
               							 <c:forEach var="department" items="${department}">
							  	<option class="yearoption" value="${department.dep_id}" >${department.dep_name} </option>
						</c:forEach> 
           						</select>
           					</div>
           					<div class="col-sm-5">
		       					<select name="classId" id="class_Id" required="required" onchange="getDivision()">
		       							 <option value="">Select Class</option>
               							 <option value="1">FE</option>
         								<option value="2" >SE</option>
           								<option value="3" >TE</option>
            							 <option value="4" >BE</option>
           						</select>
           					</div>
		       			</div>
		       			<div class="row">
  						    <div class="col-sm-1"></div>
    						<div class="col-sm-5"><b>*Division</b></div>
  						</div>
		       			<div class="row">
  						    <div class="col-sm-1"></div>
    						<div class="col-sm-5">
		       					<select name="divId" id="divlist" required="required">
               						
           						</select>
  						</div>
  						<div class="row">
  						    <div class="col-sm-2"></div>
    						<div class="col-sm-4">
                             <input type="button" value="Load" onclick="loadstud()" class="btn" style=" background-color:#008000; color:#fff ;font-weight: bold;"></span>
                   		 </div>
  						</div>
  						</div>
  						   <hr class="yello_hr"/>
		       <table id="example" class="table table-bordered table-striped">
				   <thead>
				     <tr>
				        <th style="width: 50px;">Roll No.</th>
				        <th>Full Name</th>
				    
				        <th>Batch </th>
				     </tr>
				   </thead>
				   <tbody id="studList" >
				   </tbody> 
			    </table>
		       				<div class="row">
  						    <div class="col-sm-5"></div>
    						
    						<div class="col-sm-2"><input type="submit" onclick="return Validate()" value="Submit"></div>
    						<div class="col-sm-2"><input type="reset" name="resetBtn" value="Clear" class="" ></div>
  						</div>
		       </form>
		      </div>
		  </div>
	   </section> 
     </div>
  </div>
 

<script type="text/javascript">

function getDivision() {
	 $('#studList').empty();

	var depIdval=$("#dep_id").val();
	if(depIdval==""){
		
		alert("please Selest department");
	}else{
		 $.ajax({
			    type: "POST",
			    url: "/"+location.pathname.split("/")[1]+"/web/taskforce/batch/division",
			    data:'depId='+ $("#dep_id").val() + '&classId='+ $("#class_Id").val() ,
			    dataType: 'json',
			    success: function(data){
			    	var datalist="";
			    	 if(data.length > 0){
			   
			    		 for ( var i = 0, len = data.length; i < len; ++i)  {
			          var info = data[i];
			          datalist+='<option value="'+info+'">'+info+'</option>';
			        
			 		} 
			    		 $('#divlist').html(datalist);
			       } 
			     },
				 error:function(data){
				    alert("student data error");
				}
			  }); 
	}
	
	
}



function loadstud(){

	
	 $.ajax({
		    type: "POST",
		    url: "/"+location.pathname.split("/")[1]+"/web/taskforce/batch/studentlist",
		    data:'depId='+ $("#dep_id").val() + '&classId='+ $("#class_Id").val()+ '&divId='+ $("#divlist").val() ,
		    dataType: 'json',
		    success: function(data){
		    	var batch="";
		    	 if(data.length > 0){
		   			 for ( var i = 0, len = data.length; i < len; ++i)  {
		   				 var info = data[i];
				          
				          batch+= "<tr>"+
				        	  "								     <td>"+info.rollNo+"</td>"+
				        	  "								     <td>"+ info.firstName +" "+ info.middleName+" "+info.lastName+"</td>"+
				        	  "								     <input type=\"hidden\" name=\"batchDetails["+i+"].studId\" value=\""+info.id+"\"/>"+
					        	 "								     <td>"+
				        	  "								     <input type=\"radio\" name=\"batchDetails["+i+"].batch\"  value=\""+$("#divlist").val()+"1\"  required=\"required\"/> "+$("#divlist").val()+"1. &nbsp;&nbsp  "+
				        	  "								     <input type=\"radio\" name=\"batchDetails["+i+"].batch\" value=\""+$("#divlist").val()+"2\" required\"/> "+$("#divlist").val()+"2. &nbsp;&nbsp  "+
				        	  "								     <input type=\"radio\" name=\"batchDetails["+i+"].batch\" value=\""+$("#divlist").val()+"3\" required\"/> "+$("#divlist").val()+"3. &nbsp;&nbsp "+
				        	  "								     <input type=\"radio\" name=\"batchDetails["+i+"].batch\" value=\""+$("#divlist").val()+"4\" required\"/> "+$("#divlist").val()+"4. &nbsp;&nbsp "+
				        	  "								     <input type=\"radio\" name=\"batchDetails["+i+"].batch\" value=\""+$("#divlist").val()+"5\" required\"/> "+$("#divlist").val()+"5. &nbsp;&nbsp "+

				        	  "								     </td>"+
				        	  "								     </tr>";
				        	  	

				        	  	

				 		}
				    		$("#studList").html(batch);
		       } 
		     },
			 error:function(data){
			    alert("student data error");
			}
		  }); 
	
}
$("#divlist").change(function(){
	 
	

	 $('#studList').empty();

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
