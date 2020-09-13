package com.hp.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * @author hp
 * @version 1.0
 * @date 2020/9/13 13:29
 */

@Mojo(name = "hp")
public class Hp extends AbstractMojo {

    @Parameter(property = "hp.say",defaultValue = "default say")
    private String say;
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("hello hp plugin:"+say);
    }
}
