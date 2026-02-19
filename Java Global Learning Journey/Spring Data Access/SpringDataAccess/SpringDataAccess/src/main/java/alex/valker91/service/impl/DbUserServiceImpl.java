package alex.valker91.service.impl;

import alex.valker91.entity.UserDb;
import alex.valker91.model.User;
import alex.valker91.model.impl.UserImpl;
import alex.valker91.repository.DbUserRepository;
import alex.valker91.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DbUserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(DbUserServiceImpl.class);

    @Autowired
    private DbUserRepository dbUserRepository;

    @Override
    public User getUserById(long userId) {
        log.info("Finding an user by id: {}", userId);
        Optional<UserDb> userDbOptional = this.dbUserRepository.findById(userId);
        if (userDbOptional.isPresent()) {
            User user = mapUserDbToUser(userDbOptional.get());
            log.info("User with id {} successfully found ", userId);
            return user;
        } else {
            log.info("Can not to find an user by id: " + userId);
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<UserDb> userDbOptional = this.dbUserRepository.findByEmail(email);
        if (userDbOptional.isPresent()) {
            User user = mapUserDbToUser(userDbOptional.get());
            log.info("User with email {} successfully found ", email);
            return user;
        } else {
            log.info("Can not to find an user by email: " + email);
            return null;
        }
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<UserDb> usersPage = dbUserRepository.findAllByName(name, pageable);
        return usersPage.getContent()
                .stream()
                .map(userDb -> new UserImpl(
                        userDb.getId(),
                        userDb.getName(),
                        userDb.getEmail()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public User createUser(User user) {
        if (user == null) {
            log.info("Can not to create an user: {}", user);
            return null;
        }
        UserDb userDb = mapUserToUserDb(user);
        userDb.setId(null);
        this.dbUserRepository.save(userDb);
        return user;
    }

    @Override
    public User updateUser(User user) {
        if (user == null) {
            log.info("Can not to update an user: {}", user);
            return null;
        }
        UserDb userDb = mapUserToUserDb(user);
        this.dbUserRepository.save(userDb);
        return user;
    }

    @Override
    public boolean deleteUser(long userId) {
        Optional<UserDb> userDbOptional = this.dbUserRepository.findById(userId);
        if (userDbOptional.isPresent()) {
            this.dbUserRepository.deleteById(userId);
            log.info("Successfully deletion of the user with id: {}", userId);
            return true;
        } else {
            log.info("Can not to delete an user with id: {}", userId);
            return false;
        }
    }

    private User mapUserDbToUser(UserDb userDb) {
        return new UserImpl(userDb.getId(), userDb.getName(), userDb.getEmail());
    }

    private UserDb mapUserToUserDb(User user) {
        return new UserDb(user.getId(), user.getName(), user.getEmail());
    }
}
