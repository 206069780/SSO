package com.blog.sso.dao;

import java.util.List;

public interface BaseProperties<T, V> {

    T queryById(V id);

    T queryByName(V name);

    List<T> query(T t);

    int edit(T t);

    int insert(T t);

    int deleteById(String UUID);

    int delete(T t);

}
