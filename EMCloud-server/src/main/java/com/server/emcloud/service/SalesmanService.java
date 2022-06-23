package com.server.emcloud.service;

import com.server.emcloud.domain.Salesman;

import java.util.List;

public interface SalesmanService {
    List<Salesman> getAll();

    int addSalesman(Salesman salesman);
}
