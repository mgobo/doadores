package br.com.profile.doadores.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.profile.doadores.configuration.AppConfiguration;
import br.com.profile.doadores.model.json.DoadorUF;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class DoadoresUFDao {

	public List<DoadorUF> contarDoadoresPorUF() throws Exception {
		
		try (Connection connection = DriverManager.getConnection(AppConfiguration.URL, 
																 AppConfiguration.USERNAME, 
																 AppConfiguration.PASSWORD)){
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT E.DESCRIPTION, COUNT(DOADORES.ESTADO_ID)" ) 
			   .append("FROM DOADORES ") 
			   .append("INNER JOIN ESTADO E ON E.ID = DOADORES.ESTADO_ID ") 
			   .append("GROUP BY E.DESCRIPTION ")
			   .append("ORDER BY E.DESCRIPTION");
			
			List<DoadorUF> doadoresPorUFCollection = new ArrayList<>();
			try(Statement stmt = connection.createStatement(); ResultSet resultSet = stmt.executeQuery(sql.toString())){
				while(resultSet.next()) {
					DoadorUF doadoresUF = new DoadorUF(resultSet.getString(1), resultSet.getInt(2));
					doadoresPorUFCollection.add(doadoresUF);
				}
			}catch(Exception ex) {
				throw new Exception("Error on getValues, [error] = "+ex.getMessage());
			}
			return doadoresPorUFCollection;
		}catch(Exception ex) {
			throw new SQLException("Error on running search at database, [error] = "+ex.getMessage());
		} 
	}
	
}
