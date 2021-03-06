package message;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import message.dto.MessageDto;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.springframework.util.AntPathMatcher;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hp
 * @version 1.0
 * @date 2020/9/13 21:10
 *
 *@Mojo( name = "<goal-name>",
 *        aggregator = <false|true>,
 *        configurator = "<role hint>",
 *        // 执行策略
 *        executionStrategy = "<once-per-session|always>",
 *        inheritByDefault = <true|false>,
 *        // 实例化策略
 *        instantiationStrategy = InstantiationStrategy.<strategy>,
 *        // 如果用户没有在POM中明确设置此Mojo绑定到的phase，那么绑定一个MojoExecution到那个phase
 *        defaultPhase = LifecyclePhase.<phase>,
 *        requiresDependencyResolution = ResolutionScope.<scope>,
 *        requiresDependencyCollection = ResolutionScope.<scope>,
 *        // 提示此Mojo需要被直接调用（而非绑定到生命周期阶段）
 *        requiresDirectInvocation = <false|true>,
 *        // 提示此Mojo不能在离线模式下运行
 *        requiresOnline = <false|true>,
 *        // 提示此Mojo必须在一个Maven项目内运行
 *        requiresProject = <true|false>,
 *        // 提示此Mojo是否线程安全，线程安全的Mojo支持在并行构建中被并发的调用
 *        threadSafe = <false|true> ) // (since Maven 3.0)
 *
 *
 *     @Parameter( name = "parameter",
 *                 // 在POM中可使用别名来配置参数
 *                 alias = "myAlias",
 *                 property = "a.property",
 *                 defaultValue = "an expression, possibly with ${variables}",
 *                 readonly = <false|true>,
 *                 required = <false|true> )
 */
@Mojo(name = "Message")
public class Hpmessage extends AbstractMojo {

    private static ThreadLocal<Map<String,String>> message= ThreadLocal.withInitial(HashMap::new);

    private Map<String, String> enToCn = new HashMap<>();

    @Parameter(property = "Message.token")
    private String token;
    @Parameter(property = "Message.branch", defaultValue = "master")
    private String branch;


    @Parameter(defaultValue = "${project.groupId}")
    private String groupId;

    @Parameter(defaultValue = "${project.artifactId}")
    private String artifactId;

    @Parameter(defaultValue = "${project.version}")
    private String version;

    @Parameter(property = "project.build.outputDirectory")
    private File outputDirectory;

    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        StringBuilder sb = new StringBuilder();
        sb.append("hp")
                .append(":")
                .append("groupId:")
                .append(groupId)
                .append(" artifactId:")
                .append(artifactId)
                .append(" version:")
                .append(version);
        this.getLog().info("========================================");
        this.getLog().info("==============HP Plugin==============");
        this.getLog().info("========================================");
        this.getLog().info(sb.toString());
        String uri=null;
        String content=null;
        if (null == token) {
            getLog().error("Message token is null please check");
        } else {
            //根据token获取github上的仓库及分支信息
            String url = "https://api.github.com/repos/hxvvxh/hp-maven-plugin/contents/hp-message/CN_EN_message.properties?ref=" + branch;
            uri=url;
            getLog().info("try to github api with url:" + url + "\\n" + " token:" + token);
            //http调用，转换成JSON 获取content信息 转换成map
            MessageDto dto = get(url);
            Map<String, String> map = caseContentToMap(dto.getContent());
            content=dto.getContent();
            message.set(map);
            getLog().info("cast message toMap:" + message.get());
            //调用springboot 国际化信息转换LocaleResolver
        }


//        /**
//         * 以下是将github上读取的文件，存放到target中
//         */
//        File f = this.outputDirectory;
//        if (!f.exists() && !f.mkdirs()) {
//            throw new RuntimeException("Cannot create folder " + f);
//        } else {
//            URL url;
//            URLConnection connection;
//            try {
//                url = new URL(uri);
//                connection = url.openConnection();
//                connection.setRequestProperty("Authorization",token);
//                connection.setRequestProperty("Accept","application/vnd.github.v3+json");
//                connection.setDoInput(true);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//            TarArchiveInputStream tar;
//            try {
//                InputStream inputStream=connection.getInputStream();
//                GZIPInputStream gzip = new GZIPInputStream(inputStream);
//                tar = new TarArchiveInputStream(gzip);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//            try {
//                TarArchiveEntry entry = tar.getNextTarEntry();
//                if (null == entry) {
//                    entry = tar.getNextTarEntry();
//                }
//                for (; null != entry; entry = tar.getNextTarEntry()) {
//                    String name = entry.getName();
//                    boolean cont = false;
//                    Iterator var11;
//                    String include;
//                    if (!cont) {
//                        File target;
//                        if (entry.isDirectory()) {
//                        } else {
//                            if (name.indexOf(47) > -1) {
//                                name = name.substring(name.lastIndexOf(47));
//                            }
//                            target = new File(f, name);
//                            this.getLog().info("Entry " + entry.getName() + " has been extracted to " + target);
//                            StreamUtils.copy(tar, new FileOutputStream(target));
//                        }
//                    } else {
//                        this.getLog().info("included : " + name);
//                    }
//
//                }
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }

    }

    private MessageDto get(String url) {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet(url);
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();

            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);
            httpGet.setHeader("Authorization", token);
            httpGet.setHeader("Accept", "application/vnd.github.v3+json");

            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);

            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                String str=EntityUtils.toString(responseEntity);
                System.out.println("响应内容为:" + str);
                JSON.parse(str);
                MessageDto dto= JSON.parseObject(str, MessageDto.class);
                getLog().info("MessageDto :"+dto.toString());
                return dto;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    private Map<String,String> caseContentToMap(String content){
        Base64.Decoder base64 = Base64.getDecoder();
        Map<String,String> map=new HashMap<>();
        try {
            String s=content.replaceAll("\r|\n","");
            String str = new String(base64.decode(s), "UTF-8");
            if (str.endsWith("\n")){
                str=str.substring(0,str.length()-2);
            }
            map= Splitter.on("\n").withKeyValueSeparator("=").split(str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return map;
    }

}
