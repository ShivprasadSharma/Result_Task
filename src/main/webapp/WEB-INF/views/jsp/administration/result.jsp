<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:url var="save" value="/web/taskforce/service/result/save" />
<c:url var="getAttendance" value="/web/taskforce/service/subject/list" />
<c:url var="resultReport" value="/web/taskforce/service/result/report/student" />
<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="wrapper">
<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header"  style="padding-top: 31px;">
      <h1>Result <small></small></h1>
    </section>

    <!-- Main content -->
    <section style="height:80%">
      <div>
        <div class="col-md-6">
          <!-- AREA CHART -->
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Upload File</h3>
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
              </div>
            </div>
            <div class="box-body">
              <canvas id="areaChart" style="height:0px"></canvas>
               <div class="chart" style="height:36%">
                <div align="center">
	                <form action = "${save}" method="POST"  enctype="multipart/form-data">
	            		    	<div class="form-group" >
			          		<h4 style="margin-top:13px">Select file to upload</h4>
	 		          		<input type="file" name="ResultPDFfile" accept="vnd.ms-excel/*" value=" Upload Media" class="btn " /> 
	 		          		<input type="hidden" name="empid" value="${sessionScope.user.employeeNo}">
			        		</div>
			    			<div>
					  		<input type="submit" value="Upload" class="btn btn-success" />
	 			    		</div>
	 			    </form>
 			    </div> 
              </div>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->

          <!-- DONUT CHART -->
          <div class="box box-danger">
            <div class="box-header with-border">
              <h3 class="box-title">Result</h3>

              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
              </div>
            </div>
            <div class="box-body">
              <canvas id="lineChart" style="height:0px"></canvas>           
              <div class="chart" style="height:36%">
              <div class="row" align="center">      
              	  <input type="radio" value="1" name="sem" id="sem1" checked  " > &nbsp;<label>Sem I</label> &nbsp;&nbsp;&nbsp;             
              	  <input type="radio" value="2" name="sem" id="sem2" "> &nbsp;<label>Sem II</label>         	
              </div>
               <div class="row" style="margin-top: 20px" align="center">
               <form action="${resultReport}" method="post">
                <div class="col-md-4">
                    <select  name="yearId" class="form-control" style="width:120px" id="selectYr" >
                           <option value="0">Select Year</option>                           
						    <c:forEach var="item" items="${academicYear}" >
						        <option value="<fmt:formatDate pattern="yyyy" value="${item.yearStartDate}"/>" ${item.yearId == selectedDept ? 'selected="selected"' : ''}><fmt:formatDate pattern="yyyy" value="${item.yearStartDate}"/> </option>
						     </c:forEach> 
						</select> 				
                    </div>
                    <sec:authorize access="hasAnyRole('ROLE_TEACHER')">
 			    		<div class="col-md-4" >
 			    		 <select  name="branchId" class="form-control" style="width:120px" id="selectDep" onchange="Javascript: getResult(); getsubjectlist(this);" >
                         <option value="0">Select Branch</option>
						    <c:forEach var="item" items="${departmets}" >
						       <option value="${item.dep_id}" ${item.dep_id == selectedDept ? 'selected="selected"' : ''}>${item.dep_name}</option>
						    </c:forEach> 
					   </select> 
					    <input type="hidden" id="subject" value="0">				                     
 			    	  </div>
 			    	  
 			    	  </sec:authorize>
 			    	   <sec:authorize access="hasAnyRole('ROLE_HOD','ROLE_ADMIN')">
 			    	   <div class="col-md-4" >
 			    		 <select  name="branchId" class="form-control" style="width:120px" id="selectDep" >
                         <option value="0">Select Branch</option>
						    <c:forEach var="item" items="${departmets}" >
						       <option value="${item.dep_id}" ${item.dep_id == selectedDept ? 'selected="selected"' : ''}>${item.dep_name}</option>
						    </c:forEach> 
					   </select> 
				    </div>
 			    	    <div class="col-md-4" align="center">
                      <select  name="subjectId" class="form-control" style="width:120px" id="subject" onchange="getResult()">
                           <option>select subject</option>
						    <c:forEach var="item" items="${subject}" >
						       <option value="${item.sub_id}" ${item.sub_id == selectedDept ? 'selected="selected"' : ''}>${item.academicSubject.subject_name}</option>
						    </c:forEach> 
						</select> 
                     </div> 
                     <div class='col-sm-4'>
       <select id="classId" class="form-control" name="classId" style="width:120px" onchange="getsubjectlist(this)">
       <option>select class</option>
       <option value="1">FE</option>
         <option value="2">SE</option>
           <option value="3">TE</option>
             <option value="4">BE</option>
    </select>           
        </div>       
                      <!-- <div class='col-sm-4'>
       <select id="class"  name="classId" class="form-control" style="width:120px" required="required">                                  <option value="0">Select semester</option>
       <option value="1">I</option>
         <option value="2">II</option>
           <option value="3">III</option>
             <option value="4">Iv</option>
             <option value="5">v</option>
             <option value="6">VI</option>
             <option value="7">VII</option>
             <option value="8">VII</option>
    </select>  
        </div>-->
                        </sec:authorize>
 
                                         <%-- <div class='col-sm-4' >
                     <input type="submit" value="Report" class="btn btn-success">
                     </div> --%>
                     
                     </form>                    
                	</div>
 			   </div>		   
            </div><!-- /.box-body -->
          </div>
         <!-- /.box -->
        </div>
        <!-- /.col (LEFT) -->
        <div class="col-md-6">
          <!-- LINE CHART -->
          <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">OverAll Result</h3>
              
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                
        <ul style="list-style-type:square">
  <li style="color:green;font-size:25px"><h4><span style="color:black">Pass:%<div id="overallPass"></div></span></h4></li>
  <li style="color:red;font-size:25px"><h4><span style="color:black">Fail:%<div id="overallfail"></div></span></h4></li>
  <li style="color:yellow;font-size:25px"><h4><span style="color:black">Atkt:%<div id="overallatkt"></div></span></h4></li>
  </ul>
              </div>
              <div>
              
              </div>
            </div>
            <div class="box-body">
              <div class="chart">              
                 <canvas id="pieChart" style="height:36%"></canvas>
              </div>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
          <!-- BAR CHART -->
          <div class="box box-success">
            <div class="box-header with-border">
              <h3 class="box-title">Subject Result</h3>
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                
      <ul style="list-style-type:square">
    <li style="color:green;font-size:25px"><h4><span style="color:black">Pass:%<div id="subjectpass"></div></span></h4></li>
  <li style="color:red;font-size:25px"><h4><span style="color:black">Fail:%<div id="subjectfail"></div></span></h4></li>
 </ul>
              </div>
            </div>
            <div class="box-body">
              <div class="chart">
                <canvas id="barChart" style="height:36%"></canvas>
              </div>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col (RIGHT) -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
  </div>
 </div>
<script>

	
function getResult() {
	
	  
	  var pathname = window.location.pathname;
	  var sub_id=0;
	  var yr = document.getElementById("selectYr").value;
	  var sem = document.querySelector('input[name=sem]:checked').value;
	  var dep_id = document.getElementById("selectDep").value;	
	   sub_id = document.getElementById("subject").value;
      
	   
	     $.getJSON("/"+pathname.split("/")[1]+"/web/taskforce/service/result/summary/sub",{yr:yr,sem:sem,depid:dep_id,sub:sub_id},
	         function(data) {
	    	 
			 var splitData = data.split(",");
			 var PassStudent=[];
			 var FailStudent =[];
			 var subjects =[];
			
			 	for(var j=0;j<splitData.length;j++)
			    	 {
			 		 if((j%2) == 0 )
				    	 {
			 			splitData[j] = splitData[j].replace(']','');
			 			subjects.push(splitData[j].replace('[',''));
				    	 }else
				    	     {
				    	       PassStudent.push(splitData[j].replace(']',''));
				    	       if(splitData[j].replace(']','') != 0 )
				    	       {
				    	        	 FailStudent.push((100 - (splitData[j].replace(']',''))).toFixed(2));
				    	       }else
				    	       {
				    	        	 FailStudent.push(0);
				    	       }			    	        	      	
				    	      }
		    	        	  } 
			 	
			 
			 	
	  			$("#subjectpass").text(PassStudent);
	  			
	  			$("#subjectfail").text(FailStudent);

				var areaChartCanvas = $("#areaChart").get(0).getContext("2d");
			    // This will get the first returned node in the jQuery collection.
			    var areaChart = new Chart(areaChartCanvas);
			    var areaChartData = {
			      labels: subjects,
			      
			      datasets: [
			        {
			          label: "Pass",
			          fillColor: "#4CAF50",
			          strokeColor: "rgba(210, 214, 222, 1)",
			          pointColor: "rgba(210, 214, 222, 1)",
			          pointStrokeColor: "#c1c7d1",
			          pointHighlightFill: "#fff",
			          pointHighlightStroke: "rgba(220,220,220,1)",
			          data: PassStudent
			        },
			        {
			          label: "Fail",
			          fillColor: "red",
			          strokeColor: "rgba(60,141,188,0.8)",
			          pointColor: "#3b8bba",
			          pointStrokeColor: "rgba(60,141,188,1)",
			          pointHighlightFill: "#fff",
			          pointHighlightStroke: "rgba(60,141,188,1)",
			          data: FailStudent,
			          
			        }
			      ]
          };

    var areaChartOptions = {
      //Boolean - If we should show the scale at all
      showScale: true,
      //Boolean - Whether grid lines are shown across the chart
      scaleShowGridLines: false,
      //String - Colour of the grid lines
      scaleGridLineColor: "rgba(0,0,0,.05)",
      //Number - Width of the grid lines
      scaleGridLineWidth: 1,
      //Boolean - Whether to show horizontal lines (except X axis)
      scaleShowHorizontalLines: true,
      //Boolean - Whether to show vertical lines (except Y axis)
      scaleShowVerticalLines: true,
      //Boolean - Whether the line is curved between points
      bezierCurve: true,
      //Number - Tension of the bezier curve between points
      bezierCurveTension: 0.3,
      //Boolean - Whether to show a dot for each point
      pointDot: false,
      //Number - Radius of each point dot in pixels
      pointDotRadius: 4,
      //Number - Pixel width of point dot stroke
      pointDotStrokeWidth: 1,
      //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
      pointHitDetectionRadius: 20,
      //Boolean - Whether to show a stroke for datasets
      datasetStroke: true,
      //Number - Pixel width of dataset stroke
      datasetStrokeWidth: 2,
      //Boolean - Whether to fill the dataset with a color
      datasetFill: true,
      //String - A legend template
      //Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
      maintainAspectRatio: true,
      //Boolean - whether to make the chart responsive to window resizing
      responsive: true
    };

    //Create the line chart
    areaChart.Line(areaChartData, areaChartOptions);

    //-------------
    //- LINE CHART -
    //--------------
    var lineChartCanvas = $("#lineChart").get(0).getContext("2d");
    var lineChart = new Chart(lineChartCanvas);
    var lineChartOptions = areaChartOptions;
    lineChartOptions.datasetFill = false;
    lineChart.Line(areaChartData, lineChartOptions);

   
    //-------------
    //- BAR CHART -
    //-------------
    var barChartCanvas = $("#barChart").get(0).getContext("2d");
    var barChart = new Chart(barChartCanvas);
    var barChartData = areaChartData;
    barChartData.datasets[1].fillColor = "red";
    barChartData.datasets[1].strokeColor = "red";
    barChartData.datasets[1].pointColor = "#00a65a";
    var barChartOptions = {
      //Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
      scaleBeginAtZero: true,
      //Boolean - Whether grid lines are shown across the chart
      scaleShowGridLines: true,
      //String - Colour of the grid lines
      scaleGridLineColor: "rgba(0,0,0,.05)",
      //Number - Width of the grid lines
      scaleGridLineWidth: 1,
      //Boolean - Whether to show horizontal lines (except X axis)
      scaleShowHorizontalLines: true,
      //Boolean - Whether to show vertical lines (except Y axis)
      scaleShowVerticalLines: true,
      //Boolean - If there is a stroke on each bar
      barShowStroke: true,
      //Number - Pixel width of the bar stroke
      barStrokeWidth: 2,
      //Number - Spacing between each of the X value sets
      barValueSpacing: 5,
      //Number - Spacing between data sets within X values
      barDatasetSpacing: 1,
      //String - A legend template
      //Boolean - whether to make the chart responsive
      responsive: true,
      maintainAspectRatio: true
    };
   
    barChartOptions.datasetFill = false;
    barChart.Bar(barChartData, barChartOptions);
	  });
    $.getJSON("/"+pathname.split("/")[1]+"/web/taskforce/service/result/summary",{yr:yr,sem:sem,depid:dep_id,sub:sub_id},
  	      function(data) {
    	 
    	        var d = data.split(",");
    	  
  			var atkt = parseInt(d[2].replace("]","")) ;
  			
  		
  			var fail = parseInt(d[1]);
  			
  			
  			var t = fail + atkt;
  			
  			
  			var totalStudents = parseInt(d[0].replace("[",""));
  			
  			var p = (((totalStudents-t)/totalStudents)*100).toFixed(2);
  			 			
  			
  			$("#overallPass").text(p);
  			atktP   
  			var failP = ((atkt/totalStudents)*100).toFixed(2);
  			$("#overallfail").text(failP);
            
  			var atktP = ((fail/totalStudents)*100).toFixed(2); 
  			$("#overallatkt").text(atktP);
  			
  			//alert(p+" "+failP);
  			
  //-------------
  //- PIE CHART -
  //-------------
  // Get context with jQuery - using jQuery's .get() method.
  var pieChartCanvas = $("#pieChart").get(0).getContext("2d");
  var pieChart = new Chart(pieChartCanvas);
  var PieData = [
    {	
      value: p,
      color: "#4CAF50",
      highlight: "#81C784",
      label: "Pass"
    },
    {
      value: failP,
      color: "#F95247",
      highlight: "#E0E0E0",
      label: "Fail"
    },
    {
      value: atktP,
      color: "#FFEB3B",
      highlight: "#f39c12",
      label: "A.T.K.T."
    }
  ];
  var pieOptions = {
    //Boolean - Whether we should show a stroke on each segment
    segmentShowStroke: true,
    //String - The colour of each segment stroke
    segmentStrokeColor: "#fff",
    //Number - The width of each segment stroke
    segmentStrokeWidth: 2,
    //Number - The percentage of the chart that we cut out of the middle
    percentageInnerCutout: 50, // This is 0 for Pie charts
    //Number - Amount of animation steps
    animationSteps: 100,
    //String - Animation easing effect
    animationEasing: "easeOutBounce",
    //Boolean - Whether we animate the rotation of the Doughnut
    animateRotate: true,
    //Boolean - Whether we animate scaling the Doughnut from the centre
    animateScale: false,
    //Boolean - whether to make the chart responsive to window resizing
    responsive: true,
    // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
    maintainAspectRatio: true,
    //String - A legend template
  };
  //Create pie or douhnut chart
  // You can switch between pie and douhnut using the method below.
  pieChart.Doughnut(PieData, pieOptions);
  
  });
};

function getsubjectlist(){
	
	 
	  var x = document.querySelector('input[name=sem]:checked').value;
	  
	   
	  var y = document.getElementById("selectDep").value;
	  var z = document.getElementById("classId").value;
	  
	  

    $.ajax({
         type: "POST",
         url: "/"+location.pathname.split("/")[1]+"/web/taskforce/service/subject/list",
         data:'deptid='+ y +'&classid='+ z+'&semid='+ x,
         dataType: 'json',
         success: function(data){ 
        	 alert(data);
        	 if(data.length > 0){
 		        $('#subject').append("<option value='-1'>Select Subject...</option>");
 		            for ( var i = 0, len = data.length; i < len; ++i) {
 		                var sub = data[i];
 		               
 		                $('#subject').append("<option value=\"" + sub.sub_id + "\">" + sub.subject_name +"</option>");
 		            }
            }
         },
	     error:function(data){
	    	    alert("error");
	     }
    });
}
</script>
</body>
