package dao;

import model.Animal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class AnimalDAO extends DAO {	
	public AnimalDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Animal animal) {
		boolean status = false;
		try {
			String sql = "INSERT INTO animal (nome, raca, porte, sexo, idade, id_animal, fk_ong_cnpj, categoria) "
		               + "VALUES ('" + animal.getNome() + "', "
		               + animal.getRaca() + ", " 
                       + animal.getPorte() + ", "
                       + animal.getSexo() + ", "
                       + animal.getIdade() + ", "
                       + animal.getId_animal() + ", "
                       + animal.getCategoria() + ", "
                       + animal.getFk_ong_cnpj() + ", ";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
            */
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Animal get(int id_animal) {
		Animal animal = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM animal WHERE id_animal="+id_animal;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 animal = new Animal(rs.getInt("id_animal"), rs.getString("nome"), rs.getString("raca"), 
	                				   rs.getString("porte"), 
	        			               rs.getChar("sexo"),
                                       rs.getInt("idade"),
                                       rs.getString("categoria"),
	        			               rs.getString("fk_ong_cnpj"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return animal;
	}
	
	
	public List<Animal> get() {
		return get("");
	}

	
	public List<Animal> getOrderById_animal() {
		return get("id_animal");		
	}
	
	public List<Animal> getOrderByRaca() {
		return get("raca");		
	}

	public List<Animal> getOrderByPorte() {
		return get("porte");		
	}
	
	public List<Animal> getOrderByNome() {
		return get("nome");		
	}
	
	public List<Animal> getOrderBySexo() {
		return get("sexo");		
	}
	
	public List<Animal> getOrderByCategoria() {
		return get("categoria");		
	}
	
	public List<Animal> getOrderByIdade() {
		return get("idade");		
	}
	
	public List<Animal> getOrderByFk_ong_cnpj() {
		return get("fk_ong_cnpj");		
	}

	
	private List<Animal> get(String orderBy) {
		List<Animal> animais = new ArrayList<animal>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM animal" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Animal p = new Animal(rs.getInt("id_animal"), rs.getString("nome"), rs.getString("raca"), 
                rs.getString("porte"), 
                rs.getChar("sexo"),
                rs.getInt("idade"),
                rs.getString("categoria"), 
                rs.getString("fk_ong_cnpj"));
	            animais.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return animais;
	}
	
	
	public boolean update(Animal animal) {
		boolean status = false;
		try {  
			String sql = "UPDATE animal SET nome = '" + animal.getNome() + "', "
					   + "raca = " + animal.getRaca() + ", " 
					   + "porte = " + animal.getPorte() + ","
					   + "idade = " + animal.getIdade() + ","
                       + "sexo = " + animal.getSexo() + ","
					   + "categoria = " + animal.getCategoria() + ","
                       + "id_animal = " + animal.getId_animal() + ","
					   + "fk_ong_cnpj = " + animal.getFk_ong_cnpj() +;
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int id_animal) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM animal WHERE id_animal = " + id_animal);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}
