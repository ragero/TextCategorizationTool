package TCTParameters.UnsupervisedLearning;

import java.io.Serializable;
import java.util.ArrayList;
import weka.core.Instances;


public class Parameters_K_Means implements Serializable{
    private ArrayList<Integer>ks = new ArrayList<>();
    private double minimumCohesion;
    private int maxIteration;
    private int incrementK;
    private boolean autoIncrementK;
    
    
    public Parameters_K_Means() {
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
        setMaxIteration(100);
        setMinimumCohesion(0.05);
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

    
    public ArrayList<Integer> getKs() {
        return ks;
    }

    public void setKs(ArrayList<Integer> ks) {
        this.ks = ks;
    }
    
    public int getMaxIteration() {
        return maxIteration;
    }

    public void setMaxIteration(int maxIteration) {
        this.maxIteration = maxIteration;
    }
    
    public double getMinimumCohesion() {
        return minimumCohesion;
    }

    public void setMinimumCohesion(double minimumCohesion) {
        this.minimumCohesion = minimumCohesion;
    } 
    
    public int getIncrementK() {
        return incrementK;
    }

    public void setIncrementK(int incrementK) {
        this.incrementK = incrementK;
    }
    
    public void addClusters(int k){
        ks.add(k);
    }

    public boolean isAutoIncrementK() {
        return autoIncrementK;
    }

    public void setAutoIncrementK(boolean autoIncrementK) {
        this.autoIncrementK = autoIncrementK;
    }
    
    
}
