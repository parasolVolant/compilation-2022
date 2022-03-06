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
        return tableGlobale.addVar(identif,size);
    }

    @Override
    public Object visit(SaDecFonc node) {
        Ts tableLocale = new Ts();
        String identif = node.getNom();
        int nbArgs = node.getParametres().length();
        SaLDec list = node.getVariable();
        for (int i = 0; i < list.length() ; i++) {
            tableLocale.addParam(list.getTete().getNom());
        }
        tableLocaleCourante = tableLocale;
        return tableGlobale.addFct(identif,nbArgs,tableLocale,node);
    }

    @Override
    public Object visit(SaDecVar node) {
        String identif = node.getNom();
        int size = 1;
        return tableGlobale.addVar(identif,size);
    }

    @Override
    public Object visit(SaVarSimple node) {
        String identif = node.getNom();
        int size = 1;
        if (tableLocaleCourante.getVar(identif) != null) return tableLocaleCourante.getVar(identif);
        else if (tableLocaleCourante.getVar(identif).isParam) return tableLocaleCourante.getVar(identif);
        else if (tableGlobale.getVar(identif) == null) return tableGlobale.getVar(identif);
        return null;
    }

    @Override
    public Object visit(SaAppel node) {
        String identif = node.getNom();
        SaLExp list = node.getArguments();
        int nbArgs = list.length();
        if (tableGlobale.getFct(identif) == null) return null;
        if (tableGlobale.getFct(identif).getNbArgs() != nbArgs) return null;
        if (tableGlobale.getFct("main") == null || tableGlobale.getFct("main").getNbArgs() != 0) return null;
        return tableGlobale.
    }

    @Override
    public Object visit(SaVarIndicee node) {
        String identif = node.getNom();
        int indice = Integer.parseInt(node.getIndice().toString());

    }
}
