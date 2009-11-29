package demo.session;

import java.io.Serializable;
import java.util.Map;

import demo.smart.util.ExternalContextUtil;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private boolean authenticated;

    private String firstName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAuthenticated() {
        return authenticated && username != null;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public static User get() {
        Map<Object, Object> sessionMap = ExternalContextUtil.getSessionMap();
        User user = (User) sessionMap.get(SessionKeys.USER);
        if (user == null) {
            return new User();
        }
        return user;
    }

    public static void put(User signin) {
        Map<Object, Object> sessionMap = ExternalContextUtil.getSessionMap();
        sessionMap.put(SessionKeys.USER, signin);
    }

    public static void clear() {
        Map<Object, Object> sessionMap = ExternalContextUtil.getSessionMap();
        sessionMap.remove(SessionKeys.USER);
    }
}
