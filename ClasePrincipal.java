/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sara;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Alexara
 */
public class ClasePrincipal extends Application{
       

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        
    }
    
    
    @Override
    public void start(Stage stage) throws Exception {
        VBox v = new VBox(10);
        Label name=new Label("Nombre: ");
        TextField tfNombre = new TextField("");
        Label passwd1=new Label("Password: ");
        TextField tfPasswd = new TextField("");
        Button btnLogin=new Button("Entrar");
        Label mensaje = new Label("");
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                String nombre = tfNombre.getText();
                String password = tfPasswd.getText();
                Usuario u=AccesoBD.logearUsuario(nombre,password);
                if(u==null)
                {
                    System.out.println("Error");           
                }
                else
                {//Si etá bien logueado
                    mensaje.setText("Se ha logeado correctamente "+nombre);
                    mostrarMenu();
                }
            }

        });
        
        v.getChildren().addAll(name,tfNombre,passwd1,tfPasswd,btnLogin,mensaje);
        Scene sc=new Scene(v,300,200);
        stage.setScene(sc);
        stage.show();
        
    }
    
    private void mostrarMenu() {
            Stage ventana=new Stage();
            VBox v=new VBox(20);
            Button btnClientes=new Button("Clientes");
            Button btnArticulos=new Button("Articulos");
            btnArticulos.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    pantallaArticulos();
                }

                
            });
            
            btnClientes.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    pantallaClientes();
                }

                
            });
            v.getChildren().addAll(btnClientes,btnArticulos);
            Scene sc=new Scene(v,200,200);
            ventana.setScene(sc);
            ventana.show();
        
    }
    
    private void pantallaArticulos() {
            Stage ventana=new Stage();
            VBox v=new VBox(20);
            TextField texto=new TextField();
            Button btnBuscar=new Button("Buscar");
            Button modificar=new Button("Modificar Producto");
            Label codigo=new Label("Codigo Articulo");
            Label codigo_poner=new Label();
            Label descripcion=new Label("Descripcion de articulo");
            Label descripcion_poner=new Label();
            Label precio=new Label("Precio de articulo");
            Label precio_poner=new Label();
            btnBuscar.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    String nombre_buscado=texto.getText();
                    ArrayList<Articulo> lista_articulos_buscados=AccesoBD.recuperarArticulos(nombre_buscado);
                    //EntradaSalida.mostrarContactos(lista_articulos_buscados);
                    for (Articulo articulo : lista_articulos_buscados) {
                    //label.setText(""+articulo);
                    
                    //Pongo los datos buscados en los label
                    codigo_poner.setText(articulo.getCodigoArt());
                    descripcion_poner.setText(articulo.getDescrArt());
                    precio_poner.setText(articulo.getPrecioArt());
                    
                    //Cuando le de al boton que me saque los textfield para poder modificarlos
                    modificar.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        //String buscador=texto.getText();
                        String codigo1=codigo_poner.getText();
                        String descripcion1=descripcion_poner.getText();
                        String precio1=codigo_poner.getText();
                        //String nueva_modificacion=ClasePrincipal.modificar();
                        pantallaModificarArticulos(codigo1,descripcion1,precio1);
                    }

                });
                    }
                }
            });
            
            v.getChildren().addAll(texto,btnBuscar,modificar,codigo,codigo_poner,descripcion,descripcion_poner,precio,precio_poner);
            Scene sc=new Scene(v,300,400);
            ventana.setScene(sc);
            ventana.show();
    }
    
    private void pantallaModificarArticulos(String codigo1, String descripcion1, String precio1) {
        Stage ventana=new Stage();
        VBox v=new VBox(20);
        Label titulo_codigo=new Label();
        //Codigono editable unico
        Label codigo=new Label(codigo1);
         Label titulo_descripcion=new Label();
         //descripcion
        TextField descripcion=new TextField(descripcion1);
        Label titulo_precio=new Label();
        //Precio
        TextField precio=new TextField(precio1);
        Button guardar=new Button("Guardar Cambios");
        Button volver=new Button("Menu");
        //Cuando le de al boton guardar
        guardar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                String codigo_unico=codigo.getText();
                String nueva_descripcion=descripcion.getText();
                String nuevo_precio=precio.getText();
                AccesoBD.actualizarProductos(codigo_unico,nueva_descripcion,nuevo_precio);
                if(nueva_descripcion == null){
                    System.out.println("Ha habido un error, por favor intentelo de nuevo");
                }else{
                    System.out.println("Se han realizado los cambios corectamente");
                }
                
            }
            
        });
        
        volver.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                mostrarMenu();
            }
            });
        
        v.getChildren().addAll(titulo_codigo,codigo,titulo_descripcion,descripcion,titulo_precio,precio,guardar,volver);
        Scene sc=new Scene(v,300,400);
        ventana.setScene(sc);
        ventana.show();
    }
    
    private void pantallaClientes() {
        Stage ventana=new Stage();
            VBox v=new VBox(20);
            TextField texto=new TextField();
            Label label=new Label();
            Label nif=new Label("NIF Cliente");
            Label nif_poner=new Label();
            Label domicilio=new Label("Domicilio Cliente");
            Label domicilio_poner=new Label();
            Label nombre=new Label("Nombre Cliente");
            Label nombre_poner=new Label();
            Button btnBuscar=new Button("Buscar");
            Button insertar=new Button("insertar Cliente");
            Button btnVolver=new Button("Menu");
            Button modificar1=new Button("Modificar Cliente");
            btnBuscar.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    String nombre_buscado=texto.getText();
                    ArrayList<Cliente> lista_clientes_buscados=AccesoBD.recuperarClientes(nombre_buscado);
                    //EntradaSalida.mostrarContactos(lista_articulos_buscados);
                    for (Cliente cliente : lista_clientes_buscados) {
                    //label.setText(""+cliente);
                    nif_poner.setText(cliente.getNIFCliente());
                    domicilio_poner.setText(cliente.getDomicilioCliente());
                    nombre_poner.setText(cliente.getNombreCliente());
                    
                    modificar1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        //String buscador=texto.getText();
                        String nif1=nif_poner.getText();
                        String domicilio1=domicilio_poner.getText();
                        String nombre1=nombre_poner.getText();
                        //String nueva_modificacion=ClasePrincipal.modificar();
                        pantallaModificarClientes(nif1,domicilio1,nombre1);
                    }

                        

                    });
                    }
                }
            });
            
            insertar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
                public void handle(ActionEvent t) {
                    pantallaInsertarClientes();
                }

            
            });
            
            btnVolver.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                mostrarMenu();
            }
            });
            v.getChildren().addAll(texto,btnBuscar,btnVolver,nif,nif_poner,domicilio,domicilio_poner,nombre,nombre_poner,modificar1,insertar);
            Scene sc=new Scene(v,300,400);
            ventana.setScene(sc);
            ventana.show();
    }
    
    private void pantallaModificarClientes(String nif1, String domicilio1, String nombre1) {
        Stage ventana=new Stage();
        VBox v=new VBox(20);
        Label titulo_codigo=new Label();
        //Codigono editable unico
        Label nif=new Label(nif1);
         Label titulo_descripcion=new Label();
         //descripcion
        TextField domicilio=new TextField(domicilio1);
        Label titulo_precio=new Label();
        //Precio
        TextField nombre=new TextField(nombre1);
        
        Button guardar=new Button("Guardar Cambios");
        Button volver=new Button("Menu");
        Label mensaje=new Label();
        //Cuando le de al boton guardar
        guardar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                String nif_unico=nif.getText();
                String nuevo_domicilio=domicilio.getText();
                String nuevo_nombre=nombre.getText();
                AccesoBD.actualizarClientes(nif_unico,nuevo_domicilio,nuevo_nombre);
                if(nuevo_domicilio == null){
                    mensaje.setText("Ha hbido un error");
                }else{
                    mensaje.setText("Se han realizado los cambios corectamente");
                }
            }
            
        });
        
        
        
        volver.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                mostrarMenu();
            }
            });
        
        v.getChildren().addAll(titulo_codigo,nif,titulo_descripcion,domicilio,titulo_precio,nombre,guardar,volver);
        Scene sc=new Scene(v,300,400);
        ventana.setScene(sc);
        ventana.show();
    }
    
    private void pantallaInsertarClientes() {
        Stage ventana=new Stage();
        VBox v=new VBox(20);
        Label titulo=new Label("NUEVO CLIENTE");
        Label nif=new Label("NIF Cliente");
        TextField nif_poner=new TextField();
        Label domicilio=new Label("Domicilio Cliente");
        TextField domicilio_poner=new TextField();
        Label nombre=new Label("Nombre Cliente");
        TextField nombre_poner=new TextField();
        Button volver=new Button("Menu");
        Button insertar=new Button("Insertar Cliente");
        
        insertar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                String nuevo_nif=nif_poner.getText();
                String nuevo_domicilio=domicilio_poner.getText();
                String nuevo_nombre=nombre_poner.getText();
                AccesoBD.insertarCliente(nuevo_nif,nuevo_domicilio,nuevo_nombre);
            }
        });
        v.getChildren().addAll(titulo,nif,nif_poner,domicilio,domicilio_poner,nombre,nombre_poner,volver,insertar);
        Scene sc=new Scene(v,300,400);
        ventana.setScene(sc);
        ventana.show();
    }
    
    
}
