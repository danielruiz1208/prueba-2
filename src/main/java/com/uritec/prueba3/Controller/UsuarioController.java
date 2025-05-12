package com.uritec.prueba3.Controller;

import com.uritec.prueba3.Model.Usuario;
import com.uritec.prueba3.Service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listarUsuarios(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "usuario/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoUsuarioForm(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("usuario", new Usuario());
        return "usuario/form";
    }

    @PostMapping("/nuevo")
    public String registrarUsuario(@ModelAttribute Usuario usuario, Model model) {
        try {
            usuarioService.registrarUsuario(usuario);
            return "redirect:/admin/usuarios";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "usuario/form";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarUsuarioForm(@PathVariable int id, Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        Usuario usuario = usuarioService.encontrarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        model.addAttribute("usuario", usuario);
        return "usuario/form";
    }

    @PostMapping("/editar/{id}")
    public String actualizarUsuario(@PathVariable int id, @ModelAttribute Usuario usuario, Model model) {
        try {
            usuario.setIdUsuario(id); // Asegurar que el ID se mantenga
            usuarioService.registrarUsuario(usuario);
            return "redirect:/admin/usuarios";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "usuario/form";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable int id, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        usuarioService.eliminarUsuario(id);
        return "redirect:/admin/usuarios";
    }
}
