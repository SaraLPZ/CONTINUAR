/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sara;

/**
 *
 * @author Alexara
 */
public class Articulo {
    private String CodigoArt,DescrArt,PrecioArt;

    public Articulo(String CodigoArt, String DescrArt, String PrecioArt) {
        this.CodigoArt = CodigoArt;
        this.DescrArt = DescrArt;
        this.PrecioArt = PrecioArt;
    }

    public String getCodigoArt() {
        return CodigoArt;
    }

    public void setCodigoArt(String CodigoArt) {
        this.CodigoArt = CodigoArt;
    }

    public String getDescrArt() {
        return DescrArt;
    }

    public void setDescrArt(String DescrArt) {
        this.DescrArt = DescrArt;
    }

    public String getPrecioArt() {
        return PrecioArt;
    }

    public void setPrecioArt(String PrecioArt) {
        this.PrecioArt = PrecioArt;
    }

    @Override
    public String toString() {
        return ""+CodigoArt+","+DescrArt+","+PrecioArt;
    }
    
    
}
