<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  
<!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="<c:url value="/static/css/style.css"/> ">
<!-- Theme style -->
  <link rel="stylesheet" href="<c:url value="/static/css/animate.css"/> ">
  <script src="<c:url value="/static/js/jquery.min.js"/>"></script>
  
  <title><tiles:insertAttribute name="title" ignore="true" /></title>
   <tiles:insertAttribute name="body" />
</html>
