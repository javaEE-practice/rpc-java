package com.wch.rpc;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

import com.wch.rpc.bean.MethodClass;
import com.wch.rpc.common.SerializeUtil;

/**
 * @author CH W
 * @description
 * @date 2020年5月11日 上午10:23:34
 * @version 1.0
 */
public class ClientProxy<T> {

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<T> clazz, Socket socket) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				OutputStream out = null;
				InputStream in = null;
				try {
					Class<?> clazz = proxy.getClass().getInterfaces()[0];
					out = socket.getOutputStream();
					MethodClass methodClass = new MethodClass();
					methodClass.setClassName(clazz.getName());
					methodClass.setMethodName(method.getName());
					methodClass.setArgs(args);
					methodClass.setArgsTypes(method.getParameterTypes());
					out.write(SerializeUtil.serialize(methodClass));
					
					in = socket.getInputStream();
					byte[] buf = new byte[1024];
					in.read(buf);
					T deSerialize = (T) SerializeUtil.deSerialize(buf);
					return deSerialize;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					if(out != null) {
						out.close();
					}
					if(in != null) {
						in.close();
					}
				}
			}
		});
    }

}
