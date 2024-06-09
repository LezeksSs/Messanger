import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.MessangerServer.controller.AuthController;
import com.example.MessangerServer.dto.SingUpRequest;
import com.example.MessangerServer.service.AuthenticationService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthController authController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void signUpUser_Success() throws Exception {
        SingUpRequest singUpRequest =
                new SingUpRequest("TestUser", "TestUser@mail.com", "SECRET");
        String json = objectMapper.writeValueAsString(singUpRequest);

        String expectedToken = "TEST";
        Map<String, String> response = new HashMap<>();
        response.put("token", expectedToken);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<?> responseEntity = new ResponseEntity<>(
                response,
                header,
                HttpStatus.OK
        );

        when(authenticationService.signUp(singUpRequest)).then(Answer -> responseEntity);

        ResultActions responses = mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json));

        responses.andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(jsonPath("$.token", CoreMatchers.is("TESTt")));

        verify(authenticationService, times(1)).signUp(any(SingUpRequest.class));
    }

    @Test
    public void signUpUser_UserAlreadyExists() throws Exception {
        SingUpRequest singUpRequest =
                new SingUpRequest("TestUser", "TestUser@mail.com", "SECRET");
        String json = objectMapper.writeValueAsString(singUpRequest);


        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<?> responseEntity = new ResponseEntity<>(
                "User with that nickname or email already exist",
                header,
                HttpStatus.BAD_REQUEST
        );

        when(authenticationService.signUp(singUpRequest)).then(Answer -> responseEntity);

        ResultActions responses = mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        responses.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("", CoreMatchers.is("User with that nickname or email already exist")));

        verify(authenticationService, times(1)).signUp(any(SingUpRequest.class));
    }

}
