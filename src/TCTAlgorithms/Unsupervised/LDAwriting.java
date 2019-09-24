package TCTAlgorithms.Unsupervised;

import java.io.File;
import java.io.FileWriter;
import weka.core.Attribute;
import weka.core.Instances;

public class LDAwriting {
       
    public static void writingLDA(double[][]LDAmatrix,Instances data, String dir){
        int classindex;
        Attribute att;
        String file = dir;//cria com o diretorio passado, seja o mesmo da saida ou não
        file = file+"/"+data.relationName()+"_"+LDAmatrix[0].length;//concatena ao caminho
        //o nome da base e o numero de grupos usado no clustering
        
        try{
            File output = new File(file);//cria o arquivo no caminho indicado com nome indicado
            FileWriter outputLDA = new FileWriter(output.getAbsolutePath() + ".arff");//acrescenta a propriedade
            outputLDA.write("@relation "+ data.relationName() + "_" + LDAmatrix[0].length+"\n\n");
            for(int rep=0;rep<LDAmatrix[0].length;rep++){//para o numero de clusters
                outputLDA.write("@attribute topico-" + rep + " numeric\n");
            }
            data.setClassIndex(data.numAttributes()-1);//determina qual o atributo classe do arff
            outputLDA.write(data.classAttribute().toString()+"\n\n");//class_atr
            outputLDA.write("@data\n");
            for (int instance = 0; instance < LDAmatrix.length; instance++) {
                for (int value = 0; value < LDAmatrix[0].length; value++) {
                    outputLDA.write(LDAmatrix[instance][value]+",");
                }
                classindex=data.classIndex();//to string talvez direto em instance
                //na classe attribute posso tentar recuperar seu value passando um int
                outputLDA.write(data.instance(instance).toString(classindex)+"\n");
            }
            outputLDA.close();
        }catch(Exception e){
            System.out.println("Error when generating file");
            e.printStackTrace();
        }
        
    }
}
