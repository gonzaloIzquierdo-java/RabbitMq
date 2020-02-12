package tutoriales.rabbitmq.spring.demo;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class Receptor {
    private final static String QUEUE_NAME = "MAIN_QUEUE";



    public static void main(String[] args) throws IOException,
            TimeoutException, ShutdownSignalException,
            ConsumerCancelledException, InterruptedException {
        /*
        Como en el caso anterior, en la primera sección de código tenemos el establecimiento de la conexión y del canal de comunicaciones.
         */
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        /*
        configuramos el canal de comunicaciones e instanciamos un consumidor que va a ser el encargado de obtener la información del canal.
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] A la espera de mensajes. Para salir pulse: CTRL+C");
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, true, consumer);
        /*
        establecemos un bucle que se encargue de estar a la escucha, constantemente, de mensajes enviados al canal. Hemos introducido un método «doWork» que
         simplemente emula tareas internas del hilo
         */
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Recibido: '" + message + "'");
            doWork(message);
            System.out.println(" [x] Hecho!!! ");
        }

    }

    private static void doWork(String task) throws InterruptedException {
        Thread.sleep(8000);
    }
}
