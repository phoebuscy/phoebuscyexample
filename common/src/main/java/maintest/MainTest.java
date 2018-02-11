package maintest;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import static com.utils.SUtil.isIntNumeric;

public class MainTest
{

    public static void main(String[] args)
    {

       int ret = Double.compare(0, 0D);

       LocalDateTime t1 = LocalDateTime.now();

       LocalDateTime t2 = t1.plusSeconds(50);



        Duration duration = Duration.between(t1,t2);
        long tt = duration.toMillis();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyMMdd");

        LocalDate tmpDate = LocalDate.parse("20171107", dateTimeFormatter);


        int a = 1;

    }




}
