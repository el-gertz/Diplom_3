package api;

import api.dto.User;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class UserAPI extends BaseAPI {

    public static final String AUTH = "auth";

    @Step("Регистрация пользователя")
    public ValidatableResponse create(User user) {
        return spec()
                .body(user)
                .when()
                .post(AUTH + "/register")
                .then()
                .log().all();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse delete(String accessToken) {
        return spec()
                .header("Authorization", accessToken)
                .when()
                .delete(AUTH + "/user")
                .then()
                .log().all();
    }
}
