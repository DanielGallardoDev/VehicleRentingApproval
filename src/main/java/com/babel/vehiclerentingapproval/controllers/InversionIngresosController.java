package com.babel.vehiclerentingapproval.controllers;

import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.services.preautomaticresults.ApprovalRulesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/validaciones")
public class InversionIngresosController {

    private ApprovalRulesService approvalService;

    public InversionIngresosController(ApprovalRulesService approvalService) {
        this.approvalService = approvalService;
    }

    @GetMapping("")
    ResponseEntity makeProofs(@RequestParam SolicitudRenting solicitudRentingl) {
        boolean t = this.approvalService.validateInversionIngresos(solicitudRentingl);
        boolean t1 = this.approvalService.validateScoringPersona(solicitudRentingl);
        boolean t2 = this.approvalService.validateCIFCliente(solicitudRentingl);
        boolean t3 = this.approvalService.validateYearsExperience(solicitudRentingl);
        boolean t4 = this.approvalService.validateInversion(solicitudRentingl);
        boolean t5 = this.approvalService.validateImpagoCuota(solicitudRentingl);
        boolean t6 = this.approvalService.validateNationality(solicitudRentingl);
        boolean t7 = this.approvalService.validateClienteNoAprobadoConGarantias(solicitudRentingl);
        boolean t8 = this.approvalService.validatefindPersonasByCodResultado(solicitudRentingl);
        boolean t9 = this.approvalService.validateClienteNoRechazadoPreviamente(solicitudRentingl);
        ArrayList<Boolean> lista = new ArrayList<Boolean>();


        lista.add(t);
        lista.add(t1);
        lista.add(t2);
        lista.add(t3);
        lista.add(t4);
        lista.add(t5);
        lista.add(t6);
        lista.add(t7);
        lista.add(t8);
        lista.add(t9);

        return ResponseEntity.ok((lista));
    }

}
