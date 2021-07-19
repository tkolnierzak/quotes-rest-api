package pl.decerto.tkolnierzak.restapiquotes.quote.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.decerto.tkolnierzak.restapiquotes.quote.service.QuoteService;

@RestController
@RequestMapping
public class QuoteRestApiController {
    private QuoteService quoteService;

    public QuoteRestApiController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }
}
