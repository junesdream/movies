package dev.rainbow.movies;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class MovieControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;


    @Test
    void testGetAllMovies() throws Exception {
        // Führe die GET-Anfrage aus und überprüfe die Antwort
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/movies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray()); // Überprüfe, ob die Antwort ein JSON-Array ist
    }


    @Test
    void testGetSingleMovie() throws Exception {
        // Ersetze dies durch eine gültige IMDb-ID aus deinem Datenbestand
        String imdbIdToTest = "IMDB123";

        // Erzeuge ein Beispiel-Movie-Objekt mit der IMDb-ID
        Movie movie = new Movie();
        movie.setImdbId(imdbIdToTest);

        // Konfiguriere Mockito, um die Methode singleMovie im MovieService zu stubben
        Mockito.when(movieService.singleMovie(imdbIdToTest)).thenReturn(Optional.of(movie));

        // Führe die GET-Anfrage aus und überprüfe die Antwort
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/movies/{imdbId}", imdbIdToTest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.imdbId").value(imdbIdToTest));
    }
}

