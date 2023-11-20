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
package com.googlecode.gflot.client.resources;

import com.google.gwt.core.client.GWT;

public class FlotJavaScriptLoader
{
    public interface FlotJavaScriptCallback
    {
        void onError( Throwable caught );
        void onSuccess();
    }

    private static Impl impl = GWT.create( Impl.class );

    public static Impl get()
    {
        return impl;
    }

    public static interface Impl
    {
        void loadRequiredFlotLibrary( final FlotJavaScriptCallback callback );

        PluginLoader getJqueryLoader();
		PluginLoader getJqueryCanvasWrapperLoader();
		PluginLoader getJqueryColorHelperLoader();
        PluginLoader getFlotLoader();
        PluginLoader getFlotSelectionLoader();
        PluginLoader getFlotSymbolLoader();
        PluginLoader getFlotImageLoader();
        PluginLoader getFlotPieLoader();
        PluginLoader getFlotStackLoader();
        PluginLoader getExcanvasLoader();
        PluginLoader getFlotResizeLoader();
        PluginLoader getCanvas2ImageLoader();
        PluginLoader getFlotAxisLabelsLoader();
        PluginLoader getFlotTickRotorLoader();
        PluginLoader getFlotThresholdLoader();
        PluginLoader getFlotNavigateLoader();
        PluginLoader getFlotCrosshairLoader();
        PluginLoader getFlotOrderBarsLoader();
        PluginLoader getFlotFillBetweenLoader();
        PluginLoader getFlotFillAreaLoader();
        PluginLoader getFlotTimeLoader();
        PluginLoader getFlotCanvasLoader();
        PluginLoader getFlotErrorBarsLoader();
        PluginLoader getFlotCategoriesLoader();
        PluginLoader getFlotBackgroundLoader();
        PluginLoader getFlotFlatDataLoader();
        PluginLoader getFlotHoverLoader();
        PluginLoader getFlotLegendLoader();
        PluginLoader getFlotLogAxisLoader();
        PluginLoader getFlotTouchLoader();
        PluginLoader getFlotTouchNavigateLoader();
    }

    public static class SynchronousImpl
        implements Impl
    {
        private boolean loaded = false;

        private final PluginLoader jqueryLoader = GWT.create( JQueryLoader.class );
        private final PluginLoader jqueryCanvasWrapperLoader = GWT.create( JQueryCanvasWrapperLoader.class );
        private final PluginLoader jqueryColorHelperLoader = GWT.create( JQueryColorHelpersLoader.class );
        private final PluginLoader flotLoader = GWT.create( FlotLoader.class );
        private final PluginLoader flotSelectionLoader = GWT.create( FlotSelectionLoader.class );
        private final PluginLoader flotSymbolLoader = GWT.create( FlotSymbolLoader.class );
        private final PluginLoader flotImageLoader = GWT.create( FlotImageLoader.class );
        private final PluginLoader flotPieLoader = GWT.create( FlotPieLoader.class );
        private final PluginLoader flotStackLoader = GWT.create( FlotStackLoader.class );
        private final PluginLoader flotResizeLoader = GWT.create( ExplorerCanvasLoader.class );
        private final PluginLoader excanvasLoader = GWT.create( FlotResizeLoader.class );
        private final PluginLoader canvas2ImageLoader = GWT.create( Canvas2ImageLoader.class );
        private final PluginLoader flotAxisLabelsLoader = GWT.create( FlotAxisLabelsLoader.class );
        private final PluginLoader flotTickRotorLoader = GWT.create( FlotTickRotorLoader.class );
        private final PluginLoader flotThresholdLoader = GWT.create( FlotThresholdLoader.class );
        private final PluginLoader flotNavigateLoader = GWT.create( FlotNavigateLoader.class );
        private final PluginLoader flotCrosshairLoader = GWT.create( FlotCrosshairLoader.class );
        private final PluginLoader flotOrderBarsLoader = GWT.create( FlotOrderBarsLoader.class );
        private final PluginLoader flotFillBetweenLoader = GWT.create( FlotFillBetweenLoader.class );
        private final PluginLoader flotFillAreaLoader = GWT.create( FlotFillAreaLoader.class );
        private final PluginLoader flotTimeLoader = GWT.create( FlotTimeLoader.class );
        private final PluginLoader flotCanvasLoader = GWT.create( FlotCanvasLoader.class );
        private final PluginLoader flotErrorBarsLoader = GWT.create( FlotErrorBarsLoader.class );
        private final PluginLoader flotCategoriesLoader = GWT.create( FlotCategoriesLoader.class );
        private final PluginLoader flotBackgroundLoader = GWT.create( FlotBackgroundLoader.class );
		private final PluginLoader flotFlatDataLoader = GWT.create( FlotFlatDataLoader.class );
		private final PluginLoader flotHoverLoader = GWT.create( FlotHoverLoader.class );
		private final PluginLoader flotLegendLoader = GWT.create( FlotLegendLoader.class );
		private final PluginLoader flotLogAxisLoader = GWT.create( FlotLogAxisLoader.class );
		private final PluginLoader flotTouchLoader = GWT.create( FlotTouchLoader.class );
		private final PluginLoader flotTouchNavigateLoader = GWT.create( FlotTouchNavigateLoader.class );
 
        @Override
        public void loadRequiredFlotLibrary( final FlotJavaScriptCallback callback )
        {
            if ( !loaded )
            {
                load();
                loaded = true;
            }
            callback.onSuccess();
        }

        @Override
        public PluginLoader getJqueryLoader()
        {
        	return jqueryLoader;
        }

        @Override
        public PluginLoader getJqueryCanvasWrapperLoader()
        {
            return jqueryCanvasWrapperLoader;
        }

        @Override
        public PluginLoader getJqueryColorHelperLoader()
        {
            return jqueryColorHelperLoader;
        }

        @Override
        public PluginLoader getFlotLoader()
        {
            return flotLoader;
        }

        @Override
        public PluginLoader getFlotSelectionLoader()
        {
            return flotSelectionLoader;
        }

        @Override
        public PluginLoader getFlotSymbolLoader()
        {
            return flotSymbolLoader;
        }

        @Override
        public PluginLoader getFlotImageLoader()
        {
            return flotImageLoader;
        }

        @Override
        public PluginLoader getFlotPieLoader()
        {
            return flotPieLoader;
        }

        @Override
        public PluginLoader getFlotStackLoader()
        {
            return flotStackLoader;
        }

        @Override
        public PluginLoader getExcanvasLoader()
        {
            return excanvasLoader;
        }

        @Override
        public PluginLoader getFlotResizeLoader()
        {
            return flotResizeLoader;
        }

        @Override
        public PluginLoader getCanvas2ImageLoader()
        {
            return canvas2ImageLoader;
        }

        @Override
        public PluginLoader getFlotAxisLabelsLoader()
        {
            return flotAxisLabelsLoader;
        }

        @Override
        public PluginLoader getFlotTickRotorLoader()
        {
            return flotTickRotorLoader;
        }

        @Override
        public PluginLoader getFlotThresholdLoader()
        {
            return flotThresholdLoader;
        }

        @Override
        public PluginLoader getFlotNavigateLoader()
        {
            return flotNavigateLoader;
        }

        @Override
        public PluginLoader getFlotCrosshairLoader()
        {
            return flotCrosshairLoader;
        }

        @Override
        public PluginLoader getFlotOrderBarsLoader()
        {
            return flotOrderBarsLoader;
        }

        @Override
        public PluginLoader getFlotFillBetweenLoader()
        {
            return flotFillBetweenLoader;
        }

        @Override
        public PluginLoader getFlotFillAreaLoader()
        {
            return flotFillAreaLoader;
        }

        @Override
        public PluginLoader getFlotTimeLoader()
        {
            return flotTimeLoader;
        }

        @Override
        public PluginLoader getFlotCanvasLoader()
        {
            return flotCanvasLoader;
        }

        @Override
        public PluginLoader getFlotErrorBarsLoader()
        {
            return flotErrorBarsLoader;
        }

        @Override
        public PluginLoader getFlotCategoriesLoader()
        {
            return flotCategoriesLoader;
        }

        @Override
        public PluginLoader getFlotBackgroundLoader()
        {
            return flotBackgroundLoader;
        }
        
		@Override
		public PluginLoader getFlotFlatDataLoader() {
			return flotFlatDataLoader;
		}

		@Override
		public PluginLoader getFlotHoverLoader() {
			return flotHoverLoader;
		}

		@Override
		public PluginLoader getFlotLegendLoader() {
			return flotLegendLoader;
		}

		@Override
		public PluginLoader getFlotLogAxisLoader() {
			return flotLogAxisLoader;
		}

		@Override
		public PluginLoader getFlotTouchLoader() {
			return flotTouchLoader;
		}

		@Override
		public PluginLoader getFlotTouchNavigateLoader() {
			return flotTouchNavigateLoader;
		}

        private void load()
        {
            getJqueryLoader().load();
            getJqueryCanvasWrapperLoader().load();
            getJqueryColorHelperLoader().load();
            getFlotLoader().load();
            getFlotSelectionLoader().load();
            getFlotSymbolLoader().load();
            getFlotImageLoader().load();
            getFlotPieLoader().load();
            getFlotStackLoader().load();
            getFlotResizeLoader().load();
            getExcanvasLoader().load();
            getCanvas2ImageLoader().load();
            getFlotAxisLabelsLoader().load();
            getFlotTickRotorLoader().load();
            getFlotThresholdLoader().load();
            getFlotNavigateLoader().load();
            getFlotCrosshairLoader().load();
            getFlotOrderBarsLoader().load();
            getFlotFillBetweenLoader().load();
            getFlotFillAreaLoader().load();
            getFlotTimeLoader().load();
            getFlotCanvasLoader().load();
            getFlotErrorBarsLoader().load();
            getFlotCategoriesLoader().load();
            getFlotBackgroundLoader().load();
            getFlotFlatDataLoader().load(); 
            getFlotHoverLoader().load(); 
            getFlotLegendLoader().load(); 
            getFlotLogAxisLoader().load(); 
            getFlotTouchLoader().load(); 
            getFlotTouchNavigateLoader().load(); 
		}
    }
}
