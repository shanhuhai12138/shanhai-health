package com.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 山海健康 - 启动程序
 *
 * @author shanhuhai12138
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class ShanHaiApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ShanHaiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  山海健康启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  _ _   \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  _. /  '       \n" +
                " |(_ o _) /        _( )_ .'         \n" +
                " | (_,_).' __  ___(_ o _)'          \n" +
                " |  |\\ \\  |  ||   |(_,_)'         \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              \n");
    }
}
