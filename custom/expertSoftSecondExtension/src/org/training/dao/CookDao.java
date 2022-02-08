package org.training.dao;

import org.training.model.CookModel;

import java.util.List;

public interface CookDao {
    List<CookModel> findCooks();
}
