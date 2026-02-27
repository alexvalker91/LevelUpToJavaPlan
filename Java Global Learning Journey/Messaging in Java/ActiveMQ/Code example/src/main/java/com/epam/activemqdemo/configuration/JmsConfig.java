package com.epam.activemqdemo.configuration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class JmsConfig {

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Value("${spring.activemq.user}")
    private String brokerUsername;

    @Value("${spring.activemq.password}")
    private String brokerPassword;

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setPassword(brokerPassword);
        connectionFactory.setUserName(brokerUsername);
        return connectionFactory;
    }

    @Bean
    @Qualifier("queueJmsTemplate")
    public JmsTemplate jmsQueueTemplate(ActiveMQConnectionFactory connectionFactory) {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory);
        return template;
    }

    @Bean
    @Qualifier("topicJmsTemplate")
    public JmsTemplate jmsTopicTemplate(ActiveMQConnectionFactory connectionFactory) {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory);
        template.setPubSubDomain(true);
        return template;
    }

//    Что конкретно означает template.setPubSubDomain(true);?
//    По умолчанию (false): Если pubSubDomain установлено в false (что является значением по умолчанию),
//    то JmsTemplate будет работать в режиме "point-to-point", а это означает, что сообщения будут отправляться
//    в очереди. В этом режиме каждое сообщение доставляется одному получателю.

//    Когда pubSubDomain = true: Если значение установлено в true, то JmsTemplate будет использовать топики,
//    соответствующие модели publish/subscribe (публикация/подписка).
//    В этом режиме каждое сообщение может быть доставлено нескольким получателям,
//    которые подписаны на данный топик.

    @Bean(name = "queueListenerFactory")
    public DefaultJmsListenerContainerFactory jmsListenerQueueContainerFactory(
            ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean(name = "topicListenerFactory")
    public DefaultJmsListenerContainerFactory jmsListenerTopicContainerFactory(
            ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }

//    Строка factory.setPubSubDomain(true); в конфигурации DefaultJmsListenerContainerFactory конкретно настраивает,
//    что слушатели (@JmsListener), которые будут использовать данную фабрику, должны работать в режиме
//    Publish/Subscribe. Это то же самое, что переключить фабрику в режим работы с топиками (topics),
//    а не с очередями (queues).

//    Ключевая идея:
//    В JMS (Java Message Service) есть два типа взаимодействия:

//    1) Очереди (point-to-point):
//    Сообщение отправляется в очередь (queue), где оно может быть получено одним (и только одним) потребителем.
//    Это типичная модель взаимодействия "отправитель-один-получатель".
//    2) Топики (publish/subscribe):
//    Сообщение публикуется в топик (topic), а уже несколько подписчиков одновременно (если они есть) могут его получить.
//    Это подход для широковещательной рассылки: одно сообщение распространяется на всех активных подписчиков.
}
