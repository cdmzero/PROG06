/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog06;


/**
 *
 * @author jfunez
 */

import java.io.Serializable;

public class CuerpoCeleste implements Serializable{
    
    
    private int codigoCuerpo;
    private String nombre;
    private String tipoCuerpo;
    private long diametro;

    public CuerpoCeleste() {
        this.codigoCuerpo = 1001;
        this.nombre = "test";
        this.tipoCuerpo = "luna";
        this.diametro = 1234567;
    }

    
     public CuerpoCeleste(int codigoCuerpo, String nombre, String tipoCuerpo, long diametro) {
        this.codigoCuerpo = codigoCuerpo;
        this.nombre = nombre;
        this.tipoCuerpo = tipoCuerpo;
        this.diametro = diametro;
    }
     
     public CuerpoCeleste(CuerpoCeleste object) {
        this.codigoCuerpo = object.codigoCuerpo;
        this.nombre = object.nombre;
        this.tipoCuerpo = object.tipoCuerpo;
        this.diametro = object.diametro;
    }
    
    
    

    public void setCodigoCuerpo(int codigoCuerpo) {
        this.codigoCuerpo = codigoCuerpo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipoCuerpo(String tipoCuerpo) {
        this.tipoCuerpo = tipoCuerpo;
    }

    public void setDiametro(long diametro) {
        this.diametro = diametro;
    }

    public int getCodigoCuerpo() {
        return codigoCuerpo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipoCuerpo() {
        return tipoCuerpo;
    }

    public long getDiametro() {
        return diametro;
    }



    @Override
    public String toString() {
        return "CuerpoCeleste{" + "codigoCuerpo=" + codigoCuerpo + ", nombre=" + nombre + ", tipoCuerpo=" + tipoCuerpo + ", diametro=" + diametro + '}';
    }
    
    
    
}
