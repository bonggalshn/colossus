package id.colossus.helloworld.controller;

import id.colossus.helloworld.facade.HelloWorldFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MethodNotAllowedTest {

    private MockMvc mockMvc;

    @Mock
    private HelloWorldFacade helloWorldFacade;

    @Test
    void helloWorld_postMethod_returns405() throws Exception {
        when(helloWorldFacade.helloWorld(null))
                .thenReturn(id.colossus.helloworld.commonlang.ApiResponse.success("Hello World", null));

        HelloWorldController controller = new HelloWorldController(helloWorldFacade);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/hello"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void helloWorld_putMethod_returns405() throws Exception {
        HelloWorldController controller = new HelloWorldController(helloWorldFacade);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.put("/api/hello"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void helloWorld_deleteMethod_returns405() throws Exception {
        HelloWorldController controller = new HelloWorldController(helloWorldFacade);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/hello"))
                .andExpect(status().isMethodNotAllowed());
    }
}