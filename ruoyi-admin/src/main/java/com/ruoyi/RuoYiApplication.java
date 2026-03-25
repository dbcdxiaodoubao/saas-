package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author ruoyi
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RuoYiApplication {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  码上教学服务启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "                      _                              _                       _             \n" +
                "                     | |                            | |                     (_)            \n" +
                "  _ __ ___   __ _ ___| |__   __ _ _ __   __ _    ___| | ___  __ _ _ __ _ __  _ _ __   __ _ \n" +
                " | '_ ` _ \\ / _` / __| '_ \\ / _` | '_ \\ / _` |  / _ \\ |/ _ \\/ _` | '__| '_ \\| | '_ \\ / _` |\n" +
                " | | | | | | (_| \\__ \\ | | | (_| | | | | (_| | |  __/ |  __/ (_| | |  | | | | | | | | (_| |\n" +
                " |_| |_| |_|\\__,_|___/_| |_|\\__,_|_| |_|\\__, |  \\___|_|\\___|\\__,_|_|  |_| |_|_|_| |_|\\__, |\n" +
                "                                         __/ |                                        __/ |\n" +
                "                                        |___/                                        |___/ ");
    }
}
