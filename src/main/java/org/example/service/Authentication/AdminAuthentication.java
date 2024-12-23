package org.example.service.Authentication;

import lombok.Getter;
import lombok.Setter;
import org.example.entity.Admin;

public class AdminAuthentication {

    @Setter
    @Getter
    private static Admin loggedInAdmin;

    public static void logout(){
        loggedInAdmin = null;
    }

}
