<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
  <body>

    <form:form method="POST" modelAttribute="book">
      title       <form:input path="title" />
                  <form:errors path="title"/><br/>
      rating      <form:input path="rating"/>
                  <form:errors path="rating"/> <br/>
      description <form:input path="description"/>
                  <form:errors path="description"/> <br/>
      <form:select path="publisher" items="${publishers}" itemLabel="name" itemValue="id"/>
      <form:errors path="publisher"/><br/>
      <input type="submit" value="save">
<br/><br/><br/><br/>
    <form:errors path="*"/>

    </form:form>

  </body>
</html>