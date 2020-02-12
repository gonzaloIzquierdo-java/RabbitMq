package tutoriales.rabbitmq.spring.demo;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("TERMINO DE ARRANCAR");

    }

}
