package alex.valker91;

import jakarta.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class MyConfig {

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;
    @Value("${spring.activemq.user}")
    private String brokerUsername;
    @Value("${spring.activemq.password}")
    private String brokerPassword;

    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory activeMQFactory = new ActiveMQConnectionFactory();
        activeMQFactory.setBrokerURL(brokerUrl);
        activeMQFactory.setUserName(brokerUsername);
        activeMQFactory.setPassword(brokerPassword);
        return new CachingConnectionFactory(activeMQFactory);
    }

    @Bean
    public JmsTemplate queueJmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate template = new JmsTemplate(connectionFactory);
        template.setPubSubDomain(false);
        return template;
    }
}
