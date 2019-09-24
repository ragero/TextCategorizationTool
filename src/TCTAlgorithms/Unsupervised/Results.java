package TCTAlgorithms.Unsupervised;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import weka.core.Instances;

public class Results {
    
    private double[] microRecalls;
    private double[] microPrecisions;
    private double[] macroRecalls;
    private double[] macroPrecisions;
    private double[] accuracies;
    private double [] purities;
    private double [] entropies;
    private long[] buildingTimes;
    private int [] numIterations;
    private File output;
    private int numReps;
    

    public Results(File output, int numLines){
        
        this.output = output;
        
        numReps = numLines;
        
        accuracies = new double[numLines];
        microPrecisions = new double[numLines];
        microRecalls = new double[numLines];
        macroPrecisions = new double[numLines];
        macroRecalls = new double[numLines];
        buildingTimes = new long[numLines];
        numIterations = new int[numLines];
        purities = new double [numLines];
        entropies = new double [numLines];
        //complete = new boolean[numLines];
        
        for(int lin=0;lin<numLines;lin++){
            accuracies[lin] = -1;
            microPrecisions[lin] = -1;
            microRecalls[lin] = -1;
            macroPrecisions[lin] = -1;
            macroRecalls[lin] = -1;
            buildingTimes[lin] = -1;
            numIterations[lin]= 0;
            entropies[lin]=0;
            purities[lin]=0;
            //complete[lin]= false;
        }
        
    }
    
    public void saveResults(){
        
        File outputTemp = new File(output.getAbsolutePath() + ".tmp");
        if(outputTemp.exists()){
            outputTemp.delete();
        }
        
        double acmAccuracy = 0;
        double acmMicroPrecision = 0;
        double acmMicroRecall = 0;
        double acmMacroPrecision = 0;
        double acmMacroRecall = 0;
        double acmBuildingTime = 0;
        double acmPurity=0;
        double acmEntropy=0;
        int acmIterations = 0;
        double acmSD = 0;
        
        try{
            FileWriter outputResults = new FileWriter(output.getAbsolutePath() + ".txt");
            for(int rep=0;rep<numReps;rep++){
                outputResults.write("Repetition: " + (rep + 1) + " --------------------------------------------------------------------\n");
//                outputResults.write("Fold: " + (rep + 1) + " --------------------------------------------------------------------\n");
                outputResults.write("Accuracy(%): " + this.getAccuracy(rep) + "\n");
                acmAccuracy += this.getAccuracy(rep);
//                outputResults.write("Error (%): " + (100 - this.getAccuracy(rep)) + "\n");
                outputResults.write("Micro-Precision: " + this.getMicroPrecision(rep) + "\n");
                acmMicroPrecision += this.getMicroPrecision(rep);
                outputResults.write("Micro-Recall: " + this.getMicroRecall(rep) + "\n");
                acmMicroRecall += this.getMicroRecall(rep);
                outputResults.write("Macro-Precision: " + this.getMacroPrecision(rep) + "\n");
                acmMacroPrecision += this.getMacroPrecision(rep);
                outputResults.write("Macro-Recall: " + this.getMacroRecall(rep) + "\n");
                acmMacroRecall += this.getMacroRecall(rep);
                outputResults.write("Purity: " + this.getPurity(rep)+ "\n");
                acmPurity += this.getPurity(rep);
                outputResults.write("Entropy: " + this.getEntropy(rep) + "\n");
                acmEntropy += this.getEntropy(rep);
                outputResults.write("Model Building Time (s): " + ((double)this.getBuildingTime(rep) / (double)1000) + "\n");
                acmBuildingTime += this.getBuildingTime(rep);
//                outputResults.write("Number of Iterations: " + this.getNumIterations(rep) + "\n");
//                acmIterations += this.getNumIterations(rep);
            }   
            //Saving the average of the results
            outputResults.write("\n-------------------------------------\n");
            int totalExecutions = numReps;
            double averageAccuracy = (double)acmAccuracy / (double)totalExecutions;
            double averageMicroPrecision = (double)acmMicroPrecision / (double)totalExecutions;
            double averageMicroRecall = (double)acmMicroRecall / (double)totalExecutions;
            double averageMacroPrecision = (double)acmMacroPrecision / (double)totalExecutions;
            double averageMacroRecall = (double)acmMacroRecall / (double)totalExecutions;
            double averageBuildingTime = ((double)acmBuildingTime / (double)1000) / (double)totalExecutions;
            double averageIterations = (double)acmIterations / (double)totalExecutions;
            double averageEntropy = acmEntropy / (double)totalExecutions;
            double averagePurity = acmPurity / (double)totalExecutions;
            for(int rep=0;rep<numReps;rep++){
                acmSD += Math.pow((this.getAccuracy(rep) - averageAccuracy), 2);
            }
            acmSD = (double)acmSD / (double)totalExecutions;
            double standardDeviation = Math.sqrt(acmSD);
            
            outputResults.write("Average Accuracy (%): " + averageAccuracy + "\n");
            outputResults.write("Average Micro-Precision: " + averageMicroPrecision + "\n");
            outputResults.write("Average Micro-Recall: " + averageMicroRecall + "\n");
            outputResults.write("Average Macro-Precision: " + averageMacroPrecision + "\n");
            outputResults.write("Average Macro-Recall: " + averageMacroRecall + "\n");
            outputResults.write("Standard Deviation Accuracy: " + standardDeviation +"\n");
            outputResults.write("Average Model Building Time (s): " + averageBuildingTime + "\n");
            outputResults.write("Average Number of Iterations (s): " + averageIterations+ "\n");
            outputResults.write("Average Purity: " + averagePurity + "\n");
            outputResults.write("Average Entropy: " + averageEntropy + "\n");
            outputResults.close();
        }catch(Exception e){
            System.out.println("Error when saving file");
            e.printStackTrace();
        }
    }
    
    /*public synchronized void setComplete(int lin, boolean value){
        complete[lin] = value;
        
        boolean all = true;
        for(int rep=0; rep<numReps; rep++){
                if(complete[rep] == false){
                    all = false;
            }
        }
        
        if(all == true){
            saveResults();
        }else{
            saveObject();
        }
    }*/
    
    public synchronized void computeEvaluationMeasures(Clusterer cluster, Instances data, int rep, long time){
       
        System.out.println(output.getAbsolutePath() + "-----------------------------------------------------------------------");
        System.out.println("Repetition: " + (rep+1) + "------------------------------------------------------------------");
        
        Evaluation.fMeasure(data, cluster);
        this.setAccuracy(rep, cluster.getAccuracy());
        this.setMicroPrecision(rep, cluster.getMicroPrecision());
        this.setMacroPrecision(rep, cluster.getMacroPrecision());
        this.setMicroRecall(rep, cluster.getMicroRecall());
        this.setMacroRecall(rep,cluster.getMacroRecall());
        this.setEntropy(rep, Evaluation.entropy(data, cluster));
        this.setPurity(rep, Evaluation.purity(data, cluster));
        this.setBuildingTime(rep, time);
        
    }
    
    public void saveObject(){
        try{
            ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(output.getAbsolutePath() + ".tmp"));
            objOutput.writeObject(this);
            objOutput.close();
        }catch(Exception e){
            System.err.println("Error when saving the results");
        } 
    }

    public void setAccuracy(int lin, double value){
        accuracies[lin] = value;
    }    
    
    public void setMicroPrecision(int lin, double value){
        microPrecisions[lin]= value;
    }
    
    public void setMicroRecall(int lin, double value){
        microRecalls[lin] = value;
    }
    
    public void setMacroPrecision(int lin, double value){
        macroPrecisions[lin] = value;
    }
    
    public void setMacroRecall(int lin, double value){
        macroRecalls[lin] = value;
    }
    
    public void setBuildingTime(int lin, long value){
        buildingTimes[lin] = value;
    }
    
    public void setNumIterations(int lin, int value){
        numIterations[lin] = value;
    }
    
    public double getAccuracy(int lin){
        return accuracies[lin];
    }
    
    public double getMicroPrecision(int lin){
        return microPrecisions[lin];
    }
    
    public double getMicroRecall(int lin){
        return microRecalls[lin];
    }
    
    public double getMacroPrecision(int lin){
        return macroPrecisions[lin];
    }
    
    public double getMacroRecall(int lin){
        return macroRecalls[lin];
    }
    
    public long getBuildingTime(int lin){
        return buildingTimes[lin];
    }
    
    public int getNumIterations(int lin){
        return numIterations[lin];
    }
    
    public double getEntropy(int lin){
        return entropies[lin];
    }
    
    public void setEntropy(int lin, double value){
        entropies[lin]=value;
    }
    
    public double getPurity(int lin){
        return purities[lin];
    }
    
    public void setPurity(int lin, double value){
        purities[lin]=value;
    }
    
    /*public boolean getComplete(int lin){
        return complete[lin];
    }*/
    
    
}
