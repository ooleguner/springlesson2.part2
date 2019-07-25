package lesson3;

import org.springframework.context.annotation.Bean;

/**
 * Created by oleg on 22.07.2019.
 */

public interface DBConnector {

    void connect();
    void save();
    void disconnect();
}

