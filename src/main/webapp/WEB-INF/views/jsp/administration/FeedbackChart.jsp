<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="com.zertones.model.sims.Staff"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
   <section>
   			<div class="container11">
			<c:set var="list" value="${feedbackdetails}"/>
  			<div class="row qq" style="background-color:#fff;	box-shadow: 0px 3px 10px -2px rgba(0, 0, 0, 0.2);">
  			 		<div class="col-sm-2">
  						    <div class="image-container">
								<img id="myImg" alt="Student" src="<c:url value="/static/img/pdea_logo.jpg"/>" width="100%" height="18%"  />
			        </div>
  				    </div>
  				    <div class="col-sm-9">
  				           <span class="name" style=" text-align: center"><b> ${sessionScope.collegeInfo.name}</b></span><br>
  			               <span class="mail">Faculty Feedback Report</span>
  			               <hr>
   			               <span style=" text-align: center"><h4><b>Department of ${list.departmentname} Engg.</b></h4></span>
 		               <span  style="text-align:center;color:#5dade2"><h5><b>Academic Year ${list.acadmic_Year}</b></h5></span>
 		            
  					</div>
  			
  			</div>	
			<div class="row qq" style="background-color:#fff;	box-shadow: 0px 3px 10px -2px rgba(0, 0, 0, 0.2);">
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
  			
		
            <div id="chartContainer" style=" margin-left:5%;height: 400px; width: 90%;">
            </div>

			<div class="row">
  			  	 <div class="col-sm-2">
  			  	 <h4><h4><b style="text-align:center;color:green">Total Score :-</b></h4></h4>
  			  	 </div>
  				<div class="col-sm-2">
  			  	 <h4><h4><b style="text-align:center;color:green">${list. total_avg_Rate}</b></h4></h4>
  			  	 </div>
  			  	 </div>
			</div>
  			</div>
			
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
</body>
<script type="text/javascript">
  window.onload = function () {
    var chart = new CanvasJS.Chart("chartContainer",
    {
      title:{
       text: "Feedback Report Chart",             
        fontColor: "green",
      
      },
      axisY:{
        interlacedColor: "rgb(255,250,250)",
        gridColor: "#d0ece7"
      },
      data: [
      {        
        type: "column",
        color: "#d3efc7",
        dataPoints: [
        { x: 1, y: ${list.avg1} },
        { x: 2, y: ${list.avg2} },
        { x: 3, y: ${list.avg3} },
        { x: 4, y: ${list.avg4} },
        { x: 5, y: ${list.avg5} },
        { x: 6, y: ${list.avg6} },
        { x: 7, y: ${list.avg7} },
        { x: 8, y: ${list.avg8} },
        { x: 9, y: ${list.avg9} },
        { x: 10,y: ${list.avg10} }
        ]
      }
      ]
    });

    chart.render();
  }
  </script>
  <script type="text/javascript" src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>


<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-127607784-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-127607784-1');
</script>