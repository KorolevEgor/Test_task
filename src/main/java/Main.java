import stringReverse.StringReverse;
import stringReverse.StringReverseByteArray;
import stringReverse.StringReverseStringBuffer;


public class Main {

    public static void main(String[] args) {
        StringReverse sr1 = new StringReverseStringBuffer();
        StringReverse sr2 = new StringReverseByteArray();
        testPerformance(sr1);
        testPerformance(sr2);
    }

    private static void testPerformance(StringReverse sr) {
        final double toMillisecond = 1_000_000.0;

        System.out.println(
                "Время (в мс) на выполнение 1_000, 10_000, 100_000 запросов (для " + sr.getClass().getName() + "):");
        System.out.println(timeExecution(sr, 1_000) / toMillisecond);
        System.out.println(timeExecution(sr, 10_000) / toMillisecond);
        System.out.println(timeExecution(sr, 100_000) / toMillisecond);
    }

    private static long timeExecution(StringReverse sr, int iters) {
        String testString = "qwerty";
        long startTime = System.nanoTime();
        for (int i = 0; i < iters; i++) {
            sr.reverse(testString);
        }
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }
}
