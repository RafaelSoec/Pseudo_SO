package unb.modules.memory.enums;

public enum PaginationAlgorithmEnum {
    FIFO("FIFO"),
    Second_Chance("Second Chance"),
    LRU("LRU");

    private String name;
    PaginationAlgorithmEnum(String name) { this.setName(name); }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
