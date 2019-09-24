package TCTParameters.UnsupervisedLearning;

import java.io.Serializable;
import java.util.ArrayList;
import weka.core.Instances;

public class Parameters_NMF implements Serializable{   
    private ArrayList<Integer> ks = new ArrayList<>();
    private double minimumDiference;
    private double minimumError;
    private int maxIteration;
    private int incrementK;
    private boolean autoIncrementK;
    private boolean lda;
    
    public Parameters_NMF() {
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
    
    public boolean isLda() {
        return lda;
    }

    public double getMinimumError() {
        return minimumError;
    }

    public void setMinimumError(double minimumError) {
        this.minimumError = minimumError;
    }

    public void setLda(boolean lda) {
        this.lda = lda;
    }

    public double getMinimumDiference() {
        return minimumDiference;
    }

    public void setMinimumDiference(double minimumDiference) {
        this.minimumDiference = minimumDiference;
    }

    public int getMaxIteration() {
        return maxIteration;
    }

    public void setMaxIteration(int maxIteration) {
        this.maxIteration = maxIteration;
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
