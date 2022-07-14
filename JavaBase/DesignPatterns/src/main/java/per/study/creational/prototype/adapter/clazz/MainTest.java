package per.study.creational.prototype.adapter.clazz;


import per.study.creational.prototype.adapter.MoviePlayer;

public class MainTest {
    public static void main(String[] args) {
        JPMoviePlayerAdapter adapter = new JPMoviePlayerAdapter(new MoviePlayer());
        adapter.play();
    }
}
