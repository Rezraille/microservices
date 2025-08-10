package ru.aston.mailservice.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.aston.mailservice.service.MailServiceImpl;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class MailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MailServiceImpl mailService;

    @Test
    public void sendAddEmail_ShouldReturnOk() throws Exception {
        String testEmail = "test@example.com";

        doNothing().when(mailService).sendEmailAboutAdd(testEmail);

        mockMvc.perform(get("/api/mail/sendAdd/{mail}", testEmail))
                .andExpect(status().isOk());

        verify(mailService).sendEmailAboutAdd(testEmail);
    }

    @Test
    public void sendRemoveEmail_ShouldReturnOk() throws Exception {
        String testEmail = "test@example.com";

        doNothing().when(mailService).sendEmailAboutDelete(testEmail);

        mockMvc.perform(get("/api/mail/sendRemove/{mail}", testEmail))
                .andExpect(status().isOk());

        verify(mailService).sendEmailAboutDelete(testEmail);
    }
}