package com.kalayciburak.corepackage.util.broker.kafka;

import com.kalayciburak.corepackage.util.event.Event;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@RequiredArgsConstructor
public class BaseProducer {
    private static final Logger log = LoggerFactory.getLogger(BaseProducer.class);
    private final KafkaTemplate<String, Object> template;

    /**
     * Verilen event'ı belirtilen topic'e gönderir.
     *
     * @param event Gönderilecek event.
     * @param topic Mesajın gönderileceği topic.
     */
    public <T extends Event> void sendMessage(T event, String topic) {
        log.info(String.format("%s event => %s", topic, event.toString()));
        Message<T> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();

        template.send(message);
    }
}
