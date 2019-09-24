package TCTParameters.UnsupervisedLearning;

import java.io.Serializable;


public class Parameters_LabelPropagation implements Serializable{
    
    private boolean weighted;
    private boolean unitary;
    private boolean synchronous;
    private boolean asynchronous;
    private int maxIteration;

    public Parameters_LabelPropagation() {
        this.setSynchronous(true);
        this.setWeighted(true);
        this.setMaxIteration(100);
    }
    
    
    

    public int getMaxIteration() {
        return maxIteration;
    }

    public void setMaxIteration(int maxIteration) {
        this.maxIteration = maxIteration;
    }
    
    public boolean isWeighted() {
        return weighted;
    }

    public void setWeighted(boolean weighted) {
        this.weighted = weighted;
    }

    public boolean isUnitary() {
        return unitary;
    }

    public void setUnitary(boolean unitary) {
        this.unitary = unitary;
    }

    public boolean isSynchronous() {
        return synchronous;
    }

    public void setSynchronous(boolean synchronous) {
        this.synchronous = synchronous;
    }

    public boolean isAsynchronous() {
        return asynchronous;
    }

    public void setAsynchronous(boolean asynchronous) {
        this.asynchronous = asynchronous;
    }
    
    
    
}
