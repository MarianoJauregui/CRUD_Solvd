package entities;

public class Principal {
    private Long id;
    private String name;
    private String lastName;
    private int age;
    private University university;

    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }

    public University getUniversity(){
        return university;
    }
    public void setUniversity(University university){
        this.university = university;
    }
}
