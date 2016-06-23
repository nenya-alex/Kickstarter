<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
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