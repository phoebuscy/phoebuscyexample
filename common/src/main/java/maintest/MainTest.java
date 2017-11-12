package maintest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import static com.utils.SUtil.isIntNumeric;

public class MainTest
{

    public static void main(String[] args)
    {

        ConcurrentLinkedQueue cq = new ConcurrentLinkedQueue();

        cq.notifyAll();

        List<String> strLst = new ArrayList<>();

        strLst.notifyAll();


        int a = 1;

    }




}
