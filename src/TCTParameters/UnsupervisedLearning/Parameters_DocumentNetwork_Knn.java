package TCTParameters.UnsupervisedLearning;

import java.io.Serializable;
import java.util.ArrayList;

public class Parameters_DocumentNetwork_Knn implements Serializable{
    private ArrayList<Integer> ks = new ArrayList<>();
    private boolean cosine;
    
    public Parameters_DocumentNetwork_Knn(){
        addNeighbor(3);
        cosine = true;
    }
    
    public boolean isCosseno(){
        return this.cosine;
    }
    
    public void setCosine(boolean cosine){
        this.cosine = cosine;
    }
    
    public void addK(Integer valueK){
        ks.add(valueK);
    }
    
    public Integer getK(int pos){
        return ks.get(pos);
    }
    
    public ArrayList<Integer> getKs(){
        return ks;
    }
    
    public void setK(ArrayList<Integer> ks){
        this.ks = ks;
    }
    
    public void addNeighbor(int k){
        ks.add(k);
    }
}
