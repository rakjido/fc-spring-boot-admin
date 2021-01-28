package io.rooftop.admin.controller.api;

import io.rooftop.admin.controller.CrudController;
import io.rooftop.admin.entity.User;
import io.rooftop.admin.network.Header;
import io.rooftop.admin.network.request.UserApiRequestDto;
import io.rooftop.admin.network.response.UserApiResponseDto;
import io.rooftop.admin.service.UserApiLogicService;
import io.rooftop.admin.utils.CrudInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;


//=====================================================================
// Using CrudController + BaseService
//=====================================================================

//@RestController
//@RequestMapping("/api/user")
//public class UserApiController extends CrudController<UserApiRequestDto, UserApiResponseDto, User> {
//
//}

//=====================================================================
// Using CrudController
//=====================================================================


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController extends CrudController<UserApiRequestDto, UserApiResponseDto> {

    private final UserApiLogicService userApiLogicService;

    @PostConstruct
    public void init() {
        this.baseService = userApiLogicService;
    }
}

//=====================================================================
// General
//=====================================================================

//@Slf4j
//@RequiredArgsConstructor
//@RestController
//public class UserApiController implements CrudInterface<UserApiRequestDto, UserApiResponseDto> {
//
//    private final UserApiLogicService userApiLogicService;
//
//    @Override
//    @PostMapping("/api/user")
//    public Header<UserApiResponseDto> create(@RequestBody  Header<UserApiRequestDto> request) {
//        log.info("{}", request);
//        return userApiLogicService.create(request);
//    }
//
//    @Override
//    @GetMapping("/api/user/{id}")
//    public Header<UserApiResponseDto> read(@PathVariable Long id) {
//        log.info("read : {}", id);
//        return userApiLogicService.read(id);
//    }
//
//    @Override
//    @PutMapping("/api/user")
//    public Header<UserApiResponseDto> update(@RequestBody Header<UserApiRequestDto> request) {
//        log.info("update : {}", request);
//        return userApiLogicService.update(request);
//    }
//
//    @Override
//    @DeleteMapping("/api/user/{id}")
//    public Header delete(@PathVariable Long id) {
//        log.info("delete : {}", id);
//        return userApiLogicService.delete(id);
//    }
//}
