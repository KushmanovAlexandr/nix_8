package ua.com.alevel.entity;

import ua.com.alevel.dynamicarray.MyList;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Client extends Bank {
    public int getIdBanks;
    private int id;
    private String fullName;
    private MyList<Integer> idBanks = new MyList<>();
    private boolean hasOneBank = true;

    @Override
    public String toString() {
        return "Client{" +
                "getIdBanks=" + getIdBanks +
                ", id=" + id +
                ", fullName='" + fullName + '\'' +
                ", idBanks=" + idBanks +
                ", hasOneBank=" + hasOneBank +
                '}';
    }
}