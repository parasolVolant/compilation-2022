import sa.*;
import ts.Ts;
import ts.TsItemVar;

public class Sa2ts extends SaDepthFirstVisitor {

    enum Context {LOCAL, GLOBAL, PARAM}

    private Ts tableGlobale;
    private Ts tableLocaleCourante;
    private Context context;
    private int adrVarCourante;
    private int adrArgCourant;



    @Override
    public Object visit(SaDecTab node) {
        String identif = node.getNom();
        int size = node.getTaille();
        tableGlobale.addVar(identif,size);
        TsItemVar itemVar = new TsItemVar(identif, size);
        return itemVar;
    }

    private void addToLocalTable(SaLDec list){
        SaDecVar var = (SaDecVar) list.getTete();
        String identif = var.getNom();
        tableLocaleCourante.addVar(identif, 1);
        list = list.getQueue();
        while(list.getTete() != null) {
            addToLocalTable(list);
        }
    }

    @Override
    public Object visit(SaDecFonc node) {
        String adrArgCourantidentif = node.getNom();
        int nbArgs = node.getParametres().length();
        SaLDec list = node.getVariable();
        return super.visit(node);
    }

    @Override
    public Object visit(SaDecVar node) {
        return super.visit(node);
    }

    @Override
    public Object visit(SaVarSimple node) {
        return super.visit(node);
    }

    @Override
    public Object visit(SaAppel node) {
        return super.visit(node);
    }

    @Override
    public Object visit(SaVarIndicee node) {
        return super.visit(node);
    }
}
