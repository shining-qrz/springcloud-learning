package edu.wust.qrz.controller;


import edu.wust.qrz.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageRestController {

    @Autowired
    StorageService  storageService;

    @GetMapping("/deduct")
    public String deduct(@RequestParam("commodityCode") String commodityCode,
                         @RequestParam("count") Integer count) {

        storageService.deduct(commodityCode, count);
        return "storage deduct success";
    }
}
