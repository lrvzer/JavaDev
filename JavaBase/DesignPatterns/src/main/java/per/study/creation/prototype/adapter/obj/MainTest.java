package per.study.creation.prototype.adapter.obj;

import per.study.creation.prototype.adapter.MoviePlayer;

public class MainTest {
    public static void main(String[] args) {
        JPMoviePlayerAdapter adapter = new JPMoviePlayerAdapter(new MoviePlayer());
        adapter.play();
    }
}
