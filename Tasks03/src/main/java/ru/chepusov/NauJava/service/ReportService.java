package ru.chepusov.NauJava.service;

import org.springframework.stereotype.Service;
import ru.chepusov.NauJava.entities.Operation;
import ru.chepusov.NauJava.entities.Report;
import ru.chepusov.NauJava.entities.Status;
import ru.chepusov.NauJava.repository.OperationRepository;
import ru.chepusov.NauJava.repository.ReportRepository;
import ru.chepusov.NauJava.repository.UserRepository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ReportService {
    private final ReportRepository reportRep;
    private final OperationRepository opRep;
    private final UserRepository userRep;

    public ReportService(ReportRepository reportRep, OperationRepository opRep, UserRepository userRep) {
        this.reportRep = reportRep;
        this.opRep = opRep;
        this.userRep = userRep;
    }

    public String getReportContent(long id) {
        long startTime = System.currentTimeMillis();
        Report report = reportRep.findById(id)
                .orElseThrow(() -> new RuntimeException("Отчет с ID " + id + " не найден"));
        String result = report.getContent();
        System.out.println(System.currentTimeMillis() - startTime);
        return result;
    }

    public Map.Entry<Long, List<Operation>> createReport() {
        /*
        1.  Отчет должен быть человекочитаемым (рекомендуется использовать
        табличное представление) в формате HTML-страницы.
        2.  Отчет должен содержать количество  зарегистрированных в системе
        пользователей.
        3.  Отчет  должен  содержать  список  объектов  любой  вами  выбранной
        сущности исходя из вашей темы проекта.
        4.  Отчет  должен  содержать  время,  затраченное  на  вычисление  2
        пункта.
        5.  Отчет  должен  содержать  время,  затраченное  на  вычисление  3
        пункта.
        6.  Отчет должен содержать общее время, затраченное на формирование
        отчета.
         */
        long startTime = System.currentTimeMillis();
        Report result = new Report();
        result.setStatus(Status.CREATED);

        String content = "";
        AtomicInteger usersCount = new AtomicInteger(); //= -1;
        AtomicLong time1 = new AtomicLong();
        AtomicLong time2 = new AtomicLong();

        List<Operation>  operations = new ArrayList<>();

        long operationsCount = -1l;

        CompletableFuture<AtomicInteger> future1 = CompletableFuture.supplyAsync(() -> {
            // Симулируем длительную задачу
            long startTime1 = System.currentTimeMillis();
            usersCount.set((int) userRep.count());
            time1.set(System.currentTimeMillis() - startTime1);
            return usersCount;
        });

        CompletableFuture<List<Operation>> future2 = CompletableFuture.supplyAsync(() -> {
            // Симулируем длительную задачу - 2
            long startTime2 = System.currentTimeMillis();
            for (Operation x : opRep.findAll()) {
                operations.add(x);
            }
            time2.set(System.currentTimeMillis() - startTime2);
            return operations;
        });
        future1.thenAccept(userNum -> {
            System.out.println("Пользователи посчитаны: их ровно: " + userNum + " штук.");
        });
        future2.thenAccept(list -> {
            System.out.println("Получен спиоск из : " + list.size() + " операций.");
        });
        int num =(int) future1.join().get();
        List<Operation> list = future2.join();
        long time3 = (System.currentTimeMillis() - startTime);
        // Обрабатываем полученный результат

        //long time3 = System.currentTimeMillis() - startTime;
        result.setStatus(Status.COMPLETED);
        content = "Report: " +
                "\tnumber of registered users - " + usersCount.get()
                + ".\n\tIt took " + time1 + " ms to count them.\n\t"
                + "It took " + time2 + " ms to collect all operations. \n\t"
                + "Report generation has taken " + time3 + " ms.";
        result.setT1(Long.parseLong(time1.toString()));
        result.setT2(Long.parseLong(time2.toString()));
        result.setT3(time3);
        result.setContent(content);
        result.setDateTime(OffsetDateTime.now());
        long id = reportRep.save(result).getId();

        return Map.entry(id, list);
        //return reportRep.save(result).getId();
        //System.out.println(result);
        //return 0l;
    }
}
