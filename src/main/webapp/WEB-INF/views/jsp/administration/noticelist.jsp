<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url var="getNoticeDtl" value="/web/taskforce/service/notification/" />

<Script>
	function goToNoticeDetails(notificationId)
	{
		$('.nav-tabs a[href="#listDetails"]').tab('show');
		$("#listDetails").html($("#masterFormModalLoading").html());
		$.get('${getNoticeDtl}' + notificationId, function(result)
		{
            $("#listDetails").html(result);
        });
	}
</Script>
<div class="container">
	<ul class="nav nav-tabs">
		<li class="active">
			<a data-toggle="tab" href="#home">Notifications</a>
		</li>
		<li>
			<a data-toggle="tab" href="#listDetails">Notification Details</a>
		</li>
	</ul>
	<div class="tab-content">
		<div id="home" class="tab-pane fade in active">
			<h3>Masters</h3>
			${listService.listMap}
			<section class="content " >
		    <div class="col-md-9 myStyle">
		    <section class="content-header">
      <h1>
       NOTICE BOARD &nbsp;  <select >
         <option>All</option>
      </select>
      </h1>
      
      <ol class="breadcrumb">
         <li><input type="submit" value="Pin New Notice" class="btn" style="background-color:#008000;color:#fff">
        </li>
      </ol>
     </section>
			  <!-- Info boxes -->
			     <div class="col-md-12 " >
				       <div class="box box-default" style="border-radius:21px" >
					            <!-- /.box-header -->
					            <c:forEach var="notice" items="${notificationList}">
					
					            <div class="box-body">
					            
						              <ul>
						                   <div  class="floatleft"><li style="color:green">${notice.notificatiosHeadline}</li></div>
						                   <div class="floatright"><li style="color:black">Deparment: ${notice.groups}</li></div>
									  </ul>
						            </div>
									<div class="col-md-12" style="padding-left:30px"><div class="col-md-2">
									<img src="data:image/png;base64,${notificationFiles.getByteArrayString()}" width="70px" height="70px">
									</div>
									<div class="col-md-10">
						                  ${notice.notificationDetails}
						                    </div><div style="padding-left:27px">
						              <a href="#" onClick="goToNoticeDetails(${notice.notificationId})">View Details</a>
						              </div>
						              </div><br><br><br>
						              <div class="box-body">
						              <ul>
						                   <div  class="floatleft">
						                         <li style="color:green">
						                              Student Notified:1000
						                          </li>
						                   </div>
						                   <div class="floatright">
						                         <li style="color:black">
						                              Date:${notice.notificationFromDate} to ${notice.notificationToDate}
						                          </li>
						                    </div>
									        <br/>
									 </ul> 
									 <ul>
									        <div  class="floatleft">
									            <li style="color:green">
									                    Venue: ${notice.venue}
									             </li>
									        </div>
						                    <div class="floatright">
						                         <li style="color:black">
						                                 Time: 11:00AM to 8:00PM
						                          </li>
						                     </div>
									    <br/>
									 </ul>			  
					            </div>
							</c:forEach>
						  </div>
					</div>
					
		    </div>
     <!-- /.col -->
      <div class="col-md-3 myStyle" style="padding-right:0px;background-color: ">
             <div class="box" >
				  <div class=""  style="border-radius:21px;">
					   <!-- /.box-header -->
					   <div class="box-body">
					            <h4>Upcoming Event</h4> 
					            <div class="box" style="background-color: lightgray">
					                 Swapnil
					            </div> 
			            </div>
					</div>
			</div>			
	</div>
 </section>
  	</div>
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