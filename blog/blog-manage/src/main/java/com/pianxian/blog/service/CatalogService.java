package com.pianxian.blog.service;

import com.pianxian.blog.entity.Catalog;

import java.util.List;

public interface CatalogService {
    List<Catalog> findCatalogsListByUserId(Integer userId);

    Catalog findById(Integer id);

    Catalog saveOrUpdate(Catalog catalog);

    void delete(Integer id);
}
