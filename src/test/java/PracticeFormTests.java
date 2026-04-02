import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests extends TestBase {

   @Test // Заполнение всех полей формы
    void successfulFormCompletionTest(){
        // Открыть страницу с формой
        open("/automation-practice-form.html");
//        // Убрать баннер
        $("[aria-label=Close]").click();
        // Заполнить поле FirstName
        $("[id=firstName]").click();
        $("[id=firstName]").setValue("First Name");
        // Заполнить поле LastName
        $("[id=lastName]").click();
        $("[id=lastName]").setValue("Second Name");
        // Заполнить поле Email
        $("[id=userEmail]").click();
        $("[id=userEmail]").setValue("example@mail.com");
        // Установить значение Gender
        $("[id=gender-radio-3]").click();
        // Заполнить поле Mobile
        $("[id=userNumber]").click();
        $("[id=userNumber]").setValue("9999999999");
        // Заполнить поле Date of Birth
        $("[id=dateOfBirthInput]").click();
        $x("//*[@data-day='4']").click();
        // Заполнить поле Subjects
        $("[class=subjects-input-area]").click();
        $x("//*[contains(text(), 'Math')]").click();
        // Установить значение Hobbies
        $("[id=hobbies-checkbox-2]").click();
        // Добавить изображение
        $("[id=uploadPicture]").uploadFile(new File("src/test/data/capybara.avif"));
        // Заполнить поле Current Address
        $("[id=currentAddress]").click();
        $("[id=currentAddress]").setValue("Pushkina, Kolotushkina");
        // Заполнить поле State
        $("[id=state]").click();
        $x("//*[contains(text(), 'Uttar Pradesh')]").scrollIntoView(true);
        $x("//*[contains(text(), 'Uttar Pradesh')]").click();
        // Заполнить поле City
        $("[id=city]").click();
        $x("//*[contains(text(), 'Agra')]").click();
        // Выполнить отправку формы
        $("[id=submit]").scrollIntoView(true);
        $("[id=submit]").click();
        // Проверить что отправка формы была успешна
        $("[id=resultModal]").shouldHave(visible);
        $("[id=example-modal-sizes-title-lg]").shouldHave(text("Thanks for submitting the form"));
    }

    @Test  // Заполнение только обязательных полей
    void requiredFieldsOnlyTest() {
        open("/automation-practice-form.html");
        $("[aria-label=Close]").click();
        $("[id=firstName]").click();
        $("[id=firstName]").setValue("First Name");
        $("[id=lastName]").click();
        $("[id=lastName]").setValue("Second Name");
        $("[id=userEmail]").click();
        $("[id=userEmail]").setValue("example@mail.com");
        $("[id=gender-radio-3]").click();
        $("[id=userNumber]").click();
        $("[id=userNumber]").setValue("9999999999");
        $("[id=dateOfBirthInput]").click();
        $x("//*[@data-day='4']").click();
        $("[id=submit]").click();
        $("[id=resultModal]").shouldHave(visible);
        $("[id=example-modal-sizes-title-lg]").shouldHave(text("Thanks for submitting the form"));
    }

    @Test  // Отправка пустой формы
    void unsuccessfulSubmitTest(){
        open("/automation-practice-form.html");
        $("[aria-label=Close]").click();
        $("[id=submit]").scrollIntoView(true);
        $("[id=submit]").click();
        $("[id=formError]").shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }

    @Test  // Отправка формы с некорректным номером телефона
    void incorrectNumberTest() {
        open("/automation-practice-form.html");
        $("[aria-label=Close]").click();
        $("[id=firstName]").click();
        $("[id=firstName]").setValue("First Name");
        $("[id=lastName]").click();
        $("[id=lastName]").setValue("Second Name");
        $("[id=userEmail]").click();
        $("[id=userEmail]").setValue("example@mail.com");
        $("[id=userNumber]").click();
        $("[id=userNumber]").setValue("999999999");
        $("[id=submit]").click();
        $("[id=formError]").shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }

    @Test  // Отправка формы с пустым полем First Name
    void submitWithoutNameTest() {
        open("/automation-practice-form.html");
        $("[aria-label=Close]").click();
        $("[id=lastName]").click();
        $("[id=lastName]").setValue("Second Name");
        $("[id=userEmail]").click();
        $("[id=userEmail]").setValue("example@mail.com");
        $("[id=userNumber]").click();
        $("[id=userNumber]").setValue("999999999");
        $("[id=submit]").click();
        $("[id=formError]").shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }
}
