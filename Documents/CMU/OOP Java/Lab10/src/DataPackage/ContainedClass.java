package DataPackage;

public class ContainedClass {
    private String ccString = "";

    public ContainedClass() {}

    public ContainedClass(ContainedClass cc) {
        this.ccString = cc.ccString;
    }

    public String getCcString() {
        return ccString;
    }

    public void setCcString(String ccString) {
        this.ccString = ccString;
    }
}
