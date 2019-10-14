<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <script type="text/javascript" src="//cdn.rawgit.com/MrRio/jsPDF/master/dist/jspdf.min.js">
    </script>
    <script type="text/javascript" src="//cdn.rawgit.com/niklasvh/html2canvas/0.5.0-alpha2/dist/html2canvas.min.js">
    </script>
      
<body  class="hold-transition skin-blue fixed sidebar-mini">
	<div class="wrapper " >
	 <div class="content-wrapper" >
	    <section  class="content-header">
	       <div class="box-body" >
			  <h3><b>Time Table</b> <font size="3" style="color: green" id="create_pdf" class="pull-right" ><i class="fa fa-download"></i>PDF</font></h3>	   
		   </div>
	    </section> 
	    <section >
	      <div class="box box-default"  style="margin-left:10px ; margin-right:10px">
	        <div class="box-body "  >
		      <c:set var="x" value="1"/>
			       <table class="table table-bordered table-striped" style="width: 100%">
					<thead >				
					  <tr >
						<td rowspan="3" colspan="3">KJCOEMR<br></td>
						<td rowspan="3" colspan="4"><h4 align="center"><br><br><b>Time Table</b></h4> </td>
						<td colspan="1">Class</td>			
							
						<c:forEach var="timeTable" items="${timeTable}">
							<c:forEach var="depName" items="${timeTable.timeTableDTO}">
							  <c:if test="${x eq 1 }">
							       <td colspan="3">${depName.dep_name}</td>						  
							  </c:if>
							   <c:set var="x" value="2"/>
							  </c:forEach>
						 </c:forEach>
					  </tr>
					  <tr colspan="3">
					    <td colspan="1">Time Table <br> Effective From</td>
					    <td colspan="3">15/06/2018</td>
					  </tr>
					  <tr colspan="3">
					    <td colspan="1">Academic Year</td>
					      <c:forEach var="timeTable" items="${timeTable}">
						      <c:forEach var="year" items="${timeTable.timeTableDTO}">
							       <c:if test="${x eq 2 }">
							          <td colspan="3">
							        		<fmt:formatDate pattern="yyyy" value="${year.yearStartDate}" /> - 
							        		<fmt:formatDate pattern="yy" value="${year.yearEndDate}" />
			 						  </td>
							        </c:if>
							        <c:set var="x" value="3"/>
						        </c:forEach>
					     </c:forEach> 
					  </tr>
					</thead>	
					  <tbody>				 
				    		   <tr >
				    		      <th ><h4 align="center"><br>Day\Time</h4></th>
				    		      <c:forEach var="timeTable" items="${timeTable}">
				    		         <c:forEach var="time" items="${timeTable.timeTableDTO}">
				    		            <c:if test="${x eq 3 }">
						    		        <th >
									    		<h5 align="center">${time.periodStartTime} <b>-</b> ${time.periodEndTime}</h5>
									    </th> 
								    </c:if>
								</c:forEach>   
								 <c:set var="x" value="4"/> 
							  </c:forEach>
				    		   </tr>
				    		   <c:forEach var="timeTable" items="${timeTable}">	
					    		   <c:if test="${not empty timeTable.timeTableDTO}">    		        
					    			 <tr>
					    			    <td align="center">
									    		${timeTable.day_name}
									</td>
								    <c:forEach var="sibjectInfo" items="${timeTable.timeTableDTO}">
									   <td align="center">
									       <c:if test="${sibjectInfo.subject_name ne 'Ressess'}">
									    		<b>  
									    		<c:if test="${not empty sibjectInfo.batch_name and sibjectInfo.batch_name ne 0}">						    		
									    		 B${sibjectInfo.batch_name}
									    		</c:if>
									    		<c:set var="subName" value="${fn:split(sibjectInfo.subject_name, ' ')}" />
									    		   ${fn:substring(subName[0],0,4)} ${fn:substring(subName[1],0,4)} ${fn:substring(subName[2],0,4)}
									    		     <c:if test="${sibjectInfo.theoryorPractical ne 'Theory'}">
										    		   (${fn:substring(sibjectInfo.theoryorPractical,0,3)}) 
										    		 </c:if>
										    		  <c:if test="${sibjectInfo.theoryorPractical eq 'Theory'}">
										    		   <br>
										    		 </c:if>
									    		 </b>						    		
									    		<b>${fn:substring(sibjectInfo.firstName, 0, 1)}${fn:substring(sibjectInfo.middleName, 0, 1)}${fn:substring(sibjectInfo.lastName, 0, 1)}
									    		</b>
									    		
									    	   </c:if> 
									    </td> 
								    </c:forEach>
								  </tr> 
							   </c:if>		
						   </c:forEach>  
				    		</tbody>
	 			</table> 
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
<script type="text/javascript">
 (function() {
    	 var
    	  form = $('.table'),
    	  cache_width = form.width(),
    	  a4 = [595.28, 1041.89]; // for a4 size paper width and height 

    	 $('#create_pdf').on('click', function() {
    	  $('body').scrollTop(0);
    	  createPDF();
    	 });
    	 //create pdf 
    	 function createPDF() {
    	  getCanvas().then(function(canvas) {
    	   var
    	    img = canvas.toDataURL("image/png"),
    	    doc = new jsPDF({
    	     unit: 'px',
    	     format: 'a4'
    	    });
    	   doc.addImage(img, 'JPEG', 20, 20);
    	   doc.save('TimeTable.pdf');
    	   form.width(cache_width);
    	  });
    	 }
    	 // create canvas object 
    	 function getCanvas() {
    	  form.width((a4[0] * 1.33333) - 80).css('max-width', 'none');
    	  return html2canvas(form, {
    	   imageTimeout: 2000,
    	   removeContainer: false
    	  });
    	 }
    	}());

    
</script>


</html>