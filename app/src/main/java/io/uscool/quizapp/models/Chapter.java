package io.uscool.quizapp.models;

/**
 * Created by ujjawal on 9/6/17.
 */

public class Chapter {
    private String id;
    private String name;
    private int seq;

    public Chapter() {}

    public Chapter(String id, String name, int seq) {
        this.id = id;
        this.name = name;
        this.seq = seq;
    }

    public int getSeq() {
        return seq;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
