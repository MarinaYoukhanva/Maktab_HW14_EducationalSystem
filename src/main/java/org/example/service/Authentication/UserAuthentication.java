package org.example.service.Authentication;

import lombok.Getter;
import lombok.Setter;
import org.example.entity.User;

public class UserAuthentication {

    @Getter
    @Setter
    private static User loggedInUser;

    public static void logout(){
        loggedInUser = null;
    }

}
