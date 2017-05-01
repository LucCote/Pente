
import javax.script.*;
public class Database {
 public Database(String[] args) throws Exception {
 // create a script engine manager
 ScriptEngineManager factory = new ScriptEngineManager();
 // create a JavaScript engine
 ScriptEngine engine = factory.getEngineByName("JavaScript");
 // evaluate JavaScript code from String
 engine.eval("print('Welocme to java world')");
 }
}