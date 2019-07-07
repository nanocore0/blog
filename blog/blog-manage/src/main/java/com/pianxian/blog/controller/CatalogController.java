package com.pianxian.blog.controller;

import com.pianxian.blog.common.Response;
import com.pianxian.blog.entity.Catalog;
import com.pianxian.blog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CatalogController {

    @Autowired
    CatalogService catalogService;

    @GetMapping("/catalogs/{userId}")
    public List<Catalog> listCatalogsByUserId(@PathVariable(value = "userId") Integer userId) {
        List<Catalog> catalogs = catalogService.findCatalogsListByUserId(userId);
        return catalogs;
    }

    @GetMapping("/catalog/{id}")
    public Catalog query(@PathVariable Integer id) {
        Catalog catalog = catalogService.findById(id);
        return catalog;
    }

    @PostMapping("/catalog")
    public Catalog save(@RequestBody Catalog catalog) {
        catalog = catalogService.saveOrUpdate(catalog);
        return catalog;

    }

    @PutMapping("/catalog/{id}")
    public Catalog update(@RequestBody Catalog catalog) {
        catalogService.saveOrUpdate(catalog);
        return catalog;
    }

    @DeleteMapping("/catalog/{id}")
    public void delete(@PathVariable Integer id) {
        catalogService.delete(id);
    }
}
