package alex.valker91.service.impl;

import alex.valker91.entity.UserAccountDb;
import alex.valker91.entity.UserDb;
import alex.valker91.model.UserAccount;
import alex.valker91.model.impl.UserAccountImpl;
import alex.valker91.repository.DbUserAccountRepository;
import alex.valker91.service.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class DbUserAccountServiceImpl implements UserAccountService {

    private static final Logger log = LoggerFactory.getLogger(DbUserAccountServiceImpl.class);

    @Autowired
    private DbUserAccountRepository dbUserAccountRepository;

    @Override
    public int refillUserAccount(long userId, int amount) {
        Optional<UserAccountDb> userAccountDbOptional = dbUserAccountRepository.findById(userId);
        if (userAccountDbOptional.isPresent()) {
            UserAccountDb userAccountDb = userAccountDbOptional.get();
            userAccountDb.setAmount(userAccountDb.getAmount() + amount);
            this.dbUserAccountRepository.save(userAccountDb);
            log.info("User with id {} successfully found ", userId);
            return amount;
        } else {
            log.info("Can not to find an user by id: {}", userId);
            return -1;
        }
    }

    @Override
    public UserAccount getUserAccountByUserId(long userId) {
        Optional<UserAccountDb> userAccountDbOptional = dbUserAccountRepository.findById(userId);
        if (userAccountDbOptional.isPresent()) {
            UserAccountDb userAccountDb = userAccountDbOptional.get();
            log.info("User with id {} successfully found ", userId);
            return mapUserAccountDbToUserAccount(userAccountDb);
        } else {
            log.info("Can not to find an user by id: {}", userId);
            return null;
        }
    }

    @Override
    public UserAccount updateUserAccount(UserAccount userAccount) {
        if (userAccount == null) {
            log.info("Can not to update an userAccount: {}", userAccount);
            return null;
        }
        UserAccountDb userAccountDb = mapUserAccountToUserAccountDb(userAccount);
        this.dbUserAccountRepository.save(userAccountDb);
        return userAccount;
    }

    @Override
    public UserAccount createUserAccount(UserAccount userAccount) {
        if (userAccount == null) {
            log.info("Can not to create an user account: {}", userAccount);
            return null;
        }
        UserAccountDb userAccountDb = mapUserAccountToUserAccountDb(userAccount);
        userAccountDb.setId(null);
        this.dbUserAccountRepository.save(userAccountDb);
        return userAccount;
    }

    private UserAccountDb mapUserAccountToUserAccountDb(UserAccount userAccount) {
        return new UserAccountDb(userAccount.getId(), userAccount.getUserId(), userAccount.getUserAmount());
    }

    private UserAccount mapUserAccountDbToUserAccount(UserAccountDb userAccountDb) {
        return new UserAccountImpl(userAccountDb.getId(), userAccountDb.getUserId(), userAccountDb.getAmount());
    }
}
