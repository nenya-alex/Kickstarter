<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Question</title>
</head>
<body>
	<table>
		<tr>
		<td><a id="back" href="<c:url value="/project/${project.id}"/>" > <spring:message code="category.back" /> </a> </a></td>
		<td><a id="home" href="<c:url value="/"/>"> <spring:message code="category.home" /> </a></td>
		</tr>
	</table>
   
    <p><b><spring:message code="question.ask" /> "${project.name}"</b></p>
    <form:form action="add" method="post" modelAttribute="questionBind">
    
        <form:input type="text" path="name" placeholder="Enter your question"/>
        <form:errors path="name"/>
         
        <form:input type="hidden" path="project.id" value="${project.id}" />
         
        <input id="buttonForm" type="submit" value="Submit" />
    </form:form>
   
</body>
</html>