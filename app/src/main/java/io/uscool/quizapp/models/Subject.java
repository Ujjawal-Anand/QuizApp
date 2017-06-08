package io.uscool.quizapp.models;

/**
 * Created by ujjawal on 8/6/17.
 */

public class Subject {
    private String name;
    private String id;
    private String subjectIcon;

    public Subject() {}
    public Subject(String subjectName, String subjectId) {
        this.name = subjectName;
        this.id = subjectId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSubjectIcon() {
        return subjectIcon;
    }

    public void setSubjectIcon(String subjectIcon) {
        this.subjectIcon = subjectIcon;
    }
}
