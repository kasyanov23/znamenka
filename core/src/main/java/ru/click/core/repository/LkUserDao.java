package ru.click.core.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.click.core.repository.domain.LkUserRepository;

public class LkUserDao implements UserDetailsService {

    private final LkUserRepository repository;

    @Autowired
    public LkUserDao(LkUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findOne(username);
    }
}
