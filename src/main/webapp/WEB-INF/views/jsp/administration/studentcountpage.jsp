
<%@page import="javax.naming.NoInitialContextException"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  
	<style>
body {font-family: Arial, Helvetica, sans-serif;
   background-color: #d3efc6;
   
}
input[type=text],input[type=email], input[type=date],input[type=number],select, textarea ,input[type=file]{
    width: 80%;
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
    margin-left: 70%;
    margin-top: 30px;

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

.th{

    text-align:left;
    color:green;
    padding: 6px;
    
}
.table
{
border:2px solid green;
}

.container {
	  max-width: 50%;
	  padding: 1em 3em 2em 3em;
	  margin: 0em auto;
	  font-size: 20px;
	  background-color: #fff;
	  border-radius: 4.2px;
	  box-shadow: 0px 5px 15px -2px rgba(0, 0, 0, 0.2);
	  margin-left:20px;
	  margin-top:-15px;
	  
}ixmg
 {
	  border-radius: 50%;
	}
	
	
.row {
    font-size: 10px;
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
	  width: 80%;
	  height: 35px;
	  border-radius: 5px;
	  box-shadow: 0px 6px 10px -2px rgba(0, 0, 0, 0.6);
	  padding: 1px 10px;
	  text-align: center;
	  background-color: #4CAF50;
	  color: white;
	  margin-top:10px;
}




.panel-default>.panel-heading {
  color: #333;
  background-color: #fff;
  border-color: #e4e5e7;
  padding: 0;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
.panel-body
{

margin-left: 0px;
}

.panel-default>.panel-heading a {
  display: block;
  padding: 2px 5px;
}

.panel-default>.panel-heading a:after {
  content: "";
  position: relative;
  top: 1px;
  display: inline-block;
  font-family: 'Glyphicons Halflings';
    transition: max-height 0.2s ease-out;
  
  font-style: normal;
  font-weight: 400;
  line-height: 1;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  float: right;
  transition: transform .25s linear;
  -webkit-transition: -webkit-transform .25s linear;
}

.panel-default>.panel-heading a[aria-expanded="true"] {
  background-color: #eee;
}

.panel-default>.panel-heading a[aria-expanded="true"]:after {
  content: "\2212";
  -webkit-transform: rotate(180deg);
  transform: rotate(180deg);
}

.panel-default>.panel-heading a[aria-expanded="false"]:after {
  content: "\002b";
  -webkit-transform: rotate(90deg);
  transform: rotate(90deg);
}

.accordion-option {
  width: 200%;
  float: left;
  clear: both;
  margin: 15px 0;
}

.accordion-option .title {
  font-size: 15px;
  font-weight: bold;
  float: left;
  padding: 0;
  margin: 0;
}

.accordion-option .toggle-accordion {
  float: right;
  font-size: 12px;
  color: #6a6c6f;
}


.button {
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 2px 8px;
  text-decoration: none;
  display: inline-block;
  font-size: 10px;
  margin: 4px 2px;
  cursor: pointer;
}

.button1 {
  background-color: white; 
  color: black; 
  border: 2px solid #4CAF50;
}
.kj
{
   margin-left: 20px;
}

  container {
  width: 80%;
  border: 2px solid black;

}


.button8 {
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  
  text-decoration: none;
  display: inline-block;
  font-size: 10px;

  cursor: pointer;
}


.accordion_head {
  background-color:green;
  color: white;
  cursor: pointer;
  font-family: arial;
  font-size: 14px;
  margin: 0 0 1px 0;
  padding: 7px 11px;
  font-weight: bold;
}

.accordion_body {
  background:white;
}

.accordion_body p {
  padding: 18px 5px;
  margin: 0px;
  border:solid 1px black;
}

.plusminus {
  float: right;
}

.accordion {
  background-color: #eee;
  color: #444;
  cursor: pointer;
  padding: 5px;
  width: 100%;
  border: none;
  text-align: left;
  outline: none;
  font-size: 15px;
  transition: 0.4s;
}

.active, .accordion:hover {
  background-color: #ccc;
}

.accordion:after {
  content: '\002B';
  color: #777;
  font-weight: bold;
  float: right;
  margin-left: 5px;
}

.active:after {
  content: "\2212";
}

.panel {
  padding: 0px 20px;
  background-color: white;
  max-height: 0;
  height:600px;
  border:solid 1px green;
  transition: max-height 0.4s ease-out;
 float:left;
width:100%;
overflow: auto;
}
/* 

.accordion-option .toggle-accordion:before {
  content: "Collapse All";
}

.accordion-option .toggle-accordion.active:before {
  content: "Collapse All";
} 

*/


.button {
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 10px 20px;
  text-decoration: none;
  display: inline-block;
  font-size: 30px;
  margin: 4px 2px;
  cursor: pointer;
}

.button1 {
  background-color: white; 
  color: black; 
  border: 2px solid #4CAF50;
}

.kj
{
   margin-left: 40px;
}


.div1 {
  width: 300px;
  height: 260px;
  border: 3px solid green;
   margin-left: 0px;
}

.button {
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 20px;
  margin: -1px -3px;;
  cursor: pointer;
}
.circle {
 margin-top: 0px;
  margin:-65px;
   padding: 5px 2px;
	width: 90px;
	height: 90px;
	border-radius: 50%;
	font-size: 40px;
	color: green;
	line-height: 80px;
	text-align: center;
	background: #d3efc7;
	border-style: solid;
	border-color: green;
    margin-left: 15px;
}


.h1 {
    display: inline-block;
    max-width: 100%;
    margin-top: 17px;
    padding:18px 6px 18px 2px;
    margin-bottom: 48px;
    margin-left: 28px;
}

.button1 {
width: 300px;
text-align:left;
}

.thead,th
{
 color:green;

}


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
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
  overflow: auto;
}

/* Modal Content     top margin is 10px
    right margin is 5px
    bottom margin is 15px
    left margin is 20px
*/
.modal-content {
  background-color: #fefefe;
  padding: 20px 40px;
  border: 2px solid green;
  width: 69%;
  height:500px;
  margin:-7px 52px 162px 301px;
  overflow: auto;
}

/* The Close Button */
.close {
  color: #aaaaaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}

.button12 {background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 12px 25px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
	
}



.button13 {
background-color: #4CAF50; /* Green */
  border: 2px solid green;
  color: white;
  padding: 8px 16px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 15px;
  cursor: pointer;
   margin-top:0px;
	margin-right:-19px;
	
}

.button12 {
   background-color: white; 
  color: black; 
  border: 3px solid #4CAF50;
	 
 
}

.test
{
  margin-left: 250px;
  color:green;
  
  
}

.card.hovercard {
    position: relative;
    padding-top: 0;
    overflow: hidden;
    text-align: center;
    background-color: #fff;
    background-color: rgba(255, 255, 255, 1);
}
.card.hovercard .card-background {
    height: 130px;
}

</style>


</head>
<c:url  var ="previouscount" value='/web/taskforce/attendance/punch/page/'/>${sessionScope.user.staffId}">
<body class="hold-transition skin-blue sidebar-mini" >

 
<div class="wrapper ">
 <div class="content-wrapper myStyle" >
  

    <!-- Main content -->
   		 <section class="content">
  	
				<div class="col-md-12">
				
					<div class="box box-default;col-md-12 col-sm-12" >
						<div class="box-body" >
						
									<div class="row" style="margin-top:0px;color:black;">
									
									 <div class="col-sm-2.5"> 
  			    <div class="col-sm-2.5">                       <a href="<c:url value='/web/taskforce/attendance/punch/page/'/>${sessionScope.user.staffId}">
  			    <button id="cmd" class="pull-right button8 button13" ><i class="fa fa-street-view" ></i>&nbspPrevious Student Count </button></a></div></div>
			    <b><h3 style="margin-left:372px">Today's Student Count</h3>
			   <b><h4 style="margin-left:405px">College Student Count</h4></b>
			            
									</div>
									
					<div class="test">
					
									<c:set var='count' value="${collegecount}" />
								
									      <row>
									      <label  style="margin-left: 140px;font-size: 14px">Present Student</label>
		                                  <label  style="margin-left: 15px;font-size: 14px">Total Student</label>
          
                                           </row><br/>
                                                         
                                           <row>
                                    <button class="button button12" style="margin-left: 140px;font-size: 25px;">0${count.presentcount}</button>
                                    <button class="button button12" style="margin-left: 32px ;font-size: 25px;">${count.totalcount}</button>
                                                     
                                           </row>
					
									</div>

											
								    </div>
					                 
										<div class="row">	
										
								
									<c:forEach  var="list" items="${studentcount}">
									
									<div class='col-sm-4'>	
                                    <c:set var='n' value="${n + 1 }" />
                                    
                   	<div class="div1">
					<button class="button button1"  id="${list.dept_id}" onclick="getdepartment('${list.dept_id}')" ><h4 style="color:black"><b>${n}&nbsp${list.deptname}</b></h4> </button>
					<div class="row" >

								<div class='col-sm-5'>
								<div style="float: right;">
								
		<label class="h1" style="font-size: 15px">Present Student</label>
									<div class="circle">
									<b id="${list.dept_id}">${list.presentcount}</b>
												</div>
											</div>
									</div>
									
						<div class='col-sm-4'>
						
		<label class="h1" style="font-size: 15px">Total Student</label>
											<div class="circle">
											<b id="${list.dept_id}">${list.totalcount}</b>
											</div>
										</div>
									</div>
<%-- <c:if test="${department.dep_id eq 1}"> --%> 
	<div id="myModal" class="modal">

 		<div class="modal-content">
    		<span class="close">&times;</span>
        
    			<table class="table table-bordered table-striped">
					<thead>
										<tr>
											<th>Year</th>
											<th>Division</th>
											<th>Total Count</th>
											<th>Present Count</th>
										</tr>
					</thead>
									
					<tbody id="studListRow"></tbody>
				</table>
    
  </div>
</div>				
</div>
                   <br/>
                   <br/>
      </div>
      </c:forEach>
                                   		
									
									</div>
							
							
									</div>
									
									<br>

									</div>


							</div>
	 					
				
		</section>
  
	
</div>       
 <!-- /Main Content -->
</div>

<script>
var dept="";
function getdepartment(id){
	
	var modal = document.getElementById("myModal");
	    modal.style.display = "block";
  	  // Get the <span> element that closes the modal
  	  var span11 = document.getElementsByClassName("close")[0];
  	  // When the user clicks on <span> (x), close the modal
  	  span11.onclick = function() {
  	    modal.style.display = "none";
  	  }
 	  
  	window.onclick = function(event) {
	    if (event.target == modal) {
	      modal.style.display = "none";
	    }
	  }		    

  	$.ajax({
			  type: "POST",
		         url: "/"+location.pathname.split("/")[1]+"/web/taskforce/service/count/student",
		         data:'&id='+id,
		         dataType: 'json',
		         
		         success: function(data)
		         { 
	        		var x=" ";
	        		dept =id;
	        		var tempKey = "";
		        	 
		        	var count=0;
		        	
		        	$("#studListRow").html();

		        	$.each( data, function( key, value ) {
		        		 
		        		 $.each(value, function(index,value) {
		        			 		        			
						    	if(key == 1){
						    		tempKey = "FE";    		
						    	}else if(key == 2){
						    		tempKey = "SE";
						    	}else if(key==3){
						    		tempKey = "TE";
						    	}else{
						    		tempKey = "BE";
						    	}
						    							    	
		        			  x+='<tr>'+
			      	        '						     <td>'+tempKey+'</td>'+
		      	        	'						     <td>'+value.div+'</td>'+
		      	        	'						     <td>'+value.totalcount+'</td>'+
		      	        	'						     <td>'+value.presentcount+'</td>'+
		      	        	'							</tr>';	
		        		 
		        			});
		        		
			    		 $("#studListRow").html(x);
			    		

		        		});

		         }
			 		 
			     ,
		         
			     error:function(data){
			    	    alert("error");
			     }
		    });
}


/* ....................................................... */
 
/*   $( function() {
    $( "#datepicker" ).datepicker();
  } );
 */

  
</script>
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-127607784-1"></script>
<script>
$( function() {
    $( "#datepicker1222" ).datepicker({ dateFormat: 'yy-mm-dd' });
  } );
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-127607784-1');
</script>
</body>

