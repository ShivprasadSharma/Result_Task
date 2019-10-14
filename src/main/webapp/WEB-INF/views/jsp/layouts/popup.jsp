<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="<c:url value="/static/css/bootstrap.min.css"/>" type='text/css' />
<link rel="stylesheet" href="<c:url value="/resources/CLEditor/jquery.cleditor.css"/>" />
<link rel="stylesheet" href="<c:url value="/static/css/bootstrap-datetimepicker.css"/>" />
 <link rel="stylesheet" href="<c:url value="/static/css/dataTables.bootstrap.css" />">
 
<!-- Custom Theme files -->
<link rel="stylesheet" href="<c:url value="/static/css/style.css"/>" type='text/css' />
<link rel="stylesheet" href="<c:url value="/static/css/jquery.countdown.css"/>" />

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<c:url value="/static/css/select2.min.css"/> ">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<c:url value="/static/js/jquery.min.js"/>"></script>
<script src="<c:url value="/static/js/bootstrap.min.js"/>"></script> 
<script src="<c:url value="/static/js/moment-with-locales.js"/>"></script>
<script src="<c:url value="/static/js/bootstrap-datetimepicker.js"/>"></script>
<script src="<c:url value="/resources/CLEditor/jquery.cleditor.js"/>"></script>
<script src="<c:url value="/resources/CLEditor/jquery.cleditor.min.js"/>"></script>
<script src="<c:url value="/static/js/jquery.autocomplete.min.js"/>"></script>

<script src="<c:url value="/static/js/select2.full.min.js"/>" ></script>


<script src="<c:url value="/static/js/jquery.dataTables.min.js" />"></script>
<script src="<c:url value="/static/js/dataTables.bootstrap.min.js" />"></script>

<!-- full calender -->
<link rel="stylesheet" href="<c:url value="/static/css/fullcalendar.css"/> ">
<link rel="stylesheet" href="<c:url value="/static/css/fullcalendar.min.css"/> ">
<script src="<c:url value="/static/js/fullcalendar.js"/>" ></script>
<%-- <script src="<c:url value="/static/js/fullcalendar.min.js"/>" ></script>
 --%>
<title><tiles:insertAttribute name="title" ignore="true" /></title>

<tiles:insertAttribute name="body" />

s
</html>
