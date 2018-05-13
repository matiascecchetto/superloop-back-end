package au.com.superloop.demo;

import java.util.List;

public interface ITodoService {

  List<Todo> findAll();
  Todo findById(Long id);
  Todo create(Todo todo);
  void delete(Long id);
  void update(Todo todo);

}
