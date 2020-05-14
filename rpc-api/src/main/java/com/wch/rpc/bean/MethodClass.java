package com.wch.rpc.bean;

import java.io.Serializable;

/**
 * @author CH W
 * @description
 * @date 2020年5月12日 下午3:50:01
 * @version 1.0
 */
public class MethodClass implements Serializable {
	private static final long serialVersionUID = -8031021861496791481L;
	
	private String className;
	private String methodName;
	private Object[] args;
	private Class<?>[] argsTypes;
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
	public Class<?>[] getArgsTypes() {
		return argsTypes;
	}
	public void setArgsTypes(Class<?>[] argsTypes) {
		this.argsTypes = argsTypes;
	}

}
