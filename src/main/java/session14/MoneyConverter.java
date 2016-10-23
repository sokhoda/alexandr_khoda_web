package session14;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by s_okhoda on 14.02.2016.
 */
@Converter
public class MoneyConverter implements AttributeConverter<Double, String>{
    @Override
    public String convertToDatabaseColumn(Double balance) {
        return Double.toString(balance);
    }

    @Override
    public Double convertToEntityAttribute(String balance) {
        return Double.parseDouble(balance);
    }
}
