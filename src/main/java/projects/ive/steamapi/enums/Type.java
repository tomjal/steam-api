package projects.ive.steamapi.enums;

public enum Type {
    GAME("game"),
    UNDEFINED("undefined");

    private String value;

    private Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
