package dao;

import model.Ong;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class OngDAO extends DAO {	
	public OngDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Ong ong) {
		boolean status = false;
		try {
			String sql = "INSERT INTO ong (cnpj, nome, endereco, descricao, telefone, fk_animal_id) "
		               + "VALUES ('" + ong.getCnpj() + "', "
		               + ong.getNome() + ", " + ong.getEndereco() + "', "
		               + ong.getDescricao() + "', " + ong.getTelefone() + "', "
		               + ong.getFk_animal_id() + ", ?, ?);";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Ong get(String cnpj) {
		Ong ong = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM ong WHERE cnpj="+cnpj;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 ong = new Ong(rs.getString("cnpj"), 
	        			 rs.getString("nome"), 
	        			 rs.getString("endereco"),
	        			 rs.getString("descricao"), 
	        			 rs.getString("telefone"), 
	        			 rs.getString("fk_animal_id"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return ong;
	}
	
	
	public List<Ong> get() {
		return get("");
	}

	
	public List<Ong> getOrderByCnpj() {
		return get("cnpj");		
	}
	
	public List<Ong> getOrderByNome() {
		return get("nome");		
	}
	
	
	public List<Ong> getOrderByEndereco() {
		return get("endereco");		
	}
	
	
	public List<Ong> getOrderByDescricao() {
		return get("descricao");		
	}
	
	public List<Ong> getOrderByTelefone() {
		return get("telefone");		
	}
	
	public List<Ong> getOrderByFk_animal_id() {
		return get("fk_animal_id");		
	}
	
	
	private List<Ong> get(String orderBy) {
		List<Ong> ongs = new ArrayList<Ong>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM ong" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Ong p = new Ong(rs.getString("cnpj"), 
	        			 rs.getString("nome"), 
	        			 rs.getString("endereco"),
	        			 rs.getString("descricao"), 
	        			 rs.getString("telefone"), 
	        			 rs.getString("fk_animal_id"));
	            ongs.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return ongs;
	}
	
	
	public boolean update(Ong ong) {
		boolean status = false;
		try {  
			String sql = "UPDATE ong SET nome = '" + ong.getNome() + "', "
					   + "endereco = " + ong.getEndereco() + ", " 
					   + "descricao = " + ong.getDescricao() + ", " 
					   + "telefone = " + ong.getTelefone() + ", " 
					   + "fk_animal_id = " + ong.getFk_animal_id() + ","
					   WHERE cnpj = " + ong.getCnpj()";
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
			st.executeUpdate("DELETE FROM ong WHERE cnpj = " + cnpj);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
		}
		