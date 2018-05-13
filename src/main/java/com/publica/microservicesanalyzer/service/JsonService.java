package com.publica.microservicesanalyzer.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Serviços relacionados a json
 * @author Ariel Rai Rodrigues(ariel.rodrigues@publica.inf.br)
 *
 */
@Service
public class JsonService {

	private ObjectMapper mapper;
	private static final Logger log = LoggerFactory.getLogger(JsonService.class); 
	
	public JsonService() {
		mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

		SimpleModule appModule = new SimpleModule("localDateConversos", new Version(1, 0, 0, null, null, null));
		appModule.addSerializer(Date.class, new JsonSerializer<Date>() {

			private DateFormat dateFormat = getDateFormat();
			
			@Override
			public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
				// sempre fazer um clone do DateFormat, pois o mesmo não é ThreadSafe 
				DateFormat _dateFormat = (DateFormat) dateFormat.clone();
				jgen.writeObject(_dateFormat.format(value));
			}
		});

		appModule.addDeserializer(Date.class, new JsonDeserializer<Date>() {

			private DateFormat dateFormat = getDateFormat();
			
			@Override
			public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
				try {
					// sempre fazer um clone do DateFormat, pois o mesmo não é ThreadSafe 
					DateFormat _dateFormat = (DateFormat) dateFormat.clone();
					return _dateFormat.parse(jp.getText());
				} catch (ParseException e) {
					throw new IOException(e);
				}
			}
		});
		
		// Serializa localDate
		appModule.addSerializer(LocalDate.class, new JsonSerializer<LocalDate>() {

			@Override
			public void serialize(LocalDate value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
				jgen.writeObject(value.toString());
			}
		});

		appModule.addDeserializer(LocalDate.class, new JsonDeserializer<LocalDate>() {

			@Override
			public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
				try {
					return LocalDate.parse(jp.getText());
				} catch (Exception e) {
					return ZonedDateTime.parse(jp.getText()).toLocalDate();
				}
			}
		});

		// Serializa localDatetime
		appModule.addSerializer(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {

			@Override
			public void serialize(LocalDateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
				jgen.writeObject(value.toString());
			}
		});
		appModule.addDeserializer(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {

			@Override
			public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
				try {
					return LocalDateTime.parse(jp.getText());
				} catch (Exception e) {
					return ZonedDateTime.parse(jp.getText()).toLocalDateTime();
				}
			}
		});
		mapper.registerModule(appModule);
	
	}
	

	/**
	 * Retorna o formato de Data utilizado na conversão dos valores para JSON
	 * @return um {@link DateFormat}
	 */
	public static DateFormat getDateFormat() {
		return new SimpleDateFormat("yyyy-MM-dd");
	}
	
	/**
	 * Converte uma string em formato json para uma classe
	 * @param clazz - classe que será transformado o json, <b>Deve possuir um construtor sem parâmetros</b> 
	 * @param json - o texto em formato json representando o objeto
	 * @return uma instância do tipo T
	 */
	public <T> T toObject(Class<T> clazz, String json){
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		try (JsonParser jp = mapper.getFactory().createParser(json)){
			Object readValue = mapper.readValue(jp, mapper.constructType(clazz));
			return clazz.cast(readValue);
		} catch (Exception e) {
			log.error("Não foi possível converter o objeto requisitado", e);
			return null;
		}
	}

	 /** Transforma um string em um pojo 
	 * <b> a classe deve possuir um construtor sem parâmetros </b>
	 * @param clazz - Classe que será convertida a string
	 * @param pojoString - String do pojo que será convertido
	 * @return uma instância de T, o objeto convertido
	 */
	public <T> T jsonStringToPojo(String pojoString, TypeReference<T> clazz) {
		return mapper.convertValue(pojoString.getBytes(Charset.forName("UTF-8")), clazz);
	}
	
	/**
	 * Converte o mapa de atributos em um objeto
	 * @param clazz - classe do objeto
	 * @param fields - campos a serem preenchidos
	 * @return uma instância do <T>
	 */
	public <T> T toObject(Class<T> clazz, Map<String, Object> fields) {
		return mapper.convertValue(fields, clazz);
	}
	
	public <T> T toObject(TypeReference<T> clazz, String pojoString){
		return mapper.convertValue(pojoString.getBytes(Charset.forName("UTF-8")), clazz);
	}
	
	/**
	 * Converte um object para uma string em formato json
	 * @param object - o object que será convertido para json
	 * @return uma string com o formato json
	 */
	public <T> String toJson(T object){
		try {
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			log.error("Não foi possível converter o objeto requisitado", e);
			return null;
		}
	}
}