package pl.coderslab.app.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.app.dao.BookDao;
import pl.coderslab.app.dao.PublisherDao;
import pl.coderslab.app.entity.Book;
import pl.coderslab.app.entity.Publisher;

@RequestMapping("/books")
@Controller
@RequiredArgsConstructor
public class BookController {

  private final BookDao bookDao;
  private final PublisherDao publisherDao;

  @GetMapping
  @ResponseBody
  public String findAll() {
    List<Book> all = bookDao.findAll();

    return all.stream()
        .map(Book::toString)
        .collect(Collectors.joining(" <br> "));
  }

  @GetMapping("/rating/{rating}")
  @ResponseBody
  public String getBooksByRating(@PathVariable int rating){
    List<Book> byRating = bookDao.findByRating(rating);

    return byRating.stream()
        .map(Book::toString)
        .collect(Collectors.joining("\n"));
  }

  @GetMapping("/add")
  public String createForm(Model model){
    model.addAttribute("book", new Book());

    return "book-form";
  }

  @PostMapping("/add")
  public String create(Book book){
    bookDao.save(book);

    return "redirect:/books";
  }

  @ModelAttribute("publishers")
  public List<Publisher> getPublishers(){
    return publisherDao.findAll();
  }

  @GetMapping("/add-with-publisher")
  @ResponseBody
  public String create() {
    Publisher publisher = new Publisher();
    publisher.setName("Prószyński i spółka");

    publisherDao.save(publisher);

    Book book = new Book();
    book.setTitle("Wojny Konsolowe");
    book.setDescription("Ksiazka o graniu w gry w ameryce");
    book.setRating(7);
    book.setPublisher(publisher);

    bookDao.save(book);

    return bookDao.findById(book.getId()).toString();
  }
}
