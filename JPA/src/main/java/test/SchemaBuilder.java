/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.HashMap;
import javax.persistence.Persistence;

/**
 *
 * @author Ejer
 */
public class SchemaBuilder 
{
    public static void main(String[] args) {
      
        
        HashMap puproperties=new HashMap();
            puproperties.put("javax.persistence.sql-load-script-source", "scripts/clearDB.sql");
      Persistence.generateSchema("jpaPU", puproperties);
         puproperties.put("javax.persistence.sql-load-script-source", "scripts/data.sql");
      Persistence.generateSchema("jpaPU", puproperties);

        puproperties.remove("javax.persistence.sql-load-script-source");
      Persistence.generateSchema("jpaPU", puproperties);
    }
}
// jeg loader fra script også. Hvis jeg bare skulle nulstille hver gang uden at loade fra script kunne jeg
//køre denne metode istedet Persistence.generateSchema("jpaPU", null); jeg var der ikke i tirsdags så så den der 
// video som Christian havde lavet som ikke skulle bruges som læringsressource men når jeg nu havde læst fik jeg bare 
// et landkort over hvor tingene lå nogenlunde vidende hvad jeg lavede. 