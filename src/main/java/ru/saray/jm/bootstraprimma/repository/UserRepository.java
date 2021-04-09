package ru.saray.jm.bootstraprimma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.saray.jm.bootstraprimma.model.User;

/*
Попробовала реализовать DAO, используя Spring Data JPA
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User loadUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User loadUserByUsername(@Param("username") String username);
}
