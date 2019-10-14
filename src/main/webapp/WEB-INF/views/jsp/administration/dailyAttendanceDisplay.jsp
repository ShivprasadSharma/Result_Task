<%@page import="javax.naming.NoInitialContextException"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <script type="text/javascript" src="//cdn.rawgit.com/MrRio/jsPDF/master/dist/jspdf.min.js">
    </script>
    <script type="text/javascript" src="//cdn.rawgit.com/niklasvh/html2canvas/0.5.0-alpha2/dist/html2canvas.min.js">
    </script>
<html>
<style>
table {
   
    border-spacing: 0;
    width: 100%;
   
    font-size:12px;
   border: 1px solid black
}
td{
 border: 1px solid black;
}
th, td {
    text-align: left;
    padding: 4px;
   
}
tr:nth-child(even){background-color: #f2f2f2}
.span{
 padding: 15px;
 font-size: 16px;
}
</style>
<body class="hold-transition skin-blue fixed sidebar-mini" >
<div class="wrapper ">
 <div class="content-wrapper myStyle" >
  <c:set var="n" value="0"/>
    <!-- Main content -->
    <section class="content" >
         <!-- <div class="col-md-12" style="margin-top:21px">
		    <section class="content-header">
				<div class="col-md-12 col-sm-12 col-lg-12">
				</div>
			</section > -->
			<c:set var="list" value="${mentor}"/>
				             
			<!-- Info boxes -->
			  <div class="col-md-12 col-sm-12 " >
				    <!-- <div class="box">--> 
				    <div class="box-body" style="margin-top: 2%" > 
					   <span "><font size="5">
					   <c:choose>
    <c:when test="${empty comAttendances.batch}">
        Theory
    </c:when>
    <c:otherwise>
       Practical
    </c:otherwise>
</c:choose>
					  
					    Daily Attendance
					   </font> </span></div>
					   <div class="row">
  			  	 <div class="col-sm-10"> </div>
<!--   			   	<div class="col-sm-2"> <button id="cmd" class="buttn buttn1"><i class="fa fa-download"></i> PDF Download</button></div><br>
 -->  			    </div>
					   <div class="row"  >
  			  	 <div class="col-sm-12" style="overflow-x:auto;">
  			  	 <table style="background-color:white">
								
								<tr style="color:green">
								<th colspan="8" >
										<span class="name text-center" style="text-align: center"><b
											style="font-size: 25px">${sessionScope.collegeInfo.name}
												 </b></span><br> <br>
												
											<span class="span"><b>Department: ${comAttendances.department}</b></span> 
											<span class="span"><b>Academic Year ${comAttendances.acadmic_year}
														SEM-${comAttendances.semister}</b></span>
											<span class="span"><b>Division :&nbsp${comAttendances.division}</b></span>
											<c:if test="${not empty comAttendances.batch}">
											<span class="span"><b>Batch :&nbsp${comAttendances.batch}</b></span>
											
    										</c:if>
    										<span class="span"><b>class :&nbsp${comAttendances.class_name}</b></span><br>
											
											<span class="span"><b>Date :&nbsp${comAttendances.at_Date}</b></span>
											
											<span class="span"><b>Day :&nbsp${comAttendances.day}</b></span>

								</th>
								</tr>
								
								
										<tr>
											<th style="width: 10px" rowspan="2" >Roll.No</th>
											<th rowspan="2" align="center" style="font-size: 17px">Name of Student</th>
											<c:forEach var="lecture"
												items="${comAttendances.lectureList}">
												<th style="font-size: 17px">${lecture}</th>
											</c:forEach>
											<th rowspan="2" align="center" style="font-size: 17px">Present / Total</th>
										</tr>
										<tr>
											<c:forEach var="sub" items="${comAttendances.subList}">
												<th rowspan="" style="font-size: 17px">${sub}</th>
											</c:forEach>

										</tr>
												<c:forEach var="studentList"
											items="${comAttendances.studentList}">
											<tr>
												<c:set var='present' value="0" />
												<c:set var='total' value="0" />
												<td align="center">${studentList.roll_No}</td>
												<td align="center"><b>${studentList.firstName}
													${studentList.lastName}</b></td>
													
												<c:set var='count' value="1"  />
													<c:forEach var="studAttendance" items="${studentList.studAttendance}">

													<c:if test="${studAttendance=='P'}">
														<c:set var='present' value="${present+1}" />
													</c:if>
													
													
													
													<td align="center" class="presentlist${n}" >${studAttendance}</td>
													<c:set var='count' value="${count+1}" />
													<c:set var='total' value="${total+1}" />
												
												
												</c:forEach>
											
												<td align="center" style="font-size: 17px" id="counter${count}${n}"><b>${present}&nbsp/ &nbsp${total}</b></td>
											</tr>
											
											<c:set var='n'  value="${n + 1 }" />
											
										</c:forEach>
												<input type="hidden" value="" name="${n}" id="totalIDls"/>
												<input type="hidden" value="${count}"  id="conterID"/>
								</table>

  			  	 </div>
					   

					   
			            </div>
				    </div>
		        
	  </section>  
	<!-- /Main Content -->
 
</div>
			</div>


<script type="text/javascript">
window.onload = function() {
	
	setTimeout(function(){ 
	
	
	var counttotal=$("#conterID").val();
 	var totals=document.getElementById("totalIDls").name;
 	totals--;
 	
 	$(".presentlist"+totals).css('font-size','2em');
 	
 	$("#counter"+counttotal+""+totals).text("");
 	
	}, 500);
	 
	};
	
	$('#cmd').click(function () {
		var name_val = "${list.departmentname}";
		var empid_val = $('form#smdiv input[name="empid"]').val();
		var age_val = $('form#smdiv input[name="age"]').val();
	       		
		var pdf = new jsPDF();
		pdf.setPage(3);
		pdf.setFontSize(15);
		pdf.setTextColor(0, 100, 0);
		pdf.setFontType('bold')
		pdf.text(50, 10, 'G S Moze College of Engineering, Balewadi');
		pdf.setFontSize(8);
		pdf.text(85, 18, 'Attendance Report');
		pdf.setFontSize(12);
		pdf.text(67, 24, 'Department of '+ "${comAttendances.department}"+' Engineering');
		pdf.setFontSize(8);
		pdf.text(85,30,'Academic Year ' +"${comAttendances.acadmic_year}"+' Sem- '+" ${comAttendances.semister}");
		pdf.setTextColor(0, 0, 0);
		pdf.setLineWidth(0.5)
		pdf.line(10, 35, 200, 35)
		pdf.setFontSize(10);
		pdf.setFontType('bold')
		pdf.setTextColor(0, 100, 0);
		pdf.text(10, 45, 'Class  :');
		pdf.text(150, 45, '	Division :');
		pdf.text(10, 51, 'Date & Day :');
		pdf.setTextColor(0, 0, 0);
		pdf.setFontSize(8);
		pdf.text(55, 45,'' + " ${comAttendances.class_name} ");
		pdf.text(175, 45,'' + " ${comAttendances.division}");
		pdf.text(55, 51,'' + " ${comAttendances.at_Date} "+" ${comAttendances.day}");
	    pdf.setTextColor(0, 100, 0);
	    pdf.setFontType('bold')
	    pdf.setFontSize(7);
	    pdf.setTextColor(0, 0, 0);
	    pdf.setLineWidth(0.1);
		pdf.line(10, 60, 10, 400);
		pdf.text(10, 65, 'SrNo');
		pdf.setLineWidth(0.1);
		pdf.line(16, 60, 16, 400);
		pdf.text(20, 65, 'Student');
		pdf.text(20, 67, 'Name');
		
		
		pdf.setLineWidth(0.1);
		pdf.line(38, 60, 38, 400);
		var x=40; 
		var y=65;
		<c:forEach var="lecture" items="${comAttendances.lectureList}">

		var lec="${lecture}";

		
		var lec="${lecture}";
		var start=lec.substring(0,lec.indexOf("-"));
		var end=lec.substring(lec.indexOf("-")+1,lec.length);
		pdf.text(x, y,start);
		var j=y+3;
		pdf.text(x, j,'to');
		var o=j+3;
		pdf.text(x, o,end);
		z=x+12;
		pdf.setLineWidth(0.1);
		pdf.line(z, 60, z, 400);
		
		x=x+15;
	    </c:forEach>
	    z=x+12;
	    pdf.setLineWidth(0.1);
		pdf.line(z, 60, z, 400);
	    pdf.setLineWidth(0.1);
		pdf.line(187, 60, 187, 400);
	    pdf.text(190, 65, 'Present');
	    pdf.setLineWidth(0.1);
		pdf.line(200, 60, 200, 400);
		
		var x=40; 

		var y=77;
		var y=76;
		<c:forEach var="sub" items="${comAttendances.subList}">


		var str = '${sub}';
		var ret = str.split(" ");
		var wrd="";
		for ( var i = 0, len = ret.length; i < len; ++i) {
			
			 var user = ret[i];
			 wrd+=user.charAt(0);
       }
		
		pdf.text(x,y,wrd);
		x=x+15;
	    </c:forEach>

	    
	    pdf.setLineWidth(0.5)
		pdf.line(10, 60, 200, 60)
	    pdf.setLineWidth(0.5)
		pdf.line(10, 80, 200, 80)
		
		var a=40;
		var	b=87;
		var n=1;
		<c:set var='total' value="0" />
	<c:forEach var="studentList" items="${comAttendances.studentList}">
		 
		pdf.text(12,b,''+ n);
		pdf.text(20, b,''+"${studentList.firstName}");
		b=b+3;
		pdf.text(20,b,''+"${studentList.lastName}"); 
		n=n+1;
		b=b+6;
		var g=44;
		<c:set var='present' value="0" />
	<c:forEach var="studAttendance" items="${studentList.studAttendance}">
	     var r=b-7;
    	pdf.text(g, r,''+"${studAttendance}");
    	var m=r+3;
    	   pdf.setLineWidth(0.1)
    		pdf.line(10, m, 200, m)
    	<c:set var='total' value="${total+1}" />
    	
    	    <c:if test="${studAttendance=='P'}">
		             <c:set var='present' value="${present+1}" />
	       </c:if>
		g=g+15;
	</c:forEach>
	var u=b;
	var s=b-9
	   pdf.text(193, s,''+"${present}"+" /");     
	   pdf.text(196, s,''+"${total}"); 
	
	   <c:set var='total' value="0" />
	if (b >= 285) {
		pdf.addPage(210,297);
		
		for( var j = 52; j < 190; )
			{
		pdf.setLineWidth(0.1);
		pdf.line(j, 0, j, m);
		j=j+15;
			}
	
		pdf.setLineWidth(0.1);
		pdf.line(200, 0, 200, m);
		pdf.setLineWidth(0.1);
		pdf.line(16, 0, 16, m);
		pdf.setLineWidth(0.1);
		pdf.line(38, 0, 38, m);
		pdf.setLineWidth(0.1);
		pdf.line(10, 0, 10, m);
		b=10;
		
	}
	

</c:forEach>
		
		
		
		
		
		/*pdf.text(165, 65, 'Vote');
		pdf.text(185, 65, 'Max');
		pdf.setTextColor(0, 0, 0);
		pdf.setFontType('bold');
		pdf.setFontSize(8);
		pdf.text(10, 72, '1');
		pdf.text(26, 72, ''+"${list.q1}");
		pdf.text(165, 72, ''+"${list.avg1}");
		pdf.text(185, 72, '5.0');
		
		pdf.text(10, 80, '2');
		pdf.text(26, 80, ''+"${list.q2}");
		pdf.text(165, 80, ''+"${list.avg2}");
		pdf.text(185, 80, '5.0');
		
		pdf.text(10, 88, '3');
		pdf.text(26, 88, ''+"${list.q3}");
		pdf.text(165, 88, ''+"${list.avg3}");
		pdf.text(185, 88, '5.0');
		
		pdf.text(10, 96, '4');
		pdf.text(26, 96, ''+"${list.q4}");
		pdf.text(165, 96, ''+"${list.avg4}");
		pdf.text(185, 96, '5.0');
		
		pdf.text(10, 104, '5');
		pdf.text(26, 104, ''+"${list.q5}");
		pdf.text(165, 104, ''+"${list.avg5}");
		pdf.text(185, 104, '5.0');
		
		pdf.text(10, 112, '6');
		pdf.text(26, 112, ''+"${list.q6}");
		pdf.text(165, 112, ''+"${list.avg6}");
		pdf.text(185, 112, '5.0');
		
		pdf.text(10, 120, '7');
		pdf.text(26, 120, ''+"${list.q7}");
		pdf.text(165, 120, ''+"${list.avg7}");
		pdf.text(185, 120, '5.0');
		
		pdf.text(10, 128, '8');
		pdf.text(26, 128, ''+"${list.q8}");
		pdf.text(165, 128, ''+"${list.avg8}");
		pdf.text(185, 128, '5.0');
		
		pdf.text(10, 136, '9');
		pdf.text(26, 136, ''+"${list.q9}");
		pdf.text(165, 136, ''+"${list.avg9}");
		pdf.text(185, 136, '5.0');
		
		pdf.text(10, 144, '10');
		pdf.text(26, 144, ''+"${list.q10}");
		pdf.text(165, 144, ''+"${list.avg10}");
		pdf.text(185, 144, '5.0'); */
		
	/* 	pdf.setLineWidth(0.1);
		pdf.line(10, 150, 200, 150);
		
		pdf.setFontSize(10);
		pdf.setFontType('bold')
		pdf.setTextColor(0, 100, 0);
		pdf.text(10, 160, 'Total Score :');
		pdf.text(165, 160, ''+"${list. total_avg_Rate}");
		pdf.text(185, 160, '5.0');
		pdf.setLineWidth(0.5);
		pdf.line(10, 168, 200, 168);*/
		pdf.save('Daily_Attendance.pdf');
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
