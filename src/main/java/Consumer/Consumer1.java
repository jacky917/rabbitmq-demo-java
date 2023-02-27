package Consumer;

import Utils.RabbitMqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

import static Content.Content.QUEUE_NAME;

/**
 * 自動應答
 */
public class Consumer1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtils.getChannel();

        // 接收消息
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("C1 : " + new String(message.getBody()));
        };

        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println("取消消費");
            System.out.println(consumerTag);
        };

        // 1.消費哪個隊列
        // 2.消費成功後是否要自動應答
        // 3.消費者未成功消費的回調
        // 4.消費者取消消費的回調
        assert channel != null;
        String s = channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
    }
}
