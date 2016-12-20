package ru.click.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.springframework.util.Assert.notNull;

/**
 * Реализация {@link UserDetailsService}
 * <p>
 * Создан 21.06.2016
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public class UserDao<U extends UserDetails> implements UserDetailsService {

    /**
     * Репозитрий для операций с юзерами
     */
    private final CrudRepository<U, String> repo;

    /**
     * Констурктор для внедрения зависимостей
     * @param repo репозиторий юзеров
     */
    public UserDao(CrudRepository<U, String> repo) {
        notNull(repo);
        this.repo = repo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public U loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return repo.findOne(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException(e.getMessage(), e);
        }
    }
}
