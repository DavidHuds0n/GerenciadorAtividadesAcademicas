package dados;

import java.util.HashMap;
import java.util.Map;
import negocios.Coordenador;

public class CoordenadorRepository {
    private Map<String, Coordenador> coordenadores = new HashMap<>();

    public void cadastrarCoordenador(Coordenador coordenador) {
        String username = coordenador.getUsername();
        coordenadores.put(username, coordenador);
    }

    public Coordenador buscarCoordenador(String username) {
        return coordenadores.get(username);
    }
    
    public boolean autenticarCoordenador(String username, String password) {
        Coordenador coordenador = buscarCoordenador(username);
        return coordenador != null && coordenador.getPassword().equals(password);
    }

}
