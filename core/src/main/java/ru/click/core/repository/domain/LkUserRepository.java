package ru.click.core.repository.domain;

import org.springframework.data.repository.CrudRepository;
import ru.click.core.model.LkUser;

public interface LkUserRepository extends CrudRepository<LkUser, String> {
}
