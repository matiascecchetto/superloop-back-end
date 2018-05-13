package au.com.superloop.demo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Todo {

  private Long id;
  private String name;
  private String description;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date dueDate;
  private boolean status;

  public Todo() {}

  public Todo(Long id, String name, String description, Date dueDate) {
    super();
    this.id = id;
    this.name = name;
    this.description = description;
    this.dueDate = dueDate;
    this.status = false;
  }

  public Todo(String name, String description, Date dueDate) {
    super();
    this.name = name;
    this.description = description;
    this.dueDate = dueDate;
    this.status = false;
  }

  public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

  public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

  public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

  public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

  public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
