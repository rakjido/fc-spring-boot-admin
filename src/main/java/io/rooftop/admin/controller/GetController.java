package io.rooftop.admin.controller;

import io.rooftop.admin.dto.SearchParam;
import io.rooftop.admin.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/api/getMethod")
    public String getRequest() {
        return "Hi getMethod";
    }

    @GetMapping("/api/getParameter")
    public String getparameter(@RequestParam String id, @RequestParam(name="password") String pwd){
        System.out.println("id :" + id);
        System.out.println("password : " + pwd);
        return id+pwd;
    }

    @GetMapping("/api/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam) {
//        System.out.println("searchParam.getAccount() = " + searchParam.getAccount());
//        System.out.println("searchParam.getEmail() = " + searchParam.getEmail());
//        System.out.println("searchParam.getPage() = " + searchParam.getPage());
        return searchParam;
    }

    @GetMapping("/header")
    public Header getHeader() {
        return Header.builder().resultCode("OK").description("OK").build();
    }
}
