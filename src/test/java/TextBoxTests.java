import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests extends TestBase {

    @Test // Успешное заполнение и отправка формы
    void successfulFormCompletionTest(){
        open("/text-box.html");
        $("input[id=userName]").setValue("Full Name");
        $("input[id=userEmail]").setValue("example@mail.com");
        $("textarea[id=currentAddress]").setValue("Pushkina, Kolotushkina");
        $("textarea[id=permanentAddress]").setValue("Pushkina, Kolotushkina");
        $("button[id=submit]").click();

        $("p[id=name]").shouldHave(text("Name:Full Name"));
        $("p[id=email]").shouldHave(text("Email:example@mail.com"));
        $("p[id=currentAddress]").shouldHave(text("Current Address :Pushkina, Kolotushkina"));
        $("p[id=permanentAddress]").shouldHave(text("Permananet Address :Pushkina, Kolotushkina"));
    }

    @Test // Отправка формы при некорректном значении Email
    void incorrectEmail(){
        open("/text-box.html");
        $("input[id=userEmail]").setValue("F!");
        $("button[id=submit]").click();
        $("p[id=email]").shouldNotHave(visible);
    }
}
