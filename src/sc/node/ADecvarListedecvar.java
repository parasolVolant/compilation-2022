/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class ADecvarListedecvar extends PListedecvar
{
    private PDecvar _decvar_;

    public ADecvarListedecvar()
    {
        // Constructor
    }

    public ADecvarListedecvar(
        @SuppressWarnings("hiding") PDecvar _decvar_)
    {
        // Constructor
        setDecvar(_decvar_);

    }

    @Override
    public Object clone()
    {
        return new ADecvarListedecvar(
            cloneNode(this._decvar_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseADecvarListedecvar(this);
    }

    public PDecvar getDecvar()
    {
        return this._decvar_;
    }

    public void setDecvar(PDecvar node)
    {
        if(this._decvar_ != null)
        {
            this._decvar_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._decvar_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._decvar_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._decvar_ == child)
        {
            this._decvar_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._decvar_ == oldChild)
        {
            setDecvar((PDecvar) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}