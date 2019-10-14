<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url var="saveCredential" value='/user/save' />
<script type="text/javascript">
$(document).ready( function(){
  $("#myElem").show().delay(5000).queue(function(n) {
	 $(this).hide(); 
	 window.location.href = "<c:url value='/'/>";
	});
});
function Validate() {
    var pas = document.getElementById("txtEntity").value;
    var con = document.getElementById("txtConfirmEntity").value;
    if (pas != con && con != '') {
    	    $('#message').html('Password not Match  !').css('color', 'red');
        return false;
    }
    return true;
}
</script>
<html>

<body style="background-color:#d3efc6">
     <div class="container">
	     <div >	
	        <c:if test="${not empty error}">
			     <div align="Center"><div id="myElem" class="error" >${error}</div></div>
			</c:if>
			<c:if test="${not empty msg}">
				 <div align="Center"><div id="myElem" class="msg" >${msg}</div></div>
			</c:if>
       <form:form commandName="userEntity" action="${saveCredential}" id="myForm" method='POST'>
           <table align="center">
		       <h3 align="center"> Set New Password</h3>
		        <c:set var="userInfo" value="${userInfo}"/>
			<tr> 
			  <td><form:hidden path="id" value="${userInfo.comClientName.id}" />
			    <span id='message'></span>
			  </td>
			  
			</tr>
			<tr>
			  <td><form:password path="newCredential" id="txtEntity"  required="required" placeholder="Enter New Password..." /></td>
			</tr>
			<tr>
			  <td><form:password path="confirmCredential" id="txtConfirmEntity"  required="required" placeholder="Re-Enter Password..."/>
			 </td>
			
			</tr>
			<tr>
			   <td colspan='12' align="center"><button type="submit" onclick="return Validate() ">Submit</button> 
			</tr>
		  </table><br>
		</form:form>
	   </div>
   </div>
</body>
</html>