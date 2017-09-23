package br.jteodoro.lambdas.main;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class NashornTesting {

	public static void main(String[] args) throws ScriptException {
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		Object eval = engine.eval("'Hello World!'");
		System.out.println(eval);
	}

}
