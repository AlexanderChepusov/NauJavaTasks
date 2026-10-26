package ru.chepusov.NauJava.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.chepusov.NauJava.entities.MoneyOperation;
import ru.chepusov.NauJava.service.MoneyOperationService;
import ru.chepusov.NauJava.service.OperationService;
import org.springframework.context.annotation.Profile;

//@Component
//@Profile("!test")
public class CommandProcessor
{
    private final MoneyOperationService moneyService;
    //private final OperationService<>

    //@Autowired
    public CommandProcessor(MoneyOperationService moneyService)
    {
        this.moneyService = moneyService;
    }


    public void processCommand(String input)
    {
        String[] cmd = input.split(" ");
        switch (cmd[0])
        {
            case "pay" ->
            {
                moneyService.payment(Double.parseDouble(cmd[1]), cmd[2], cmd[3]);// .createUser(Long.valueOf(cmd[1]), cmd[2]);
                System.out.println("Расход успешно добавлен...");
            }
            case "get" ->
            {
                moneyService.income(Double.parseDouble(cmd[1]), cmd[2], cmd[3]);// .createUser(Long.valueOf(cmd[1]), cmd[2]);
                System.out.println("Приход успешно добавлен...");
            }
            case "list" ->
            {
                moneyService.showAll();
            }
            case "cat" ->
            {
                //System.out.println("Баланс по категории " + cmd[1] + " равен " + moneyService.summCategory(cmd[1]));

            }
            case "acc" ->
            {
                System.out.println("Баланс по счету " + cmd[1] + " равен " + moneyService.summAccount(cmd[1]));
            }
            // …. здесь логика Обработки других команды
            default -> System.out.println("Введена неизвестная команда...");
        }
    }
}