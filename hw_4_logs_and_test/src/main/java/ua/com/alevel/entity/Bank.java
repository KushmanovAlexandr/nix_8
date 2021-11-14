package ua.com.alevel.entity;

import ua.com.alevel.dynamicarray.MyList;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Bank extends Object{
    private int id;
    private String title;
    private String type;
    private int countOfBranches;
    private MyList<Integer> idClients = new MyList<>();
    private boolean hasOneClient = true;

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", countOfBranches=" + countOfBranches +
                ", idClients=" + idClients +
                ", hasOneClient=" + hasOneClient +
                '}';
    }
}