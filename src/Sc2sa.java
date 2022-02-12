import sa.*;
import sc.analysis.DepthFirstAdapter;
import sc.node.*;

public class Sc2sa extends DepthFirstAdapter {

    private SaNode returnValue;

    @Override
    public void caseADecvarldecfoncProgramme(ADecvarldecfoncProgramme node) {
        SaLDec list1 = null;
        SaLDec list2 = null;
        node.getOptdecvar().apply(this);
        list1 = (SaLDec) this.returnValue;
        node.getListedecfonc().apply(this);
        list2 = (SaLDec) this.returnValue;
        this.returnValue = new SaProg(list1, list2);
    }

    @Override
    public void caseALdecfoncProgramme(ALdecfoncProgramme node) {
        SaLDec list = null;
        node.getListedecfonc().apply(this);
        list = (SaLDec) this.returnValue;
        this.returnValue = new SaProg(null, list);
    }

    @Override
    public void caseAOptdecvar(AOptdecvar node) {
        node.getListedecvar().apply(this);
    }

    @Override
    public void caseADecvarldecvarListedecvar(ADecvarldecvarListedecvar node) {
        SaDecVar var = null;
        SaLDec list = null;
        node.getDecvar().apply(this);
        var = (SaDecVar) this.returnValue;
        node.getListedecvarbis().apply(this);
        list = (SaLDec) this.returnValue;
        this.returnValue = new SaLDec(var, list);
    }

    @Override
    public void caseADecvarListedecvar(ADecvarListedecvar node) {
        SaDecVar var = null;
        node.getDecvar().apply(this);
        var = (SaDecVar) this.returnValue;
        this.returnValue = new SaLDec(var, null);
    }

    @Override
    public void caseADecvarldecvarListedecvarbis(ADecvarldecvarListedecvarbis node) {
        SaDecVar var = null;
        SaLDec list = null;
        node.getDecvar().apply(this);
        var = (SaDecVar) this.returnValue;
        node.getListedecvarbis().apply(this);
        list = (SaLDec) this.returnValue;
        this.returnValue = new SaLDec(var, list);
    }

    @Override
    public void caseADecvarListedecvarbis(ADecvarListedecvarbis node) {
        SaDecVar var = null;
        node.getDecvar().apply(this);
        var = (SaDecVar) this.returnValue;
        this.returnValue = new SaLDec(var, null);
    }

    @Override
    public void caseADecvarentierDecvar(ADecvarentierDecvar node) {


    }

    @Override
    public void caseADecvartableauDecvar(ADecvartableauDecvar node) {
        super.caseADecvartableauDecvar(node);
    }

    @Override
    public void caseALdecfoncrecListedecfonc(ALdecfoncrecListedecfonc node) {
        super.caseALdecfoncrecListedecfonc(node);
    }

    @Override
    public void caseALdecfoncfinalListedecfonc(ALdecfoncfinalListedecfonc node) {
        super.caseALdecfoncfinalListedecfonc(node);
    }

    @Override
    public void caseADecvarinstrDecfonc(ADecvarinstrDecfonc node) {
        super.caseADecvarinstrDecfonc(node);
    }

    @Override
    public void caseAInstrDecfonc(AInstrDecfonc node) {
        super.caseAInstrDecfonc(node);
    }

    @Override
    public void caseASansparamListeparam(ASansparamListeparam node) {
        super.caseASansparamListeparam(node);
    }

    @Override
    public void caseAAvecparamListeparam(AAvecparamListeparam node) {
        node.getListedecvar().apply(this);
    }

    @Override
    public void caseAInstraffectInstr(AInstraffectInstr node) {
        node.getInstraffect().apply(this);
    }

    @Override
    public void caseAInstrblocInstr(AInstrblocInstr node) {
        node.getInstrbloc().apply(this);
    }

    @Override
    public void caseAInstrsiInstr(AInstrsiInstr node) {
        node.getInstrsi().apply(this);
    }

    @Override
    public void caseAInstrtantqueInstr(AInstrtantqueInstr node) {
        node.getInstrtantque().apply(this);
    }

    @Override
    public void caseAInstrappelInstr(AInstrappelInstr node) {
        node.getInstrappel().apply(this);
    }

    @Override
    public void caseAInstrretourInstr(AInstrretourInstr node) {
        node.getInstrretour().apply(this);
    }

    @Override
    public void caseAInstrecritureInstr(AInstrecritureInstr node) {
        node.getInstrecriture().apply(this);
    }

    @Override
    public void caseAInstrvideInstr(AInstrvideInstr node) {
        node.getInstrvide().apply(this);
    }

    @Override
    public void caseAInstraffect(AInstraffect node) {
        SaVar var = null;
        SaExp exp = null;
        node.getVar().apply(this);
        var = (SaVar) this.returnValue;
        node.getExp().apply(this);
        exp = (SaExp) this.returnValue;
        this.returnValue = new SaInstAffect(var, exp);
    }

    @Override
    public void caseAInstrbloc(AInstrbloc node) {
        node.getListeinst().apply(this);
    }

    @Override
    public void caseALinstrecListeinst(ALinstrecListeinst node) {
        SaInst inst = null;
        SaLInst list = null;
        node.getInstr().apply(this);
        inst = (SaInst) this.returnValue;
        node.getListeinst().apply(this);
        list = (SaLInst) this.returnValue;
        this.returnValue = new SaLInst(inst, list);
    }

    @Override
    public void caseALinstfinalListeinst(ALinstfinalListeinst node) {
        super.caseALinstfinalListeinst(node);
    }

    @Override
    public void caseAAvecsinonInstrsi(AAvecsinonInstrsi node) {

    }

    @Override
    public void caseASanssinonInstrsi(ASanssinonInstrsi node) {
        super.caseASanssinonInstrsi(node);
    }

    @Override
    public void caseAInstrsinon(AInstrsinon node) {
        super.caseAInstrsinon(node);
    }

    @Override
    public void caseAInstrtantque(AInstrtantque node) {
        super.caseAInstrtantque(node);
    }

    @Override
    public void caseAInstrappel(AInstrappel node) {
        super.caseAInstrappel(node);
    }

    @Override
    public void caseAInstrretour(AInstrretour node) {
        super.caseAInstrretour(node);
    }

    @Override
    public void caseAInstrecriture(AInstrecriture node) {
        super.caseAInstrecriture(node);
    }

    @Override
    public void caseAInstrvide(AInstrvide node) {
        super.caseAInstrvide(node);
    }

    @Override
    public void caseAOuExp(AOuExp node) {
        SaExp op1 = null;
        SaExp op2 = null;
        node.getExp().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp1().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpOr(op1, op2);
    }

    @Override
    public void caseAExp1Exp(AExp1Exp node) {
        node.getExp1().apply(this);
    }

    @Override
    public void caseAEtExp1(AEtExp1 node) {
        SaExp op1 = null;
        SaExp op2 = null;
        node.getExp1().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp2().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpAnd(op1, op2);
    }

    @Override
    public void caseAExp2Exp1(AExp2Exp1 node) {
        node.getExp2().apply(this);
    }

    @Override
    public void caseAInfExp2(AInfExp2 node) {
        SaExp op1 = null;
        SaExp op2 = null;
        node.getExp2().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp3().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpInf(op1, op2);
    }

    @Override
    public void caseAEgalExp2(AEgalExp2 node) {
        SaExp op1 = null;
        SaExp op2 = null;
        node.getExp2().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp3().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpEqual(op1, op2);
    }

    @Override
    public void caseAExp3Exp2(AExp3Exp2 node) {
        node.getExp3().apply(this);
    }

    @Override
    public void caseAPlusExp3(APlusExp3 node) {
        SaExp op1 = null;
        SaExp op2 = null;
        node.getExp3().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp4().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpAdd(op1, op2);

    }

    @Override
    public void caseAMoinsExp3(AMoinsExp3 node) {
        SaExp op1 = null;
        SaExp op2 = null;
        node.getExp3().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp4().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpSub(op1, op2);
    }

    @Override
    public void caseAExp4Exp3(AExp4Exp3 node) {
        node.getExp4().apply(this);
    }

    @Override
    public void caseAFoisExp4(AFoisExp4 node) {
        SaExp op1 = null;
        SaExp op2 = null;
        node.getExp4().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp5().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpMult(op1, op2);
    }

    @Override
    public void caseADiviseExp4(ADiviseExp4 node) {
        SaExp op1 = null;
        SaExp op2 = null;
        node.getExp4().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp5().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpDiv(op1, op2);
    }

    @Override
    public void caseAExp5Exp4(AExp5Exp4 node) {
        node.getExp5().apply(this);
    }

    @Override
    public void caseANonExp5(ANonExp5 node) {
        SaExp op = null;
        node.getExp5().apply(this);
        op = (SaExp) this.returnValue;
        this.returnValue = new SaExpNot(op);
    }

    @Override
    public void caseAExp6Exp5(AExp6Exp5 node) {
        node.getExp6().apply(this);
    }

    @Override
    public void caseANombreExp6(ANombreExp6 node) {
        node.getNombre().apply(this);
    }

    @Override
    public void caseAAppelfctExp6(AAppelfctExp6 node) {
        super.caseAAppelfctExp6(node);
    }

    @Override
    public void caseAVarExp6(AVarExp6 node) {
        super.caseAVarExp6(node);
    }

    @Override
    public void caseAParenthesesExp6(AParenthesesExp6 node) {
        node.getExp().apply(this);
    }

    @Override
    public void caseALireExp6(ALireExp6 node) {
        node.getLire().apply(this);
    }

    @Override
    public void caseAVartabVar(AVartabVar node) {

    }

    @Override
    public void caseAVarsimpleVar(AVarsimpleVar node) {
        super.caseAVarsimpleVar(node);
    }

    @Override
    public void caseARecursifListeexp(ARecursifListeexp node) {
        SaExp exp = null;
        SaLExp list = null;
        node.getExp().apply(this);
        exp = (SaExp) this.returnValue;
        node.getListeexpbis().apply(this);
        list = (SaLExp) this.returnValue;
        this.returnValue = new SaLExp(exp, list);
    }

    @Override
    public void caseAFinalListeexp(AFinalListeexp node) {
        super.caseAFinalListeexp(node);
    }

    @Override
    public void caseARecursifListeexpbis(ARecursifListeexpbis node) {
        SaExp exp = null;
        SaLExp list = null;
        node.getExp().apply(this);
        exp = (SaExp) this.returnValue;
        node.getListeexpbis().apply(this);
        list = (SaLExp) this.returnValue;
        this.returnValue = new SaLExp(exp, list);
    }

    @Override
    public void caseAFinalListeexpbis(AFinalListeexpbis node) {
        super.caseAFinalListeexpbis(node);
    }

    @Override
    public void caseAAppelfct(AAppelfct node) {
        String identif = node.getIdentif().getText();
        SaLExp list = null;
        node.getListeexp().apply(this);
        list = (SaLExp) this.returnValue;
        this.returnValue = new SaAppel(identif, list);
    }
}
