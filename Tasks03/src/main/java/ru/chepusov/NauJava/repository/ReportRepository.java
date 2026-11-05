package ru.chepusov.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.chepusov.NauJava.entities.Report;

public interface ReportRepository extends CrudRepository<Report, Long> {

}
