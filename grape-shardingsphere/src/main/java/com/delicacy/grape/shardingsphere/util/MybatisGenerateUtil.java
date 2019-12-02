package com.delicacy.grape.shardingsphere.util;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.*;


public class MybatisGenerateUtil {
    /**
     * 开发人员设置
     */
    // 当前项目路径
    private static Map<String, String> tableMap = new HashMap<>();
    private static boolean enableLombok = false;
    private static boolean enableExample = true;

    static {
        tableMap.put("t_order", "Order");
        tableMap.put("t_order_item", "OrderItem");
    }
    // 加上service包的上层目录路径，例如：com.wy.imc.base
    private static String javaRootPath = "com.delicacy.grape.shardingsphere";
    /**
     * 配置管理员设置
     */
    // 默认mysql，如果是mysql可以不用设置这个变量 com.mysql.cj.jdbc.Driver
    private static String driverClass = "com.mysql.cj.jdbc.Driver";
    // 数据库访问地址
    private static String connectionURL = "jdbc:mysql://192.168.189.142:3306/test?serverTimezone=GMT&useInformationSchema=true";
    // 指定哪个库
    private static String catelog = null;
    // 数据库用户名
    private static String userId = "root";
    // 数据库密码
    private static String password = "123456";
    // Java模型生成的路径，例如：com.easyrong.domain
    private static String modelTargetPackage = javaRootPath; //+ ".dto";
    // map文件生成的路径。默认在resources，如果配置mybatis，标识生成到resources/mybatis下
    private static String mapTargetPackage = "mapper";
    // dao文件生成的路径，例如：com.easyrong.dao
    private static String iDaoTargetPackage = javaRootPath + ".mapper";
    // 如果是模块划分，则需要加上模块名字
    private static String module = "";
    private static String projectPath = new File(MybatisGenerateUtil.class.getResource("/").getPath()).getAbsolutePath().replace("\\target\\classes", "");
    private static String packagePath = MybatisGenerateUtil.class.getName().substring(0, MybatisGenerateUtil.class.getName().lastIndexOf(".")+1);
    private static String configurationType = packagePath+"MyCommentGenerator";
    private static String PaginationPlugin=packagePath+"PaginationPlugin";
    private static String OverIsMergeablePlugin = packagePath+"OverIsMergeablePlugin";
    //是否加入子包
    private static String enableSubPackages = "false";

    public static void main(String[] args) {
        MybatisGenerateUtil.doGenerateCode();
    }

    public static void doGenerateCode() {
        try {
            Configuration config = new Configuration();
            Context context = new Context(null);
            context.setId("MysqlTables");
            context.setTargetRuntime("MyBatis3");
            // 设置公共的配置
            doCommentGenerator(context);
            // 设置数据库
            doJdbcConnection(context);
            // 设置Java类型解析器
            doJavaTypeResolver(context);
            // 设置Java模型
            doJavaModelGenerator(context, "domain");
            // 设置Map文件
            doSqlMapGenerator(context);
            // 设置dao
            doJavaClientGenerator(context);
            // 设置table
            doTable(context);
            // 设置plugin
            doPlugin(context);
            config.addContext(context);
            DefaultShellCallback shellCallback = new DefaultShellCallback(true);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, shellCallback, new ArrayList<>());
            myBatisGenerator.generate(null, new HashSet(), new HashSet());
            System.out.println("表：" + tableMap.keySet().toString() + ",自动生成代码成功~!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void doPlugin(Context context) {
        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setConfigurationType(PaginationPlugin);
        context.addPluginConfiguration(pluginConfiguration);
        pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setConfigurationType(OverIsMergeablePlugin);
        context.addPluginConfiguration(pluginConfiguration);

    }

    private static void doJavaTypeResolver(Context context) {
        JavaTypeResolverConfiguration resolver = new JavaTypeResolverConfiguration();
        resolver.getProperties().setProperty("forceBigDecimals", "false");
        context.setJavaTypeResolverConfiguration(resolver);
    }

    private static void doCommentGenerator(Context context) {
        CommentGeneratorConfiguration common = new CommentGeneratorConfiguration();
        Properties properties = common.getProperties();
        //div Comment class
        common.setConfigurationType(configurationType);
        properties.setProperty("suppressDate", "true");
        // 是否去除自动生成的注释 true：是 ： false:否
        properties.setProperty("suppressAllComments", "true");
        properties.setProperty("dateFormat", "yyyy-MM-dd");
        properties.setProperty("isLombok", enableLombok?"true":"false");
        context.setCommentGeneratorConfiguration(common);

    }

    private static void doTable(Context context) {
        List<TableConfiguration> list = context.getTableConfigurations();
        list.clear();
        if (tableMap != null && !tableMap.isEmpty()) {
            Set<Map.Entry<String, String>> entries = tableMap.entrySet();
            for (Map.Entry<String, String> e : entries) {
                TableConfiguration tableConfiguration = new TableConfiguration(context);
                tableConfiguration.setSchema(catelog);
                tableConfiguration.setCatalog(catelog);
                tableConfiguration.setTableName(e.getKey());
                String bean = e.getValue() == null ? tableToBean(e.getKey()) : e.getValue();
                tableConfiguration.setDomainObjectName(bean);
                tableConfiguration.setCountByExampleStatementEnabled(enableExample);
                tableConfiguration.setUpdateByExampleStatementEnabled(enableExample);
                tableConfiguration.setDeleteByExampleStatementEnabled(enableExample);
                tableConfiguration.setSelectByExampleStatementEnabled(enableExample);
                list.add(tableConfiguration);
            }
        }
    }

    private static String tableToBean(String tableName) {
        int flag = tableName.indexOf("_");
        if (flag > 0) {
            tableName = tableName.toLowerCase();
            String[] tableStr = tableName.split("_");
            String result = "";
            int index = 0;
            for (String tableTemp : tableStr) {
                index++;
                if (1 == index) {
                    continue;
                }
                result += tableTemp.substring(0, 1).toUpperCase() + tableTemp.substring(1);
            }
            return result;
        } else {
            return tableName.substring(0, 1).toUpperCase() + tableName.substring(1);
        }
    }

    private static void doJavaClientGenerator(Context context) {
        JavaClientGeneratorConfiguration dao = new JavaClientGeneratorConfiguration();
        if (module != null && !"".equals(module)) {
            dao.setTargetPackage(iDaoTargetPackage + "." + module);
        } else {
            dao.setTargetPackage(iDaoTargetPackage);
        }
        dao.setConfigurationType("XMLMAPPER");
        if (projectPath != null && !"".equals(projectPath)) {
            dao.setTargetProject(projectPath + "/src/main/java");
        } else {
            dao.setTargetProject("src/main/java");
        }

        dao.getProperties().setProperty("enableSubPackages", enableSubPackages);
        context.setJavaClientGeneratorConfiguration(dao);
    }

    private static void doSqlMapGenerator(Context context) {
        SqlMapGeneratorConfiguration sql = new SqlMapGeneratorConfiguration();
        sql.setTargetPackage(mapTargetPackage);
        if (projectPath != null && !"".equals(projectPath)) {
            sql.setTargetProject(projectPath + "/src/main/resources");
        } else {
            sql.setTargetProject("src/main/resources");
        }
        sql.getProperties().setProperty("enableSubPackages", enableSubPackages);
        context.setSqlMapGeneratorConfiguration(sql);
    }

    private static void doJavaModelGenerator(Context context, String packagestr) {
        JavaModelGeneratorConfiguration java = new JavaModelGeneratorConfiguration();
        modelTargetPackage = javaRootPath + "." + packagestr;
        if (module != null && !"".equals(module)) {
            java.setTargetPackage(modelTargetPackage + "." + module);
        } else {
            java.setTargetPackage(modelTargetPackage);
        }
        if (projectPath != null && !"".equals(projectPath)) {
            java.setTargetProject(projectPath + "/src/main/java");
        } else {
            java.setTargetProject("src/main/java");
        }
        java.getProperties().setProperty("enableSubPackages", enableSubPackages);
        java.getProperties().setProperty("trimStrings", "true");
        context.setJavaModelGeneratorConfiguration(java);
    }

    private static void doJdbcConnection(Context context) {
        JDBCConnectionConfiguration jdbc = new JDBCConnectionConfiguration();
        if (connectionURL != null && !"".equals(connectionURL)) {
            jdbc.setConnectionURL(connectionURL);
        }
        if (driverClass != null && !"".equals(driverClass)) {
            jdbc.setDriverClass(driverClass);
        }
        if (password != null && !"".equals(password)) {
            jdbc.setPassword(password);
        }
        if (userId != null && !"".equals(userId)) {
            jdbc.setUserId(userId);
        }
        context.setJdbcConnectionConfiguration(jdbc);
    }


}
