/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class Desafio5 extends JFrame {
    public Desafio5() {
        super("Ejercicios Swing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 420);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("1) Calculadora", new CalculadoraSimplePanel());
        tabs.addTab("2) Tamaño de Fuente", new TamanoFuentePanel());
        tabs.addTab("3) Lenguajes", new SelectorLenguajesPanel());
        tabs.addTab("4) Edad", new ValidacionEdadPanel());

        setContentPane(tabs);
    }

    /* ---------------------------- EJERCICIO 1 ---------------------------- */
    static class CalculadoraSimplePanel extends JPanel {
        private final JTextField display = new JTextField("0");
        private double memoria = 0.0;        
        private Character operacion = null;  
        private boolean iniciarNumero = true;

        CalculadoraSimplePanel() {
            setLayout(new BorderLayout(8, 8));

            display.setEditable(false);
            display.setHorizontalAlignment(JTextField.RIGHT);
            display.setFont(display.getFont().deriveFont(Font.BOLD, 28f));
            add(display, BorderLayout.NORTH);

            JPanel centro = new JPanel(new GridLayout(4, 3, 6, 6));
            for (int i = 1; i <= 9; i++) centro.add(crearBotonNumero(String.valueOf(i)));
            centro.add(new JLabel());                         
            centro.add(crearBotonNumero("0"));
            JButton punto = crearBotonNumero(".");
            centro.add(punto);
            add(centro, BorderLayout.CENTER);

            JPanel inferior = new JPanel(new GridLayout(1, 6, 6, 6));
            inferior.add(crearBotonOperacion("+"));
            inferior.add(crearBotonOperacion("-"));
            inferior.add(crearBotonOperacion("*"));
            inferior.add(crearBotonOperacion("/"));

            JButton igual = new JButton("=");
            igual.addActionListener(e -> calcular());
            inferior.add(igual);

            JButton clear = new JButton("C");
            clear.addActionListener(e -> limpiar());
            inferior.add(clear);

            add(inferior, BorderLayout.SOUTH);
        }

        private JButton crearBotonNumero(String txt) {
            JButton b = new JButton(txt);
            b.setFont(b.getFont().deriveFont(20f));
            b.addActionListener(e -> escribirNumero(txt));
            return b;
        }

        private JButton crearBotonOperacion(String op) {
            JButton b = new JButton(op);
            b.setFont(b.getFont().deriveFont(Font.BOLD, 20f));
            b.addActionListener(e -> operar(op.charAt(0)));
            return b;
        }

        private void escribirNumero(String txt) {
            if (iniciarNumero) {
                display.setText((".").equals(txt) ? "0." : txt);
                iniciarNumero = false;
            } else {
                // Evita dos puntos decimales
                if (".".equals(txt) && display.getText().contains(".")) return;
                display.setText(display.getText() + txt);
            }
        }

        private void operar(char op) {
            try {
                double valorActual = Double.parseDouble(display.getText());
                if (operacion == null) {
                    memoria = valorActual;    
                } else if (!iniciarNumero) {
                    memoria = aplicarOperacion(memoria, valorActual, operacion);
                }
                operacion = op;
                iniciarNumero = true;
                display.setText(formatear(memoria));
            } catch (NumberFormatException ex) {
                mostrarError("Número inválido");
            } catch (ArithmeticException ex) {
                mostrarError(ex.getMessage());
            }
        }

        private void calcular() {
            if (operacion == null) return; 
            try {
                double valorActual = Double.parseDouble(display.getText());
                memoria = aplicarOperacion(memoria, valorActual, operacion);
                display.setText(formatear(memoria));
                operacion = null; 
                iniciarNumero = true;
            } catch (NumberFormatException ex) {
                mostrarError("Número inválido");
            } catch (ArithmeticException ex) {
                mostrarError(ex.getMessage());
            }
        }

        private void limpiar() {
            display.setText("0");
            memoria = 0.0;
            operacion = null;
            iniciarNumero = true;
        }

        private static double aplicarOperacion(double a, double b, char op) {
            switch (op) {
                case '+': return a + b;
                case '-': return a - b;
                case '*': return a * b;
                case '/':
                    if (b == 0) throw new ArithmeticException("No se puede dividir por cero");
                    return a / b;
                default: throw new IllegalArgumentException("Operación desconocida: " + op);
            }
        }

        private static String formatear(double v) {
            if (Math.rint(v) == v) return String.valueOf((long) v);
            return String.valueOf(v);
        }

        private void mostrarError(String msg) {
            JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
            limpiar();
        }
    }

    /* ---------------------------- EJERCICIO 2 ---------------------------- */
    static class TamanoFuentePanel extends JPanel {
        private final JLabel label = new JLabel("Texto de ejemplo", SwingConstants.CENTER);
        private final Integer[] tamanos = {12, 14, 16, 18, 20};

        TamanoFuentePanel() {
            setLayout(new BorderLayout(8, 8));

            JComboBox<Integer> combo = new JComboBox<>(tamanos);
            combo.setSelectedItem(16);
            combo.addActionListener(e -> {
                Integer t = (Integer) combo.getSelectedItem();
                if (t != null) label.setFont(label.getFont().deriveFont((float) t));
            });

            label.setFont(label.getFont().deriveFont(16f));
            add(combo, BorderLayout.NORTH);
            add(label, BorderLayout.CENTER);
        }
    }

    /* ---------------------------- EJERCICIO 3 ---------------------------- */
    static class SelectorLenguajesPanel extends JPanel {
        SelectorLenguajesPanel() {
            setLayout(new BorderLayout(8, 8));

            String[] lenguajes = {"Java", "Python", "C++", "JavaScript", "C#", "Go", "Rust"};
            JList<String> list = new JList<>(lenguajes);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setVisibleRowCount(6);

            Map<String, String> info = new HashMap<>();
            info.put("Java", "Orientado a objetos, JVM, muy usado en backend y Android.");
            info.put("Python", "Multiparadigma, sintaxis simple, data science, IA, scripting.");
            info.put("C++", "Alto rendimiento, control de memoria, sistemas y videojuegos.");
            info.put("JavaScript", "Lenguaje de la web, front-end y back-end con Node.js.");
            info.put("C#", "Ecosistema .NET, aplicaciones de escritorio, backend y juegos (Unity).");
            info.put("Go", "De Google, concurrencia sencilla, servicios y microservicios.");
            info.put("Rust", "Seguridad de memoria sin GC, rendimiento y sistemas.");

            list.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2 && !e.isConsumed()) {
                        e.consume();
                        String sel = list.getSelectedValue();
                        if (sel != null) {
                            String msg = info.getOrDefault(sel, "Sin datos");
                            JOptionPane.showMessageDialog(SelectorLenguajesPanel.this,
                                    sel + ": " + msg,
                                    "Información",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            });

            add(new JScrollPane(list), BorderLayout.CENTER);
            add(new JLabel("Doble clic para ver info", SwingConstants.CENTER), BorderLayout.SOUTH);
        }
    }

    /* ---------------------------- EJERCICIO 4 ---------------------------- */
    static class ValidacionEdadPanel extends JPanel {
        private final JTextField txtEdad = new JTextField();
        private final JButton btnValidar = new JButton("Validar");

        ValidacionEdadPanel() {
            setLayout(new BorderLayout(8, 8));

            JPanel form = new JPanel(new GridLayout(1, 2, 6, 6));
            form.add(new JLabel("Ingresá tu edad:"));
            form.add(txtEdad);

            btnValidar.addActionListener(e -> validar());

            add(form, BorderLayout.NORTH);
            add(btnValidar, BorderLayout.SOUTH);
        }

        private void validar() {
            String input = txtEdad.getText().trim();
            try {
                int edad = Integer.parseInt(input);
                if (edad < 18) {
                    JOptionPane.showMessageDialog(this,
                            "Error: Debés ser mayor de 18.",
                            "Acceso denegado",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "¡Bienvenido/a!",
                            "Acceso permitido",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Entrada no válida. Ingresá solo números enteros.",
                        "Error de formato",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Desafio5().setVisible(true));
    }
}
