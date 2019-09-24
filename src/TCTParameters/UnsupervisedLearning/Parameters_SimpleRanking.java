package TCTParameters.UnsupervisedLearning;

import java.io.Serializable;
import java.util.ArrayList;
import weka.core.Instances;


public class Parameters_SimpleRanking implements Serializable{
    
    private ArrayList<Integer> ks = new ArrayList<>();
    private ArrayList<Double> alfa = new ArrayList<>();
    private double minimumDifference;
    private int maxIteration;
    private int incrementK;
    private boolean autoIncrementK;
    
    public Parameters_SimpleRanking() {
        addClusters(2);
        addClusters(5);
        addClusters(7);
        addClusters(11);
        addClusters(13);
        addClusters(17);
        addClusters(19);
        addClusters(23);
        addClusters(29);
        addClusters(31);
        addClusters(37);
        addAlfa(0.1);
        addAlfa(0.2);
        addAlfa(0.3);
        addAlfa(0.4);
        addAlfa(0.5);
        addAlfa(0.6);
        addAlfa(0.7);
        addAlfa(0.8);
        addAlfa(0.9);
        addAlfa(1.0);
        setMaxIteration(100);
        setMinimumDifference(0.01);
        setIncrementK(2);
        setAutoIncrementK(true);
    }
 
    public synchronized ArrayList<Integer> automaticallyGenerate(Instances data){
        int clusters=0;
        ArrayList<Integer> newks = new ArrayList<>();
        int numclasses=0;
        clusters=(int) Math.sqrt(data.numInstances());
        data.setClassIndex(data.numAttributes()-1);
        numclasses =data.numClasses();
        for(int group=numclasses;group<clusters;group+=incrementK){
            newks.add(group);
        }
        return newks;
    }
    
    
    public int getIncrementK() {
        return incrementK;
    }

    public void setIncrementK(int incrementK) {
        this.incrementK = incrementK;
    }
    
    public ArrayList<Integer> getKs() {
        return ks;
    }

    public void setKs(ArrayList<Integer> ks) {
        this.ks = ks;
    }

    public double getMinimumDifference() {
        return minimumDifference;
    }

    public void setMinimumDifference(double minimumDifference) {
        this.minimumDifference = minimumDifference;
    }

    public int getMaxIteration() {
        return maxIteration;
    }

    public void setMaxIteration(int maxIteration) {
        this.maxIteration = maxIteration;
    }

    public boolean isAutoIncrementK() {
        return autoIncrementK;
    }

    public void setAutoIncrementK(boolean autoIncrementK) {
        this.autoIncrementK = autoIncrementK;
    }

    public ArrayList<Double> getAlfa() {
        return alfa;
    }

    public void setAlfa(ArrayList<Double> alfa) {
        this.alfa = alfa;
    }

    public void addClusters(int k){
        ks.add(k);
    }
    
    public void addAlfa(double k){
        alfa.add(k);
    }
    
    
}
