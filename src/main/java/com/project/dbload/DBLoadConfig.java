package com.project.dbload;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app.dbload")
public class DBLoadConfig {
	
	//Valor por defecto, determina si se carga la base de datos al inicializar el proyecto
	private boolean loadDBOnRun=true;
	
	public boolean isLoadDBOnRun() {
		return loadDBOnRun;
	}

	public void setLoadDBOnRun(boolean loadDBOnRun) {
		this.loadDBOnRun = loadDBOnRun;
	}

}
