package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
public class ToolCategory extends Model {
    @Id
    public Long id;

    @Constraints.Required
    public String name;

    @OneToMany
    public List<Tool> toolList;

    // A finder object for easier querying
    public static Finder<Long, ToolCategory> find = new Finder<Long, ToolCategory>(ToolCategory.class);

}
