package co.edu.escuelaing.arep.appweb;

import static spark.Spark.*;

public class AppWeb {
    public static void main(String... args){
        port(getPort());
        get("/", (req,res) -> {
            return "Hello";
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
