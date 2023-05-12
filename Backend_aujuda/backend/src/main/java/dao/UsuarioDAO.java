package dao;

import model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO extends DAO {	
	public UsuarioDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Usuario usuario) {
		boolean status = false;
		try {
			String sql = "INSERT INTO usuario (cpf, nome, sobrenome, username, telefone, cidade) "
		               + "VALUES ('" + usuario.getCpf() + "', "
		               + usuario.getNome() + ", " + usuario.getSobrenome() + "', "
		               + usuario.getUsername() + "', " + usuario.getTelefone() + "', "
		               + usuario.getCidade() + ", ?, ?);";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Usuario get(String cpf) {
		Usuario usuario = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario WHERE cpf="+cpf;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 usuario = new Usuario(rs.getString("cpf"), 
	        			 rs.getString("nome"), 
	        			 rs.getString("sobrenome"),
	        			 rs.getString("username"), 
	        			 rs.getString("telefone"), 
	        			 rs.getString("cidade"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuario;
	}
	
	
	public List<Usuario> get() {
		return get("");
	}

	
	public List<Usuario> getOrderByCpf() {
		return get("cpf");		
	}
	
	public List<Usuario> getOrderByNome() {
		return get("nome");		
	}
	
	
	public List<Usuario> getOrderBySobrenome() {
		return get("sobrenome");		
	}
	
	
	public List<Usuario> getOrderByUsername() {
		return get("username");		
	}
	
	public List<Usuario> getOrderByTelefone() {
		return get("telefone");		
	}
	
	public List<Usuario> getOrderByCidade() {
		return get("cidade");		
	}
	
	
	private List<Usuario> get(String orderBy) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Usuario p = new Usuario(rs.getString("cpf"), 
	        			 rs.getString("nome"), 
	        			 rs.getString("sobrenome"),
	        			 rs.getString("username"), 
	        			 rs.getString("telefone"), 
	        			 rs.getString("cidade"));
	            usuarios.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuarios;
	}
	
	
	public boolean update(Usuario usuario) {
		boolean status = false;
		try {  
			String sql = "UPDATE usuario SET nome = '" + usuario.getNome() + "', "
					   + "sobrenome = " + usuario.getSobrenome() + ", " 
					   + "username = " + usuario.getUsername() + ", " 
					   + "telefone = " + usuario.getTelefone() + ", " 
					   + "cidade = " + usuario.getCidade() + ","
					   WHERE cpf = " + usuario.getCpf();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM usuario WHERE cpf = " + cpf);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}
