package spb.city.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import spb.city.demo.Injection.Injection;
import spb.city.demo.ProductRepository.ProductRepository;

import spb.city.demo.service.VkladInfo;

import java.util.List;


/**
 * Доступны все методык взаимодействию и возвращает данные в формате json
 */
@RestController
@RequestMapping(value = "/api/vklad", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class VkladRestController {

    @Autowired
    private ProductRepository repository;

    /**
     * Возвращает информацию об вклдах пользователя
     * @return
     */
    @GetMapping("/all")
    public List<VkladInfo> getAllVklad(){

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Injection.class);
        List <VkladInfo> information = repository.readAllVklad();
        applicationContext.close();

        return information;
    }

    /**
     * Принимает информацию о новом вкладе и создает новый вклад.
     * @param info
     * @return
     */
    @PostMapping
    public boolean createVklad(@RequestBody VkladInfo info){

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Injection.class);
        boolean result = repository.addVklad(info);

       if ( result == true) {
           return true;
       }else {
           return false;
       }
    }

    /**
     * Принимает номер вклада и закрывает его, если такого вклада нет,
     * выбрасывает ошибку.
     * @param numberVklad
     * @return
     */
    @DeleteMapping("/{vkladNummber}")
    public boolean deleteVklad(@PathVariable("vkladNummber") Integer numberVklad) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Injection.class);
        boolean result = repository.closeVklad(numberVklad);

        if (result == true) {
            return true;
        } else {
            return false;
        }
    }
}
