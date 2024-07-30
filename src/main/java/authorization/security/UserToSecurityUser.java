package authorization.security;

import authorization.entity.Role;
import authorization.entity.User;

import java.util.stream.Collectors;

public class UserToSecurityUser {

    public static SecurityUser from(User user){
        return new SecurityUser(user.getUsername(), user.getPassword(),
                user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
    }

}
