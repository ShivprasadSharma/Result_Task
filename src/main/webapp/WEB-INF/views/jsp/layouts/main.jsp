<html>
<head>
<link rel="shortcut icon" href="https://res.cloudinary.com/dcptr5ivh/image/upload/v1555936240/zertones/favicon.ico" />
</head>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="<c:url value="/static/css/bootstrap.min.css"/>" type='text/css' />
<link rel="stylesheet" href="<c:url value="/resources/CLEditor/jquery.cleditor.css"/>" />
<link rel="stylesheet" href="<c:url value="/static/css/bootstrap-datetimepicker.css"/>" />
<link rel="stylesheet" href="<c:url value="/static/css/dataTables.bootstrap.css" />">

<!-- Custom Theme files -->
<link rel="stylesheet" href="<c:url value="/static/css/style.css"/>" type='text/css' />
<link rel="stylesheet" href="<c:url value="/static/css/jquery.countdown.css"/>" />
<link rel="stylesheet" href="<c:url value="/static/css/zerton.min.css"/>"/>
<link rel="stylesheet" href="<c:url value="/static/css/all_color.min.css"/> ">
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
<script src="<c:url value="/static/js/app.min.js"/>" ></script>
<script src="<c:url value="/static/js/select2.full.min.js"/>" ></script>
<script src="<c:url value="/static/js/Chart.min.js"/>"></script> 
<script src="<c:url value="/static/js/jquery.dataTables.min.js" />"></script>
<script src="<c:url value="/static/js/dataTables.bootstrap.min.js" />"></script>

<script src="<c:url value="/static/js/jquery.flot.min.js" />"></script>
<!-- FLOT RESIZE PLUGIN - allows the chart to redraw when the window is resized -->
<script src="<c:url value="/static/js/jquery.flot.resize.min.js"/>"></script> 
<script src="<c:url value="/static/js/jquery.flot.pie.min.js"/>"></script> 


<!-- full calender -->
<%-- <link rel="stylesheet" href="<c:url value="/static/css/fullcalendar.css"/> ">
 --%><link rel="stylesheet" href="<c:url value="/static/css/fullcalendar.min.css"/> ">
<script src="<c:url value="/static/js/fullcalendar.js"/>" ></script>

<title><tiles:insertAttribute name="title" ignore="true" /></title>

<tiles:insertAttribute name="header" />
  
<tiles:insertAttribute name="menu" />

<tiles:insertAttribute name="body" />

<tiles:insertAttribute name="footer" />

</html>
