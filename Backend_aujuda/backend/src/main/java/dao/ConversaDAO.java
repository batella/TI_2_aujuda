package dao;

import model.Conversa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class ConversaDAO extends DAO {	
	public ConversaDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Conversa conversa) {
		boolean status = false;
		try {
			String sql = "INSERT INTO conversa (id_conversa, fk_ong_cnpj, fk_usuario_cpf, conteudo, dataconversa) "
		               + "VALUES ('" + conversa.getConteudo() + "', "
		               + conversa.getFk_usuario_cpf() + ", " 
                       + conversa.getDataconversa() + ", "
                       + conversa.getId_conversa() + ", "
                       + conversa.getFk_ong_cnpj() + ", ";
            PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
			
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Conversa get(int id_conversa) {
		Conversa conversa = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM conversa WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 conversa = new Conversa(rs.getInt("id_conversa"), rs.getString("conteudo"),
				 						rs.getString("fk_usuario_cpf"), 
	        			               rs.getDate("dataconversa"),
	        			               rs.getString("fk_ong_cnpj"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return conversa;
	}
	
	
	public List<Conversa> get() {
		return get("");
	}

	
	public List<Conversa> getOrderById_conversa() {
		return get("id_conversa");		
	}
	

	public List<Conversa> getOrderByDataconversa() {
		return get("dataconversa");		
	}
	
	public List<Conversa> getOrderByFk_ong_cnpj() {
		return get("fk_ong_cnpj");		
	}
	
	public List<Conversa> getOrderByFk_usuario_cpf() {
		return get("fk_usuario_cpf");		
	}
	
	public List<Conversa> getOrderByConteudo() {
		return get("conteudo");		
	}

	
	private List<Conversa> get(String orderBy) {
		List<Conversa> conversas = new ArrayList<conversa>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM conversa" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Conversa p = new Conversa(rs.getInt("id_conversa"), rs.getString("conteudo"),
				rs.getString("fk_usuario_cpf"), 
			  rs.getDate("dataconversa"),
			  rs.getString("fk_ong_cnpj"));
	            conversas.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return conversas;
	}
	
	
	public boolean update(Conversa conversa) {
		boolean status = false;
		try {  
			String sql = "UPDATE conversa SET conteudo = '" + conversa.getConteudo() + "', "
					   + "fk_usuario_cpf = " + conversa.getFk_usuario_cpf() + ", " 
                       + "dataconversa = " + conversa.getDataconversa() + ","
                       + "id_conversa = " + conversa.getId_conversa() + ","
					   + "fk_ong_cnpj = " + conversa.getFk_ong_cnpj() +;
            
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
            
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int id_conversa) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM conversa WHERE id_conversa = " + id_conversa);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}
