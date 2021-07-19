package pl.decerto.tkolnierzak.restapiquotes.quote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.decerto.tkolnierzak.restapiquotes.quote.entity.Quote;

import java.util.Collection;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    Collection<Quote> findByAuthor(String author);
}
