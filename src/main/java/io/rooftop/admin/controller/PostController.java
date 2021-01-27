package io.rooftop.admin.controller;

import io.rooftop.admin.dto.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

//    HTML <form>
//    ajax
//    http post body -> data
//    json, xml, mulipart / text-plain

//    @RequestMapping(method = RequestMethod.POST, value = "/api/postMethod")
//    @PostMapping(value = "/api/postMethod", produces = {"application-json"})
    @PostMapping(value = "/api/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam) {
        return searchParam;
    }
}
