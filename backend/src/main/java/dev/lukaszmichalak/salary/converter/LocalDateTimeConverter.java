package dev.lukaszmichalak.salary.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Converter()
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, String> {

  private static final DateTimeFormatter formatter =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  @Override
  public String convertToDatabaseColumn(LocalDateTime localDateTime) {
    return (localDateTime != null) ? localDateTime.format(formatter) : null;
  }

  @Override
  public LocalDateTime convertToEntityAttribute(String dateTimeString) {
    return (dateTimeString != null) ? LocalDateTime.parse(dateTimeString, formatter) : null;
  }
}
