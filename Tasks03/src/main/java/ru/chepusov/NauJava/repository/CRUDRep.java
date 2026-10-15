package ru.chepusov.NauJava.repository;

public  interface CRUDRep<T> {

    void create(T entity);

    T read(int id);

    void update(int id, T entity);

    void delete(int id);

}
