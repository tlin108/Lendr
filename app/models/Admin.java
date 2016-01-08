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

public class Admin extends Model {
    public String userName;

    public String password_hash;

    public Admin(String userName, String password_hash) {
        this.userName = userName;

        String passwordHash = BCrypt.hashpw(password_hash, BCrypt.gensalt());
        this.password_hash = passwordHash;
    }

    public boolean authenticate(String password) {
        return BCrypt.checkpw(password, this.password_hash);
    }

}
