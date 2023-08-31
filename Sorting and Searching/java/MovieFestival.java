import java.io.*;
import java.util.*;

public class MovieFestival {

	protected static class Movie {
        int startTime;
        int endTime;

        Movie(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        int getStartTime() {
            return startTime;
        }

        int getEndTime() {
            return endTime;
        }

		@Override
		public String toString() {
			return "Movie [startTime=" + startTime + ", endTime=" + endTime + "]";
		}
    }

    public static void main(String[] args) throws IOException {
		// Reader
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		List<Movie> moviesList = new ArrayList<>();
		
        // Get total movies to be displayed at the festival
        int totalMovies = Integer.parseInt(read.readLine());
		int startTime = 0;
		int endTime = 0;

		// Get Movie start and end time and store in array
		for (int i = 0; i < totalMovies; i++) {
            StringTokenizer customer = new StringTokenizer(read.readLine());
			startTime = Integer.parseInt(customer.nextToken());
			endTime = Integer.parseInt(customer.nextToken());
			moviesList.add(new Movie(startTime, endTime));
		}

		// sort the list based on endTime
		moviesList.sort(Comparator.comparingInt(Movie::getEndTime));
        //System.out.println("MoviesList: " + moviesList.toString());

		int maxMovies = 0;
		int previousMovieEndTime = 0;
		for (Movie movie : moviesList) {
			if (movie.getStartTime() >= previousMovieEndTime) {
				maxMovies++;
				previousMovieEndTime = movie.getEndTime();
			}
		}

        // print the solution
		System.out.println(maxMovies);
        
        return;
	}
}
