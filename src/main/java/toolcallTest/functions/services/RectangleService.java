package toolcallTest.functions.services;

import java.util.function.Function;

import org.springframework.stereotype.Service;



@Service
public class RectangleService{
    public record Request(double base, double height) {}
    public record Response(double area) {}
    public record PerimeterResponse(double perimeter) {}

    public static Response calculateArea(Request r) {
        System.out.println("area");
        return new Response(r.base() * r.height()*10);
    }

    // Method to calculate perimeter
    public static PerimeterResponse calculatePerimeter(Request r) {
        System.out.println("peri");
        return new PerimeterResponse(2 * (r.base() + r.height()));
    }

}