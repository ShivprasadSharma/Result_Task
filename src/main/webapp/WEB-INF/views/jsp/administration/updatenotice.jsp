<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url var="updateNotice" value="/web/taskforce/service/notification/update" />

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
    	    	document.getElementById("dep").style.display="block";
    	    	document.getElementById("class").style.display="block";
    	    	document.getElementById("studIn").style.display="none";
    	    	document.getElementById("faculty").style.display="none";
    	    	document.getElementById("venue").style.display="none";
    	    	
    	    }else if(select.value == "2"){
    	    	document.getElementById("dep").style.display="block";
    	    	document.getElementById("class").style.display="block";
    	    	document.getElementById("studIn").style.display="none";
    	    	document.getElementById("faculty").style.display="none";
    	    	document.getElementById("venue").style.display="none";
    	    
    	    }else if(select.value == "3"){
    	    	document.getElementById("dep").style.display="block";
    	    	document.getElementById("class").style.display="block";
    	    	document.getElementById("studIn").style.display="none";
    	    	document.getElementById("faculty").style.display="none";
    	    	document.getElementById("venue").style.display="none";
    	    
    	    }else if(select.value == "4"){
    	    	document.getElementById("dep").style.display="block";
    	    	document.getElementById("class").style.display="block";
    	    	document.getElementById("studIn").style.display="block";
    	    	document.getElementById("faculty").style.display="block";
    	    	document.getElementById("venue").style.display="block";
    	    
    	    }else{
    	    	document.getElementById("dep").style.display="none";
    	    	document.getElementById("class").style.display="none";
    	    	document.getElementById("studIn").style.display="none";
    	    	document.getElementById("faculty").style.display="none";
    	    	document.getElementById("venue").style.display="none";
    	    
    	    }
    	  }
   	});
    function check(){
    	var none = document.getElementById("select");
    	if(none.value == "none"){
    		alert("Please Select Notice Type");
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
            $('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
        });
    });
    function getList(){
    	      var x= document.getElementById("department").value;
    	      var y = document.getElementById("cls").value;
    	       $.ajax({
                   type: "POST",
                   url: "/CollegeApp/web/sims/student/listforevent",
                   data:'dep='+ x +'&cls='+ y,
                   dataType: 'json',
                   success: function(data){ 
                	   $('#allUsers').empty();
	        		    	if(data.length > 0){
	        		        $('#allUsers').append("<option value='-1'>Select Student Incharge...</option>");
	        		            for ( var i = 0, len = data.length; i < len; ++i) {
	        		                var user = data[i];
	        		                $('#allUsers').append("<option value=\"" + user.studentId + "\">" + user.comClientName.firstName +" "+ user.comClientName.middleName + " " + user.comClientName.lastName+ "</option>");
	 	        		           }
	        		    	}else{
	        		    		 $('#allUsers').append("<option value='-1'>No result found</option>");
	        		    }
               }
           });
    	 }
  
</script>
<body  class="hold-transition skin-blue sidebar-mini">
<div class="wrapper myStyle" >
<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" >
    <!-- Content Header (Page header) -->
    <section  class="content-header">
       <div class="box-body"><span><font size="5">NOTICE TYPE</font></span>
              <span style="padding-left:30px" > 
                  <select id="select" onselect="show()">
                      <option value="nonne">None</option>
                      <option value="0" ${0 == noticeforupdate.notificationType ? 'selected="selected"' : ''}>All</option>
				      <option value="1" ${1 == noticeforupdate.notificationType ? 'selected="selected"' : ''}>Academic</option>
				      <option value="2" ${2 == noticeforupdate.notificationType ? 'selected="selected"' : ''}>Achivment</option>
			          <option value="3" ${3 == noticeforupdate.notificationType ? 'selected="selected"' : ''}>Anouncement</option>
				      <option value="4" ${4 == noticeforupdate.notificationType ? 'selected="selected"' : ''}>Event</option>
		         </select>
		    </span>
		</div>
	</section> 
    <!-- Main content -->
   <section  class="content" style="padding-top:0px">
   <div class="box box-default" style="margin-left:40px ; margin-right:40px">
        <!-- /.box-header -->
        <div class="box-body" style="background-color:#eff5ec;" >
        <c:set var="deptlist" value="${departments}"/>	
        <form:form   id="notification" modelAttribute="notification" action="${updateNotice}" method="post" class="pure-form pure-form-aligned" role="form" enctype="multipart/form-data">
              <div class="row "  >
		             <div class="col-md-6">
		             <form:hidden path="createdBy" value="${noticeforupdate.createdBy }"/>
		             <form:hidden path="createdDate" value="${noticeforupdate.createdDate }"/>
		             <form:hidden path="groups" value="${noticeforupdate.groups}"/>
		             <form:hidden path="notificationType" id="notificationType" value="${noticeforupdate.notificationType }"/>
		              <c:if test="${noticeforupdate.department != null }">  
		                <div class="form-group" id="dep">
		                  <label>Department</label>
		                  <form:select path="department" class="form-control" onchange="getList()">
						    <c:forEach var="item" items="${deptlist}">
						        <option value="${item.dep_id}" ${item.dep_id == noticeforupdate.department ? 'selected="selected"' : ''}>${item.dep_name}</option>
						    </c:forEach> 
						</form:select>
		                </div>
		             </c:if>
		                  <form:hidden path="notificationId" value="${noticeforupdate.notificationId }" class="form-control"  disabled="disabled"/>
		                <div class="form-group">
		                 <label>Heading</label>
		                   <form:input path="notificatiosHeadline" value="${noticeforupdate.notificatiosHeadline }" class="form-control"  placeholder="Headline ..." onclick="check()" required="required"/>
		                  </div>
		              <div class="form-group">
		                  <label>Description</label>
		                  <form:textarea path="notificationDetails" value="${noticeforupdate.notificationDetails }" class="form-control"  rows="5"  placeholder="Description ..." onclick="check()" required="required" />
		                </div>
		                
		                 <div class="form-group" id="studIn" style="display:none">
		                  <label>Student Incharge</label>
		                  <form:select path="studentIncharge"  id="allUsers" class="form-control select2" data-placeholder="Select Student.." style="width: 100%;">
		                 </form:select>
		                </div>
		            </div>
		            <div class="col-md-6">
		            <c:if test="${noticeforupdate.division != 0 }">
		             <div class="form-group" id="class" >
		                  <label>Class</label>
		                  <div >
		                  <form:select path="division" value="0"  id="cls" class="form-control" onchange="getList()">
		                  		<option value="1" ${1 == noticeforupdate.division ? 'selected="selected"' : '' }>I</option>
		                  		<option value="2" ${2 == noticeforupdate.division ? 'selected="selected"' : '' }>II</option>
		                  		<option value="3" ${3 == noticeforupdate.division ? 'selected="selected"' : '' }>III</option>
		                  		<option value="4" ${4 == noticeforupdate.division ? 'selected="selected"' : '' }>IV</option>
		                  </form:select>
		                  </div>
		                </div>
		              </c:if>
		              <div class="form-group">
		                  <label>Date and Time</label><br>
		                  
		                  <div class="col-md-6"><form:input path="notificationFromDate" value="${noticeforupdate.notificationFromDate}" id="datetimepicker6" class="form-control"   required="required" />
		                 </div>
						  <div class="col-md-6"><form:input path="notificationToDate"  value="${noticeforupdate.notificationToDate}" id="datetimepicker7" class="form-control"  onclick="check()" required="required" />
						  </div>
		                </div><br>
		                <div class="form-group" >
		                  <label style="margin-top:13px">Select Image</label> 
		                  <input type="file" id="notifile1" name="notificationFile" accept="image/*" value=" " class="btn btn-default" onchange="check1()" />
		                   <p style="color:red" id="demo"></p >
		                </div>
		                <c:if test="${noticeforupdate.facultyIncharge != 0  and noticeforupdate.notificationType == 4}">
		                <div class="form-group"  >
		                  <label>Faculty </label>
		                   <c:set var="staffLst" value="${stafflist}"/>	
		                  <form:select path="facultyIncharge" class="form-control select2" data-placeholder="Select Faculty..." style="width: 100%">
			                  <c:forEach var="Sitem" items="${staffLst}">
							        <option value="${Sitem.id}" ${Sitem.id == noticeforupdate.facultyIncharge ? 'selected="selected"' : ''}>${Sitem.firstName} ${Sitem.lastName}</option>
							  </c:forEach> 
		                </form:select>
		                </div>
		                </c:if >
		                 <div class="form-group" id="faculty" style="display:none">
		                  <label>Faculty </label>
		                
		                   <c:set var="staffLst" value="${stafflist}"/>	
		                  <form:select path="facultyIncharge" class="form-control select2" data-placeholder="Select Faculty..." style="width: 100%">
			                  <c:forEach var="Sitem" items="${staffLst}">
							        <option value="${Sitem.id}" ${Sitem.id == noticeforupdate.facultyIncharge ? 'selected="selected"' : ''}>${Sitem.firstName} ${Sitem.lastName}</option>
							  </c:forEach> 
		                </form:select>
		                </div>
		                 <c:if test="${noticeforupdate.venue != 'none'   &&  noticeforupdate.venue != null }">
			                <div class="form-group"  >
			                  <label>Venue</label>
			                  <form:input path="venue" onclick="x()"  class="form-control"  value="${noticeforupdate.venue }" placeholder="Venue Details ..." required="required" />
			                </div>
		                </c:if>
		                <!-- /.form-group -->
		            </div>
		            
				   </div>
				     <div class="form-group" align="right">
				         
                        <input type="submit" id="submitbtn" value="Update" class="btn btn-success" />
				        <input type="Reset" value="Clear" class="btn btn-default" /> 
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

