package dev.rainbow.movies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAllMovies() {
        // Erstellen Sie eine Liste von Beispiel-Filmen
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie(/* Hier fügen Sie die erforderlichen Feldwerte hinzu */));
        movieList.add(new Movie(/* Hier fügen Sie die erforderlichen Feldwerte hinzu */));

        // Wenn movieRepository.findAll() aufgerufen wird, geben Sie die Beispiel-Filmliste zurück
        when(movieRepository.findAll()).thenReturn(movieList);

        // Rufen Sie die Methode allMovies() auf dem Service auf
        List<Movie> result = movieService.allMovies();

        // Überprüfen Sie, ob die zurückgegebene Liste die erwartete Liste von Filmen ist
        assertEquals(movieList, result);

        // Überprüfen Sie, ob movieRepository.findAll() genau einmal aufgerufen wurde
        verify(movieRepository, times(1)).findAll();
    }

    @Test
    public void testSingleMovie() {
        // Erstellen Sie eine Beispiel-IMDb-ID
        String imdbId = "tt1234567";

        // Erstellen Sie einen Beispiel-Film
        Movie movie = new Movie(/* Hier fügen Sie die erforderlichen Feldwerte hinzu */);

        // Wenn movieRepository.findMovieByImdbId() mit der IMDb-ID aufgerufen wird, geben Sie den Beispiel-Film zurück
        when(movieRepository.findMovieByImdbId(imdbId)).thenReturn(Optional.of(movie));

        // Rufen Sie die Methode singleMovie() auf dem Service auf
        Optional<Movie> result = movieService.singleMovie(imdbId);

        // Überprüfen Sie, ob der zurückgegebene Film dem erwarteten Film entspricht
        assertEquals(Optional.of(movie), result);

        // Überprüfen Sie, ob movieRepository.findMovieByImdbId() genau einmal mit der IMDb-ID aufgerufen wurde
        verify(movieRepository, times(1)).findMovieByImdbId(imdbId);
    }
}
