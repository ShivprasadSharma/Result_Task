<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.io.FileInputStream" %>
<%@page import="java.io.File" %>
<%@page import="java.io.InputStreamReader" %>
<%@page import="java.net.URL" %>
<%@page import="java.io.FileReader" %>
<%@page import="java.io.BufferedReader" %>
<%@page import="java.io.InputStream" %>
<%@page import="java.io.IOException" %>;
<%@page import="java.io.*" %>

<style>
.bbttnn {
    background-color: #00A55A;
    border: none;
    color: white;
    padding: 8px 10px;
    cursor: pointer;
    font-size: 11px;
     border-radius: 4px
}

/* Darker background on mouse-over */
.bbttnn:hover {
    background-color:Green;
}



input[type=text], input[type=email], input[type=date], input[type=number],
	select, textarea {
	width: 100%;
	padding: 4px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 5px;
	resize: vertical;
	background-color: #f2f2f2;
}

.new5 {
   height: 2px;
   color: yellow;
   background-color: #ffe63b;	
  }	
  
  .btnn {
  border: 2px solid black;
  background-color: #d9f1cf;
  color: black;
  padding: 7px 20px;
  font-size: 15px;
  cursor: pointer;
  border-radius: 1px;
  box-shadow: 0.3em 0.3em 0.3em rgba(0, 0, 0, 0.2);
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


  

</style>
<c:url var="saveuser" value="/web/taskforce/service/save" />
<body class="hold-transition skin-blue fixed sidebar-mini" >
<div class="wrapper">
 <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
        
   <!-- Main content -->
    <section class="content">
      <!-- /.col -->
        <div class="col-md-12" >
          <div class="box box-default">          
            <div class="box-body">
            
             <div class="btn-pref btn-group btn-group-justified btn-group-lg" role="group" aria-label="...">
							        
							         <div class="btn-group" role="group">
							             <a href="<c:url value="/web/taskforce/stafflist/1"/>"><button type="button" class="btn btn success"style="border-radius: 0px;" ><span class="fa fa-users" style="font-size:15px;" ></span>
							            <div  style="font-size:14px;">
							               
							                Head Of Department </div>
							            </button></a>
							        </div>
							         <div class="btn-group" role="group">
							             <a href="<c:url value="/web/taskforce/stafflist/2"/>"><button type="button" class="btn btn success"style="border-radius: 0px;" ><span class="fa fa-users" style="font-size:15px;" ></span>
							            <div  style="font-size:14px;">
							               
							                Teaching Staff </div>
							            </button></a>
							        </div>
							         <div class="btn-group" role="group">
							             <a href="<c:url value="/web/taskforce/stafflist/4"/>"><button type="button" class="btn btn success"style="border-radius: 0px;" ><span class="glyphicon glyphicon-user" style="font-size:15px;" ></span>
							            <div  style="font-size:14px;">
							               
							               Administration </div>
							            </button></a>
							        </div>
							         <div class="btn-group" role="group">
							             <a href="<c:url value="/web/taskforce/stafflist/6"/>"><button type="button" class="btn btn success" style="border-radius: 0px;"><span class="fa fa-desktop" style="font-size:15px;" ></span>
							            <div  style="font-size:14px;">
							               
							               IT Support </div>
							            </button></a>
							        </div>
							       <div class="btn-group" role="group">
							             <a href="<c:url value="/web/taskforce/service/Admin_details"/>"><button type="button" class="btn btn success"style="border-radius: 0px;" ><span class="fa fa-user-plus" style="font-size:15px;" ></span>
							            <div  style="font-size:14px;">
							               
							                Add New User</div>
							            </button></a>
							        </div>
							        
							        <div class="btn-group" role="group">
							             <a href="<c:url value="/web/taskforce/services/add"/>"><button type="button" class="btn btn success"style="border-radius: 0px; background-color: #4CAF50;color:white;" ><span class="fa fa-file-excel-o" style="font-size:15px;" ></span>
							            <div  style="font-size:14px;">
							               
							                Import Users Excel</div>
							            </button></a>
							        </div>
							        
							    </div>
							    <hr class="new5">
					
            <span><font size="5">Upload user details Excel file</font></span>
            <!-- enctype="multipart/form-data" -->
            		<form action = "${saveuser}" method = "post" enctype="multipart/form-data">
            		    <div class="form-group" align="center" > <b>Student :</b> <input type="radio" checked="checked" name="fileType[]" value="2">  &nbsp; &nbsp;&nbsp;&nbsp; <b>Staff: </b> <input type="radio" name="fileType[]" value="1"> </div>
               		<div class="form-group" >
		          		<label style="margin-top:13px">Select file to upload</label>
		          		
		          		<lable style="margin-top:13px" class="pull-right"><b>Student File </b>&nbsp;&nbsp;&nbsp; <b> Staff File</b>  <br> 
		          		
		          		<a href="https://res.cloudinary.com/dcptr5ivh/raw/upload/v1542970553/EXcel%20Files/student_tested_ok.xlsx" download><i class="fa fa-download bbttnn"> Download</i></a>
		          		&nbsp;<a href="https://res.cloudinary.com/dcptr5ivh/raw/upload/v1542970186/EXcel%20Files/staff_info.xlsx" download><i class="fa fa-download bbttnn"> Download</i></a>
		          		</lable>
		          		<h5><b> <font color=red>*</font> Note : Here You Can Select Only Formated Excel File. Sample file present in download  </b><br></h5>
		          		
		          		
 		          		<input type="file" id="notifile1" name="ExcelFile" accept=".xls,.xlsx" value=" Upload Media" class="btn btn-default" onchange="check1()" /> 
		        		  <p style="color:red" id="demo"></p>
		        		</div>
		    			<div>
				  		<input type="submit"id="submitbtn" value="Upload" class="btn btn-success" onmouseover="check1()"/>
 			    		</div>
 			    </form>
              </div>
           </div>
           <%
			 /* String filePath = "/Users/sujit/File41.txt";
			 try {
			     BufferedReader lineReader = new BufferedReader(new FileReader(filePath));
			     String lineText = null;
			  
			     while ((lineText = lineReader.readLine()) != null) {
			         System.out.println(lineText);
			         String output=lineText;
			     }
			     lineReader.close();
			 } catch (IOException ex) {
			     System.err.println(ex);
			 } */
			  
			%>
           
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
</html>