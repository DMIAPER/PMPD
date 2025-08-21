/**
 * MainActivity.java
 *
 * Esta clase conteien la vista principal de la aplicación.
 *
 * @author DMIAPER (Diógenes Miaja Pérez)
 * @version 1.0.0
 * @size 20/05/2025
 */
package com.example.pmdm09;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    // Constante para identificar la solicitud de permisos.
    private static final int REQUEST_PERMISSIONS = 100;

    // ActivityResultLauncher para manejar el resultado de la selección de un contacto.
    // Es la forma moderna y recomendada de obtener resultados de otras Activities.
    // Comentario original: Se instancia un laucher para abrir el
    private ActivityResultLauncher<Intent> contactLauncher;


    // Se instancian los objetos de la vista.
    private EditText etNumero;
    private EditText etMensaje;
    private Button btEnviar;
    private Button btContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Se inicializan los objetos de la vista
        etNumero = findViewById(R.id.etNTelefono);
        etMensaje = findViewById(R.id.etMensaje);
        btEnviar = findViewById(R.id.btEnviar);
        btContacto = findViewById(R.id.btContacto);

        // Se comprueban y se solicitan los permisoso cuando se ejecute la aplicación.
        if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            // Si falta alguno de los permisos, se solicitan al usuario.
            requestPermissions(
                    // Array de Strings con los permisos que se solicitan.
                    new String[]{
                            Manifest.permission.SEND_SMS, // Permiso para enviar SMS.
                            Manifest.permission.READ_CONTACTS // Permiso para leer contactos.
                    },
                    // Se asigna un código de petición
                    REQUEST_PERMISSIONS);
        }

        // Se registra un ActivityResultLauncher para obtener el resultado de la Activity de selección de contactos.
        contactLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),result -> {
                    // Se comprueba si la operación fue exitosa y si hay datos devueltos.
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        // Se obtiene el Intent con los datos del contacto seleccionado.
                        Intent data = result.getData();
                        // Se oObtiene el identificador único del contacto seleccionado.
                        Uri contactUri = data.getData();
                        // Se definen los parámetros que se van a utilizar del contcto
                        String[] projection = {
                                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                                ContactsContract.CommonDataKinds.Phone.NUMBER
                        };

                        // Realiza una consulta al proveedor de contactos para obtener los datos del contacto
                        Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                        // Verifica si la consulta devolvió un cursor válido y si este tiene al menos una fila (el contacto).
                        if (cursor != null && cursor.moveToFirst()) {
                            // Obtiene el nombre del contacto
                            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                            // Obtiene el número de teléfono del contacto
                            String numero = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            // Se convierte el numero de contato recibido a digitos
                            numero = limpiarNumero(numero);
                            // Establece el número de teléfono obtenido en el EditText etNumero.
                            etNumero.setText(numero);
                            // Crea un mensaje predefinido con el nombre del contacto y lo establece en etMensaje.
                            etMensaje.setText("Hola " + nombre + ", te escribo desde mi app.");
                            // Cierra el cursor para liberar recursos. Muy importante.
                            cursor.close();
                        }
                    }
                }
        );

        // Configura el listener para el evento onClick del botón "btContacto".
        btContacto.setOnClickListener(v -> { // Lambda que se ejecuta cuando se hace clic en el botón.
            // Crea un Intent para seleccionar un contacto que tenga un número de teléfono.
            // Intent.ACTION_PICK: Acción para seleccionar un dato de una colección.
            // ContactsContract.CommonDataKinds.Phone.CONTENT_URI: URI que apunta a los contactos con números de teléfono.
            Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
            // Lanza la Activity de selección de contactos usando el contactLauncher.
            // Ya no se usa startActivityForResult directamente.
            contactLauncher.launch(intent);
        });

        /**
         * Método para dar funcionalidad al botón enviar.
         *
         * Este botón capturará los datos introducidos en los dos campos de texto
         * (etNumero y etMensaje) y los enviará a través de SMS.
         */
        btEnviar.setOnClickListener(v -> {
            String numero = etNumero.getText().toString().trim();   // Obtener número
            String mensaje = etMensaje.getText().toString().trim(); // Obtener mensaje
            // Validación: número no vacío y válido
            if (!numero.matches("\\d{9,15}")) {
                // Se informa que el número no es válido
                Toast.makeText(this, "Número no válido", Toast.LENGTH_SHORT).show();
                return;
            }
            // Validación: mensaje no vacío
            if (mensaje.isEmpty()) {
                // Se informa que se ha de escribir un mensaje
                Toast.makeText(this, "Escribe un mensaje", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                // Enviar SMS
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(numero, null, mensaje, null, null);
                // Si todo ha ido bien se muestra un mensaje de éxito
                Toast.makeText(this, "Mensaje enviado a " + numero, Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                // En caso de erro se muestra el mensaje de error
                Toast.makeText(this, "Error al enviar SMS: " + e.getMessage(), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        });

    }

    /**
     * Método de callback que se invoca cuando el usuario responde a una solicitud de permisos.
     *
     * @param requestCode El código de solicitud que se usó en requestPermissions().
     * @param permissions El array de permisos solicitados.
     * @param grantResults El array con los resultados de la concesión para cada permiso (PERMISSION_GRANTED o PERMISSION_DENIED).
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // Se llama al método de la clase padre
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Se comprueba si el código de petición es el esperado
        if (requestCode == REQUEST_PERMISSIONS) {
            // `Se comprueba si se han concedido los permisos.`
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                // Si ambos permisos estan concedidos, muestra un mensaje de confirmación.
                Toast.makeText(this, "Permisos concedidos", Toast.LENGTH_SHORT).show();
            } else {
                // Se muestra un mensaje de error y se cierra la app
                Toast.makeText(this, "Permisos denegados. Cerrando aplicación.", Toast.LENGTH_LONG).show();
                // Se cierra la aplicación
                finish();
            }
        }
    }

    /**
     * Método para limpiar el formato del número de teléfono
     * @param numero String con el número de teléfono
     */
    public String limpiarNumero(String numero) {
        return numero.replaceAll("\\D", "");
    }

}