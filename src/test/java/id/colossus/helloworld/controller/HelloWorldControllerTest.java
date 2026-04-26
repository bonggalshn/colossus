package id.colossus.helloworld.controller;

import id.colossus.helloworld.facade.HelloWorldFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class HelloWorldControllerTest {

    private MockMvc mockMvc;

    @Mock
    private HelloWorldFacade facade;

    @BeforeEach
    void setUp() {
        HelloWorldController controller = new HelloWorldController(facade);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void helloWorld_withoutName_returnsDefaultGreeting() throws Exception {
        when(facade.helloWorld(null))
                .thenReturn(id.colossus.helloworld.commonlang.ApiResponse.success("Hello World", null));

        mockMvc.perform(get("/api/hello"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Hello World"))
                .andExpect(jsonPath("$.status").value("success"));
    }

    @Test
    void helloWorld_withName_returnsPersonalizedGreeting() throws Exception {
        when(facade.helloWorld("John"))
                .thenReturn(id.colossus.helloworld.commonlang.ApiResponse.success("Hello John", null));

        mockMvc.perform(get("/api/hello").param("name", "John"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Hello John"))
                .andExpect(jsonPath("$.status").value("success"));
    }

    @Test
    void helloWorld_withEmptyName_returnsDefaultGreeting() throws Exception {
        when(facade.helloWorld(""))
                .thenReturn(id.colossus.helloworld.commonlang.ApiResponse.success("Hello World", null));

        mockMvc.perform(get("/api/hello").param("name", ""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello World"));
    }
}