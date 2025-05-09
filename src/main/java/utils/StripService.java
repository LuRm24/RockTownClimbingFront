/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import io.github.cdimascio.dotenv.Dotenv;

public class StripService {

    public static void inicializarStripe() {
        Dotenv dotenv = Dotenv.load(); // carga el archivo .env automáticamente
        String apiKey = dotenv.get("STRIPE_SECRET_KEY");
        Stripe.apiKey = apiKey;
    }

    public static String simularPago(double cantidadEuros) throws Exception {
        long cantidadCentimos = (long)(cantidadEuros * 100); 

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
            .setAmount(cantidadCentimos)
            .setCurrency("eur")
            .setPaymentMethod("pm_card_visa") //Tarjeta de pruebas
            .setConfirm(true)
            .setReturnUrl("https://example.com")
            .build();

        PaymentIntent intent = PaymentIntent.create(params);
        return intent.getStatus(); 
    }

}

