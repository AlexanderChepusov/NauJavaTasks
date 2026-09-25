package Tasks3.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Task2 {
    public static void main(String[] args) {
        //Работа со списками
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            List<Double> list = new ArrayList<>();
            int min = Integer.MIN_VALUE / 2;
            int max = Integer.MAX_VALUE / 2;
            for (int i = 0; i < n; i++) {
                double x = Math.random();
                x = min + x * (2 * max); // - min + 1);
                list.add(x);
            }
            System.out.println("Original list:");
            //System.out.println(list);
            System.out.println();

//            List<Double> data = new ArrayList<>(list); //List.copyOf(list);
//            long start = System.nanoTime();
//            //bubbleSort(data);
//            long finish = System.nanoTime();
//
//            //System.out.println(data);
//
//            double duration_ms = (finish - start) / 1_000_000.0;
            //System.out.println("Merge sort lasts " + duration_ms + " ms");
            //System.out.println(Duration.between(finish, start));

            startSorts(list);


        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }
    /*
    1. Сортировка слиянием (Merge Sort).
2. Быстрая сортировка (Quick Sort).
3. Пузырьковая сортировка (Bubble Sort).
4. Сортировка выбором (Selection Sort).
5. Сортировка вставками (Insertion Sort).
     */
    static void mergeSort(List<Double> list) {
        System.out.print("Merge");
    }

    static void bubbleSort(List<Double> list) {
        int n = list.size();
        for (int i = n - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                double a1 = list.get(j);
                double a2 = list.get(j + 1);
                if (a1 > a2) {
                    list.set(j + 1, a1);
                    list.set(j, a2);
                }
            }
        }
        System.out.print("Bubble");
    }

    static void quickSort(List<Double> list) {
        System.out.print("Quick");
    }

    static void selectionSort(List<Double> list) {
        int n = list.size();
        int minI = 0;

        for (int i = 0; i < n; i++) {
            double min = Double.MAX_VALUE;
            for (int j = i; j < n; j++) {
                double x = list.get(j);
                if (x < min) {
                    minI = j;
                    min = x;
                }
            }
            double temp = list.get(i);
            list.set(i, min);
            list.set(minI, temp);
        }
        System.out.print("Selection");
    }

    static void insertionSort(List<Double> list) {
        System.out.print("Insertion");
    }

    static void startSorts(List<Double> list) {
        Consumer<List> merge = Task2::mergeSort;
        Consumer<List> bubble = Task2::bubbleSort;
        Consumer<List> quick = Task2::quickSort;
        Consumer<List> selection = Task2::selectionSort;
        Consumer<List> insertion = Task2::insertionSort;

        List<Consumer<List>> datas = List.of(
                merge,
                bubble,
                quick,
                selection,
                insertion
        );

        for (Consumer<List> x : datas
             ) {
            List<Double> data = new ArrayList<>(list);
            long start = System.nanoTime();
            x.accept(data);
            long finish = System.nanoTime();
            double duration_ms = (finish - start) / 1_000_000.0;
            System.out.println(" sort lasts " + duration_ms + " ms");
            //System.out.println(data);
        }

    }
}
