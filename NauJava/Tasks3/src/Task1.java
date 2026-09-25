package Tasks3.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task1 {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            int[] arr = new int[n];
            int max = 0;
            int min = Integer.MAX_VALUE;
            long summ = 0l;
            long posSumm = 0l;
            int last = 0;
            for (int i = 0; i < n; i++) {
                double k = Math.random();
                long d = 2 * ((long)Integer.MAX_VALUE - 1); //- Integer.MIN_VALUE + 1;
                double dk = k * d;
                arr[i] = (int)((long)Integer.MIN_VALUE + dk); //(int) (Math.random() * (Integer.MAX_VALUE - Integer.MIN_VALUE));
                //System.out.println(arr[i] + ": " + k + " * " + d);
                int a = Math.abs(arr[i]);
                if (a > max) {
                    max = a;
                } else if (a < min) {
                    min = a;
                }
                summ += arr[i];
                if (arr[i] > 0) {
                    posSumm += arr[i];
                    if (arr[i] > last) {
                        last = arr[i];
                    }
                }
            }
            //System.out.println();
            System.out.println("Max ABS = " + max);
            System.out.println("Min ABS = " + min);
            System.out.println("Average = " + summ / n);
            System.out.println("Summ of positives = " + posSumm);
            System.out.println(last > 0 ? "Last = " + last : "No posistive elements");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
