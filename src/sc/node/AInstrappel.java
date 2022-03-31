/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AInstrappel extends PInstrappel
{
    private PAppelfct _appelfct_;
    private TPointVirgule _pointVirgule_;

    public AInstrappel()
    {
        // Constructor
    }

    public AInstrappel(
        @SuppressWarnings("hiding") PAppelfct _appelfct_,
        @SuppressWarnings("hiding") TPointVirgule _pointVirgule_)
    {
        // Constructor
        setAppelfct(_appelfct_);

        setPointVirgule(_pointVirgule_);

    }

    @Override
    public Object clone()
    {
        return new AInstrappel(
            cloneNode(this._appelfct_),
            cloneNode(this._pointVirgule_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAInstrappel(this);
    }

    public PAppelfct getAppelfct()
    {
        return this._appelfct_;
    }

    public void setAppelfct(PAppelfct node)
    {
        if(this._appelfct_ != null)
        {
            this._appelfct_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._appelfct_ = node;
    }

    public TPointVirgule getPointVirgule()
    {
        return this._pointVirgule_;
    }

    public void setPointVirgule(TPointVirgule node)
    {
        if(this._pointVirgule_ != null)
        {
            this._pointVirgule_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._pointVirgule_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._appelfct_)
            + toString(this._pointVirgule_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._appelfct_ == child)
        {
            this._appelfct_ = null;
            return;
        }

        if(this._pointVirgule_ == child)
        {
            this._pointVirgule_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._appelfct_ == oldChild)
        {
            setAppelfct((PAppelfct) newChild);
            return;
        }

        if(this._pointVirgule_ == oldChild)
        {
            setPointVirgule((TPointVirgule) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
