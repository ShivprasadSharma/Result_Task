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
   <c:url var="uploadfile" value="/web/taskforce/service/resultsubject/uploadfile" />
<html>
<body class="hold-transition skin-blue fixed sidebar-mini" >
<div class="wrapper">
 <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
        <section  class="content-header">
       <div class="box-body"><span><font size="5">Upload Subject Excel File</font></span></div>
	</section>
   <!-- Main content -->
    <section class="content">
      <!-- /.col -->
        <div class="col-md-12" >
          <div class="box box-default">          
            <div class="box-body">
            <!-- enctype="multipart/form-data" -->
            		<form action = "${uploadfile}" method = "post" enctype="multipart/form-data">
            		    <div class="form-group" >
		          		<label style="margin-top:13px">Select file to upload</label><br/>
		          		  <label>Please  select subject xml file</label>
 		          		<input type="file" id="notifile1" name="SubjectExcelFile" accept=".xls,.xlsx" value=" Upload Media" class="btn btn-default"  onchange="check1()"/> 
		        		  <p style="color:red" id="demo">
		        		 </p>
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
   </body>
 </html>