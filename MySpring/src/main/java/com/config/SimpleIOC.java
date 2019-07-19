package com.config;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xxy
 * @date 2019/6/28
 * @description
 */
public class SimpleIOC {
    private Map<String,Object> beanMap = new HashMap<>();
    public SimpleIOC(String location) throws Exception{
        loadBeans(location);
    }
    public Object getBean(String name){
        Object bean = beanMap.get(name);
        if(bean==null){
            throw new IllegalArgumentException("there is no bean with name " + name);
        }
        return bean;
    }
    private void loadBeans(String location) throws Exception{
        // 加载xml配置文件
        InputStream inputStream = new FileInputStream(location);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document doc = docBuilder.parse(inputStream);
        Element root = doc.getDocumentElement();
        NodeList nodes = root.getChildNodes();

        // 遍历<bean>标签
        for(int i = 0;i<nodes.getLength();i++){
            Node node  = nodes.item(i);
            if(node instanceof Element){
                Element ele = (Element) node;
                String id = ele.getAttribute("id");
                String className = ele.getAttribute("class");
                Class beanClass = null;
                try{
                    // 加载beanClass
                    beanClass = Class.forName(className);
                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                    return;
                }
                // 创建 bean
                Object bean = beanClass.newInstance();
                // 遍历<property> 标签
                NodeList propertyNodes = ele.getElementsByTagName("property");
                for(int j = 0;j<propertyNodes.getLength();j++){
                    Node propertyNode = propertyNodes.item(j);
                    if(propertyNode instanceof Element){
                        Element propertyElement = (Element) propertyNode;
                        String name = propertyElement.getAttribute("name");
                        String value = propertyElement.getAttribute("value");
                        // 利用反射机制将bean相关字段访问权限设为可访问
                        Field declaredFiled =  bean.getClass().getDeclaredField(name);
                        declaredFiled.setAccessible(true);
                        if(value!=null&&value.length()>0){
                            declaredFiled.set(bean,value);
                        }else{
                            String ref = propertyElement.getAttribute("ref");
                            if(ref==null||ref.length()==0){
                                throw new IllegalArgumentException("ref config error");
                            }
                            declaredFiled.set(bean,getBean(ref));
                        }
                        registerBean(id,bean);
                    }
                }
            }
        }
    }
    private void registerBean(String id,Object bean){
        beanMap.put(id,bean);
    }
}
