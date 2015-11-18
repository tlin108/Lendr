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
import java.util.List;


@Table(name="users")
@Entity
public class User extends Model {
  @Id
  private Long id;

  @Constraints.Required
  @Column(unique=true)
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

  @Constraints.Required
  private String email;

  //public List<User> userList;

  // A finder object for easier querying
  public static Finder<Long, User> find = new Finder<Long, User>(User.class);

  //Get methods
  public Long getId() { return id; }
  public String getUserName(){
    return userName;
  }
  public String getPassword_hash() { return password_hash; }
  public String getFirstName() { return firstName; }
  public String getLastName() {return lastName; }
  public String getAddress() { return address; }
  public String getPhoneNum() { return phoneNum; }
  public String getEmail() { return email; }

  // NOT FOR PRODUCTION - must ensure this is a valid user first. I have not done that.
  public boolean authenticate(String password) {
      return BCrypt.checkpw(password, this.password_hash);
  }


  public static User createNewUser(String userName, String password, String firstname, String lastname, String email, String address, String phonenum) {
  	if(password == null || userName == null || firstname == null || lastname == null || phonenum == null ||
            userName.length() < 4 || password.length() < 8 || firstname.length() == 0 || firstname.length() < 2 ||
            lastname.length() == 0 || lastname.length() < 2 || phonenum.length() == 0 || phonenum.length() < 10) {
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
