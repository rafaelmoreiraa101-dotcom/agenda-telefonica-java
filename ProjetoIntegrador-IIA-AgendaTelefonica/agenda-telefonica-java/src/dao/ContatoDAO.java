package dao;

import database.Conexao;
import model.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class ContatoDAO {

    // Cores ANSI
    public static final String RESET = "\u001B[0m";
    public static final String VERDE = "\u001B[32m";
    public static final String VERMELHO = "\u001B[31m";
    public static final String AMARELO = "\u001B[33m";

    // CREATE
    public void adicionarContato(Contato contato) {

        String sql =
                "INSERT INTO contatos (nome, telefone, email) VALUES (?, ?, ?)";

        try (
                Connection conexao = Conexao.conectar();
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getTelefone());
            stmt.setString(3, contato.getEmail());

            stmt.executeUpdate();

            System.out.println(
                    VERDE +
                    "\n[SUCESSO] Contato cadastrado com sucesso!" +
                    RESET
            );

        } catch (SQLException erro) {

            System.out.println(
                    VERMELHO +
                    "\n[ERRO] Não foi possível cadastrar o contato." +
                    RESET
            );

            System.out.println(
                    AMARELO +
                    "Detalhes: " + erro.getMessage() +
                    RESET
            );
        }
    }

    // READ
    public ArrayList<Contato> listarContatos() {

        ArrayList<Contato> lista = new ArrayList<>();

        String sql = "SELECT * FROM contatos";

        try (
                Connection conexao = Conexao.conectar();
                PreparedStatement stmt =
                        conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {

                Contato contato = new Contato();

                contato.setId(rs.getInt("id"));
                contato.setNome(rs.getString("nome"));
                contato.setTelefone(rs.getString("telefone"));
                contato.setEmail(rs.getString("email"));

                lista.add(contato);
            }

        } catch (SQLException erro) {

            System.out.println(
                    VERMELHO +
                    "\n[ERRO] Não foi possível listar contatos." +
                    RESET
            );
        }

        return lista;
    }

    // BUSCAR
    public ArrayList<Contato> buscarContato(String nome) {

        ArrayList<Contato> lista = new ArrayList<>();

        String sql =
                "SELECT * FROM contatos WHERE nome LIKE ?";

        try (
                Connection conexao = Conexao.conectar();
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setString(1, "%" + nome + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Contato contato = new Contato();

                contato.setId(rs.getInt("id"));
                contato.setNome(rs.getString("nome"));
                contato.setTelefone(rs.getString("telefone"));
                contato.setEmail(rs.getString("email"));

                lista.add(contato);
            }

        } catch (SQLException erro) {

            System.out.println(
                    VERMELHO +
                    "\n[ERRO] Não foi possível buscar contatos." +
                    RESET
            );
        }

        return lista;
    }

    // UPDATE
    public void atualizarContato(Contato contato) {

        String sql =
                "UPDATE contatos SET nome = ?, telefone = ?, email = ? WHERE id = ?";

        try (
                Connection conexao = Conexao.conectar();
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getTelefone());
            stmt.setString(3, contato.getEmail());
            stmt.setInt(4, contato.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {

                System.out.println(
                        VERDE +
                        "\n[SUCESSO] Contato atualizado!" +
                        RESET
                );

            } else {

                System.out.println(
                        AMARELO +
                        "\n[AVISO] Contato não encontrado." +
                        RESET
                );
            }

        } catch (SQLException erro) {

            System.out.println(
                    VERMELHO +
                    "\n[ERRO] Não foi possível atualizar." +
                    RESET
            );
        }
    }

    // DELETE
    public void removerContato(int id) {

        String sql =
                "DELETE FROM contatos WHERE id = ?";

        try (
                Connection conexao = Conexao.conectar();
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {

                System.out.println(
                        VERDE +
                        "\n[SUCESSO] Contato removido!" +
                        RESET
                );

            } else {

                System.out.println(
                        AMARELO +
                        "\n[AVISO] Contato não encontrado." +
                        RESET
                );
            }

        } catch (SQLException erro) {

            System.out.println(
                    VERMELHO +
                    "\n[ERRO] Não foi possível remover." +
                    RESET
            );
        }
    }

    // VALIDAR TELEFONE
    public boolean telefoneExiste(String telefone) {

        String sql =
                "SELECT * FROM contatos WHERE telefone = ?";

        try (
                Connection conexao = Conexao.conectar();
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setString(1, telefone);

            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException erro) {

            System.out.println(
                    VERMELHO +
                    "\n[ERRO] Não foi possível validar telefone." +
                    RESET
            );
        }

        return false;
    }
}