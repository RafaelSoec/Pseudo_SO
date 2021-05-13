package unb.modules.inputoutput.dtos;

import unb.modules.process.dtos.Procedure;

import java.util.List;

/** Jean Rodrigues Magalhães - 15/0079923
 *
 * DTO que armazena as informações dos cilindros, bem como último cilindro, cilindro onde o braço se encontra e uma lista com os numeros dos seguintes cilindros
 * requisitados.
 * */
public class DiskDriver {
    private int lastCylinder;
    private int currentCylinder;
    private List<Integer> requests;

    public DiskDriver() {

    }

    public int getLastCylinder() {
        return this.lastCylinder;
    }

    public void setLastCylinder(int lastCylinder) {
        this.lastCylinder = lastCylinder;
    }

    public int getcurrentCylinder() {
        return this.currentCylinder;
    }

    public void setcurrentCylinder(int currentCylinder) {
        this.currentCylinder = currentCylinder;
    }

    public List<Integer> getRequests() {
        return this.requests;
    }

    public void setRequests(List<Integer> requests) {
        this.requests = requests;
    }
}
