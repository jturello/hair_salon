import org.sql2o.*;
import java.util.List;

public class Cuisine {
  private int cuisine_id;
  private String type;

  public Cuisine (String type) {
    this.type = type;
  }

  public int getId() {
    return cuisine_id;
  }

  public String getType() {
    return type;
  }

  @Override
  public boolean equals(Object otherCuisine){
    if (!(otherCuisine instanceof Cuisine)) {
      return false;
    } else {
      Cuisine newCuisine = (Cuisine) otherCuisine;
      return this.getType().equals(newCuisine.getType()) &&
        this.getId() == newCuisine.getId();
    }
  }

  //CREATE
  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO Cuisine(type) VALUES (:type)";
      this.cuisine_id = (int) con.createQuery(sql, true)
        .addParameter("type", this.type)
        .executeUpdate()
        .getKey();
    }
  }

  //READ
  public static List<Cuisine> all() {
    String sql = "SELECT cuisine_id, type FROM Cuisine";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Cuisine.class);
    }
  }

  //UPDATE
  public void update(String newType) {
    this.type = newType;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE cuisine SET type = :type WHERE cuisine_id = :cuisine_id";
      con.createQuery(sql)
         .addParameter("type", type)
         .addParameter("cuisine_id", cuisine_id)
         .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM cuisine WHERE cuisine_id = :cuisine_id;";
      con.createQuery(sql)
         .addParameter("cuisine_id", cuisine_id)
         .executeUpdate();
    }
  }

  public static Cuisine find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM Cuisine WHERE cuisine_id = :id";
      Cuisine cuisine = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Cuisine.class);
        return cuisine;
    }
  }

  public List<Restaurant> getRestaurants() {
    String sql = "SELECT * FROM restaurants WHERE cuisine_id = :id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id", cuisine_id)
        .executeAndFetch(Restaurant.class);
    }
  }
}
