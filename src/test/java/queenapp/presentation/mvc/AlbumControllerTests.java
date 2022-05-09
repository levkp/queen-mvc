package queenapp.presentation.mvc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import queenapp.service.QueenUserService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AlbumControllerTests {
    @Autowired
    MockMvc mvc;

    @Autowired
    QueenUserService userService;

    @BeforeAll
    void setup() {
        userService.create("tester", "secret", false);
    }

    @Test
    @WithUserDetails("tester")
    void requestingAlbumsPageWithAuthReturnsAlbumsPage() throws Exception {
        mvc.perform(get("/albums")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("albums"))
                .andExpect(model().attributeExists("albums"))
                .andExpect(model().attributeExists("ownerName"));
    }
}
