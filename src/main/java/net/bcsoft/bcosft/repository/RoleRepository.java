package net.bcsoft.bcosft.repository;

import net.bcsoft.bcosft.entity.Role;
import net.bcsoft.bcosft.entity.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


}
