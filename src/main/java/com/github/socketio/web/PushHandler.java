package com.github.socketio.web;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.github.socketio.ChatObject;
import com.github.socketio.ServerRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ron
 * @version PushHandler, v 0.1 2019/2/13 17:24 Administrator Exp $
 * @contact raosay92@gmail.com
 */
@Controller
public class PushHandler {


    @Autowired
    private ServerRunner serverRunner;


    @RequestMapping(value = "push",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void pushMessage(@RequestParam String userName,@RequestParam String message ,@RequestParam String fromUserName){

        SocketIOClient  client = serverRunner.clientMap.get(userName);
        if(client == null){
            System.out.println("未找到链接的用户");
            return ;
        }
        ChatObject chatObject = new ChatObject();
        chatObject.setMessage(message);
        chatObject.setUserName(fromUserName);
        client.sendEvent("push_event",chatObject);
    }
}
