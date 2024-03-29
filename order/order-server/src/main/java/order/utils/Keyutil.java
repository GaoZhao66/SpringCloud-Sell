package order.utils;


import lombok.Data;

import java.util.Random;

@Data
public class Keyutil {

    /**
     * 生成唯一主键
     * 格式：时间 + 随机数
     * */

    public static synchronized String genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }

}
