package ru.chepusov.NauJava.service;

import org.springframework.stereotype.Service;
import ru.chepusov.NauJava.entities.Category;
import ru.chepusov.NauJava.entities.Operation;
import ru.chepusov.NauJava.repository.OperationRepository;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

@Service
public class OperationAnalysisService {
    private final OperationRepository opRep;

    public OperationAnalysisService(OperationRepository opRep) {
        this.opRep = opRep;
    }

    public String[][] getOperationsPerCategory(long id) { //Map<Category, Operation>
        //List<Category> cats2 = opRep.findCategoryByUserIdOrderByCategory_Name(id);
        List<Category> cats = opRep.getUserCategorises(id);
        int n = cats.size();
        //Array{"сумма", "количество операций", "средний чек"}
        String data[][] = new String[n][4];
        int i = 0;
        for(Category x : cats) {
            List<Operation> ops = opRep.findByUserAndCategory_NameIgnoreCaseAndType(
                    id, x.getName(), "payment");
//            DoubleStream ds = (DoubleStream) ops.stream().map(op -> op.getAmount()).toList();
//            DoubleSummaryStatistics stats = ds.collect(DoubleSummaryStatistics::new,
//                    DoubleSummaryStatistics::accept,
//                    DoubleSummaryStatistics::combine);

            DoubleSummaryStatistics ds = ops.stream()
                    .mapToDouble(op -> op.getAmount()) // используем mapToDouble
                    .summaryStatistics();

            data[i][0] = cats.get(i).getName();
            data[i][1] = String.valueOf(ds.getSum());
            data[i][2] = String.valueOf(ds.getAverage());
            data[i][3] = String.valueOf(ds.getCount());
            i++;
        }
        printArr(data) ;//, cats);
        //Map<Category, Operation> map = new TreeMap<>();
        return data;

    }

    static void printArr(String[][] data){ //}, List<Category> list) {
        int i =0;
        for (String[] x : data ) {
            //System.out.print(list.get(i++) + ": ");
            for (String y : x) {
                System.out.print(y + "\t");
            }
            System.out.println();
        }
    }


}
