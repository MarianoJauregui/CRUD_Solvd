package enums;

public enum DegreeList {
    LAWYER("Lawyer"),
    PHILOSOPHER("Philosopher");

    private final String degree;

    DegreeList(String degree){
        this.degree = degree;
    }

    public String getDegree(){
        return degree;
    }
}
