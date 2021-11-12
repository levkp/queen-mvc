package prog21assignment.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import prog21assignment.util.StringToLocalDate;
import prog21assignment.util.StringToYearMonth;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToLocalDate());
        registry.addConverter(new StringToYearMonth());
    }
}
