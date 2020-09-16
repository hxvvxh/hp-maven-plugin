package message.dto;

import java.io.Serializable;

/**
 * @author hp
 * @version 1.0
 * @date 2020/9/16 22:03
 */
public class MessageDto implements Serializable {
    private String name;
    private String path;
    private String sha;
    private String size;
    private String url;
    private String html_url;
    private String git_url;
    private String download_url;
    private String type;
    private String content;
    private String encoding;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getGit_url() {
        return git_url;
    }

    public void setGit_url(String git_url) {
        this.git_url = git_url;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", sha='" + sha + '\'' +
                ", size='" + size + '\'' +
                ", url='" + url + '\'' +
                ", html_url='" + html_url + '\'' +
                ", git_url='" + git_url + '\'' +
                ", download_url='" + download_url + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", encoding='" + encoding + '\'' +
                '}';
    }
}
