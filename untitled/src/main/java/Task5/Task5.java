package Task5;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/*
5. Реализуйте интерфейс “Task” для сканирования сети на открытые
порты  в  заданном  диапазоне.  При  нахождении  открытого  порта,
информация  о  нем  печатается  в  консоль.  Метод  start()  начинает
сканирование,  а  stop()  прерывает  его.  Для  выполнения  задания
рекомендуется использовать класс “java.net.Socket”.

 */
public class Task5 implements Task {

    private int startPort = 1;
    private int endPort = 1024;
    private int satrtIp = 1;
    private int endIp = 255;
    static boolean isPermitted; ///= true;
    int timeoutMs = 5; //1_000; //_000;
    @Override
    public void start() {
        isPermitted = true;


        for (int i = satrtIp; i < endIp + 1; i++) {
            String ip = "195.19.129." + i;
            for (int port = startPort; port < endPort + 1; port++) {
                try (Socket socket = new Socket()) {
                    if (isPermitted) {

                        socket.connect(new InetSocketAddress(ip, port), timeoutMs);
                    } else {
                        return;
                    }
                    System.out.println("IP 195.19.129." + i + " has open port " + port);
//                } catch (InterruptedException x) {
//                    System.out.println("Прервано...");
                } catch (IOException e) {
                    //System.out.println(e.getMessage());
                    //e.printStackTrace();
                    //continue;
                    //System.out.println(ip + "--" + port);
                }
            }
            System.out.println(ip + " scan finished.");
        }



    }

    @Override
    public void stop() {
        isPermitted = false;
    }


}
