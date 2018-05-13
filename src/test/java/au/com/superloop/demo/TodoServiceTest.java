package au.com.superloop.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TodosApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoServiceTest {

    @Autowired
    private TodoService todoService;

    @Test
    public void contextLoads() throws Exception {
        assertThat(todoService).isNotNull();
    }

    @Test
    public void canCreateANewTodo() throws Exception {
        // given
        Todo newTodo = new Todo("Plansomething", "ThisTODO", new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-03"));

        // when
        Todo createdTodo = todoService.create(new Todo("Plansomething", "ThisTODO", new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-03")));

        // then
        assertThat(createdTodo.getName()).isEqualTo(newTodo.getName());
        assertThat(createdTodo.getDescription()).isEqualTo(newTodo.getDescription());
        assertThat(createdTodo.getDueDate()).isEqualTo(newTodo.getDueDate());
    }

    @Test
    public void cannotCreateANewTodoWhenPassingNull() throws Exception {
        // given
        // passing value null

        // when
        Todo createdTodo = todoService.create(null);

        // then
        assertThat(createdTodo).isNull();
    }

    @Test
    public void cannotCreateANewTodoWhenNameIsNull() throws Exception {
        // given
        Todo newTodo = new Todo("Plansomething", "ThisTODO", new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-03"));
        newTodo.setName(null);

        // when
        Todo createdTodo = todoService.create(newTodo);

        // then
        assertThat(createdTodo).isNull();
    }

    @Test
    public void cannotCreateANewTodoWhenDueDateIsNull() throws Exception {
        // given
        Todo newTodo = new Todo("Plansomething", "ThisTODO", new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-03"));
        newTodo.setDueDate(null);

        // when
        Todo createdTodo = todoService.create(newTodo);

        // then
        assertThat(createdTodo).isNull();
    }

}
