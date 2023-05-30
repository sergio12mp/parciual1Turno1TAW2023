package es.taw.sampletaw.controller;


import es.taw.sampletaw.dao.ClienteEntityRepository;
import es.taw.sampletaw.dao.CuentaRepository;
import es.taw.sampletaw.dao.MovimientoRepository;
import es.taw.sampletaw.dao.TipoOperacionRepository;
import es.taw.sampletaw.entity.ClienteEntity;
import es.taw.sampletaw.entity.CuentaEntity;
import es.taw.sampletaw.entity.MovimientoEntity;
import es.taw.sampletaw.entity.TipooperacionEntity;
import es.taw.sampletaw.ui.Filtro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@Controller
public class Controlador {

    @Autowired
    ClienteEntityRepository clienteEntityRepository;
    @Autowired
    CuentaRepository cuentaRepository;
    @Autowired
    MovimientoRepository movimientoRepository;

    @Autowired
    TipoOperacionRepository tipoOperacionRepository;


    @GetMapping("/")
    public String doListar(Model model) {
        List<CuentaEntity> listaCuentas = this.cuentaRepository.findAll();
        model.addAttribute("listaCuentas", listaCuentas);


        return "index";
    }

    @GetMapping("cambiarEstado")
    public String doCambiarEstado(Model model, @RequestParam("idCuenta") Integer idCuenta) {
        CuentaEntity cuenta = this.cuentaRepository.findById(idCuenta).orElse(null);
        if (cuenta.getActiva() == 1) {
            cuenta.setActiva((short) 0);
        } else if (cuenta.getActiva() == 0) {
            cuenta.setActiva((short) 1);
        }
        cuentaRepository.save(cuenta);
        return doListar(model);
    }

    @GetMapping("realizarTransferencia")
    public String doRealizarTransferencia(Model model, @RequestParam("idCuenta") Integer idCuenta) {
        CuentaEntity cuenta = this.cuentaRepository.findById(idCuenta).orElse(null);
        ClienteEntity cliente = cuenta.getClienteByTitular();
        List<CuentaEntity> listaCuentasActivas = this.cuentaRepository.listaCuentasActivas(cuenta.getIban());
        Filtro filtro = new Filtro();

        model.addAttribute("cuenta", cuenta);
        model.addAttribute("cliente", cliente);
        model.addAttribute("listaCuentasActivas", listaCuentasActivas);
        model.addAttribute("Filtro", filtro);

        return "transferencia";
    }

    @PostMapping("/autorizarTransferencia")
    public String doAutorizarTransferencia(Model model, @ModelAttribute("Filtro") Filtro filtro) {
        MovimientoEntity movimientoEjecutor = new MovimientoEntity();
        MovimientoEntity movimientoReceptor = new MovimientoEntity();
        TipooperacionEntity transferencia = this.tipoOperacionRepository.findById(2).orElse(null);
        TipooperacionEntity ingreso = this.tipoOperacionRepository.findById(4).orElse(null);
        System.out.println(filtro.getFIBANORIGEN());
        System.out.println(filtro.getFIBANDESTINO());

        CuentaEntity cuentaEjecutor = this.cuentaRepository.cuentaPorIban(filtro.getFIBANORIGEN());
        CuentaEntity cuentaReceptor = this.cuentaRepository.findById(Integer.valueOf(filtro.getFIBANDESTINO())).orElse(null);

        cuentaEjecutor.setSaldoactual(cuentaEjecutor.getSaldoactual() - filtro.getFCantidad());
        cuentaReceptor.setSaldoactual(cuentaReceptor.getSaldoactual() + filtro.getFCantidad());

        movimientoEjecutor.setCuentaByCuentaorigen(cuentaEjecutor);
        movimientoEjecutor.setCuentaByCuentadestinatario(cuentaReceptor);
        movimientoEjecutor.setTipooperacionByTipooperacion(transferencia);
        movimientoEjecutor.setCantidad(filtro.getFCantidad());
        movimientoEjecutor.setFecha(Timestamp.valueOf(LocalDateTime.now()));
        movimientoEjecutor.setDescripcion(filtro.getFDescripcion());

        movimientoReceptor.setCuentaByCuentaorigen(cuentaEjecutor);
        movimientoReceptor.setCuentaByCuentadestinatario(cuentaReceptor);
        movimientoReceptor.setTipooperacionByTipooperacion(ingreso);
        movimientoReceptor.setCantidad(filtro.getFCantidad());
        movimientoReceptor.setFecha(Timestamp.valueOf(LocalDateTime.now()));
        movimientoReceptor.setDescripcion(filtro.getFDescripcion());

        movimientoRepository.save(movimientoEjecutor);
        movimientoRepository.save(movimientoReceptor);
        cuentaRepository.save(cuentaEjecutor);
        cuentaRepository.save(cuentaReceptor);

        return doListar(model);
    }


    //path- nombre del parametro del filtro
    //items
}
