package fr.paul.corswithspring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CorsHttpRequestTests {

    @Autowired
    private WebApplicationContext wac;

    public MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        DefaultMockMvcBuilder builder = MockMvcBuilders
                .webAppContextSetup(this.wac)
                .dispatchOptions(true);
        this.mockMvc = builder.build();
    }

    @ParameterizedTest
    @ValueSource(strings = {"http://localhost:2000", "http://localhost:2050", "http://localhost:3000", "http://localhost:1234", "toto"})
    void publicGetBooksControllerShouldBeAccessibleFromAnyOrigins(String origin) throws Exception {
        this.mockMvc
                .perform(get("/public-api/books")
                        .header("Origin", origin))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(strings = {"http://localhost:2000", "http://localhost:2050", "http://localhost:3000", "http://localhost:1234", "toto"})
    void publicGetBookControllerShouldBeAccessibleFromAnyOrigins(String origin) throws Exception {
        this.mockMvc
                .perform(get("/public-api/book/1")
                        .header("Origin", origin))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(strings = {"http://localhost:3000", "http://localhost:5000"})
    void rateInfBookControllerShouldBeAccessibleFromSomeOrigins(String origin) throws Exception {
        this.mockMvc
                .perform(get("/api/rateInf/4")
                        .header("Origin", origin))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(strings = {"http://localhost:2000", "toto"})
    void rateInfBookControllerShouldNotBeAccessibleFromSomeOrigins(String origin) throws Exception {

        this.mockMvc
                .perform(get("/api/rateInf/4")
                    .header("Origin", origin))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @ParameterizedTest
    @ValueSource(strings = {"http://localhost:3000", "http://localhost:5000"})
    void rateEqBookControllerShouldBeAccessibleFromAnyLocalHost(String origin) throws Exception {
        this.mockMvc
                .perform(get("/api/rateEq/4")
                        .header("Origin", origin))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(strings = {"http://domain1", "http://fr.ippon.tech"})
    void rateEqBookControllerShouldNotBeAccessibleFromAnyOtherThanLocalHost(String origin) throws Exception {
        this.mockMvc
                .perform(get("/api/rateEq/4")
                        .header("Origin", origin))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void rateSupBookControllerShouldBeAccessibleFromLocalHost3000() throws Exception {
        this.mockMvc
                .perform(get("/api/rateSup/2")
                        .header("Origin", "http://localhost:3000"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(strings = {"http://domain1", "http://fr.ippon.tech", "http://localhost:1234", "toto"})
    void rateSupBookControllerShouldNotBeAccessibleFromAnyOtherThanLocalHost3000(String origin) throws Exception {
        this.mockMvc
                .perform(get("/api/rateSup/2")
                        .header("Origin", origin))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @ParameterizedTest
    @ValueSource(strings = {"http://localhost:1000", "http://localhost:5000", "http://localhost:2000"})
    void confBookControllerShouldBeAccessibleFromWhitelistAddr() throws Exception {
        this.mockMvc
                .perform(get("/api/rateSup/2")
                        .header("Origin", "http://localhost:3000"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(strings = {"http://domain1", "http://fr.ippon.tech", "http://localhost:1234", "toto"})
    void confBookControllerShouldNotBeAccessibleFromAnyOtherThanWhitelistAddr(String origin) throws Exception {
        this.mockMvc
                .perform(get("/api/rateSup/2")
                        .header("Origin", origin))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}
