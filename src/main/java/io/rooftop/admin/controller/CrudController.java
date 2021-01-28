package io.rooftop.admin.controller;

import io.rooftop.admin.network.Header;
import io.rooftop.admin.service.BaseService;
import io.rooftop.admin.utils.CrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

//===============================================
// for Abstract controller + (Abstract) BaseService
//===============================================

@Component
public abstract class CrudController<Req, Res, Entity> implements CrudInterface<Req, Res> {

    @Autowired(required = false)
    protected BaseService<Req, Res, Entity> baseService;

    @Override
    @PostMapping("")
    public Header<Res> create(@RequestBody Header<Req> request) {
        return baseService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<Res> read(@PathVariable Long id) {
        return baseService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<Res> update(@RequestBody Header<Req> request) {
        return baseService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return baseService.delete(id);
    }
}


//===============================================
// for Abstract controller
//===============================================

//public class CrudController<Req, Res> implements CrudInterface<Req, Res> {
//
//    protected CrudInterface<Req, Res> baseService;
//
//    @Override
//    @PostMapping("")
//    public Header<Res> create(@RequestBody Header<Req> request) {
//        return baseService.create(request);
//    }
//
//    @Override
//    @GetMapping("{id}")
//    public Header<Res> read(@PathVariable Long id) {
//        return baseService.read(id);
//    }
//
//    @Override
//    @PutMapping("")
//    public Header<Res> update(@RequestBody Header<Req> request) {
//        return baseService.update(request);
//    }
//
//    @Override
//    @DeleteMapping("{id}")
//    public Header delete(@PathVariable Long id) {
//        return baseService.delete(id);
//    }
//}
