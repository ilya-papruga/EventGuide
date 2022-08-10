package by.it_academy.jd2.EventFilmService.service.converters;

import by.it_academy.jd2.EventFilmService.core.dto.flim.FilmRead;
import by.it_academy.jd2.EventFilmService.core.dto.page.PageRead;
import by.it_academy.jd2.EventFilmService.core.entity.Film;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PageToPageReadConverter implements Converter<Page<Film>, PageRead<FilmRead>> {
    private final FilmToFilmReadConverter filmToFilmReadConverter;

    public PageToPageReadConverter(FilmToFilmReadConverter filmToFilmReadConverter) {
        this.filmToFilmReadConverter = filmToFilmReadConverter;
    }

    @Override
    public PageRead<FilmRead> convert(Page<Film> page) {

        PageRead<FilmRead> dtoPage = new PageRead<>();

        List<FilmRead> dtoContent = new ArrayList<>();

        for (Film entity : page.getContent()) {
            dtoContent.add(filmToFilmReadConverter.convert(entity));
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
