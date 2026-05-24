package ru.khalov.tests.emailnotificationservice.persistence.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khalov.tests.emailnotificationservice.persistence.entity.ProcessedEventEntity;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProcessedEventEntity, Long> {
    ProcessedEventEntity findByMessageId(String messageId);
}
