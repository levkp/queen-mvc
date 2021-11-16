package prog21assignment.util;

import org.springframework.core.convert.converter.Converter;

import java.time.YearMonth;

public class StringToYearMonth implements Converter<String, YearMonth> {

    @Override
    public YearMonth convert(String source) {
        return YearMonth.parse(source);
    }
}
