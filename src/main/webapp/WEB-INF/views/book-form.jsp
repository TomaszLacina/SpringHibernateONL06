<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
  <body>

    <form:form method="POST" modelAttribute="book">
      title       <form:input path="title" /> <br/>
      rating      <form:input path="rating"/> <br/>
      description <form:input path="description"/> <br/>

      <form:select path="publisher" items="${publishers}" itemLabel="name" itemValue="id"/> <br/>

      <input type="submit" value="save">
    </form:form>

  </body>
</html>