package books.books.Controller;


import books.books.Model.Book;
import books.books.Repository.AuthorRepository;
import books.books.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
public class BookController {

   // @Autowired
   // AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;

    //Read All
    @CrossOrigin(origins = "*")
    @GetMapping("/book")
    public Iterable<Book> allBooks() {
        return bookRepository.findAll();
    }

    //Read One
    @CrossOrigin(origins = "*")
    @GetMapping("/book/{id}")
    public ResponseEntity<Optional<Book>> findOne(@PathVariable Long id) {
        Optional<Book> response = bookRepository.findById(id);

        if (response.isPresent())
            return ResponseEntity.status(200).body(response);
        else {
            return ResponseEntity.status(400).body(response);
        }
    }

    //Create
    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    @PostMapping("/book")
    public ResponseEntity<String> create(@ModelAttribute Book b){
        Book book =bookRepository.save(b);
        return ResponseEntity.status(201). header("Location", "/" + book.getId()).body("{msg : Book created}");
    }

    //Delete
    @CrossOrigin(origins = "*")
    @DeleteMapping("/book/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        bookRepository.deleteById(id);
        return ResponseEntity.status(204).body("{msg : Book Deleted}");
    }

    //Update
    @CrossOrigin(origins = "*")
    @PutMapping("/book")
        public ResponseEntity<String> update(@ModelAttribute Book b){
            bookRepository.save(b);
        return ResponseEntity.status(204).body("{msg : Book updated");
    }

}
