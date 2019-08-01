package lesson3_HW.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lesson3_HW.AppException.BadRequestException;
import lesson3_HW.beans.Storage;
import lesson3_HW.dao.GeneralDao;
import lesson3_HW.dao.StorageDaoImpl;
import lesson3_HW.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;


public class StorageController {

    StorageService storageService;


    @Autowired
    StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping (method = RequestMethod.POST, value = "/saveStorage", produces = "text,plain")
    @ResponseBody
    public String save(HttpServletRequest request) {
        Storage currentStorage = null;
        Storage storage = null;
        try {
            currentStorage = fromJson(request);
            storage=storageService.save(currentStorage);
        } catch (IOException e) {
            return "Storage not saved. Can not convert from JSON";
        } catch (BadRequestException e){
            return "Storage not saved. " + e.toString();
        }
        return "Storage " + storage.toString() + " saved in DB";
    }

    
    private Storage fromJson(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = request.getReader()){
            String line;
            while ((line=reader.readLine())!=null){
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper= new ObjectMapper();
        String input = objectMapper.writeValueAsString(sb.toString());
        return objectMapper.convertValue(input,Storage.class);
    }


    public void delete(long id) throws BadRequestException {
        storageService.delete(id);
    }
}
