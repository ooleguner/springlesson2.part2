package lesson3_HW.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lesson3_HW.AppException.BadRequestException;
import lesson3_HW.beans.Storage;
import lesson3_HW.dao.GeneralDao;
import lesson3_HW.dao.StorageDaoImpl;
import lesson3_HW.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Controller
public class StorageController {

    StorageService storageService;

    @Autowired
    StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

/*
{"formatsSupported":"JPG",
"storageCountry":"USA",
"storageSize":"3000"}
 */
    @RequestMapping(method = RequestMethod.POST, value = "/saveStorage", produces = "text/plain")
    @ResponseBody
    public String save(HttpServletRequest request) {
        Storage currentStorage = null;
        Storage storage = null;
        try {
            currentStorage = fromJson(request);
            storage = storageService.save(currentStorage);
        } catch (IOException e) {
            return "Storage not saved. Can not convert from JSON";
        } catch (BadRequestException e) {
            return "Storage not saved. " + e.toString();
        }
        return "Storage " + storage.toString() + " saved in DB";
    }

/*
{"id":"260",
"formatsSupported":"updatedJPG",
"storageCountry":"updatedUSA",
"storageSize":"3500"}
 */
    @RequestMapping(method = RequestMethod.PUT, value = "/updateStorage", produces = "text/plain")
    @ResponseBody
    public String update(HttpServletRequest request) {
        Storage currentStorage = null;
        Storage storage = null;
        try {
            currentStorage = fromJson(request);
            storage = storageService.update(currentStorage);
        } catch (IOException e) {
            return "Storage not updated. Can not convert from JSON";
        } catch (BadRequestException e) {
            return "Storage not updated. " + e.toString();
        }
        return "Storage " + storage.toString() + " updated in DB";
    }


//  http://localhost:8080/deleteStorage?id=258
    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteStorage", produces = "text/plain")
    @ResponseBody
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        Long id;
        try {
            id = Long.parseLong(request.getParameter("id"));
            storageService.delete(id);
        } catch (BadRequestException e) {
            return "Storage not deleted. " + e.toString();
        }
        return "Storage with id " + id  + " delete from DB";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllStorages", produces = "text/plain")
    @ResponseBody
    public String getAll(HttpServletRequest request) {
        return storageService.gerAllStorages();
    }

    private Storage fromJson(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        ObjectMapper objectMapper = new ObjectMapper();
        String input;

        BufferedReader reader = request.getReader();
        while ((input = reader.readLine()) != null) {
            sb.append(input);
        }
        return objectMapper.readValue(sb.toString(), Storage.class);
    }
}
