package net.canang.cfi.web.so.client.model;

/**
 * @author rafizan.baharum
 * @since 10/20/13
 */
public class UserModel extends PrincipalModel {

    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String REALNAME = "realname";

    public UserModel() {
    }

    public String getUsername() {
        return get(USERNAME);
    }

    public void setUsername(String username) {
        set(USERNAME, username);
    }

    public String getEmail() {
        return get(EMAIL);
    }

    public void setEmail(String email) {
        set(EMAIL, email);
    }

    public String getPassword() {
        return get(PASSWORD);
    }

    public void setPassword(String password) {
        set(PASSWORD, password);
    }

    public String getRealname() {
        return get(REALNAME);
    }

    public void setRealname(String realname) {
        set(REALNAME, realname);
    }
}
