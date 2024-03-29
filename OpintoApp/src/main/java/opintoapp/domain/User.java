package opintoapp.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Sovelluksen käyttäjää edustava luokka.
 *
 */
public class User {

    private String username;
    private String name;
    private String pswd;

    public User(String username, String name, String pswd) {
        this.username = username;
        this.name = name;
        this.pswd = pswd;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPswd() {
        return pswd;
    }

    @Override
    public String toString() {
        return this.username + ", " + this.name;
    }
}
