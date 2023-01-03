package com.spectral369.utils;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.layout.LayoutContext;
import com.itextpdf.layout.layout.LayoutResult;
import com.itextpdf.layout.properties.Property;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;
import com.itextpdf.layout.renderer.ParagraphRenderer;

public class AutoScalingParagraph extends Paragraph {
    Paragraph innerParagraph;

    public AutoScalingParagraph(Paragraph innerParagraph) {
        this.innerParagraph = innerParagraph;
    }

    @Override
    protected IRenderer makeNewRenderer() {
        return new AutoScalingParagraphRenderer(this);
    }
}

class AutoScalingParagraphRenderer extends ParagraphRenderer {
    private IRenderer innerRenderer;

    public AutoScalingParagraphRenderer(AutoScalingParagraph modelElement) {
        super(modelElement);
    }

    @Override
    public LayoutResult layout(LayoutContext layoutContext) {
        LayoutResult baseResult = super.layout(layoutContext);
        this.innerRenderer = ((AutoScalingParagraph)modelElement).innerParagraph.createRendererSubTree().setParent(this);
  
        if (baseResult.getStatus() == LayoutResult.FULL) {
            float fontSizeL = 0.0001f, fontSizeR= 10000;
            while (Math.abs(fontSizeL - fontSizeR) > 1e-1) {
                float curFontSize = (fontSizeL + fontSizeR) / 2;
                this.innerRenderer.setProperty(Property.FONT_SIZE, UnitValue.createPointValue(curFontSize));

                if (this.innerRenderer.layout(new LayoutContext(getOccupiedArea().clone())).getStatus() == LayoutResult.FULL) {
                    // we can fit all the text with curFontSize
                    fontSizeL = curFontSize;
                  
                } else {
                
                    fontSizeR = curFontSize;
                 
                }
            }
            
      
            if(fontSizeL> 12) {
            	
            	int diff = (int) ((int) fontSizeL-12);
            	
            	if(diff>2) {
            		//System.out.println("--> innersize: "+diff);
            //	getOccupiedArea().getBBox().setY(getOccupiedArea().getBBox().getY()+0.135f*(diff));
            	}
            	fontSizeL = 12;//orig 12
            }
            
            if(fontSizeL < 12) {
            	int diff = (int) ((int) 12f-fontSizeL);
            	getOccupiedArea().getBBox().setY(getOccupiedArea().getBBox().getY()-0.65f*(diff));//0.55f
            }
            
            
            this.innerRenderer.setProperty(Property.FONT_SIZE, UnitValue.createPointValue(fontSizeL));


      //      this.innerRenderer.layout(new LayoutContext(getOccupiedArea().clone()));
        }
        return baseResult;
    }

    @Override
    public void drawChildren(DrawContext drawContext) {
        super.drawChildren(drawContext);
        this.innerRenderer.layout(new LayoutContext(getOccupiedArea().clone()));
        this.innerRenderer.draw(drawContext);
    }

    @Override
    public IRenderer getNextRenderer() {
        return new AutoScalingParagraphRenderer((AutoScalingParagraph) modelElement);
    }
}
