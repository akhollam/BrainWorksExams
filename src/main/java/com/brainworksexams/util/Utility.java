package com.brainworksexams.util;

import java.util.UUID;

public class Utility {

	public static String uuid() {
		return UUID.randomUUID().toString();//.replaceAll("-", "");
	}
}
