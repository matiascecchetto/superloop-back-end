package au.com.superloop.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

import java.util.HashMap;
import java.util.Map;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import org.springframework.stereotype.Service;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

@Service
public class TodoService implements ITodoService {

  private Map<Integer, Todo> todos = new HashMap<Integer, Todo>();

  public TodoService() {
    loadDummyData();
  }

  public List<Todo> findAll() {
    return new ArrayList<Todo>(todos.values());
  }

  public Todo findById(Long todoId) throws ResourceNotFoundException {
    for (Todo todo : todos.values()) {
      if (todo.getId().equals(todoId)) {
        return todo;
      }
    }
    throw new ResourceNotFoundException();
  }

  //TODO: as it's only using a HashMap and incrementing the Id programmatically, can't throw a ResourceAlreadyExistException
  public Todo create(Todo newTodo) {
    if (newTodo != null && newTodo.getName() != null && newTodo.getDueDate() != null) {
      newTodo.setId(Collections.max(todos.keySet()) + 1);
      if (newTodo.getDescription() == null) {
        newTodo.setDescription("");
      }
      todos.put(newTodo.getId().intValue(), newTodo);
      return newTodo;
    }
    return null;
  }

  public void update(Todo updatedTodo) throws ResourceNotFoundException {
    if (updatedTodo != null && (updatedTodo.getName() != null && updatedTodo.getName() != "") && (updatedTodo.getDueDate() != null)) {
      for (Todo todo : todos.values()) {
        if (todo.getId().equals(updatedTodo.getId())) {
          todos.put(todo.getId().intValue(), updatedTodo);
          return;
        }
      }
    }
    throw new ResourceNotFoundException();
  }

  public void delete(Long id) {
    for (Todo todo : todos.values()) {
      if (todo.getId().equals(id)) {
        todos.remove(todo.getId().intValue());
        return;
      }
    }
    throw new ResourceNotFoundException();
  }

  private void loadDummyData() {
    try {
      Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-11");
      Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-13");
      Date date3 = new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-21");
      Date date4 = new SimpleDateFormat("yyyy-MM-dd").parse("2018-04-01");
      Date date5 = new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-10");
      Date date6 = new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-11");
      Todo todo1 = new Todo(1L, "Plan something", "This is a simple TODOs", date1);
      Todo todo2 = new Todo(2L, "Do something else", "This is a another simple TODOs", date2);
      Todo todo3 = new Todo(3L, "Test something", "This is yet another simple TODOs", date3);
      Todo todo4 = new Todo(4L, "Maybe code something", "This is the real challange", date4);
      todo4.setStatus(true);
      Todo todo5 = new Todo(5L, "Have a coffee", "This is called a break or code with your cup", date5);
      Todo todo6 = new Todo(6L, "Go for a run", "This is called exercise", date6);
      todos.put(1, todo1);
      todos.put(2, todo2);
      todos.put(3, todo3);
      todos.put(4, todo4);
      todos.put(5, todo5);
      todos.put(6, todo6);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

}
