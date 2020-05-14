package com.wch.rpc.service;

import com.wch.rpc.service.TalkService;

/**
 * @author CH W
 * @description
 * @date 2020年5月11日 上午9:52:56
 * @version 1.0
 */
public class TalkServiceImpl implements TalkService {

	@Override
	public String sayHello(String content) {
		System.out.println("hi，" + content);
		return "hi，你最近在忙啥";
	}

}
