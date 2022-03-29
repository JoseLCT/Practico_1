import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PanelBarra extends JPanel implements PropertyChangeListener {

    private Barra barra = new Barra();
    private JButton btnCargar = new JButton("CREAR BARRA");
    private JFrame ventana = new JFrame();
    private JTextField txtX = new JTextField("X");
    private JTextField txtAltura = new JTextField("A");
    private JButton btnCargarBarra = new JButton();

    private Logger logger = LogManager.getRootLogger();

    public PanelBarra(int ancho, int alto) {
        this.setSize(ancho, alto);
        this.setBackground(Color.DARK_GRAY);
        btnCargar.setBounds(10, 10, 70, 70);
        btnCargar.setBackground(Color.WHITE);
        //btnCargar.setBorder(null);
        this.add(btnCargar);
        this.barra.addObserver(this);
        btnCargar.addActionListener(e -> {
            ventana.setVisible(true);
            cargarVentana();
        });
    }

    public void agregarBarra(int x, int altura) {
        barra.agregarBarra(5*x, 5*altura);
    }

    private void cargarVentana() {
        ventana.setSize(500, 500);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(null);
        //ventana.setResizable(false);
        txtAltura.setBounds(1, 1, ventana.getWidth() / 2, 50);
        txtX.setBounds(1, 50, ventana.getWidth() / 2, 50);
        btnCargarBarra.setBounds(80, 80, 50, 50);
        ventana.getContentPane().setBackground(Color.lightGray);
        ventana.add(txtAltura);
        ventana.add(txtX);
        ventana.add(btnCargarBarra);
        btnCargarBarra.addActionListener(e -> {
            agregarBarra(Integer.parseInt(txtX.getText()), Integer.parseInt(txtAltura.getText()));
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Barra barra : barra.getListaDeBarras()) {
            barra.dibujar(g);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("MOVIMIENTO") || evt.getPropertyName().equals("NUEVA_BARRA")) {
            repaint();
            logger.error("Property");
        }
    }
}
