package br.com.profile.doadores.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.stream.Stream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.util.ResourceUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.profile.doadores.model.Doadores;
import br.com.profile.doadores.model.DoadoresFaixaIdade;
import br.com.profile.doadores.model.DoadoresGenero;
import br.com.profile.doadores.model.DoadoresJson;
import br.com.profile.doadores.model.DoadoresTipoSanguineo;
import br.com.profile.doadores.model.Estado;
import br.com.profile.doadores.repositories.DoadoresDaoImpl;
import br.com.profile.doadores.repositories.DoadoresFaixaIdadeDaoImpl;
import br.com.profile.doadores.repositories.DoadoresGeneroDaoImpl;
import br.com.profile.doadores.repositories.DoadoresTipoSaguineoImpl;
import br.com.profile.doadores.repositories.EstadoDaoImpl;

@Configuration
public class AppConfiguration {

	public static String URL 	  = System.getenv("URL");
	public static String USERNAME = System.getenv("USERNAME");
	public static String PASSWORD = System.getenv("PASSWORD");
	
	@Autowired
	private DoadoresDaoImpl doadoresDaoImpl;
	
	@Autowired
	private EstadoDaoImpl estadoDaoImpl;
	
	@Autowired
	private DoadoresFaixaIdadeDaoImpl doadoresFaixaIdadeDaoImpl;
	
	@Autowired
	private DoadoresGeneroDaoImpl doadoresGeneroDaoImpl;
	
	@Autowired
	private DoadoresTipoSaguineoImpl doadoresTipoSanguineoImpl;
	
	private Integer age;
	
	@EventListener(classes = ApplicationStartedEvent.class)
	public void loadFile() throws Exception{
		try {
			System.out.println("Cleaning all documents from collection...");
			this.doadoresTipoSanguineoImpl.deleteAll();
			this.doadoresFaixaIdadeDaoImpl.deleteAll();
			this.estadoDaoImpl.deleteAll();
			this.doadoresDaoImpl.deleteAll();
			
			File file = null;
			if(System.getenv("ENVIRONMENT").equals("HML")) {
				file = ResourceUtils.getFile("src/main/resources/data.json");
			} else {
				file = new File("/opt/workspace/data.json");
			}
			try(FileReader fr = new FileReader(file)){
				JsonReader reader 	= Json.createReader(fr);
				JsonArray jsonArray = reader.readArray();
				jsonArray.stream().forEach(j->{
					GsonBuilder gsonBuilder = new GsonBuilder();
					gsonBuilder.setLenient();
					
					Gson gson = gsonBuilder.create();
					DoadoresJson doadoresJson = gson.fromJson(j.asJsonObject().toString(), DoadoresJson.class);
					this.criarDoadores(doadoresJson);
				});
				
			}catch(IOException ex) {
				throw new IOException(ex.getMessage());
			}
		}catch(FileNotFoundException ex) {
			throw new FileNotFoundException("File was not found, error = "+ex.getMessage());
		}
	}
	
	private void criarDoadores(DoadoresJson doadoresJson) {
		Doadores doadores = new Doadores();
		Method[] methods  = doadores.getClass().getDeclaredMethods();
		for(Method m : methods) {
			if(m.getName().contains("get")) {
				boolean exists = Stream.of(doadoresJson.getClass().getDeclaredMethods()).anyMatch(json->json.getName().equals(m.getName()));
				if(exists) {
					try {
						String methodName  = m.getName().replace("get", "set");
						String methodTypeP = m.getReturnType().getSimpleName();
						String methdoTypeJ = doadoresJson.getClass().getMethod(m.getName()).getReturnType().getSimpleName();
						if(methodTypeP.equals(methdoTypeJ)) {
							Method method = doadores.getClass().getDeclaredMethod(methodName, m.getReturnType());
							method.invoke(doadores, doadoresJson.getClass().getMethod(m.getName()).invoke(doadoresJson));
						}
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
		Estado estado = estadoDaoImpl.findByDescrption(doadoresJson.getEstado());
		if(estado == null) {
			estado = new Estado();
			estado.setDescription(doadoresJson.getEstado());
			estado = estadoDaoImpl.save(estado);
		}
		DoadoresFaixaIdade faixaIdade 				= this.faixaIdade(doadoresJson.getData_nasc(), doadoresJson.getPeso(), doadoresJson.getAltura());
		DoadoresTipoSanguineo doadoresTipoSanguineo = this.pacienteTipoSanguineo(doadoresJson.getTipo_sanguineo(), this.age);
		doadores.setDoadoresFaixaIdade(faixaIdade);
		doadores.setDoadoresTipoSanguineo(doadoresTipoSanguineo);
		doadores.setEstado(estado);
		
		doadores = this.doadoresDaoImpl.save(doadores);
		this.pacienteGenero(doadoresJson, doadores);
	}
	
	private DoadoresFaixaIdade faixaIdade(String idade, Double peso, Double altura) {
		LocalDate currentDate = LocalDate.now();
		Integer yearAge       = Integer.parseInt(idade.split("/")[2]);
		
		String description = "";
		String codigo 	   = "";
		this.age = currentDate.getYear() - yearAge;
		if(age > 0 && age <= 10) {
			codigo = "1";
			description = "Entre 0 e 10";
		}else if(age >= 11 && age <= 20) {
			codigo = "2";
			description = "Entre 11 e 20";
		}else if(age >= 21 && age <= 30) {
			codigo = "3";
			description = "Entre 21 e 30";
		}else if(age >= 31 && age <= 40) {
			codigo = "4";
			description = "Entre 31 e 40";
		}else if(age >= 41 && age <= 50) {
			codigo = "5";
			description = "Entre 41 e 50";
		}else if(age >= 51 && age <= 60) {
			codigo = "6";
			description = "Entre 51 e 60";
		}else if(age >= 61 && age <= 70) {
			codigo = "7";
			description = "Entre 61 e 70";
		}else if(age >= 71 && age <= 80) {
			codigo = "8";
			description = "Entre 71 e 80";
		}else if(age >= 81 && age <= 90) {
			codigo = "9";
			description = "Entre 81 e 90";
		}else if(age >= 91) {
			description = "Maior que 91";
			codigo = "10";
		}
		
		DoadoresFaixaIdade doadoresFaixaIdade = this.doadoresFaixaIdadeDaoImpl.getFaixaIdadeByCodigo(codigo);
		if(doadoresFaixaIdade == null) {
			doadoresFaixaIdade = new DoadoresFaixaIdade();
			doadoresFaixaIdade.setCodigo(codigo);
			doadoresFaixaIdade.setFaixaDescription(description);
			doadoresFaixaIdade.setSomatoriaAltura(altura);
			doadoresFaixaIdade.setSomatoriaPeso(peso);
		} else {
			doadoresFaixaIdade.setSomatoriaPeso(doadoresFaixaIdade.getSomatoriaPeso()+peso);
			doadoresFaixaIdade.setSomatoriaAltura(doadoresFaixaIdade.getSomatoriaAltura()+altura);
		}
		doadoresFaixaIdade = this.doadoresFaixaIdadeDaoImpl.save(doadoresFaixaIdade);
		return doadoresFaixaIdade;
	}
	
	private DoadoresTipoSanguineo pacienteTipoSanguineo(String tipoSanguineo, Integer idade) {
		DoadoresTipoSanguineo doadoresTipoSanguineo = this.doadoresTipoSanguineoImpl.getTipoSanguineoByTipo(tipoSanguineo);
		if(doadoresTipoSanguineo == null) {
			doadoresTipoSanguineo = new DoadoresTipoSanguineo();
			doadoresTipoSanguineo.setTipo_sanguineo(tipoSanguineo);
			doadoresTipoSanguineo.setSomaIdades(idade.longValue());
			doadoresTipoSanguineo.setSomaDoadores(1l);
		} else {
			doadoresTipoSanguineo.setSomaIdades(doadoresTipoSanguineo.getSomaIdades()+idade.longValue());
			doadoresTipoSanguineo.setSomaDoadores(doadoresTipoSanguineo.getSomaDoadores()+1l);
		}
		doadoresTipoSanguineo = this.doadoresTipoSanguineoImpl.save(doadoresTipoSanguineo);
		return doadoresTipoSanguineo;
	}
	
	private DoadoresGenero pacienteGenero(DoadoresJson doadoresJson, Doadores paciente){
		DoadoresGenero doadoresGenero = new DoadoresGenero();
		doadoresGenero.setIdade(this.age);
		doadoresGenero.setAltura(doadoresJson.getAltura());
		doadoresGenero.setPeso(doadoresJson.getPeso());
		doadoresGenero.setImc(doadoresGenero.getPeso()/(doadoresGenero.getAltura()*doadoresGenero.getAltura()));
		doadoresGenero.setSexo(doadoresJson.getSexo());
		doadoresGenero.setDoadores(paciente);
		
		doadoresGenero = this.doadoresGeneroDaoImpl.save(doadoresGenero);
		return doadoresGenero;
	}
}
