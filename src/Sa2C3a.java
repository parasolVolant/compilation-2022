import c3a.*;
import sa.*;

public class Sa2C3a<t> extends SaDepthFirstVisitor<C3aOperand> {



    private C3a c3a;
    public Sa2C3a(SaNode root){
        c3a = new C3a();
        root.accept(this);
    }



    public C3aOperand visit(SaProg node)
    {
        defaultIn(node);
        if(node.getVariables() != null)
            node.getVariables().accept(this);
        if(node.getFonctions() != null)
            node.getFonctions().accept(this);
        defaultOut(node);
        return null;
    }


    public C3aOperand visit(SaDecTab node){
        defaultIn(node);
        defaultOut(node);
        return new C3aVar(node.tsItem, new C3aConstant(node.getTaille()));


    }

    public C3aOperand visit(SaExp node)
    {
        defaultIn(node);
        defaultOut(node);
        return null;
    }

    public C3aOperand visit(SaExpInt node)
    {
        defaultIn(node);
        defaultOut(node);
        return null;
    }
    public C3aOperand visit(SaExpVar node)
    {
        defaultIn(node);
        node.getVar().accept(this);
        defaultOut(node);
        return null;
    }

    public C3aOperand visit(SaInstEcriture node)
    {
        defaultIn(node);
        node.getArg().accept(this);
        defaultOut(node);
        return null;
    }

    public C3aOperand visit(SaInstTantQue node)
    {
        defaultIn(node);
        node.getTest().accept(this);
        if (node.getFaire() != null)
            node.getFaire().accept(this);
        defaultOut(node);
        return null;
    }
    public C3aOperand visit(SaLInst node)
    {
        defaultIn(node);
        if(node != null){
            if(node.getTete() != null)node.getTete().accept(this);
            if(node.getQueue() != null) node.getQueue().accept(this);
        }
        defaultOut(node);
        return null;
    }


    public C3aOperand visit(SaDecFonc node)
    {defaultIn(node);
        c3a.ajouteInst(new C3aInstFBegin(node.tsItem,""));
        if(node.getParametres() != null) node.getParametres().accept(this);
        node.getParametres().accept(this);
        if(node.getVariable() != null) node.getVariable().accept(this);
        node.getVariable().accept(this);
        if(node.getCorps() != null) node.getCorps().accept(this);
        node.getCorps().accept(this);
        c3a.ajouteInst(new C3aInstFEnd(""));
        defaultOut(node);
        return null;
    }


    public C3aOperand visit(SaDecVar node)
    {
        defaultIn(node);
        defaultOut(node);
        return new C3aVar(node.tsItem,null);
    }

    public C3aOperand visit(SaInstAffect node)
    {
        defaultIn(node);
        C3aOperand left = node.getLhs().accept(this);
        C3aOperand right = node.getRhs().accept(this);
        c3a.ajouteInst(new C3aInstAffect(right, left, ""));
        defaultOut(node);
        return null;
    }

    public C3aOperand visit(SaLDec node)
    {
        defaultIn(node);
        if(node.getTete() != null) node.getTete().accept(this);
        if(node.getQueue() != null) node.getQueue().accept(this);
        defaultOut(node);
        return null;
    }

    public C3aOperand visit(SaVarSimple node)
    {
        defaultIn(node);
        defaultOut(node);
        return new C3aVar(node.tsItem, null);

    }

    public C3aOperand visit(SaAppel node)
    {
        C3aTemp tem = c3a.newTemp();

        defaultIn(node);
        if(node.getArguments() != null) node.getArguments().accept(this);
        defaultOut(node);
        return null;
    }

    public C3aOperand visit(SaExpAppel node)
    {
        defaultIn(node);
        node.getVal().accept(this);
        defaultOut(node);
        return null;
    }


    public C3aOperand visit(SaExpAdd node)
    {
        defaultIn(node);
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aOperand result = c3a.newTemp();
        c3a.ajouteInst(new C3aInstAdd(op1, op2, result, ""));
        defaultOut(node);
        return result;
    }


    public C3aOperand visit(SaExpSub node)
    {
        defaultIn(node);
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aOperand result = c3a.newTemp();
        c3a.ajouteInst(new C3aInstSub(op1, op2, result, ""));
        defaultOut(node);
        return null;
    }

    public C3aOperand visit(SaExpMult node)
    {
        defaultIn(node);
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aOperand result = c3a.newTemp();
        c3a.ajouteInst(new C3aInstMult(op1, op2, result, ""));
        defaultOut(node);
        return null;
    }


    public C3aOperand visit(SaExpDiv node)
    {
        defaultIn(node);
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aOperand result = c3a.newTemp();
        c3a.ajouteInst(new C3aInstDiv(op1, op2, result, ""));
        defaultOut(node);
        return null;
    }


    public C3aOperand visit(SaExpInf node)
    {
        defaultIn(node);
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aOperand temp = c3a.newTemp();
        C3aLabel vrai = c3a.newAutoLabel();
        c3a.ajouteInst(new C3aInstAffect(c3a.True, temp, ""));
        c3a.ajouteInst(new C3aInstJumpIfLess(op1, op2, vrai, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.False, temp, ""));
        c3a.addLabelToNextInst(vrai);
        defaultOut(node);
        return temp;

    }


    public C3aOperand visit(SaExpEqual node)
    {
        defaultIn(node);
        node.getOp1().accept(this);
        node.getOp2().accept(this);
        defaultOut(node);
        return null;
    }


    public C3aOperand visit(SaExpAnd node)
    {
        defaultIn(node);
        node.getOp1().accept(this);
        node.getOp2().accept(this);
        defaultOut(node);
        return null;
    }



    public C3aOperand visit(SaExpOr node)
    {
        defaultIn(node);
        node.getOp1().accept(this);
        node.getOp2().accept(this);
        defaultOut(node);
        return null;
    }

    public  C3aOperand visit(SaExpNot node)
    {
        defaultIn(node);
        node.getOp1().accept(this);
        defaultOut(node);
        return null;
    }


    public C3aOperand visit(SaExpLire node)
    {
        defaultIn(node);
        defaultOut(node);
        return null;
    }

    public C3aOperand visit(SaInstBloc node)
    {
        defaultIn(node);
        node.getVal().accept(this);
        defaultOut(node);
        return null;
    }

    public C3aOperand visit(SaInstSi node)
    {
        defaultIn(node);
        node.getTest().accept(this);
        if (node.getAlors() != null)
            node.getAlors().accept(this);
        if(node.getSinon() != null) node.getSinon().accept(this);
        defaultOut(node);
        return null;
    }


    public C3aOperand visit(SaInstRetour node)
    {
        defaultIn(node);
        node.getVal().accept(this);
        defaultOut(node);
        return null;
    }


    public C3aOperand visit(SaLExp node)
    {
        defaultIn(node);
        node.getTete().accept(this);
        if(node.getQueue() != null)
            node.getQueue().accept(this);
        defaultOut(node);
        return null;
    }
    public C3aOperand visit(SaVarIndicee node)
    {
        defaultIn(node);
        node.getIndice().accept(this);
        defaultOut(node);
        return null;
    }

}
