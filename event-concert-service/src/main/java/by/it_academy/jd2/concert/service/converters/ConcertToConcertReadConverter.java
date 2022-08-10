package by.it_academy.jd2.concert.service.converters;

import by.it_academy.jd2.concert.core.dto.concert.ConcertRead;
import by.it_academy.jd2.concert.core.entity.Concert;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConcertToConcertReadConverter implements Converter<Concert, ConcertRead> {

    @Override
    public ConcertRead convert(Concert entity) {

        ConcertRead dto = new ConcertRead();

        dto.setUuid(entity.getUuid());
        dto.setDtCreate(entity.getDtCreate());
        dto.setDtUpdate(entity.getDtUpdate());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setDtEvent(entity.getDtEvent());
        dto.setDtEndOfSale(entity.getDtEndOfSale());
        dto.setType(entity.getType());
        dto.setEventStatus(entity.getEventStatus());
        dto.setCategory(entity.getCategory());

        return dto;

    }
}

