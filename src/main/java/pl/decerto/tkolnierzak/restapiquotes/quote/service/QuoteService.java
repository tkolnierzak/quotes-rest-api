package pl.decerto.tkolnierzak.restapiquotes.quote.service;

import org.springframework.stereotype.Service;
import pl.decerto.tkolnierzak.restapiquotes.quote.entity.Quote;
import pl.decerto.tkolnierzak.restapiquotes.quote.repository.QuoteRepository;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class QuoteService {
    private QuoteRepository quoteRepository;

    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public Stream<Quote> findQuotesByAuthor(String author) {
        return quoteRepository.findByAuthor(author).stream();
    }

    public void replaceQuote(long id, Quote newQuote) {
        newQuote.setId(id);
        quoteRepository.save(newQuote);
    }

    public void updateQuote(long id, Quote newPartialQuote) {
        findQuoteById(id).ifPresent(quote -> {
            if (newPartialQuote.getAuthor() != null) {
                quote.setAuthor(
                        newPartialQuote.getAuthor());
            }
            if (newPartialQuote.getContents() != null) {
                quote.setContents(newPartialQuote.getContents());
            }
            quoteRepository.save(quote);
        });
    }

    public void addContent(long id, String content) {
        findQuoteById(id).ifPresent(quote -> {
            quote.getContents().add(content);
            quoteRepository.save(quote);
        });
    }

    public Stream<Quote> getQuotes() {
        return quoteRepository.findAll().stream();
    }

    public Optional<String> getAuthorOfContents(long id) {
        return findQuoteById(id).map(Quote::getAuthor);
    }

    public Optional<Quote> getQuote(long id) {
        return findQuoteById(id);
    }

    public Quote addQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    public void deleteQuote(long id) {
        quoteRepository.deleteById(id);
    }

    private Optional<Quote> findQuoteById(long id) {
        return quoteRepository.findById(id);
    }
}
