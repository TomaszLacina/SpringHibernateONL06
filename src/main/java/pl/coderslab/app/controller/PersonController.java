package pl.coderslab.app.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.app.dao.PersonDao;
import pl.coderslab.app.entity.Person;

@Controller
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

  private final PersonDao personDao;

  @GetMapping("/add")
  public String addForm(Model model) {
    return "person-form";
  }

  @PostMapping("/add")
  @ResponseBody
  public String add(@RequestParam("login") String login,
      @RequestParam("password") String password,
      @RequestParam("email") String email) {

    Person person = new Person(null, login, password, email);

    personDao.save(person);

    return person.toString();
  }
}
