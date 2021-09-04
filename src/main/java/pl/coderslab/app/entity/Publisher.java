package pl.coderslab.app.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "publishers")
@Data
public class Publisher {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

//  @OneToMany(mappedBy = "publisher", fetch = FetchType.EAGER)
//  private List<Book> books = new ArrayList<>();
}
