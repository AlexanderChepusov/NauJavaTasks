package Task5;

public class Main {
    static int time = 2;
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        Task5 task = new Task5();

        Thread t2 = new Thread(() -> {
            task.start();
        });

        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(time * 1000);
                task.stop();
                t2.interrupt();

                System.out.println("Завершено через " + ((System.nanoTime() - startTime) / 1_000_000_000) + " c");

            } catch (InterruptedException e) {
                System.out.println("Interrupted");
                Thread.currentThread().interrupt();
            }
        });

        //t2.start();
        //t1.start();

        Task5_2 t3 = new Task5_2(7);
        Thread t31 = new Thread(() -> {
            t3.start();
        });

        Thread t32 = new Thread(() -> {
            try {
                Thread.sleep(4578);
                t3.stop();
            } catch (InterruptedException e) {
                //System.out.println();
            }

        });


        t31.start();
        t32.start();


        /*
        Thread thread = new Thread(() -> {
    System.out.println("Поток работает: " + Thread.currentThread().getName());
});
thread.start();
         */
    }
}
