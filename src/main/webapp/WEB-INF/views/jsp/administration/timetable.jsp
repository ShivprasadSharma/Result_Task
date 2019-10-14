<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="saveTimeTable" value="/web/taskforce/timeTable/insert" />

<style>
label{
   margin-top: 10px;
   margin-bottom: 10px;
   color: green;
}
</style>
<body  class="hold-transition skin-blue fixed sidebar-mini">
<div class="wrapper" >
<div class="content-wrapper">
<section  class="content-header">
       <div class="box-body">
              <h3><b>Create Time Table.</b></h3>
	   </div>
   </section> 
  <section>
   <div class="box box-default"  style="margin-left:10px ; margin-right:10px">
      <div class="box-body ">
  	   <form:form action="${saveTimeTable}" method="POST" modelAttribute="setTimeTable" commandName="setTimeTable">
  		  <div class="row">
  		    <div class="col-md-6">
  		    <label>Day :</label> 
			        <form:select  path="days.day_vid" required="required" class="form-control">
					     <c:forEach var="day" items="${daylist}">
							  	<option class="yearoption" value="${day.day_vid}" ${day.day_vid eq timeTableResponse.days.day_vid ? 'selected="selected"' : ''}>${day.day_name} </option>
						</c:forEach> 
					</form:select>  
  		        
  		           <label> Year :</label>
  		            <form:select id="acYear" class="form-control" path="academicYear" required="required">
				      	<option class="yearoption" value="">Year</option>
						<option class="yearoption" value="1" >FE</option>	
						<option class="yearoption" value="2" >SE</option>	
						<option class="yearoption" value="3" >TE</option>	
						<option class="yearoption" value="4" >BE</option>						       						   
				    </form:select>  				    
			 <label>Division :</label>
					<form:select path="division" id="divID" required="required" class="form-control">
						 <option class="yearoption" value=""> Division</option>
						 <option class="yearoption" value="A" ${'A' eq timeTableResponse.division ? 'selected="selected"' : ''}>Div-A</option>
						 <option class="yearoption" value="B" ${'B' eq timeTableResponse.division ? 'selected="selected"' : ''}>Div-B</option>
					     <option class="yearoption" value="C" ${'C' eq timeTableResponse.division ? 'selected="selected"' : ''}>Div-C</option>
						 <option class="yearoption" value="D" ${'D' eq timeTableResponse.division ? 'selected="selected"' : ''}>Div-D</option>
									</form:select>
					<form:hidden path="courseMaster.courseId" value="1"/>
					
			   <label>Subject :</label>
				  <form:select id="subject" class="form-control" path="academicSubject.sub_id" onclick="checksub(this.value)" onchange="Batchlist(this.value)">
				  </form:select>    
					 <label>Type :</label>		
				<label><form:radiobutton path="TheoryorPractical" value="Theory" onclick="batch(this.value)" required="required" /> Theory.
					<form:radiobutton path="TheoryorPractical" value="practical" onclick="batch(this.value)" required="required" style="margin-left:10px;font-size:20px" />
                 Practical. <form:radiobutton path="TheoryorPractical" value="Tutorial" onclick="batch(this.value)" required="required" style="margin-left:10px;font-size:20px" />
                 Tutorial. </label>   
		    </div>
    			<div class="col-md-6"> 
    			<%-- <label>End Time : </label>
			 		<form:input type='time' path="periodEndTime" class="form-control" value='${timeTableResponse.periodEndTime}' required="required"/> 
			 --%>
			 <label>Lecture No : </label>
			    <form:select class="form-control" path="lectures.lecture_no" required="required">
  		            <c:forEach var="lecture" items="${lecture}">
					    <option class="yearoption" value="${lecture.lecture_no}" >${lecture.lecture_no} (${lecture.start_time} - ${lecture.end_time}) </option>
				    </c:forEach> 
  		        </form:select>
			 <label>Department :</label>
		         <form:select id="Department" class="form-control" path="department.dep_id" required="required">
					<option class="yearoption" value="">Deparment</option>
					   <c:forEach var="item" items="${departments}">
                           <c:if test="${item.dep_id ne 0}">
				               <option class="yearoption" value="${item.dep_id}" ${item.dep_id eq timeTableResponse.department.dep_id ? 'selected="selected"' : ''}>${item.dep_name}</option>
			               </c:if>
		                </c:forEach>
			      </form:select> 
		    <label> Semester :</label>
				 <form:select id="sem" onchange="subjectlist(this.value)" path="academicSemester.semesterId" required="required" class="form-control">
					  <option class="yearoption" value="">Semester</option>
						<c:forEach var="semList"  items="${Semisterlist}">
						   <option class="yearoption" value="${semList.semesterId}" ${semList.semesterId eq timeTableResponse.academicSemester.semesterId ? 'selected="selected"' : ''}>Semister-${semList.semesterId}</option>
					    </c:forEach>
				</form:select> 
		    <label> Teacher:</label>
				<form:input path="" id="techerName" class="form-control"/>
				<form:hidden path="comClientName.id" id="custId" />
				<input type="hidden" id="subjectValue" value="${timeTableResponse.academicSubject.sub_id}"/>
				<div></div>		  	
		   <BR>
					<form:select path="batch_name" style="display: none" id="batchId" class="form-control" />
			</div>
	   </div>
		<div align="center" style="padding-top: 20px">
		    <input type="submit" value="Submit" class="btn btn-success" />
         </div>
     </form:form>	
   </div>			  
  </div>
  </section>
  </div>
</div>
<script type="text/javascript">
$(function(){  

	  $('input[type="time"][value="now"]').each(function(){    
	    var d = new Date(),        
	        h = d.getHours(),
	        m = d.getMinutes();
	    if(h < 10) h = '0' + h; 
	    if(m < 10) m = '0' + m; 
	    $(this).attr({
	      'value': h + ':' + m
	    });
	  });
	});
	function subjectlist(semId) {		
		var depId=$("#Department").val();		
		  $.ajax({
	         type: "POST",
	         url: "/"+location.pathname.split("/")[1]+"/web/taskforce/subject/getAcademicSubject",
	         data:'depId='+ depId +'&semId='+ semId,
	         dataType: 'json',
	         success: function(data){ 
	        	  if(data.length > 0){	 
	        		   var list="<option class=\"yearoption\" value=\" \" >Subject</option>";  
	 		             for( var i = 0; i < data.length; i++ ) {
	 		                  var obj = data[ i ];
	                         list=list+"<option class=\"yearoption\" value="+obj.sub_id+" >"+obj.subject_name+"</option>";
	 		              }
	 		        $("#subject").html(list);
	        	     }
	         },
	    	     error:function(data){
	    	    	    alert("error subject");
	    	     }
	      }); 		
	}
	
	function setstaffId(value) {
		 var res = value.substr(0, value.indexOf("."));
		$("#custId").val(res);
		var res1 = value.substr(value.indexOf(".")+1,value.length);
	}
</script>
<script type="text/javascript">
    window.onload = function () { 
    	var sem=$("#sem").val();
    	var sub =$("#subjectValue").val();
    	if(sem != 0){
    	 subjectlist(sem);
    	 Batchlist(sub)
    	}
    	var id="${depId}";
    	if(id!="")
    	{
    		$("#Department").val("${depId}");
        	$("#acYear").val("${semId}");
        	$("#divID").val("${devisionID}");
    	}
    		
    	
    }
    function checksub(val) {
    	
    	
    	 if(val=="")
    		{
    		alert("Please Select Department and Semister");
    		}
		
	}
    function batch(val) {
    	if(val=="practical")
    		$('#batchId').show();
    	else{
    		$('#batchId').hide();
    		$("#batchId").val("0");
    	}
    		
	}
    
    function Batchlist(SubId) {
     	$.ajax({
	         type: "POST",
	         url: "/"+location.pathname.split("/")[1]+"/web/sims/timeTable/getBatchlist",
	         data:'SubId='+ SubId,
	         dataType: 'json',
	         success: function(data){ 
	        	 var BList="";
	        	  if(data.length > 0){	        	  						
	        		  BList="<option value=\"0\"> Batch</option>";
	        		  for ( var i = 0, len = data.length; i < len; ++i) {
	 		                var user = data[i];
	 		               BList=BList+"<option value=\"" + user.batch_name + "\"> Batch-" +user.batch_name + "</option>";
	 		        } 
	        		  $("#batchId").html(BList);
	        	   }
	         },
	    	     error:function(data){
	    	    	    alert("error");
	    	     }
	        }); 
		
		 $.ajax({
	         type: "POST",
	         url: "/"+location.pathname.split("/")[1]+"/web/sims/timeTable/getsubjectTeacherlist",
	         data:'SubId='+ SubId,
	         dataType: 'json',
	         success: function(data){ 	       
	        	 if(data!=null)
	        		{
	        		   var id= data.substr(0,data.indexOf("."));
	        		   var name=data.substr(data.indexOf(".")+1,data.length);
	        		   $("#techerName").val(name);
	        		   $("#custId").val(id);	        		   
	        		}
	         },
	    	     error:function(data){
	    	    	    alert("error");
	    	     }
	     });
    }
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
