package com.delicacy.grape.autoconfigure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.DeprecatedConfigurationProperty;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author yutao
 * @create 2019-10-27 9:53
 **/
@Data
@ConfigurationProperties(prefix = "user")
public class UserProperties {
    private String username;
    private String age;
    private Boolean enable = true;
    @DurationUnit(ChronoUnit.SECONDS)
    private Duration expire = Duration.ofSeconds(2000);
    @DataSizeUnit(DataUnit.MEGABYTES)
    private DataSize photoSize = DataSize.of(20, DataUnit.MEGABYTES);


    private final User user = new User();

    @DeprecatedConfigurationProperty(reason = "error",replacement = "none")
    public String getAge(){
        return age;
    }


}
