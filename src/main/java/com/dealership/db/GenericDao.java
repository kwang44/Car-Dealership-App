package com.dealership.db;

/**
 * A  dao or data access object
 * @param <T> object being worked on
 * @param <I> the primary key
 */
public interface GenericDao<T, I> {
    //Save to db
    int save(T t);
    //select an object by its primary key
    T get(I pk);
    //gather all objects in the db
    T[] getAll();
    //gather all objects in the db with the specific primary key of a composite key (or get 1 row if table only has 1 primary key)
    T[] getAll(I pk);
    //remove from db
    int remove(I pk);
    //update object in db
    int update(T t);
    //combination of update or insert when need
    int insertOrUpdate(T t);
}
