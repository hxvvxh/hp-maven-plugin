import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hp
 * @version 1.0
 * @date 2020/9/13 21:10
 */
@Mojo(name = "Message")
public class Hpmessage extends AbstractMojo {

    private Map<String,String> enToCn=new HashMap<>();

    @Parameter(property = "Message.token")
    private String token;
    @Parameter(property = "Message.branch",defaultValue = "master")
    private String branch;
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        if (null == token){
            getLog().error("Message token is null please check");
        }
        else{
            //根据token获取github上的仓库及分支信息
            String url="https://api.github.com/repos/hxvvxh/hp-maven-plugin/contents/hp-message/CN_EN_message.properties?ref="+branch;
            getLog().info("try to github api with url:"+url+"\\n"+" token:"+token);
            //http调用，转换成JSON 获取content信息 转换成map

            //调用springboot 国际化信息转换LocaleResolver
        }
    }
}
