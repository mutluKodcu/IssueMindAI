package com.uyg5.dmtbkts.shared.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic issueTopic() {
        return new NewTopic("issue-topic", 1, (short) 1);
    }

    @Bean
    public NewTopic nlpResultTopic() {
        return new NewTopic("nlp-result-topic", 1, (short) 1);
    }

    @Bean
    public NewTopic feedbackTopic() {
        return new NewTopic("feedback-topic", 1, (short) 1);
    }
}
