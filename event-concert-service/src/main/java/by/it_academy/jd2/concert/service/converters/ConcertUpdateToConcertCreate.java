package by.it_academy.jd2.concert.service.converters;

import by.it_academy.jd2.concert.core.dto.concert.ConcertUpdate;
import by.it_academy.jd2.concert.core.entity.Concert;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConcertUpdateToConcertCreate implements Converter <ConcertUpdate, Concert> {

    @Override
    public Concert convert(ConcertUpdate dto) {

        Concert concert = new Concert();

        concert.setTitle(dto.getTitle());
        concert.setDescription(dto.getDescription());
        concert.setDtEvent(dto.getDtEvent());
        concert.setDtEndOfSale(dto.getDtEndOfSale());
        concert.setType("concert");
        concert.setEventStatus(dto.getEventStatus());
        concert.setCategory(dto.getCategory());

        return concert;


    }
}
