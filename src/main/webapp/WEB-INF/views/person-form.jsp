<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
  <body>

    <form:form method="POST" modelAttribute="person">
      <form:input path="login" />
      <form:password path="password"/>
      <form:input path="email"/>

      <input type="submit" value="save">
    </form:form>

  </body>
</html>