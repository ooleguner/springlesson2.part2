package lesson2_1;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by oleg on 09.07.2019.
 */

public class Service {
    private Long id;
    private String name;
    private List paramsToCall;

    public Service() {
    }


    public Service(Long id, String name, List paramsToCall) {
        this.id = id;
        this.name = name;
        this.paramsToCall = paramsToCall;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getParamsToCall() {
        return paramsToCall;
    }

    public void setParamsToCall(List paramsToCall) {
        this.paramsToCall = paramsToCall;
    }


    @Override
    public String toString() {
        return "Service {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", paramsToCall=" + paramsToCall +
                '}';
    }
}
