package io.rooftop.admin.controller.api;

import io.rooftop.admin.controller.CrudController;
import io.rooftop.admin.entity.OrderGroup;
import io.rooftop.admin.network.Header;
import io.rooftop.admin.network.request.OrderGroupApiRequestDto;
import io.rooftop.admin.network.response.OrderGroupApiResponseDto;
import io.rooftop.admin.service.OrderGroupApiLogicService;
import io.rooftop.admin.utils.CrudInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

//=====================================================================
// Using CrudController + BaseService
//=====================================================================

@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupApiController extends CrudController<OrderGroupApiRequestDto, OrderGroupApiResponseDto, OrderGroup> {

}


//=====================================================================
// Using CrudController
//=====================================================================

//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/api/orderGroup")
//public class OrderGroupApiController extends CrudController<OrderGroupApiRequestDto, OrderGroupApiResponseDto> {
//
//    private final OrderGroupApiLogicService orderGroupApiLogicService;
//
//    @PostConstruct
//    public void init() {
//        this.baseService = orderGroupApiLogicService;
//    }
//}

//=====================================================================
// General
//=====================================================================
//@RequiredArgsConstructor
//@RestController
//public class OrderGroupApiController implements CrudInterface<OrderGroupApiRequestDto, OrderGroupApiResponseDto> {
//
//    private final OrderGroupApiLogicService orderGroupApiLogicService;
//
//    @Override
//    @PostMapping("/api/orderGroup")
//    public Header<OrderGroupApiResponseDto> create(@RequestBody Header<OrderGroupApiRequestDto> request) {
//        return orderGroupApiLogicService.create(request);
//    }
//
//    @Override
//    @GetMapping("/api/orderGroup/{id}")
//    public Header<OrderGroupApiResponseDto> read(@PathVariable Long id) {
//        return orderGroupApiLogicService.read(id);
//    }
//
//    @Override
//    @PutMapping("/api/orderGroup")
//    public Header<OrderGroupApiResponseDto> update(@RequestBody Header<OrderGroupApiRequestDto> request) {
//        return orderGroupApiLogicService.update(request);
//    }
//
//    @Override
//    @DeleteMapping("/api/orderGroup/{id}")
//    public Header delete(@PathVariable Long id) {
//        return orderGroupApiLogicService.delete(id);
//    }
//}
