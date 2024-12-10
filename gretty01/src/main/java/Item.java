import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "item")
public class Item {
  Item() {}
  public Item(String json) {
    this.id = null;
    this.json = json;
  }
  @DatabaseField(generatedId = true)
  public Long id;
  @DatabaseField(canBeNull = false)
  public String json;
}
