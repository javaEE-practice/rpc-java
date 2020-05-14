package com.wch.rpc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

import com.wch.rpc.bean.MethodClass;
import com.wch.rpc.common.SerializeUtil;

/**
 * @author CH W
 * @description
 * @date 2020年5月11日 上午10:06:27
 * @version 1.0
 */
public class RPCServer {

	public static void main(String[] args) {
		try {
			openServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 这个方法用来启动服务端,然后接受数据,返回处理完的结果
	public static void openServer() throws IOException {
		ServerSocket serverSocket = new ServerSocket(8080);
		try {
			System.out.println("服务开启");
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println(socket.getInetAddress() + "-connected");
				InputStream in = socket.getInputStream();
				byte[] buf = new byte[1024];
				in.read(buf);
				
				MethodClass methodClasss = (MethodClass) SerializeUtil.deSerialize(buf);
				Class<?> clazz = Class.forName(methodClasss.getClassName() + "Impl");
				Method method = clazz.getMethod(methodClasss.getMethodName(), methodClasss.getArgsTypes());
				Object result = method.invoke(clazz.newInstance(), methodClasss.getArgs());
				
				OutputStream out = socket.getOutputStream();
				byte[] bytes = SerializeUtil.serialize(result);
				out.write(bytes);
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			serverSocket.close();
		}
	}

}
