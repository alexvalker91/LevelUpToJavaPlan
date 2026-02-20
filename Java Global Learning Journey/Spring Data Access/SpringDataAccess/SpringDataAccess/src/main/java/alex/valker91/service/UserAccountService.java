package alex.valker91.service;

import alex.valker91.model.User;
import alex.valker91.model.UserAccount;

public interface UserAccountService {

    int refillUserAccount(long userId, int amount);
    UserAccount getUserAccountByUserId(long userId);
    UserAccount updateUserAccount(UserAccount userAccount);
    UserAccount createUserAccount(UserAccount userAccount);
}
