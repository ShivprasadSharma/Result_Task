<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>

.yello_hr {
  	height: 2px;
   	color: #ffe63b;
   	background-color: #ffe63b;	
  }	
  
.btnnn {
height: 150px;
border-radius:0px;
box-shadow: 5px 5px 8px  #85929e ;
cursor:pointer;
}

/* Green */
.success {
  	border-color: #4CAF50;
  	color: green;
}

.success:hover {
  	border-color: black;
  	background-color: #4CAF50;
  	color: white;
  }
  
.dd{
  	box-shadow: 1em 1em 1em rgba(0, 0, 0, 0.2);

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
<body class="hold-transition fixed skin-blue sidebar-mini" >
<div class="wrapper myStyle">
<div class="content-wrapper">
   
<!-- Main content -->
    <section class="content " >
	   <div class="col-md-12 col-sm-12">
    <section class="content-header">
    <h4><b>Online Exam / Test  >></b></h4>
     </section >
       <div class="box box-default" >
		 <div class="box-body"  >
					<div class="btn-pref btn-group btn-group-justified btn-group-lg" role="group" aria-label="...">
							        <div class="btn-group" role="group">
							             <a href="<c:url value="#"/>"><button type="button" class="btnnn btn success" ><span class="fa fa-clipboard" style="font-size:25px;" ></span>
							            <div  style="font-size:16px;">
							                Schedule Of Exam</div>
							            </button></a>
							        </div>
							        
							        <div class="btn-group" role="group">
							             <a href="<c:url value="#"/>"><button type="button" class="btnnn btn success" ><span class=" 	fa fa-th-list" style="font-size:25px;" ></span>
							            <div  style="font-size:16px;">
							                Upcoming Exam / Test</div>
							            </button></a>
							        </div>
							        
							        <div class="btn-group" role="group">
							             <a href="<c:url value="#"/>"><button type="button" class="btnnn btn success" ><span class="fa fa-plus-square" style="font-size:25px;" ></span>
							            <div  style="font-size:16px;">
							                Add New Exam</div>
							            </button></a>
							        </div>
							           
					</div>
					<div class="btn-pref btn-group btn-group-justified btn-group-lg" role="group" aria-label="...">
							        <div class="btn-group" role="group">
							             <a href="<c:url value="#"/>"><button type="button" class="btnnn btn success" ><span class="fa fa-book" style="font-size:25px;" ></span>
							            <div  style="font-size:16px;">
							                Add Question</div>
							            </button></a>
							        </div>
							        
							        <div class="btn-group" role="group">
							             <a href="<c:url value="#"/>"><button type="button" class="btnnn btn success" ><span class="fa fa-upload" style="font-size:25px;" ></span>
							            <div  style="font-size:16px;">
							                Upload Question</div>
							            </button></a>
							        </div>
							        
							        <div class="btn-group" role="group">
							             <a href="<c:url value="#"/>"><button type="button" class="btnnn btn success" ><span class="fa fa-file-text" style="font-size:25px;" ></span>
							            <div  style="font-size:16px;">
							                Report</div>
							            </button></a>
							        </div>							           
					</div>
					
					<div class="btn-pref btn-group btn-group-justified btn-group-lg" role="group" aria-label="...">
							        <div class="btn-group" role="group"  onclick="deleteSubject()">
							              <a href="<c:url value="#"/>"><button type="button" class="btnnn btn success" ><span class="fa fa-book" style="font-size:25px;" ></span>
							            <div  style="font-size:16px;">
							                Add Subject Unit</div>
							            </button></a>
							        </div >
							        <div class="btn-group" role="group">
							        </div>
							        <div class="btn-group" role="group"></div>
					</div>
				<hr class="yello_hr">
			
				
            </div>
	      </div>
	      
	      
	  </div>
   </section>
   <div id="mygraph" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <span class="closegraph">&times;</span>
    <h3>Add Subject Unit /Chapter</h3>
  
       <jsp:include page="AddUnitPopUp.jsp" flush="true"/>


</div>
</div>
</div> 
</div>
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-127607784-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-127607784-1');
  
  
//Get the modal
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

</script>
</body>