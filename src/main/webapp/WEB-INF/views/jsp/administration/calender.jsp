<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> 
<body class="hold-transition skin-blue fixed sidebar-mini" >
<div class="wrapper">
<div class="content-wrapper">
<!-- Main content -->
    <section class="content " >
          <div class="col-md-9 col-sm-9">
		    <section class="content-header">
				<div class=" col-md-9 col-sm-9" style="padding-top: 17px;">  
				    <div class=" col-md-3 col-sm-3"><font size="3px"><b>Calendar</b></font></div>
			    </div>
			 </section >
             <!-- Calendar -->
			 <div class="col-md-12 col-sm-12" style="height:495px ; overflow-x:hidden">
			     <div class="box box-default " style="border-radius:7px;">
                     <div id="calendar"></div>
				 </div>
			  </div>
			</div>
	  <!-- /.col -->
      <div class="col-md-3 col-sm-3" style="background-color:#fff;height:85.3%;">
	      <div style="border-radius:21px;">
	       <!-- Event Details -->
			<div id="eventDetails"  style="padding-top:25px; display: none">
				<h4>Event On : <span id="eventDate"></span></h4> 
				<div style="height:440px;overflow-x: hidden;">
				    <table class="table table-striped ">
				        <tr>
		                  <td style="width:7%">Title </td>
		                  <td >
		                    <div >
		                    	 <font style="color:green" size="2"><span id="evntTitle"></span></font>
		                    </div>
		                  </td>
		                </tr>
		                <tr>
		                  <td>Start Date </td>
		                  <td>
		                    <div>
		                    	 <font style="color:green" size="2"><span id="startDt"></span></font>
		                    </div>
		                  </td>
		                </tr>
		                <tr>
		                  <td>End Date </td>
		                  <td>
		                    <div>
		                    	 <font style="color:green" size="2"><span id="endDt"></span></font>
		                    </div>
		                  </td>
		                </tr>
		                <tr>
		                  <td>Venue Details</td>
		                  <td>
		                    <div>
		                    	 <font style="color:green" size="2"><span id="venuDtl"></span></font>
		                    </div>
		                  </td>
		                </tr>
		                <tr>
		                  <td>Descriptions </td>
		                  <td>
		                    <div>
		                    	 <font style="color:green" size="2"><span id="eventDtl"></span></font>
		                    </div>	 
		                  </td>
		                </tr>
		             </table>
				</div> 
			 </div>
			<!-- /Event Details --> 
			<!-- Notices -->
			<div id="noticeDetails" style="background-color:#fff;height:85.3%;display: none" >
              <div >
		        <div style="border-radius:21px;">
			      <!-- /.box-header -->
					<div  style="padding-top:25px">
						<h4>Notice On : <span id="noticeDate"></span></h4> <hr>
						<div style="height:440px;overflow-x: hidden;" id="notices"></div> 
				    </div>
			      </div>
		       </div>	
	        </div>
			<!-- /Notices --> 
		  </div>
	  </div>
	   
	</section>
   <!-- /.row -->
  </div>
 </div>
 <!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-125893256-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-125893256-1');
</script>
</body>
<script>
  $(function () {  
	var pathname = window.location.pathname;
	 //Date for the calendar events (dummy data)
    var date = new Date();
    var d = date.getDate(),
        m = date.getMonth(),
        y = date.getFullYear();
	    $('#calendar').fullCalendar({
	    	  header: {
	        left: 'prev,next today',
	        center: 'title',
	        right: 'month,agendaWeek,agendaDay'
	      },
	      buttonText: {
	        today: 'today',
	        month: 'month',
	        week: 'week',
	        day: 'day'
	      },
	      events:"/"+pathname.split("/")[1]+"/web/taskforce/service/event/list",
	      eventClick: function(event) {
	    	       document.getElementById("eventDetails").style.display="block";
	    	       document.getElementById("noticeDetails").style.display="none";
	 	       document.getElementById("evntTitle").innerHTML=event.title;
	    	       document.getElementById("startDt").innerHTML=event.start.format('DD-MM-YYYY');
	    	       document.getElementById("eventDate").innerHTML=event.start.format('DD-MM-YYYY');
	    	     
	           if((new Date(event.start)).toISOString().slice(0, 10)){
	              document.getElementById("endDt").innerHTML=event.end.format('DD-MM-YYYY');
	           }else{
	        	      document.getElementById("endDt").innerHTML=event.start.format('DD-MM-YYYY');
	           }
	           document.getElementById("venuDtl").innerHTML=event.venu;
		       document.getElementById("eventDtl").innerHTML=event.eventDtl;
	        },	        
	        dayClick:function(date){
	        	 document.getElementById("noticeDate").innerHTML=date.format('DD-MM-YYYY');
  	    	     
	    	      $.ajax({
	    	    	   url: "/"+pathname.split("/")[1]+"/web/taskforce/service/notification/calendar" ,
	    	    	   data: 'date='+date.format("YYYY-MM-DD ") ,
	    	    	   type: "GET",
	    	    	   success: function(data) {	   
	    	    		   $('#notices').empty();
	    	    		   if(data.length > 0){
	    	    			   document.getElementById("noticeDetails").style.display="block";
	    	    			   document.getElementById("eventDetails").style.display="none";
	    	    			  
	    	    		 	    for ( var i = 0, len = data.length; i < len; ++i) {
	    	    		           var notice = data[i];
	    	    		           $('#notices').append('<div class="box" style="background-color: #eff5ec">'+
	    	    		            '<div style="padding-left:10px;"><a href="service/notification/' + notice.notificationId+'"><font size="4px" style="color:#008000">'+notice.notificatiosHeadline+'</font></a></div>'+
	    	    		            '<p style="padding-left:10px ; color:gray"> '+notice.notificationDetails.substr(0, 70)+'</p><div style="padding-left:10px"> Date: '+notice.notificationFromDate.substr(0, 5)+
	    	    		            ' To '+notice.notificationToDate.substr(0, 10)+'</div>'+'<div style="padding-left:10px">'+' Time: '+notice.notificationFromDate.substr(11, 15)+' To '+notice.notificationToDate.substr(11, 15)+'</div></div>  '
	    	    		            );
	    	    		         } 
	    	            }else{
	    	            	   document.getElementById("noticeDetails").style.display="block";
	    	    			   document.getElementById("eventDetails").style.display="none";
	    	               $('#notices').append('<div><div style="padding-left:10px;"><a href=""><font size="4px" style="color:#008000">Notice Not Available</font></a></div></div>');
	    	             }
	    	    	    },
	    	    	   error: function(data) {
		    	    	    alert("Error");
		    	    	   }
	    	    	   })
	        }
         });
	    $('#calendar').fullCalendar('option', 'aspectRatio',1.8); 	
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
