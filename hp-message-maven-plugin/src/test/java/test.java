import com.google.common.base.Splitter;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Map;

/**
 * @author hp
 * @version 1.0
 * @date 2020/9/13 21:04
 */
public class test {

    public static void main(String[] args) {
        Base64.Decoder base64 = Base64.getDecoder();
        try {
            String s="aHAubWVzc2FnZT1xd2UKaHAubWVzc2FnZTI9MTIK\n";
            s=s.replaceAll("\r|\n","");
            String str = new String(base64.decode(s), "UTF-8");
            if (str.endsWith("\n")){
                str=str.substring(0,str.length()-2);
            }
            Map<String,String> map=Splitter.on("\n").withKeyValueSeparator("=").split(str);
            System.out.println(str);
            System.out.println(map);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
