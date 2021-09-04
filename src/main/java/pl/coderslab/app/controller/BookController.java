package pl.coderslab.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping("/add-with-publisher")
  @ResponseBody
  public String create(){
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
