package Producer;

import Utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import static Content.Content.QUEUE_NAME;


public class Producer1 {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        RabbitMqUtils rabbitMqUtils = new RabbitMqUtils();
        Channel channel = rabbitMqUtils.getChannel();

        // 1.隊列名稱
        // 2.是否進行持久化
        // 3.該隊列是否只供一個消費者消費（是否共享）
        // 4.是否自動刪除
        // 5.其他參數
        assert channel != null;
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World";

        // 1.發送到哪個交換機
        // 2.路由的key值是哪個，本次是隊列的名稱
        // 3.其他參數
        // 4.消息體
        int index = 0;
        while (true) {
            String message_n = "Hello World " + index;
            channel.basicPublish("", QUEUE_NAME, null, message_n.getBytes(StandardCharsets.UTF_8));
            System.out.println(index + " Success");
            Thread.sleep(5000);
            index ++ ;
        }
    }
}
