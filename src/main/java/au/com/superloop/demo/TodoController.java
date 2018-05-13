package au.com.superloop.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import java.net.URISyntaxException;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Controller
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping(path="/api/todos")
public class TodoController {

  @Autowired
	private ITodoService todoService;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public ResponseEntity<List<Todo>> findAll() {
    List<Todo> todos = todoService.findAll();
    return ResponseEntity.ok(todos);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Todo> findOne(@PathVariable Long id) {
    try {
      Todo todo = todoService.findById(id);
      return ResponseEntity.ok(todo);  // return 200, with json body
    } catch (ResourceNotFoundException e) {
      //TODO: log exception
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); //return 404, with null body
    }
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity<Void> create(@RequestBody Todo todo) throws URISyntaxException {
      Todo newTodo = todoService.create(todo);
      if (newTodo == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }
      URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newTodo.getId()).toUri();
      return ResponseEntity.created(location).build();
      //TODO: as it's only using a HashMap and incrementing the Id programmatically, can't catch a ResourceAlreadyExistException
      // try {
      //   Todo newTodo = todoService.create(todo);
      //   URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newTodo.getId()).toUri();
      //   return ResponseEntity.created(location).build();
      // } catch (ResourceAlreadyExistException e) {
      // //TODO: log exception
      //  return ResponseEntity.status(HttpStatus.CONFLICT).build();
      // }
  }

  @RequestMapping(value = "", method = RequestMethod.PUT)
  public ResponseEntity<Void> update(@RequestBody Todo todo) {
    try {
      todoService.update(todo);
      return ResponseEntity.noContent().build();
    } catch (ResourceNotFoundException e) {
      //TODO: log exception
      return ResponseEntity.notFound().build();
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> remove(@PathVariable Long id) {
    try {
      todoService.delete(id);
      return ResponseEntity.noContent().build();
    } catch (ResourceNotFoundException e) {
      //TODO: log exception
      return ResponseEntity.notFound().build();
    }
  }

}
