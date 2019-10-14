<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<c:url var="getBalnkGroupNotice" value="/web/taskforce/notice/blankGroupNotice/" />
<c:url var="editGroup" value="/web/taskforce/group/info/" />
<c:url var="getGroupMember" value="/web/taskforce/group/memberlist/" />
<c:url var="removeMember" value="/web/taskforce/group/removeMember/" />

<style>
  .myMsg{
	padding: 10px 6px 10px 11px;
	margin-bottom: 11px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
   }
   .errorMsg{
	padding: 10px 6px 10px 11px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #f2dede;
	border-color: #f2dede;
   }
</style>

<script>
  $(document).ready( function(){
   $("#myElem").show().delay(5000).queue(function(n) {
	  $(this).hide(); 
	});
  });
  function formSubmit() {
			document.getElementById("logoutForm").submit();
  }
  
  function check1()
  { 
	  document.getElementById('submitbtn').disabled = true;
  	 var text;
  	 var none = document.getElementById("notifile1").files[0].size;
  	     if(none > 1048576) 
  	     {
  	    		text = "<h5 ><i>*Please select less then <u> 1MB </u> file</i></h5>";
  	        	document.getElementById("demo").innerHTML = text;
  	       	    document.getElementById('submitbtn').disabled = true;
  	      }
  	   else
	   {
	        document.getElementById("demo").style.display ="none";
	    	document.getElementById('submitbtn').disabled = false;
	   }
  }
  function likeFunction(noficationId,client_Id) {
		var likeStatus=$("#likebtn"+noficationId).val();
		if(likeStatus == "false"){
			likeStatus = true;
		}else if(likeStatus == "true"){
			likeStatus= false;
		}
		 $.ajax({
	         type: "POST",
	         url: "/"+location.pathname.split("/")[1]+"/web/sims/like/incriment",
	         data:'noticeId='+ noficationId +'&likeStatus='+ likeStatus+'&client_Id='+client_Id,
	         dataType: 'json',
	         success: function(data){
	        		//alert("Response :"+data);
	        			if(data)
	        			{
	        				var count=$("#likeCount"+noficationId).text();
	        				var a=parseInt(count);
	        				var commentCount=++a;
	        				$("#likeCount"+noficationId).html(commentCount);
	        				$("#likebtn"+noficationId).css({"color":"#008000"});
	        				$("#likebtn"+noficationId).val(true);
	        			}else
	        			{
	        				var count=$("#likeCount"+noficationId).text();
	        				var a=parseInt(count);
	        				var commentCount=--a;
	        				$("#likeCount"+noficationId).html(commentCount);
	        				$("#likebtn"+noficationId).css({"color":"black"});
	        				$("#likebtn"+noficationId).val(false);
	        			}
	         },
		     error:function(data){
		    	    alert("Opps..!");
		     }
	    }); 	 
	}
  
  function commentInsert(noticeId,clientid,commCount) {
		var comText=$("#commenttext"+noticeId).val();
		$("#commenttext"+noticeId).val("");
		var count=$("#commentCount"+noticeId).text();
		var a=parseInt(count);
		var commentCount=++a;
		$.ajax({
	         type: "POST",
	         url: "/"+location.pathname.split("/")[1]+"/web/sims/comment/insert",
	         data:'noticeId='+ noticeId +'&clientId='+ clientid+'&comment='+comText+'&CommentCount='+commentCount,
	         dataType: 'json',
	         success: function(data){
	        	 var commentlist="";
		        	if(data)
		        	{
		        		$("#commentlist"+noticeId).append("<p><b>${sessionScope.user.comClientName.firstName} ${sessionScope.user.comClientName.lastName} : </b>"+comText+"</p>");
		        		$("#commentCount"+noticeId).html(commentCount);
		        	}	        	 
	         },
		     error:function(data){
		    	    //alert("error");
		     }
	    });
	}
  function commentfunction(noticeId,clientid) {
		var check=$("#commentbtn"+noticeId).val(); 	
		if(check=="true")
		{	    
			$.ajax({    
		         type: "POST",
		         url: "/"+location.pathname.split("/")[1]+"/web/sims/comment/CommentList",
		         data:'noticeId='+ noticeId,
		         dataType: 'json',
		         success: function(data){
		        	var commentlist="";
		        	if(data!="")
		        	{
		        		var datalist="";
		        		var count=0;
		        		 for ( var i = 0; i < data.length; i++) {
				               var user = data[i];				           
				              	datalist=datalist+"<p><b>" + user.firstName+" "+user.lastName+" &nbsp; :&nbsp; </b> "+user.comment +"</p>";
				                count++;
				            } 
		        		 $("#commentlist"+noticeId).html(datalist);		        		
		        	}    	
		         },
			     error:function(data){
			    	   // alert("error");
			     }
		    });
			$("#commentbtn"+noticeId).val("false");
			$("#commentbox"+noticeId).show();
		}	
		else
		{				
			$("#commentbtn"+noticeId).val("true");
			$("#commentbox"+noticeId).hide();
		}	
	}
	
  function addGroupNotice(groupId)
  {
      $.get('${getBalnkGroupNotice}'+ groupId, function(result)
  	{
  		$("#noticeFormModal").html(result);
      });
      $("#noticeFormModal").modal('show');
  }
  
  function editGroup(groupId)
  {
  	 $.get('${editGroup}'+ groupId, function(result)
  	{
  		$("#editGroupModel").html(result);
      });
      $("#editGroupModel").modal('show');
  }
  
  function addMembers(groupId)
  {
  	$.get('${getGroupMember}'+ groupId, function(result)
  	{
  		$("#groupMemberModal").html(result);
      });
      $("#groupMemberModal").modal('show');
  }
  function removeMember(groupId)
  {
  	$.get('${removeMember}'+ groupId, function(result)
  	{
  		$("#removeMemberModel").html(result);
      });
      $("#removeMemberModel").modal('show');
  }
  
 /*  function getInfo() {
	  
	  var stffId="${sessionScope.user.comClientName.id}";
	
	  var forValue = 1;
	  if (document.getElementById('staff').checked) {
		 	forValue=$("#staff").val();
		 	$('#incharge').hide();
		}else{
			forValue=$("#student").val();
			$('#incharge').show();
		}
	var groupType=$("#groupType").val(); 
	//alert("groupfor :"+ forValue + " grouptype:"+groupType);
	test(forValue ,groupType,stffId);
  }   */
 /*  
  function test(forValue ,groupType,stffId){
	  $.ajax({    
	        type: "POST",
	        url: "/"+location.pathname.split("/")[1]+"/web/taskforce/user/group/d",
	        data:'forValue='+ forValue + '&groupType='+ groupType + '&StaffID='+ stffId,
	        dataType: 'json',
	        success: function(data){	        	 
	        	$('#member').empty();
	       
	      	var member="";	       	
	       		var memberlist="";
	       		 for ( var i = 0; i < data.length; i++) {
			               var user = data[i];
			               if(!document.getElementById('staff').checked){
			              	$("#member").append(
			              			'<option value="' + user.id + '">'+ user.firstName + ' ' + user.lastName + ' (' + user.year + ' ' + user.depName.substring(0, 4) + ')</option>'     
			              		    );
			               }else{
			            	   var x =${sessionScope.user.comClientName.id};
			            	   var y =user.id;
			            	   if(x != y){
			            	   $("#member").append(
				              		    '<option value="' + user.id + '">'+ user.firstName + ' ' + user.lastName + ' </option>'     
				              		    );
	  							}
			               }
			            } 
	       		 //$("#member").html(memberlist);		        		
	          	
	        },
		     error:function(data){
		    	   // alert("error");
		     }
	   });
  } */
  
  </script>
 <!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-109514479-1"></script>
  <header class="main-header">   
    <!-- Logo -->    
    <a href="<c:url value="/web/taskforce/service/notification/list" />" class="logo">
      <span class="logo-mini" style="color:green"></br><b>${sessionScope.loginInfo.short_context}</b></span>
      <span class="logo-lg" style="color:green"><b>${sessionScope.loginInfo.short_context}</b><br><b>${sessionScope.loginInfo.context}</b></span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">${msg}</span>
      </a>
    <div class="navbar-custom-menu"> 
       <ul class="nav navbar-nav">
        <!-- Messages: style can be found in dropdown.less-->
           <li style="padding-top:25px">
               <c:if test="${ not empty errorMsg}"> 
					<span id="myElem" class="errorMsg" >${errorMsg}</span>
				</c:if>
				<c:if test="${ not empty message}"> 
					<span id="myElem" class="myMsg" >${message}</span>
				</c:if>
               
           </li>             
          <!-- Notifications: style can be found in dropdown.less -->
          <li class="dropdown notifications-menu">
           <!--  <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-bell-o"></i>
              <span class="label label-warning">10</span>
            </a> -->
            <ul class="dropdown-menu">
              <li class="header">You have 10 notifications</li>
              <li>
                inner menu: contains the actual data
                <ul class="menu">
                  <li>
                    <a href="#">
                      <i class="fa fa-users text-aqua"></i> 5 new members joined today
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-warning text-yellow"></i> Very long description here that may not fit into the
                      page and may cause design problems
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-users text-red"></i> 5 new members joined
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-shopping-cart text-green"></i> 25 sales made
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-user text-red"></i> You changed your username
                    </a>
                  </li>
                </ul>
              </li>
              <li class="footer"><a href="#">View all</a></li>
            </ul>
          </li>
          <!-- Tasks: style can be found in dropdown.less -->
         <li class="dropdown tasks-menu">
            <!-- <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-flag-o"></i>
              <span class="label label-danger">9</span>
            </a> -->
            <ul class="dropdown-menu">
              <li class="header">You have 9 tasks</li>
              <li>
                inner menu: contains the actual data
                <ul class="menu">
                  <li>Task item
                    <a href="#">
                      <h3>
                        Design some buttons
                        <small class="pull-right">20%</small>
                      </h3>
                      <div class="progress xs">
                        <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">20% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  end task item
                  <li>Task item
                    <a href="#">
                      <h3>
                        Create a nice theme
                        <small class="pull-right">40%</small>
                      </h3>
                      <div class="progress xs">
                        <div class="progress-bar progress-bar-green" style="width: 40%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">40% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  end task item
                  <li>Task item
                    <a href="#">
                      <h3>
                        Some task I need to do
                        <small class="pull-right">60%</small>
                      </h3>
                      <div class="progress xs">
                        <div class="progress-bar progress-bar-red" style="width: 60%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">60% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  end task item
                  <li>Task item
                    <a href="#">
                      <h3>
                        Make beautiful transitions
                        <small class="pull-right">80%</small>
                      </h3>
                      <div class="progress xs">
                        <div class="progress-bar progress-bar-yellow" style="width: 80%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">80% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  end task item
                </ul>
              </li>
              <li class="footer">
                <a href="#">View all tasks</a>
              </li>
            </ul>
          </li>
          
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu" >
           <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            		  <c:choose>
	              <c:when test="${sessionScope.user.comClientName.imgUrl  !=null }">
					       <img src="${sessionScope.user.comClientName.imgUrl}" class="user-image" alt="User Image">
	                </c:when>
					<c:otherwise>
					   <img src="<c:url value="/static/img/author.png"/>" class="user-image" alt="User Image">
	                </c:otherwise>
            </c:choose> 
            <span class="hidden-xs" style="color:green">${sessionScope.user.comClientName.firstName} ${sessionScope.user.comClientName.lastName}</span>			 </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header" style="background-color:#d3efc6">
               <c:choose>
	                <c:when test="${sessionScope.user.comClientName.imgUrl != null}">
					       <img src="${sessionScope.user.comClientName.imgUrl}" class="img-circle">
	                </c:when>
					<c:otherwise>
					   <img src="<c:url value="/static/img/author.png"/>" class="img-circle">
	            </c:otherwise>
	            </c:choose>
               <p style="color:green">
                 ${sessionScope.user.comClientName.firstName} ${sessionScope.user.comClientName.lastName}
                  <small>Member since <fmt:formatDate pattern="MMM yy" value="${ sessionScope.user.getCreatedDate() }" /></small>
                </p>
             
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="<c:url value="/web/taskforce/user/profile" />" class="btn btn-default btn-flat">Profile</a>
                </div>
                <div class="pull-right">
                  <a href="javascript:formSubmit()" class="btn btn-default btn-flat">Sign out</a>
                </div>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </nav>
   <!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-127607784-1"></script>
  </header>
   <c:url var="logoutUrl" value="/logout"/>
   <form action="${logoutUrl}" method="get" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>	
  <div class="modal " id="noticeFormModal" role="dialog"></div> 
  