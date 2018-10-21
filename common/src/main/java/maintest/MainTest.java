package maintest;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainTest
{

    public static void main(String[] args)
    {

      TestA testA = new TestA("ttt", 12);

        ArrayList lst = new ArrayList();
        lst.add(testA);
        TestA tmpT = (TestA) lst.get(0);
        tmpT.name = "bb";
        String  aa = lst.get(0).toString();


       int ret = Double.compare(0, 0D);

       LocalDateTime t1 = LocalDateTime.now();

       LocalDateTime t2 = t1.plusSeconds(50);


        OffsetDateTime odt = OffsetDateTime.now();
        ZoneOffset zoneOffset = odt.getOffset ();


        Duration duration = Duration.between(t1,t2);
        long tt = duration.toMillis();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyMMdd");

        LocalDate tmpDate = LocalDate.parse("20171107", dateTimeFormatter);


        int a = 1;

    }


    private static class TestA
    {
        public String name;
        public int age;

        public TestA(String name, int age)
        {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString()
        {
            return name + ": " + age;
        }
    }




}
