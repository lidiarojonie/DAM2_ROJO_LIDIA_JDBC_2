# RESPUESTAS EXAMEN 
**PREGUNTA 1: Explica cómo funciona la relación 1:N entre CentroForense y MuestraForense tanto en SQL como en Java.

En SQL:Se crea una Clave Foránea (`FOREIGN KEY`) en la tabla `MuestraForense` 
llamada `CentroForense_ID` que apunta a la Clave Primaria (`ID`) de la tabla `CentroForenseS`. 
En Java: En lugar de guardar un `int CentroForenseId`, la clase `MuestraForense` contiene una referencia completa al objeto `CentroForense` (`private CentroForense CentroForense;`). Cuando el DAO lee de la BD, mapea el `CentroForense_ID` creando un objeto `CentroForense` y asignándoselo a la Muestra, logrando composición orientada a objetos en vez de simple código relacional. 


**PREGUNTA 2: Explica por qué en Java utilizamos `private CentroForense CentroForense;` y no `private int CentroForenseId;`.

Porque Java es un lenguaje Orientado a Objetos (POO). Si guardáramos `int CentroForenseId`, para saber el nombre de la CentroForense desde una Muestra tendríamos que hacer otra consulta separada. Al usar `private CentroForense CentroForense;`, delegamos la estructura relacional a objetos completos, pudiendo hacer `MuestraForense.getCentroForense().getNombre()` en memoria. El DAO se 
encarga de este "mapping". 


**PREGUNTA 3: Explica qué ventaja aporta PreparedStatement frente a concatenar SQL 
manualmente.
Seguridad (Previene Inyección SQL): Automáticamente escapa caracteres peligrosos. 
Rendimiento: La base de datos pre-compila la consulta. Si ejecutamos el mismo `INSERT` 1000 veces con valores distintos, el motor de BD no tiene que calcular el plan de ejecución 1000 veces, solo una. 
