package by.it_academy.jd2.UserService.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class ControllerConfig {

    @Bean
    public ObjectMapper objectMapper(){
         ObjectMapper mapper = new ObjectMapper();
         mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
         mapper.registerModule(new JavaTimeModule());

         return mapper;
    }

    @Bean
    public MappingJackson2HttpMessageConverter jsonHttpMessageConverter(ObjectMapper mapper){
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

        messageConverter.setObjectMapper(mapper);

        return messageConverter;

    }

}
