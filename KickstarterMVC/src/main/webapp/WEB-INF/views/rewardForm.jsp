<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Reward</title>
</head>
<body>
<table>
	<tr>
	<td><a id="back" href="<c:url value="/project/${project.id}"/>" > <spring:message code="category.back" /> </a></td>
	<td><a id="home" href="<c:url value="/"/>"> <spring:message code="category.home" /> </a></td>
	</tr>
	</table>
    
    <form:form action="addReward" method="post" modelAttribute="rewardForm">
    
                <form:input type="hidden" path="amount" value="${reward.amount}" />
                 
                <p><b><spring:message code="payment.enterName" /></b></p>
                 <form:input type="text" path="cardholderName"/><br/>
                  <form:errors path="cardholderName"/> 
                  
                 <p><b><spring:message code="payment.enterCardNumber" /></b></p>
                 <form:input type='text' path='cardNumber'/><br/>
                 <form:errors path="cardNumber"/>
                  
                <form:input type="hidden" path="project.id" value="${project.id}" /> 
                
          <p><input id="buttonForm" type="submit" value="Submit" /></p>
    </form:form>
</body>
</html>