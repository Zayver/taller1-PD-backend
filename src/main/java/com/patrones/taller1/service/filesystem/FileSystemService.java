package com.patrones.taller1.service.filesystem;

import com.patrones.taller1.entity.Appointment;
import com.patrones.taller1.service.io.IOFactory;
import com.patrones.taller1.service.io.IOType;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

@Service
public class FileSystemService {
    @Autowired
    private IOFactory writerFactory;

    private static final String ROOT_NAME = "APPOINTMENTS";


    FileSystemService(){
        createDir(Paths.get(ROOT_NAME));
    }


    public void save(Appointment appointment, IOType type){
        val path = getInternalPath(appointment.getDate().replaceAll("/", ""));
        createDir(path);
        try{
            val filePath = path.resolve(appointment.getCc().toString());
            val writer = IOFactory.getManager(type);
            Files.write(filePath, writer.getBytes(appointment));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Iterable<Appointment> search(String date, IOType type){
        val path = getInternalPath(date.replaceAll("/", ""));
        val result = new LinkedList<Appointment>();
        val reader = IOFactory.getManager(type);
        try(val files = Files.walk(path)) {
            files.filter(Files::isRegularFile)
                    .forEach(file -> {
                System.out.println(file);
                try {
                    val string = Files.readString(file);
                    result.add(reader.readFile(string));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            })
            ;
        }catch (NoSuchFileException e) {
            return List.of();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    private void createDir(Path pathName){
        if (!Files.exists(pathName)){
            try {
                Files.createDirectories(pathName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private Path getInternalPath(String path){
        return Paths.get(ROOT_NAME, path);
    }
}