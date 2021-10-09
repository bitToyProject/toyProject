package kr.bora.api.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Producer {
//    private static final String TOPIC = "test-events";
//
//    @Autowired
//    private KafkaProducer<String,String> kafkaProducer;
//
//    public void sendMessage(String message){
//        log.info("##### -> Producing message -> {}", message);
//        this.kafkaProducer.send(TOPIC,message);
//    }
}
