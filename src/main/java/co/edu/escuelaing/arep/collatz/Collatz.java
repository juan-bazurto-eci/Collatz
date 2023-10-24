package co.edu.escuelaing.arep.collatz;

import spark.Spark;

import static spark.Spark.*;

public class Collatz {
    private static Integer number;

    public static void main(String... args) {
        port(getPort());
        Spark.staticFiles.header("Access-Control-Allow-Origin", "*");
        options("/*", (req, res) -> {
            String accessControlRequestHeaders = req.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                res.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = req.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                res.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });
        before((req, res) -> {
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Allow-Headers", "*");
            res.type("application/json");
        });
        get("collatzsequence", (req, res) -> {
            if(req.queryParams("value")!= null) {
                Integer number = Integer.parseInt(req.queryParams("value"));
                return "{\n" +
                        "\n" +
                        " \"operation\": \"collatzsequence\",\n" +
                        "\n" +
                        " \"input\":  " + number + ",\n" +
                        "\n" +
                        " \"output\":  " + CollatzSequence(number) + "\n" +
                        "\n" +
                        "}";
            }
            return "";
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }

    private static String CollatzSequence(int number) {
        StringBuilder secuence = new StringBuilder(number + " -> ");

        while (number > 1) {
            if (number % 2 == 0) {
                number = number / 2;
            } else {
                number = (number * 3) + 1;
            }

            if (number != 1) {
                secuence.append(number).append(" -> ");
            } else {
                secuence.append(number);
            }
        }


        return secuence.toString();
    }
}
