package com.demo.xml;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhaoming
 * @create 2017-04-05
 */
public class Routable implements Serializable{


    private static final long serialVersionUID  = -1563920797266872071L;

    private static List<RouterInfo> routerInfos       = new ArrayList<RouterInfo>();

    private static Routable instance = null;

    private String oauthAddress = "http://192.168.2.252:8501";

    private final static String ROUTER_TABLE_FILE = Config.getBaseString("rootable");

    private static

    public static RouterTable getInstance() throws IOException {
        if (instance == null) {
            try {
                File loadFile = new File(ROUTER_TABLE_FILE);
                if (!loadFile.exists()) {
                    log.info("路由表文件不存在！");
                    instance = new RouterTable();
                    instance.save();
                } else {
                    log.info("实例化路由表配置...");
                    XMLDecoder decoder = new XMLDecoder(new FileInputStream(loadFile));
                    instance = (RouterTable) decoder.readObject();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private void save() throws IOException {
        File file = new File(ROUTER_TABLE_FILE);
        if (!file.exists()) {
            file.createNewFile();
        }
        XMLEncoder encoder = new XMLEncoder(new FileOutputStream(file));
        encoder.writeObject(this);
        encoder.close();
    }

    public static void reload() {
        try {
            XMLDecoder decoder = new XMLDecoder(new FileInputStream(ROUTER_TABLE_FILE));
            instance = null;
            instance = (RouterTable) decoder.readObject();
        } catch (Exception e) {
            log.error(e.toString(), e);
        }
    }

    public static RouterInfo getByRequestURL(String requestURL, String mothed) {
        for (RouterInfo router : routerInfos) {
            Pattern p = Pattern.compile(router.getRequestURL());
            Matcher m = p.matcher(requestURL);
            log.debug("regex validate  : " + m.matches());
            if (m.matches() && mothed.equals(router.getRequestMothed())) {
                log.debug("requestURL is : " + requestURL);
                if (router.getReplaceRegex() != null && (!"".equals(router.getReplaceRegex()))) {
                    Pattern pa = Pattern.compile(router.getReplaceRegex());
                    Matcher ma = pa.matcher(router.getMappingURL());
                    String temp = ma.replaceAll(requestURL);
                    log.debug("MappingURL is :  " + temp);
                    router.setMappingURL(temp);
                } else {
                    return router;
                }
                return router;
            }
        }
        return null;
    }

    public List<RouterInfo> getRouterInfos() {
        Iterator<RouterInfo> iter = routerInfos.iterator();
        while (iter.hasNext()) {
            RouterInfo r = iter.next();

        }
        return routerInfos;
    }

    public void setRouterInfos(List<RouterInfo> routerInfos) {
        this.routerInfos = routerInfos;
    }

    public String getOauthAddress() {
        return oauthAddress;
    }

    public void setOauthAddress(String oauthAddress) {
        this.oauthAddress = oauthAddress;
    }
}
