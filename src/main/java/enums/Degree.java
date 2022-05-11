package enums;

public enum Degree {
    LAWYER("Lawyer");

    private final String degree;

    Degree(String degree){
        this.degree = degree;
    }

    public String getDegree(){
        return degree;
    }
}
