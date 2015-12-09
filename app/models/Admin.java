package models;

import com.avaje.ebean.Model;
import org.mindrot.jbcrypt.BCrypt;
import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by alex_alarcon on 12/8/2015.
 */

@Table(name="administrators")
@Entity
public class Admin extends Model {
    @Id
    public Long id;

    @Constraints.Required
    @Column(unique=true)
    public String userName;

    public String password_hash;

    // A finder object for easier querying
    public static Finder<Long, Admin> find = new Finder<Long, Admin>(Admin.class);

    public Admin(String userName, String password_hash) {
        this.userName = userName;
        this.password_hash = password_hash;
    }

    public boolean authenticate(String password) {
        return BCrypt.checkpw(password, this.password_hash);
    }

    public static Admin createAdmin(String userName, String password) {
        Admin admin = new Admin(userName, password);

        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());

        admin.userName = userName;
        admin.password_hash = passwordHash;

        return admin;
    }



}
