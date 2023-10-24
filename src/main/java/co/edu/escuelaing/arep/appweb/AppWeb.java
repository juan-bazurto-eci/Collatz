package co.edu.escuelaing.arep.appweb;

import spark.Spark;

import static spark.Spark.*;

public class AppWeb {
    public static void main(String... args){
        port(getPort());
        Spark.staticFiles.header("Access-Control-Allow-Origin", "*");
        //configuration for request from front
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
        // More configuration for request from back
        before((req, res) -> {
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Allow-Headers", "*");
            res.type("application/json");
        });
        get("/", (req,res) -> {

            return "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>SparkWebApp</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>COLLATZ SECUENCE</h1>\n" +
                    "<form id=\"collatzForm\">\n" +
                    "    <label for=\"collatzInput\">Ingrese número: </label>\n" +
                    "    <input type=\"number\" id=\"collatzInput\" step=\"any\" required>\n" +
                    "    <button type=\"button\" onclick=\"calculateCollatz()\">Calcular</button>\n" +
                    "    <div id=\"collatzResult\"></div>\n" +
                    "</form>\n" +
                    "\n" +
                    "<script>\n" +
                    "    function calculateCollatz() {\n" +
                    "        const input = document.getElementById(\"collatzInput\").value;\n" +
                    "        fetch(`https://ec2-3-82-12-223.compute-1.amazonaws.com:5000/collatzsequence?value=${input}`)\n" +
                    "            .then(response => response.text())\n" +
                    "            .then(result => {\n" +
                    "                document.getElementById(\"collatzResult\").textContent = `${result}`;\n" +
                    "            });\n" +
                    "    }\n" +
                    "            document.getElementById(\"collatzInput\").addEventListener(\"keydown\", function(event) {\n" +
                    "                if (event.key === \"Enter\") {\n" +
                    "                    event.preventDefault();\n" +
                    "                    calculateCollatz();\n" +
                    "                }\n" +
                    "            });\n" +
                    "</script>\n" +
                    "</body>\n" +
                    "</html>\n";
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;
    }
}
