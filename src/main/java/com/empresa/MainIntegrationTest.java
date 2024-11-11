// Prueba de integración para el menú interactivo
package com.empresa;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class MainIntegrationTest {

    @Test
    public void testMenuInteraction() {
        String input = "1\nJuan Perez\nCalle Falsa 123\n123456789\njuan.perez@example.com\n5\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Main.main(new String[]{});
        // Verificar la salida esperada...
    }

    // Otras pruebas de integración...
}