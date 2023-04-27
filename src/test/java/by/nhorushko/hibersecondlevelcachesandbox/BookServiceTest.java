package by.nhorushko.hibersecondlevelcachesandbox;

import com.yannbriancon.interceptor.HibernateQueryInterceptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {


    @Autowired
    BookService service;

    @Autowired
    protected HibernateQueryInterceptor queryInterceptor;

    protected void startQueryCount() {
        System.out.println("======================= START QUERY COUNTER ====================================");
        queryInterceptor.startQueryCount();
    }

    protected Long getQueryCount() {
        Long queryCount = queryInterceptor.getQueryCount();
        System.out.println("======================= END QUERY COUNTER ================================ queries: " + queryCount);
        return queryCount;
    }

    @Test
    void getById_shouldBeOneTransactional() {
        startQueryCount();
        service.getById(1);
        service.getById(1);
        service.getById(1);
        service.getById(1);
        service.getById(1);
        assertEquals(1, getQueryCount());
    }

    @Test
    void getByIds_shouldBeOneTransactional() {
        startQueryCount();
        service.getById(List.of(1, 2, 3));
        service.getById(1);
        service.getById(2);
        service.getById(3);
        assertEquals(1, getQueryCount());
    }

    @Test
    void getByTitle_shouldBeOneTransactions() {
        startQueryCount();
        service.getByTitle("1984");
        service.getById(3);
        assertEquals(1, getQueryCount());
    }

    @Test
    void updateTitleAndGetById_shouldBeTwoTransactions() {
        startQueryCount();
        service.updateTitle(3, "1985");
        assertEquals("1985", service.getById(3).getTitle());
        assertEquals(1, getQueryCount());
    }

    @Test
    void updateTitleAndGetById_shouldBeThreeTransactions() {
        startQueryCount();
        service.getById(3);
        service.updateTitle(3, "1985");
        assertEquals("1985", service.getById(3).getTitle());
        assertEquals(3, getQueryCount());
    }

    @Test
    void updateTitleByAuthorAndGetById_shouldBeTwoTransactions2() {
        startQueryCount();
        service.getById(3);
        service.updateTitleByAuthor("George Orwell", "1985");
        assertEquals("1985", service.getById(3).getTitle());
        assertEquals(3, getQueryCount());
    }
}
