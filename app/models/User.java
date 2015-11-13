package models;

import com.avaje.ebean.Model;
import org.mindrot.jbcrypt.BCrypt;
import play.data.validation.Constraints;

import com.avaje.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Constraint;

@Entity
public class User extends Model {
  @Id
  private String id;

  @Constraints.Required
  private String userName;

  @Constraints.Required
  private String password_hash;

  @Constraints.Required
  private String firstName;

  @Constraints.Required
  private String lastName;

  @Constraints.Required
  private String address;

  @Constraints.Required
  private String phoneNum;

  private String email;

  // A finder object for easier querying
  private static Finder<Long, User> find = new Finder<Long, User>(User.class);


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
