package by.nhorushko.hibersecondlevelcachesandbox;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByTitle(String title);

    @Modifying
    @Query("UPDATE Book e SET e.title = :title WHERE e.id =:id ")
    void updateTitle(Integer id, String title);

    @Modifying
    @Query("UPDATE Book e SET e.title = :title WHERE e.author =:author")
    void updateTitleByAuthor(String author, String title);

    Optional<Book> findBookByTitle(String title);
}
