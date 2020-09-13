# hp-maven-plugin

## maven 插件学习

(0)执行 mvn clean install 将插件打入本地maven仓库

(1)引用maven 插件
`
    <build>
        <plugins>
            <plugin>
                <groupId>com.hp.plugin</groupId>
                <artifactId>hpHello-maven-plugin</artifactId>
                <version>1.0-SNAPSHOT</version>
                <executions>
                    <execution>
                        <!-- 作用域，表示在package阶段之后的作用域才会生效：package,compile,install,deploy -->
                        <phase>package</phase>
                        <goals>
                            <goal>hp</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
`

(2)执行命令
    mvn install 或mvn install -Dhp.say=hello word
(3)查看结果
   控制台会显示
   `[INFO] --- hpHello-maven-plugin:1.0-SNAPSHOT:hp (default) @ apache-springboot ---
    [INFO] hello hp plugin:default say`     
    或
    `[INFO] --- hpHello-maven-plugin:1.0-SNAPSHOT:hp (default) @ apache-springboot ---
     [INFO] hello hp plugin:say`