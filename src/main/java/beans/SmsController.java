/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author buddhika
 */
@Named
@SessionScoped
public class SmsController implements Serializable {

    private String userName;
    private String password;
    private String userAlias;
    private String number;
    private String message;
    private String output;
    private String promo;

    /**
     * Creates a new instance of SmsController
     */
    public SmsController() {
    }

    public void sentHutchSms(){
        // System.out.println("sentHutchSms");
        if(message==null || message.trim().equals("")){
            // System.out.println("no message");
            output = "No Message";
            return;
        }
        if(number==null || number.trim().equals("")){
            // System.out.println("no number");
            output = "Number";
            return;
        }
//        
//        
//        Ex:
//https://bulksms.hutch.lk/sendsmsmultimask.php?USER=username&PWD=password&MASK=CallerLine
//Id&NUM=DNumber&MSG=message
        
        
userName="ArogyaHCS";
userAlias="AROGYA LAB";
password="eVog@737";

/**
         * 
         * 
         * https://bulksms.hutch.lk/sendsmsmultimask.php?USER=ArogyaHCS&PWD=password&MASK=AROGYA%20LAB&NUM=DNumber&MSG=message
         * http://bulksms.hutch.lk/sendsmsmultimask.php?USER=ArogyaHCS&PWD=eVog@737&MASK=AROGYA%20LAB&NUM=DNumber&MSG=message
         * https://bulksms.hutch.lk/sendsmsmultimask.php?USER=ArogyaHCS&PWD=eVog@737&MASK=AROGYA%20LAB&NUM=0715812399&MSG=Test
         * http://bulksms.hutch.lk/sendsmsmultimask.php?USER=ArogyaHCS&PWD=eVog@737&MASK=AROGYA%20LAB&NUM=0715812399&MSG=Test
         */

        Map<String, String> m = new HashMap();
        m.put("PWD", password);
        m.put("USER", userName);
        m.put("MASK", userAlias);
        m.put("NUM", number);
        m.put("MSG", message);
        String res = executePost("https://bulksms.hutch.lk/sendsmsmultimask.php", m);
        // System.out.println("res = " + res);
        if (res == null) {
            output = "";
        } else if (res.toUpperCase().contains("200")) {
            output="200";
        } else {
            output=res;
        }
    }
    
    
    public String executePost(String targetURL, Map<String, String> parameters) {
        // System.out.println("executePost");
        // System.out.println("targetURL = " + targetURL);
        // System.out.println("parameters = " + parameters);
        HttpURLConnection connection = null;
        if (parameters != null && !parameters.isEmpty()) {
            targetURL += "?";
        }
        Set s = parameters.entrySet();
        Iterator it = s.iterator();
        while (it.hasNext()) {
            Map.Entry m = (Map.Entry) it.next();
            String pVal;
            try {
                pVal = java.net.URLEncoder.encode(m.getValue().toString(), "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                pVal = "";
                // System.out.println("ex = " + ex);
            }
            String pPara = (String) m.getKey();
            targetURL += pPara + "=" + pVal.toString() + "&";
        }


        try {
            //// System.out.println("targetURL = " + targetURL);
            //Create connection
            //Create connection
            //Create connection
            //Create connection
            //Create connection
            //Create connection
            //Create connection
            //Create connection
            // System.out.println("1");
            URL url = new URL(targetURL);
            // System.out.println("2");
            connection = (HttpURLConnection) url.openConnection();
            // System.out.println("3");
            connection.setRequestMethod("GET");
            // System.out.println("4");
            connection.setDoOutput(true);
            // System.out.println("4");
            //Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            // System.out.println("5");
            wr.writeBytes(targetURL);
            // System.out.println("6");
            wr.flush();
            // System.out.println("wr = " + wr);
            // System.out.println("7");
            wr.close();
            // System.out.println("8");
            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            // System.out.println("e = " + e.getMessage());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    
    public void sendSms() {
        // System.out.println("sendSms");
        if (userName == null || password == null || userAlias == null || number == null
                || message == null) {
            output = "failure";
            // System.out.println("SMS sending failure. No sufficient data provided.");
            return;
        }

        String baseUrl = "http://smeapps.mobitel.lk:8585/EnterpriseSMSV3/esmsproxyURL.php";

        
        
        
        
        Map<String, String> m = new HashMap();
        m.put("username", userName);
        m.put("password", password);
        m.put("from", userAlias);
        m.put("to", number);
        m.put("text", message);
        m.put("messageType", "1");
        String json = "{"
                + "&quot;username&quot;:&quot;" + userName + "&quot;,"
                 + "&quot;password&quot;:&quot;" + password + "&quot;,"
                 + "&quot;from&quot;:&quot;" + userAlias + "&quot;,"
                 + "&quot;to&quot;:&quot;" + number + "&quot;,"
                 + "&quot;text&quot;:&quot;" + message + "&quot;,"
                 + "&quot;messageType&quot;:&quot;" + "1" + "&quot;,"
                + "}";
        String ret = executePostWithJson(baseUrl, json);
        try {
            if (ret.contains("200")) {
                output = "success 200";

            } else {
                output = "failure " + ret;
            }
            // System.out.println("output = " + output);
            return;
        } catch (Exception ex) {
            output = "failure " + ex.getMessage();
            // System.out.println("ex.getMessage() = " + ex.getMessage());
            return;
        }
    }

    public String executePostWithJson(String url, String json) {
        // System.out.println("executePostWithJson");
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            String JSON_STRING=json;
            HttpEntity stringEntity = new StringEntity(JSON_STRING,ContentType.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);
            CloseableHttpResponse response2 = httpclient.execute(httpPost);
            // System.out.println("response2 = " + response2);
            // System.out.println("response2 = " + response2.toString());
            return response2.toString();
        } catch (IOException ex) {
            Logger.getLogger(SmsController.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }

  
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

}
