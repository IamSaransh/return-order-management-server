package me.saransh13.repository;

import me.saransh13.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request,Long> {
    Optional<Request>  findById(long requestId);
}