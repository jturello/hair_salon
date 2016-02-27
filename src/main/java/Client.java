import org.sql2o.*;
import java.util.List;

public class Client {
  private int id;
  private String name;
  private int stylist_id;


  // public Client(String name) {
  //   this.name = name;
  // }

  public Client (String name, int stylist_id) {
    this.name = name;
    this.stylist_id = stylist_id;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getStylistId() {
    return stylist_id;
  }

  public String getStylistName() {
    return Stylist.find(stylist_id).getName();
  }

  @Override
  public boolean equals(Object otherClient){
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName()) &&
        this.stylist_id == newClient.getStylistId();
    }
  }

  //CREATE
  public void save() {
    String sql = "INSERT INTO clients(name, stylist_id) VALUES (:name, :stylist_id)";
    try (Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
          .addParameter("stylist_id", stylist_id)
          .addParameter("name", name)
          .executeUpdate()
          .getKey();
    }
  }

  //READ
  public static List<Client> all() {
    String sql = "SELECT * FROM clients"; // ORDER BY stylist_id, name
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

    public static List<Client> findByStylistId(int stylist_id) {
      String sql = "SELECT * FROM clients WHERE stylist_id = :stylist_id ORDER BY name";
      try(Connection con = DB.sql2o.open()) {
        List<Client> clients = con.createQuery(sql)
        .addParameter("stylist_id", stylist_id)
        .executeAndFetch(Client.class);
        return clients;
      }
    }

  //UPDATE
  public void update(String newName, int newStylistId) {
    this.name = newName;
    this.stylist_id = newStylistId;
    String sql = "UPDATE clients SET name = :name, stylist_id = :stylist_id WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("stylist_id", this.stylist_id)
        .addParameter("id", id)
        .executeUpdate();
      }
  }

  public void update(String newName) {
    this.name = newName;
    String sql = "UPDATE clients SET name = :name WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
      }
  }

  public void update(int newStylistId) {
    this.stylist_id = newStylistId;
    String sql = "UPDATE clients SET stylist_id = :stylist_id WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("stylist_id", stylist_id)
        .addParameter("id", id)
        .executeUpdate();
      }
  }

  //DELETE
  public void delete() {
    String sql = "DELETE FROM clients WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public static Client find(int id) {
    String sql = "SELECT * FROM clients WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      Client client = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Client.class);
      return client;
    }
  }

}
