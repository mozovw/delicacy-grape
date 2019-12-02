package com.delicacy.grape.shardingsphere.util;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

public class MyCommentGenerator extends DefaultCommentGenerator {

    private Properties properties;
    private Properties systemPro;
    private boolean suppressDate;
    private boolean suppressAllComments;
    private SimpleDateFormat dateFormat;
    private boolean isLombok;

    public MyCommentGenerator() {
        super();
        properties = new Properties();
        suppressDate = false;
        suppressAllComments = false;
        systemPro = System.getProperties();
        isLombok = false;
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);
        suppressDate = isTrue(properties
                .getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));
        suppressAllComments = isTrue(properties
                .getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
        String dateFormatString = properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_DATE_FORMAT);
        if (StringUtility.stringHasValue(dateFormatString)) {
            dateFormat = new SimpleDateFormat(dateFormatString);
        }

        isLombok = isTrue(properties.getProperty("isLombok"));
        properties.setProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS,"false");
        super.addConfigurationProperties(properties);
    }

    @Override
    public void addClassComment(InnerClass innerClass,
                                IntrospectedTable introspectedTable) {
        addClassComment(innerClass, introspectedTable, false);
    }



    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        FullyQualifiedJavaType type = innerClass.getType();
        String fullyQualifiedName = type.getFullyQualifiedName();
        if (isContains(fullyQualifiedName)) return;
        StringBuilder sb = new StringBuilder();
        if (isLombok) {
            sb.append("import io.swagger.annotations.ApiModelProperty;\n")
                    .append("import lombok.Getter;\n")
                    .append("import lombok.Setter;\n")
                    .append("import lombok.experimental.Accessors;\n")
                    .append("\n");
            innerClass.addJavaDocLine(sb.toString());
        }
        sb = new StringBuilder();
        innerClass.addJavaDocLine("/**");
        sb.append(" * 描述:");
        sb.append(introspectedTable.getFullyQualifiedTable() + "表的实体类");
        innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
        sb.setLength(0);
        sb.append(" * @version  1.0");
        innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
        sb.setLength(0);
        sb.append(" * @author:  ");
        sb.append(systemPro.getProperty("user.name"));
        //sb.append(systemPro.getProperty("user.name"));
        innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
        sb.setLength(0);
        sb.append(" * @创建时间: ");
        sb.append(dateFormat.format(new Date()));
        innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
        innerClass.addJavaDocLine(" */");
        if (isLombok) {
            innerClass.addJavaDocLine("@Getter");
            innerClass.addJavaDocLine("@Setter");
            innerClass.addJavaDocLine("@Accessors(chain = true)");
        }
    }

    private Boolean isContains(String str) {
        List<String> ts = Arrays.asList("GeneratedCriteria", "Criteria", "Criterion");
        return ts.contains(str);
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass,
                                     IntrospectedTable introspectedTable) {
        addClassComment(topLevelClass, introspectedTable, false);
    }


    @Override
    public void addFieldComment(Field field,
                                IntrospectedTable introspectedTable,
                                IntrospectedColumn introspectedColumn) {
        String remarks = introspectedColumn.getRemarks();
        if (true && StringUtility.stringHasValue(remarks)) {
            if (!isLombok) {
                StringBuilder sb = new StringBuilder();
                field.addJavaDocLine("/**");
                sb.append(" * ");
                sb.append(introspectedColumn.getRemarks());
                field.addJavaDocLine(sb.toString().replace("\n", " "));
                field.addJavaDocLine(" */");
            } else {
                field.addJavaDocLine("@ApiModelProperty(\"" + introspectedColumn.getRemarks() + "\")");
            }
        }
    }


    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        String remarks = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        if (true && StringUtility.stringHasValue(remarks)) {
            if (!isLombok) {
                StringBuilder sb = new StringBuilder();
                field.addJavaDocLine("/**");
                sb.append(" * ");
                sb.append(remarks);
                field.addJavaDocLine(sb.toString().replace("\n", " "));
                field.addJavaDocLine(" */");
            }

        }

    }

}
