package ru.chepusov.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.chepusov.NauJava.entities.Marketplace;

import java.util.List;

public interface MarketplaceRepository extends CrudRepository<Marketplace, Integer> {
    List<Marketplace> findByAddress(String address);
    Marketplace findById(int id);
    List<Marketplace> findByName(String name);
    List<Marketplace> findByWorkingTimeAfter(int hours);

}
