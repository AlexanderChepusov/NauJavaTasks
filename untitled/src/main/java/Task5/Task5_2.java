package Task5;

public class Task5_2 implements Task {
    private int startTime;
    private boolean isStopped = false;

    public Task5_2() {
        startTime = 10;
    }

    public Task5_2(int startTime) {
        this.startTime = startTime;
    }

    @Override
    public void start() {
        long st = System.nanoTime();
        isStopped = false;
        while ((!isStopped) && startTime > -1) {
            System.out.print("Timer: " + (startTime--) + "\r");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted.");
            }
        }
        System.out.println();
        System.out.println(st - System.nanoTime() / 1_000_000_000.0 + " s is left.");

    }

    @Override
    public void stop() {
        //isStopped = true;
        Thread.currentThread().interrupt();
    }
}
