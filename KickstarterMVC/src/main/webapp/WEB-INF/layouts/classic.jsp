<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<link href="<c:url value="/resources/style.css" />" rel="stylesheet" type="text/css" />
	<script src="<c:url value="/resources/jquery-2.2.3.min.js" />"></script>
	<script src="<c:url value="/resources/scripts.js" />"></script>
</head>
 
<body>
   <table>
       <tr>
           <td colspan="2"><tiles:insertAttribute name="header" /></td>
       </tr>
       <tr>
           <td width="80%"><tiles:insertAttribute name="bodyOne" /></td>
       </tr>
       <tr>
           <td width="80%"><tiles:insertAttribute name="body" /></td>
       </tr>
       <tr>
           <td colspan="2"><tiles:insertAttribute name="footer" /></td>
       </tr>
   </table>
</body>
</html>