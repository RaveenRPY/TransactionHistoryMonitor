package dev.danvega.repository;

import dev.danvega.domain.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repository interface for managing Detail entities
public interface DetailRepository extends JpaRepository<Detail, Long> {
}
