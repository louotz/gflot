/*
 * Copyright (c) 2012 Nicolas Morel
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package com.googlecode.gflot.client.options;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author Alexander De Leon
 */
public class AxisOptions
    extends AbstractAxisOptions<AxisOptions>
{
    /**
     * Creates a {@link AxisOptions}
     */
    public static final AxisOptions create()
    {
        return JavaScriptObject.createObject().cast();
    }
    
    public enum ShowTickLabelsMode {
        NONE( "none" ), ENDPOINTS( "endpoints" ), MAJOR( "major" ), ALL( "all" );
        
        private final String flotValue;
        
        private ShowTickLabelsMode(String flotValue) {
            this.flotValue = flotValue;
        }
        
        public String getFlotValue() {
            return flotValue;
        }

        static ShowTickLabelsMode findByFlotValue( String flotValue )
        {
            if ( null != flotValue && !"".equals( flotValue ) )
            {
                for ( ShowTickLabelsMode mode : values() )
                {
                    if ( mode.getFlotValue().equals( flotValue ) )
                    {
                        return mode;
                    }
                }
            }
            return null;
        }
    }

    protected AxisOptions()
    {
    }

    /**
     * Set the ticks that will be displayed. The default is to display major ticks only.
     */
    public final AxisOptions setShowTickLabels( ShowTickLabelsMode mode )
    {
        put( SHOW_TICK_LABELS_KEY, mode.getFlotValue() );
        return this;
    }

    /**
     * @return the ticks displayed
     */
    public final ShowTickLabelsMode getShowTickLabels()
    {
        return ShowTickLabelsMode.findByFlotValue( getString( SHOW_TICK_LABELS_KEY ) );
    }

    /**
     * Set that you want to display only the major ticks.
     */
    public final AxisOptions clearShowTickLabels()
    {
        clear( SHOW_TICK_LABELS_KEY );
        return this;
    }

    /**
     * Set the tick interval size. If you set it to 2, you'll get ticks at 2, 4, 6, etc.
     */
    public final AxisOptions setTickSize( double tickSize )
    {
        put( TICK_SIZE_KEY, Double.valueOf( tickSize ) );
        return this;
    }

    /**
     * @return the tick interval size
     */
    public final Double getTickSize()
    {
        return getDouble( TICK_SIZE_KEY );
    }

    /**
     * Set that you don't want ticks at a size less than the specified one
     */
    public final AxisOptions setMinTickSize( double minTickSize )
    {
        put( MIN_TICK_SIZE_KEY, Double.valueOf( minTickSize ) );
        return this;
    }

    /**
     * @return the minimal tick size
     */
    public final Double getMinTickSize()
    {
        return getDouble( MIN_TICK_SIZE_KEY );
    }

    /**
     * Set the number of decimals to display (default is auto-detected).
     */
    public final AxisOptions setTickDecimals( double tickDecimals )
    {
        put( TICK_DECIMALS_KEY, Double.valueOf( tickDecimals ) );
        return this;
    }

    /**
     * @return the number of decimals to display
     */
    public final Double getTickDecimals()
    {
        return getDouble( TICK_DECIMALS_KEY );
    }

    /**
     * Clear the number of decimals to display
     */
    public final AxisOptions clearTickDecimals()
    {
        clear( TICK_DECIMALS_KEY );
        return this;
    }

    /**
     * Show minor ticks between the major ticks. Default is to show minor ticks.
     */
    public final AxisOptions setShowMinorTicks( boolean showMinorTicks )
    {
        put( SHOW_MINOR_TICKS_KEY, showMinorTicks );
        return this;
    }

    /**
     * @return whether the minor ticks are visible.
     */
    public final Boolean getShowMinorTicks()
    {
        return getBoolean( SHOW_MINOR_TICKS_KEY );
    }

    /**
     * Hide minor ticks.
     */
    public final AxisOptions clearShowMinorTicks()
    {
        clear( SHOW_MINOR_TICKS_KEY );
        return this;
    }

    /**
     * Set the visibility of all tick marks.
     */
    public final AxisOptions setShowTicks( boolean showTicks )
    {
        put( SHOW_TICKS_KEY, showTicks );
        return this;
    }

    /**
     * @return the visibility of all tick marks.
     */
    public final Boolean getShowTicks()
    {
        return getBoolean( SHOW_TICKS_KEY );
    }

    /**
     * Display all tick marks.
     */
    public final AxisOptions clearShowTicks()
    {
        clear( SHOW_TICKS_KEY );
        return this;
    }
}
