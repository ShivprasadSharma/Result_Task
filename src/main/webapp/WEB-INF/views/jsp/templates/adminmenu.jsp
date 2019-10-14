<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:url var="getBalnkProfile" value="/web/taskforce/service/mentor" />
<c:url var="getBalnkUser" value="/web/taskforce/user/blank/" />
<c:url var="getStudentList" value="/web/taskforce/cims/student/list/" />
<%-- <c:url var="getPastNotice" value="/web/taskforce/service/notification/past/" />
 --%>
<style>
.sidebar {
  width: auto;
  height: 650px;
  overflow: auto;
}
</style>
<c:url var="chat" value="web/taskforce/chatList/" />

<!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar " >
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar"  >
     <!-- Sidebar user panel -->
      <div class="user-panel">
      <ul class="sidebar-menu nav" >
       <%-- <sec:authorize access="hasAnyRole('ROLE_TEACHER','ROLE_HOD','ROLE_LECTURER','ROLE_PRINCIPAL')">
        <li class="treeview">
          <a href="#">
            <i class="fa fa-user"></i> <span style="color:#000000"><input type="submit" value="New Group" onclick="window.location.href='${getBalnkUser}${sessionScope.user.comClientName.id}'" class="btn" style=" background-color:#008000; color:#fff ;font-weight: bold;"></span>
          </a><hr/> 
        </li>
        </sec:authorize>   --%>  
              
        <sec:authorize access="hasAnyRole('ROLE_SUPER_ADMIN')">
       <li class="treeview " >
          <a href="<c:url value="/web/institute/list" />" >
            <i class="fa fa-institution"></i> <span
							style="color: #000000">Institutes</span>
					</a>
        </li> 
        </sec:authorize> 
        
        
        <sec:authorize access="hasAnyRole('ROLE_SUPER_ADMIN')">
       <li class="treeview " >
          <a href="<c:url value="/taskforce/service/result/commonreport" />" >
            <i class="fa fa-institution"></i> <span
							style="color: #000000">SubjectReport</span>
					</a>
        </li> 
        </sec:authorize> 
        
      <%--      <c:if test="${not empty sessionScope.TPO}">
      
        <sec:authorize access="hasAnyRole('ROLE_TPO')">
         <li class="treeview">
        
          <a href="<c:url value="/web/taskforce/service/tpohome" />" >
            <i class="fa fa-bank"></i>
            <span style="color:#000000">TPO </span>
           <span class="pull-right-container">
               <i class="fa fa"></i>
            </span>
          </a>
         --%>  
          <%--  <li class="treeview">
                 <a href="<c:url value="/web/taskforce/service/assigncoordinator/"/>${sessionScope.user.comClientName.id}" >
			       <i class="fa fa-chevron-right"></i>
			         <span style="color:#000000">Allotment</span> 
			          <span class="pull-right-container">
			              <i class="fa fa-angle-right pull-left"></i>
			         </span>
			     </a> 
     	  </li> --%>
      	 
       </li>
   <%--       </sec:authorize>
          </c:if>
    --%>    <%--    <sec:authorize access="hasAnyRole('ROLE_HOD')">
         
           <li class="treeview">
                 <a href="<c:url value="/web/taskforce/service/assigncoordinator/"/>${sessionScope.user.comClientName.id}" >
			       <i class="fa fa-chevron-right"></i>
			         <span style="color:#000000">Portfolio</span> 
			          <span class="pull-right-container">
			              <i class="fa fa-angle-right pull-left"></i>
			         </span>
			     </a> 
     	  </li>
      
         </sec:authorize>
 --%>        <%-- <li class="treeview " >
          <a href="<c:url value="/web/taskforce/service/notification/list" />" >
            <i class="fa  fa-file-text-o"></i> <span style="color:#000000">Notice Board</span>
          </a>
        </li>       
        <li class="treeview">
        <!-- <c:url  value='/web/taskforce/service/notification/past/'/> -->
          <a href="<c:url  value='/web/taskforce/service/notification/past/0/0'/>">
            <i class="fa fa-laptop"></i>
            <span style="color:#000000">Past Notices</span>
          </a>
        </li> --%>
        <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_HOD','ROLE_PRINCIPAL')">
       <%--    <li class="treeview">
        <!-- <c:url  value='/web/taskforce/service/notification/past/'/> -->
          <a href="<c:url  value='/web/taskforce/serives/Add_Subject'/>">
            <i class="fa fa-laptop"></i>
            <span style="color:#000000">Add Subject</span>
          </a>
        </li>
         <li class="treeview">
          <a href="<c:url  value='/web/taskforce/Teacher_Subject_List'/>">
            <i class="fa fa-laptop"></i>
            <span style="color:#000000">Assign Subject To Teacher</span>
          </a>
        </li>  --%>
        <!-- Time Table -->
       <%-- 
         <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_PRINCIPAL')">
          <c:if test="${not empty sessionScope.Grievance}">
           <li class="treeview">
          <a href="#">
            <i class="fa fa-user"></i>
            <span style="color:#000000">Grievance</span>
           <span class="pull-right-container">
               <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">       
             <li class="treeview">
                 <a href="<c:url value="/web/taskforce/serives/grievance/create/committee"/>">
			       <i class="fa "></i>
			         <span style="color:#000000">Create Committee</span> 
			          <span class="pull-right-container">
			              <i class="fa fa-angle-right pull-left"></i>
			         </span>
			     </a>   
             </li>
             <li class="treeview">
                 <a href="<c:url value="/web/taskforce/serives/grievance/GC_members/list"/>" >
			       <i class="fa "></i>
			         <span style="color:#000000">Members</span> 
			          <span class="pull-right-container">
			              <i class="fa fa-angle-right pull-left"></i>
			         </span>
			     </a>   
             </li>
               <li class="treeview">
                 <a href="<c:url value="/web/taskforce/serives/grievance/list"/>" >
			       <i class="fa "></i>
			         <span style="color:#000000">Grievance's</span> 
			          <span class="pull-right-container">
			              <i class="fa fa-angle-right pull-left"></i>
			         </span>
			     </a>   
             </li>
             <li class="treeview">
                 <a href="<c:url value="/web/taskforce/serives/grievance/download/page"/>" >
			       <i class="fa "></i>
			         <span style="color:#000000">Download Grievances</span> 
			          <span class="pull-right-container">
			              <i class="fa fa-angle-right pull-left"></i>
			         </span>
			     </a>   
             </li>
          </ul>
         </li> 
         </c:if>
         --%><%--            <c:if test="${not empty sessionScope.Poll}">
         
	       <li class="treeview " >
	          <a href="<c:url value="/web/taskforce/serives/poll/page" />" >
	            <i class="fa fa-file-excel-o"></i> <span style="color:#000000">Poll</span>
	            <span class="pull-right-container">
	              <i class="fa"></i>
	            </span>
	          </a>
	        </li> 
	          </c:if>
	       </sec:authorize>
	        <sec:authorize access="hasAnyRole('ROLE_HOD')">
	           <c:if test="${not empty sessionScope.Activities}">
	        
        <li class="treeview">
          <a href="#">
            <i class="fa fa-user"></i>
            <span style="color:#000000">Activities</span>
           <span class="pull-right-container">
               <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="treeview">
                 <a href="<c:url  value='/web/taskforce/serives/Add_Subject'/>">
			       <i class="fa "></i>
			         <span style="color:#000000">Add Subject</span> 
			          <span class="pull-right-container">
			              <i class="fa fa-angle-right pull-left"></i>
			         </span>
			     </a>   
             </li>
             <li class="treeview">
                     <a href="<c:url value="/web/taskforce/practical_batch/"/>${sessionScope.user.staffId}">
			       <i class="fa "></i>
			         <span style="color:#000000">Assign Practical Batch</span> 
			          <span class="pull-right-container">
			              <i class="fa fa-angle-right pull-left"></i>
			         </span>
			     </a>   
              </li> 
             <li class="treeview">
                 <a href="<c:url  value='/web/taskforce/Teacher_Subject_List'/>">
			       <i class="fa "></i>
			         <span style="color:#000000">Assign Subject To Teacher</span> 
			          <span class="pull-right-container">
			              <i class="fa fa-angle-right pull-left"></i>
			         </span>
			     </a>   
             </li>
              <li class="treeview">
          <a href="#">
            <i class="fa fa-university"></i>
            <span style="color:#000000">Student List</span>
          <span class="pull-right-container">
               <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
             <c:forEach var="item" items="${departments}">
                <c:if test="${item.dep_id ne 0}">
				    <li><a href="<c:url value="/web/taskforce/cims/student/list/${item.dep_id}"/>"  onclick="getStudentList(${item.dep_id})" ><i ></i>${item.dep_name}</a></li>
			    </c:if>
		     </c:forEach> 
           </ul>
        </li>
         --%>    <%--  <li class="treeview">
                 <a href="<c:url  value='/web/taskforce/service/timeTable'/>">
			       <i class="fa "></i>
			         <span style="color:#000000">Time Table</span> 
			          <span class="pull-right-container">
			              <i class="fa fa-angle-right pull-left"></i>
			         </span>
			     </a>   
             </li> --%>
            <%--  <li class="treeview">
                 <a href="<c:url  value='/web/taskforce/service/timeTable/1'/>" >
			       <i class="fa "></i>
			         <span style="color:#000000">Get Time Table</span> 
			          <span class="pull-right-container">
			              <i class="fa fa-angle-right pull-left"></i>
			         </span>
			     </a>   
             </li> --%>
         <%--  </ul>
         </li>
         </c:if>
         --%> </sec:authorize>
        <!-- /Time Table  -->
        
        <%-- <li class="treeview">
        <!-- <c:url  value='/web/taskforce/service/notification/past/'/> -->
          <a href="<c:url  value='/web/taskforce/service/timeTable'/>" ">
            <i class="fa fa-laptop"></i>
            <span style="color:#000000">Time Table</span>
          </a>
        </li> --%>
         <%-- <li class="treeview">
        <!-- <c:url  value='/web/taskforce/service/notification/past/'/> -->
          <a href="<c:url  value='/web/taskforce/service/timeTable/1'/>" ">
            <i class="fa fa-laptop"></i>
            <span style="color:#000000">Get Time Table</span>
          </a>
        </li> --%>
<%--         </sec:authorize>
 --%>       <!-- Don't Delete this tab use in future   -->
     <%--   <sec:authorize access="hasAnyRole('ROLE_TEACHER','ROLE_LECTURER','ROLE_HOD')">
  <c:if test="${not empty sessionScope.Attendance}">
         <li class="treeview">
          <a href="#">
            <i class="fa fa-building-o"></i>
            <span style="color:#000000">Attendance</span>
           <span class="pull-right-container">
               <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
         
        <li class="treeview">
          <a href="<c:url value='/web/taskforce/student/set/attendance/'/>${sessionScope.user.comClientName.id}">
            <i class="fa fa-building-o"></i>
            <span style="color:#000000">Take Attendance</span>
          </a>
        </li> 
         <li class="treeview">
          <a href="<c:url value='/web/taskforce/student/attendance/daily/'/>${sessionScope.user.comClientName.id}">
            <i class="fa fa-building-o"></i>
            <span style="color:#000000">Daily Attendance</span>
          </a>
        </li>
      
         <sec:authorize access="hasAnyRole('ROLE_HOD')">
        <li class="treeview">
          <a href="<c:url value='/web/taskforce/student/attendance/daily/report/'/>${sessionScope.user.staffId}">
            <i class="fa fa-building-o"></i>
            <span style="color:#000000">Attendance Report</span>
          </a>
        </li>
        </sec:authorize>
      --%>
         <%-- <sec:authorize access="hasAnyRole('ROLE_TEACHER','ROLE_LECTURER','ROLE_HOD')">
        <li class="treeview">
          <a href="<c:url value='/web/taskforce/student/attendance/'/>${sessionScope.user.comClientName.id}">
            <i class="fa fa-building-o"></i>
            <span style="color:#000000">Get Attendance Result </span>
          </a>
        </li> 
        </sec:authorize>  --%>
        </ul>
        </li>
      <%--   </c:if>
         </sec:authorize> 
       --%>  <%-- <a href="<c:url value ='/web/taskforce/student/attendance/1'/>${sessionScope.user.employeeNo} "> --%>       
         <%-- <sec:authorize access="hasAnyRole('ROLE_TEACHER','ROLE_LECTURER')">
         <li class="treeview" id="group">
          <a href="<c:url value ='/web/taskforce/group'/>">
            <i class="fa fa-group"></i>
            <span style="color:#000000">Group</span>
          </a>
         </li>
         </sec:authorize>
         <sec:authorize access="hasAnyRole('ROLE_HOD','ROLE_PRINCIPAL','ROLE_STUDENT')">
         <li class="treeview" id="group">
          <a href="<c:url value ='/web/taskforce/group'/>">
            <i class="fa fa-group"></i>
            <span style="color:#000000">Group</span>
          </a>
         </li>
         </sec:authorize>
          <sec:authorize access="hasAnyRole('ROLE_HOD')">
	        <li class="treeview " >
	          <a href="<c:url value='/web/taskforce/feedback/feedback' />" >
	            <i class="fa fa-edit"></i> <span style="color:#000000">Feedback</span>
	            <span class="pull-right-container">
	            </span>
	          </a>
	        </li>
	        <li class="treeview">
          <a href="#">
            <i class="fa fa-user"></i>
            <span style="color:#000000"> GFM </span>
           <span class="pull-right-container">
               <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
           <li class="treeview">
            
                 <a href="<c:url value="/web/taskforce/service/mentor/"/>${sessionScope.user.comClientName.id}" >
			       <i class="fa "></i>
			         <span style="color:#000000">Create GFM Profile</span> 
			          <span class="pull-right-container">
			              <i class="fa fa-angle-right pull-left"></i>
			         </span>
			     </a> 
			  
             <li class="treeview">
                 <a href="<c:url value="/web/taskforce/service/mentor/mentstaffprofile"/>" >
			       <i class="fa "></i>
			         <span style="color:#000000">GFM List</span> 
			          <span class="pull-right-container">
			              <i class="fa fa-angle-right pull-left"></i>
			         </span>
			     </a>   
             </li>
             <li class="treeview">
           <a href="<c:url value="/web/taskforce/service/mentor/grplist/"/>${sessionScope.user.staffId}" >
            <i class="fa f"></i>
            <span style="color:#000000"> GFM Profile</span>
           <span class="pull-right-container">
               <!-- <i class="fa fa-angle-left pull-right"></i> -->
            </span>
          </a>
          </li>
          </ul>
         </li>
         </sec:authorize>
	     --%>   
	        <%-- <sec:authorize access="hasAnyRole('ROLE_HOD','ROLE_ADMIN')">
	        <li class="treeview " >
	            <a href="" >
	            <i class="fa fa-edit"></i> <span style="color:#000000"></span>
	            <span class="pull-right-container">
	            </span>
	          </a>
	        </li>
	        
	       </sec:authorize> --%>
         <%-- <li class="treeview">
         <a href="<c:url value='/web/taskforce/chatList/'/>${sessionScope.user.employeeNo}">
           <i class="fa fa-commenting"></i>
            <span style="color:#000000">Chat</span>
           <!--  <span class="pull-right-container">
              <span class="label label-primary pull-right">4</span>
            </span> -->
          </a>
         </li> --%>
        
      <sec:authorize access="hasAnyRole('ROLE_IT','ROLE_ADMIN','ROLE_TPO','ROLE_PRINCIPAL')">
     <%--  <hr/>
      
      <li class="treeview">
           <a href="<c:url value="/web/taskforce/stafflist/1"/>">
            <i class="fa fa-users"></i>
            <span style="color:#000000">Staff List</span>
           <span class="pull-right-container">
            </span>
          </a>
          <ul class="treeview-menu">
             <li class="treeview">
                     <a href="<c:url value="/web/taskforce/services/add"/>">
			            <i class="fa "></i>
			            <span style="color:#000000">Add User</span> 
			              <span class="pull-right-container">
			              <i class="fa fa-angle-right pull-left"></i>
			            </span>
			          </a>
			            <ul class="treeview-menu">       
             <li class="treeview">
                 <a href="<c:url value="/web/taskforce/services/add"/>">
			       <i class="fa "></i>
			         <span style="color:#000000">Upload File</span> 
			          <span class="pull-right-container">
			              <i class="fa fa-angle-right pull-left"></i>
			         </span>
			     </a>   
             </li>
             <li class="treeview">
                 <a href="<c:url value="/web/taskforce/service/Admin_details"/>">
			       <i class="fa "></i>
			         <span style="color:#000000">upload form</span> 
			          <span class="pull-right-container">
			              <i class="fa fa-angle-right pull-left"></i>
			         </span>
			     </a>   
             </li>
          </ul>  
             <li class="treeview">
                     <a href="<c:url value="/web/taskforce/stafflist/1"/>">
			            <i class="fa "></i>
			            <span style="color:#000000">Head Of Department</span> 
			              <span class="pull-right-container">
			              <i class="fa fa-angle-right pull-left"></i>
			            </span>
			          </a>   
                </li>
                <li class="treeview"><a href="<c:url value="/web/taskforce/stafflist/6"/>">
			            <i class="fa "></i>
			            <span style="color:#000000">IT Support</span> 
			              <span class="pull-right-container">
			              <i class="fa fa-angle-right pull-left"></i>
			            </span>
			          </a>
                  
                </li>
                <li class="treeview"><a href="<c:url value="/web/taskforce/stafflist/4"/>">
			            <i class="fa "></i>
			            <span style="color:#000000">Administration</span> 
			              <span class="pull-right-container">
			              <i class="fa fa-angle-right pull-left"></i>
			            </span>
			          </a>               
                </li>
                <li class="treeview"><a href="<c:url value="/web/taskforce/stafflist/2"/>">
			            <i class="fa "></i>
			            <span style="color:#000000">Teaching Staff</span> 
			              <span class="pull-right-container">
			              <i class="fa fa-angle-right pull-left"></i>
			            </span>
			          </a>
                 </li>
            </ul>
         </li>         
         <li class="treeview">
          <a href="<c:url value="/web/taskforce/cims/student/list/1"/>" >
            <i class="fa fa-university"></i>
            <span style="color:#000000">Student List</span>
          <span class="pull-right-container">
               <i class="fa fa-angl"></i>
            </span>
          </a>
          <ul class="treeview-menu">
             <c:forEach var="item" items="${sessionScope.departments}">
                <c:if test="${item.dep_id ne 0}">
				    <li><a href="<c:url value="/web/taskforce/cims/student/list/${item.dep_id}"/>"  onclick="getStudentList(${item.dep_id})" ><i ></i>${item.dep_name}</a></li>
		    </c:if>
		     </c:forEach> 
           </ul>
        </li>
     --%>     <%--  <sec:authorize access="hasAnyRole('ROLE_IT','ROLE_ADMIN')">
         <li class="treeview">
          <a href="<c:url value ='/web/taskforce/activation/student'/>">
            <i class="fa fa-check-square"></i>
            <span style="color:#000000">Student Verification</span>
            <!-- <span class="pull-right-container">
              <span class="label label-primary pull-right">4</span>
            </span>  -->
          </a>
         </li>
         </sec:authorize>
      --%></sec:authorize>
     
      </ul>
	    <hr>
	     <ul class="sidebar-menu nav" >
	      <sec:authorize access="hasAnyRole('ROLE_TEACHER','ROLE_IT','ROLE_ADMIN','ROLE_TPO','ROLE_PRINCIPAL')">
	   <%--    
	      <li class="treeview">
	      <c:if test="${sessionScope.user.comClientName.coordinatortype==3}">
                 <a href="<c:url value="/web/taskforce/service/mentor/"/>${sessionScope.user.comClientName.id}" >
			       <i class="fa fa-user "></i>
			         <span style="color:#000000">Create GFM Profile</span> 
			          <span class="pull-right-container">
			              <i class="fa fa-angle-right pull-left"></i>
			         </span>
			     </a> 
			     </c:if>  
	      </li> --%>
	         <li class="treeview">
	         
	          
           <%-- <a href="<c:url value="/web/taskforce/service/mentor/grplist/"/>${sessionScope.user.staffId}" >
            <i class="fa fa-user"></i>
            <span style="color:#000000"> GFM Profile</span>
           <span class="pull-right-container">
               <!-- <i class="fa fa-angle-left pull-right"></i> -->
            </span>
          </a> --%>
        <%--   <ul class="treeview-menu">
          
             <li class="treeview">
                 <a href="<c:url value="/web/taskforce/service/mentor/student/"/>${sessionScope.user.staffId}" >
			       <i class="fa "></i>
			         <span style="color:#000000"> Assigned GFM Students</span> 
			          <span class="pull-right-container">
			              <i class="fa fa-angle-right pull-left"></i>
			         </span>
			     </a>   
             </li>
             <li class="treeview">
                 <a href="<c:url value="/web/taskforce/service/mentor/d/"/>${sessionScope.user.staffId}" >
			       <i class="fa "></i>
			         <span style="color:#000000">Discussed</span> 
			          <span class="pull-right-container">
			              <i class="fa fa-angle-right pull-left"></i>
			         </span>
			     </a>   
             </li>
          </ul> --%>
         </li>
	      </sec:authorize> 
	    
	      
	      <!-- ............Upload Excel Files Folder.......... -->
	      <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_HOD','ROLE_IT','ROLE_TPO','ROLE_PRINCIPAL')">
	      <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_HOD','ROLE_IT','ROLE_TPO','ROLE_PRINCIPAL')">
	      <%--  <li class="treeview">
          <a href="#">
            <i class="fa fa-user"></i>
            <span style="color:#000000">Upload Yearly Files</span>
           <span class="pull-right-container">
               <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
	        <li class="treeview " >
	          <a href="<c:url value="/web/taskforce/serives/notice/uploadfile" />" >
	            <i class="fa fa-file-excel-o"></i> <span style="color:#000000">Upload Notice Excel File</span>
	            <span class="pull-right-container">
	              <i class="fa fa-angle-left pull-right"></i>
	            </span>
	          </a>
	        </li>
	        
	        	    
	     
	    </ul>
	    </li> --%>
	      </sec:authorize>
	    
	    
	       <%--   <li class="treeview">
          <a href="<c:url value='/web/taskforce/service/result/upload/'/>${sessionScope.user.employeeNo}">
            <i class="fa fa-building-o"></i>
            <span style="color:#000000">Upload Result File</span>
          </a>
        </li>
         --%>
           <%--  <li class="treeview">
         <a href="<c:url value="/web/taskforce/serives/uploadsubfile" />" >
            <i class="fa fa-building-o"></i>
            <span style="color:#000000">Upload Subject Excel File</span>
          </a>
        </li>
        
         <li class="treeview">
         <a href="<c:url value="/web/taskforce/serives/uploadfeedbackfile" />" >
            <i class="fa fa-building-o"></i>
            <span style="color:#000000">Upload Feedback Excel File</span>
          </a>
        </li> --%>
        
                 <!-- <li class="treeview">
          <a href="#">
            <i class="fa fa-file-o"></i>
            <span style="color:#000000">Result</span>
           <span class="pull-right-container">
               <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a> -->
          
          
          <%-- <ul class="treeview-menu">
          
             <li class="treeview">
       <a href="<c:url value="/web/taskforce/service/result/upload"/>" >
            <i class="fa fa-building-o"></i>
            <span style="color:#000000">Subject Result Graph</span>
          </a>
        </li> --%>
        
        
          <%--    <li class="treeview">
         <a href="<c:url value="/web/taskforce/service/resultsubject" />" >
            <i class="fa fa-building-o"></i>
            <span style="color:#000000">Result Upload  Subject Excel File</span>
          </a>
        </li>
         --%>
             <li class="treeview">
                 <a href="<c:url value="/web/taskforce/service/resultpage/staffreport"/>" >
			       <i class="fa fa-file-o"></i>
			         <span style="color:#000000">Upload Result File</span> 
			          <span class="pull-right-container">
			              <i class="fa fa-angle-right pull-left"></i>
			         </span>
			     </a>   
             </li>
             
             <!-- 
             
          </ul>
         </li>
                 </ul>
         -->
        
	       </sec:authorize>
	    </ul>
	    
	    
	    <!-- newwww -->
	    <!-- new se -->
	    <%--  <ul class="sidebar-menu nav" >
         <li class="treeview">
          <a href="<c:url value ='#'/>">
            <i class="fa fa-files-o"></i>
            <span style="color:#000000">Get Reports</span>
            <!-- <span class="pull-right-container">
              <span class="label label-primary pull-right">4</span>
            </span>  -->
          </a>
         </li>
         </ul>  --%>
         
         <%--  <c:if test="${not empty sessionScope.OnlineExam}"> --%>
        <%--    <ul class="sidebar-menu nav" >
         <li class="treeview">
          <a href="<c:url value ='/web/taskforce/service/exam/dashboard'/>">
            <i class="fa fa-file-text"></i>
            <span style="color:#000000">Online Exam</span>
            <!-- <span class="pull-right-container">
              <span class="label label-primary pull-right">4</span>
            </span>  -->
          </a>
         </li>
         </ul> --%>
         <%-- </c:if> --%>
       
	     <%--  <ul class="sidebar-menu nav" >
         <li class="treeview">
          <a href="<c:url value ='/web/taskforce/calender'/>">
            <i class="fa fa-calendar"></i>
            <span style="color:#000000">Calendar</span>
            <!-- <span class="pull-right-container">
              <span class="label label-primary pull-right">4</span>
            </span>  -->
          </a>
         </li>
         </ul>  --%>
           <%--  <sec:authorize access="hasAnyRole('ROLE_TEACHER','ROLE_HOD','ROLE_LECTURER','ROLE_ADMIN')">
          <c:if test="${not empty sessionScope.StudentCount}">
         
          <ul class="sidebar-menu nav" >
          <li class="treeview">
                      <a href="<c:url value='/web/taskforce/attendance/punch/page/'/>${sessionScope.user.staffId}">
            <i class="fa fa-street-view"></i>
            <span style="color:#000000">Student Count</span>
            <!-- <span class="pull-right-container">
              <span class="label label-primary pull-right">4</span>
            </span>  -->
          </a>
         </li>
         </ul>
         
         </c:if>
         
         </sec:authorize> --%>
         
      <%--   <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_HOD','ROLE_PRINCIPAL','ROLE_DIRECTOR')">
          <c:if test="${not empty sessionScope.TodayCount}">
         
          <ul class="sidebar-menu nav" >
          <li class="treeview">
                  <a href="<c:url value="/web/taskforce/service/count/studentcountpagess"/>" >
            <i class="fa fa-street-view"></i>
            <span style="color:#000000">Today's Count</span>
            <!-- <span class="pull-right-container">
              <span class="label label-primary pull-right">4</span>
            </span>  -->
          </a>
         </li>
         </ul>   
         </c:if>
         </sec:authorize>
         
        <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_HOD','ROLE_PRINCIPAL')">
           <c:if test="${not empty sessionScope.FeeDetails}">
          <ul class="sidebar-menu nav" >
          <li class="treeview">
                  <a href="<c:url value="/web/taskforce/service/fee/studentfeedetails"/>" >
            <i class="fa fa-rupee"></i>
            <span style="color:#000000">Fee details</span>
            <!-- <span class="pull-right-container">
              <span class="label label-primary pull-right">4</span>
            </span>  -->
          </a>
         </li>
         </ul>   
      </c:if>
         </sec:authorize>
       --%>   
         
         <%--   <ul class="sidebar-menu nav" >
         <li class="treeview">
          <a href="<c:url value ='/web/taskforce/help'/>">
      
            <i class="fa fa-question-circle"></i>
            <span style="color:#000000">Help</span>
            <!-- <span class="pull-right-container">
              <span class="label label-primary pull-right">4</span>
            </span>  -->
          </a>
         </li>
         </ul> 
       --%><%--    	      <sec:authorize access="hasAnyRole('ROLE_STUDENT')">
                   <c:if test="${not empty sessionScope.Punch}">
         
          <ul class="sidebar-menu nav" >
         <li class="treeview">
        <a href="<c:url value='/web/taskforce/punch/'/>${sessionScope.user.comClientName.id}">
      
            <i class="fa fa-hand-o-up"></i>
            <span style="color:#000000">Punch</span>
            <!-- <span class="pull-right-container">
              <span class="label label-primary pull-right">4</span>
            </span>  -->
          </a>
         </li>
         </ul>
         </c:if>
         </sec:authorize>
        <c:if test="${sessionScope.user.comClientName.salutation==1}">
         <c:if test="${not empty sessionScope.Grievance}">
        
      <ul class="sidebar-menu nav" >
      <li class="treeview " >
          <a href="<c:url value="/web/taskforce/serives/grievance/list" />" >
            <i class="fa fa-address-card-o"></i> <span style="color:#000000">Grievances</span>
          </a>
        </li>    
      </ul> 
      </c:if>
         </c:if> 
  --%>    </div>
  </section>
<!-- /.sidebar -->
</aside>
<div class="modal fade" id="userFormModal" role="dialog"></div>
<script>
	$(document).ready(function() {
		var pathname = window.location.pathname;
		 if ((location.pathname.split("/")[5]) == "notification" && location.pathname.split("/")[6] != "past"){
			 $('.nav > li > a[href="/'+location.pathname.split("/")[1]+'/web/taskforce/service/notification/list"]').parent().addClass('active');
			}
		 if ((location.pathname.split("/")[6]) == "past"){
			 $('.nav > li > a[href="/'+location.pathname.split("/")[1]+'/web/taskforce/service/notification/past/*/*"]').parent().addClass('active');
			}
		$('.nav > li > a[href="'+pathname+'"]').parent().addClass('active');
		$('.nav > li > ul > li > a[href="'+pathname+'"]').parent().addClass('active').parent().parent().addClass('active');
	})
	function addUser()
	{
		$.get('${getBalnkUser}', function(result)
		{
			$("#userFormModal").html(result);
	    });
	    $("#userFormModal").modal('show');
	}
	function getStudentList(depId)
	{
		window.location = '${getStudentList}' + depId;
	}
	function createMentorProfile()
	{
		$.get('${getBalnkProfile}', function(result)
		{
			$("#userFormModal").html(result);
	    });  
	    $("#userFormModal").modal('show');
	}	
</script>