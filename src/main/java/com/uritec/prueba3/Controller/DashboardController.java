package com.usalesiana.proy15.controller;

import com.usalesiana.proy15.model.Usuario;
import com.usalesiana.proy15.service.EquipoService;
import com.usalesiana.proy15.service.LaboratorioService;
import com.usalesiana.proy15.service.MantenimientoService;
import com.usalesiana.proy15.service.PersonaService;
import com.usalesiana.proy15.service.PrestamoService;
import com.usalesiana.proy15.service.SancionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final PersonaService personaService;
    private final PrestamoService prestamoService;
    private final EquipoService equipoService;
    private final MantenimientoService mantenimientoService;
    private final SancionService sancionService;
    private final LaboratorioService laboratorioService;

    public DashboardController(PersonaService personaService, PrestamoService prestamoService,
                               EquipoService equipoService, MantenimientoService mantenimientoService,
                               SancionService sancionService, LaboratorioService laboratorioService) {
        this.personaService = personaService;
        this.prestamoService = prestamoService;
        this.equipoService = equipoService;
        this.mantenimientoService = mantenimientoService;
        this.sancionService = sancionService;
        this.laboratorioService = laboratorioService;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        Usuario.RolUsuario role = (Usuario.RolUsuario) session.getAttribute("role");
        if (username == null) {
            return "redirect:/login";
        }

        model.addAttribute("username", username);
        model.addAttribute("role", role);

        if (role == Usuario.RolUsuario.Administrador) {
            model.addAttribute("personas", personaService.getAllPersonas());
            model.addAttribute("prestamos", prestamoService.getAllPrestamos());
            model.addAttribute("equipos", equipoService.getAllEquipos());
            model.addAttribute("mantenimientos", mantenimientoService.getAllMantenimientos());
            model.addAttribute("sanciones", sancionService.getAllSanciones());
        } else {
            model.addAttribute("prestamos", prestamoService.getPrestamosByUsername(username));
            model.addAttribute("sanciones", sancionService.getSancionesByUsername(username));
        }
        model.addAttribute("laboratorios", laboratorioService.getAllLaboratorios());

        return "dashboard";
    }
}