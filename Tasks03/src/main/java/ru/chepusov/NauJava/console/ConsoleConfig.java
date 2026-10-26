package ru.chepusov.NauJava.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Scanner;

//@Configuration
//@Profile("!test")
public class ConsoleConfig
{
//    @Autowired
    private CommandProcessor commandProcessor;

//    @Bean
    public CommandLineRunner commandScanner()
    {
        return args ->
        {
            try (Scanner scanner = new Scanner(System.in))
            {
                System.out.println("Введите команду. 'exit' для выхода.");
                while (true)
                {
                    // Показать приглашение для ввода
                    System.out.print("Введите команду в формате <команда сумма категория счет>\n\r> ");
                    String input = scanner.nextLine();
                    // Выход из цикла, если введена команда "exit"
                    if ("exit".equalsIgnoreCase(input.trim()))
                    {
                        System.out.println("Выход из программы...");
                        break;
                    }
                    // Обработка команды
                    commandProcessor.processCommand(input);
                }
            }
        };
    }
}



