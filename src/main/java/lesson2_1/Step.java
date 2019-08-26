package lesson2_1;
import java.util.Map;

/**
 * Created by oleg on 09.07.2019.
 */
public class Step {

    private Long id;
    private Service serviceFrom;
    private Service serviceTo;
    private Map paramsServiceFrom;
    private Map paramsServiceTo;

    public Step(Long id, Service serviceFrom, Service serviceTo) {
        this.id = id;
        this.serviceFrom = serviceFrom;
        this.serviceTo = serviceTo;
    }

    public Step() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Service getServiceFrom() {
        return serviceFrom;
    }

    public void setServiceFrom(Service serviceFrom) {
        this.serviceFrom = serviceFrom;
    }

    public Service getServiceTo() {
        return serviceTo;
    }

    public void setServiceTo(Service serviceTo) {
        this.serviceTo = serviceTo;
    }

    public Map getParamsServiceFrom() {
        return paramsServiceFrom;
    }

    public void setParamsServiceFrom(Map paramsServiceFrom) {
        this.paramsServiceFrom = paramsServiceFrom;
    }

    public Map getParamsServiceTo() {
        return paramsServiceTo;
    }

    public void setParamsServiceTo(Map paramsServiceTo) {
        this.paramsServiceTo = paramsServiceTo;
    }


    @Override
    public String toString() {
        return "Step{" +
                "<br>id=" + id +
                ", <br>serviceFrom=" + serviceFrom +
                ", <br>serviceTo=" + serviceTo +
                ", <br>paramsServiceFrom=" + paramsServiceFrom +
                ", <br>paramsServiceTo=" + paramsServiceTo +
                '}';
    }
}
