package com.uritec.prueba3.Controller;

import com.uritec.prueba3.Model.Usuario;
import jakarta.servlet.http.HttpSession;
import com.uritec.prueba3.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute Usuario usuario, Model model, HttpSession session) {
        Usuario user = usuarioService.login(usuario.getUsuario(), usuario.getContrasena());
        if (user != null) {
            session.setAttribute("usuario", user);
            switch (user.getRol()) {
                case Administrador:
                    return "redirect:/admin/usuarios";
                case Estudiante:
                    return "redirect:/estudiante/dashboard";
                case Docente:
                    return "redirect:/docente/dashboard";
                default:
                    return "redirect:/dashboard";
            }
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}