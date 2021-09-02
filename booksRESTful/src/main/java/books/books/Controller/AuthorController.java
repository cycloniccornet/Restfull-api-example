package books.books.Controller;

import books.books.Model.Author;
import books.books.Model.Book;
import books.books.Repository.AuthorRepository;
import books.books.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthorController {


    @Autowired
    AuthorRepository authorRepository;
  //  @Autowired
   // BookRepository bookRepository;

    //Read All
    @CrossOrigin(origins = "*")
    @GetMapping("/author")
    public Iterable<Author> allAuthors() {
        return authorRepository.findAll();
    }

    //Read One
    @CrossOrigin(origins = "*")
    @GetMapping("/author/{id}")
    public ResponseEntity<Optional<Author>> findOne(@PathVariable Long id) {
        Optional<Author> response = authorRepository.findById(id);

        if (response.isPresent())
            return ResponseEntity.status(200).body(response);
        else {
            return ResponseEntity.status(400).body(response);
        }
    }

    //Create
    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    @PostMapping("/author")
    public ResponseEntity<String> create(@ModelAttribute Author a){
        Author author = authorRepository.save(a);
        return ResponseEntity.status(201). header("Location", "/" + author.getId()).body("{msg : Author created}");
    }

    //Delete
    @CrossOrigin(origins = "*")
    @DeleteMapping("/author/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        authorRepository.deleteById(id);
        return ResponseEntity.status(200).body("{msg : Author Deleted}");
    }

    //Update
    @CrossOrigin(origins = "*")
    @PutMapping("/author")
    public ResponseEntity<String> update(@ModelAttribute Author a){
        authorRepository.save(a);
        return ResponseEntity.status(204).body("{msg : Author updated");
    }

}

