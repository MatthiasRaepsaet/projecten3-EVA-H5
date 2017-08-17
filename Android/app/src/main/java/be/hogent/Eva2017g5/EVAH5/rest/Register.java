package be.hogent.Eva2017g5.EVAH5.rest;

/**
 * Created by sofie.
 */

public class Register {
    private String username;
    private String password;
    private String email, firstname, lastname;

    public Register( String email,String firstname,  String lastname, String password,String username) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
