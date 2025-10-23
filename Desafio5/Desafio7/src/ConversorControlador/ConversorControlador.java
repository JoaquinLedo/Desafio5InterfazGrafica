/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConversorControlador;

/**
 *
 * @author Joaquin
 */
import Vista.ConversorVista;    
import Conversor.Conversor;      

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Locale;

public class ConversorControlador {
    private final Conversor model;
    private final ConversorVista view;

    public ConversorControlador(Conversor model, ConversorVista view) {
        this.model = model;
        this.view = view;
        ActionListener convertir = e -> realizarConversion();
        view.getBtnConvertir().addActionListener(convertir);
        view.getTxtEntrada().addActionListener(convertir); 
        view.setVisible(true); 
    }

    private void realizarConversion() {
        String s = view.getTxtEntrada().getText().trim();
        try {
            double v = Double.parseDouble(s.replace(',', '.')); 
            boolean cToF = view.getCombo().getSelectedIndex() == 0;
            double res = cToF ? model.celsiusAFahrenheit(v) : model.fahrenheitACelsius(v);
            view.getTxtSalida().setText(String.format(Locale.US, "%.2f", res));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view,
                    "Ingresá un número válido (ej: 36.6)",
                    "Error de formato",
                    JOptionPane.ERROR_MESSAGE);
            view.getTxtEntrada().requestFocus();
        }
    }
}


