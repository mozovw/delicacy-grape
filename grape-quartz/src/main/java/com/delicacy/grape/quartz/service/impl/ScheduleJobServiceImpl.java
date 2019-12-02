/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.delicacy.grape.quartz.service.impl;


import com.delicacy.grape.quartz.config.ScheduleManager;
import com.delicacy.grape.quartz.enums.ScheduleStatus;
import com.delicacy.grape.quartz.model.ScheduleJob;
import com.delicacy.grape.quartz.service.ScheduleJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author lgh
 */
@Slf4j
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {

	@Autowired
	private ScheduleManager scheduleManager;

	@Override
	public void saveAndStart(ScheduleJob scheduleJob) {
		log.info("开始......");
		scheduleJob.setCreateTime(new Date());
		scheduleJob.setStatus(ScheduleStatus.NORMAL.getType());
		scheduleManager.createScheduleJob(scheduleJob);
	}

	@Override
	public void updateScheduleJob(ScheduleJob scheduleJob) {

	}

	@Override
	public void deleteBatch(Long[] jobIds) {
		Arrays.stream(jobIds).forEach(e->{
			ScheduleJob scheduleJob = new ScheduleJob();
			scheduleJob.setJobId(e);
			scheduleManager.deleteScheduleJob(scheduleJob);
		});
	}

	@Override
	public int updateBatch(Long[] jobIds, int status) {
		return 0;
	}

	@Override
	public void run(Long[] jobIds) {
		Arrays.stream(jobIds).forEach(e->{
			ScheduleJob scheduleJob = new ScheduleJob();
			scheduleJob.setJobId(e);
			scheduleManager.run(scheduleJob);
		});
	}

	@Override
	public void pause(Long[] jobIds) {
		Arrays.stream(jobIds).forEach(e->{
			ScheduleJob scheduleJob = new ScheduleJob();
			scheduleJob.setJobId(e);
			scheduleManager.pauseJob(scheduleJob);
		});

	}

	@Override
	public void resume(Long[] jobIds) {
		Arrays.stream(jobIds).forEach(e->{
			ScheduleJob scheduleJob = new ScheduleJob();
			scheduleJob.setJobId(e);
			scheduleManager.resumeJob(scheduleJob);
		});
	}
}
