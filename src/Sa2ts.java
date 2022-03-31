import sa.*;
import ts.Ts;
import ts.TsItemVar;

public class Sa2ts extends SaDepthFirstVisitor<Void> {

    enum Context {LOCAL, GLOBAL, PARAM}


    private Ts tableGlobale;
    private Ts tableLocaleCourante;
    private Context context;


    public Sa2ts(SaNode root) {
        tableGlobale = new Ts();
        context= Context.GLOBAL;
        root.accept(this);
    }

    public Ts getTableGlobale() {

        return tableGlobale;
    }


    @Override
    public Void visit(SaDecTab node) {
       defaultIn(node);

        String identif = node.getNom();
        int taille = node.getTaille();
        Ts tableContext;

        if (tableLocaleCourante != null) {
            tableContext = tableLocaleCourante;
        }
        else  {
            tableContext = tableGlobale;
        }

        node.tsItem = tableContext.variables.get(identif);
        if (node.tsItem == null || node.tsItem.getPortee() != tableContext) {
            if (context == Context.GLOBAL)
                node.tsItem = tableContext.addVar(identif,taille*4);
            else
                new Exception("test");
        } else {
            new Exception("test");
        }

       defaultOut(node);

        return null;




    }

    @Override
    public Void visit(SaDecFonc node) {
        defaultIn(node);


        tableLocaleCourante = new Ts();
        String identif = node.getNom();
        node.tsItem = tableGlobale.fonctions.get(identif);

        if(node.tsItem == null){

            int nbArgs = (node.getParametres() == null)? 0 : node.getParametres().length();

            context = Context.PARAM;

            if(node.getParametres() != null) this.visit(node.getParametres());

            context = Context.LOCAL;

            if(node.getVariable() != null) this.visit(node.getVariable());


            if(node.getCorps() != null) node.getCorps().accept(this);

            context = Context.GLOBAL;

            node.tsItem = tableGlobale.addFct(identif,nbArgs,tableLocaleCourante,node);


            node.tsItem = tableGlobale.fonctions.get(identif);
        }
        else  new Exception("test");
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaDecVar node) {
        defaultIn(node);

        String identif = node.getNom();
        int taille = 4;
        Ts tableContext;


        if (tableLocaleCourante!=null){

            tableContext = tableLocaleCourante;
        }

        else {
            tableContext = tableGlobale; }

        node.tsItem = tableContext.variables.get(identif);
        if (node.tsItem  == null || node.tsItem.getPortee() != tableContext) {

            if (context == Context.PARAM)
                node.tsItem = tableContext.addParam(identif);
            else
                node.tsItem = tableContext.addVar(identif,taille);
        }



        else {
            new Exception("test");

        }

        defaultOut(node);

        return null;
    }



    @Override
    public Void visit(SaVarSimple node) {
        defaultIn(node);

        String identif = node.getNom();
        TsItemVar variable = null;


        if(context != Context.GLOBAL){
            variable = tableLocaleCourante.variables.get(identif);
        }
        if (variable == null){
            variable = tableGlobale.variables.get(identif);
        }
        node.tsItem = variable;
        defaultOut(node);
        return null;
    }


    @Override
    public Void visit(SaAppel node) {
        defaultIn(node);

        String identif = node.getNom();

        int nbArgs = (node.getArguments() == null)? 0 : node.getArguments().length();

        node.tsItem = tableGlobale.getFct(identif);

        if (node.tsItem == null) {
            new Exception("test");
        }

        if (nbArgs == 0 && node.getArguments() != null || nbArgs > 0 && node.getArguments() != null && nbArgs != node.getArguments().length()) {
            this.visit(node.getArguments());
        }
        if (tableGlobale.fonctions.get("main") == null || tableGlobale.fonctions.get("main").getNbArgs() != 0)
            new Exception("test");




        defaultOut(node);
        return null;

    }


    @Override
    public Void visit(SaVarIndicee node) {

        defaultIn(node);

        String identif = node.getNom();

        node.tsItem = tableGlobale.variables.get(identif);
        if ( node.tsItem != null) {
            node.tsItem = tableGlobale.variables.get(identif);
        }
        else {
            node.tsItem = tableLocaleCourante.variables.get(identif);
        }
        node.getIndice().accept(this);


        defaultOut(node);
        return null;
    }







}
