package model;

public interface BaseCRUD<T> {
    int id = 10;
    void create(T t);

    void update(T t);
}