/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tcp;

/**
 *
 * @author Thắng Bùi
 */
public class Nha {
    private String soNha;
    private float giaBan;
    private boolean tinhTrang;

    public Nha() {
    }

    public Nha(String soNha, float giaBan, boolean tinhTrang) {
        this.soNha = soNha;
        this.giaBan = giaBan;
        this.tinhTrang = tinhTrang;
    }

    public String getSoNha() {
        return soNha;
    }

    public void setSoNha(String soNha) {
        this.soNha = soNha;
    }

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }

    public boolean isTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
    
    public String showInfo() {
        return "So nha: "+soNha+
                "\nGia ban: "+giaBan+
                "\nTinh trang: "+tinhTrang+"\n";
    }

    @Override
    public String toString() {
        return "Nha{" + "soNha=" + soNha + ", giaBan=" + giaBan + ", tinhTrang=" + tinhTrang + '}';
    }
    
    
}
