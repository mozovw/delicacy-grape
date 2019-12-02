/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.delicacy.grape.quartz.listener;


import com.delicacy.grape.quartz.event.ScheduleJobEvent;
import com.delicacy.grape.quartz.model.ScheduleJob;
import com.delicacy.grape.quartz.model.ScheduleJobLog;
import com.delicacy.grape.quartz.util.SpringBeanTaskUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Date;


/**
 * 异步监听定时任务事件，解决job线程无故丢失的问题
 */
@Slf4j
@Component
@EnableAsync
@AllArgsConstructor
public class SysJobListener {

	//private final ScheduleJobLogService scheduleJobLogService;

	@Async
	@EventListener(ScheduleJobEvent.class)
	public void scheduleJobEventListener(ScheduleJobEvent event) {
		ScheduleJob scheduleJob = event.getScheduleJob();
		//数据库保存执行记录
		ScheduleJobLog jobLog = new ScheduleJobLog();
		jobLog.setJobId(scheduleJob.getJobId());
		jobLog.setBeanName(scheduleJob.getBeanName());
		jobLog.setMethodName(scheduleJob.getMethodName());
		jobLog.setParams(scheduleJob.getParams());
		jobLog.setCreateTime(new Date());

		//任务开始时间
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		try {
			//执行任务
			log.info("任务准备执行，任务ID：" + scheduleJob.getJobId());
			SpringBeanTaskUtil.invokeMethod(scheduleJob);
			//任务执行总时长
			stopWatch.stop();
			long times = stopWatch.getTotalTimeMillis();
			jobLog.setTimes((int)times);
			jobLog.setStatus(1);
			StringBuilder sb = new StringBuilder();

			log.info(sb.append("任务执行完毕，任务ID：").append(jobLog.getJobId()).append("  总共耗时：").append(times).append("毫秒").toString());
		} catch (Exception e) {
			log.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);

			//任务执行总时长
			stopWatch.stop();
			long times = stopWatch.getTotalTimeMillis();
			jobLog.setTimes((int)times);
			jobLog.setStatus(0);
			//jobLog.setError(StrUtil.sub(e.toString(), 0, 2000));
		}finally {
			//scheduleJobLogService.save(jobLog);
		}
	}
}
