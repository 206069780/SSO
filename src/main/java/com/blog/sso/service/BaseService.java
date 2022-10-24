package com.blog.sso.service;

import com.blog.sso.entity.ResponseEntity;

import java.util.List;

public interface BaseService<T, V> {
    T queryById(V id, String daoName);

    T queryByName(V name, String daoName);

    List<T> query(T t, String daoName);

    boolean edit(T t, String daoName);

    boolean insert(T t, String daoName);

    boolean delete(String UUID, String daoName);

    boolean delete(T t, String daoName);
}
