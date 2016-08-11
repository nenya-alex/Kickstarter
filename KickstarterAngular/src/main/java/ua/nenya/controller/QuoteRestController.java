package ua.nenya.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.nenya.dao.QuoteDao;
import ua.nenya.domain.Quote;

@RestController
public class QuoteRestController {
	
	@Autowired
	private QuoteDao quoteDao;
	
	@RequestMapping(value = "/quote", method = RequestMethod.GET)
	public Quote getQuote() {
		return quoteDao.getRandomQuote();
	}

}
