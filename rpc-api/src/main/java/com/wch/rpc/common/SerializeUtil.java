package com.wch.rpc.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author CH W
 * @description	序列化/反序列化工具类
 * @date 2020年5月12日 下午3:50:47
 * @version 1.0
 */
public class SerializeUtil {

	/**
	 * --对象序列化
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	public static byte[] serialize(Object obj) throws IOException {
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteOutput);
		out.writeObject(obj);
		out.flush();
		byte[] byteArray = byteOutput.toByteArray();
		out.close();
		byteOutput.close();
		return byteArray;
	}
	/**
	 * --反序列化对象
	 * @param bytes
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object deSerialize(byte[] bytes) throws IOException, ClassNotFoundException {
		ByteArrayInputStream byteInput = new ByteArrayInputStream(bytes);
		ObjectInputStream in = new ObjectInputStream(byteInput);
		Object readObject = in.readObject();
		in.close();
		byteInput.close();
		return readObject;
	}
	
}
