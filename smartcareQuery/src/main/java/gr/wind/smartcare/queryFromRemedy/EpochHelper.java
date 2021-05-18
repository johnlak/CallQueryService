package gr.wind.smartcare.queryFromRemedy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class EpochHelper
{
    public static long epochDayToday()
    {
        LocalDate now = LocalDate.now();
        LocalDate epoch = LocalDate.ofEpochDay(0);
        return ChronoUnit.DAYS.between(epoch, now);
    }

    public static long epochDay(LocalDateTime localDateTime)
    {
        LocalDate epoch = LocalDate.ofEpochDay(0);
        return ChronoUnit.DAYS.between(epoch, localDateTime);
    }

    public static long getEpochSeconds(LocalDateTime time)
    {
        ZoneId zoneId = ZoneId.systemDefault(); // or: ZoneId.of("Europe/Oslo");
        long epoch = time.atZone(zoneId).toEpochSecond();
        return epoch;
    }

    public static LocalDateTime getDateTime(String dateTimeStr)
    {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /*
    // Identify Dates and Transform Unix Epoch Dates.
    // Transform when they don't contain slashes or dashes
    String dateContentToString(String textDate)
    {
      String cleanDate = ""
      if (textDate != null)
      {
        if (textDate != "" && textDate != "null" && !textDate.contains("/") && !textDate.contains("-"))
        {
          try
          {
            Long epochDate = Long.parseLong(textDate)
            Date dateObj =  new Date( ((long)epochDate) )
            cleanDate = new SimpleDateFormat('yyyy-MM-dd').format(dateObj)
          }
          catch(Exception e)
          {
            throw new Exception("DATE ERROR: value:${textDate}, error:${e.toString()}")
          }
        }
        else
        {
          cleanDate = textDate
        }
      }
      return cleanDate
    }

    // Identify Dates and Transform Unix Epoch Dates.
    // Transform when they don't contain slashes or dashes
    String datetimeContentToString(String textDate)
    {
      String cleanDate = ""
      if (textDate != null)
      {
        if (textDate != "" && textDate != "null" && !textDate.contains("/") && !textDate.contains("-"))
        {
          try
          {
            Long epochDate = Long.parseLong(textDate)
            Date dateObj =  new Date( ((long)epochDate) )
            cleanDate = new SimpleDateFormat('yyyy-MM-dd HH:mm:ss').format(dateObj)
          }
          catch(Exception e)
          {
            throw new Exception("DATE ERROR: value:${textDate}, error:${e.toString()}")
          }
        }
        else
        {
          cleanDate = textDate
        }
      }
      return cleanDate
    }
     */



}
