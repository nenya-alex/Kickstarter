package ua.nenya.mapper;

import org.mapstruct.Mapper;
import ua.nenya.domain.Quote;
import ua.nenya.dto.QuoteDTO;

@Mapper(componentModel = "spring", uses = {})
public interface QuoteMapper {

    QuoteDTO quoteToQuoteDTO(Quote quote);
}
