package com.senrui.sso.service.iml;

import com.senrui.sso.dao.BaseProperties;
import com.senrui.sso.service.BaseService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BaseServiceIml<T, V> implements BaseService<T, V> {

    @Autowired
    Map<String, BaseProperties<T, V>> baseProperties;

    @Override
    public T queryById(V id, String daoName) {
        return baseProperties.get(daoName).queryById(id);
    }

    @Override
    public T queryByName(V name, String daoName) {
        return baseProperties.get(daoName).queryByName(name);
    }

    @Override
    public List<T> query(T t, String daoName) {
        return baseProperties.get(daoName).query(t);
    }

    @Override
    public boolean edit(T t, String daoName) {
        val num = baseProperties.get(daoName).edit(t);
        return getStatus(num);
    }

    @Override
    public boolean insert(T t, String daoName) {
        return getStatus(baseProperties.get(daoName).insert(t));
    }


    @Override
    public boolean delete(String UUID, String daoName) {
        return getStatus(baseProperties.get(daoName).deleteById(UUID));
    }

    @Override
    public boolean delete(T t, String daoName) {
        return getStatus(baseProperties.get(daoName).delete(t));
    }

    private boolean getStatus(int num) {
        return num > 0;
    }
}
