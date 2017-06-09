package io.uscool.quizapp.models;

/**
 * Created by ujjawal on 9/6/17.
 */

public class Chapter {
    private String id;
    private String name;


    public Chapter() {}

    public Chapter(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
