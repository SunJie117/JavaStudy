package com.camark.Generic.dao;

import com.camark.annotationstudy.Transaction;

import java.io.Serializable;

public interface Dao<T> {
    void add(T t);

    T findOne(Serializable id);

    @Transaction
    boolean save(T t);
}
