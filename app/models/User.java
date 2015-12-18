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
import javax.persistence.OneToMany;
import java.util.List;


@Table(name="users")
@Entity
public class User extends Model {
  @Id
  public Long id;

  @Constraints.Required
  @Column(unique=true)

  public String userName;

  @Constraints.Required
  public String password_hash;

  @Constraints.Required
  public String firstName;

  @Constraints.Required
  public String lastName;

  public String address;

  public String phoneNum;

  public String email;

  //public List<User> userList;

  @OneToMany
  public List<Tool> toolList;

  // A finder object for easier querying
  public static Finder<Long, User> find = new Finder<Long, User>(User.class);

  public String getUserName(){
    return userName;
  }

  public boolean authenticate(String password) {
    return BCrypt.checkpw(password, this.password_hash);
  }

  public static User createNewUser(String userName, String password, String firstname, String lastname, String email, String address, String phonenum) {
  	if(password == null || userName == null || firstname == null || lastname == null || phonenum == null ||
            userName.length() < 4 || password.length() < 8 || firstname.length() == 0 || firstname.length() < 2 ||
            lastname.length() == 0 || lastname.length() < 2) {
   		return null;
    }

    // Create a password hash
    String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());

    User user = new User();
    user.userName = userName;
    user.password_hash = passwordHash;
    user.firstName = firstname;
    user.lastName = lastname;
    user.phoneNum = phonenum;

    return user;
    }


}
