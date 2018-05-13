package au.com.superloop.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.context.embedded.LocalServerPort;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TodoControllerTest {

    @LocalServerPort
    private int port;

    @MockBean
    private TodoService todoService;

    @Autowired
    private TestRestTemplate restTemplate;

    private URL baseURL;

    HttpHeaders headers = new HttpHeaders();

    @Before
    public void setUp() throws Exception {
      this.baseURL = new URL("http://localhost:" + port + "/v1/api/todos");
    }

    @Test
    public void contextLoads() throws Exception {
      assertThat(todoService).isNotNull();
    }

    @Test
    public void canRetrieveTodoByIdWhenExists() throws Exception {
      // given
      given(todoService.findById(1L))
              .willReturn(new Todo(1L, "Plan something'", "This is a simple TODOs", new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-10")));

      // when
      ResponseEntity<Todo> response = restTemplate.getForEntity(this.baseURL.toString() + "/1", Todo.class);

      // then
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
      assertThat(response.getBody().equals(new Todo(1L, "Plan something", "This is a simple TODOs", new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-10"))));
    }

    @Test
    public void cannotRetrieveTodoByIdWhenDoesNotExist() {
      // given
      given(todoService.findById(9999L))
              .willThrow(new ResourceNotFoundException());

      // when
      ResponseEntity<Todo> response = restTemplate.getForEntity(this.baseURL.toString() + "/9999", Todo.class);

      // then
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
      assertThat(response.getBody()).isNull();
    }

    @Test
    public void canUpdateATodo() throws Exception {
      // given
      given(todoService.findById(1L))
            .willReturn(new Todo(1L, "Plan something'", "This is a simple TODOs", new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-10")));

      HttpEntity<Todo> entity = new HttpEntity<>(new Todo(1L, "Plan something different", "This is a simple TODO", new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-15")));

      // when
      ResponseEntity<Todo> response = restTemplate.exchange(this.baseURL.toString(), HttpMethod.PUT, entity, Todo.class);

      // then
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
      assertThat(response.getBody()).isNull();
      assertThat(todoService.findById(1L).getName().equals("Plan something different"));
      }

    // TODO: find out what's happening with this test
    // @Test
    // public void canCreateANewTodo() throws Exception {
    //   // given
    //   HttpEntity<Todo> entity = new HttpEntity<>(new Todo("Plan something", "This TODO", new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-03")));
    //
    //   // when
    //   ResponseEntity<Todo> response = restTemplate.exchange(this.baseURL.toString(), HttpMethod.POST, entity, Todo.class);
    //
    //   // then
    //   assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    //   assertThat(response.getBody()).isNotNull();
    //   assertThat(response.getBody().getName().equals("Plan something"));
    // }

    @Test
    public void canDeleteTodo() throws Exception {
      // given
      HttpEntity<String> entity = new HttpEntity<String>(null, headers);

      // when
      ResponseEntity<Todo> response = restTemplate.exchange(this.baseURL.toString() + "/1", HttpMethod.DELETE, entity, Todo.class);

      // then
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


  }
