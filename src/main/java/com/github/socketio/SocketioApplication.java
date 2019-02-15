package com.github.socketio;

import com.corundumstudio.socketio.AckMode;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SocketioApplication {

	/**
	 *
	 */
	@Value("${socketio.host}")
	private String host;
	@Value("${socketio.port}")
	private Integer port;
	@Value("${socketio.bossCount}")
	private int bossCount;
	@Value("${socketio.workCount}")
	private int workCount;
	@Value("${socketio.upgradeTimeout}")
	private int upgradeTimeout;
	@Value("${socketio.pingTimeout}")
	private int pingTimeout;
	@Value("${socketio.pingInterval}")
	private int pingInterval;


	@Bean
	public SocketIOServer socketIOServer(){
		SocketConfig socketConfig  = new SocketConfig();
		socketConfig.setTcpNoDelay(true);
		socketConfig.setSoLinger(0);
		Configuration config = new Configuration();
		config.setSocketConfig(socketConfig);
		config.setAckMode(AckMode.AUTO_SUCCESS_ONLY);
		config.setHostname(host);
		config.setPort(port);
		config.setBossThreads(bossCount);
		config.setWorkerThreads(workCount);
		config.setAllowCustomRequests(true);
		config.setUpgradeTimeout(upgradeTimeout);
		config.setPingTimeout(pingTimeout);
		config.setPingInterval(pingInterval);
		SocketIOServer socketIOServer = new SocketIOServer(config);
		return socketIOServer;
	}



	@Bean
	public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
		return new SpringAnnotationScanner(socketServer);
	}



	public static void main(String[] args) {
		SpringApplication.run(SocketioApplication.class, args);
	}

}

