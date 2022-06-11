<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Deliberation existe deja !</title>
</head>
<body>
	<p>Les etudiants suivants existent deja ! Voulez-vous ecraser les donnees ?</p>
	<table>
  <c:forEach items="${eliste}" var="item">
    <tr>
      <td><c:out value="${item}" /></td>
       <td><a
			href="/admin/updatePersonForm/${p.idUtilisateur}">Mettre a jour </a></td>
    </tr>
  </c:forEach>
</table>
</body>
</html>