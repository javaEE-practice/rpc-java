package com.wch.rpc;

import java.io.IOException;
import java.net.Socket;

import com.wch.rpc.service.TalkService;

/**
 * @author CH W
 * @description
 * @date 2020年5月11日 上午10:31:29
 * @version 1.0
 */
public class RPCClient {
	
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("127.0.0.1", 8080);
		TalkService talkService = ClientProxy.getInstance(TalkService.class, socket);
		String sayHello = talkService.sayHello("李雷");
		System.out.println("sayHello：" + sayHello);
		socket.close();
    }

}
