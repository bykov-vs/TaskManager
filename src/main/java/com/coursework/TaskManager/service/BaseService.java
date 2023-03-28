package com.coursework.TaskManager.service;

import java.util.List;

public interface BaseService<T> {
    void save(T t);

    T find(T t);

    T find(long id);

    T delete(long id);

    T delete(T t);

}
