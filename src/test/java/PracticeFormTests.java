import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests extends TestBase {

   @Test // Заполнение всех полей формы
    void successfulFormCompletionTest(){
        // Открыть страницу с формой
        open("/automation-practice-form.html");
//        // Убрать баннер
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        // Заполнить поле FirstName
        $("#firstName").click();
        $("#firstName").setValue("First Name");
        // Заполнить поле LastName
        $("#lastName").click();
        $("#lastName").setValue("Second Name");
        // Заполнить поле Email
        $("#userEmail").click();
        $("#userEmail").setValue("example@mail.com");
        // Установить значение Gender
        $("#gender-radio-3").click();
        // Заполнить поле Mobile
        $("#userNumber").click();
        $("#userNumber").setValue("9999999999");
        // Заполнить поле Date of Birth
        $("#dateOfBirthInput").click();
        $x("//*[@data-day='4']").click();
        // Заполнить поле Subjects
        $(".subjects-input-area").click();
        $x("//*[contains(text(), 'Math')]").click();
        // Установить значение Hobbies
        $("#hobbies-checkbox-2").click();
        // Добавить изображение
        $("#uploadPicture").uploadFromClasspath("capybara.avif");
        // Заполнить поле Current Address
        $("#currentAddress").click();
        $("#currentAddress").setValue("Pushkina, Kolotushkina");
        // Заполнить поле State
        $("#state").click();
        $x("//*[contains(text(), 'Uttar Pradesh')]").scrollIntoView(true);
        $x("//*[contains(text(), 'Uttar Pradesh')]").click();
        // Заполнить поле City
        $("#city").click();
        $x("//*[contains(text(), 'Agra')]").click();
        // Выполнить отправку формы
        $("#submit").scrollIntoView(true);
        $("#submit").click();
        // Проверить что отправка формы была успешна
        $("#resultModal").shouldHave(visible);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        ElementsCollection results = $("#resultBody").findAll("td");
        results.get(1).shouldHave(text("First Name Second Name"));
        results.get(3).shouldHave(text("example@mail.com"));
        results.get(5).shouldHave(text("Other"));
        results.get(7).shouldHave(text("9999999999"));
        results.get(9).shouldHave(text("2008-07-04"));
        results.get(11).shouldHave(text("Math"));
        results.get(13).shouldHave(text("Reading"));
        results.get(15).shouldHave(text("capybara.avif"));
        results.get(17).shouldHave(text("Pushkina, Kolotushkina"));
        results.get(19).shouldHave(text("Uttar Pradesh Agra"));
    }

    @Test  // Заполнение только обязательных полей
    void requiredFieldsOnlyTest() {
        open("/automation-practice-form.html");
        $("[aria-label=Close]").click();
        $("#firstName").click();
        $("#firstName").setValue("First Name");
        $("#lastName").click();
        $("#lastName").setValue("Second Name");
        $("#userEmail").click();
        $("#userEmail").setValue("example@mail.com");
        $("#gender-radio-3").click();
        $("#userNumber").click();
        $("#userNumber").setValue("9999999999");
        $("#dateOfBirthInput").click();
        $x("//*[@data-day='4']").click();
        $("#submit").click();
        $("#resultModal").shouldHave(visible);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
    }

    @Test  // Отправка пустой формы
    void unsuccessfulSubmitTest(){
        open("/automation-practice-form.html");
        $("[aria-label=Close]").click();
        $("#submit").scrollIntoView(true);
        $("#submit").click();
        $("#formError").shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }

    @Test  // Отправка формы с некорректным номером телефона
    void incorrectNumberTest() {
        open("/automation-practice-form.html");
        $("[aria-label=Close]").click();
        $("#firstName").click();
        $("#firstName").setValue("First Name");
        $("#lastName").click();
        $("#lastName").setValue("Second Name");
        $("#userEmail").click();
        $("#userEmail").setValue("example@mail.com");
        $("#userNumber").click();
        $("#userNumber").setValue("999999999");
        $("#submit").click();
        $("#formError").shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }

    @Test  // Отправка формы с пустым полем First Name
    void submitWithoutNameTest() {
        open("/automation-practice-form.html");
        $("[aria-label=Close]").click();
        $("#lastName").click();
        $("#lastName").setValue("Second Name");
        $("#userEmail").click();
        $("#userEmail").setValue("example@mail.com");
        $("#userNumber").click();
        $("#userNumber").setValue("999999999");
        $("#submit").click();
        $("#formError").shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }
}
