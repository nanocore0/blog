package com.pianxian.blog.mapper;

import com.pianxian.blog.entity.Catalog;

import java.util.List;

public interface CatalogMapper {

    Catalog findCatalog(Integer id);

    List<Catalog> findCatalogsListByUserId(Integer userId);

    int insertCatalog(Catalog catalog);

    int updateCatalog(Catalog catalog);

    int deleteCatalog(Integer id);
}
