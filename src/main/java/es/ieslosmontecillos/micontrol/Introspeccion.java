package es.ieslosmontecillos.micontrol;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;

public class Introspeccion {
    public static void main(String[] args) {
        MiControl miControl = new MiControl();
        BigDecimal bigDecimal = new BigDecimal(2);

        Class c1 = bigDecimal.getClass();
        System.out.println("La clase del objeto miControl es :" + c1);
        System.out.println("El nombre de la clase de miControl es: " + c1.getName());

        String nombreClase = "es.ieslosmontecillos.micontrol.MiControl";

        try {
            Class c2 = Class.forName(nombreClase);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("La clase " + c1.getName() + " extends " + c1.getSuperclass().getName());

        muestraConstructores(c1);
        muestraMetodos(c1);
        muestraCampos(c1);
        //c1.getConstructors();
    }

    public static void muestraConstructores(Class c1) {
        Constructor[] constructor = c1.getDeclaredConstructors();
        System.out.println("Los constructores de clase son: ");
        for (Constructor c : constructor) {
            String nombre = c.getName();
            System.out.print("    " + Modifier.toString(c.getModifiers()));
            System.out.print("    " + nombre + "(");

            Class[] tipoParametro = c.getParameterTypes();
            for (int i = 0; i < tipoParametro.length; i++) {
                if (i > 0) System.out.print(",     ");
                System.out.print(tipoParametro[i].getName());
            }

            System.out.println(");");
        }
    }

    public static void muestraMetodos(Class c1) {
        Method[] metodos = c1.getDeclaredMethods();
        System.out.println("Los metodos de la clase son: ");
        for (Method m : metodos) {
            Class tipoDevuelto = m.getReturnType();
            String nombre = m.getName();
            System.out.print(" " + Modifier.toString(m.getModifiers()));
            System.out.print(" " + tipoDevuelto.getName() + " " + nombre + "(");

            Class[] tipoParams = m.getParameterTypes();
            for(int i = 0; i<tipoParams.length;i++){
                if(i>0) System.out.print(", ");
                System.out.print(tipoParams[i].getName());
            }
            System.out.println(");");
        }
    }
    public static void muestraCampos(Class c1){
        Field[] campos = c1.getDeclaredFields();
        System.out.println("Los campos de la clase son: ");
        for(Field f: campos){
            Class tipoCampo = f.getType();
            String nombre = f.getName();
            System.out.print(" " + Modifier.toString(f.getModifiers()));
            System.out.print(" " + tipoCampo.getName() + " " + nombre + " (");
            System.out.println(");");
        }
    }
}
