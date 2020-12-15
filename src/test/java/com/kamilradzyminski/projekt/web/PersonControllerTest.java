package com.kamilradzyminski.projekt.web;

import com.kamilradzyminski.projekt.dto.PersonEditRequest;
import com.kamilradzyminski.projekt.dto.PersonRequest;
import com.kamilradzyminski.projekt.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationContext.class, PersonController.class, PersonServiceImpl.class})
@WebAppConfiguration
@WebMvcTest
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void homepage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void persons() throws Exception {
        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk())
                .andExpect(view().name("persons"));
    }

    @Test
    public void personForm() throws Exception {
        mockMvc.perform(get("/persons/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("personForm"));
    }

    @Test
    public void personFormEdit() throws Exception {
        mockMvc.perform(get("/persons/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("404"));
    }

    @Test
    public void personFormSubmit() throws Exception {
        PersonRequest personRequest = PersonRequest.builder().firstName("Johny").lastName("Deep").email("johny@deep.com").gender("Male").creditCardNumber("678912345").creditCardType("visa").build();
        mockMvc.perform(post("/persons/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .flashAttr("newPerson", personRequest)
        ).andExpect(view().name("redirect:/persons"));
    }

    @Test
    public void personFormEditSubmit() throws Exception {
        PersonEditRequest personEditRequest = PersonEditRequest.builder().firstName("Johny").lastName("Deep").email("johny@deep.com").gender("Malee").creditCardNumber("678912345").creditCardType("visa").build();
        mockMvc.perform(post("/persons/edit/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .flashAttr("oldPerson", personEditRequest)
        ).andExpect(view().name("personFormEdit"));
    }

    @Test
    public void personRemoveUser() throws Exception {
        mockMvc.perform(get("/persons/remove/2"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/persons"));
    }

    @Test
    public void searchPerson() throws Exception {
        mockMvc.perform(get("/persons/search"))
                .andExpect(status().isOk())
                .andExpect(view().name("personSearch"));
    }

    @Test
    public void searchPersonResult() throws Exception {
        mockMvc.perform(get("/persons/search"))
                .andExpect(status().isOk())
                .andExpect(view().name("personSearch"));
    }

    @Test
    public void settings() throws Exception {
        mockMvc.perform(get("/settings"))
                .andExpect(status().isOk())
                .andExpect(view().name("settings"));
    }

    @Test
    public void personStatistics() throws Exception {
        mockMvc.perform(get("/persons/statistics"))
                .andExpect(status().isOk())
                .andExpect(view().name("personStatistics"));
    }
}
