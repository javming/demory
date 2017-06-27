package com.demo.test;

import com.demo.bean.Book;
import com.demo.bean.ResultBean;
import com.demo.util.HttpUtil;
import com.demo.util.MD5;
import com.demo.util.RedisUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 测试类
 *
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-10-09-16:44
 */
public class ITest {
    private static final char[] chr1 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    private static final char[] chr2 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final char[] chr3 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    /**
     *
     * @param length 字符串长度
     * @param type 字符串类型（1：大小写字母+数字；2小写字母+数字；3大写字母+数字）
     * @return
     */
    public static String getRrandomString(int length,int type){
        char[] chr = chr1;
        int max = 62;
        switch (type){
            case 2:
                chr = chr2;
                max = 36;
                break;
            case 3:
                chr = chr3;
                max = 36;
                break;
            default:break;
        }
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            buffer.append(chr[random.nextInt(max)]);
        }
        return buffer.toString();
    }

    @Test
    public void testR(){
        String s = getRrandomString(16, 1);
        System.out.println(s);
    }


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
        String a= "333333";
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
        String refreshToken = "cd0b56824c008a338faccf68b7a81f31";
        String key = "ac5df60b-486c-429f-b0c3-2b0df19c6e6a0r";
        RedisUtil.setEx(key,600,refreshToken);
        System.out.println("cache success!");
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString().trim().replaceAll("-", ""));
        System.out.println(System.currentTimeMillis());
        String random1 = RandomStringUtils.randomAlphanumeric(16);
        String random2 = RandomStringUtils.randomNumeric(16);
        String random3 = RandomStringUtils.random(16, new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'});
        System.out.println(random1);
        System.out.println(random2);
        System.out.println(random3);
    }

    @Test
    public void testFastJson() throws ParseException {
        String da = "2016-10-15 10:31:29";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        Date parse = simpleDateFormat.parse(da);
        long time = parse.getTime();
        System.out.println(time);
    }

    @Test
    public void testUrlencode(){
        URL resource = getClass().getResource("");
        System.out.println(resource.toString());
    }
    @Test
    public void testHttpUtil(){
        String server = "123.56.252.133:";
        String uri = "22040/getFromRedis";
        Map<String,Object> headers = new HashMap<String,Object>();
        headers.put("token","TKAFF7219D321EF676");
        headers.put("openId","6a471a35-a26c-46e5-aeeb-8ca34748cc34");
        Map<String,Object> body = new HashMap<String,Object>();
        body.put("key","1:4");
        body.put("dbNum",0);
        String res = HttpUtil.httpRest(server ,uri, headers, body, "dsfa");
        System.out.println(res);
    }

}
