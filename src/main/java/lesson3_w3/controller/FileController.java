package lesson3_w3.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import lesson2_2.model.Item;
import lesson3_w3.bean.File;
import lesson3_w3.bean.Storage;
import lesson3_w3.exceptions.ObjectPersistException;
import lesson3_w3.exceptions.ConditionException;
import lesson3_w3.service.FileService;
import lesson3_w3.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

@Controller
public class FileController {

    @Autowired
    FileService fileService;

    @Autowired
    StorageService storageService;

    /*
   {"name":"testSaveFile",
   "format":"doc",
   "size":"50" }
        */
    @RequestMapping(method = RequestMethod.POST, value = "/saveFile", produces = "text/plain")
    public @ResponseBody
    String save(HttpServletRequest request) {
        try {
            File file = mappingFile(request);
            File savedFile = fileService.save(file);
            return "File " + savedFile.toString() + " successfully saved is System.";
        } catch (IOException e) {
            return e.getMessage();
        } catch (ConditionException e) {
            return e.getMessage();
        }
    }

    private File mappingFile(HttpServletRequest req) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        ObjectMapper objectMapper = new ObjectMapper();
        String line;
        BufferedReader reader = req.getReader();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return objectMapper.readValue(stringBuilder.toString(), File.class);
    }

    // delete from system
    public void delete(long id) throws ObjectPersistException {
        fileService.delete(id);
    }

    public File update(File file) throws ObjectPersistException, ConditionException {
        return fileService.update(file);
    }

    /*
         http://localhost:8080/getFile?id=1000
    */
    @RequestMapping(method = RequestMethod.GET, value = "/getFile", produces = "text/plain")
    public @ResponseBody
    String findById(HttpServletRequest request) {
        return fileService.findById(Integer.parseInt(request.getParameter("id"))).toString();
    }

    /*
         http://localhost:8080/put?idStorage=452&idFile=968
    */
    @RequestMapping(method = RequestMethod.PUT, value = "/put", produces = "text/plain")
    public @ResponseBody
    String put(HttpServletRequest req) {
        try {
            Storage storage = storageService.findById(Integer.parseInt(req.getParameter("idStorage")));
            File file = fileService.findById(Integer.parseInt(req.getParameter("idFile")));
            return "File " + fileService.put(storage, file) + "is adding to storage with id " + req.getParameter("idStorage");
        } catch (ObjectPersistException e) {
            return e.getMessage();
        } catch (ConditionException e) {
            return e.getMessage();
        }
    }

    /*
             http://localhost:8080/delete?idStorage=452&idFile=968
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", produces = "text/plain")
    public @ResponseBody
    String delete(HttpServletRequest req) {
        try {
            Storage storage = storageService.findById(Integer.parseInt(req.getParameter("idStorage")));
            File file = fileService.findById(Integer.parseInt(req.getParameter("idFile")));
            fileService.deleteFromStorage(storage, file);
            return "File " + file.getId() + " was deleted from Storage " + storage.getId();
        } catch (ObjectPersistException e) {
            return e.getMessage();
        }
    }

    /*
             http://localhost:8080/transferAll?idStorageFrom=452&idStorageTo=453
    */
    @RequestMapping(method = RequestMethod.PUT, value = "/transferAll", produces = "text/plain")
    public @ResponseBody
    String transferAll(HttpServletRequest req) {

        try {
            Storage storageFrom = storageService.findById(Integer.parseInt(req.getParameter("idStorageFrom")));
            Storage storageTo = storageService.findById(Integer.parseInt(req.getParameter("idStorageTo")));
            fileService.transferAll(storageFrom, storageTo);
            return " All files from Storage id: " + storageFrom.getId() + " was transfered to Storage id: " + storageTo.getId();
        } catch (ObjectPersistException e) {

            return "TransferAll() not finished by reason : " + e.getMessage();
        } catch (ConditionException e) {
            return "TransferAll() not finished by reason : " + e.getMessage();
        }
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws ObjectPersistException, ConditionException {
        fileService.transferFile(storageFrom, storageTo, id);
    }

}
