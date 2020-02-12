package tutoriales.rabbitmq.spring.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class EmisorGonza {

    private final static String QUEUE_NAME = "MAIN_QUEUE";

    public static void main(String[] args) throws IOException,
            TimeoutException {

        /*
        Por un lado tenemos el establecimiento de la conexión y del canal de comunicaciones. Para ello hacemos uso de una factoría de conexiones,
        establecemos el Host, generamos una nueva conexión y creamos un nuevo canal de comunicaciones a través de la conexión.
         */
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        /*
        Por otro lado, establecemos las propiedades del canal y lo utilizamos para publicar un mensaje en el mismo.
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "¡Hola desde emisor Gonza!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Enviar '" + message + "'");

    }
}
