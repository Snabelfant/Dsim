package dsim.model;

import java.util.Map;

/**
 * Created by Dag on 30.09.2017.
 */
public class Agent {
    public static final Agent NOBODY = new Agent();

    private static final Object UNINITIALIZED = new Object();

    protected void defineVariable(String name, Map<String, Object> variables) {
        if (variables.get(name) != null) {
            throw new RuntimeException("Finnes allerede " + name);
        }

        variables.put(name, UNINITIALIZED);
    }

    protected void setVariable(String name, Object value, Map<String, Object> variables) {
        if (value == null) {
            throw new RuntimeException("Null i " + name);
        }

        if (variables.put(name, value) == null) {
            throw new RuntimeException("Finnes ikke " + name);
        }
    }

    protected void setInt(String name, int value, Map<String, Object> variables) {
        setVariable(name, value, variables);
    }

    protected Object getVariable(String name, Map<String, Object> variables) {
        Object value = variables.get(name);

        if (value == null) {
            throw new RuntimeException("Finnes ikke " + name);
        }

        return value;
    }

    protected int getInt(String name, Map<String, Object> variables) {
        return (int) getVariable(name, variables);
    }
}


