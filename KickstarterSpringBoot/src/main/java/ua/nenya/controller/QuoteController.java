package ua.nenya.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nenya.dto.QuoteDTO;
import ua.nenya.service.QuoteService;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class QuoteController {

    private static final Logger log = LoggerFactory.getLogger(QuoteController.class);

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/quote")
    public ResponseEntity<QuoteDTO> getQuote() {
        log.debug("REST request to get random Quote");
        QuoteDTO quoteDTO = quoteService.getRandomQuote();
        return ResponseEntity.ok().body(quoteDTO);
    }

}
