package TCTAlgorithms.Unsupervised;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Utilities {
    
    
    public static void structuringResults(String results, String output){
        String[] split = new String[2];
        ArrayList<File> files = new ArrayList<>();
        Arquivos.lista(new File(results), files);
        StringBuilder csv;
        //pegar primeira parte do nome antes do _ se nao ingessa o prog
        for(int i=0; i<files.size(); i++){
            if(!files.get(i).getAbsolutePath().endsWith(".txt"))
                continue;
            split=files.get(i).getName().split("_");
            try{
                File outputFile = new File(output+"//"+split[0]+".txt");
                if(!(outputFile.exists())){
                    csv = new StringBuilder();
                    csv.append("Algorithm/Parameters;"
                            + "Min-Accuracy(%);Max-Accuracy(%);Avg-Accuracy(%);Dev-Accuracy(%);"
                            + "Min-Micro_Precision;Max-Micro_Precision;Avg-Micro_Precision;Dev-Micro_Precision;"
                            + "Min-Micro_Recall;Max-Micro_Recall;Avg-Micro_Recall;Dev-Micro_Recall;"
                            + "Min-Macro_Precision;Max-Macro_Precision;Avg-Macro_Precision;Dev-Macro_Precision;"
                            + "Min-Macro_Recall;Max-Macro_Recall;Avg-Macro_Recall;Dev-Macro_Recall;"
                            + "Min-Entropy;Max-Entropy;Avg-Entropy;Dev-Entropy;"
                            + "Min-Purity;Max-purity;Avg-Purityy;Dev-Purity;"
                            + "Min-Micro_F1;Max-Micro_F1;Avg-Micro_F1;Dev-Micro_F1;"
                            + "Min-Macro_F1;Max-Macro_F1;Avg-Macro_F1;Dev-Macro_F1;");
                            
                    csv.append("\n");
                }else{
                    BufferedReader reader = new BufferedReader(new FileReader(outputFile));
                    csv = new StringBuilder();
                    String line;
                    line = reader.readLine();
                    while(line != null){
                        csv.append(line+"\n");
                        line = reader.readLine();
                    }
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
                structuring(csv, files.get(i));
                csv.append("\n");
                writer.append(csv);
                writer.close();
            }catch(Exception e){
                e.printStackTrace();
            }   
        }
    }
    
    public static void structuring(StringBuilder csv, File result){
        ArrayList<Double> accuracies = new ArrayList();
        ArrayList<Double> micro_precisions = new ArrayList();
        ArrayList<Double> macro_precisions= new ArrayList();
        ArrayList<Double> micro_recalls = new ArrayList();
        ArrayList<Double> macro_recalls = new ArrayList();
        ArrayList<Double> purities= new ArrayList();
        ArrayList<Double> entropies= new ArrayList();
        ArrayList<Double> micro_f1= new ArrayList();
        ArrayList<Double> macro_f1= new ArrayList();
        String line="";
        String[] values = new String[3];
        BufferedReader file;
        try {
            file = new BufferedReader(new FileReader(result));
            csv.append(result.getName()+";");
            line=file.readLine();
            
            while(!(line.contains("Average"))){
                
                values=line.split(" ");
                if(line.contains("Accuracy")){
                    accuracies.add(Double.parseDouble(values[1]));
                }
                if(line.contains("Micro-Precision")){
                    micro_precisions.add(Double.parseDouble(values[1]));
                }
                if(line.contains("Micro-Recall")){
                    micro_recalls.add(Double.parseDouble(values[1]));
                }
                if(line.contains("Macro-Precision")){
                    macro_precisions.add(Double.parseDouble(values[1]));
                }
                if(line.contains("Macro-Recall")){
                    macro_recalls.add(Double.parseDouble(values[1]));
                }
                if(line.contains("Entropy")){
                    entropies.add(Double.parseDouble(values[1]));
                }
                if(line.contains("Purity")){
                    purities.add(Double.parseDouble(values[1]));
                }
                line=file.readLine();
            }
            micro_f1 = f1(micro_precisions,micro_recalls);
            macro_f1 = f1(macro_precisions,macro_recalls);
            writeValues(csv,accuracies);
            writeValues(csv,micro_precisions);
            writeValues(csv,micro_recalls);
            writeValues(csv,macro_precisions);
            writeValues(csv,macro_recalls);
            writeValues(csv,entropies);
            writeValues(csv,purities);
            writeValues(csv,micro_f1);
            writeValues(csv,macro_f1);
        } catch (IOException ex) {
            ex.printStackTrace();
        }            
    }
    
    public static void writeValues(StringBuilder csv, ArrayList<Double>values){
        double max=0;
        double soma=0;
        double min=10;
        double media=0;
        for(int i=0; i<values.size();i++){
            soma+=values.get(i);
            if(values.get(i)<min){
                min=values.get(i);
            }
            if(values.get(i)>max){
                max=values.get(i);
            }
        }
        media=soma/values.size();
        csv.append(min+";");
        csv.append(max+";");
        csv.append(media+";");
        csv.append(standardDeviation(values,media)+";");
    }
    
    public static double standardDeviation(ArrayList<Double> values, double media){
        double temporary=0;
        double soma=0;
        for(int value=0; value<values.size();value++){
            soma += Math.pow((values.get(value) - media),2);
//            System.out.println("media: "+ media);
//            temporary=values.get(value);
//            System.out.println("valor atual: " + temporary);
//            temporary=temporary-media;
//            System.out.println(temporary);
//            //para desvio padrao utiliza-se valor - a media
//            temporary=temporary*temporary;
//            System.out.println(temporary);
//            //e esse resultado faz ao quadrado
//            soma+=temporary;
//            System.out.println("soma até o momento:" + soma);
        }
        soma = soma/(values.size());
        soma=Math.sqrt(soma);
        return soma; 
    }
    
    public static ArrayList<Double> f1 (ArrayList<Double> precision, ArrayList<Double> recall){
        ArrayList<Double> f1Micro = new ArrayList<>();
        double value=0;
        for (int i = 0; i < precision.size(); i++) {
            f1Micro.add(2*precision.get(i)*recall.get(i)/(precision.get(i)+recall.get(i)));
        }
        return f1Micro;
    }
   
}
