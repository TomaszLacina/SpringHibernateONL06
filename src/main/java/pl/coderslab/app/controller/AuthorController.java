package pl.coderslab.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.app.dao.AuthorDao;
import pl.coderslab.app.dao.BookDao;

@Controller
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
  private final AuthorDao authorDao;
  private final BookDao bookDao;



}
