package enums;

public enum Subject {
    HIST("History"),
    POL_SCI("Political science"),
    ROM_LAW("Roman law"),
    THEO("Theology"),
    CIV_LAW("Civil law");
    
    private final String subject;

     Subject(String subject){
        this.subject = subject;
    }
    
    public String getSubject(){
         return subject;
    }
}
