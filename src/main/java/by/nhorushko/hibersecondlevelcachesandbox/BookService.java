package by.nhorushko.hibersecondlevelcachesandbox;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book getById(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Book> getById(Collection<Integer> ids) {
        return repository.findAllById(ids);
    }

    public Book getByTitle(String title) {
        return repository.findBookByTitle(title).orElseThrow();
    }

    public void updateTitle(Integer id, String title) {
        repository.updateTitle(id, title);
    }

    public void updateTitleByAuthor(String author, String title) {
        repository.updateTitleByAuthor(author, title);
    }
}
