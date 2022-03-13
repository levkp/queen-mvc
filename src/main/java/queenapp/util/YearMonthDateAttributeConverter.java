package queenapp.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.YearMonth;

@Converter
public class YearMonthDateAttributeConverter implements AttributeConverter<YearMonth, Date> {

    @Override
    public Date convertToDatabaseColumn(YearMonth ym) {
        if (ym != null) {
            return Date.valueOf(ym.atDay(1));
        }
        return  null;
    }

    @Override
    public YearMonth convertToEntityAttribute(Date d) {
        if (d != null) {
            return YearMonth.from(d.toLocalDate());
        }
        return null;
    }
}
