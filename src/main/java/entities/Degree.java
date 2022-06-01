package entities;

public class Degree {
    private Long id;
    private String name;
    private String duration;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDuration(String duration){
        this.duration = duration;
    }

    public String getDuration(){
        return duration;
    }


}
