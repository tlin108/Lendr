package models;

import com.avaje.ebean.Model;
import org.mindrot.jbcrypt.BCrypt;
import play.data.validation.Constraints;

import com.avaje.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Constraint;
import javax.persistence.Table;
import javax.persistence.Column;


@Table(name="users")
@Entity
public class User extends Model {
  @Id
  public String id;

  @Constraints.Required
  @Column(unique=true)
  public String userName;

  @Constraints.Required
  public String password_hash;

  @Constraints.Required
  public String firstName;

  @Constraints.Required
  public String lastName;

  @Constraints.Required
  public String address;

  @Constraints.Required
  public String phoneNum;

  @Constraints.Required
  public String email;

  // A finder object for easier querying
  public static Finder<Long, User> find = new Finder<Long, User>(User.class);

  public String getUserName(){
    return userName;
  }

  public String getID(){
    return id;
  }

  // NOT FOR PRODUCTION - must ensure this is a valid user first. I have not done that.
  public boolean authenticate(String password) {
      return BCrypt.checkpw(password, this.password_hash);
  }

  public static User createNewUser(String userName, String password) {
  	if(password == null || userName == null || userName.length() < 4 || password.length() < 8 ) {
   		return null;
    }

    // Create a password hash
    String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());

    User user = new User();
    user.userName = userName;
    user.password_hash = passwordHash;

    return user;
    }

}
