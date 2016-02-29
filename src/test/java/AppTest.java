import org.fluentlenium.adapter.FluentTest;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Add a new stylist");
  }

    @Test
    public void click_addNewStylist_displaysStylistsPage_true() {
      goTo("http://localhost:4567/");
      click("a", withText("Add a new stylist"));
      assertThat(pageSource()).contains("Add a stylist name");
    }

    @Test
    public void click_returnToStylistList_displaysStylistListPage_true() {
      goTo("http://localhost:4567/");
      click("a", withText("Add a new stylist"));
      click("a", withText("Return to stylist list"));
      assertThat(pageSource()).contains("Add a new stylist");
    }

    @Test
    public void stylistPageDisplaysTextStylistsClientList_true() {
      goTo("http://localhost:4567/");
      click("a", withText("seedStylist"));
      assertThat(find("h3").getTexts().contains("seedStylist's client list"));
    }

    @Test
    public void stylistPage_addClient_addsClientToStylistList_true() {
      goTo("http://localhost:4567/stylists/1");
      fill("#clientName").with("newClient");
      submit(".btn");
      assertThat(pageSource()).contains("newClient");
    }

    @Test
    public void clientPage_containsTextClientPage_true() {
      goTo("http://localhost:4567/stylists/1");
      fill("#clientName").with("newClient");
      submit(".btn");
      click("a", withText("newClient"));
      assertThat(pageSource()).contains("Client Page");
    }

    @Test
    public void deleteClient_removesClientFromStylistPage_true() {
      goTo("http://localhost:4567/stylists/1");
      fill("#clientName").with("newClient");
      submit(".btn");
      click("a", withText("newClient"));
      submit(".btn");
      assertThat(pageSource()).doesNotContain("newClient");
    }

    @Test
    public void clientPage_clickReturnToStylistListDisplaysStylistList_true() {
      goTo("http://localhost:4567/stylists/1");
      fill("#clientName").with("newClient");
      submit(".btn");
      click("a", withText("newClient"));
      click("a", withText("Return to Stylist List"));
      assertThat(pageSource()).contains("Add a new stylist");
    }

}
