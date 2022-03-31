package nasm;

public class NasmResd extends NasmPseudoInst {
    
    public NasmResd(NasmLabel label, int nb, String comment){
	this.label = label;
	this.nb = nb;
	this.sizeInBytes = 4;
	this.comment = comment;
    }

    public <T> T accept(NasmVisitor <T> visitor) {
        return visitor.visit(this);
    }
    
    public String toString(){
	return super.formatInst(this.label, "resd", this.nb, this.comment);
    }


}
