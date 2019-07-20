package lesson2_1;

import java.util.List;

/**
 * Created by oleg on 09.07.2019.
 */
public class Route {

    private String id;
    private List steps;

    public Route() {
    }

    public Route(String id, List steps) {
        this.id = id;
        this.steps = steps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List getSteps() {
        return steps;
    }

    public void setSteps(List steps) {
        this.steps = steps;
    }


    @Override
    public String toString() {
        return "Route{" +
                "<br>id='" + id + '\'' +
                ", <br>steps=" + steps +
                '}';
    }
}
