package tn.esprit.tpfoyer17.controllers;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.services.IBlocService;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping(value = "api/blocs")

public class BlocController {
    IBlocService blocService;

    @PostMapping("add")
    @RequestMapping(method=RequestMethod.POST)
    public Bloc addingBloc(@RequestBody Bloc bloc){
        return blocService.addBloc(bloc);
    }
    @GetMapping("getAll")
    public List<Bloc> gettingAllBloc(){
        return blocService.getAllBlocs();
    }

    @GetMapping("get")
    public Bloc gettingBloc(@RequestParam("idBloc") long idBloc){
        return blocService.getBlocById(idBloc);
    }

    @DeleteMapping("delete/{idBloc}")
    public void deletingBloc(@PathVariable("idBloc") long idBloc){
        blocService.deleteBloc(idBloc);
    }

    @PutMapping("update")
    public Bloc updatingBloc(@RequestBody Bloc bloc){
        return blocService.updateBloc(bloc);
    }



    @PutMapping("affecter-chambre-bloc")
    public Bloc affecterChambresABloc(@RequestBody List<Long> numChambre,@RequestParam("idBloc") long idBloc){
        return blocService.affecterChambresABloc(numChambre,idBloc);
    }




}
