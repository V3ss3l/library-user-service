package com.library.libraryuserservice.repos;

import com.library.libraryuserservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.username = ?1 order by u.id asc")
    User findByUsername(String username);
}
