package TCTAlgorithms.Unsupervised;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import weka.core.Instances;


public class Prototype_Based extends Clusterer{
    
    private ArrayList<HashMap<Integer,Double>> centroides;
    private ArrayList<Double> cohesions;
    private double minimumDif;
    private int numTerms;
    private int k;    
    
    public Prototype_Based(int numInstances, int numkClusters) {
        super(numInstances);
        cohesions=new ArrayList<>();
        k=numkClusters;
    }

    //get-set
    public int getNumTerms() {
        return numTerms;
    }

    public void setNumTerms(int numTerms) {
        this.numTerms = numTerms;
    }
    
    
    public ArrayList<HashMap<Integer,Double>> getCentroides() {
        return centroides;
    }

    public void setCentroides(ArrayList<HashMap<Integer,Double>> centroides) {
        this.centroides = centroides;
    }
    
    public ArrayList<Double> getCohesions() {
        return cohesions;
    }

    public void setCohesions(ArrayList<Double> cohesions) {
        this.cohesions = cohesions;
    }

    public double getMinimumDif() {
        return minimumDif;
    }

    public void setMinimumDif(double minimumDif) {
        this.minimumDif = minimumDif;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    @Override
    public boolean buildCluster(Instances data, int rep) {
        return true;
    }
      
    
}
