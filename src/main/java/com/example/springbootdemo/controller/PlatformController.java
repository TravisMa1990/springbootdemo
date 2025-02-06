package com.example.springbootdemo.controller;

//import com.example.springbootdemo.eventbus.RMQProducer;
import com.example.springbootdemo.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/platform")
public class PlatformController {
//    @Autowired
//    private RMQProducer rmqProducer;

    @Autowired
    private PlatformService platformService;

//    @GetMapping("/testasync")
//    public String testasync(String msg) {
//        rmqProducer.sendAsyncMessage(msg);
//        return "success";
//    }
//
    @GetMapping("/testsync")
    public String test(String msg) {
//        rmqProducer.sendSyncMessage(msg);
        return "success";
    }

    @GetMapping("/data/{id}")
    public DeferredResult<ResponseEntity<?>> getData(@PathVariable String id,
                                                     @RequestParam(value = "timeout", required = false, defaultValue = "10000") Long timeout) {
        DeferredResult<ResponseEntity<?>> output = new DeferredResult<>(timeout);
        output.onTimeout(() -> {
            output.setErrorResult(new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT));
        });
        // 异步查询数据库
        platformService.getData(id, data -> {
            if (data != null) {
                output.setResult(ResponseEntity.ok(data));
            }
//            else {
//                output.setErrorResult(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//            }
        });

        return output;
    }

}
