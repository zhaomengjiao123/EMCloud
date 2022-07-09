package com.server.emcloud.controller;

import com.server.emcloud.service.AsyncService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/08/19:59
 * @Description:
 */
@RestController
@RequestMapping("/asyncController")
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/async")
    public Object async(){
        JSONObject jsonObject = new JSONObject();
        asyncService.executeAsync();
        jsonObject.put("res:","ok");
        return jsonObject;
    }
}
