import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class ValidarPerfil {
    public static void main(String[] args) {
        testePerfil();
        // criarUsuario();
        // criarColab();
        // criarFornec();
        // criarPessoa();
        // criarEntidade();
    }

    private static void testePerfil() {
        Colaborador colaborador;
        colaborador = new Colaborador("proprietario", TipoPessoa.FISICA, "21/12/2019", 0003, "Adriano", "00000000");

        Usuario user;
        user = new Usuario(colaborador, "super", "mar", "senha1232");

        ControlePerfil controlePerfil;
        controlePerfil = new ControlePerfil();
        controlePerfil.AddPerfilGrupo(user, user.getPerfil());

         new Telas(controlePerfil, user);

        colaborador = new Colaborador("proprietario", TipoPessoa.FISICA, "22/12/2019", 0004, "Marta", "0332334544");
        user = new Usuario(colaborador, "admin", "adri", "senha123");
        controlePerfil.AddPerfilGrupo(user, user.getPerfil());

        //new Telas(controlePerfil, user);

        colaborador = new Colaborador("proprietario", TipoPessoa.FISICA, "22/12/2019", 0005, "Felipe", "000099998888");
        user = new Usuario(colaborador, "user", "lipe", "senha123");
        controlePerfil.AddPerfilGrupo(user, user.getPerfil());

        //new Telas(controlePerfil, user);
    }

    private static void criarUsuario() {
        Colaborador colaborador = new Colaborador("rh", TipoPessoa.FISICA, "21/11/2019", 1, "Colaborador 2",
                "221.223.432-77");
        Usuario usuario = new Usuario(colaborador, "admin", "usuario 1", "senha");
        System.out.println(usuario);
    }

    private static void criarColab() {
        Colaborador colaborador = new Colaborador("rh", TipoPessoa.FISICA, "10/05/2015", 1, "Coalaborador 1",
                "120.886.345-74");
        System.out.println(colaborador);
    }

    private static void criarFornec() {
        Fornecedor fornecedor = new Fornecedor("rh", TipoPessoa.JURIDICA, "22/11/2013", "Fornecedor 1",
                "08.756.325/0001-54", "123.333.432");
        System.out.println(fornecedor);
    }

    private static void criarPessoa() {
        Pessoa pessoa = new Pessoa("admin", TipoPessoa.FISICA, "21/12/2019", "Pessoa 1", "220.123.323-44");
        System.out.println(pessoa);
    }

    private static void criarEntidade() {
        Entidade entidade = new Entidade();

        entidade.setDataCriacao("12/08/2018");
        entidade.setNome("Entidade 1");
        entidade.setProprietario("admin");

        System.out.println(entidade);
    }

}

class Telas {
    List<String> telas = new ArrayList<>();

    Telas(ControlePerfil perfil, Usuario user) {
        telas.add("CadastroUsuario");
        telas.add("CadastroColaborador");
        telas.add("CadastroFornecedor");
        telas.add("Configuracoes");
        telas.add("Relatorios");

        System.out.println("Telas Disponiveis");
        mostrarTelas(perfil, user);
        System.out.println("*******//*********\n");
    }

    void mostrarTelas(ControlePerfil perfil, Usuario user) {
        if (perfil.getUserAdmin().contains(user)) {
            for (String string : telas) {
                System.out.println(string);
            }

        } else if (perfil.getUserSup().contains(user)) {
            System.out.println(telas.get(0));
            System.out.println(telas.get(3));
            System.out.println(telas.get(4));

        } else if (perfil.getUser().contains(user)) {
            System.out.println(telas.get(3));
            System.out.println(telas.get(4));

        } else {
            System.out.println("voce nao tem telas.");
        }
    }

}

class ControlePerfil {
    List<Usuario> userAdmin = new ArrayList<>();
    List<Usuario> userSup = new ArrayList<>();
    List<Usuario> user = new ArrayList<>();

    public void AddPerfilGrupo(Usuario user, String perfil) {
        if (perfil.equals("admin")) {
            this.userAdmin.add(user);

        } else if (perfil.equals("super")) {
            this.userSup.add(user);

        } else if (perfil.equals("user")) {
            this.user.add(user);
        }
    }

    public List<Usuario> getUserAdmin() {
        return userAdmin;
    }

    public List<Usuario> getUserSup() {
        return userSup;
    }

    public List<Usuario> getUser() {
        return user;
    }

}

class Usuario extends Entidade {
    private Colaborador colaborador;
    private String perfil;
    private String user;
    private String senha;

    Usuario(Colaborador colaborador, String perfil, String user, String senha) {
        this.colaborador = colaborador;
        this.perfil = perfil;
        this.user = user;
        this.senha = senha;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario [colaborador=" + colaborador + ", perfil=" + perfil + ", senha=" + senha + ", user=" + user
                + "]";
    }

}

class Fornecedor extends Pessoa {
    Fornecedor(String proprietario, Enum tipo, String dataCriacao, String nome, String cnpj, String inscricaoEstadual) {
        super(proprietario, tipo, dataCriacao, nome, cnpj, inscricaoEstadual);

    }
}

class Colaborador extends Pessoa {
    int registroColaborador;

    Colaborador(String proprietario, Enum tipo, String dataCriacao, int registroColaborador, String nome, String cpf) {
        super(proprietario, tipo, dataCriacao, nome, cpf);
        setRegistroColaborador(registroColaborador);

    }

    @Override
    public String toString() {
        return "registroColaborador=" + registroColaborador + " " + super.toString();
    }

    public int getRegistroColaborador() {
        return registroColaborador;
    }

    public void setRegistroColaborador(int registroColaborador) {
        this.registroColaborador = registroColaborador;
    }

}

class Pessoa extends Entidade {
    private Enum tipo;
    private String cpf;
    private String rg;
    private String cnpj;
    private String inscricaoEstadual;

    public Pessoa(String proprietario, Enum tipo, String dataCriacao, String nome, String cpf) {
        this.setProprietario(proprietario);
        this.setTipo(tipo);
        this.setDataCriacao(dataCriacao);
        this.setNome(nome);
        this.setCpf(cpf);

    }

    public Pessoa(String proprietario, Enum tipo, String dataCriacao, String nome, String cnpj, String inscricao) {
        this.setProprietario(proprietario);
        this.setTipo(tipo);
        this.setDataCriacao(dataCriacao);
        this.setNome(nome);
        this.setCpf(cnpj);
        this.setInscricaoEstadual(inscricaoEstadual);

    }

    public Enum getTipo() {
        return tipo;
    }

    public void setTipo(Enum tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "tipo=" + tipo + " " + super.toString();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

}

/**
 * registro tem que ter data nome dono
 */
class Entidade {
    private final UUID uuid = UUID.randomUUID();
    private String dataCriacao;
    private String nome;
    private String proprietario;

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "ID= " + uuid + ", dataCriacao=" + dataCriacao + ", nome=" + nome + ", proprietario=" + proprietario
                + "\n";
    }

}

enum TipoPessoa {
    FISICA("Fisica"), JURIDICA("Juridica");

    private final String tipoPessoa;

    TipoPessoa(String tipo) {
        tipoPessoa = tipo;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }
}