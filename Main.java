package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    /*
     * to calculate discounted price
     * @input barcode the barcode
     * @return discountedPrice the discountedPrice
     */
    private static int getDiscountedPrice(String barcode) throws IOException {
        URL url = new URL("https://jsonmock.hackerrank.com/api/inventory?barcode=" + barcode);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        InputStream responseStream = connection.getInputStream();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readValue(responseStream, JsonNode.class);
        JsonNode dataJsonNode = jsonNode.get("data").get(0);
        if (dataJsonNode == null) {
            return -1;
        }
        int price = dataJsonNode.get("price").asInt();
        double discount = dataJsonNode.get("discount").asDouble();
        int discountedPrice = (int) (price - ((discount / 100) * price));
        return discountedPrice;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Discounted price: " + getDiscountedPrice("74005364"));
    }
}
