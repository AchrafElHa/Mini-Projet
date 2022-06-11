<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>


<jsp:include page="../fragments/userheader.jsp" />
<div class="container">

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">



			<jsp:include page="../fragments/usermenu.jsp" />

		</div>
	</nav>






	<div>
		<h3>Prof home page</h3>
		<p>Hello and welcome to your application</p>

		<s:authorize access="isAuthenticated()">
    			You are connected with: 
    			 <s:authentication property="principal.username" /> <br>
			Your Email : <s:authentication property="principal.email" /><br>
			Your First Name : <s:authentication property="principal.firstName" /><br>
			Your Last name : <s:authentication property="principal.LastName" /><br>
		</s:authorize>
	</div>




	<div>
		<h3>Veuillez importer le fichier de deliberation</h3>

	<!-- Upload  -->
	<form action="/uploadFile" method="post" id="file-upload-form" class="uploader" enctype="multipart/form-data">

    <label for="file-upload" id="file-drag">
    <div id="start">
      <i class="fa fa-download" aria-hidden="true"></i>
      <div>Select a file or drag here</div>
      <input id="file-upload" type="file" name="file" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"/>
      <input type="submit">
    </div>
  </label>
</form>
	
	</div>


<jsp:include page="../fragments/userfooter.jsp" />

