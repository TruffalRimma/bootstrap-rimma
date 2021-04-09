package ru.saray.jm.bootstraprimma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.saray.jm.bootstraprimma.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
