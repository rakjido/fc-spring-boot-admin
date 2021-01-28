package io.rooftop.admin.controller.api;

import io.rooftop.admin.controller.CrudController;
import io.rooftop.admin.entity.Item;
import io.rooftop.admin.network.Header;
import io.rooftop.admin.network.request.ItemApiRequestDto;
import io.rooftop.admin.network.response.ItemApiResponseDto;
import io.rooftop.admin.service.ItemApiLogicService;
import io.rooftop.admin.utils.CrudInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;


//=====================================================================
// Using CrudController + BaseService
//=====================================================================

@RestController
@RequestMapping("/api/item")
public class ItemApiController extends CrudController<ItemApiRequestDto, ItemApiResponseDto, Item> {

}

//=====================================================================
// Using CrudController
//=====================================================================

//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/api/item")
//public class ItemApiController extends CrudController<ItemApiRequestDto, ItemApiResponseDto> {
//
//    private final ItemApiLogicService itemApiLogicService;
//
//    @PostConstruct
//    public void init() {
//        this.baseService = itemApiLogicService;
//    }
//}

//=====================================================================
// General
//=====================================================================

//@RequiredArgsConstructor
//@RestController
//public class ItemApiController implements CrudInterface<ItemApiRequestDto, ItemApiResponseDto> {
//
//    private final ItemApiLogicService itemApiLogicService;
//
//    @Override
//    @PostMapping("/api/item")
//    public Header<ItemApiResponseDto> create(@RequestBody  Header<ItemApiRequestDto> request) {
//        return itemApiLogicService.create(request);
//    }
//
//    @Override
//    @GetMapping("/api/item/{id}")
//    public Header<ItemApiResponseDto> read(@PathVariable Long id) {
//        return itemApiLogicService.read(id);
//    }
//
//    @Override
//    @PutMapping("/api/item")
//    public Header<ItemApiResponseDto> update(@RequestBody Header<ItemApiRequestDto> request) {
//        return itemApiLogicService.update(request);
//    }
//
//    @Override
//    @DeleteMapping("/api/item/{id}")
//    public Header delete(@PathVariable Long id) {
//        return itemApiLogicService.delete(id);
//    }
//}
