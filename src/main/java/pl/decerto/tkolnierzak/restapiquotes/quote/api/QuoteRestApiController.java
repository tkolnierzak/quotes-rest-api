package pl.decerto.tkolnierzak.restapiquotes.quote.api;

import org.springframework.hateoas.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.decerto.tkolnierzak.restapiquotes.quote.entity.Quote;
import pl.decerto.tkolnierzak.restapiquotes.quote.service.QuoteService;

import java.net.URI;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/quotes", produces = {"application/json"})
public class QuoteRestApiController {
    private static final String REL_ALL_QUOTES = "allQuotes";
    private static final String REL_QUOTES_BY_TITLE = "quotesByAuthor";
    private static final String REL_SELF = "self";
    private QuoteService quoteService;

    public QuoteRestApiController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource<Quote>> getQuote(
            @PathVariable long id) {
        return quoteService.getQuote(id).map(this::resource)
                .map(this::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Resources<Resource<Quote>> getQuotes() {
        Resources<Resource<Quote>> resources = new Resources<>(
                quoteService.getQuotes().map(this::resource)
                        .collect(Collectors.toList()));
        addDocsLink(resources, REL_SELF);
        addFindDocsLink(resources, REL_QUOTES_BY_TITLE, null);
        return resources;
    }

    @DeleteMapping("/{id}")
    public void deleteAllAuthorsQuotes(@PathVariable("id") long id) {
        quoteService.deleteQuote(id);
    }

    @PatchMapping("/{id}")
    public void updateQuote(@PathVariable long id,
                               @RequestBody Quote newPartialQuote) {
        quoteService.updateQuote(id, newPartialQuote);
    }

    @PutMapping("/{id}")
    public void replaceQuote(@PathVariable long id,
                                @RequestBody Quote newQuote) {
        quoteService.replaceQuote(id, newQuote);
    }

    @GetMapping(params = "author")
    public Resources<Resource<Quote>> findQuoteByAuthor(
            @RequestParam("author") String author) {
        Resources<Resource<Quote>> resources = new Resources<>(
                quoteService.findQuotesByAuthor(author)
                        .map(this::resource)
                        .collect(Collectors.toList()));
        addFindDocsLink(resources, REL_SELF, author);
        addDocsLink(resources, REL_ALL_QUOTES);
        return resources;
    }

    @PostMapping
    public ResponseEntity<?> addQuote(@RequestBody Quote quote) {
        Quote addedQuote = quoteService.addQuote(quote);
        return ResponseEntity.created(URI.create(
                resource(addedQuote).getLink(REL_SELF).getHref()))
                .build();
    }

    @PostMapping(value = "/{quoteId}/quotes", consumes =
            MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addQuote(@PathVariable long quoteId,
                       @RequestBody String content) {
        quoteService.addContent(quoteId, content);
    }

    private void addFindDocsLink(Resources<Resource<Quote>> resources,
                                 String rel, String title) {
        resources.add(linkTo(methodOn(QuoteRestApiController.class)
                .findQuoteByAuthor(title)).withRel(rel));
    }

    private void addDocsLink(Resources<Resource<Quote>> resources,
                             String rel) {
        resources.add(linkTo(QuoteRestApiController.class)
                .withRel(rel));
    }

    private Resource<Quote> resource(Quote quote) {
        Resource<Quote> quoteResource = new Resource<>(quote);
        quoteResource.add(linkTo(
                methodOn(QuoteRestApiController.class)
                        .getQuote(quote.getId()))
                .withSelfRel());
        return quoteResource;
    }

    private ResponseEntity<String> notFound() {
        return ResponseEntity.notFound().build();
    }

    private <T> ResponseEntity<T> ok(T body) {
        return ResponseEntity.ok().body(body);
    }
}
