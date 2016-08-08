package day11;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/8 0008.
 */

public class ParseXMLDemo {
    public static void main(String[] args) {
        try{
			/*
			 *解析XML的大致步骤:
			 * 1:创建DOM4J提供的高级流
			 * SAXReader用于解析xml文档
			 * 2:使用SAXReader读取要解析的文档并返回一个Document对象,该对象即表示解析的文档
			 * 3:通过Document获取根元素
			 * 4:根据XML文档结构通过根元素开始逐级解析,已达到获取XML内容的目的
			 */
            SAXReader reader = new SAXReader();

			/*
			 * Element有很多获取元素的相关内容的方法
			 * String getName()
			 * 获取当前标签的方法
			 *
			 * 获取子元素的方法
			 * Element element(String name)
			 * 获取当前标签中指定名字的子标签
			 *
			 * List element()
			 * 获取当前标签中的所有子标签
			 *
			 * List element(String name)
			 * 获取当前标签下所有同名子标签
			 */

            Document doc = reader.read(new File("emplist.xml"));

            Element root = doc.getRootElement();
            String name = doc.getName();



            List<Element> list = root.elements();

            List<Emp> emplist = new ArrayList<Emp>();

            for(Element empEle : list) {
                Element nameEle = empEle.element("name");
				/*
				* Element 提供的方法:
				* String getText()
				* 可以获取当前标签中间的文本
				*/
                String empName = nameEle.getText();

//				获取名字
//				String empname = empEle.elementText("name");

//				获取年龄
                int age = Integer.parseInt(empEle.elementText("age"));
//				获取性别
                String gender = empEle.elementText("gender");
//				获取工资
                int salary = Integer.parseInt(empEle.elementText("salary"));

//				获取标签中的"id"属性
                Attribute attr = empEle.attribute("id");
                int id = Integer.parseInt(attr.getValue());

                Emp emp = new Emp(id,empName,age,gender,salary);
                emplist.add(emp);
            }
            System.out.println("解析完毕!");
            for	(Emp emp : emplist) {
                System.out.println(emp);
            }

//			for(Element empEle : list) {
//				Element ageEle = empEle.element("age");
//				String empage = ageEle.getText();
//			}
//
//			for(Element empEle : list) {
//				Element genderEle = empEle.element("gender");
//				String empgender = genderEle.getText();
//			}
//
//			for(Element empEle : list) {
//				Element salaryEle = empEle.element("salary");
//				String empsalary = salaryEle.getText();
//			}


        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}
