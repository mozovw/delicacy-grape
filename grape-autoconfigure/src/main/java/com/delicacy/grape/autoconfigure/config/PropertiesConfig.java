package com.delicacy.grape.autoconfigure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author yutao
 * @create 2019-10-27 9:38
 **/
@Configuration
@Import({UserConfig.class})
public class PropertiesConfig {


}
