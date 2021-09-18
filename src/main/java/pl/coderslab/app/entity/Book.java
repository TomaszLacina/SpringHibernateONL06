package pl.coderslab.app.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.pl.PESEL;

@Entity
@Table(name = "books")
@Data
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(min = 5)
  private String title;

  @Range(min = 1, max = 10)
  private int rating;

  @Size(max = 600)
  private String description;

  @ManyToOne
  @NotNull
  private Publisher publisher;

  @Min(1)
  private int pages;
}
