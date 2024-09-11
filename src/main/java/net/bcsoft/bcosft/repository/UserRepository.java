package net.bcsoft.bcosft.repository;

import net.bcsoft.bcosft.entity.Role;
import net.bcsoft.bcosft.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long>
{
    public Role getRole(Optional<Users> user);
}


