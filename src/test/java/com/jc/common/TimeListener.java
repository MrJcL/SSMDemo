/**
 * This file created at 2010-10-22.
 *
 * Copyright (c) 2002-2010 Bingosoft, Inc. All rights reserved.
 */
package com.jc.common;

import java.util.Calendar;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class TimeListener implements ServletContextListener {
	 //每天的毫秒数  
    public static final long DAY = 86400000;  
    //定时器  
    private Timer timer;  
      
    /** 
     * 在Web应用结束时停止任务 
     */  
    public void contextDestroyed(ServletContextEvent sce) {  
        timer.cancel();//定时器销毁  
  
    }  
  
    /** 
     * 在Web应用启动时初始化任务 
     */  
    public void contextInitialized(ServletContextEvent sce) {  
        //定义定时器  
    	Calendar c = Calendar.getInstance();  
    	c.add(Calendar.DATE, 1);  
    	c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);  
        timer = new Timer(true);  
        TimeTask locationTask = new TimeTask();//定时执行的内容  
        timer.schedule(locationTask, 5000, 60000); // 启动后5秒执行,后每隔20秒再执行  
        
    }  
}  