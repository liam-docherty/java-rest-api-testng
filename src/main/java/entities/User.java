package entities;

public class User {
    public static final String LOGIN = "login";
    public static final String ID = "id";

    private String login;
    private int id;

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    // TODO: Add all other fields, otherwise jackson will throw an UnrecognizedPropertyException
}
