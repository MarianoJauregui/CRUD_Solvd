package daos.enums;

public enum Fields {
    ID("id"),
    NAME("first_name"),
    LAST_NAME("last_name"),
    EMAIL("email"),
    AGE("age"),
    UNIVERSITY_ID("University_id");

    private final String FIELD;

    Fields(String FIELD){
        this.FIELD = FIELD;
    }

    public String getFIELD() {
        return FIELD;
    }
}
