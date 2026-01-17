package software.ulpgc.application;

import software.ulpgc.architecture.io.RemoteStore;
import software.ulpgc.architecture.model.Movie;

import java.io.IOException;
import java.util.stream.Stream;

public class main {
    public static void main(String[] args) throws IOException {
        Stream<Movie> movies = new RemoteStore(MovieDeserializer::fromTsv).loadAll();
        Histogram histogram = HistogramBuilder.with(movies).build(m -> (m.year() / 10) * 10);
        System.out.println(histogram.size());
        for (Integer bin: histogram){
            System.out.println(bin + ":" + histogram.count(bin));
        }
    }

}
