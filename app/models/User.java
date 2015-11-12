package models;

import com.avaje.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User extends Model {
  @Id
  private String id;
  private String userName;
  private String firstName;
  private String lastName;
  private String address;
  private String phoneNum;

}
