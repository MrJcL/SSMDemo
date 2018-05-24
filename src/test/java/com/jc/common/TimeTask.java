/**
 * This file created at 2010-10-22.
 *
 * Copyright (c) 2002-2010 Bingosoft, Inc. All rights reserved.
 */
package com.jc.common;

import java.util.Date;
import java.util.TimerTask;

public class TimeTask extends TimerTask {
	private static boolean isRunning = false;

	public void run() {
		if (!isRunning) {
			isRunning = true;
			System.out.println("------------------------定时开始时间：" + new Date());
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("------------------------定时结束时间：" + new Date());
			isRunning = false;

		} else {
			System.out.println("执行错误");
		}

	}

}