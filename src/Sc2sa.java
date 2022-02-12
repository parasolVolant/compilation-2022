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
        node.getDecvar().apply(this);
    }

    @Override
    public void caseADecvarldecvarListedecvarbis(ADecvarldecvarListedecvarbis node) {
        super.caseADecvarldecvarListedecvarbis(node);
    }

    @Override
    public void caseADecvarListedecvarbis(ADecvarListedecvarbis node) {
        super.caseADecvarListedecvarbis(node);
    }

    @Override
    public void caseADecvarentierDecvar(ADecvarentierDecvar node) {
        super.caseADecvarentierDecvar(node);
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
        super.caseAAvecparamListeparam(node);
    }

    @Override
    public void caseAInstraffectInstr(AInstraffectInstr node) {
        super.caseAInstraffectInstr(node);
    }

    @Override
    public void caseAInstrblocInstr(AInstrblocInstr node) {
        super.caseAInstrblocInstr(node);
    }

    @Override
    public void caseAInstrsiInstr(AInstrsiInstr node) {
        super.caseAInstrsiInstr(node);
    }

    @Override
    public void caseAInstrtantqueInstr(AInstrtantqueInstr node) {
        super.caseAInstrtantqueInstr(node);
    }

    @Override
    public void caseAInstrappelInstr(AInstrappelInstr node) {
        super.caseAInstrappelInstr(node);
    }

    @Override
    public void caseAInstrretourInstr(AInstrretourInstr node) {
        super.caseAInstrretourInstr(node);
    }

    @Override
    public void caseAInstrecritureInstr(AInstrecritureInstr node) {
        super.caseAInstrecritureInstr(node);
    }

    @Override
    public void caseAInstrvideInstr(AInstrvideInstr node) {
        super.caseAInstrvideInstr(node);
    }

    @Override
    public void caseAInstraffect(AInstraffect node) {
        super.caseAInstraffect(node);
    }

    @Override
    public void caseAInstrbloc(AInstrbloc node) {
        super.caseAInstrbloc(node);
    }

    @Override
    public void caseALinstrecListeinst(ALinstrecListeinst node) {
        super.caseALinstrecListeinst(node);
    }

    @Override
    public void caseALinstfinalListeinst(ALinstfinalListeinst node) {
        super.caseALinstfinalListeinst(node);
    }

    @Override
    public void caseAAvecsinonInstrsi(AAvecsinonInstrsi node) {
        super.caseAAvecsinonInstrsi(node);
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
        super.caseAOuExp(node);
    }

    @Override
    public void caseAExp1Exp(AExp1Exp node) {
        super.caseAExp1Exp(node);
    }

    @Override
    public void caseAEtExp1(AEtExp1 node) {
        super.caseAEtExp1(node);
    }

    @Override
    public void caseAExp2Exp1(AExp2Exp1 node) {
        super.caseAExp2Exp1(node);
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
        super.caseAExp3Exp2(node);
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
        super.caseAExp4Exp3(node);
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
        super.caseAExp5Exp4(node);
    }

    @Override
    public void caseANonExp5(ANonExp5 node) {
        super.caseANonExp5(node);
    }

    @Override
    public void caseAExp6Exp5(AExp6Exp5 node) {
        super.caseAExp6Exp5(node);
    }

    @Override
    public void caseANombreExp6(ANombreExp6 node) {
        super.caseANombreExp6(node);
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
        super.caseAParenthesesExp6(node);
    }

    @Override
    public void caseALireExp6(ALireExp6 node) {
        super.caseALireExp6(node);
    }

    @Override
    public void caseAVartabVar(AVartabVar node) {
        super.caseAVartabVar(node);
    }

    @Override
    public void caseAVarsimpleVar(AVarsimpleVar node) {
        super.caseAVarsimpleVar(node);
    }

    @Override
    public void caseARecursifListeexp(ARecursifListeexp node) {
        super.caseARecursifListeexp(node);
    }

    @Override
    public void caseAFinalListeexp(AFinalListeexp node) {
        super.caseAFinalListeexp(node);
    }

    @Override
    public void caseARecursifListeexpbis(ARecursifListeexpbis node) {
        super.caseARecursifListeexpbis(node);
    }

    @Override
    public void caseAFinalListeexpbis(AFinalListeexpbis node) {
        super.caseAFinalListeexpbis(node);
    }

    @Override
    public void caseAAppelfct(AAppelfct node) {
        super.caseAAppelfct(node);
    }
}
