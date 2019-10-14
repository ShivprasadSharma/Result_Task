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
<c:url var="save" value="/web/taskforce/student/attendance/save" />
<body class="hold-transition skin-blue sidebar-mini">
<div >
  <div class="content-wrapper">
    <!-- Main content -->
    <section class="content">
     <div class="col-md-6"  >
          <div class="box box-success">
            <div class="box-header with-border">
            <i class="fa fa-bar-chart-o"></i>
              <h3 class="box-title">Upload Attendance File</h3>
			  <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
              </div>
            </div>
            <div class="box-body">
              <div class="chart" style="height:167px">
              <div align="center">
                <form action = "${save}" method = "post" enctype="multipart/form-data">
            		    	<div class="form-group" >
		          		<h4 style="margin-top:13px">Select file to upload</h4>
 		          		<input type="file" name="ExcelFile" accept="vnd.ms-excel/*" value=" Upload Media" class="btn " /> 
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
          
          <div class="box box-success">
            <div class="box-header with-border">
            <i class="fa fa-bar-chart-o"></i>
              <h3 class="box-title">Student Attendance</h3>

              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
              </div>
            </div>
            <div class="box-body">
              <div class="chart" style="height:167px;" align="center">
                    <h4>Select Subject</h4>
                     <select class="form-control" style="width:190px">
                         <option> Subject 1</option>
                         <option> Subject 2</option>
                         <option> Subject 3</option>
                         <option> Subject 4</option>
                     </select>
                     <div align="center"><br>
				  		<input type="submit" value="Submit" class="btn btn-success" />
 			    		</div>
 			   </div>
            </div>
            <!-- /.box-body -->
          </div>
        </div>
        <div class="col-md-6">
          <div class="box box-success">
            <div class="box-header with-border">
            <i class="fa fa-bar-chart-o"></i>
              <h3 class="box-title">Subject</h3>
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
              </div>
            </div>
            <div class="box-body">
              <div class="chart">
                <canvas id="barChart" style="height:167px"></canvas>
              </div>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- Donut chart -->
          <div class="box box-success">
            <div class="box-header with-border">
              <i class="fa fa-bar-chart-o"></i>
              <h3 class="box-title">Subject </h3>
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
              </div>
            </div>
            <div class="box-body">
              <div id="donut-chart" style="height: 167px;"></div>
            </div>
            <!-- /.box-body-->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
    </section>
    <!-- /.content -->
  </div>
  <!-- Control Sidebar -->
</div>
<script>
  $(function () {
	  var pathname = window.location.pathname;
     /*
     * DONUT CHART
     */
      $.getJSON("/"+pathname.split("/")[1]+"/web/taskforce/service/attendance/subject",
     		function(data) {    	
        	          donutData  = [
    	                  {label: "Present", data: data[1], color: "green"},
    	                  {label: "Absent", data: data[4]+data[5], color: "red"}
    	                ];
    	              $.plot("#donut-chart", donutData, {
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
    	       });
     });
  function labelFormatter(label, series) {
    return '<div style="font-size:12px; text-align:center; padding:2px; color: #fff; font-weight: 600;">'
        + label
        + "<br>"
        + Math.round(series.percent) + "%</div>";
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
