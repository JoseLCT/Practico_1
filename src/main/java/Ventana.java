import javax.swing.*;
import java.util.ArrayList;

public class Ventana extends JFrame {

    private Barra barra = new Barra();
    private JButton btnAbrirVentana = new JButton();

    private JFrame ventana_agregarBarra = new JFrame();
    private JLabel lbPosicion = new JLabel();
    private JLabel lbAltura = new JLabel();
    private JTextField txtPosicion = new JTextField();
    private JTextField txtAltura = new JTextField();
    private JButton btnAgregar = new JButton("AGREGAR");
    private JButton btnAtras = new JButton();
    private ArrayList<Barra> listaBarras = new ArrayList<>();

    private PanelBarra panelBarra;

    public Ventana() {
        this.setLayout(null);
        this.setSize(900, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        btnAbrirVentana.setBounds(10, 10, 60, 70);
        panelBarra = new PanelBarra(getWidth(), getHeight());
        //this.setResizable(false);
        this.add(panelBarra);
        this.setVisible(true);
    }

    private void cargarVentanaAgregarBarra() {
    }
}
