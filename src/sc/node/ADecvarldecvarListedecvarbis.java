/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class ADecvarldecvarListedecvarbis extends PListedecvarbis
{
    private TVirgule _virgule_;
    private PDecvar _decvar_;
    private PListedecvarbis _listedecvarbis_;

    public ADecvarldecvarListedecvarbis()
    {
        // Constructor
    }

    public ADecvarldecvarListedecvarbis(
        @SuppressWarnings("hiding") TVirgule _virgule_,
        @SuppressWarnings("hiding") PDecvar _decvar_,
        @SuppressWarnings("hiding") PListedecvarbis _listedecvarbis_)
    {
        // Constructor
        setVirgule(_virgule_);

        setDecvar(_decvar_);

        setListedecvarbis(_listedecvarbis_);

    }

    @Override
    public Object clone()
    {
        return new ADecvarldecvarListedecvarbis(
            cloneNode(this._virgule_),
            cloneNode(this._decvar_),
            cloneNode(this._listedecvarbis_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseADecvarldecvarListedecvarbis(this);
    }

    public TVirgule getVirgule()
    {
        return this._virgule_;
    }

    public void setVirgule(TVirgule node)
    {
        if(this._virgule_ != null)
        {
            this._virgule_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._virgule_ = node;
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

    public PListedecvarbis getListedecvarbis()
    {
        return this._listedecvarbis_;
    }

    public void setListedecvarbis(PListedecvarbis node)
    {
        if(this._listedecvarbis_ != null)
        {
            this._listedecvarbis_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._listedecvarbis_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._virgule_)
            + toString(this._decvar_)
            + toString(this._listedecvarbis_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._virgule_ == child)
        {
            this._virgule_ = null;
            return;
        }

        if(this._decvar_ == child)
        {
            this._decvar_ = null;
            return;
        }

        if(this._listedecvarbis_ == child)
        {
            this._listedecvarbis_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._virgule_ == oldChild)
        {
            setVirgule((TVirgule) newChild);
            return;
        }

        if(this._decvar_ == oldChild)
        {
            setDecvar((PDecvar) newChild);
            return;
        }

        if(this._listedecvarbis_ == oldChild)
        {
            setListedecvarbis((PListedecvarbis) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
