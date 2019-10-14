<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url var="savesubject" value="/web/taskforce/Adding_Teacher_Subject_list" />
<c:url var="deletesubject" value="/web/taskforce/service/delete/subject" />
<head>
<style>

/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	margin-left: 25%;
	width: 60%;
}

/* The Close Button */
.close {
	color: #aaaaaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}
.closegraph {
	color: #aaaaaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.closegraph:hover, .closegraph:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}
</style>

</head>
<body class="hold-transition skin-blue fixed sidebar-mini" >
<script>
$(function () {
	$(".select2").select2();
 });
function myFunction() {
	
    var x = document.getElementById("myDate").value;
    document.getElementById("demo").innerHTML = x;
}
$('#date').datepicker({ dateFormat: 'yyyy-mm-dd' }).val();
</script>
<div class="wrapper" >
	<div class="content-wrapper">
	 <section  class="content-header">
	       <div class="box-body">
	              <h3><b>Teacher & Subject Details.</b></h3>
		   </div>
	   </section> 	 
 	<c:set var="list" value="${stafflist}"/>
 	<c:set var="list1" value="${subjectlist}"/>
 	<div class="box box-default"  style="margin-left:10px ; margin-right:10px">
      <div class="box-body ">
       <form:form action="${savesubject}" method="post" modelAttribute="hodform">
          
        <%--     <div>
               <div align="center" >
	              <label>Department :</label>	               
			      <select style="width:300px;" name="depid" id="select"class="form-control">
			       <option value="#">Select Department</option>
					 <c:forEach var="dep" items="${deplist}" >	
					    <c:if test="${dep.dep_id > 0 }">					
					      <option value="${dep.dep_id} ">${dep.dep_name} </option>
					    </c:if>
					 </c:forEach>
				  </select>				
			  </div>
			</div>
          --%>
           <div class="row">
  		       <div class="col-md-6">
		         <label>Teacher List :</label> 
		            <select name="client_id" id="select" class="form-control select2" data-placeholder="Select Teacher...">
				       <c:forEach var="x" items="${list}" >						
						  <option value="${x.id} ">${x.firstName} ${x.middleName} ${x.lastName} </option>
					   </c:forEach>
					</select>
		       </div>
		       <div class="col-sm-6"><label>Subject List : </label>
 		         	<select name="subject_id" id="subjectlist" multiple="multiple" class="form-control select2" data-placeholder="Select Subject..."  required="required">
					  <c:forEach var="sub" items="${subjectList}" >						
						  <option value="${sub.sub_id} ">${sub.subject_name} (${sub.subject_code})</option>
					   </c:forEach>  
				    </select> 
		         </div>
		 </div>
		 <div class="row" >
		     <div align="center" style="padding-top: 20px">
		    	      <input type="submit" onclick="return Validate()" value="Submit" class="btn btn-success" />
		      	 		     </div>
		  </div>	 
		  
		  <div> 
		  <button  onclick="deleteSubject()" class="btn btn-danger">Delete </button>
		  
		  
		  </div>
		  
		 
		</form:form> 
		
		<div id="mygraph" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <span class="closegraph">&times;</span>
    <h1>Delete Assigned Subject</h1>
  
<h3 id="graphquesion"></h3>
<div class="container createpolls" id="progressBar" style="margin-top: 1%; width: 100%; background-color: white;">
						
						<form action="${deletesubject}" method="post">
	 <div class="row">
  		       <div class="col-sm-12">
		         <label>Select Teacher :</label> 
		            <select  name="clientId" id="clientIDdelete" onchange="deletefun(this.value)" class="form-control select2" data-placeholder="Select Teacher..." style="width: 60%">
		            <option value=""> </option>
				       <c:forEach var="x" items="${list}" >						
						  <option value="${x.id} ">${x.firstName} ${x.middleName} ${x.lastName} </option>
					   </c:forEach>
					</select>
		       </div>
		         
		      
		 </div>
		 <br>
		  <div class="row">
  		      
		         <div class="col-sm-12">
		         <label>Select Subject :</label> 
		            <select name="subjectId" id="subjectListDelete" class="form-control select2" data-placeholder="Select Teacher..." style="width: 60%">
				      
					</select>
		       </div>
		      
		 </div>
		 <div class="row" >
		     <div align="center" style="padding-top: 20px">
		    	      <input type="submit" onclick="return Validate()" value="Delete" class="btn btn-danger" />
		     </div>
		  </div>
		  </form>		 						
							
							
  </div>

</div>
</div>
	  </div>
	</div> 
  </div>       
 <!-- /Main Content -->
</div>

<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-127607784-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-127607784-1');

	// Get the modal
	var modal = document.getElementById('myModal');
	
	var graphid = document.getElementById('mygraph');

	// Get the button that opens the modal
	var btn = document.getElementById("myBtn");

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];
	var closegraph = document.getElementsByClassName("closegraph")[0];

	// When the user clicks the button, open the modal 

	
	function deleteSubject() {
		//alert("hiiiiiiiiii");
		graphid.style.display = "block";
		
	}
	
	//............................................................................................................
		// When the user clicks on <span> (x), close the modal
		
		closegraph.onclick = function() {
			graphid.style.display = "none";
			
		}

	// When the user clicks anywhere outside of the modal, close it	
	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
			
		}
		if (event.target == graphid) {
			graphid.style.display = "none";
			
		}
	}

	
	function deletefun(id) {
		//alert(id);

		var myvar ="";
			

		$.ajax({
	         type: "POST",
	         url: "/"+location.pathname.split("/")[1]+"/web/taskforce/service/assign/subjectList",
	         data:'id='+ id,
	         dataType: 'json',
	         success: function(data){ 
	        	 for (var k in data){
	        		    if (data.hasOwnProperty(k)) {
	        		          myvar+='<option value="'+k+'">'+data[k]+'</option>';
	        		    }
	        		}
	        	 $("#subjectListDelete").html(myvar);
	         },
		     error:function(data){
		    	  
		     }
	    });
	}
	
	/* $("#clientIDdelete").change(function(){
		alert("call fun");
	var	clientIDs=$("#clientIDdelete").val();
	alert("call fun");
	 $.ajax({
         type: "POST",
         url: "/"+location.pathname.split("/")[1]+"/web/taskforce/service/assign/subjectList",
         data:'id='+ clientIDs,
         dataType: 'json',
         success: function(data){ 
        	 alert(data);
         },
	     error:function(data){
	    	    alert("error");
	     }
    });
			 
		 

	    });  */
  
</script>
</body>
</html>