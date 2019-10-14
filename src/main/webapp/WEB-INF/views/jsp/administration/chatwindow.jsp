<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.zertones.model.ComClientName"%>
<%@page import="com.zertones.model.sims.Staff"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url var="save" value="/web/taskforce/chat/save" />

<script>

function getMsges(fromId,deviceId){
	getmsg(fromId,deviceId);
}
function getStudentInfo(fromId){
	$.ajax({
		type: "GET",
        url:  "/CollegeApp/api/sims/student/getstudrecbyid/"+fromId,
        dataType : 'json',
	    context: 'application/json; charset=utf-8',
        success: function(Student){
        $.each(Student, function(k, sdata) {
        	 $("#studentIfo").append(
        			 '<div class="" style="margin-left:30px">',
			         '<img class="direct-chat-img" src="<c:url value="/static/img/author.png"/>" style="width:60px; height:60px" alt="Message User Image">',
	                 '<span style="padding-left:10px;" id="name"><b >'+sdata.comClientName.firstName+'</b></span><br>',
	                 'span style="padding-left:10px; color:#008000">Student</span>',
	          '</div>'
        	  );
           });
      },
    error: function(){
        //alert('There was an error in communication in Student Info.');
    } 
    
});
}

function getmsg(fromId,deviceId){
	alert(fromId +" "+deviceId);
	//$('#msgContainer').load(location.href +  '#msgContainer');
	 $('#msgContainer').empty();
	$('.treeview a[href="#chat_'+fromId+'"]').tab('show');
	var uId = document.getElementById('userId').value;
	document.getElementById('studentId').value = fromId;
	document.getElementById('diviceId').value = deviceId;
	var name=null;
	$.ajax({
				type: "GET",
		        url:  "/CollegeApp/web/taskforce/chatList/getMsg/"+deviceId+"/"+uId,
		        dataType : 'json',
			    context: 'application/json; charset=utf-8',
		        success: function(Object){
		        $.each(Object, function(j, pdata) {
		        	if(pdata != ''){	
		        		if(uId == pdata.fromId){
		        		 $('#msgContainer').append(
		        				 '<div class="direct-chat-msg " ><div class="direct-chat-text col-md-4" >'+pdata.textMsg+'</div></div>'
		        		 )
		        		 }else{
		        			 $('#msgContainer').append(
			        				 ' <div class="direct-chat-msg right"><div class="direct-chat-text col-md-4 col-md-push-7" >'+pdata.textMsg+'</div></div>'
			        		 ) 
		        		 }
		        	 }
		           });
		      },
		    error: function(){
		        alert('There was an error in communication.');
		    } 
		    
		});
  getStudentInfo(fromId);
}
$(document).ready(function () {  
    $("#btnSave").click(function () {  
    	
    	
    	 var ComChatMessages={}
    	 ComChatMessages.textMsg = $("#textMsg").val();  
    	 ComChatMessages.fromId = $("#userId").val(); 
    	 ComChatMessages.toId = $("#diviceId").val();
    	 var userId = $("#userId").val(); ;
      var deviceId = $("#diviceId").val(); 
    
    if( userId != null && deviceId !=null){
      $.ajax({  
            url: '/CollegeApp/web/taskforce/chatList/save',  
            method: 'post',  
            contentType: 'application/json;Charset=utf-8',  
            data:JSON.stringify(ComChatMessages),  
            success: function () {  
            	$('#textMsg').val("");
            	getmsg(userId,deviceId);
            },  
            error: function (err) {  
                alert("Internal Error is Occured.");  
            }  
        });  
    }else{
    	alert("Please Select a at least one Member from chat menu..! ");
    }
        
    });  
});  
</script>

<body class="hold-transition skin-blue fixed sidebar-mini" id="bodyContaint">
<!-- Site wrapper -->
<div class="wrapper">
<!-- =============================================== -->
 <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
  
    <!-- Main content -->
     <div class="col-md-9 " style="padding-top:40px; ">
         <div class="box-default" style="border-radius:21px" >
          
	           <div class="direct-chat-messages" id="test">
		              <div id="msgContainer">
		                  
		              </div>
			   </div>
		  </div>
		  <!-- /.box-body -->
            <div class="box-footer">
                
               <%--  <form:form action="${save}" method="POST" commandName="msgform"> --%>
               
                  <div class="input-group" >
                   <input  path="textMsg" id="textMsg" placeholder="Type Message ..." class="form-control" />
                      <span class="input-group-btn">
                        <button type="submit" id="btnSave" class="btn btn-success btn-flat">Send</button>
                      </span>
                      <input type="hidden" path="fromId" id="userId" value="${sessionScope.user.staffId} " />
                       <input type="hidden" path="toId" id="studentId" />
                       <input type="hidden" path="toId" id="diviceId" />
                </div>
                  <%-- </form:form> --%>
                
               
            </div>
            <!-- /.box-footer-->
     </div>
    <div class="col-md-3 mystyle1 " >
         <div class="" style="border-radius:21px" >
		       <h3 align="center">Student Information</h3>
			   <hr>
		           <div id="studentIfo">
						 <div class="" style="margin-left:30px">
				         <img class="direct-chat-img" src="<c:url value="/static/img/author.png"/>" style="width:60px; height:60px" alt="Message User Image">
		                 <span style="padding-left:10px;" id="name"><b >Student</b></span><br>
		                 <span style="padding-left:10px; color:#008000" >Student</span>
		           </div>
			   </div>
		 </div>
		 <div class="" style="border-radius:21px" >
	           <div class=""><br>
					<div class="" style="margin-left:30px">
			                 Class:
			          </div>
			          <div class="" style="margin-left:30px">
			                 Dep.:
			          </div>
			          <div class="" style="margin-left:30px">
			                 Marks:
			          </div>
			          <div class="" style="margin-left:30px">
			                 Grade:
			          </div>
			          <div class="" style="margin-left:30px">
			                Add Note:<input type="text">
			          </div>
			   </div>
		 </div>
     </div>
    <!-- /.content -->
 </div>
  <!-- =============================================== -->
  <!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-127607784-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());
  gtag('config', 'UA-127607784-1');
</script>
</body>

