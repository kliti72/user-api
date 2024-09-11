package net.bcsoft.bcosft.repository;

import net.bcsoft.bcosft.entity.Role;
import net.bcsoft.bcosft.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
