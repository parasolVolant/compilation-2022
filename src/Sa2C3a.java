import c3a.*;
import ts.*;
import sa.*;


public class Sa2C3a extends SaDepthFirstVisitor <C3aOperand> {
    private C3a c3a;
    int indentation;
    public C3a getC3a(){return this.c3a;}
    Ts tableGlobale;

    public Sa2C3a(SaNode root, Ts tableGlobale){
        c3a = new C3a();
        C3aTemp result = c3a.newTemp();
        C3aFunction fct = new C3aFunction(tableGlobale.getFct("main"));
        c3a.ajouteInst(new C3aInstCall(fct, result, ""));
        c3a.ajouteInst(new C3aInstStop(result, ""));
        indentation = 0;
        root.accept(this);
    }

    public void defaultIn(SaNode node)
    {
        for(int i = 0; i < indentation; i++){System.out.print(" ");}
        indentation++;
        System.out.println("<" + node.getClass().getSimpleName() + ">");
    }

    public void defaultOut(SaNode node)
    {
        indentation--;
        for(int i = 0; i < indentation; i++){System.out.print(" ");}
        System.out.println("</" + node.getClass().getSimpleName() + ">");
    }



    public C3aOperand visit(SaProg node) {
        node.getFonctions().accept(this);
        return null;
    }



    @Override
    public C3aOperand visit(SaExp node) {
        node.accept(this);
        return null;
    }


   @Override
    public C3aOperand visit(SaExpAppel node) {
        defaultIn(node);
        C3aOperand call = node.getVal().accept(this);
        defaultOut(node);
        return call;

    }


    @Override
    public C3aOperand visit(SaExpNot node) {
        C3aLabel notLabel = c3a.newAutoLabel();
        C3aTemp result = c3a.newTemp();
        C3aOperand op1 = node.getOp1().accept(this);
        c3a.ajouteInst(new C3aInstAffect(c3a.True, result, ""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(op1, c3a.False, notLabel, null));
        c3a.ajouteInst(new C3aInstAffect(c3a.False, result, ""));
        c3a.addLabelToNextInst(notLabel);
        return result;
    }

    @Override
    public C3aOperand visit(SaLExp node) {
        if (node.getTete() != null)
            return node.getTete().accept(this);
        if (node.getQueue() != null)
            return node.getQueue().accept(this);
        return null;
    }

    @Override
    public C3aOperand visit(SaLInst node) {
        if (node.getTete() != null)
            node.getTete().accept(this);
        if (node.getQueue() != null)
            node.getQueue().accept(this);
        return null;
    }

    @Override
    public C3aOperand visit(SaLDec node) {
        if (node.getTete() != null)
            node.getTete().accept(this);
        if (node.getQueue() != null)
            node.getQueue().accept(this);
        return null;
    }


   @Override
    public C3aOperand visit(SaAppel node) {

        defaultIn(node);
        node.getArguments().accept(this);
        C3aFunction function = new C3aFunction(this.tableGlobale.getFct(node.getNom()));
        C3aOperand result = c3a.newTemp();
        C3aInstCall  call = new C3aInstCall(function,result,"");
        c3a.ajouteInst(call);
       defaultOut(node);
       return result;
    }




    public C3aOperand visit(SaExpAdd node) {
        defaultIn(node);
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aOperand result = c3a.newTemp();
        c3a.ajouteInst(new C3aInstAdd(op1, op2, result, ""));
        defaultOut(node);
        return result;
    }


    public C3aOperand visit(SaExpSub node) {
        defaultIn(node);
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aOperand result = c3a.newTemp();
        c3a.ajouteInst(new C3aInstSub(op1, op2, result, ""));
        defaultOut(node);

        return result;
    }

    public C3aOperand visit(SaExpMult node) {
        defaultIn(node);
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aOperand result = c3a.newTemp();
        c3a.ajouteInst(new C3aInstMult(op1, op2, result, ""));
        defaultOut(node);

        return result;
    }

    public C3aOperand visit(SaExpDiv node) {
        defaultIn(node);
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aOperand result = c3a.newTemp();
        c3a.ajouteInst(new C3aInstDiv(op1, op2, result, ""));
        defaultOut(node);

        return result;
    }

    public C3aOperand visit(SaInstAffect node) {
        defaultIn(node);
        C3aOperand affectLabel1 = node.getLhs().accept(this);
        C3aOperand affectLabel2 = node.getRhs().accept(this);
        c3a.ajouteInst(new C3aInstAffect(affectLabel2, affectLabel1, ""));
        defaultOut(node);
        return affectLabel1;
    }

    public C3aOperand visit(SaExpInt node) {
        defaultIn(node);
        C3aOperand value = new C3aConstant(node.getVal());
        defaultOut(node);
        return value;
    }

    public C3aOperand visit(SaExpVar node) {
        defaultIn(node);
        defaultOut(node);
        return node.getVar().accept(this);
    }

    public C3aOperand visit(SaExpEqual node) {
        defaultIn(node);
        C3aLabel equalLabel = c3a.newAutoLabel();
        C3aTemp result = c3a.newTemp();
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        c3a.ajouteInst(new C3aInstAffect(c3a.True, result, ""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(op1, op2, equalLabel, null));
        c3a.ajouteInst(new C3aInstAffect(c3a.False, result, ""));
        c3a.addLabelToNextInst(equalLabel);
        defaultOut(node);
        return result;

    }



    public C3aOperand visit(SaExpInf node) {
        defaultIn(node);
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aOperand result = c3a.newTemp();
        C3aLabel infLabel = c3a.newAutoLabel();
        c3a.ajouteInst(new C3aInstAffect(c3a.True, result, ""));
        c3a.ajouteInst(new C3aInstJumpIfLess(op1, op2, infLabel, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.False, result, ""));
        c3a.addLabelToNextInst(infLabel);
        defaultOut(node);
        return result;
    }

    public C3aOperand visit(SaExpAnd node) {
        defaultIn(node);
        C3aLabel andLabel1 = c3a.newAutoLabel();
        C3aLabel andLabel2 = c3a.newAutoLabel();
        C3aTemp result = c3a.newTemp();
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        c3a.ajouteInst(new C3aInstJumpIfEqual(op1, c3a.False, andLabel2, null));
        c3a.ajouteInst(new C3aInstJumpIfEqual(op2, c3a.False, andLabel2, null));
        c3a.ajouteInst(new C3aInstAffect(c3a.True, result, ""));
        c3a.ajouteInst(new C3aInstJump(andLabel1, null));
        c3a.addLabelToNextInst(andLabel2);
        c3a.ajouteInst(new C3aInstAffect(c3a.False, result, ""));
        c3a.addLabelToNextInst(andLabel1);
        defaultOut(node);
        return result;


    }

    public C3aOperand visit(SaExpOr node) {
        defaultIn(node);
        C3aLabel orLabel1 = c3a.newAutoLabel();
        C3aLabel orLabel2 = c3a.newAutoLabel();
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aTemp result = c3a.newTemp();
        c3a.ajouteInst(new C3aInstJumpIfNotEqual(op1, c3a.False, orLabel2, ""));
        c3a.ajouteInst(new C3aInstJumpIfNotEqual(op2, c3a.False, orLabel2, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.False, result, ""));
        c3a.ajouteInst(new C3aInstJump(orLabel1, ""));
        c3a.addLabelToNextInst(orLabel2);
        c3a.ajouteInst(new C3aInstAffect(c3a.True, result, ""));
        c3a.addLabelToNextInst(orLabel1);
        defaultOut(node);
        return result;
    }


    public C3aOperand visit(SaInstSi node) {
        defaultIn(node);

        C3aLabel ifLabel = c3a.newAutoLabel();
        C3aLabel ifLabel2 = c3a.newAutoLabel();
        C3aOperand operand = node.getTest().accept(this);
        if (node.getSinon() != null) {
            c3a.ajouteInst(new C3aInstJumpIfEqual(operand, c3a.False, ifLabel, ""));

            node.getAlors().accept(this);
            c3a.ajouteInst(new c3a.C3aInstJump(ifLabel2, ""));

            c3a.addLabelToNextInst(ifLabel);
            node.getSinon().accept(this);

            c3a.addLabelToNextInst(ifLabel2);
        } else {
            c3a.ajouteInst(new C3aInstJumpIfEqual(operand, c3a.False, ifLabel2, ""));

            node.getAlors().accept(this);

            c3a.addLabelToNextInst(ifLabel2);
        }
        defaultOut(node);
        return null;
    }

    @Override
    public C3aOperand visit(SaInstTantQue node) {
        C3aLabel whileLabel1 = c3a.newAutoLabel();
        C3aLabel whileLabel2 = c3a.newAutoLabel();
        c3a.addLabelToNextInst(whileLabel1);
        C3aOperand operand = node.getTest().accept(this);
        c3a.ajouteInst(new C3aInstJumpIfEqual(operand, c3a.False, whileLabel2, ""));
        node.getFaire().accept(this);
        c3a.ajouteInst(new C3aInstJump(whileLabel1, ""));
        c3a.addLabelToNextInst(whileLabel2);
        return null;
    }



    public C3aOperand visit(SaInstBloc node) {
        defaultIn(node);
        node.getVal().accept(this);
        defaultOut(node);
        return null;
    }

    public C3aOperand visit(SaInstEcriture node) {
        defaultIn(node);
        C3aOperand write = node.getArg().accept(this);
        c3a.ajouteInst(new C3aInstWrite(write, ""));
        defaultOut(node);
        return null;
    }


    public C3aOperand visit(SaExpLire node) {
        defaultIn(node);
        C3aTemp read = c3a.newTemp();
        c3a.ajouteInst(new C3aInstRead(read, ""));
        defaultOut(node);
        return read;
    }

    public C3aOperand visit(SaInstRetour node) {
        defaultIn(node);
        C3aOperand ret = node.getVal().accept(this);
        c3a.ajouteInst(new C3aInstReturn(ret, ""));
        defaultOut(node);
        return ret;
    }

    public C3aOperand visit(SaDecVar node) {
        defaultIn(node);
        TsItemVar variable = this.tableGlobale.getVar(node.getNom());
        defaultOut(node);
        return new C3aVar(variable, null);
    }

    public C3aOperand visit(SaDecTab node) {
        defaultIn(node);
        TsItemVar variable = this.tableGlobale.getVar(node.getNom());
        C3aConstant length = new C3aConstant(node.getTaille());
        defaultOut(node);
        return new C3aVar(variable, length);
    }

    public C3aOperand visit(SaDecFonc node) {
        defaultIn(node);
        c3a.ajouteInst(new C3aInstFBegin(node.tsItem, "entree fonction"));
        node.getCorps().accept(this);
        c3a.ajouteInst(new C3aInstFEnd(""));
        defaultOut(node);
        return null;
    }


    public C3aOperand visit(SaVarSimple node) {
        defaultIn(node);
        TsItemVar variable = node.tsItem;
        C3aVar var = new C3aVar(variable, null);
        defaultOut(node);
        return var;
    }

    public C3aOperand visit(SaVarIndicee node) {
        defaultIn(node);
        TsItemVar variable = node.tsItem;
        C3aOperand index = node.getIndice().accept(this);
        defaultOut(node);
        return new C3aVar(variable, index);
    }
}



