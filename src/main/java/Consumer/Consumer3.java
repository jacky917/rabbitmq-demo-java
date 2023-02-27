package Consumer;

import Utils.RabbitMqUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.concurrent.TimeoutException;

import static Content.Content.QUEUE_NAME;

/**
 * 手動應答
 */
public class Consumer3 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtils.getChannel();

        // 接收消息
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            assert channel != null;

            RabbitMqUtils.mySleep(1000);

            System.out.println("C2 : " + new String(message.getBody()));
//            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
//            channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
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
        String s = channel.basicConsume(QUEUE_NAME, false, deliverCallback, cancelCallback);
    }
}
