package ts;

import sa.*;

public class Sa2ts extends SaDepthFirstVisitor<Void> {

    public Sa2ts(SaProg prog) {
        super();

    }

    enum Context {LOCAL, GLOBAL, PARAM}

    private Ts tableGlobale;
    private Ts tableLocaleCourante;
    private Context context;
    private int adrVarCourante;
    private int adrArgCourant;



    @Override
    public Void visit(SaDecTab node) {

        defaultIn(node);
        String identif = node.getNom();
        int taille = node.getTaille();
        node.tsItem = tableGlobale.variables.get(identif);
        if (node.tsItem == null) {
            context = Context.GLOBAL;
            node.tsItem = tableGlobale.addVar(identif,taille);

        }
        else  new Exception();
        defaultOut(node);
        return null;



    }

    @Override
    public Void visit(SaDecFonc node) {
        defaultIn(node);

        String identif = node.getNom();
        int nbArgs;
        node.tsItem = tableGlobale.fonctions.get(identif);

        if(node.tsItem == null){

           if (node.getParametres() == null) nbArgs = 0;

           else nbArgs = node.getParametres().length();

           tableLocaleCourante = new Ts();

           node.tsItem = tableGlobale.addFct(identif,nbArgs,tableLocaleCourante,node);

            context = Context.PARAM;

            if(node.getParametres() != null) this.visit(node.getParametres());

            context = Context.LOCAL;

            if(node.getVariable() != null) this.visit(node.getVariable());

            if(node.getCorps() != null) node.getCorps().accept(this);

            context = Context.GLOBAL;
    }
        else  new Exception();

        defaultOut(node);

        return null;
    }

    @Override
    public Void visit(SaDecVar node) {
        defaultIn(node);

        String identif = node.getNom();
        int taille = 1;
        node.tsItem = tableGlobale.variables.get(identif);
        if (node.tsItem == null) {

        tableGlobale.addVar(identif,taille);
        context = Context.GLOBAL;


        }


        else  new Exception();

        defaultOut(node);

        return null;
    }

    @Override
    public Void visit(SaVarSimple node) {
        defaultIn(node);
        String identif = node.getNom();
        int taille = 1;

        context = Context.LOCAL;
        node.tsItem = tableLocaleCourante.getVar(identif);
        if (node.tsItem != null)  new Exception();

        context = Context.PARAM;
        node.tsItem = tableLocaleCourante.getVar(identif);
        if (node.tsItem.isParam)  try {
            throw new Exception();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        context = Context.GLOBAL;
        node.tsItem = tableGlobale.getVar(identif);
        if (node.tsItem == null)  try {
            throw new Exception();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaAppel node) {
        defaultIn(node);
        String identif = node.getNom();
        int nbArgs;

        node.tsItem = tableGlobale.fonctions.get(identif);

        if(node.tsItem != null) {

            if (node.getArguments() == null) nbArgs = 0;

            else nbArgs = node.getArguments().length();


            context = Context.PARAM;
            if (node.tsItem.getNbArgs() != nbArgs) new Exception();

            if (node.getArguments() != null) this.visit(node.getArguments());

            if (tableGlobale.fonctions.get("main") == null || tableGlobale.fonctions.get("main").getNbArgs() != 0)
                new Exception();

        }

        else new Exception();

        defaultOut(node);
        return null;

    }

    @Override
    public Void visit(SaVarIndicee node) {

        defaultIn(node);

        String identif = node.getNom();
        int indice = Integer.parseInt(node.getIndice().toString());

        node.tsItem = tableLocaleCourante.variables.get(identif);

        if (node.tsItem != null){
        context = Context.GLOBAL;
        this.visit(node.getIndice());
        }
        defaultOut(node);



        return null;
    }

    public Ts getTableGlobale() {
        return tableGlobale;
    }
}
