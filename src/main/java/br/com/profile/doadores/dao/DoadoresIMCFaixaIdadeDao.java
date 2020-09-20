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
import br.com.profile.doadores.model.json.DoadorIMC;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class DoadoresIMCFaixaIdadeDao {

	public List<DoadorIMC> doadoresIMCFaixaIdade() throws Exception {
		
		try (Connection connection = DriverManager.getConnection(AppConfiguration.URL, 
																 AppConfiguration.USERNAME, 
																 AppConfiguration.PASSWORD)){
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT F.FAIXA_DESCRIPTION, F.SOMATORIA_ALTURA, F.SOMATORIA_PESO, COUNT(D.FAIXA_IDADE) TOTAL ") 
			   .append("FROM DOADORES D ") 
			   .append("INNER JOIN DOADORES_FAIXA_IDADE F ON F.ID = D.FAIXA_IDADE ") 
			   .append("GROUP BY F.FAIXA_DESCRIPTION, F.SOMATORIA_ALTURA, F.SOMATORIA_PESO ")
			   .append("ORDER BY TOTAL ");
			
			List<DoadorIMC> faixaIdadeDoadoresCollection = new ArrayList<>();
			try(Statement stmt = connection.createStatement(); ResultSet resultSet = stmt.executeQuery(sql.toString())){
				while(resultSet.next()) {
					double mediaAltura = resultSet.getDouble(2)/resultSet.getInt(4);
					double mediaPeso   = resultSet.getDouble(3)/resultSet.getInt(4);
					
					double imcMedio = mediaPeso/(mediaAltura*mediaAltura);
					
					DoadorIMC doadoresFaixaIdade = new DoadorIMC(resultSet.getString(1), imcMedio, resultSet.getInt(4));
					faixaIdadeDoadoresCollection.add(doadoresFaixaIdade);
				}
			}catch(Exception ex) {
				throw new Exception("Error on getValues, [error] = "+ex.getMessage());
			}
			return faixaIdadeDoadoresCollection;
		}catch(Exception ex) {
			throw new SQLException("Error on running search at database, [error] = "+ex.getMessage());
		} 
	}
	
}
