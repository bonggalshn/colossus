package id.colossus.helloworld.controller;

import id.colossus.helloworld.facade.HealthCheckFacade;
import id.colossus.helloworld.facade.HelloWorldFacade;
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
class AcceptanceScenarioTest {

    private MockMvc mockMvc;

    @Mock
    private HelloWorldFacade helloWorldFacade;

    @Mock
    private HealthCheckFacade healthCheckFacade;

    @Test
    void us1_helloWorld_returnsDefaultGreeting() throws Exception {
        when(helloWorldFacade.helloWorld(null))
                .thenReturn(id.colossus.helloworld.commonlang.ApiResponse.success("Hello World", null));

        HelloWorldController controller = new HelloWorldController(helloWorldFacade);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/api/hello"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Hello World"))
                .andExpect(jsonPath("$.status").value("success"));
    }

    @Test
    void us2_helloWorldWithName_returnsPersonalizedGreeting() throws Exception {
        when(helloWorldFacade.helloWorld("John"))
                .thenReturn(id.colossus.helloworld.commonlang.ApiResponse.success("Hello John", null));

        HelloWorldController controller = new HelloWorldController(helloWorldFacade);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/api/hello").param("name", "John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello John"));
    }

    @Test
    void us3_healthCheck_returnsHealthyStatus() throws Exception {
        when(healthCheckFacade.getHealthStatus())
                .thenReturn(id.colossus.helloworld.commonlang.ApiResponse.success("Health check successful",
                        java.util.Map.of("status", "healthy", "version", "1.0.0")));

        HealthCheckController controller = new HealthCheckController(healthCheckFacade);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("success"));
    }
}