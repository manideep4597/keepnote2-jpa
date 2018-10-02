<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="form" uri="<a class="vglnk" href="http://www.springframework.org/tags/form"%" rel="nofollow"><span>http</span><span>://</span><span>www</span><span>.</span><span>springframework</span><span>.</span><span>org</span><span>/</span><span>tags</span><span>/</span><span>form</span><span>"%</span></a>>
<%@ taglib prefix="c" uri="<a class="vglnk" href="http://java.sun.com/jsp/jstl/core" rel="nofollow"><span>http</span><span>://</span><span>java</span><span>.</span><span>sun</span><span>.</span><span>com</span><span>/</span><span>jsp</span><span>/</span><span>jstl</span><span>/</span><span>core</span></a>" %>

<!DOCTYPE html>
<html lang="en">

<head>
<title>Keep-Board</title>
</head>

<body>
	<!-- Create a form which will have text boxes for Note title, content and status along with a Add 
		 button. Handle errors like empty fields -->
		 <form:form method="POST" modelAttribute="note">
		 <table>
		            <tr>
                          <td><label for="noteId">Note Id: </label> </td>
                          <td><form:input path="noteId" id="noteId"/></td>
                          <td><form:errors path="noteId" /></td>
                     </tr>
                     <tr>
                         <td><label for="noteTitle">Note Title: </label> </td>
                         <td><form:input path="noteTitle" id="noteTitle"/></td>
                         <td><form:errors path="noteTitle" /></td>
                     </tr>

                     <tr>
                         <td><label for="noteContent">Note Content: </label> </td>
                         <td><form:input path="noteContent" id="noteContent"/></td>
                         <td><form:errors path="noteContent" /></td>
                     </tr>

                     <tr>
                         <td><label for="noteStatus">Note Status: </label> </td>
                         <td><form:input path="noteStatus" id="noteStatus"/></td>
                         <td><form:errors path="noteStatus" /></td>
                     </tr>
          </table>
          <a href="<c:url value='/' />" >Display</a>
          <a href="<c:url value='/add' />" >Add</a>
          <a href="<c:url value='/delete' />" >Delete</a>
          <a href="<c:url value='/update' />" >Update</a>
</form:form>


	<!-- display all existing notes in a tabular structure with Title,Content,Status, Created Date and Action -->
	<h2>List of Notes ${result}</h2>
        <table>
            <tr>
                <td>Note Title</td><td>Content</td><td>Status</td><td></td>
            </tr>
            <c:forEach items="${notes}" var="note">
                <tr>
                <td>${note.noteId}</td>
                <td>${note.noteTitle}</td>
                <td>${note.noteContent}</td>
                <td>${note.noteStatus}</td>
                </tr>
            </c:forEach>
        </table>
</body>

</html>