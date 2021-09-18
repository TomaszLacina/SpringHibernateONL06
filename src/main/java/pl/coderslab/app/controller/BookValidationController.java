package pl.coderslab.app.controller;


import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.app.entity.Book;

@Controller
@RequestMapping("/validation")
@RequiredArgsConstructor
public class BookValidationController {

  private final Validator validator;

  @GetMapping("/book")
  @ResponseBody
  public String validateBook(){
    Book book = new Book();

    Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);

    StringBuilder sb = new StringBuilder();

    for (ConstraintViolation violation : constraintViolations){
      sb.append(violation.getPropertyPath());
      sb.append("   :   ");
      sb.append(violation.getMessage());
      sb.append("<br/>");
    }

    return sb.toString();
  }
}
