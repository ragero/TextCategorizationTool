//*****************************************************************************
// Author: Rafael Geraldeli Rossi
// E-mail: rgr.rossi at gmail com
// Last-Modified: January 29, 2015
// Description: 
//*****************************************************************************  

package TCTAlgorithms.Unsupervised;

public class IndexValue implements Comparable{
    public int index;
    public double value;
    
    public IndexValue(){
        index = 0;
        value = 0;
    }
    
    public IndexValue(int index, double value){
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public int compareTo(Object o) {
        IndexValue ind = (IndexValue)o;
        if(this.value > ind.value){
            return 1;
        }
        if(this.value < ind.value){
            return -1;
        }
        return 0;
    }
    
    
}
