// Name: Shlok Kalekar
// Andrew ID: skalekar

import java.util.Comparator;

public class SortByYear implements Comparator<Movie> {

    @Override
    public int compare(Movie movie1, Movie movie2) {
        return Integer.compare(movie1.getYear(), movie2.getYear());
    }
}
