<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<!DOCTYPE html>
<html>
<script type="text/javascript">
$(document).ready( function(){
   $("#myElem").show().delay(5000).queue(function(n) {
	  $(this).hide(); 
	});
});
</script>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Add icon library -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;
        

}

* {
  box-sizing: border-box 1px black;
}

form { 
        
         
        padding: 10px 10px;
top-margin:100px;
 margin-bottom: 15px;
    } 
    
.input-container {
  display: -ms-flexbox; /* IE10 */
  display: flex;
  width: 100%;
  margin-bottom: 15px;
  
}
.selectfield
{
 background: white;
 width: 100%;
  padding: 10px;
  outline: none
  
}
.icon {
  padding: 10px;
  background: green;
  color: white;
  min-width: 50px;
  text-align: center;
}

.input-field {
  width: 100%;
  padding: 10px;
  outline: none;
}

.input-field:focus {
  border: 3px solid gray;

}

/* Set a style for the submit button */
.btn {
  background-color: green;
  color: white;
  padding: 12px 12px;
  border: none;
  cursor: pointer;
  width: 20%;
  opacity: 0.9;
  margin-left: 0px;
}

.btn:hover {
  opacity: 1;
}
.testmsg
{
color:red;
 font-size:12px;
 font-style: italic;
}
.testcontactno
{
color:red;
 font-size:12px;
 font-style: italic;
}
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}


.container
{
 margin-top: 70px;
}

.modal-content {
  background-color: #fefefe;
  margin: auto;
  padding: 15px;
  border: 3px solid green;
  width: 35%;
}

</style>
</head>
<body onload='document.loginForm.username.focus();' background="https://res.cloudinary.com/dcptr5ivh/image/upload/v1567082987/ctluump8etwqmzoymcyp.png">

<div class="container" >
<div class="modal-content">
<form  
action="<c:url value='/taskforce/service/staff/register/save' />" method='POST'style="max-width:500px;margin:auto" id="registerform"
 >
  <center><h3><i>Register Form</i></h3></center>

  <div class="input-container">
    <i class="fa fa-user icon"></i>
    <input class="input-field" type="text" placeholder="First Name" name="firstName" >
  </div>
  
  <div class="input-container">
    <i class="fa fa-user icon">
    </i>
    <input class="input-field" type="text" placeholder="Last Name" name="lastName" required="required" >
  </div>

  <div class="input-container">
    <i class="fa fa-envelope icon"></i>
    <input class="input-field" type="text" placeholder="Email" name="emailId" id="emailId" onchange="checkemailid()" required>
  </div>
  <div class="testmsg" id="demo"></div>
  
  <div class="input-container">
    <i class="fa fa-phone icon icon"></i>
    <input class="input-field" type="text" placeholder="Contact Number" name="contactNos" id="contactNos" onchange="checkcontactno()">
  </div>
   <div class="testcontactno" id="contactno"></div>
   
  <div class="input-container">
    <i class="fa fa-institution icon"></i>
    <input class="input-field" type="text" placeholder="Institute Code" name="instituteid" >
  </div>
  
  
  <div class="input-container">
    <i class="fa fa-mortar-board icon"></i>
     <select  class="selectfield" name="designations" placeholder="Select Designation" >
      <option value="0">Select Designation</option>
                            	 	 <option value="ROLE_ADMIN">System Admin</option>
                             		 <option value="ROLE_IT">IT Support</option>
                             		 <option value="ROLE_PRINCIPAL">Principal</option>
                             		 <option value="ROLE_HOD">HOD</option>
                             		 <option value="ROLE_TEACHER">Teacher</option>	
                               </select> </div>


<div class="input-container">
    <i class="fa fa-key icon"></i>
    
     <select  class="selectfield" name="deptid" placeholder="Select department" >
      <option value="0">Select Department</option>                
                            <c:forEach var="item" items="${deptlist}" >
						       <option value="${item.dep_id}" ${item.dep_id == selectedDept ? 'selected="selected"' : ''}>${item.dep_name}</option>
						    </c:forEach>
								   </select>
								   </div>

									  
  <button type="submit" name="submit" class="btn">Register</button>
 <a href="<c:url value='/login'/>"><font style="font-style: italic ; color:green">Login</font></a>
  
</form>
</div>
 </div>

<script type="text/javascript">

function checkemailid() {
   
        var email;
        email = document.getElementById("emailId").value;

            var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

            if (reg.test(emailId.value) == false) 
            {
            document.getElementById("demo").style.color = "red";
            document.getElementById("demo").innerHTML ="Please Enter Valid Email Id :"+ email;               
                return false;
            } else{
            	 $.ajax({
           		  
            		  type : "POST",
            	         url: "/"+location.pathname.split("/")[1]+"/taskforce/service/staff/test/check",
            	                data:'&email='+email,			
            					dataType: 'json',		        
                         success: function(data)
            	         { 
                       	  var message=data;
                       
                       	  document.getElementById("demo").innerHTML = ""+message;   

            	         },
            		     error:function(data)
            		     {
            		    	   alert("error in jsp"+email);
            		     }
            	    });
               
            	             }
       return true;
   	
 	}

function checkcontactno()
{
    var contactno;
    contactno = document.getElementById("contactNos").value;

        var regularexp =/^\d{10}$/;

        if (regularexp.test(contactNos.value) == false) 
        {
        document.getElementById("contactno").style.color = "red";
        document.getElementById("contactno").innerHTML ="Please Enter 10 digit number:"+ contactno;               
            return false;
        } else{
        document.getElementById("contactno").style.color = "DarkGreen";      
        document.getElementById("contactno").innerHTML ="";
        }
   return true;
   }
  
</script>
</body>

</html>
