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
<c:url var="download" value="/web/taskforce/serives/student/studentdata" />

<c:url var="download1" value="/web/taskforce/serives/staff/staffdata" />

<html>
<style>
.success {
    background-color: #ddffdd;
    border-left: 6px solid #4CAF50;
    width: 400px;
    margin-bottom: 15px;
    padding: 4px 12px;
}

</style>

<body class="hold-transition skin-blue fixed sidebar-mini" >

<div class="wrapper">
 <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
        <section  class="content-header">
       <div class="box-body"><span><font size="5">Student and staff data</font></span></div>
	</section>
   <!-- Main content -->
    <section class="content">
      <!-- /.col -->
        <div class="col-md-12" >
          <div class="box box-default">          
            <div class="box-body">
            <!-- enctype="multipart/form-data" -->
            			<div class="container" id="datepicker" style="margin-top: 5%;">
					<form action="${download}" method="post" id="addform">
    <div class="row">
    <div class='col-sm-3'>
        </div> 
     <div class='col-sm-2'>
    
        
    </div>
    
    <div class="row">
     <div class='col-sm-3'>
        </div> 
     
        
    </div>
    <div class="success" id="notification" style="display: none">
  <p><strong>Response!</strong> Grievances not available pls Check Date...</p>
</div>  	
    <div class="row">
     <div class='col-sm-3'>
        </div> 
     <div class='col-sm-3'>
        </div> 
        <div class='col-sm-2'>
        </div>
           <div class='col-sm-3'><br/>
            	<input type="submit"  value="Download Excel File Student" class="btn btn-success" />
        </div>
       
    </div>
    
     
         
    </form>
    
</div>
        			<div class="container" id="datepicker" style="margin-top: 5%;">
					<form action="${download1}" method="post" id="addform">
    <div class="row">
    <div class='col-sm-3'>
        </div> 
     <div class='col-sm-2'>
    
        
    </div>
    
    <div class="row">
     <div class='col-sm-3'>
        </div> 
     
        
    </div>
    <div class="success" id="notification" style="display: none">
  <p><strong>Response!</strong> Grievances not available pls Check Date...</p>
</div>  	
    <div class="row">
     <div class='col-sm-3'>
        </div> 
     <div class='col-sm-3'>
        </div> 
        <div class='col-sm-2'>
        </div>
           <div class='col-sm-3'><br/>
            	<input type="submit"  value="Download Excel File Staff" class="btn btn-success" />
        </div>
       
    </div>
    
     
         
    </form>
    
</div>
	
	
              </div>
           </div>
         </div>
        </section>
       </div>
     </div> 
     <script type="text/javascript">
            $(function () {
              
                $('#datetimepicker2').datetimepicker();
                
            });
        </script>
	  <script type="text/javascript">
            $(function () {
                $('#datetimepicker1').datetimepicker();
              
                
            });
           
          
            var sts="${status}";
            if(sts=="false")
            {
            	$("#notification").show();
            	 showNotification();
                 setTimeout(hideNotification, 5000);
           		     
            }
            
 
            function showNotification() {
                $(".success")
                  .fadeIn()
                  .css({ right: 0, position: "absolute" })
                  .animate({ left: 0 }, 1500, function() {
                    // $('#selector').delay(5000).fadeOut('slow');
                  });
              }

              function hideNotification() {
                $(".success").fadeOut("slow");
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