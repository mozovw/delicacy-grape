package com.delicacy.grape.enable;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author yutao
 * @create 2019-09-25 9:52
 **/
public class EchoImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{
                "com.delicacy.grape.enable.InitData"};
    }


}
