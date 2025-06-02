/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * Servicio auxiliar para la integración con Stripe desde la aplicación Rocktown
 * Climbing.
 * <p>
 * Esta clase permite inicializar la clave secreta de Stripe de forma segura
 * desde un archivo `.env` y simular un pago de prueba utilizando una tarjeta de
 * desarrollo proporcionada por Stripe.
 *
 * Es útil para realizar pruebas sin necesidad de integrar un sistema de cobro
 * real en producción.
 *
 * @author Lucia Rodriguez Martin
 * @version 1.0
 */
public class StripService {

    /**
     * Inicializa la API de Stripe cargando la clave secreta desde un archivo
     * .env.
     * <p>
     * La clave debe estar definida con el nombre {@code STRIPE_SECRET_KEY} en
     * el archivo .env situado en la raíz del proyecto.
     */
    public static void inicializarStripe() {
        Dotenv dotenv = Dotenv.load(); // Carga automática del archivo .env
        String apiKey = dotenv.get("STRIPE_SECRET_KEY");
        Stripe.apiKey = apiKey;
    }

    /**
     * Simula un pago de prueba con Stripe utilizando una tarjeta de desarrollo.
     * <p>
     * Crea un {@link PaymentIntent} con confirmación automática y un importe
     * definido en euros. Este método está diseñado exclusivamente para entornos
     * de pruebas.
     *
     * @param cantidadEuros Cantidad a pagar en euros (por ejemplo, 9.99)
     * @return El estado del pago devuelto por Stripe (por ejemplo, "succeeded")
     * @throws Exception Si ocurre un error al comunicarse con la API de Stripe
     */
    public static String simularPago(double cantidadEuros) throws Exception {
        long cantidadCentimos = (long) (cantidadEuros * 100); // Stripe trabaja en céntimos

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(cantidadCentimos)
                .setCurrency("eur")
                .setPaymentMethod("pm_card_visa") // Tarjeta de pruebas de Stripe
                .setConfirm(true)
                .setReturnUrl("https://example.com")
                .build();

        PaymentIntent intent = PaymentIntent.create(params);
        return intent.getStatus(); // Devuelve el estado del intento de pago
    }

}
