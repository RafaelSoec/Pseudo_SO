package unb.modules.inputoutput.dtos;

import unb.modules.process.dtos.Procedure;

import java.util.List;

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
