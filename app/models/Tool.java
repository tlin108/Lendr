package models;

import com.avaje.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tool extends Model {
  @Id
  String id;
  String name;
  String description;
  String owner;
  String borrower;

  // A finder object for easier querying
  public static Finder<Long, Tool> find = new Finder<Long, Tool>(Tool.class);
}
