package dev.danvega.repository;

import dev.danvega.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository interface for managing User entities
public interface UserRepository extends JpaRepository<User, Long> {
}
