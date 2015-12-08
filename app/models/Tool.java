package models;

import com.avaje.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Tool extends Model {
  @Id
  public Long id;
  
  public String name;
  
  public String description;

  public boolean available;
  
  @ManyToOne
  public User owner;

  @ManyToOne
  public ToolCategory toolcategory;
  
  // A finder object for easier querying
  public static Finder<Long, Tool> find = new Finder<Long, Tool>(Tool.class);

  public static Tool createNewTool(String toolName, String toolDescription, User toolOwner) {
    if(toolName == null || toolDescription == null || toolOwner == null || toolName.length() == 0 || toolDescription.length() == 0){
      return null;
    }

    Tool tool = new Tool();
    tool.name = toolName;
    tool.description = toolDescription;
    tool.owner = toolOwner;
    tool.available = true;

    return tool;
  }
}