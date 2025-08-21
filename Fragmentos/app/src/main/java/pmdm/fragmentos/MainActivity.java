package pmdm.fragmentos;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Se llama al método seleccionarFragmento para mostrar el fragmento por defecto.
        seleccionarFragmento(findViewById(R.id.btnNavegador));
    }


    /**
     * Método que sirve para mostrar los fragementos según la seleccion del boton.
     * @param v recibe la vista
     */
    public void seleccionarFragmento(View v){
        // Se crea un objeto Fragment
        Fragment fragmento;
        // Se comprueba que boton se ha pulsado
        if(v==findViewById(R.id.btnCalculadora)){
            fragmento = new Calculadora();
        }else if(v==findViewById(R.id.btnNavegador)){
            fragmento = new Navegador();
        }else{
            fragmento = new Navegador();
        }
        // Se llama al método cargarFragmento
        FragmentTransaction transaccion = getSupportFragmentManager().beginTransaction();
        transaccion.replace(R.id.LinearLayoutContenedorDeFragments, fragmento);
        transaccion.addToBackStack(null);
        transaccion.commit();
    }

}