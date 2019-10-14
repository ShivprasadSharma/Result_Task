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
<c:url var="addmembers" value="/web/taskforce/serives/grievance/add/members" />


<html>
<body class="hold-transition skin-blue fixed sidebar-mini" >

<div class="wrapper">
 <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
        <section  class="content-header">
       <div class="box-body"><span><font size="5">Add Committee Member's</font></span></div>
	</section>
   <!-- Main content -->
    <section class="content">
      <!-- /.col -->
        <div class="col-md-12" >
          <div class="box box-default">          
            <div class="box-body">
            <!-- enctype="multipart/form-data" -->
            		<form action ="${addmembers}" method = "post">
            		    <div class="form-group" >
		          		 <div class="col-sm-12">
					      <div class="col-sm-6">
					       <c:set var="stafflist" value="${stafflist}"/>
							<label class="control-label" style="color:#008000"for="id">Add Member's:</label>
							<select class="js-example-basic-multiple" name="userId[]" multiple="multiple" data-placeholder="Select Members..." style="width: 100%">
							     <c:forEach var="Sitem" items="${stafflist}">
								        <option value="${Sitem.id}" ${Sitem.id == selectedDept ? 'selected="selected"' : ''}>${Sitem.firstName} ${Sitem.lastName}</option>
								  </c:forEach> 
			                </select> 
						  </div>
						
					</div>
			                </div>
		    			<div>
				  		<input type="submit" id="submitbtn" value="Add Members" class="btn btn-success" onclick="check()" style="margin-left: 50%;margin-top: 5%"/>
 			    		</div>
 			    </form>
              </div>
           </div>
         </div>
        </section>
       </div>
     </div> 
     <script type="text/javascript">
     
     $(document).ready(function() {
    	    $('.js-example-basic-multiple').select2();
    	});
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