import jdk.nashorn.internal.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

/**
 * @author hp
 * @version 1.0
 * @date 2020/9/13 21:04
 */
public class test {

    public static void main(String[] args) {
        Base64.Decoder base64 = Base64.getDecoder();
        try {
            String str = new String(base64.decode(""), "UTF-8");
            System.out.println(str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        String url = "https://api.github.com/repos/hxvvxh/hp-maven-plugin/contents/hp-message/CN_EN_message.properties?ref=master";
        try {
            URL uri = new URL(url);
            URLConnection conn = uri.openConnection();
            conn.setRequestProperty("Authorization", "2466dc723708d0ff74dbe779dd29c70a0be01272");
            conn.setRequestProperty("Content-Type", "application/Json; charset=UTF-8");
            conn.setRequestProperty("Accept", "application/vnd.github.v3+json");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setConnectTimeout(5000);
            conn.connect();
            //服务器返回东西了，先对响应码判断
            StringBuffer result =new StringBuffer();
            BufferedReader in = null;
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                if (null != line){
                    result.append(line);
                }
            }
            System.out.println(JSONParser.quote(result.toString()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
