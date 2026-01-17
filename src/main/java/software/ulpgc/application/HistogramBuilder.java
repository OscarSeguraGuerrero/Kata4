package software.ulpgc.application;

import software.ulpgc.architecture.model.Movie;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class HistogramBuilder {
    private final Stream<Movie> movies;

    public static HistogramBuilder with(Stream<Movie> movies){
        return new HistogramBuilder(movies);

    }

    private HistogramBuilder(Stream<Movie> movies) {
        this.movies = movies;
    }

    public Histogram build(Function<Movie, Integer> binarize){
        Histogram histogram = new Histogram();
        movies.map(binarize).forEach(histogram::addTo);
        return histogram;
    }
}
