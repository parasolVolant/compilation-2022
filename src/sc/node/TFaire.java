/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class TFaire extends Token
{
    public TFaire()
    {
        super.setText("faire");
    }

    public TFaire(int line, int pos)
    {
        super.setText("faire");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TFaire(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTFaire(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TFaire text.");
    }
}
