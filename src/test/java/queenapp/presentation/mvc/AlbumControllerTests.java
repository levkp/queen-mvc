package queenapp.presentation.mvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AlbumControllerTests {
    @Autowired
    MockMvc mockMvc;

    @Test
    void showAllAlbumsUseCorrectViewAndModel() throws Exception {


    }
}
