package com.example.demo.controller;

import com.example.demo.model.SendRedPack;
import com.example.demo.util.XMLUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wangyong on 2018/04/03.
 */
@RequestMapping("/hello")
@RestController
public class HelloController {

    @RequestMapping("/demo/{fix}/print")
    public Map<String, String> helloPrint(@PathVariable("fix") String fix) throws Exception {
        Map<String, String> result = new HashMap<>();
        if (fix == null || fix.isEmpty()) {
            throw new Exception("fix不能为空");
        }
        result.put("print", fix + "-:打印成功");
        System.out.println(fix + "-:打印成功");
        return result;
    }

    @RequestMapping("/demo/{fix}/send/redPack")
    public Map<String, String> sendRedPack(String re_openid, int total_amount) throws Exception{
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String str = sdf.format(date);

        SendRedPack sendRedPack = new SendRedPack();
        // 随机字符串
        sendRedPack.setNonce_str(UUID.randomUUID().toString().toUpperCase());
        // 商户号
        sendRedPack.setMch_id("10033011");
        // 商户订单号
        sendRedPack.setMch_billno("10033011" + str);
        // 公众账号 wxb3ecaaf52e687a1c wx459cf45f993f6f58
        sendRedPack.setWxappid("wxb3ecaaf52e687a1c");
        // 商户名称
        sendRedPack.setSend_name("上海博卡");
        // 红包发放总人数
        sendRedPack.setTotal_num(1);
        // 活动名称
        sendRedPack.setAct_name("股东卡分红");
        //红包祝福语
        sendRedPack.setWishing("谢谢您的推荐");
        //备注
        sendRedPack.setRemark("股东型会员卡");
        sendRedPack.setClient_ip("8.8.8.8");

        sendRedPack.setRe_openid(re_openid);
        sendRedPack.setTotal_amount(total_amount);
        String sign = createSendRedPackOrderSign(sendRedPack);
        sendRedPack.setSign(sign);

        XMLUtil xmlUtil= new XMLUtil();
        xmlUtil.xstream().alias("xml", sendRedPack.getClass());
        String xml = xmlUtil.xstream().toXML(sendRedPack);
        //红包回退地址
        String response = ssl("http://www.shboka.com/", xml);
        Map<String, String> map = xmlUtil.parseXml(response);
        return map;
    }
    /**
      * 发送请求
      * */
    private String ssl(String url,String data){
        StringBuffer message = new StringBuffer();
        try {
            // 商户号
            String mchId = "10033011";
            KeyStore keyStore  = KeyStore.getInstance("PKCS12");
            String certFilePath = "/opt/node/front/cert/10033011.p12";
            FileInputStream instream = new FileInputStream(new File(certFilePath));
            keyStore.load(instream, mchId.toCharArray());
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mchId.toCharArray()).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            HttpPost httpost = new HttpPost(url);
            httpost.addHeader("Connection", "keep-alive");
            httpost.addHeader("Accept", "*/*");
            httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpost.addHeader("Host", "api.mch.weixin.qq.com");
            httpost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpost.addHeader("Cache-Control", "max-age=0");
            httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            httpost.setEntity(new StringEntity(data, "UTF-8"));
            System.out.println("executing request" + httpost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpost);
            try {
                HttpEntity entity = response.getEntity();
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    System.out.println("Response content length: " + entity.getContentLength());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                       message.append(text);
                    }
                }
                EntityUtils.consume(entity);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                response.close();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return message.toString();
    }

    /**
     * 生成签名
     * @param redPack
     * @return
     */
    public String createSendRedPackOrderSign(SendRedPack redPack){
        StringBuffer sign = new StringBuffer();
        sign.append("act_name=").append(redPack.getAct_name());
        sign.append("&client_ip=").append(redPack.getClient_ip());
        sign.append("&mch_billno=").append(redPack.getMch_billno());
        sign.append("&mch_id=").append(redPack.getMch_id());
        sign.append("&nonce_str=").append(redPack.getNonce_str());
        sign.append("&re_openid=").append(redPack.getRe_openid());
        sign.append("&remark=").append(redPack.getRemark());
        sign.append("&send_name=").append(redPack.getSend_name());
        sign.append("&total_amount=").append(redPack.getTotal_amount());
        sign.append("&total_num=").append(redPack.getTotal_num());
        sign.append("&wishing=").append(redPack.getWishing());
        sign.append("&wxappid=").append(redPack.getWxappid());
        //注：key为商户平台设置的密钥key
        sign.append("&key=").append("6a1cb2c716cd4b8a9e4f4cd1e01809f6");
        return DigestUtils.md5Hex(sign.toString()).toUpperCase();
    }
}
