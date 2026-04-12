import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests extends TestBase {

    @Test // Успешное заполнение и отправка формы
    void successfulFormCompletionTest(){
        open("/text-box.html");
        $("#userForm").$(byId("userName")).setValue("Full Name");
        $("#userForm").$(byId("userEmail")).setValue("example@mail.com");
        $("#userForm").$(byId("currentAddress")).setValue("Pushkina, Kolotushkina");
        $("#userForm").$(byId("permanentAddress")).setValue("Pushkina, Kolotushkina");
        $("#submit").click();

        $("#output").$(byId("name")).shouldHave(text("Name:Full Name"));
        $("#output").$(byId("email")).shouldHave(text("Email:example@mail.com"));
        $("#output").$(byId("currentAddress")).shouldHave(text("Current Address :Pushkina, Kolotushkina"));
        $("#output").$(byId("permanentAddress")).shouldHave(text("Permananet Address :Pushkina, Kolotushkina"));
    }

    @Test // Отправка формы при некорректном значении Email
    void incorrectEmail(){
        open("/text-box.html");
        $("#userForm").$(byId("userEmail")).setValue("F!");
        $("#submit").click();
        $("#output").$(byId("email")).shouldNotHave(visible);
    }
}
