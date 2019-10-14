<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="com.zertones.model.sims.Staff"%>

<c:url var="saveNotice" value="/web/taskforce/service/notification/add" />

<c:url var="getNoticeSendBySubordinate" value="/web/taskforce/service/notice/sendbysubordinate" />

 <script type="text/javascript">
    $(function () {
    	var date = new Date(); 
    	var today = new Date(date.getFullYear(), date.getMonth(), date.getDate());
        date.setDate(date.getDate() + 30);     
    	$('#datetimepicker6').datetimepicker({ 
    	        minDate: today
    	 });
    	var select = document.getElementById("select");
    	select.onchange=function(){
    		document.getElementById("notificationType").value = select.value;
    	    if(select.value=="1"){
    	    	
    	    	$("#department").val(1); 

    	    	$(".departId0").hide();
    	    	document.getElementById("dep").style.display="block";
    	    	document.getElementById("class").style.display="none";
    	    	document.getElementById("studIn").style.display="none";
    	    	document.getElementById("faculty").style.display="none";
    	    	document.getElementById("venue").style.display="none";
    	    
    	    	
    	    	
    	    }else if(select.value == "2"){
    	    	document.getElementById("dep").style.display="none";
    	    	document.getElementById("class").style.display="none";
    	    	document.getElementById("studIn").style.display="none";
    	    	document.getElementById("faculty").style.display="none";
    	    	document.getElementById("venue").style.display="none";
    	    	$("#department").val(0); 
    	    
    	    }else if(select.vahidelue == "3"){
    	    	document.getElementById("dep").style.display="none";
    	    	document.getElementById("class").style.display="none";
    	    	document.getElementById("studIn").style.display="none";
    	    	document.getElementById("faculty").style.display="none";
    	    	document.getElementById("venue").style.display="none";
    	    	$("#department").val(0); 
    	    }else if(select.value == "4"){
    	    	document.getElementById("dep").style.display="none";
    	    	document.getElementById("class").style.display="none";
    	    //	document.getElementById("studIn").style.display="block";
    	    	document.getElementById("faculty").style.display="none";
    	    	document.getElementById("venue").style.display="block";
    	    	$("#department").val(0); 
    	    }else{
    	    	document.getElementById("dep").style.display="none";
    	    	document.getElementById("class").style.display="none";
    	    	document.getElementById("studIn").style.display="none";
    	    	document.getElementById("faculty").style.display="none";
    	    	document.getElementById("venue").style.display="none";
    	    	$("#department").val(0);  

    	    }
    	  }
    	
    	/* var selectdep = document.getElementById("select_event_type");
    	selectdep.onchange=function(){
    		if(selectdep.value=="1"){
    	    	  document.getElementById("cls").style.display="block";
    		}else{
    		  document.getElementById("cls1").style.display="block";
    		  document.getElementById("cls").style.display="none";
    		}
    	}  */
});
    
    
    function check()
    {
    	var none = document.getElementById("select");
    	if (none.value=="none")
    		{
    		alert("please select Notice Type");
    		}
   
    }  
    
    function check1()
    {
    	 var x, text;
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
	
</script> 
<script type="text/javascript">
    $(function () {
    	 $(".select2").select2();
        $('#datetimepicker6').datetimepicker();
        
        $('#datetimepicker7').datetimepicker({
            useCurrent: false //Important! See issue #1075
        });
        $("#datetimepicker6").on("dp.change", function (e) {
            $('#datetimepicker7').data("DateTimePicker").minDate(e.date);
        });
        $("#datetimepicker7").on("dp.change", function (e) {
            $('#datetimepicker6countryList').data("DateTimePicker").maxDate(e.date);
        });
    });
    
 
</script>
<script>

function getList(){
	var x= document.getElementById("department").value;
    var y = document.getElementById("cls1").value;
    $.ajax({
         type: "POST",
         url: "/"+location.pathname.split("/")[1]+"/web/sims/student/listforevent",
         data:'dep='+ x +'&cls='+ y,
         dataType: 'json',
         success: function(data){ 
        	 if(data.length > 0){
 		        $('#allUsers').append("<option value='-1'>Select Student Incharge...</option>");
 		            for ( var i = 0, len = data.length; i < len; ++i) {
 		                var user = data[i];
 		                $('#allUsers').append("<option value=\"" + user.studentId + "\">" + user.comClientName.firstName +" "+ user.comClientName.middleName + " " + user.comClientName.lastName+ "</option>");
 		            } hide
            }
         },
	     error:function(data){
	    	    alert("error");
	     }
    });
}

</script>
<body  class="hold-transition skin-blue fixed sidebar-mini">
<div class="wrapper " style="padding-top: 31px">
<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" >
    <!-- Content Header (Page header) -->
    <section  class="content-header">
       <div class="box-body">
              <span class="pull-left" ><font size="5">NOTICE TYPE</font></span>
              <span style="padding-left:30px" class="col-md-3" > 
                     <select id="select" class="form-control"> 
                             <option value="none">None</option>
                             <option value="0">All</option>
							 <option value="1">Department</option>
							 <option value="2">Awards & Achievement</option>
							 <option value="3">Circulars</option>
							 <option value="4">Event</option>
					 </select>
		    </span>
	   </div>
   </section> 
   <!-- Main content -->
   <section>
     <div class="box box-default" style="margin-left:40px ; margin-right:40px">
        <!-- /.box-header -->
        <div class="box-body " style="background-color:#eff5ec;" >
        <c:set var="deptlist" value="${departments}"/>	
        <form:form   id="notification" modelAttribute="notification" action="${saveNotice}" method="post" class="pure-form pure-form-aligned" role="form" enctype="multipart/form-data">
              <div class="row "  >
		             <div class="col-md-6">
		             <form:hidden path="notificationType"  id="notificationType"  />
		             <div class="form-group" id="dep" style="display:none">
		                  <label>Department</label>
		                  <c:set var="userdep" value="${sessionScope.user.department}"/>
		                  <form:select path="department" id="department" class="form-control" onchange="getList()" required="required">
						    <option class="departId0" value="0" >ALL</option>
						   
						    <c:forEach var="item" items="${deptlist}">
						       <c:if test="${item.dep_id eq 0 || item.dep_id eq userdep }">
						        <option class="departId${item.dep_id}" value="${item.dep_id}" ${item.dep_id == selectedDept ? 'selected="selected"' : ''}>${item.dep_name}</option>
						       </c:if>
						    </c:forEach> 
						</form:select>
		                </div>
		                <div class="form-group">
		                 <label>Heading</label>
		                   <form:input path="notificatiosHeadline" class="form-control"  placeholder="Headline ..." onclick="check()" required="required"/>
		                  </div>
		              <div class="form-group">
		                  <label>Description</label>
		                  <form:textarea path="notificationDetails" class="form-control" maxlength="250" onpaste="return true;" rows="5"  placeholder="Description ...0 to 250 Characters" onclick="check()" required="required" />
		                </div>
		                 <div class="form-group" id="studIn" style="display:none">
		                  <label>Student Incharge</label>
		                  <form:select path="studentIncharge" id="allUsers"   class="form-control select2" data-placeholder="Select Student.." style="width: 100%;">
			             </form:select>
			            </div>
		            </div>
		            <div class="col-md-6">
		             <div class="form-group" id="class" style="display:none">
		                  <label>Year</label>
		                  <div >
		                  <%-- <form:select path="division" value="0"  onchange="getList()" id="cls" class="form-control">
		                        <option value="0" >All</option>
		                  </form:select> --%>
		                   <form:select path="division" value="0" onchange="getList()" id="cls1" class="form-control" required="required">
		                        <option value="0">All</option>
<!-- 		                       <option value="1">FE</option>
 -->		                    <option value="2">SE</option>
		                  		<option value="3">TE</option>
		                  		<option value="4">BE</option>
		                  </form:select>
		                  </div>
		                </div>
		                <div class="form-group">
		                  <label>Date and Time</label><br>
		                  <div class="col-md-6"><form:input path="notificationFromDate" id="datetimepicker6" class="form-control"  placeholder="From..." onclick="check()" required="required" />
		                 </div>
						  <div class="col-md-6"><form:input path="notificationToDate" id="datetimepicker7" class="form-control"  placeholder="To ..." onclick="check()" required="required" />
						  </div>
		                </div><br>
		                <div class="form-group" >
		                  <label style="margin-top:13px">Select Image</label>
		                  <input type="file" id="notifile1" name="notificationFile" accept="image/*,application/msword, application/vnd.ms-excel, application/vnd.ms-powerpoint,text/plain, application/pdf" value=" Upload Media" class="btn btn-default" onchange="check1()"/> 
		                    <p style="color:red", id="demo"></p>
		                 </div>
		               <div class="form-group" id="faculty" style="display:hidden">
		                  <label>Faculty </label>
		                   <c:set var="staffLst" value="${stafflist}"/>	
		                  <form:select path="facultyIncharge" class="form-control select2" data-placeholder="Select Faculty..." style="width: 100%">
			                  <c:forEach var="Sitem" items="${staffLst}">
							        <option value="${Sitem.id}" ${Sitem.id == selectedDept ? 'selected="selected"' : ''}>${Sitem.firstName} ${Sitem.lastName}</option>
							  </c:forEach> 
		                </form:select>
		                </div>
		                <div class="form-group" id="venue" style="display:none">
		                  <label>Venue</label>
		                  <form:input path="venue" class="form-control"  value="none" placeholder="Venue Details ..."  />
		                </div>
						<!-- /.form-group -->
		            </div>
				   </div>
				   <div align="right">
				        <input type="submit" id="submitbtn" value="Send" class="btn btn-success" />
				        <input type="button"  value="cancel" onclick="window.location.href='<c:url value="/web/taskforce/service/notification/list"/>' "/>
			 </div>
			</form:form>
         </div>
        </div>
	    <div  style="background-color:#e6e6e6;  margin-left:40px; margin-right:40px; height:130px;overflow-x:hidden " >
		          <table class="table table-striped table-bordered">
					<thead>
						<tr>
						   <th>Sr #</th>
						   <th>Sender Name</th>
						   <th>Head Line</th>
						   <th>Description</th>
						   <th>Date</th>
						   <th>Type</th>
						   <th>Action</th>
						</tr>
					</thead>
					<tbody>
					<c:set var="no" value="0" />
					  <c:forEach var="notice" items="${noticesSendByTecherList}">
					<tr >
					  <td>${no= (no + 1)}</td>
					  <td>${notice.postNotice}</td>
					  <td>${notice.notificatiosHeadline}</td>
					  <c:set var="msg" value="${notice.notificationDetails}"/>
					  <td>${fn:substring(msg,0,40)}</td>
					  <td><fmt:formatDate pattern="dd MMM yyyy" value="${notice.notificationFromDate}" /></td>
					  <td> 
					     <c:forEach var="group" items="${group}">
							<c:if test="${notice.groups == group.groupId}">${group.groupName} </c:if>
						</c:forEach> 
						<c:if test="${notice.notificationType == 0 }">All</c:if>
						<c:if test="${notice.notificationType == 1 }">Academic</c:if>
						<c:if test="${notice.notificationType == 2 }">Awards & Achivement</c:if>
						<c:if test="${notice.notificationType == 3 }">Circulars</c:if>
					    <c:if test="${notice.notificationType == 4 }">Event</c:if>
						  
					  </td>
					  <td>
						 <a href="<c:url value='/web/taskforce/service/notification/delete/${notice.notificationId}'/>" onclick="return confirm('Are You Sure You Want To Delete This Notification ?')"><i class="fa fa-fw fa-trash"></i></a> &nbsp; 
					     <a href="<c:url value='/web/taskforce/service/notifications/update/${notice.notificationId}'/>" ><i class="fa fa-fw fa-pencil"></i></a>
					  </td>
					</tr>
			      </c:forEach>
			   </tbody>
		    </table>
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

