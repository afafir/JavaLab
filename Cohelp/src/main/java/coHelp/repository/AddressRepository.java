package coHelp.repository;

import coHelp.model.Address;
import coHelp.model.user.User;

import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, Long>  {
    Optional<Address> findByUser(User user);
}
