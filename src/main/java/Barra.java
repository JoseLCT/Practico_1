import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Barra {

    private int x;
    private int y;
    private int altura;
    private int anchura;
    private ArrayList<Barra> listaDeBarras = new ArrayList<>();

    private PropertyChangeSupport notificadorCambios = new PropertyChangeSupport(this);

    public Barra() {
    }

    public Barra(Barra barra) {
        new Barra(barra.getX(), barra.getAltura());
    }

    public Barra(int x, int altura) {
        this.x = x;
        this.y = 450;
        this.altura = altura;
        this.anchura = 5;
        //Se indica que clase (objeto) sera la fuente de los eventos, en este caso es esta clase ya que de aqui se
        //tendra que notificar a los observadores que el rectangulo se movio de posicion.
    }

    public void addObserver(PropertyChangeListener observador) {
        this.notificadorCambios.addPropertyChangeListener(observador);
        //Se agregar un observador para que cuando ocurra un evento en esta clase, se notifique al observador para
        //que haga la accion correspondiente (hacer un repaint en este caso).
    }

    public void movimiento(ArrayList<Barra> listaDeBarras, Barra barraActual) {
        for (Barra barraComparar : listaDeBarras) {
            try {
                Thread.sleep(100);
                if (barraActual.getAltura() > barraComparar.getAltura()) {
                    barraActual.setX(barraActual.getX() + 5);
                    barraComparar.setX(barraComparar.getX() - 5);
                } else {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getX() {
        return this.x;
    }

    public int getAltura() {
        return this.altura;
    }

    public void setX(int x) {
        int oldX;
        oldX = this.x;
        this.x = x;
        this.notificadorCambios.firePropertyChange("MOVIMIENTO", oldX, this.x);
        //Se notifica que el evento acaba de ocurrir a todos los observadores, asi podran realizar la accion que
        //tenian programado si ocurria este evento (en este caso haran un repaint).
    }

    public void setAltura(int altura) {
        int oldAltura;
        oldAltura = this.altura;
        this.altura = altura;
        this.notificadorCambios.firePropertyChange("MOVIMIENTO", oldAltura, this.altura);
    }

    public void agregarBarra(int x, int altura) {
        Barra barra = new Barra(x, altura);
        this.listaDeBarras.add(barra);
        for (int vueltas = 0; vueltas < this.listaDeBarras.size() - 1; vueltas++) {
            for (int contador = 0; contador < this.listaDeBarras.size() - 1; contador++) {
                int auxX = 0;
                int auxAltura = 0;
                if (this.listaDeBarras.get(contador + 1).getAltura() > this.listaDeBarras.get(contador).getAltura()) {
                    auxX = this.listaDeBarras.get(contador + 1).getX();
                    auxAltura = this.listaDeBarras.get(contador + 1).getAltura();
                    this.listaDeBarras.get(contador + 1).setX(this.listaDeBarras.get(contador).getX() - 5);
                    this.listaDeBarras.get(contador + 1).setAltura(this.listaDeBarras.get(contador).getAltura());
                    this.listaDeBarras.get(contador).setX(auxX);
                    this.listaDeBarras.get(contador).setAltura(auxAltura);
                }
                System.out.println("NUevo" + this.listaDeBarras.get(contador + 1).getX());
                System.out.println(this.listaDeBarras.get(contador).getX());
                if (this.listaDeBarras.get(contador + 1).getX() > this.listaDeBarras.get(contador).getX()) {
                    if (this.listaDeBarras.get(contador + 1).getAltura() < this.listaDeBarras.get(contador).getAltura()) {
                        auxX = this.listaDeBarras.get(contador + 1).getX();
                        this.listaDeBarras.get(contador + 1).setX(this.listaDeBarras.get(contador).getX());
                        this.listaDeBarras.get(contador).setX(auxX);
                    }
                }
                if (this.listaDeBarras.get(contador + 1).getX() == this.listaDeBarras.get(contador).getX()) {
                    if (this.listaDeBarras.get(contador + 1).getAltura() < this.listaDeBarras.get(contador).getAltura()) {
                        this.listaDeBarras.get(contador + 1).setX(this.listaDeBarras.get(contador).getX() - 5);
                    } else {
                        this.listaDeBarras.get(contador).setX(this.listaDeBarras.get(contador).getX() - 5);
                    }
                }
            }
        }
        notificadorCambios.firePropertyChange("MOVIMIENTO", this.listaDeBarras.size() - 1, this.listaDeBarras.size());
    }

    public ArrayList<Barra> getListaDeBarras() {
        return this.listaDeBarras;
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.white);
        g.drawRect(x, y, anchura, altura);
    }
}
