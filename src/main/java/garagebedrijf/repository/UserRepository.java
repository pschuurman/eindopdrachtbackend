package garagebedrijf.repository;

import garagebedrijf.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
