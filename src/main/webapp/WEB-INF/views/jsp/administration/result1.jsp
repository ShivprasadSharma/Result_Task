<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:url var="save" value="/web/taskforce/service/result/save" />
<c:url var="getAttendance" value="/web/taskforce/service/attendance/subject/" />
<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="wrapper">
 <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header" style="padding-top: 31px;">
       <h4>Result Summary</h4>
    </section>
    <!-- Main content -->
    <section style="height:80%">
      <div>
        <div class="col-md-6">
          <!-- AREA CHART -->
          <div class="box box-success">
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
          <div class="box box-success">
            <div class="box-header with-border">
              <h3 class="box-title">Result</h3>
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
              </div>
            </div>
            <div class="box-body">
              <canvas id="pieChart" style="height:0px"></canvas>
              <div class="chart" style="height:36%">
              <div class="row" align="center">      
              	  <input type="radio" value="1" name="sem" id="sem1" checked  onclick="getResult()" > &nbsp;<label>Sem I</label> &nbsp;&nbsp;&nbsp;             
              	  <input type="radio" value="2" name="sem" id="sem2" onclick="getResult()"> &nbsp;<label>Sem II</label>         	
              </div>
               <div class="row">
                <div class="col-md-6">
                    <h4>Select Year</h4>
                      <select  class="form-control" style="width:190px" id="selectYr" >
                           <option value="0">Select Year</option>                           
						    <c:forEach var="item" items="${academicYear}" >
						        <option value="<fmt:formatDate pattern="yyyy" value="${item.yearStartDate}"/>" ${item.yearId == selectedDept ? 'selected="selected"' : ''}><fmt:formatDate pattern="yyyy" value="${item.yearStartDate}"/> </option>
						     </c:forEach> 
						</select> 				
                    </div>
 			    		<div class="col-md-6">
 			    		<h4>Select Branch</h4>
                       <select  class="form-control" style="width:190px" id="selectDep" onchange="getResult()" >
                         <option value="0">Select Branch</option>
						    <c:forEach var="item" items="${departmets}" >
						       <option value="${item.dep_id}" ${item.dep_id == selectedDept ? 'selected="selected"' : ''}>${item.dep_name}</option>
						    </c:forEach> 
					   </select> 				                     
 			    	  </div>
 			    	</div>
 			   </div>		   
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col (LEFT) -->
        <div class="col-md-6">
          <!-- LINE CHART -->
          <div class="box box-success">
            <div class="box-header with-border">
              <h3 class="box-title">Overall Result </h3>
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
              </div>
            </div>
            <div class="box-body">
              <div class="chart">
                <canvas id="lineChart" style="height:0px"></canvas>
                <div id="donut-chart" style="height:36%;"></div>
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

<!-- page script -->
<script>
  function getResult(){
	  var pathname = window.location.pathname;
	  var yr = document.getElementById("selectYr").value;
	  var sem = document.querySelector('input[name=sem]:checked').value;
	  var dep_id = document.getElementById("selectDep").value;	
   $.getJSON("/"+pathname.split("/")[1]+"/web/taskforce/service/result/summary",{yr:yr,sem:sem,depid:dep_id},
      function(data) {
		   	   var d = data.split(",");
		    	   var fail = parseInt(d[2].replace("]","")) ;
		    	   var atkt = parseInt(d[1]);
		    	   var t = fail + atkt;
		    	   var totalStudents = parseInt(d[0].replace("[","") / 7);
		    	   var p = (((totalStudents-t)/totalStudents)*100).toFixed(2);
		    	   var atktP = ((atkt/totalStudents)*100).toFixed(2);
		    	   var failP = ((fail/totalStudents)*100).toFixed(2);
		    	   if(!jQuery.isEmptyObject(data)){
		    		  donutData  = [
		    			            {label: "PASS", data: p , color: "green"},
		    			            {label: "ATKT", data: atktP, color: "#F4D03F"},
		    			            {label: "FAIL", data: failP, color: "red"}
		    	                   ];       	              
		    	              $.plot("#donut-chart", donutData, 
		    	            		{
		    	                  series: {
		    	                    		pie: {
		    	                      		show: true,
		    	                      		radius: 1,
		    	                      		innerRadius: 0.4,
		    	                     		 label: {
		    	                      		  			show: true,
		    	                        					radius: 2 / 3,
		    	                        					formatter: labelFormatter,
		    	                        					threshold: 0.1
		    	                      				}
		    	            		    			  }
		    	                  		},
		    	                  		legend: {
		    	                    				show: false
		    	                  				}
		    	             	});
		    	              function labelFormatter(label, series) {
		 	                	 return '<div style="font-size:12px; text-align:center; padding:2px; color: #fff; font-weight: 600;">'
		 	                        + label + "<br>" + series.percent + "%</div>";
		 	          }
		    	        }   
		          }); 	    
		    $.getJSON("/"+pathname.split("/")[1]+"/web/taskforce/service/result/summary/sub",{yr:yr,sem:sem,depid:dep_id},
		    function(data) {	
		      	 var splitData = data.split(",");
		          var PassStudent=[];
		    	      var FailStudent =[];
		    	      var months =[];
		    	      for(var j=0;j<splitData.length;j++)
		    	      {
			    	    if((j%2) == 0 )
			    	    {
			    	       months.push(splitData[j].replace('[',''));
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
		  	        // Get context with jQuery - using jQuery's .get() method.
	                 var areaChartCanvas = $("#areaChart").get(0).getContext("2d");
	                 // This will get the first returned node in the jQuery collection.
	                 var areaChart = new Chart(areaChartCanvas);
	                 var areaChartData = {
	                   labels: months,
	                   datasets: [
	                     {
	                       label: "Pass",
	                       fillColor: "green",
	                       strokeColor: "rgba(210, 214, 222, 1)",
	                       pointColor: "rgba(210, 214, 222, 1)",
	                       pointStrokeColor: "#c1c7d1",
	                       pointHighlightFill: "#fff",
	                       pointHighlightStroke: "rgba(220,220,220,1)",
		           	       data: PassStudent
		           	       
	                     },
	                     {
	                       label: "Fail",
	                       fillColor: "rgba(60,141,188,0.9)",
	                       strokeColor: "rgba(60,141,188,0.8)",
	                       pointColor: "#3b8bba",
	                       pointStrokeColor: "rgba(60,141,188,1)",
	                       pointHighlightFill: "#fff",
	                       pointHighlightStroke: "rgba(60,141,188,1)",
	                       data: FailStudent
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
	                 //- PIE CHART -
	                 //-------------
	                 // Get context with jQuery - using jQuery's .get() method.
	                 var pieChartCanvas = $("#pieChart").get(0).getContext("2d");
	                 var pieChart = new Chart(pieChartCanvas);
	                 var PieData = [
	                   {
	                     value: 700,
	                     color: "#f56954",
	                     highlight: "#f56954",
	                     label: "Chrome"
	                   },
	                   {
	                     value: 500,
	                     color: "#00a65a",
	                     highlight: "#00a65a",
	                     label: "IE"
	                   },
	                   {
	                     value: 400,
	                     color: "#f39c12",
	                     highlight: "#f39c12",
	                     label: "FireFox"
	                   },
	                   {
	                     value: 600,
	                     color: "#00c0ef",
	                     highlight: "#00c0ef",
	                     label: "Safari"
	                   },
	                   {
	                     value: 300,
	                     color: "#3c8dbc",
	                     highlight: "#3c8dbc",
	                     label: "Opera"
	                   },
	                   {
	                     value: 100,
	                     color: "#d2d6de",
	                     highlight: "#d2d6de",
	                     label: "Navigator"
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
	         }
      </script>  
  </body> 
</html>