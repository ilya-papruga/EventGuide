package by.it_academy.jd2.UserService.service.converters;

import by.it_academy.jd2.UserService.core.dto.admin.UserRead;
import by.it_academy.jd2.UserService.core.dto.page.PageRead;
import by.it_academy.jd2.UserService.core.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PageToPageReadConverter implements Converter<Page<User>, PageRead<UserRead>> {

    private final UserToUserReadConverter converter;

    public PageToPageReadConverter(UserToUserReadConverter converter) {
        this.converter = converter;
    }

    @Override
    public PageRead<UserRead> convert(Page<User> page) {

        PageRead<UserRead> dtoPage = new PageRead<>();

        List<UserRead> dtoContent = new ArrayList<>();

        for (User user : page.getContent()) {
            dtoContent.add(converter.convert(user));
        }
        dtoPage.setNumber(page.getNumber());
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
