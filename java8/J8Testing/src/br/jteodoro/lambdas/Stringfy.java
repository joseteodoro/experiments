package br.jteodoro.lambdas;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Stringfy implements Consumer<Field> {

	Map<String, String> fields = new HashMap<>();
	
	private Object target;
	
	public Stringfy(Object target) {
		this.target = target;
	}
	
	@Override
	public void accept(Field t) {
		try {
			fields.put(t.getName(), String.valueOf(t.get(target)));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		MapToJson mapToJson = new MapToJson();
		fields.entrySet().stream().forEach(mapToJson);;
		return mapToJson.toString();
	}
	
	public static String jsonfy(Class<?> t, Object target ) {
		Stringfy stringfy = new Stringfy(target);
		Arrays.asList(t.getDeclaredFields()).forEach(stringfy);
		return stringfy.toString();
	}
	
}