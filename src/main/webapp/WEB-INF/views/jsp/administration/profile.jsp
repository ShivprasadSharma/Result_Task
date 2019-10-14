<%@page import="com.zertones.model.ComClientName"%>
<%@page import="com.zertones.model.sims.Staff"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <c:url var="saveProfile" value="/web/taskforce/sims/staff/update" />
  <c:url var="saveUserEntity" value="/web/taskforce/sims/staff/set" />
 
<script>
$(function () {
    $("#profileImg").click(function(){
    	$("#inputImage").trigger("click");
    });
    $("#inputImage").change(function(){
	    readURL(this);
		});  
    });
	function readURL(input) {
         if (input.files && input.files[0]) {
	        var reader = new FileReader();
             reader.onload = function (e) {
            	  $('#profileImg').attr('src', e.target.result);
			  }
            reader.readAsDataURL(input.files[0]);
	    }
	}
	function Validate() {
	    var p = document.getElementById("txtNewEntity").value;
	    var c = document.getElementById("txtConfirmEntiry").value;
	    if (p != c && c != '') {
	    	    $('#message').html('Password not Match  !').css('color', 'red');
	    	    $("#message").show().delay(5000).queue(function(n) {
	    			 $(this).hide(); 
	    		});
	        return false;
	    }
	    return true;
	}
	 function check1()
	  {
	  	 var text;
	  	 var none = document.getElementById("inputImage").files[0].size;
	  	     if(none > 1048576) 
	  	     {
	  	    		text = "<h5 ><i>*Please select less then <u> 1MB </u> file</i></h5>";
	  	        	document.getElementById("demo").innerHTML = text;
	  	       	    document.getElementById('submitbtn').disabled = true;
	  	      }
	  }
</script>

<body  class="hold-transition skin-blue fixed sidebar-mini" >
<div class="wrapper" >
<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" >
    <!-- Content Header (Page header) -->
    <section class="content-header" style="padding-top:31px">
      <h1>PERSONAL SETTING  </h1>     
    </section>
   <!-- Main content -->
   <section  class="myStyle" style="padding-top:0px">
     <div class="box box-default" style="margin-left:40px ; margin-right:40px">
        <!-- /.box-header -->
        <div class="box-body" style="background-color:#eff5ec;" >
           <form:form id="profileForm" action="${saveProfile}" commandName="profileForm" modelAttribute="profileForm" method="POST" class="pure-form pure-form-aligned" role="form" enctype="multipart/form-data">
             <div class="row "  >
                 <input type="hidden" name="staffId" value="${sessionScope.user.staffId}"/> 
                 <input type="hidden" name="comClientName.id" value="${sessionScope.user.comClientName.id} "/> 
                 <c:set var="inputDisplay" value="${msg}" /> 
		              <div align="center">
			                <c:choose>
				                <c:when test="${sessionScope.user.comClientName.imgUrl != null}">
								       <img id="profileImg" style="width:90px;  height:90px" src="${sessionScope.user.comClientName.imgUrl}" class="img-circle">
				                       <input type="file"  id="inputImage" name="profilefile" style="display:none" accept="image/*" onchange="check1()" >
			                           <p style="color:red" id="demo"></p>
			                    </c:when>
								<c:otherwise>
								   <img id="profileImg" src="<c:url value="/static/img/author.png"/>" style="width:90px;  height:90px" class="img-circle">
								   <input type="file"  id="inputImage" name="profilefile" style="display:none" accept="image/*" onchange="check1()" >
			                        <p style="color:red" id="demo"></p>
			                    </c:otherwise>			                    
				            </c:choose> 
			         </div>
		             <div class="col-md-6">
		                <div class="form-group">
		                <h4></h4> 
		                  <label>First Name*</label>
		                       <input name="comClientName.firstName" value="${sessionScope.user.comClientName.firstName}" class="form-control" placeholder="Name ..."/>
		                  </div>
		              <div class="form-group">
		                  <label>Mobile Number*</label>
		                 <input name="comClientName.contactNos" value="${sessionScope.user.comClientName.contactNos}" class="form-control"    placeholder="Contact number ..." /> 
		                </div>
		              
					  <div class="form-group">
		                  <label>Designation</label>
		                 <input name="designation" value="${sessionScope.user.designation}" class="form-control"  readonly="readonly"  placeholder="Designation ..." /> 
		                </div>
		            </div>		           
		            <div class="col-md-6">
		               <div class="form-group" >
		                  <label style="margin-top:13px">Last Name*</label>
		                  <input name="comClientName.lastName" class="form-control" value="${sessionScope.user.comClientName.lastName}" placeholder="Last Name ..." /> 
		                </div>
						
						<div class="form-group">
		                  <label>Email Id</label>
		                  <input name="comClientName.emailId" class="form-control" value="${sessionScope.user.comClientName.emailId}" readonly="true" placeholder="Email Address ..."/>  
		                </div>						
						<!-- /.form-group -->
		             </div>
				    </div>
				   <div align="right">
				        <input type="submit" id="submitbtn" value="Save" class="btn btn-success" /> &nbsp;&nbsp;
				        <input type="reset" value="Clear" class="btn btn-default" /> 
				   </div>
			</form:form> 
         </div>
        
      </div>
      <h3>&nbsp; &nbsp; &nbsp; PASSWORD SETTING</h3>
      <div class="box box-default" style="margin-left:40px ; margin-right:40px">
        <!-- /.box-header -->
        <div class="box-body" style="background-color:#eff5ec;" >
             <c:if test="${not empty error}">
			     <div align="Center"><div id="myElem" class="error" >${error}</div></div>
			  </c:if>
			  <c:if test="${not empty msg}">
				 <div align="Center"><div id="myElem" class="msg" >${msg}</div></div>
			</c:if>
          <form:form  commandName="userEntity" action="${saveUserEntity}" method="post" class="pure-form pure-form-aligned" role="form" enctype="multipart/form-data">
              <form:hidden path="id" value="${sessionScope.user.comClientName.id}"/> 
              
                <div class="row "  >
                   <div class="col-md-12">
            			    <div class="col-md-6">
				             <div class="form-group ">
				                 <label>Old Password*</label>
				                 <form:password path="oldCredential" class="form-control" required="required"  placeholder="Enter Old Password ..."/> 
				             </div>
			            </div>
                   </div>
                   <div class="col-md-12">
			             	  <div class="col-md-6">
				                	<div class="form-group ">
				                 		 <label>New Password*</label>
				                  		<form:password path="newCredential"  id="txtNewEntity" class="form-control" required="required" placeholder="Enter New Password ..."/> 
				                 	</div>
			                 </div>
			                  <div class="col-md-6">
			                       <div id="message"></div>
			                        <div class="form-group">
			                  	       <label>Re-Enter Password*</label>
			                  		   <form:password path="confirmCredential" id="txtConfirmEntiry" class="form-control" required="required" placeholder="Re-Enter Password ..." />
			                		   </div>
			                  </div>
		                </div>
		           </div>
				   <div align="right">
				        <input type="submit" onclick="return Validate()" value="save" class="btn btn-success" /> &nbsp;&nbsp;
				        <input type="reset" name="resetBtn" value="Clear" class="btn btn-default" /> 
				   </div>
			 </form:form> 
         </div>
        
      </div>
      </section>
      </div>
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

