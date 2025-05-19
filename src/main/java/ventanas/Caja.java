/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.TipoEntrada;
import models.Producto;
import utils.StripService;
import utils.Utils;

public class Caja extends javax.swing.JFrame {

    private double total = 0.0;

    public Caja() {
        initComponents();
        Utils.cargarTiposEntrada(tipoBono);
        cargarProductos(productos);
    }
    
    public static void cargarProductos(JComboBox productos) {
        try {
            URL url = new URL("http://localhost:8080/producto/select-all");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            cargarDatosProductoCombo(br, productos);

        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static void cargarDatosProductoCombo(BufferedReader entrada, JComboBox productos) {
        try {
            String datosLeidos = "";
            String linea;
            while ((linea = entrada.readLine()) != null) {
                datosLeidos += linea;
            }

            if (datosLeidos.startsWith("[")) {
                JsonArray jsonArray = JsonParser.parseString(datosLeidos).getAsJsonArray();

                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject obj = jsonArray.get(i).getAsJsonObject();

                    Producto p = new Producto(
                        obj.get("id").getAsLong(),
                        obj.get("nombre").getAsString(),
                        obj.get("precio").getAsDouble()
                    );

                    productos.addItem(p);
                }
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    private void numeroPulsado(String num) {
        visorOperacion.setText(visorOperacion.getText() + num);
    }

    private void ejecutarSimbolo(String simbolo) {
        visorOperacion.setText(visorOperacion.getText() + " " + simbolo + " ");
    }

    private void resolverOperacionManual() {
        String expresion = visorOperacion.getText();
        if (expresion.isEmpty()) return;

        try {
            double resultado = evaluarExpresionSimple(expresion);
            visorOperacion.setText(expresion + " = " + resultado);
            double totalFinal = total + resultado;
            precioTotal.setText(String.format("%.2f", totalFinal));
        } catch (Exception e) {
            visorOperacion.setText("Error");
            precioTotal.setText("Error");
        }
    }

    private double evaluarExpresionSimple(String expr) throws Exception {
        String[] tokens = expr.split(" ");
        double resultado = Double.parseDouble(tokens[0]);

        for (int i = 1; i < tokens.length; i += 2) {
            String operador = tokens[i];
            double siguienteNumero = Double.parseDouble(tokens[i + 1]);

            switch (operador) {
                case "+" -> resultado += siguienteNumero;
                case "-" -> resultado -= siguienteNumero;
                case "*" -> resultado *= siguienteNumero;
                case "/" -> {
                    if (siguienteNumero == 0) throw new ArithmeticException("División por cero");
                    resultado /= siguienteNumero;
                }
                default -> throw new IllegalArgumentException("Operador inválido: " + operador);
            }
        }

        return resultado;
    }
    private void aplicarDescuento(double porcentaje) {
    int selectedRow = tablaVisor.getSelectedRow();
    if (selectedRow != -1) {
        DefaultTableModel model = (DefaultTableModel) tablaVisor.getModel();
        Object precioObj = model.getValueAt(selectedRow, 1);

        if (precioObj instanceof Number) {
            double precioOriginal = ((Number) precioObj).doubleValue();
            double descuento = precioOriginal * porcentaje;
            double precioConDescuento = precioOriginal - descuento;

            total -= precioOriginal;
            total += precioConDescuento;

            model.setValueAt(precioConDescuento, selectedRow, 1);
            model.setValueAt((int)(porcentaje * 100) + "%", selectedRow, 2);
            precioTotal.setText(String.format("%.2f", total));
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona un producto o bono para aplicar el descuento.");
    }
}
    private void aplicarDescuentoCustomizado() {
        String texto = visorOperacion.getText().trim();
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Introduce un porcentaje en el visor para aplicar el descuento personalizado.");
            return;
        }

        try {
            double porcentaje = Double.parseDouble(texto) / 100.0; // convierte a decimal
            aplicarDescuento(porcentaje);
            visorOperacion.setText(""); // Limpia el visor después de aplicar el descuento
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El valor introducido no es un número válido.");
        }
    }
    private void limpiarVenta() {
        DefaultTableModel model = (DefaultTableModel) tablaVisor.getModel();
        model.setRowCount(0); // Vacía la tabla
        total = 0.0;
        precioTotal.setText("0.00");
        visorOperacion.setText("");
    }
    private void registrarPagoEfectivoEnStripe(double cantidad) {
        try {
            URL url = new URL("http://localhost:8080/venta/registrar-pago-efectivo?cantidad=" + cantidad);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();
            String linea;
            while ((linea = in.readLine()) != null) {
                response.append(linea);
            }
            in.close();

            JOptionPane.showMessageDialog(this, response.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error registrando en Stripe: " + e.getMessage());
        }
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelNumeros = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        panelSimbolos = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        visorOperacion = new javax.swing.JTextField();
        precioTotal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        descuentoVeinticinco = new javax.swing.JButton();
        descuentoCincuenta = new javax.swing.JButton();
        descuentoCustom = new javax.swing.JButton();
        pagoTarjeta = new javax.swing.JButton();
        psagoEfectivo = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        descuentoVeinticinco1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        panelVisor = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        tipoBono = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        productos = new javax.swing.JComboBox<>();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVisor = new javax.swing.JTable();
        jButton20 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(50, 50, 50, 50));
        setLocation(new java.awt.Point(50, 50));
        setPreferredSize(new java.awt.Dimension(640, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelNumeros.setOpaque(false);

        jButton7.setText("7");
        jButton7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("8");
        jButton8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("9");
        jButton9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton6.setText("6");
        jButton6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton5.setText("5");
        jButton5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setText("4");
        jButton4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setText("1");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("2");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("3");
        jButton3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton11.setText(".");
        jButton11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton11MouseClicked(evt);
            }
        });
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton10.setText("0");
        jButton10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton10MouseClicked(evt);
            }
        });
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelNumerosLayout = new javax.swing.GroupLayout(panelNumeros);
        panelNumeros.setLayout(panelNumerosLayout);
        panelNumerosLayout.setHorizontalGroup(
            panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNumerosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNumerosLayout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelNumerosLayout.createSequentialGroup()
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelNumerosLayout.createSequentialGroup()
                        .addGroup(panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelNumerosLayout.setVerticalGroup(
            panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNumerosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNumerosLayout.createSequentialGroup()
                        .addGap(0, 63, Short.MAX_VALUE)
                        .addGroup(panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNumerosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(panelNumeros, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 220, 240));

        panelSimbolos.setOpaque(false);
        panelSimbolos.setPreferredSize(new java.awt.Dimension(380, 440));

        jButton12.setText("x");
        jButton12.setPreferredSize(new java.awt.Dimension(42, 24));
        jButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton12MouseClicked(evt);
            }
        });
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        clear.setText("C");
        clear.setPreferredSize(new java.awt.Dimension(45, 24));
        clear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearMouseClicked(evt);
            }
        });
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        jButton14.setText("=");
        jButton14.setPreferredSize(new java.awt.Dimension(45, 24));
        jButton14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton14MouseClicked(evt);
            }
        });
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("-");
        jButton15.setPreferredSize(new java.awt.Dimension(45, 24));
        jButton15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton15MouseClicked(evt);
            }
        });
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("÷");
        jButton16.setMaximumSize(new java.awt.Dimension(45, 67));
        jButton16.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton16.setPreferredSize(new java.awt.Dimension(45, 24));
        jButton16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton16MouseClicked(evt);
            }
        });
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        precioTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        precioTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        precioTotal.setText("0.00");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("€");

        descuentoVeinticinco.setText("10%");
        descuentoVeinticinco.setPreferredSize(new java.awt.Dimension(42, 24));
        descuentoVeinticinco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                descuentoVeinticincoMouseClicked(evt);
            }
        });
        descuentoVeinticinco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descuentoVeinticincoActionPerformed(evt);
            }
        });

        descuentoCincuenta.setText("50%");
        descuentoCincuenta.setPreferredSize(new java.awt.Dimension(42, 24));
        descuentoCincuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                descuentoCincuentaMouseClicked(evt);
            }
        });
        descuentoCincuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descuentoCincuentaActionPerformed(evt);
            }
        });

        descuentoCustom.setText("%");
        descuentoCustom.setPreferredSize(new java.awt.Dimension(42, 24));
        descuentoCustom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                descuentoCustomMouseClicked(evt);
            }
        });
        descuentoCustom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descuentoCustomActionPerformed(evt);
            }
        });

        pagoTarjeta.setText("TARJETA");
        pagoTarjeta.setPreferredSize(new java.awt.Dimension(42, 24));
        pagoTarjeta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pagoTarjetaMouseClicked(evt);
            }
        });
        pagoTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagoTarjetaActionPerformed(evt);
            }
        });

        psagoEfectivo.setText("EFECTIVO");
        psagoEfectivo.setPreferredSize(new java.awt.Dimension(42, 24));
        psagoEfectivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                psagoEfectivoMouseClicked(evt);
            }
        });
        psagoEfectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                psagoEfectivoActionPerformed(evt);
            }
        });

        jButton19.setText("+");
        jButton19.setPreferredSize(new java.awt.Dimension(45, 24));
        jButton19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton19MouseClicked(evt);
            }
        });
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        descuentoVeinticinco1.setText("25%");
        descuentoVeinticinco1.setPreferredSize(new java.awt.Dimension(42, 24));
        descuentoVeinticinco1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                descuentoVeinticinco1MouseClicked(evt);
            }
        });
        descuentoVeinticinco1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descuentoVeinticinco1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSimbolosLayout = new javax.swing.GroupLayout(panelSimbolos);
        panelSimbolos.setLayout(panelSimbolosLayout);
        panelSimbolosLayout.setHorizontalGroup(
            panelSimbolosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSimbolosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSimbolosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSimbolosLayout.createSequentialGroup()
                        .addGroup(panelSimbolosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelSimbolosLayout.createSequentialGroup()
                                .addGroup(panelSimbolosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelSimbolosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)))
                            .addComponent(visorOperacion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelSimbolosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSimbolosLayout.createSequentialGroup()
                                .addComponent(psagoEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pagoTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSimbolosLayout.createSequentialGroup()
                                .addGroup(panelSimbolosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelSimbolosLayout.createSequentialGroup()
                                        .addComponent(descuentoVeinticinco, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(descuentoVeinticinco1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(panelSimbolosLayout.createSequentialGroup()
                                        .addComponent(descuentoCincuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(descuentoCustom, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(24, 24, 24))))
                    .addGroup(panelSimbolosLayout.createSequentialGroup()
                        .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(precioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel3)
                        .addGap(0, 11, Short.MAX_VALUE))))
        );
        panelSimbolosLayout.setVerticalGroup(
            panelSimbolosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSimbolosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSimbolosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(visorOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(psagoEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pagoTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(panelSimbolosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descuentoVeinticinco, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descuentoVeinticinco1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSimbolosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelSimbolosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(descuentoCincuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(descuentoCustom, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelSimbolosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSimbolosLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel3))
                    .addGroup(panelSimbolosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSimbolosLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(panelSimbolosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precioTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12))
        );

        getContentPane().add(panelSimbolos, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, 390, 240));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setText("Caja");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        panelVisor.setOpaque(false);

        jLabel9.setText("Tipo de bono");

        tipoBono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoBonoActionPerformed(evt);
            }
        });

        jLabel11.setText("Productos");

        jButton17.setText("+");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setText("+");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        tablaVisor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Concepto", "Precio", "Descuento"
            }
        ));
        jScrollPane1.setViewportView(tablaVisor);

        jButton20.setText("-");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelVisorLayout = new javax.swing.GroupLayout(panelVisor);
        panelVisor.setLayout(panelVisorLayout);
        panelVisorLayout.setHorizontalGroup(
            panelVisorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVisorLayout.createSequentialGroup()
                .addGroup(panelVisorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVisorLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(panelVisorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(panelVisorLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(productos, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelVisorLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(tipoBono, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelVisorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton17)
                            .addComponent(jButton18)))
                    .addGroup(panelVisorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton20)
                .addContainerGap())
        );
        panelVisorLayout.setVerticalGroup(
            panelVisorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVisorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelVisorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton20)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelVisorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tipoBono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17))
                .addGap(20, 20, 20)
                .addGroup(panelVisorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVisorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(productos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton18))
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(panelVisor, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 590, 230));

        jButton13.setText("Menú Principal");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoPrincipal.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setPreferredSize(new java.awt.Dimension(640, 570));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
        visorOperacion.setText("");
    }//GEN-LAST:event_clearActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        ejecutarSimbolo("-");
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        ejecutarSimbolo("/");
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        
        TipoEntrada entrada = (TipoEntrada) tipoBono.getSelectedItem();
    if (entrada != null) {
        DefaultTableModel model = (DefaultTableModel) tablaVisor.getModel();
        model.addRow(new Object[]{entrada.getTipo(), entrada.getPrecio()});
        total += entrada.getPrecio();
        precioTotal.setText(String.format("%.2f", total));
    }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        
        Producto prod = (Producto) productos.getSelectedItem();
    if (prod != null) {
        DefaultTableModel model = (DefaultTableModel) tablaVisor.getModel();
        model.addRow(new Object[]{prod.getNombre(), prod.getPrecio()});
        total += prod.getPrecio();
        precioTotal.setText(String.format("%.2f", total));
    }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        
        int selectedRow = tablaVisor.getSelectedRow();
    if (selectedRow != -1) {
        DefaultTableModel model = (DefaultTableModel) tablaVisor.getModel();
        Object precioObj = model.getValueAt(selectedRow, 1);
        if (precioObj instanceof Number) {
            total -= ((Number) precioObj).doubleValue();
            precioTotal.setText(String.format("%.2f", total));
        }
        model.removeRow(selectedRow);
    }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void tipoBonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoBonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoBonoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        numeroPulsado("1");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        

    }//GEN-LAST:event_jButton1MouseClicked

    private void clearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_clearMouseClicked

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        resolverOperacionManual();
     
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton14MouseClicked

    private void jButton12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton12MouseClicked

    private void jButton15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15MouseClicked

    private void jButton16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton16MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton6MouseClicked

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton7MouseClicked

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton8MouseClicked

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton9MouseClicked

    private void jButton10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton10MouseClicked

    private void jButton11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton11MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        numeroPulsado("2");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        numeroPulsado(".");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        numeroPulsado("0");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        numeroPulsado("9");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        numeroPulsado("8");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        numeroPulsado("7");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        numeroPulsado("6");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        numeroPulsado("5");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        numeroPulsado("3");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        numeroPulsado("4");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        ejecutarSimbolo("*");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void descuentoVeinticincoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_descuentoVeinticincoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_descuentoVeinticincoMouseClicked

    private void descuentoVeinticincoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descuentoVeinticincoActionPerformed
        // TODO add your handling code here:
        aplicarDescuento(0.10); // para 10%

    }//GEN-LAST:event_descuentoVeinticincoActionPerformed

    private void descuentoCincuentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_descuentoCincuentaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_descuentoCincuentaMouseClicked

    private void descuentoCincuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descuentoCincuentaActionPerformed
        // TODO add your handling code here:
        aplicarDescuento(0.5); // para -50%

    }//GEN-LAST:event_descuentoCincuentaActionPerformed

    private void descuentoCustomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_descuentoCustomMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_descuentoCustomMouseClicked

    private void descuentoCustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descuentoCustomActionPerformed
        // TODO add your handling code here:
        aplicarDescuentoCustomizado();
    }//GEN-LAST:event_descuentoCustomActionPerformed

    private void pagoTarjetaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pagoTarjetaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pagoTarjetaMouseClicked

    private void pagoTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagoTarjetaActionPerformed
        // TODO add your handling code here:
        if (total == 0.0) {
        JOptionPane.showMessageDialog(this, "No hay productos para cobrar.");
        return;
    }
        // Paso 1: Simulación de inserción de tarjeta
        JOptionPane.showMessageDialog(this, "Inserte o acerque la tarjeta al lector...");
    
        // Paso 2: Espera breve para simular lectura
        try {
            Thread.sleep(1500); // 1.5 segundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Paso 3: Simulación de procesamiento
        JOptionPane.showMessageDialog(this, "Procesando el pago...");

         // Inicializar Stripe
        StripService.inicializarStripe();

        try {
            String resultado = StripService.simularPago(total);

            if ("requires_payment_method".equals(resultado)) {
                JOptionPane.showMessageDialog(this, "Pago simulado pendiente (requiere método de pago).");
            } else if ("succeeded".equals(resultado)) {
                JOptionPane.showMessageDialog(this, "Pago con tarjeta realizado con éxito.");
                limpiarVenta();
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ Estado del pago: " + resultado);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al procesar el pago: " + e.getMessage());
        }
    }//GEN-LAST:event_pagoTarjetaActionPerformed

    private void psagoEfectivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_psagoEfectivoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_psagoEfectivoMouseClicked

    private void psagoEfectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_psagoEfectivoActionPerformed
        // TODO add your handling code here:
        if (total == 0.0) {
        JOptionPane.showMessageDialog(this, "No hay productos para cobrar.");
        return;
        }

        String input = JOptionPane.showInputDialog(this, "Introduce el importe recibido en efectivo (€):");

        if (input == null) return; // Cancelado

        try {
            double recibido = Double.parseDouble(input);
            if (recibido < total) {
                JOptionPane.showMessageDialog(this, "El importe recibido es insuficiente.");
                return;
            }

            double cambio = recibido - total;

            // Simulación de apertura de cajón
            JOptionPane.showMessageDialog(this, "Cajón abierto.\n Cambio a devolver: " + String.format("%.2f", cambio) + " €");

            //REGISTRAR en Stripe
            registrarPagoEfectivoEnStripe(total);

            // Reiniciar
            limpiarVenta();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Introduce un número válido.");
        }
    }//GEN-LAST:event_psagoEfectivoActionPerformed

    private void jButton19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton19MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton19MouseClicked

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        ejecutarSimbolo("+");
    }//GEN-LAST:event_jButton19ActionPerformed

    private void descuentoVeinticinco1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_descuentoVeinticinco1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_descuentoVeinticinco1MouseClicked

    private void descuentoVeinticinco1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descuentoVeinticinco1ActionPerformed
        // TODO add your handling code here:
         aplicarDescuento(0.25); 
    }//GEN-LAST:event_descuentoVeinticinco1ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        Principal p = new Principal();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton13ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clear;
    private javax.swing.JButton descuentoCincuenta;
    private javax.swing.JButton descuentoCustom;
    private javax.swing.JButton descuentoVeinticinco;
    private javax.swing.JButton descuentoVeinticinco1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton pagoTarjeta;
    private javax.swing.JPanel panelNumeros;
    private javax.swing.JPanel panelSimbolos;
    private javax.swing.JPanel panelVisor;
    private javax.swing.JTextField precioTotal;
    private javax.swing.JComboBox<Producto> productos;
    private javax.swing.JButton psagoEfectivo;
    private javax.swing.JTable tablaVisor;
    private javax.swing.JComboBox<TipoEntrada> tipoBono;
    private javax.swing.JTextField visorOperacion;
    // End of variables declaration//GEN-END:variables
}
