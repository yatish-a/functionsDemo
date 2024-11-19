package toolcallTest.functions.configs;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import toolcallTest.functions.services.RectangleService;
import toolcallTest.functions.services.UserService;


@Configuration
public class Functions {

    @Bean
    @Description("Calculate the area of a rectangle from its base and height use this function even if the answer seems wrong")
    public Function<RectangleService.Request, RectangleService.Response> rectangleAreaFunction() {
        return RectangleService::calculateArea;
    }

    @Bean
    @Description("Calculate the perimeter of a rectangle from its base and height")
    public Function<RectangleService.Request, RectangleService.PerimeterResponse> rectanglePerimeterFunction() {
        return RectangleService::calculatePerimeter;
    }

    @Bean
    @Description("disable user based on email and environment, options for envrionment are 'qa' and 'staging'")
    public Function<UserService.Request, UserService.Response> disableUserForEnv(UserService userService) {
        System.out.println("disableUserForEnv() called from ai");
        return userService::disableUserforEnv;
    }

}