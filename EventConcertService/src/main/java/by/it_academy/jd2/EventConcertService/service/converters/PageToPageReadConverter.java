package by.it_academy.jd2.EventConcertService.service.converters;

import by.it_academy.jd2.EventConcertService.core.dto.concert.ConcertRead;
import by.it_academy.jd2.EventConcertService.core.dto.page.PageRead;
import by.it_academy.jd2.EventConcertService.core.entity.Concert;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PageToPageReadConverter implements Converter<Page<Concert>, PageRead<ConcertRead>> {

    private final ConcertToConcertReadConverter concertToConcertReadConverter;

    public PageToPageReadConverter(ConcertToConcertReadConverter concertToConcertReadConverter) {
        this.concertToConcertReadConverter = concertToConcertReadConverter;
    }

    @Override
    public PageRead<ConcertRead> convert(Page<Concert> page) {

        PageRead<ConcertRead> dtoPage = new PageRead<>();

        List<ConcertRead> dtoContent = new ArrayList<>();

        for (Concert entity : page.getContent()) {
            dtoContent.add(concertToConcertReadConverter.convert(entity));
        }

        dtoPage.setNumber(page.getNumber()+1);
        dtoPage.setSize(page.getSize());
        dtoPage.setTotalPages(page.getTotalPages());
        dtoPage.setTotalElements(page.getTotalElements());
        dtoPage.setFirst(page.isFirst());
        dtoPage.setNumberOfElements(page.getNumberOfElements());
        dtoPage.setLast(page.isLast());
        dtoPage.setContent(dtoContent);

        return dtoPage;
    }
}
