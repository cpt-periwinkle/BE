package DataPackage;

import java.util.ArrayList;
import java.util.Arrays;

public class Data {
    private int iValue = 10;
    private String sValue = "a string";
    private int[] iList = {12, 15, 20};
    private ArrayList<Integer> aList = new ArrayList<>();
    private ContainedClass cc = new ContainedClass();

    Data() {
        for (int i=0; i<iList.length; i++) {
            aList.add(iList[i]);
        }
        cc.setCcString("a contained string");
    }

    Data(Data d) {
        this.iValue = d.iValue;
        this.sValue = d.sValue;
        this.iList = Arrays.copyOf(d.iList, d.iList.length);
        this.aList = new ArrayList<>(d.aList);
        this.cc  = new ContainedClass(d.cc);
    }

    @Override
    public String toString() {
        return "DataPackage.Data{" +
                "iValue=" + iValue +
                ", sValue='" + sValue + '\'' +
                ", iList=" + Arrays.toString(iList) +
                ", aList=" + aList +
                ", cc = " + cc.getCcString() +
                '}';
    }

    public int getiValue() {
        return iValue;
    }

    public String getsValue() {
        return sValue;
    }

    public int[] getiList() {
        return Arrays.copyOf(iList, iList.length);
    }

    public ArrayList<Integer> getaList() {
        return new ArrayList<>(aList);
    }

    public ContainedClass getCc() {
        return new ContainedClass(cc);
    }

    public void setiValue(int iValue) {
        if (iValue >= 0) {
            this.iValue = iValue;
        }
    }

    public void setsValue(String sValue) {
        if (sValue.length() <= 10) {
            this.sValue = sValue;
        }
    }

    public void setiList(int[] iList) {
        boolean valid_flag = true;
        if (iList.length > 4) return;
        for (int i : iList) {
            if (i < 10 || i > 20) {
                valid_flag = false;
                break;
            }
        }
        if (valid_flag) {
            this.iList = Arrays.copyOf(iList, iList.length);
        }
    }

    public void setaList(ArrayList<Integer> aList) {
        this.aList = aList;
    }

    public void setCc(ContainedClass cc) {
        this.cc = new ContainedClass(cc);
    }
}
