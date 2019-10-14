<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.zertones.model.ComClientName"%>
<%@page import="com.zertones.model.sims.Staff"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<c:url var="createprofile" value="/web/taskforce/service/mentor/create" />

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
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 5px;
      box-sizing: border-box;
      margin-top: 10px;
      margin-bottom: 14px;
      resize: vertical;
      background-color: #f2f2f2;
}
.catagory{

    font-size:13px;

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
	       <span class="pull-left" ><font size="5">New Mentor Profile</font></span>
	    </div>
	     <div class="col-md-12 col-sm-12 " >
	     <div class="box">
		<div class="box-body" >

  
				  <form:form id="mentorProfile" modelAttribute="mentorProfile" commandName="mentorProfile" action="${createprofile}" method="post" class="pure-form pure-form-aligned" role="form">					  
					<div class="col-sm-12">
					      <div class="col-sm-6">
					       <c:set var="staffLst" value="${stafflist}"/>	
						    <label class="control-label" style="color:#008000" for="value" >Select Mentor:</label>
							 <form:select path="staff.comClientName.id" class="form-control select2" data-placeholder="Select Staff" style="width: 100%;" required="required">
				               <c:forEach var="Sitem" items="${staffLst}">
								 <option  value="${Sitem.comClientName.id}">${Sitem.comClientName.firstName}  ${Sitem.comClientName.lastName}</option>								   
							   </c:forEach> 
			                 </form:select>
						  </div>
					      <div class="col-sm-6">
					      <label class="control-label" style="color:#008000" >Select Year:</label>
						  <select  name="year" class="form-control  select2" id="year" >
						          <option value="">--select Year--</option>
						          <option value="1">FE</option>
								  <option value="2">SE</option>
								  <option value="3">TE</option>
								  <option value="4">BE</option>
						  </select> <br><br>
						  </div>
						 
					</div>		
					  
				<div class="col-sm-12">
						  <div class="col-sm-6">
				 <label class="control-label" style="color:#008000" for="value" >Select Division:</label>
                       <select  name="standard" class="form-control  select2" id="divId" >	
                      			    <option class="yearoption" value="">--select Division--</option>
     				 	    		<option class="yearoption" value=1> A</option>
     				 	    		<option class="yearoption" value=2> B</option>
     				 	    		<option class="yearoption" value=3> C</option>
     				 	    		<option class="yearoption" value=4> D</option>
     				 	    		<option class="yearoption" value=5> E</option>
     				 	    		<option class="yearoption" value=6> F</option>
     				 	    		<option class="yearoption" value=7> G</option>
     				 	    		</select>
     				 	    	</div>
						  <div class="col-sm-6">
						<c:set var="studentLst" value="${studentlist}"/>
							<label class="control-label" style="color:#008000"for="id">Add Student:</label>
							<select name="students" multiple="multiple" id="studentsId" class="form-control select2" data-placeholder="Select Student..." style="width: 100%" required="required">
				              <%--  <c:forEach var="Sitem" items="${studentlist}">
				                
								  <option  value="${Sitem.id}">${Sitem.firstName} ${Sitem.lastName} </option>							   
							   </c:forEach>  --%>
			                 </select> 
			               <br>
						</div><br><br><br><br>
						
						
						
						  <%-- <div class="col-sm-6">
						<c:set var="studentLst" value="${studentlist}"/>
							<label class="control-label" style="color:#008000" ></label>
				            <input type="text" name="mgroupname" id="mgroupname"/>
			               <br>
						</div><br><br><br><br> --%>
					</div> <br><br>
					<br>
					
					<hr class="yello_hr"/>
					<!-- <div class="col-sm-12">
						  <div class="col-sm-12">
		       <table id="example" class="table table-bordered table-striped"  >
				   <thead >
				     <tr>
				        <th style="width: 50px;">#</th>
				        <th>Full Name</th>
				        <th> Mark Student  </th>
				     </tr>
				   </thead>
				   <tbody id="students" >
				   </tbody> 
			    </table> 
					
					</div>
					</div> -->
					<div class="form-group">
						<div class="fileupload ">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" style="background-color:green" value="Create Profile" class="btn ">
						</div> 
					</div>
					
			</form:form>
			</div>
		</div>
		</div>
   </section>
   </div>
   </div>
   <script>
	 $(function () {
		$(".select2").select2();
	 });	
	 
	 /*.....................................................................................  */

	  $("#year").change(function(){
		  document.getElementById("divId").selectedIndex = "0";
	  }); 
	  
	  $("#divId").change(function(){
		  var x= document.getElementById("year").value;
		    var y = document.getElementById("divId").value;
		    var z="${sessionScope.user.comClientName.id}"; 
		 
		  var datalist="";
		    $.ajax({
		         type: "POST",
		         url: "/"+location.pathname.split("/")[1]+"/web/taskforce/service/student/list",
		         data:'yr='+ x +'&div='+ y+'&client_id='+z,
		         dataType: 'json',
		          success: function(data){
				    	
				    	 if(data.length > 0){
				    		 var srno=1;
				    		 for ( var i = 0, len = data.length; i < len; ++i)  {
				          var info = data[i];
				          
				          datalist+="<option value='"+info.comClientName.id+"'>"+ info.comClientName.firstName +"&nbsp;&nbsp;"+ info.comClientName.middleName +"&nbsp;&nbsp;"+ info.comClientName.lastName +"</option>" ;
				          
				 		   srno++;
				 		}
				    		 
				    		 $('#studentsId').html(datalist);
					 		  
				       } 
				     },
		         
			     error:function(data){
			    	    alert("error");
			     }
		    });
	  }); 
 	
   </script>
   
