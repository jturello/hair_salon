import java.util.*;

import java.util.Map;
import java.util.HashMap;
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      List<Stylist> stylists = Stylist.all();

      if (stylists == null) {
        stylists = new ArrayList<Stylist>();
        request.session().attribute("stylists", stylists);
      }

      model.put("stylists", stylists);

      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/clients/new", (request, reponse) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      List<Stylist> stylists = Stylist.all();

      if (stylists == null) {
        stylists = new ArrayList<Stylist>();
        request.session().attribute("stylists", stylists);
      }

      model.put("stylists", stylists);
      model.put("template", "templates/clients.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    post("/clients", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      String clientName = request.queryParams("clientName");
      int stylistId = Integer.parseInt(request.queryParams("stylistId"));
      Stylist stylist = Stylist.find(stylistId);

      Client newClient = new Client(clientName, stylistId);
      newClient.save();

      List<Client> clients = Client.all();
      request.session().attribute("clients", clients);

      List<Client> clientsForStylist = Client.findByStylistId(stylistId);

      model.put("stylist", stylist);
      model.put("clients", clientsForStylist);
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/clients/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      Client client = Client.find(Integer.parseInt(request.params(":id")));

      model.put("client", client);
      model.put("template", "templates/client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    post("/clients/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      Client client = Client.find(Integer.parseInt(request.queryParams("clientId")));
      Stylist stylistForClient = Stylist.find(client.getStylistId());

      client.delete();
      List<Client> clients = Client.all();
      request.session().attribute("clients", clients);

      List<Client> clientsForStylist = Client.findByStylistId(stylistForClient.getId());

      model.put("clients", clientsForStylist);
      model.put("stylist", stylistForClient);
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    post("/stylists", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      String stylistName = request.queryParams("name");
      Stylist newStylist = new Stylist(stylistName);

      newStylist.save();
      List<Stylist> stylists = Stylist.all();
      request.session().attribute("stylists", stylists);

      model.put("stylists", stylists);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());



    get("/stylists/new", (request, reponse) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      List<Stylist> stylists = Stylist.all();

      if (stylists == null) {
        stylists = new ArrayList<Stylist>();
        request.session().attribute("stylists", stylists);
      }

      model.put("stylists", stylists);
      model.put("template", "templates/stylists.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/stylists/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));

      List<Client> clients = Client.findByStylistId(Integer.parseInt(request.params(":id")));

      model.put("stylist", stylist);
      model.put("clients", clients);
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  } // END OF MAIN
} // END OF APP CLASS
