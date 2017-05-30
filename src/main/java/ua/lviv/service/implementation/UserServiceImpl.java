package ua.lviv.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.lviv.entity.User;
import ua.lviv.repositories.UserRepo;
import ua.lviv.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by PC-6 on 25.04.2017.
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService{
    @Autowired
    private UserRepo userRepo;

    public void addUser(String firstName, String lastName, String email, String phone, String login, String password) {
        User user = new User(firstName, lastName, email, phone, login, password);
        userRepo.save(user);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User findByLogin(String login) {
        return userRepo.findByLogin(login);
    }

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepo.findByLogin(login);
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), authorities);
    }
}
