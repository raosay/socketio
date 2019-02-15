package com.github.socketio;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ron
 * @version ServerRunner, v 0.1 2019/2/14 11:39 Administrator Exp $
 * @contact raosay92@gmail.com
 */
@Component
public class ServerRunner implements CommandLineRunner {


    public final Map<String,SocketIOClient> clientMap = new HashMap<>();

    private final SocketIOServer socketIOServer;

    @Autowired
    public ServerRunner(SocketIOServer socketIOServer){
        this.socketIOServer = socketIOServer;
    }



    @Override
    public void run(String... args) throws Exception {
        socketIOServer.addConnectListener(client -> {
            String userName = getParamsByClient(client);
            if (userName !=null){
                clientMap.put(userName ,client);
                System.out.println("connection  cname:"+userName +",date :"+System.currentTimeMillis());
                System.out.println(clientMap.size());
            }
        });

        socketIOServer.addDisconnectListener(client -> {
            String userName = getParamsByClient(client);
            if (userName !=null){
                clientMap.remove(userName);
                client.disconnect();
                System.out.println("disconnection  cname:"+userName +",date :"+System.currentTimeMillis() );
                System.out.println(clientMap.size());


            }
        });

        socketIOServer.addEventListener("pushMessage", ChatObject.class, new DataListener<ChatObject>() {
            @Override
            public void onData(SocketIOClient client, ChatObject data, AckRequest ackSender) throws Exception {
                if(!ackSender.isAckRequested()){
                    System.out.println("收到ack");
                }
                System.out.println("收到消息: data = "+ data.toString() +" , clientInfo = "+client.toString()+" , ack ="+ackSender.toString());
            }
        });

        socketIOServer.start();
    }


    /**
     * 此方法为获取client连接中的参数，可根据需求更改
     * @param client
     * @return
     */
    public String getParamsByClient(SocketIOClient client) {
        // 从请求的连接中拿出参数（userName）
        Map<String, List<String>> params = client.getHandshakeData().getUrlParams();
        List<String> list = params.get("userName");
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }



}
