package com.patrones.taller1.service.io;

import org.springframework.stereotype.Component;

@Component
public class IOFactory {
    public static IOManager getManager(IOType type){
        switch (type){
            case JSON -> {
                return new IOJson();
            }
            case XML -> {
                return new IOXml();
            }
        }
        throw new RuntimeException("N/A");
    }
}
