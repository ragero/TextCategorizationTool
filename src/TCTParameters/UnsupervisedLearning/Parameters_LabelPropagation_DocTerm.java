package TCTParameters.UnsupervisedLearning;

import java.io.Serializable;

public class Parameters_LabelPropagation_DocTerm implements Serializable{
    
    private boolean weighted;
    private boolean unitary;
    private int maxIteration;

    public Parameters_LabelPropagation_DocTerm() {
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

}
