import java.io.UnsupportedEncodingException;
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
            String s="aHAudGVzdD3mtYvor5UKaHAudGVzdDI95rWL6K+VMgo=\n";
            s=s.replaceAll("\r|\n","");
            String str = new String(base64.decode(s), "UTF-8");
            System.out.println(str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
