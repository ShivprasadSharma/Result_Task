<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:url var="resultReport" value="/web/taskforce/service/result/report/student" />
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
	</section>
   <!-- Main content -->
    <section class="content">
      <!-- /.col -->
        <div class="col-md-12"  style="margin-top: 35px" >
          <div class="box box-default">          
            <div class="box-body">
                   <div class="box-body"><span><font size="5">Student Report</font></span></div>
            
            <!-- enctype="multipart/form-data" -->
            			<div class="container" id="datepicker" style="margin-top: 5%;">
                              <form action="${resultReport}" method="post">
               
    <div class="row">
    <div class='col-sm-1'></div>
     <div class='col-sm-1'>
    <b>Year</b> 
        </div> 
        <!-- <div class="row" style="margin-top: 20px" > -->
                 
                 
                 
                 <div class="col-md-4">
                    <select  name="yearId" class="form-control" style="width:120px" id="selectYr" >
                           <option value="0">Select Year</option>                           
						    <c:forEach var="item" items="${academicYear}" >
						        <option value="<fmt:formatDate pattern="yyyy" value="${item.yearStartDate}"/>" ${item.yearId == selectedDept ? 'selected="selected"' : ''}>
						        <fmt:formatDate pattern="yyyy" value="${item.yearStartDate}"/> </option>
						     </c:forEach> 
						</select> 				
                    </div>
                
                     <div class='col-sm-1'>
    <b>Department</b> 
        </div> 
         <div class="col-md-4" >
 			    		 <select  name="branchId" class="form-control" style="width:120px" id="selectDep" onchange="getResult()" >
                         <option value="0">Select Branch</option>
						    <c:forEach var="item" items="${departmets}" >
						       <option value="${item.dep_id}" ${item.dep_id == selectedDept ? 'selected="selected"' : ''}>${item.dep_name}</option>
						    </c:forEach> 
					   </select> 
					    <input type="hidden" id="subject" value="0">				                     
 			    	  </div>
         
         </div>  
         <br>
          <div class="row"> 
           <div class='col-sm-1'></div>  
         <div class='col-sm-1'>
            
         
    <b>Semester</b> 
        </div> 
         <div class='col-sm-4'>
       <select id="class"  name="classId" class="form-control" style="width:120px" required="required">
                                  <option value="0">Select semester</option>
       <option value="1">I</option>
         <option value="2">II</option>
           <option value="3">III</option>
             <option value="4">Iv</option>
             <option value="5">v</option>
             <option value="6">VI</option>
             <option value="7">VII</option>
             <option value="8">VII</option>
    </select> 
        </div>
         </div>     
       
    
    
   
    
      
    <div class="row">
     <div class='col-sm-3'>
        </div> 
     <div class='col-sm-3'>
        </div> 
        <div class='col-sm-2'>
        </div>
           <div class='col-sm-3'><br/>
            	<input type="submit"  value="Download Excel File" class="btn btn-success" />
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

            
        </script>
	  
   </body>
 </html>