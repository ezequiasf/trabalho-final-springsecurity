package com.dbccompany.receitasapp.utils;

import com.dbccompany.receitasapp.entity.UserEntity;
import com.dbccompany.receitasapp.exceptions.UserNotActiveException;

public class Verify {

    public static void userActivity (UserEntity user) throws UserNotActiveException {
        if (!user.getIsActive()) {
            throw new UserNotActiveException("User inactive!");
        }
    }
}
