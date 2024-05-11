package ru.mirea.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.platform.entity.MyUser;

import java.util.Optional;



public interface UserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByUsername(String username);
    Boolean existsMyUserByUsername(String username);
}
