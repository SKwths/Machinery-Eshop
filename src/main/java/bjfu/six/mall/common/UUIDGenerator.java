package bjfu.six.mall.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 创建主键
 */
public class UUIDGenerator {
    //62个可打印字符
    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };

    /**
     * 创建用户主键ID
     * @return	返回格式:"yyyyMMddHHmmss"+8位UUID字符
     */
    public static String generateId() {

        Date now = new Date();
        // pattern描述日期和时间格式的格式
        // SimpleDateFormat格式化时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        String id = sdf.format(now) + generateShortUuid();
        return id;
    }

    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = getUUID();
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    /**
     * 生成UUID字符串
     * 例如:2cda7135b9f0416b980a00110ff38d86
     * @return UUID字符串
     */
    public static String getUUID() {
        String str = UUID.randomUUID().toString().replace("-","");
        return str;
    }

    /**
     * 用作SC,StuTopic,TC,TopicPaper,Paper的ID
     * 生成UUID字符串
     * 例如:2cda7135b9f0416b980a00110ff38d86
     * @return UUID字符串
     */
    public static String getRecordID() {
        String str = UUID.randomUUID().toString().replace("-","");
        return str;
    }


    /**
     *用作User即teacher，student的ID
     */
    public static String getUserID()
    {
        return String.valueOf(System.currentTimeMillis());
    }

}