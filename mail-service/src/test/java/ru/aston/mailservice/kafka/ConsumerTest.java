package ru.aston.mailservice.kafka;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.aston.mailservice.service.MailServiceImpl;


import static org.mockito.Mockito.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ActiveProfiles("test")
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:0"})
public class ConsumerTest {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @MockitoBean
    private MailServiceImpl mailService;

    @Test
    public void whenCreateMessageReceived_thenSendAddEmail() throws Exception {
        String email = "test@example.com";

        kafkaTemplate.send("USERS", "CREATE", email);

        verify(mailService, timeout(5000).times(1)).sendEmailAboutAdd(email);
    }

    @Test
    public void whenRemoveMessageReceived_thenSendDeleteEmail() throws Exception {
        String email = "test@example.com";

        kafkaTemplate.send("USERS", "DELETE", email);

        verify(mailService, timeout(5000).times(1)).sendEmailAboutDelete(email);
    }

    @Test
    public void whenUnknownMessageReceived_thenSendDeleteEmail() throws Exception {
        String email = "test@example.com";

        kafkaTemplate.send("USERS", "unk", email);

        verify(mailService, after(5000).never()).sendEmailAboutDelete(email);
        verify(mailService, after(5000).never()).sendEmailAboutAdd(email);
    }
}