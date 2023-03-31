package com.babel.vehiclerentingapproval.Services.impl;

import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.SolicitudRentingNotFoundException;
import com.babel.vehiclerentingapproval.models.TipoResultadoSolicitud;
import com.babel.vehiclerentingapproval.persistance.database.mappers.SolicitudRentingMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.TipoResultadoSolicitudMapper;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import com.babel.vehiclerentingapproval.services.impl.SolicitudRentingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SolicitudRentingServiceImplTest {
    SolicitudRentingService solicitudService;
    TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper;
    SolicitudRentingMapper solicitudRentingMapper;

    @BeforeEach
    void setUpAll(){
        tipoResultadoSolicitudMapper = Mockito.mock(TipoResultadoSolicitudMapper.class);
        solicitudRentingMapper = Mockito.mock(SolicitudRentingMapper.class);
        solicitudService = new SolicitudRentingServiceImpl(solicitudRentingMapper,tipoResultadoSolicitudMapper);

    }

    @Nested
    class TestsVerEstadoSolicitud{
        @Test
        public void verEstadoSolicitud_shouldThrow_EstadoSolicitudNotFoundException_when_codSolicitudNull_or_idNotExists(){
            Assertions.assertThrows(EstadoSolicitudNotFoundException.class,() ->{
                int id = -43;
                String estado = solicitudService.verEstadoSolicitud(id);
            });
        }
    }

    @Nested
    class TestsVerSolicitudRenting{
        @Test
        public void verSolicitudRenting_shouldThrow_SolicitudRentingNotFoundException_when_solicitudIdNotExists(){
            Assertions.assertThrows(SolicitudRentingNotFoundException.class,()->{
                int id = -10;
                solicitudService.getSolicitudById(id);
            });
        }
    }


    @Nested
    class TestsModificaEstadoSolicitud{
        @Test
        public void modificaEstadoSolicitud_shouldThrow_EstadoSolicitudNotFoundException_when_codSolicitudNotExist(){

            Mockito.when(tipoResultadoSolicitudMapper.existeCodigoResolucion(any())).thenReturn(0);

            Assertions.assertThrows(EstadoSolicitudNotFoundException.class,() ->{

                int id = 1;
                String estadoCodigo = "WW";
                TipoResultadoSolicitud tipoResultadoSolicitud = new TipoResultadoSolicitud();
                tipoResultadoSolicitud.setCodResultado(estadoCodigo);
                tipoResultadoSolicitud.setDescripcion("");

            });
        }

        @Test
        public void modificaSolicitudRenting_shouldThrow_SolicitudRentingNotFoundException_when_solicitudIdNotExists() {

            Mockito.when(tipoResultadoSolicitudMapper.existeCodigoResolucion(any())).thenReturn(1);
            Mockito.when()
            Assertions.assertThrows(SolicitudRentingNotFoundException.class, () -> {

                int id = -1;

                String estadoCodigo = "CA";
                TipoResultadoSolicitud tipoResultadoSolicitud = new TipoResultadoSolicitud();
                tipoResultadoSolicitud.setCodResultado(estadoCodigo);
                tipoResultadoSolicitud.setDescripcion("");

                solicitudService.modificaEstadoSolicitud(id,tipoResultadoSolicitud);
            });
        }
    }

}
