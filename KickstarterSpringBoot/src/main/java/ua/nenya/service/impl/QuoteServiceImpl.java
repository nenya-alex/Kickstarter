package ua.nenya.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nenya.domain.Quote;
import ua.nenya.dto.QuoteDTO;
import ua.nenya.exceptions.QuoteException;
import ua.nenya.mapper.QuoteMapper;
import ua.nenya.repository.QuoteRepository;
import ua.nenya.service.QuoteService;

import java.util.List;
import java.util.Random;

@Service
@Transactional
public class QuoteServiceImpl implements QuoteService{

    private static final Logger log = LoggerFactory.getLogger(QuoteServiceImpl.class);

    private final QuoteRepository quoteRepository;

    private final QuoteMapper quoteMapper;

    public QuoteServiceImpl(QuoteRepository quoteRepository,
                            QuoteMapper quoteMapper) {
        this.quoteRepository = quoteRepository;
        this.quoteMapper = quoteMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public QuoteDTO getRandomQuote() {
        log.debug("Request to get all Quotes");
        List<Quote> quotes = quoteRepository.findAll();
        return quoteMapper.quoteToQuoteDTO(getSingleRandomResult(quotes));
    }

    private Quote getSingleRandomResult(List<Quote> quotes) {
        Quote quote = quotes.get(new Random().nextInt(quotes.size()));
        if (quote == null){
            throw new QuoteException("Quote entity does not exist");
        }
        return quote;
    }

}
