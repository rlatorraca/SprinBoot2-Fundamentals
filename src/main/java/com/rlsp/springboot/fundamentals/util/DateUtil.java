package com.rlsp.springboot.fundamentals.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

/**
 * @Repository ==> especializacao de @Component para ser utulizada com DAO, faz com as EXCECOES nao checadas serao traduzidas usando o Spring Data Access Exception
 * @Service ==> especializacao de @Component, mas sem adicionar qualquer funcionalidade, utilizada nas camadas de SERVICO
 * @Component  ==> Marca que a CLASSE sera SCANEADO pelo @ComponentScan (no classe de inicializacao)
 * 
 * @author rlatorraca
 *
 */
@Component // ==> Marca que a CLASSE sera SCANEADO pelo @ComponentScan (no classe de inicializacao)
public class DateUtil {

	public String formatLocalDateTimeToDBStyle (LocalDateTime localDateTime) {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime);
				
	}
}
