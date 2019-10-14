<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="com.zertones.model.sims.Staff"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.min.js"></script>
 --><script type="text/javascript" src="//cdn.rawgit.com/MrRio/jsPDF/master/dist/jspdf.min.js">
    </script>
    <script type="text/javascript" src="//cdn.rawgit.com/niklasvh/html2canvas/0.5.0-alpha2/dist/html2canvas.min.js">
    </script>
      
<style type="text/css">


body {font-family: Arial, Helvetica, sans-serif;
     background-color: #d3efc6;
}

.qq{
     background-color: #F4F6F6;
     padding: 7px;
     border: 2px solid #2faf2f;
     border-radius: 4px;
     box-sizing: border-box;
     margin-bottom: 16px;  
}
input[type=time], input[type=date]{
background-color: #F4F6F6;
    padding: 7px;
    border: 2px solid #2faf2f;
    border-radius: 4px;
    box-sizing: border-box;
    margin-top: 6px;
    margin-bottom: 16px;
    resize: vertical;
    height:37px;
    width:150px;
    }
.buttn {
    background-color: #4CAF50;
    border: none;
    color: white;
    padding: 4px 24px;
    border-radius: 1px;
    text-decoration: none;
    font-size: 16px;
    cursor: pointer;
}
.buttn1 {
    background-color: white; 
    color: black; 
    border: 2px solid #4CAF50;
}
.buttn1:hover {
    background-color: #4CAF50;
    color: white;
}
input[type=submit], input[type=reset] {
    background-color: #4CAF50;
    color: white;
    padding: 12px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    margin-left:46px;
    margin-top:30px;
    width:110px;
}
input[type=submit]:hover {
    	background-color: #45a049;
}
.container11 {
		  max-width: 97%;
		  padding: 1em 3em 2em 3em;
		  margin: 0em auto;
		  background-color:  #f1f3f2 ;
		  border-radius: 4.2px;
		  box-shadow: 0px 5px 15px -2px rgba(0, 0, 0, 0.2);
		  margin-left:20px;
		  margin-top:8px;
		  height:66em;
		  
    width: 100%;
    height: 100%;
    overflow: scroll;
}
.container1 {
	  max-width: 35em;
	  padding: 1em 1em 2em 3em;
	  margin: 0em auto;
	  background-color: #fff;
	  border-radius: 4.2px;
	  box-shadow: 0px 3px 10px -2px rgba(0, 0, 0, 0.2);
	  margin-left:39em;
	  margin-top:-59.2em;
	  height:65em;
	}
ixmg {
	  border-radius: 50%;
	}
	

.name {
      text-align: left;
	  font-size: 23px;
	  text-align:left;
	  margin-top: 300px;
	  margin-left:20px;
	  color:green;
}
.mail{
      text-align: left;
	  font-size: 18px;
	  margin-left:35%;
	  color:green;
     

}
	th {
	  text-align: left;
	}

td, th {
	padding: 20px 45px;
}
.head
{
	  width:100%;
	  height: 60px;
	  border-radius: 5px;
	  padding: 2px 12px;
	  text-align: center;
	  background-color: #4CAF50;
	  color: white;
	  margin-top:10px;
}
/*.....................................................................................  */
.image-container
	{
		  margin-top:10px;
	    border-radius: 5px;
	
	padding:10px 10px 10px 10px;
	box-shadow:1px 2px 10px -2px hsla(120,100%,25%);;
}
.txtblur {
    font-weight: 900;
    color:   #80958d  ;
}
#myImg {
    border-radius: 2px;
    cursor: pointer;
    transition: 0.3s;
}
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 12%;
    top: 12%;
    width: auto; /* Full width */
    height: auto; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.5); /* Black w/ opacity */
}
#myImg:hover {opacity: 0.7;}

/* The Modal (background) */


/* Modal Content (image) */
.modal-content {
    margin: auto;
    display: block;
    width: 80%;
    max-width: 700px;
}

/* Caption of Modal Image */
#caption {
    margin: auto;
    display: block;
    width: 80%;
    max-width: 700px;
    text-align: center;
    color: #ccc;
    padding: 10px 0;
    height: 150px;
}

/* Add Animation */
.modal-content, #caption {    
    -webkit-animation-name: zoom;
    -webkit-animation-duration: 0.6s;
    animation-name: zoom;
    animation-duration: 0.6s;
}

@-webkit-keyframes zoom {
    from {-webkit-transform:scale(0)} 
    to {-webkit-transform:scale(1)}
}

@keyframes zoom {
    from {transform:scale(0)} 
    to {transform:scale(1)}
}

/* The Close Button */
.close {
    position: absolute;
    top: 15px;
    right: 35px;
    color: #fff;
    font-size: 80px;
    font-weight: bold;
    transition: 0.3s;
}

.close:hover,
.close:focus {
    color: #bbb;
    text-decoration: none;
    cursor: pointer;
}

/* 100% Image Width on Smaller Screens */
@media only screen and (max-width: 100px){
    .modal-content {
        width: 100%;
    }
}
	 

</style>
</head>
<body class="hold-transition skin-blue sidebar-mini" >
<div class="wrapper myStyle">
  <div class="content-wrapper">
   <section >
   <c:set var="list" value="${feedbackdetails}"/>
   			<div class="container11" id="smdiv">
   			<div class="row">
  			  	 <div class="col-sm-10"> </div>
  			   	<div class="col-sm-2"> <button id="cmd" class="buttn buttn1"><i class="fa fa-download"></i> PDF</button></div><br>
  			    </div>
			  			
			
  			<div   class="row qq" style="background-color:#fff;	box-shadow: 0px 3px 10px -2px rgba(0, 0, 0, 0.2);">
  			 	<div class="col-sm-2">
  						    <div class="image-container">
								<img id="myImg" alt="Student" src="<c:url value="/static/img/pdea_logo.jpg"/>" width="100%" height="18%"  />
			        </div> 
  				    </div>
  				    <div class="col-sm-9">
  				           <span class="name" style=" text-align: center"><b> ${sessionScope.collegeInfo.name} </b></span><br>
  			               <span class="mail">Faculty Feedback Report</span>
  			               <hr>
   			               <span style=" text-align: center"><h4><b>Department of ${list.departmentname} Engg.</b></h4></span>
 		              	   <span  style="text-align:center;color:#5dade2"><h5><b>Academic Year ${list.acadmic_Year}</b></h5></span>
 		            
  					</div>
  			
  			</div>		
  			 <!--..............Image View Code..............  -->
  			 <div id="myModal" class="modal">
                  <span class="close">&times;</span>
                  <img class="modal-content" id="img01">
                   <div id="caption"></div>
            </div>	  
  <!--.............................................  -->
  				
  		
  		<div   class="row qq" style="background-color:#fff;	box-shadow: 0px 3px 10px -2px rgba(0, 0, 0, 0.2);">
  		    <div class="col-sm-12">
  		    
  		     <div class="row">
  			  	 <div class="col-sm-3">
  			   	<h4><b style="text-align:center;color:green">Faculty Name :</b></h4>
  			     </div>
  			      <div class="col-sm-5">
  			   	<h4><b>${list.f_name} ${list.l_name}</b></h4>
  			     </div>
  			      <div class="col-sm-2">
  			   	<h4><b style="text-align:center;color:green">Semester :</b></h4>
  			     </div>
  			     <div class="col-sm-2">
  			  	
  			  	     <c:if test="${list.semester  == 1}">
	  			  	 <h4><b>I</b></h4>
	           		 </c:if>
	    			 <c:if test="${list.semester == 2}">
	        		 <h4><b>II</b></h4>
	        		 </c:if>
	        		 <c:if test="${list.semester == 3}">
	        		 <h4><b>III</b></h4>
	        		 </c:if>
	        		 <c:if test="${list.semester == 4}">
	        		 <h4><b>IV</b></h4>
	        		 </c:if>
	        		 <c:if test="${list.semester == 5}">
	        		 <h4><b>V</b></h4>
	        		 </c:if>
	        		 <c:if test="${list.semester == 6}">
	        		 <h4><b>VI</b></h4>
	        		 </c:if>
	        		 <c:if test="${list.semester == 7}">
	        		 <h4><b>VII</b></h4>
	        		 </c:if>
	        		 <c:if test="${list.semester == 8}">
	        		 <h4><b>VIII</b></h4>
	   				 </c:if>	
  			   	
  			     </div>
  			</div>
  			<div class="row">
  			  	 <div class="col-sm-3">
  			   	<h4><b style="text-align:center;color:green">Subject Name :</b></h4>
  			     </div>
  			      <div class="col-sm-4">
  			   	<h4><b> ${list.subname }</b></h4>
  			   	</div>
  			</div> 
  			
  			<hr>
  			 
  			<div class="row">
  			  	 <div class="col-sm-1">
  			  	 <h4><b style="text-align:center;color: green">Sr.NO</b></h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-7">
  			  	 <h4><b style="text-align:center;color:green">Criteria</b></h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-2">
  			  	 <h4><b style="text-align:center;color:green">vote</b></h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-2">
  			  	 <h4><b style="text-align:center;color:green">Max</b></h4>
  			  	 </div>
  			</div>
  			    
  		<div class="row">
  			  	 <div class="col-sm-1">
  			  	 <h4>1</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-7">
  			  	 <h4>${list.q1}</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-2">
  			  	 <h4>${list.avg1}</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-2">
  			  	 <h4>5.0</h4>
  			  	 </div>
  			</div>
  			
  			<div class="row">
  			  	 <div class="col-sm-1">
  			  	 <h4>2</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-7">
  			  	 <h4>${list.q2}</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-2">
  			  	 <h4>${list.avg2}</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-2">
  			  	 <h4>5.0</h4>
  			  	 </div>
  			</div>
  			
  			<div class="row">
  			  	 <div class="col-sm-1">
  			  	 <h4>3</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-7">
  			  	 <h4>${list.q3}</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-2">
  			  	 <h4>${list.avg3}</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-2">
  			  	 <h4>5.0</h4>
  			  	 </div>
  			</div>
  			
  			
  			<div class="row">
  			  	 <div class="col-sm-1">
  			  	 <h4>4</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-7">
  			  	 <h4>${list.q4}</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-2">
  			  	 <h4>${list.avg4}</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-2">
  			  	 <h4>5.0</h4>
  			  	 </div>
  			</div>
  			
  			<div class="row">
  			  	 <div class="col-sm-1">
  			  	 <h4>5</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-7">
  			  	 <h4>${list.q5}</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-2">
  			  	 <h4>${list.avg5}</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-2">
  			  	 <h4>5.0</h4>
  			  	 </div>
  			</div>
  			
  				
  			<div class="row">
  			  	 <div class="col-sm-1">
  			  	 <h4>6</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-7">
  			  	 <h4>${list.q6}</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-2">
  			  	 <h4>${list.avg6}</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-2">
  			  	 <h4>5.0</h4>
  			  	 </div>
  			</div>
  				
  			<div class="row">
  			  	 <div class="col-sm-1">
  			  	 <h4>7</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-7">
  			  	 <h4>${list.q7}</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-2">
  			  	 <h4>${list.avg7}</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-2">
  			  	 <h4>5.0</h4>
  			  	 </div>
  			</div>
  				
  			<div class="row">
  			  	 <div class="col-sm-1">
  			  	 <h4>8</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-7">
  			  	 <h4>${list.q8}</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-2">
  			  	 <h4>${list.avg8}</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-2">
  			  	 <h4>5.0</h4>
  			  	 </div>
  			</div>
  				
  			<div class="row">
  			  	 <div class="col-sm-1">
  			  	 <h4>9</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-7">
  			  	 <h4>${list.q9}</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-2">
  			  	 <h4>${list.avg9}</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-2">
  			  	 <h4>5.0</h4>
  			  	 </div>
  			</div>
  				
  			<div class="row">
  			  	 <div class="col-sm-1">
  			  	 <h4>10</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-7">
  			  	 <h4>${list.q10}</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-2">
  			  	 <h4>${list.avg10}</h4>
  			  	 </div>
  			  	 
  			  	 <div class="col-sm-2">
  			  	 <h4>5.0</h4>
  			  	 </div>
  			</div>
  			<hr>
  			<div class="row">
  			  	 <div class="col-sm-8">
  			  	 <h4><h4><b style="text-align:center;color:green">Total Score</b></h4></h4>
  			  	 </div>
  				<div class="col-sm-2">
  			  	 <h4><h4><b style="text-align:center;color:green">${list. total_avg_Rate}</b></h4></h4>
  			  	 </div>
  			  	 
  				<div class="col-sm-2">
  			  	 <h4><h4><b style="text-align:center;color:green">5.0</b></h4></h4>
  			  	 </div>
  			</div>
  			
 			  </div>
  			</div>
  		<!-- <div id="editor"></div> -->
			
  		</div>
  		
  		
	</section>
  </div>
  </div>

<script type="text/javascript">

//Get the modal
var modal = document.getElementById('myModal');

//Get the image and insert it inside the modal - use its "alt" text as a caption
var img = document.getElementById('myImg');
var modalImg = document.getElementById("img01");
var captionText = document.getElementById("caption");
img.onclick = function(){
 modal.style.display = "block";
 modalImg.src = this.src;
 captionText.innerHTML = this.alt;
}

//Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

//When the user clicks on <span> (x), close the modal
span.onclick = function() { 
 modal.style.display = "none";
}


	
	function setstaffId(value) {
		 var res = value.substr(0, value.indexOf("."));
		$("#custId").val(res);
		var res1 = value.substr(value.indexOf(".")+1,value.length);
	}
</script>
<script type="text/javascript">
 
    	$('#cmd').click(function () {
    		var name_val = "${list.departmentname}";
    		var empid_val = $('form#smdiv input[name="empid"]').val();
    		var age_val = $('form#smdiv input[name="age"]').val();
    	       		
    		var pdf = new jsPDF();
    		pdf.setFontSize(15);
    		pdf.setTextColor(0, 100, 0);
    		pdf.setFontType('bold')
    		pdf.text(30, 10, '${sessionScope.collegeInfo.name}');
    		pdf.setFontSize(8);
    		pdf.text(83, 18, 'Faculty Feedback Report');
    		pdf.setFontSize(12);
    		pdf.text(60, 24, 'Department of '+ "${list.departmentname}"+' Engineering');
    		pdf.setFontSize(8);
    		pdf.text(85,30,'Academic Year ' +"${list.acadmic_Year}");
    		pdf.setTextColor(0, 0, 0);
    		pdf.setLineWidth(0.5)
    		pdf.line(10, 35, 200, 35)
    		pdf.setFontSize(10);
    		pdf.setFontType('bold')
    		pdf.setTextColor(0, 100, 0);
    		pdf.text(10, 45, 'Faculty Name :');
    		pdf.text(150, 45, 'Semester :');
    		pdf.text(10, 51, 'Subject Name :');
    		pdf.setTextColor(0, 0, 0);
    		pdf.text(55, 45,'' + " ${list.f_name} "+" ${list.l_name} ");
    		pdf.text(175, 45,'' + " ${list.semester}");
    		pdf.text(55, 51,'' + " ${list.subname}");
    		pdf.setTextColor(0, 100, 0);
    		pdf.text(10, 65, 'Sr.No');
    		pdf.text(26, 65, 'Criteria');
    		pdf.text(165, 65, 'Vote');
    		pdf.text(185, 65, 'Max');
    		pdf.setTextColor(0, 0, 0);
    		pdf.setFontType('bold');
    		pdf.setFontSize(8);
    		pdf.text(10, 72, '1');
    		pdf.text(26, 72, ''+"${list.q1}");
    		pdf.text(165, 72, ''+"${list.avg1}");
    		pdf.text(185, 72, '5.0');
    		
    		pdf.text(10, 80, '2');
    		pdf.text(26, 80, ''+"${list.q2}");
    		pdf.text(165, 80, ''+"${list.avg2}");
    		pdf.text(185, 80, '5.0');
    		
    		pdf.text(10, 88, '3');
    		pdf.text(26, 88, ''+"${list.q3}");
    		pdf.text(165, 88, ''+"${list.avg3}");
    		pdf.text(185, 88, '5.0');
    		
    		pdf.text(10, 96, '4');
    		pdf.text(26, 96, ''+"${list.q4}");
    		pdf.text(165, 96, ''+"${list.avg4}");
    		pdf.text(185, 96, '5.0');
    		
    		pdf.text(10, 104, '5');
    		pdf.text(26, 104, ''+"${list.q5}");
    		pdf.text(165, 104, ''+"${list.avg5}");
    		pdf.text(185, 104, '5.0');
    		
    		pdf.text(10, 112, '6');
    		pdf.text(26, 112, ''+"${list.q6}");
    		pdf.text(165, 112, ''+"${list.avg6}");
    		pdf.text(185, 112, '5.0');
    		
    		pdf.text(10, 120, '7');
    		pdf.text(26, 120, ''+"${list.q7}");
    		pdf.text(165, 120, ''+"${list.avg7}");
    		pdf.text(185, 120, '5.0');
    		
    		pdf.text(10, 128, '8');
    		pdf.text(26, 128, ''+"${list.q8}");
    		pdf.text(165, 128, ''+"${list.avg8}");
    		pdf.text(185, 128, '5.0');
    		
    		pdf.text(10, 136, '9');
    		pdf.text(26, 136, ''+"${list.q9}");
    		pdf.text(165, 136, ''+"${list.avg9}");
    		pdf.text(185, 136, '5.0');
    		
    		pdf.text(10, 144, '10');
    		pdf.text(26, 144, ''+"${list.q10}");
    		pdf.text(165, 144, ''+"${list.avg10}");
    		pdf.text(185, 144, '5.0');
    		
    		pdf.setLineWidth(0.1);
    		pdf.line(10, 150, 200, 150);
    		
    		pdf.setFontSize(10);
    		pdf.setFontType('bold')
    		pdf.setTextColor(0, 100, 0);
    		pdf.text(10, 160, 'Total Score :');
    		pdf.text(165, 160, ''+"${list. total_avg_Rate}");
    		pdf.text(185, 160, '5.0');
    		pdf.setLineWidth(0.5);
    		pdf.line(10, 168, 200, 168);
    		pdf.save('Feedback_'+" ${list.f_name}"+'_'+"${list.l_name} "+'.pdf');
    		});
    	
    window.onload = function () { 
	
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
</html>

