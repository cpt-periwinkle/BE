// Name: Shlok Kalekar
// Andrew ID: skalekar

import java.util.ArrayList;

public class Movie implements Comparable<Movie> {
	private int movieID;
	private String movieName;
	private int year;
	private String country;
	private ArrayList<String> genres;
	
    // Overloaded constructor
	public Movie(int movieID, String movieName, int year, String country, ArrayList<String> genres ) {
		this.movieID = movieID;
		this.movieName = movieName;
		this.year = year;
		this.country = country;
		this.genres = genres;
	}
	
    // Don't change this
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String g: genres) {
			sb.append(g + " ");
		}
		return movieID + " " + movieName + " " + year +
				" " + country + " " + sb.toString().strip();
	}

    public int getMovieID() { return movieID; }
	public String getMovieName() { return movieName; }
	public int getYear() { return year; } 
	public String getCountry() { return country; }
	public ArrayList<String> getGenres() { return genres; }


    // Compare on movieID
	public int compareTo(Movie two) {
        // Your code here
        return Integer.compare(this.movieID, two.movieID);
	}
	
}
