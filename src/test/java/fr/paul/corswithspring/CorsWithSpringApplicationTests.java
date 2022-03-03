package fr.paul.corswithspring;

import fr.paul.corswithspring.controller.BookController;
import fr.paul.corswithspring.controller.ConfBookController;
import fr.paul.corswithspring.controller.PublicBookController;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CorsWithSpringApplicationTests {

    @Autowired
    PublicBookController publicBookController;

    @Autowired
    BookController bookController;

    @Autowired
    ConfBookController confBookController;

    @Test
    void publicBookControllerLoads() {
        assertThat(publicBookController).isNotNull();
    }

    @Test
    void bookControllerLoads() {
        assertThat(bookController).isNotNull();
    }

    @Test
    void configBookControllerLoads() {
        assertThat(confBookController).isNotNull();
    }

}
