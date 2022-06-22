package entities;

public class Subject {
    public Subject() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    private Long id;
    private String theme;

    public Subject(String theme) {
    }

    public Subject(Long id, String theme){}
}
