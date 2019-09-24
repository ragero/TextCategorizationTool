package TCTConfigurations.UnsupervisedLearning;


public class Configuration_Dimensionality_Reduction_Analysis extends Configuration_Unsupervised_Base{
    
    private boolean simpleDRA;
    private boolean mixtureModelDRA;
    private String text;
    
    public Configuration_Dimensionality_Reduction_Analysis() {
        super();
    }

    public boolean isSimpleDRA() {
        return simpleDRA;
    }

    public void setSimpleDRA(boolean simpleDRA) {
        this.simpleDRA = simpleDRA;
    }

    public boolean isMixtureModelDRA() {
        return mixtureModelDRA;
    }

    public void setMixtureModelDRA(boolean mixtureModelDRA) {
        this.mixtureModelDRA = mixtureModelDRA;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    } 
    
    
}
