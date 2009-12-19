package demo.smart.helper;

import java.util.concurrent.Callable;

import demo.smart.session.User;

public class AuthorizationHelper implements Callable<Boolean> {

    @Override
    public Boolean call() throws Exception {
        User user = User.get();
        return user.isAuthorized();
    }

}
