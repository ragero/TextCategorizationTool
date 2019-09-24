package TCTParameters.UnsupervisedLearning;

import java.io.Serializable;
import java.util.ArrayList;
import weka.core.Instances;


public class Parameters_MixtureModel implements Serializable{
    
    private ArrayList<Integer> ks = new ArrayList<>();
    private boolean converged;
    private int maxIteration;
    private double tolerance;
    private int incrementK;
    private boolean autoIncrementK;

    public Parameters_MixtureModel() {
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
        setIncrementK(2);
        setAutoIncrementK(true);
        this.setConverged(false);
        this.setTolerance(1e-10);
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

    public boolean isAutoIncrementK() {
        return autoIncrementK;
    }

    public void setAutoIncrementK(boolean autoIncrementK) {
        this.autoIncrementK = autoIncrementK;
    }
    
    public ArrayList<Integer> getKs() {
        return ks;
    }

    public void setKs(ArrayList<Integer> ks) {
        this.ks = ks;
    }

    public boolean isConverged() {
        return converged;
    }

    public void setConverged(boolean converged) {
        this.converged = converged;
    }

    public int getMaxIteration() {
        return maxIteration;
    }

    public void setMaxIteration(int maxIteration) {
        this.maxIteration = maxIteration;
    }

    public double getTolerance() {
        return tolerance;
    }

    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }
    
    public void addClusters(int k){
        ks.add(k);
    }
    
}
