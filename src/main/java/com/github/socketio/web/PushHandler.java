package com.github.socketio.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ron
 * @version PushHandler, v 0.1 2019/2/13 17:24 Administrator Exp $
 * @contact raosay92@gmail.com
 */
@Controller
public class PushHandler {


    @RequestMapping(value = "push",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void pushMessage(){
        System.out.println("111");
    }
}
