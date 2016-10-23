package springnotes.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 14.02.16
 */
@Converter
public class MoneyConverter implements AttributeConverter<Long, String> {
    @Override
    public String convertToDatabaseColumn(Long aLong) {
        return String.valueOf(aLong);
    }

    @Override
    public Long convertToEntityAttribute(String str) {
        return Long.parseLong(str.substring(0, str.indexOf(".")));
    }
}
