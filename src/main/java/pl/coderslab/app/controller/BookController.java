package pl.coderslab.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.app.dao.BookDao;
import pl.coderslab.app.dao.PublisherDao;
import pl.coderslab.app.entity.Book;
import pl.coderslab.app.entity.Category;
import pl.coderslab.app.entity.Publisher;
import pl.coderslab.app.repositories.BookRepository;
import pl.coderslab.app.repositories.CategoryRepository;

@RequestMapping("/books")
@Controller
@RequiredArgsConstructor
public class BookController {

  private final BookDao bookDao;
  private final PublisherDao publisherDao;
  private final BookRepository bookRepository;
  private final CategoryRepository categoryRepository;

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


  @GetMapping("/testRepo")
  @ResponseBody
  public String testRepo(){
    Category category = categoryRepository.findById(1L).get();

    List<Book> withCategory = bookRepository.findByTitle("Z Kategoria");
    List<Book> byTitle = bookRepository.pleaseGiveMeBooksByTitle("Z Kategoria");

    List<Book> byCategory = bookRepository.findByCategory(category);
    List<Book> byCategoryQuery = bookRepository.pleaseGiveMeBooksByCategory(category);
    //    List<Book> byCategoryId = bookRepository.findByCategoryId(2L);
//    List<Book> byCategoryName = bookRepository.findByCategoryName("Kategoria 33");


    withCategory.forEach(System.out::println);
    System.out.println();
    byTitle.forEach(System.out::println);
    System.out.println();


//    byCategoryId.forEach(System.out::println);
    System.out.println();
    byCategory.forEach(System.out::println);
    System.out.println();
    byCategoryQuery.forEach(System.out::println);
    System.out.println();
//    byCategoryName.forEach(System.out::println);


    return "sprawdz w kosnoli";
  }
  @PostMapping("/add")
  public String create(@Valid Book book, BindingResult result){
    if(result.hasErrors()){
      return "book-form";
    }

    Category category = new Category();
    category.setName("Kategoria 33");
    categoryRepository.save(category);

    book.setCategory(category);
    bookRepository.save(book);

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
