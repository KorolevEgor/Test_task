import stringReverse.StringReverse;
import stringReverse.StringReverseByteArray;
import stringReverse.StringReverseStringBuffer;

import java.io.*;


public class Main {

    public static void main(String[] args) {
        StringReverse sr1 = new StringReverseStringBuffer();
        StringReverse sr2 = new StringReverseByteArray();
        testPerformance(sr1);
        testPerformance(sr2);

        createDataForBigOPlot(sr1);
    }

    private static void createDataForBigOPlot(StringReverse sr) {
        File f = new File("dataForBigOPlot.py");
        try (FileWriter fw = new FileWriter(f);
             BufferedWriter bw = new BufferedWriter(fw);) {
            final int maxLength = 500;
            f.createNewFile();
            bw.write("data = [");
            for (int i = 0; i < maxLength; i++) {
                String testLine = genString(i);

                long startTime = System.nanoTime();
                sr.reverse(testLine);
                long finishTime = System.nanoTime();

                // приведение размерности к микросекундам
                Double time = (finishTime - startTime) / 1_000.0;

                bw.write(time.toString());

                if (i + 1 != maxLength) {
                    bw.write(", ");
                }
            }
            bw.write("]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String genString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append("*");
        }
        return sb.toString();
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
