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
   <c:url var="uploadNoticeFile" value="/web/taskforce/service/notice/fileupload" />
   
<html>
<style>
.bbttnn {
    background-color: #00A55A;
    border: none;
    color: white;
    padding: 8px 15px;
    cursor: pointer;
    font-size: 15px;
     border-radius: 3px
}

/* Darker background on mouse-over */
.bbttnn:hover {
    background-color:Green;
}
</style>
<head>
</head>
<body class="hold-transition skin-blue fixed sidebar-mini" >
<div class="wrapper">
 <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
        <section  class="content-header">
       <div class="box-body"><span><font size="5">Upload Notification Excel File</font></span></div>
	</section>
   <!-- Main content -->
    <section class="content">
      <!-- /.col -->
        <div class="col-md-12" >
          <div class="box box-default">          
            <div class="box-body">
            <!-- enctype="multipart/form-data" -->
            		<form action = "${uploadNoticeFile}" method = "post" enctype="multipart/form-data">
            		    <div class="form-group" >
		          		<label style="margin-top:13px">Select file to upload</label><lable style="margin-top:13px" class="pull-right"><b>Sample File</b> <br> 
		          		
		          		<a href="https://res.cloudinary.com/dcptr5ivh/raw/upload/v1542969471/EXcel%20Files/Event_Notification_file.xlsx" download><i class="fa fa-download bbttnn"> Download</i></a>
		          		</lable>
		          		<h5><b> <font color=red>*</font> Note : Here You Can Select Only TPO Calendar/ Academic Calendar/ Events/ Library Notification Formated Excel File. </b><br></h5>
		          		
 		          		<input type="file" id="notifile1" name="NoticeExcelFile" accept=".xls,.xlsx" value=" Upload Media" class="btn btn-default"  onchange="check1()"/> 
		        		 <p style="color:red" id="demo"></p>
		        		</div>
		    			<div>
				  		<input type="submit" id="submitbtn" value="Upload" class="btn btn-success" onmouseover="check1()"/>
 			    		</div>
 			    </form>
              </div>
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
 </html>