package ts;

import sa.*;

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
        node.tsItem = tableGlobale.variables.get(identif);
        if (node.tsItem == null) {
            context = Context.GLOBAL;
            node.tsItem = tableGlobale.addVar(identif,taille);
            node.tsItem = tableGlobale.variables.get(identif);

        }
        else  new Exception("test");
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
        int taille = 1;

        node.tsItem = tableGlobale.variables.get(identif);
        if (node.tsItem == null) {

            context = Context.GLOBAL;
            tableGlobale.addVar(identif,taille);
            node.tsItem = tableGlobale.variables.get(identif);
        }
        else  new Exception("test");
        defaultOut(node);

        return null;
    }

    @Override
    public Void visit(SaVarSimple node) {
        defaultIn(node);
        String identif = node.getNom();
        int taille = 1;

        if(context == Context.LOCAL){
            node.tsItem = tableLocaleCourante.variables.get(identif);
            if (node.tsItem == null)  new Exception("test");}

        if(context == Context.PARAM){
            node.tsItem = tableLocaleCourante.variables.get(identif);
            if (!node.tsItem.isParam) new Exception("test");}


        if(context == Context.GLOBAL){
            node.tsItem = tableGlobale.variables.get(identif);
            if (node.tsItem == null)  new Exception("test");}

        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaAppel node) {
        defaultIn(node);

        String identif = node.getNom();

        node.tsItem = tableGlobale.fonctions.get(identif);

        if(node.tsItem != null)
        {

            int nbArgs = (node.getArguments() == null)? 0 : node.getArguments().length();

            if(context == Context.PARAM){

                if (node.tsItem.getNbArgs()!= nbArgs) new Exception("test");

                if (node.getArguments() != null) this.visit(node.getArguments());

                if (tableGlobale.fonctions.get("main") == null || tableGlobale.fonctions.get("main").getNbArgs() != 0)
                    new Exception("test");

            }
            node.tsItem = tableGlobale.fonctions.get(identif);

        }
        else new Exception("test");
        defaultOut(node);
        return null;

    }



    @Override
    public Void visit(SaVarIndicee node) {

        defaultIn(node);

        String identif = node.getNom();
        int indice = Integer.parseInt(node.getIndice().toString());

        node.tsItem = tableLocaleCourante.variables.get(identif);

        if(context == Context.GLOBAL){
            if (node.tsItem != null){

                this.visit(node.getIndice());
            }
            node.tsItem = tableLocaleCourante.variables.get(identif);
        }
        else new Exception("test");
        defaultOut(node);
        return null;
    }




}
