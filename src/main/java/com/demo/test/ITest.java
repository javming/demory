package com.demo.test;

import com.demo.bean.Book;
import com.demo.bean.ResultBean;
import com.demo.bean.SystemStatus;
import com.demo.util.MD5;
import net.sf.json.JSONObject;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 测试类
 *
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-10-09-16:44
 */
public class ITest {

    @Test
    public void test1(){
        String a = RandomStringUtils.randomAlphanumeric(32);//大小写都有
        String b = RandomStringUtils.random(32);//全部随机啥都有
        String alphabetic = RandomStringUtils.randomAlphabetic(32);
        String ascii = RandomStringUtils.randomAscii(32);
        String numeric = RandomStringUtils.randomNumeric(32);
        System.out.println("a==="+a);
        System.out.println("b==="+b);
        System.out.println("alphabetic==="+alphabetic);
        System.out.println("ascii==="+ascii);
        System.out.println("numeric=="+numeric);
    }
    @Test
    public void test2() {
        File file = new File("src/main/resources/book.xml");//books.xml文件应放在和ReadXMLFile.java同级的文件夹下
        List<Book> books = readXMLFile(file);
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    public List<Book> readXMLFile(File file) {
        List<Book> lists = new ArrayList<Book>();
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            NodeList bookList = doc.getElementsByTagName("book");
            for (int i = 0; i < bookList.getLength(); i++) {
                Node bookNode = bookList.item(i);
                if (bookNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element bookElement = (Element) bookNode;
                    Book book = new Book();
                    book.setCategory(bookElement.getAttribute("category"));
                    Element titleElement = (Element) bookElement.getElementsByTagName("title").item(0);
                    book.setTitle(titleElement.getTextContent());
                    book.setTitleLang(titleElement.getAttribute("lang"));
                    NodeList authorList = bookElement.getElementsByTagName("author");
                    String author = "";
                    for (int j = 0; j < authorList.getLength(); j++) {
                        author = author + authorList.item(j).getTextContent() + "/";
                    }
                    author = author.substring(0, author.length() - 1);
                    book.setAuthor(author);
                    book.setYear(Integer.valueOf(bookElement.getElementsByTagName("year").item(0).getTextContent()));
                    book.setPrice(Double.valueOf(bookElement.getElementsByTagName("price").item(0).getTextContent()));
                    lists.add(book);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    @Test
    public void testMD5(){
        String a= "edewfrefgrtghyjuyjuijuhbfewrtg6yhthbvcxfergtyujhygfdasxzchjtgefdsasvfgfrtgerwefdcervtrgtwvfd";
        long start = System.currentTimeMillis();
        String b = MD5.getMD5(a);
        System.out.println("b spend "+(System.currentTimeMillis()-start)+"ms");
        start = System.currentTimeMillis();
        String c = MD5.stringMD5(a);
        System.out.println("c spend "+(System.currentTimeMillis()-start)+"ms");


        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        System.out.println(b);
        System.out.println(c);
        System.out.println(uuid);
        System.out.println(b.equals(c));
    }

    @Test
    public void testFactory(){
        MyThreadFactory factory = new MyThreadFactory("mythreadFactory");
        Task task = new Task();
        Thread thread;
        for (int i=0;i<10;i++){
            thread = factory.newThread(task);
            thread.start();
        }
        System.out.printf("Factory stats:\n");
        System.out.printf("%s\n",factory.getStats());

    }
    @Test
    public void testJsonObject(){
        ResultBean result = new ResultBean();
        result.setFailMsg(SystemStatus.BAD_REQUEST);
        JSONObject jsonObject = JSONObject.fromObject(result);
        System.out.println(jsonObject);
    }

}
