package software.ulpgc.application;

import software.ulpgc.architecture.io.RemoteStore;
import software.ulpgc.architecture.model.Movie;

import java.io.IOException;
import java.util.stream.Stream;

public class main {
    public static void main(String[] args) throws IOException {
        Display
                .create()
                .show(histogram())
                .setVisible(true);
    }

    private static Histogram histogram() throws IOException {
        Histogram histogram = HistogramBuilder.
                with(movies()).
                tittle("Histogram").
                x("EJEX").
                y("EJEY").
                leyend("Movies").
                build(m -> (m.year() / 10) * 10);
        return histogram;
    }

    private static Stream<Movie> movies() throws IOException {
        return new RemoteStore(MovieDeserializer::fromTsv)
                .loadAll()
                .limit(1000);
    }

}
