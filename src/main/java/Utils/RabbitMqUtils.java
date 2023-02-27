package Utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqUtils {
    public static Channel getChannel() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("127.0.0.1");
            factory.setUsername("admin");
            factory.setPassword("123456");
            Connection connection = factory.newConnection();
            return connection.createChannel();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void mySleep(int s){
        try{
            Thread.sleep(s * 1000L);
        }catch (InterruptedException t) {
            t.printStackTrace();
        }
    }
}
