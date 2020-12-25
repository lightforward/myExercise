package com.example.gen;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wns
 * @create 2020年12月25日16:34:40
 */
public class MybatisPlusGenerator {

    public static void main(String[] args) {

        // 1.配置数据源：
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setUrl("jdbc:mysql://192.190.30.116:3306/tmc_db?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");

        // 2.设置一些全局的配置：
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");// 输出目录
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setAuthor("wns");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");

        // 3.生成策略配置：
        StrategyConfig strategy = new StrategyConfig();
        //strategy.setTablePrefix(new String[] { "SYS_" });// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 生成类需要继承的父类
        strategy.setSuperEntityClass("com.baomidou.mybatisplus.extension.activerecord.Model");
//        strategy.setSuperControllerClass("com.baomidou.demo.TestController");
//        strategy.setSuperServiceClass("com.baomidou.demo.TestService");
//        strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
//        strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
        // 需要生成的表
        strategy.setInclude(new String[]{"sp_check_notebook"});

        // 4.生成文件所在包配置：
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.example");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setEntity("model");

        // 5.xml文件配置：
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-rb");
                this.setMap(map);
            }
        };
        //xml生成路径
        List<FileOutConfig> focList = new ArrayList<>();
        // 调整 xml 生成目录演示
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // XML生成路径
                return projectPath + "/src/main/java/com/example/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);

        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        AutoGenerator mpg = new AutoGenerator();
        mpg.setDataSource(dsc);         //数据源配置
        mpg.setGlobalConfig(gc);        //全局配置
        mpg.setStrategy(strategy);      //生成策略配置
        mpg.setPackageInfo(pc);         //包配置
        mpg.setCfg(cfg);                //xml配置
        mpg.setTemplate(tc);
        // 执行生成
        mpg.execute();

    }

}
 