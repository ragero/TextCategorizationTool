
package TCTAlgorithms.Unsupervised;

import java.io.File;
import java.util.ArrayList;

public class Arquivos {
    
    
    public static void lista(File entrada, ArrayList<File> arffs){
        File[] arquivos = entrada.listFiles();
        for(int i=0; i<arquivos.length;i++){
            if(arquivos[i].isFile())
                arffs.add(arquivos[i]);
            else
                lista(arquivos[i],arffs);
        } 
    }
    
}
