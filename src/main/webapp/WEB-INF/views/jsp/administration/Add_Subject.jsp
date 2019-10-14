<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--   <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 -->  
<c:url var="insertSubject" value="/web/taskforce/serives/Insert_Subject" />
<body class="hold-transition skin-blue sidebar-mini" >
<div class="wrapper ">
 <div class="content-wrapper " >
 <c:set var="deptlist"  value="${departments}"/>
  <c:set var="sublist"  value="${subjectlist}"/>
   <form:form action="${insertSubject}" method="post" modelAttribute="AddSubform">
    <!-- Main content -->
   		 <section class="content " >
   			<div class="container">
			<hr>
  			<div class="head">
  				<h4><b>Add Subjects.</b></h4>
  			 </div>
  					<hr>
  						<div class="row">
  						    <div class="col-sm-1"></div>
    						<div class="col-sm-5"><b>*Department Name :</b></div>
    						<div class="col-sm-5"><b>*Semester :</b></div>
  						</div>
  						
   						<div class="row">
	   						<div class="col-sm-1"></div>
	   						<div class="col-sm-5">
		       				    <select  id="dept_id" name="department"  onchange="getsub()">
		       				    <option value="">Select Department</option>
           							<c:forEach var="x" items="${deptlist}">
           							  <c:if test="${x.dep_id gt 0 }">
               							<option value="${x.dep_id}">${x.dep_name}</option>
               						  </c:if>	
           							</c:forEach>
           						</select>
           					</div>
		       				<div class="col-sm-5">
		       					<select name="semister" onchange="getsub()" id="sem_id" >
               							<option value="">select semester</option>
               							<option value="1" id="firstSem" style="display: none">Semester : I</option>
               							<option value="2" id="firstSem1" style="display: none">Semester : II</option>
               							<option value="3">Semester : III</option>
               							<option value="4">Semester : IV</option>
               							<option value="5">Semester : V</option>
               							<option value="6">Semester : VI</option>
               							<option value="7">Semester : VII</option>
               							<option value="8">Semester : VIII</option>
           						</select>
           					</div>
		       			</div>
		       				  <div class="row">
		       				  <div class="col-sm-12"><br></div>  
		       				  </div>
		   				<div class="row">
		   				 	<div class="col-sm-1"></div>
		         			<div class="col-sm-5"><b>Subject Name :</b></div>	         			
		         		</div>
		    			<div class="row">
		    			 	<div class="col-sm-1"></div>
		    			    <div class="col-sm-5">
                            	<div class="multiselect">
   								<div class="selectBox" onclick="showCheckboxes()">
          			      	<select>
					        	<option>Select an option</option>
					      	</select>
					      <div class="overSelect"></div>
					    </div>
					     	<div id="allSubject" style="overflow-y:scroll; overflow-x:hidden; height:150px; background-color:#f2f2f2;">
					       </div>
					  	  </div>
           			</div>		    			 
			        	<div class="col-sm-6">
		    			<div class="Submitbtn">
			     			<input type="submit" onclick="return Validate()" value="Submit">
			      			<input type="reset" name="resetBtn" value="Clear" class="" > 
			      			</div>
			 			</div>
			 			</div>
			 		<hr>
				</div>
		</section>  
	</form:form>  
</div>       
 <!-- /Main Content -->
</div>
<script>
function getsub()
{
	var dept = document.getElementById("dept_id").value;
	var sem = document.getElementById("sem_id").value;
	if(dept == 1){
		document.getElementById("firstSem").style.display = "block";
		document.getElementById("firstSem1").style.display = "block";
	}else{
		document.getElementById("firstSem").style.display = "none";
		document.getElementById("firstSem1").style.display = "none";
	}
	$.ajax({
        type: "POST",
        url: "/"+location.pathname.split("/")[1]+"/web/sims/subject/getsubject",
        data:'dep='+ dept +'&sem='+sem,
        dataType: 'json',
        success: function(data){
        	var checkboxlist="";      		
        if(data.length > 0)
        	{       
	        	for(i=0;i < data.length;i++)
	        	{
	        		var sub = data[i];
		              checkboxlist=checkboxlist+"<label for=\""+i+"\"><input type=\"checkbox\"name=\"subjectname\" value=\""+sub.sub_id+"\" id=\"one\" /> &nbsp;"+ sub.subjectname+"&nbsp;("+sub.subjectcode+")</label><br/>";
	        	}
        		$('#allSubject').html(checkboxlist);
        }else{
        	
            $('#allSubject').html(checkboxlist);
        }
      },
	     error:function(data){
	    	    alert("error");
	     }
       });
	}

function myFunction() {
    var x = document.getElementById("myDate").value;
    document.getElementById("demo").innerHTML = x;
}
//$('#date').datepicker({ dateFormat: 'yyyy-mm-dd' }).val();

var expanded = false;

function showCheckboxes() {
  var checkboxes = document.getElementById("checkboxes");
  if (!expanded) {
    checkboxes.style.display = "block";
    expanded = true;
  } else {
    checkboxes.style.display = "none";
    expanded = false;
  }
}
</script>
<script type="text/javascript">
   /*  $(function () {
        $('#lstFruits').multiselect({
            includeSelectAllOption: true
        });
    }); */
</script>
<script type="text/javascript">
function depcheck()
    {	
	
    	var none =$("#dept_id").val();

    	if (none=="")
    		{
    		alert("please select Department");
    		
    		}
    }  
</script>
<style>
body {font-family: Arial, Helvetica, sans-serif;
      background-color: #d3efc6;
}

input[type=text],input[type=email], input[type=date],input[type=number],select, textarea {
      width: 100%;
      padding: 5px;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
      margin-top: 5px;
      margin-bottom: 14px;
      resize: vertical;
      background-color: #f2f2f2;
}
.Submitbtn{
      background-color: #FFF;
  	  color: white;
	  margin-left: 31%;
	  margin-top: 33%;

}
input[type=submit],input[type=reset]{
      background-color: #45a049;
    
}
.container {
	  max-width: 100%;
	  padding: 1em 3em 2em 3em;
	  margin: 0em auto;
	  font-size: 20px;
	  background-color: #fff;
	  border-radius: 4.2px;
	  box-shadow: 0px 5px 15px -2px rgba(0, 0, 0, 0.2);
	  margin-left:10px;
	  margin-top:8px;
}

ixmg {
	  border-radius: 50%;
	}
	th {
	  text-align: left;
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
.row{
      font-size: 13px;
	  padding: 0px 25px;
}
.left {
	  position: absolute;
	  top: 0;
	  left:20px;
	  box-sizing: border-box;
	  padding: 40px;
	  width: 500px;
	  height: 400px;
}
.right {
	  position: absolute;
	  top: 0;
	  right: 0;
	  box-sizing: border-box;
	  padding: 40px;
	  width: 300px;
	  height: 400px;
	  background: url('https://goo.gl/YbktSj');
	  background-size: cover;
	  background-position: center;
	  border-radius: 0 2px 2px 0;
	}
.head
{
	  width: 98%;
	  height: 35px;
	  border-radius: 5px;
	  box-shadow: 0px 6px 10px -2px rgba(0, 0, 0, 0.6);
	  padding: 1px 10px;
	  text-align: center;
	  background-color: #4CAF50;
	  color: white;
	  margin-top:10px;
}

.selectBox {
  position: relative;
}

.selectBox select {
  width: 100%;
  font-weight: bold;
}

.overSelect {
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
}

#checkboxes {
  display: none;
  border: 1px #dadada solid;
}

#checkboxes label {
  display:inline-table;
  background-color: red;
  
}

#checkboxes label:hover {
  background-color: #1e90ff;
}
</style>
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-127607784-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-127607784-1');
</script>
</body>

