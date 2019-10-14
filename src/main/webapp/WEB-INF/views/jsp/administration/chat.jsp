<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="com.zertones.model.sims.Staff"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<style>
* {
  box-sizing: border-box; margin: 0; padding: 0;
}
.menu {
  float:left;
  width:20%;
  height:84%;
  text-align:center;
  overflow:scroll; 
  padding-top: 2%;
}
.menuitem {
  background-color:#e5e5e5;
}
.main {
  float:left;
  width:60%;
  height:84%;
  padding:0 20px;
  background-color: #d3efc6;
}
.rightmenu {
  background-color:#e5e5e5;
  float:left;
  width:20%;
  height:84%;
  padding:15px;
  margin-top:7px;
  text-align:center;
  background-color: #ffff;
}
.input-container{
		   width:57%;
		   position:absolute;
		   bottom:7%; 
           float: left;
        }
       
  
    /* Flexbox Example */
    .flexbox { display: flex;}
    .flexbox .stretch { flex: 1;}
    .flexbox .normal { flex: 0; margin: 0 0 0 1rem; color:#ffff}
    .flexbox div input { padding: .5em 1em; width: 100%; }
    .flexbox div button { padding: .5em 1em; white-space: nowrap; background-color: #008000;
    }

@media only screen and (max-width:1000px) {
/* For mobile phones: */
.main {
   width: 100% !important;
   height: 93%;
  }
  .menu {
    display: none;
  }
  .rightmenu {
    display: none;
  }
 .input-container{
   width:90%;
   padding-bottom: 0px;
 }
}

.container {
    border: 2px solid #dedede;
    background-color: #f1f1f1;
    padding: 10px;
    margin: 10px;
    width:95%;
    height:7%;
    margin-top:5px;width:85%;border-radius:5px;padding:5px;display:flex;
}

.darker {
    border-color: #ccc;
    background-color: #ddd;
    height:7%;
    width:70%;
     
}

.container::after {
    content: "";
    clear: both;
    display: table;
}


.time-right {
    float: right;
    color: #aaa;
    
}

.time-left {
    float: left;
    color: #999;
}
</style>

<style>

.mytext{
    border:0;padding:10px;background:whitesmoke;
}
.text{
    width:75%;display:flex;flex-direction:column;
}
.text > p:first-of-type{
    width:100%;margin-top:0;margin-bottom:auto;line-height: 13px;font-size: 12px;
}
.text > p:last-of-type{
    width:100%;text-align:right;color:silver;margin-bottom:-7px;margin-top:auto;
}
.text-l{
    float:left;padding-right:10px;
}        
.text-r{
    float:right;padding-left:10px;
}
.avatar{
    display:flex;
    justify-content:center;
    align-items:center;
    width:25%;
    float:left;
    padding-right:10px;
}
.macro{
    margin-top:5px;width:85%;border-radius:5px;padding:5px;display:flex;
}
.msj-rta{
    float:right;background:whitesmoke;
}
.msj{
    float:left;background:white;
}

ul {
    width:100%;
    list-style-type: none;
}
.msj:before{
    width: 0;
    height: 0;
    content:"";
    top:-5px;
    left:-14px;
    position:relative;
    border-style: solid;
    border-width: 0 13px 13px 0;
    border-color: transparent #ffffff transparent transparent;            
}
.msj-rta:after{
    width: 0;
    height: 0;
    content:"";
    top:-5px;
    left:14px;
    position:relative;
    border-style: solid;
    border-width: 13px 13px 0 0;
    border-color: whitesmoke transparent transparent transparent;           
}  
input:focus{
    outline: none;
}        
::-webkit-input-placeholder { /* Chrome/Opera/Safari */
    color: #d4d4d4;
}
::-moz-placeholder { /* Firefox 19+ */
    color: #d4d4d4;
}
:-ms-input-placeholder { /* IE 10+ */
    color: #d4d4d4;
}
:-moz-placeholder { /* Firefox 18- */
    color: #d4d4d4;
}   

</style>
<c:url var="save" value="/web/taskforce/chat/save" />

<script>

function getMsges(fromId,deviceId){
	getmsg(fromId,deviceId);
}
function getStudentInfo(fromId){
  $('#studentIfo').empty();
  $.ajax({
		type: "GET",
        url:  "/CollegeApp/api/sims/student/getstudrecbyid/"+fromId,
        dataType : 'json',
	    context: 'application/json; charset=utf-8',
        success: function(Student){
        $.each(Student, function(k, sdata) {
        	 $("#studentIfo").append(
        			 '<div class="" style="margin-left:30px">',
			         '<img class="direct-chat-img" src="<c:url value="/static/img/author.png"/>" style="width:60px; height:60px" alt="Message User Image"><br>',
	                 '<span style="padding-left:10px; color:black" id="name"><b >Student Name </b></span><br>',
	                 '<span style="padding-left:10px; color:green" ><b >'+sdata.comClientName.firstName+'</b></span><br>',
	              '</div>'
        	       );
           });
      },
    error: function(){
        alert("Opps..! Error Occured");
    } 
  });
}

function getmsg(fromId,deviceId){
	var pathname = window.location.pathname;
	$('#msgContainer').empty();
	$('.treeview a[href="#chat_'+fromId+'"]').tab('show');
	var uId = document.getElementById('userId').value;
	document.getElementById('studentId').value = fromId;
	document.getElementById('diviceId').value = deviceId;
	var name=null;
	$.ajax({
		type: "GET",
		url:  "/"+location.pathname.split("/")[1]+"/web/taskforce/chatList/getMsg/"+deviceId+"/"+uId,
		dataType : 'json',
		context: 'application/json; charset=utf-8',
		success: function(Object){
			$.each(Object, function(j, pdata) {
			  if(pdata != ''){	
			     if(uId == pdata.fromId){
			        	$('#msgContainer').append(
			        		'<div class="direct-chat-msg right" ><div class="direct-chat-text  col-md-4 col-md-push-7" >'+pdata.textMsg+'</div></div>'
			        	)
			       }else{
			        		$('#msgContainer').append(
				        		' <div class="direct-chat-msg "><div class="direct-chat-text  col-md-4 " >'+pdata.textMsg+'</div></div>'
				        	) 
			        	}
			    }
			  });
		    },
		    error: function(){
		       // alert('There was an error in communication.');
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
            url: '/'+location.pathname.split("/")[1]+'/web/taskforce/chatList/save',  
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

<%
	Staff user =(Staff)request.getSession().getAttribute("user");    
%>
</head>
<body style="font-family:Verdana;color:#aaaaaa;" class="hold-transition skin-blue sidebar-mini" id="bodyContaint">
 <div style="overflow:auto">
  <div class="menu ">
   <ul class="col-md-12" style="display:flex;flex-direction: column;">
	    <c:forEach var="chatList1" items="${chatList}">
	      <li class=" treeview"  style="margin: 5px">
	        <a href='#chat_${chatList1.comClientName.id}' onclick="getMsges(${chatList1.studentId},${chatList1.comClientName.comUserDetails.id}) ">
	            <i class="fa" style="float: left">
	               <c:choose>
		                <c:when test="${chatList1.profileImage != null}">
						       <img  src="data:image/jpg;base64,${chatList1.getByteArrayString()}"  style="width:40px; height:40px"  class="img-circle">
		                </c:when>
						<c:otherwise>
						   <img src="<c:url value="/static/img/author.png"/>" style="width:40px; height:40px" class="img-circle">
		               </c:otherwise>
	               </c:choose>
	            </i >
	              <span style="color:#000000;margin-left:10px;margin-top:13px; float: left" ><font size='2'>${chatList1.comClientName.firstName}  ${chatList1.comClientName.lastName}</font></span>
	             <span class="pull-right-container"></span>
	          </a>
	        </li>
	      </c:forEach> 
	  </ul>
    </div>
  <div class="main">
    <div class="box-default " style="border-radius:21px" >
	    <div class="direct-chat-messages" style="height:89%;" id="test">
		    <div id="msgContainer">
		    </div>
		</div>
	</div>
    <section class="flexbox input-container">
        <div class="stretch">
            <input type="text" id="textMsg" placeholder="Message..." />
        </div>
        <div class="normal">
             <button class="btn " id="btnSave" type="submit">SEND</button>
        </div>
    </section>
    <input type="hidden" path="fromId" id="userId" value="<%= user.getStaffId()%>" />
        <input type="hidden" path="toId" id="studentId" />
        <input type="hidden" path="toId" id="diviceId" />
  </div>
  <div class="rightmenu" id="info">
             <div  >
		       <h3 align="center">Student Information</h3>
			   <hr>
		       <div id="studentIfo">
			   </div>
			   </div>
			   <div style="border-radius:21px" >
	           <div ><br>
					<div style="margin-left:30px">
			                 Class:
			          </div>
			          <div style="margin-left:30px">
			                 Dept.:
			          </div>
			          <div style="margin-left:30px">
			                 Marks:
			          </div>
			          <div style="margin-left:30px">
			                 Grade:
			          </div>
			          <div style="margin-left:30px">
			                Add Note:<input type="text">
			          </div>
			   </div>
		   </div>
	     </div>
	   </div>
     <div style="background-color:#fff;text-align:center;">© Zerton Engineering Services Pvt Ltd <a>zerton.in</a></div>
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

