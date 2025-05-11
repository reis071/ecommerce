package org.example.spring_ecommerce.application.services.usuario;

import lombok.AllArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.entities.grupo.GrupoEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.entities.usuario.UsuarioEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.repositories.usuarioGrupo.UsuarioGrupoRepositoryJPA;
import org.example.spring_ecommerce.adapters.outBound.repositories.usuario.UsuarioDAO;
import org.example.spring_ecommerce.application.services.email.EmailService;
import org.example.spring_ecommerce.application.useCases.grupo.GrupoUseCases;
import org.example.spring_ecommerce.application.useCases.usuario.UsuarioUseCases;
import org.example.spring_ecommerce.infrastructure.configuration.advices.exceptionExclusives.ErroAutenticacao;
import org.example.spring_ecommerce.infrastructure.configuration.advices.exceptionExclusives.TokenInvalido;
import org.example.spring_ecommerce.adapters.inBound.controllers.dto.EmailDto;
import org.example.spring_ecommerce.adapters.inBound.controllers.dto.UsuarioDto;
import org.example.spring_ecommerce.domain.grupo.Grupo;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.example.spring_ecommerce.adapters.outBound.entities.usuarioGrupo.UsuarioGrupoEntityJPA;
import org.example.spring_ecommerce.infrastructure.configuration.security.jwt.JwtService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UsuarioService implements  UsuarioUseCases,UserDetailsService {


    private final UsuarioDAO usuarioDAO;

    private final GrupoUseCases grupoUseCases;
    private final UsuarioGrupoRepositoryJPA usuarioGrupoRepositoryJPA;

    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final JwtService jwtService;


    //Cadastra Usuario
    public Usuario salvar(Usuario usuario, List<String> grupos){
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        List<UsuarioGrupoEntityJPA> listaUsuarioGrupoEntityJPA = grupos.stream().map(nomeGrupo -> {
                    Grupo grupo = grupoUseCases.procurarGruPorNomeService(nomeGrupo);
                        usuarioDAO.save(usuario);

                        UsuarioEntityJPA usuarioEntityJPA = new UsuarioEntityJPA(usuario);
                        GrupoEntityJPA grupoEntityJPA = new GrupoEntityJPA(grupo);

                        return new UsuarioGrupoEntityJPA(usuarioEntityJPA, grupoEntityJPA);
                })
                .collect(Collectors.toList());

        usuarioGrupoRepositoryJPA.saveAll(listaUsuarioGrupoEntityJPA);

        return usuario;
    }

    //autentica usuario
    public UserDetails autenticar( Usuario usuario ){
        UserDetails user = loadUserByUsername(usuario.getEmail());
        boolean senhasBatem = passwordEncoder.matches( usuario.getSenha(), user.getPassword() );
        if(senhasBatem){
            return user;
        }
        throw new ErroAutenticacao();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioDAO.findByEmail(email);
        List<String> permissoes = usuarioGrupoRepositoryJPA.findPermissoesByUsuario(usuario);
        return new UsuarioDto(usuario, permissoes); // Retorna um UsuarioDto com permissões
    }

    //Ver a permissão do usuario
    public UsuarioDto obterUsuarioComPermissoes(String email) {
        Usuario usuario = usuarioDAO.findByEmail(email);

        List<String> permissoes = usuarioGrupoRepositoryJPA.findPermissoesByUsuario(usuario);
        return new UsuarioDto(usuario, permissoes); // Retorna um UsuarioDto com permissões
    }
    //valida

    //envia token para alterar senha
    public void enviarSolicitacaoDeResetarSenha(String email) {
        Usuario user = usuarioDAO.findByEmail(email);

        String token = jwtService.gerarToken    (new UsuarioDto(user,user.getPermissoes()));

        EmailDto emailDtoDetails = new EmailDto(
                user.getEmail(),
                "Reset de Senha",
                "Para redefinir sua senha:"
                        + "token=" + token
        );

        emailService.sendEmail(emailDtoDetails); // Corrigido: Método correto
    }

    //valida se é um token valido e reseta a senha
    public void resetarSenha(String token, String newPassword) {
        if (!jwtService.tokenValido(token)) {
            throw new TokenInvalido();
        }

        String email = jwtService.obterLoginUsuario(token);

        Usuario user = usuarioDAO.findByEmail(email);

        user.setSenha(passwordEncoder.encode(newPassword));

        usuarioDAO.save(user);
    }

    public void depositar(double deposito){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Usuario usuario = usuarioDAO.findByEmail(email);

        if (deposito > 0) {
            usuario.setSaldo(usuario.getSaldo() + deposito);
            usuarioDAO.save(usuario);
        } else {
            throw new RuntimeException("Saldo não pode ser negativo");
        }
    }

}

