package ua.nenya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nenya.domain.Quote;

public interface QuoteRepository extends JpaRepository<Quote,Long> {
}
