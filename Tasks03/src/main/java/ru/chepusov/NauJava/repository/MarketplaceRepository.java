package ru.chepusov.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.chepusov.NauJava.entities.Marketplace;

import java.util.List;

@RepositoryRestResource
public interface MarketplaceRepository extends CrudRepository<Marketplace, Integer> {
    List<Marketplace> findByAddress(String address);
    Marketplace findById(int id);
    List<Marketplace> findByName(String name);
    List<Marketplace> findByWorkingTimeAfter(int hours);

}
