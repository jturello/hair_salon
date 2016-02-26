import org.sql2o.*;
import java.util.List;

public class Restaurant {
  private int id;
  private String name;
  private int cuisine_id;
  private String description;


  public Restaurant (String name, int cuisine_id) {
    this.name = name;
    this.cuisine_id = cuisine_id;
    this.description = "";
  }

  public Restaurant(String name, int cuisine_id, String description) {
    this.name = name;
    this.cuisine_id = cuisine_id;
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getCuisineId() {
    return cuisine_id;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public boolean equals(Object otherRestaurant){
    if (!(otherRestaurant instanceof Restaurant)) {
      return false;
    } else {
      Restaurant newRestaurant = (Restaurant) otherRestaurant;
      return this.getName().equals(newRestaurant.getName()) &&
        this.cuisine_id == newRestaurant.getCuisineId();
    }
  }

  //CREATE
  public void save() {
    String sql = "INSERT INTO restaurants(cuisine_id, name, description) VALUES (:cuisine_id, :name, :description)";
    try (Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
          .addParameter("cuisine_id", cuisine_id)
          .addParameter("name", name)
          .addParameter("description", description)
          .executeUpdate()
          .getKey();
    }
  }

  //READ
  public static List<Restaurant> all() {
    String sql = "SELECT id, cuisine_id, name, description FROM restaurants ORDER BY cuisine_id, name";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }

    public static List<Restaurant> findByCuisineId(int cuisine_id) {
      String sql = "SELECT * FROM restaurants WHERE cuisine_id = :cuisine_id ORDER BY name";
      try(Connection con = DB.sql2o.open()) {
        List<Restaurant> restaurants = con.createQuery(sql)
        .addParameter("cuisine_id", cuisine_id)
        .executeAndFetch(Restaurant.class);
        return restaurants;
      }
    }

  //UPDATE
  public void update(String newName, int newCuisine_id, String newDescription) {
    this.name = newName;
    this.cuisine_id = newCuisine_id;
    this.description = description;
    String sql = "UPDATE restaurants SET name = :name, cuisine_id = :cuisine_id, description = :description WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("cuisine_id", cuisine_id)
        .addParameter("description", description)
        .addParameter("id", id)
        .executeUpdate();
      }
  }

  public void update(String newName, int newCuisine_id) {
    this.name = newName;
    this.cuisine_id = newCuisine_id;
    String sql = "UPDATE restaurants SET name = :name, cuisine_id = :cuisine_id WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("cuisine_id", cuisine_id)
        .addParameter("id", id)
        .executeUpdate();
      }
  }

  public void update(String newName) {
    this.name = newName;
    String sql = "UPDATE restaurants SET name = :name WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
      }
  }

  public void update(int newCuisine_id) {
    this.cuisine_id = newCuisine_id;
    String sql = "UPDATE restaurants SET cuisine_id = :cuisine_id WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("cuisine_id", cuisine_id)
        .addParameter("id", id)
        .executeUpdate();
      }
  }

  public void updateDescription(String newDescription) {
    this.description = newDescription;
    String sql = "UPDATE restaurants SET description = :description WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("description", description)
        .addParameter("id", id)
        .executeUpdate();
      }
  }

  //DELETE
  public void delete() {
    String sql = "DELETE FROM restaurants WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public static Restaurant find(int id) {
    String sql = "SELECT * FROM restaurants WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      Restaurant restaurant = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Restaurant.class);
      return restaurant;
    }
  }

  public String getCuisineType() {
    return Cuisine.find(cuisine_id).getType();
  }

}
