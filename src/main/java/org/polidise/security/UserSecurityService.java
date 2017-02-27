package org.polidise.security;

import org.polidise.domain.User;
import org.polidise.persistence.entity.UserEntity;
import org.polidise.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Implements a UserDetailService for the Security Subsystem.
 * <p>
 * You can get the UserDetails-object by giving the user-name.
 * The data is retrieved from the EmployeeService
 * </p>
 */
@Service
public class UserSecurityService implements UserDetailsService {

    private UserRepository repo;

    @Autowired
    public UserSecurityService(UserRepository repo) {
        this.repo = repo;
    }

    /**
     * This fetches the login-data from behind the security system,
     * as this will deny all accesses when no user is logged in.
     *
     * @return a list with all employees.
     */
    private List<User> getAllLogins() {
        List<User> result = new ArrayList<>();
        Iterable<UserEntity> all = repo.findAll();
        for (UserEntity entity : all) {
            result.add(new User(entity.getUsername(), entity.getPasswordHash(), entity.getActive()));
        }
        return result;
    }

    public List<User> getLogin( Predicate<User> filter) {
        return getAllLogins().parallelStream().filter(filter).collect(Collectors.toList());
    }


    /**
     * Searches for a user by its name.
     * <p>
     * The search is case-insensitive
     * </p>
     * @param s the username to search for
     * @return the object describing the user.
     * @throws UsernameNotFoundException thrown when no user was found.
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<User> users = getLogin(u -> u.getUsername().equalsIgnoreCase(s));
        if (users.size() != 1) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User " + s + " was not found!");
        }
        User user = users.get(0);
        System.out.println("New Login: User: " + user.getUsername());
        return user;
    }
}
