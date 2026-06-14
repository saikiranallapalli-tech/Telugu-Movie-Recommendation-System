import java.util.*;

public class main {

    static class Movie {
        String name;
        String genre;

        Movie(String name, String genre) {
            this.name = name;
            this.genre = genre;
        }
    }

    static List<Movie> movies = new ArrayList<>();

    public static void loadMovies() {

        movies.add(new Movie("Baahubali", "Action Fantasy"));
        movies.add(new Movie("RRR", "Action Drama"));
        movies.add(new Movie("Pushpa", "Action Crime"));
        movies.add(new Movie("Ala Vaikunthapurramuloo", "Family Comedy"));
        movies.add(new Movie("Arjun Reddy", "Romance Drama"));
        movies.add(new Movie("Eega", "Fantasy Adventure"));
        movies.add(new Movie("Magadheera", "Action Fantasy"));
        movies.add(new Movie("Jersey", "Sports Drama"));
        movies.add(new Movie("HIT", "Crime Thriller"));
        movies.add(new Movie("Kalki 2898 AD", "Sci-Fi Action"));
    }

    public static double similarity(String g1, String g2) {

        String[] a = g1.split(" ");
        String[] b = g2.split(" ");

        int common = 0;

        for(String x : a){
            for(String y : b){
                if(x.equalsIgnoreCase(y)){
                    common++;
                }
            }
        }

        return (double) common /
                (a.length + b.length - common);
    }

    public static void recommend(String movieName){

        Movie selected = null;

        for(Movie m : movies){
            if(m.name.equalsIgnoreCase(movieName)){
                selected = m;
                break;
            }
        }

        if(selected == null){
            System.out.println("Movie not found");
            return;
        }

        Map<String,Double> scores =
                new HashMap<>();

        for(Movie m : movies){

            if(!m.name.equalsIgnoreCase(movieName)){

                double score =
                        similarity(
                                selected.genre,
                                m.genre);

                scores.put(m.name,score);
            }
        }

        scores.entrySet()
                .stream()
                .sorted((a,b)->
                        Double.compare(
                                b.getValue(),
                                a.getValue()))
                .limit(5)
                .forEach(x ->
                        System.out.println(
                                x.getKey()
                                +" Score="
                                +x.getValue()));
    }

    public static void main(String[] args){

        loadMovies();

        Scanner sc =
                new Scanner(System.in);

        System.out.print(
                "Enter Movie Name: ");

        String movie =
                sc.nextLine();

        System.out.println(
                "\nTop Recommendations:");

        recommend(movie);
    }
}