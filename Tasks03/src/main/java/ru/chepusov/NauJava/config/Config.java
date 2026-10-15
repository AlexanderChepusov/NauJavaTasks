package ru.chepusov.NauJava.config;

import java.util.Map;
import java.util.TreeMap;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import ru.chepusov.NauJava.entities.MoneyOperation;

@Configuration
@ConfigurationProperties(prefix="app")
@PropertySource("classpath:application.properties")
public class Config
{
    //@Value(prefix=app)
    String name;
    String version;

    @PostConstruct
    private void appDataPrint() {
        System.out.println(name + ", version " + version);
    }

    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public Map<Integer , MoneyOperation> userContainer()
    {
        return new TreeMap<>();
    }
}
