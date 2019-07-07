package com.pianxian.blog.service.impl;

import com.pianxian.blog.entity.Catalog;
import com.pianxian.blog.mapper.CatalogMapper;
import com.pianxian.blog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    CatalogMapper catalogMapper;

    @Override
    @Cacheable(cacheNames = {"catalogs"}, key = "'catalogs:user_id:'+#userId")
    public List<Catalog> findCatalogsListByUserId(Integer userId) {
        return catalogMapper.findCatalogsListByUserId(userId);
    }

    @Override
    public Catalog findById(Integer id) {
        return catalogMapper.findCatalog(id);
    }

    @Override
    @CacheEvict(cacheNames = {"catalogs"}, key = "'catalogs:user_id:'+#root.args[0].userId")
    public Catalog saveOrUpdate(Catalog catalog) {
        if (catalog.getId() == null) {
            catalogMapper.insertCatalog(catalog);
        } else {
            catalogMapper.updateCatalog(catalog);
        }
        return catalog;
    }

    @Override
    @CacheEvict(cacheNames = {"catalogs"}, allEntries = true)
    public void delete(Integer id) {
        catalogMapper.deleteCatalog(id);
    }
}
