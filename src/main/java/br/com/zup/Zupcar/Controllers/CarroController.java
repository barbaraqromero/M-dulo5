package br.com.zup.Zupcar.Controllers;


import br.com.zup.Zupcar.DTOS.CarroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {
  private List<CarroDTO> frota = new ArrayList<>();

  @GetMapping
  public List<CarroDTO> listarCarros() {
    return frota;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void cadastrarCarro(@RequestBody CarroDTO carroDTO) {
    frota.add(carroDTO);
  }

  @GetMapping("/{nomeDoCarro}")
  public CarroDTO exibirCarro(@PathVariable String nomeDoCarro) {
    for (CarroDTO referencia : frota) {
      if (referencia.getModelo().equalsIgnoreCase(nomeDoCarro)) {
        return referencia;
      }


    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND);

  }

  @PutMapping("/{nomeDoCarro}")
  public CarroDTO atualizarCarro(@PathVariable String nomeDoCarro, @RequestBody CarroDTO carroDTO) {
    for (CarroDTO referencia : frota){
      if (referencia.getModelo().equalsIgnoreCase(nomeDoCarro)){
        referencia.setCor(referencia.getCor());
        referencia.setMotor(referencia.getMotor());
        referencia.setAno(referencia.getAno());
        return referencia;
      }
    }
    throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
  }
}



