package io.uscool.quizapp.models;

/**
 * Created by ujjawal on 8/6/17.
 */

public class Subject {
    private String name;
    private String id;
    private String subjectIcon;

    private int icon_id;
    private int underline_color_id;

    public Subject() {}
    public Subject(String subjectId, String subjectName) {
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

    public void setIcon_id(int icon_id) {
        this.icon_id = icon_id;
    }

    public int getIcon_id() {
        return icon_id;
    }

    public void setUnderline_color_id(int underline_color_id) {
        this.underline_color_id = underline_color_id;
    }

    public int getUnderline_color_id() {
        return underline_color_id;
    }
}
