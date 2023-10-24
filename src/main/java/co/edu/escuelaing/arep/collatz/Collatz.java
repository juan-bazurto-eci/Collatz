package co.edu.escuelaing.arep.collatz;

import static spark.Spark.*;

public class Collatz {
    public static void main(String... args){
        port(getPort());
        get("collatzsequence", (req,res) -> {
            System.out.println(req);

            return req;
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }
}
