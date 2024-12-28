// Name: Shlok Kalekar
// Andrew ID: skalekar

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Lab16Main {
	public ArrayList<Movie> movieList = new ArrayList<Movie>();
	private static Scanner keyboard = new Scanner(System.in);
    public static final int MOVIE_COUNT = 5; // Return this many movies in the searches

	private Map<String, Movie> byNameMap = new HashMap<>();
	private Map<Integer, ArrayList<Movie>> byYearMap = new HashMap<>();
	private Map<String, ArrayList<Movie>> byGenreMap = new HashMap<>();
	
    /*
      Partially complete
      Add your code where needed
    */

	public static void main(String[] args) {
		Lab16Main lab16main = new Lab16Main();
		lab16main.readMovies("movies.tsv");
		int choice;
		do {
			choice = getMenuChoice();
			switch (choice) {
                // case 1 is done for you
				case 1: lab16main.sortBy("ID");
					lab16main.displayMovies(lab16main.getList());
					break;
				case 2:
                    lab16main.sortBy("Name");
                    lab16main.displayMovies(lab16main.getList());
					break;
				case 3:
                    // Your code here
					lab16main.sortBy("Year");
					lab16main.displayMovies(lab16main.getList());
					break;
				case 4:
                    // Your code here
					lab16main.sortBy("ReverseYear");
					lab16main.displayMovies(lab16main.getList());
					break;
				case 5:
					System.out.print("Enter the movie name: ");
                    // Use nextLine() everywhere!
					String name = keyboard.nextLine();
                    // Do something with name
					lab16main.displayMovies(lab16main.searchByName(name));
					break;
				case 6:
                    // Your code here
					System.out.print("Enter the movie year: ");
					int year = Integer.parseInt(keyboard.nextLine());
					lab16main.displayMovies(lab16main.searchByYear(year));
					break;
				case 7:
                    // Your code here
					System.out.print("Enter the movie genre: ");
					String genre = keyboard.nextLine();
					lab16main.displayMovies(lab16main.searchByGenre(genre));
					break;
				case 8:
					lab16main.displayTotals();
					System.out.println("Bye");
					break;
			}
		} while (choice != 8);
	}
	
    /*
       Don't change this method
    */
	public static int getMenuChoice() {
		System.out.println("1. Display by ID\n2. Display by name\n3. Display by year\n" + 
				"4. Display by year in reverse\n5. Search by name\n6. Search by year\n" +
				"7. Search by genre\n8. Quit");
		System.out.print("Enter your choice: ");
		int choice = Integer.parseInt(keyboard.nextLine());
		if (choice < 1 || choice > 8) choice = 8;
		return choice;
	}

	public void readMovies(String filename) { // Lab 17
		Scanner fileInput = null;

		try {
			fileInput = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if (fileInput != null) {
			while (fileInput.hasNextLine()) {
				String line = fileInput.nextLine();
				String[] fields = line.split("\t");
				Movie movie = toMovie(fields);
				movieList.add(movie);

				byNameMap.put(movie.getMovieName(), movie);

				byYearMap.computeIfAbsent(movie.getYear(), k -> new ArrayList<>()).add(movie);

				for (String genre : movie.getGenres()) {
					byGenreMap.computeIfAbsent(genre, k -> new ArrayList<>()).add(movie);
				}
			}
			fileInput.close();
		}
	}
		
//	public void readMovies(String filename) { // Lab 16
//		Scanner fileInput = null;
//
//		try {
//			fileInput = new Scanner(new File(filename));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//
//        /* Your code here
//           While there are lines in the file
//            read a line and split it on \t
//            create a Movie using toMovie
//            add it to movieList
//        */
//
//		if (fileInput != null)	{
//			while(fileInput.hasNextLine())	{
//				String line = fileInput.nextLine();
//				String[] fields = line.split("\t");
//				Movie movie = toMovie(fields);
//				movieList.add(movie);
//			}
//		}
//	}
	
	public Movie toMovie(String[] str) {
        /*  Returns one Movie from the data in str
            Each line of str should contain one field
            Change last one to ArrayList<String> for genres
        */
		int movieID = Integer.parseInt(str[0].strip());
		String movieName = str[1].strip().replace("\"", "");
		int year = Integer.parseInt(str[2].strip());
		String country = str[3].strip().replace("\"", "");
		ArrayList<String> genres = new ArrayList<String>();
        // Start at position #4
        // Strip and add to the ArrayList of genres
		for (int i=4; i<str.length; i++) {
			genres.add(str[i].strip());
		}
		return new Movie(movieID, movieName, year, country, genres);
	}
	
    // Don't change this, even if you don't like my table spacing
	private void displayMovies(ArrayList<Movie> list) {
		if (list.size() == 0) {
			System.out.println("Nothing to display");
		} else {
			System.out.format("%7s %50s %5s %30s %6s\n", "ID", "Name", "Year", "Country", "Genres");
			for (Movie m: list) {
				System.out.format("%7s %50s %5d %30s ", m.getMovieID(), m.getMovieName(), 
					m.getYear(), m.getCountry());
				for (int i=0; i<m.getGenres().size(); i++) {
					System.out.print(m.getGenres().get(i) + " ");
				}
				System.out.println();
			}
		}
		System.out.println();
	}
	
    // Sort according to the field indicated by s
	public void sortBy(String s) {
        switch (s) {
            case "ID":
                // Use Movie's built-in compareTo
                Collections.sort(movieList);
                break;
            case "Name":
                // Your code here
				Collections.sort(movieList, Comparator.comparing(Movie::getMovieName));
                break;
            case "Year":
                // Your code
				Collections.sort(movieList, Comparator.comparing(Movie::getYear));
                break;
            case "ReverseYear":
                // Your code here
				Comparator<Movie> reverseYears = Collections.reverseOrder(new SortByYear());
				Collections.sort(movieList, reverseYears);
                break;
        }
	}

	public ArrayList<Movie> searchByName(String name) {	// Lab 17
		ArrayList<Movie> list = new ArrayList<>();
		Movie movie = byNameMap.get(name);
		if (movie != null) list.add(movie);
		return list;
	}

    // Search for MOVIE_COUNT movies by name
//	public ArrayList<Movie> searchByName(String name) {	// Lab 16
//        // Sort by id before searches for consistent results
//        sortBy("ID");
//
//        // List of results
//		ArrayList<Movie> list = new ArrayList<Movie>();
//
//        // Count the # of matches
//		int count = 0;
//		for (Movie m: movieList) {
//            // Does m match on the name key?
//			if (m.getMovieName().equals(name)) {
//                // Yes, so add it to the result list
//				list.add(m);
//				count++;
//
//                // Quit if we hit the maximum # of movies to return
//				if (count == Lab16Main.MOVIE_COUNT) break;
//			}
//		}
//		return list;
//	}

	public ArrayList<Movie> searchByYear(int year) {	// Lab 17
		return byYearMap.getOrDefault(year, new ArrayList<>());
	}
	
    // Search for MOVIE_COUNT movies by year
//	public ArrayList<Movie> searchByYear(int year) {	// Lab 16
//		ArrayList<Movie> list = new ArrayList<Movie>();
//
//        // Fill up list with MOVIE_COUNT movies that match on year
//        // Your code here
//		int count = 0;
//		for (Movie m: movieList) {
//			if (m.getYear() == year) {
//				list.add(m);
//				count++;
//				if (count == Lab16Main.MOVIE_COUNT) break;
//			}
//		}
//
//		return list;
//	}

	public ArrayList<Movie> searchByGenre(String genre) { // Lab 17
		return byGenreMap.getOrDefault(genre, new ArrayList<>());
	}

	// Search for MOVIE_COUNT movies by genre
//	public ArrayList<Movie> searchByGenre(String genre) {	// Lab 16
//		ArrayList<Movie> list = new ArrayList<Movie>();
//
//        // Fill up list with MOVIE_COUNT movies that match on genre
//        // Your code here
//		int count = 0;
//		for (Movie m: movieList) {
//			if (m.getGenres().contains(genre)) {
//				list.add(m);
//				count++;
//				if (count == Lab16Main.MOVIE_COUNT) break;
//			}
//		}
//
//		return list;
//	}

	public void displayTotals() {
		System.out.println("Movie totals");
		System.out.println("movieList size : " + movieList.size());
		System.out.println("byNameMap size : " + byNameMap.size());
		System.out.println("byYearMap size : " + byYearMap.size());
		System.out.println("byGenreMap size : " + byGenreMap.size());
	}

	// Breaks encapsulation, boo!
	public ArrayList<Movie> getList() { return movieList; }

}
